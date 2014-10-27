package com.main

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import akka.kernel.Bootable
import com.frontend.RestInterface
import com.typesafe.config.ConfigFactory
import spray.can.Http

/**
 * Created by bo on 26/10/14.
 */
class TaxiSystemMain(actorSys:ActorSystem) extends Bootable{
  val config = ConfigFactory.load()
  val host = config.getString("spray.can.server.http.host")
  val port = config.getInt("spray.can.server.http.port")
  implicit val system = actorSys
  val api = system.actorOf(Props(new RestInterface()), "httpInterface")

  override def startup(): Unit =
  {



    IO(Http) ! Http.Bind(listener = api, interface = host, port = port)

  }

  override def shutdown(): Unit =
  {


  }
}
