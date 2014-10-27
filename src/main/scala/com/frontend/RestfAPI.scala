package com.frontend

import akka.actor._
import akka.util.Timeout
import com.actors.TaxCentralSystem
import com.model._
import spray.http.StatusCodes
import spray.routing.{RequestContext, _}

import scala.concurrent.duration._

class RestInterface extends HttpServiceActor
with RestApi {
  def receive = runRoute(routes)
}

trait RestApi extends HttpService with ActorLogging { actor: Actor =>

  implicit val timeout = Timeout(10 seconds)
  import akka.pattern.{ask, pipe}

  val TaxiCentralSystem= context.actorOf(Props[TaxCentralSystem])

  def routes: Route =

    path("tubeclose") {
      put {
        entity(as[TubeCloseCheckMessage]) { tubeEvent => requestContext =>
          val responder = createResponder(requestContext)
          TaxiCentralSystem.ask(tubeEvent).pipeTo(responder)
        }
      }
    } ~
      path("getlocation") {
        get {
          entity(as[LocationRequestJob]) { locationRequest => requestContext =>
            val responder = createResponder(requestContext)
            TaxiCentralSystem.ask(locationRequest).pipeTo(responder)
          }
        }
      } ~
      path("location") {
        get{
          entity(as[LocationMessage]) { ticketRequest => requestContext =>
            val responder = createResponder(requestContext)
            TaxiCentralSystem.ask(ticketRequest).pipeTo(responder)
          }
        }
      }
  def createResponder(requestContext:RequestContext) = {
    context.actorOf(Props(new Responder(requestContext, TaxiCentralSystem)))
  }

}

class Responder(requestContext:RequestContext, taxiCentralSystem:ActorRef) extends Actor with ActorLogging {

  def receive = {

    case TubeCloseIndicator(indicator) =>

      requestContext.complete(StatusCodes.OK, indicator)
      self ! PoisonPill

    case Location(location) =>
      requestContext.complete(StatusCodes.OK, location)
      self ! PoisonPill

  }
}

