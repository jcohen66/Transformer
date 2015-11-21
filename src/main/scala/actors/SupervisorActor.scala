package actors

import akka.actor.{Actor, Props}
import akka.event.Logging
import akka.pattern.ask
import akka.routing.RoundRobinPool
import akka.util.Timeout
import data._

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps


class SupervisorActor extends Actor {

  val log = Logging(context.system, this)

  // File locations
  val readFilePath = "/Users/jcohen66/src/scala/Transformer/"
  val writeFilePath = "/Users/jcohen66/src/scala/Transformer/"

  // Worker actor pools
  val fileReaderActors = context.system.actorOf(Props[FileReaderActor].withRouter(RoundRobinPool(10)), "filereaderactorpool")
  val transformerActors = context.system.actorOf(Props[TransformerActor].withRouter(RoundRobinPool(10)), "transformeractors")
  val fileWriterActors = context.system.actorOf(Props[FileWriterActor].withRouter(RoundRobinPool(10)), "filewriteractors")

  // Load the data from file.
  fileReaderActors ! ReadFile(readFilePath + "test_csv.txt")

  // Event processing
  override def receive: Receive = {

    case l: LoadFile => log.info("reauest to load file: " + l.filename)
      fileReaderActors ! ReadFile(l.filename)

    case c: Client => log.info(s"received Client: " + c)
      implicit val timeout = Timeout(5 seconds)
      val future = transformerActors ? c
      val result = Await.result(future, timeout.duration).asInstanceOf[String]
      fileWriterActors ! WriteFile(getFilename(c), result)

    //    case anotherMethod: String => val future2: Future[String] = ask(transformerActor, anotherMethod).mapTo[String]
    //                    val result2 = Await.result(future2, 1 second)

    case "Done" => log.info("File write operation complete")

    case Done => context.system.shutdown()

    case RawMessage("testing") => log.info("testing")

    case _ => {
      log.info("received something unexpected")
    }
  }

  def getFilename(c: Client): String = {
    writeFilePath + c.id + ".json"
  }
}
