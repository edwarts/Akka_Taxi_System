package com.model

/**
 * Created by bo on 27/10/14.
 */

case class LocationMessage(lat:String,long:String,carId:String)
case class LocationRequestJob(locationInfo:LocationMessage,jobId:String)
case class LocationRequestMessage(locationInfo:LocationMessage)
case class TubeCloseCheckMessage(locationInfo:LocationMessage)
case class Location(locationName:String)
case class TubeCloseIndicator(indicator:String)

