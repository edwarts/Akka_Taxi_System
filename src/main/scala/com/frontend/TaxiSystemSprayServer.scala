package com.frontend

trait ResfulServerProvider
{
  def gettHttpRestfulServer
}
trait TaxiSystemHttpRestfulServerInterface
{

}
trait TaxiSystemHttpServerProvider extends ResfulServerProvider
{
  def getHttpRestfulServer: TaxiSystemHttpRestfulServerInterface=
  {
    return new TaxiSystemSprayServer()

  }
}
class TaxiSystemSprayServer extends TaxiSystemHttpRestfulServerInterface
{


}
