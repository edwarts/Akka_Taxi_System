package com.actors

import akka.actor.Actor
import com.model.{Location, LocationMessage}
import com.providers.LocationDataProvider

/**
 * Created by bo on 26/10/14.
 */
class GPSSystem extends Actor{
  this:LocationDataProvider=>

  override def receive: Receive =
  {
    case LocationMessage(lat,long)=>
    {
      val location=getLocationData(lat,long)
      sender ! Location(location)

    }

  }
}
