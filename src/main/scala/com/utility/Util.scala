package com.utility

/**
 * Created by bo on 27/10/14.
 */
object Util {

  def approximateLocationChecker(lat:Double,long:Double,expectedLat:Double,expectedLong:Double,offset:Double): Boolean=
  {
    if(math.sqrt(math.pow((lat-expectedLat),2.0)+math.pow((long-expectedLong),2.0))<=offset)
    {
      true
    }
    else
      false
  }

}
