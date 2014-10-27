package com.providers

/**
 * Created by bo on 27/10/14.
 */
trait LocationStorageProvider {

  def getLocationStorage:LocationStorage

}
class FileLocationStorageProvider(fileLocationPath:String) extends LocationStorageProvider
{

  override def getLocationStorage: LocationStorage = new FileLocationStorage(fileLocationPath)
}
trait LocationStorage
{
  def save(lat:String,long:String,carId:String,ts:String)
  def getPreviouseLocation(from:String,to:String):List[(String,String)]
}

class FileLocationStorage(fileLocationPath:String) extends LocationStorage
{

  def save(lat:String,long:String,carId:String,ts:String)=
  {

  }
  def getPreviouseLocation(from:String,to:String):List[(String,String)]= ???

}
