package actors

import java.io.StringWriter

import akka.actor.Actor
import akka.event.Logging
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import data.Client

class TransformerActor extends Actor {
  val log = Logging(context.system, this)
  val mapper = new ObjectMapper()

  mapper.registerModule(DefaultScalaModule)
  val out = new StringWriter


  def receive = {
    case c: Client  => val json = packageAsJson(c)
                        sender ! json
    case _          => log.info("received something unexpected.")
  }

  def packageAsJson(c: Client): String = {
    val out = new StringWriter
    mapper.writeValue(out, c)
    val json = out.toString
    json
  }
}
