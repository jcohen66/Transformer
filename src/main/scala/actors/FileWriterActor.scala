package actors

import akka.actor.Actor
import akka.event.Logging
import data.{RawMessage, WriteFile}
import util.TextFileWriter

/**
  *
  */
class FileWriterActor extends Actor {
  val log = Logging(context.system, this)


  override def receive: Receive = {

    case WriteFile(filename, json: String) =>

      log.info("writing JSON file: " + filename)
      val tfw = TextFileWriter.writeFile(filename, json)

      log.info(s"wrote: ${filename} from file consisting of ${json.size} bytes")
      sender ! "Done"


    case RawMessage("testing") => log.info("testing")
    case _ => log.info("received something unexpected.")
  }

}
