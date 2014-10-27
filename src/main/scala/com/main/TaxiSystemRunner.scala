package com.main

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory

/**
 * Created by bo on 26/10/14.
 */


object TaxiSystemRunner {
  val SYSTEM_NAME = "taxisystem"
  val CONFIG_FILE = "application.conf"
  def main(args:Array[String]): Unit =
  {
    val system = ActorSystem(SYSTEM_NAME, ConfigFactory.load(CONFIG_FILE)) //inject a main ActorSystem with the config only once at the start of the sytem

    val bootable = new TaxiSystemMain(system)
    bootable.startup()

    println(s"$SYSTEM_NAME has started. Press Enter to shutdown.")
    readLine()
    bootable.shutdown()
  }

}
