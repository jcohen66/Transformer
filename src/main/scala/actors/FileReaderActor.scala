package actors

import akka.actor.{Actor, ActorLogging}
import data.{Client, RawMessage, ReadFile}
import util.TextFileReader

/**
  *
  */
class FileReaderActor extends Actor with ActorLogging {
  // val log = Logging(context.system, this)


  override def receive: Receive = {

    case ReadFile(filename) =>

      log.info("reading file: " + filename)
      val tfr = TextFileReader.readFile(filename)
      while(tfr.hasNext) {
        val line = tfr.next
        val args = parseCSVFormat(line)
        val client = Client(args(0).toInt, args(1), args(2))
        log.info(s"Read: ${client.toString} from file")
        sender ! client
      }

    case RawMessage("testing") => log.info("testing")
    case _ => log.info("received something unexpected.")
  }

  def parseCSVFormat(line: String): Array[String] = {
    line.split(",")
  }
}
