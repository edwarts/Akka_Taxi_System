package com.providers

/**
 * Created by bo on 27/10/14.
 */
trait LocationDataProvider {

  def getLocationData(lat:String,long:String):String
  def isCloseToTube(lat:String,long:String):String
}
class FileLocaitonDataProvider extends LocationDataProvider
{

  lazy val fileLocationData=scala.io.Source.fromFile("locationFile.tsv").getLines().toList
  override def getLocationData(lat:String,long:String): String =
  {
    import com.utility.Util

    // data storage in TSV file format
    fileLocationData.filter(p=>{

      val dataArray=p.split("t")
      Util.approximateLocationChecker(lat.toDouble,long.toDouble,dataArray(0).toDouble,dataArray(1).toDouble,5.0)

    }).toList.map(result=>{
      val dataArray=result.split("t")
      dataArray(2)
    }).head

  }

  override def isCloseToTube(lat: String, long: String): String =
  {

  }
}


