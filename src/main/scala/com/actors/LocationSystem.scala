package com.actors

import akka.actor.Actor
import com.model.{TubeCloseCheckMessage, LocationMessage}
import com.providers.LocationDataProvider

/**
 * Created by bo on 26/10/14.
 */
class LocationSystem extends Actor{
  this:LocationDataProvider=>
  override def receive: Receive =
  {
    case LocationMessage(lat,long)=>
    {
      sender ! getLocationData(lat,long)
    }
    case TubeCloseCheckMessage=>
    {
      sender ! get
    }
  }
}
