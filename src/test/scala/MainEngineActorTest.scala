import actors.SupervisorActor
import akka.actor.{ActorSystem, Props}
import akka.testkit.CallingThreadDispatcher
import akka.util.Timeout
import data.{Done, LoadFile}
import org.junit.Test

import scala.concurrent.duration._

class MainEngineActorTest {

  val readFilePath = "/Users/jcohen66/src/scala/Transformer/"
  val writeFilePath = "/Users/jcohen66/src/scala/Transformer/"

    @Test
    def testMainActor () = {
      val dispatcherId = CallingThreadDispatcher.Id
      val _system = ActorSystem("testsystem")
      val master = _system.actorOf(Props[SupervisorActor].withDispatcher(dispatcherId), name = "supervisoractor")

      println ("Created Supervisor Actor")


      implicit val timeout = Timeout(5 seconds)

      println ("Sending messages")

      for (i <- ( 1 to 1000)) {
        master ! LoadFile(readFilePath + "test_csv.txt")
        master ! LoadFile(readFilePath + "test_csv2.txt")
      }

      Thread.sleep(4000)

      master ! Done
    }



}
