package com.actors

import akka.actor.{Actor, Props}
import com.model.{LocationMessage, LocationRequestJob, TubeCloseCheckMessage}
import com.providers.{FileLocaitonDataProvider, LocationStorageProvider}

/**
 * Created by bo on 26/10/14.
 */
class TaxCentralSystem extends Actor{
  this:LocationStorageProvider=>

  override def receive: Receive =
  {
    //Taxi send the location message
    case LocationMessage(lat,long,carId)=>
    {
      val timestamp: Long = System.currentTimeMillis
      getLocationStorage.save(lat,long,carId,timestamp.toString)

    }
    //Taxi request the location
    case LocationRequestJob(locationMessage,jobId)=>
    {

      val GPSLocationSystemActor=context.actorOf(Props[GPSSystem with FileLocaitonDataProvider])
      GPSLocationSystemActor ! locationMessage


    }
    //get whether close station request
    case TubeCloseCheckMessage(locationMesage)=>
    {
      val LocationSystemActor=context.actorOf(Props[LocationSystem with FileLocaitonDataProvider])
      LocationSystemActor ! locationMesage

    }

  }
}
