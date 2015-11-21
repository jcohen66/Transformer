import actors.SupervisorActor
import akka.actor.{Props, ActorSystem}
import akka.testkit.{EventFilter, CallingThreadDispatcher, TestKit}
import data.RawMessage
import housekeeping.StopSystemAfterAll
import org.scalatest.WordSpecLike

class SupervisorActor01Test extends TestKit(ActorSystem("testsystem"))
with WordSpecLike
with StopSystemAfterAll {

  "The actors.SupervisorActor" must {
    "say testing when a RawMessage(\"testing\") is sent to it" in {
      val dispatcherId = CallingThreadDispatcher.Id

      val props = Props[SupervisorActor].withDispatcher(dispatcherId)
      val top = system.actorOf(props)

      // Intercept the log messages that were logged
      EventFilter.info(message = "actors.SupervisorActor - testing",
        occurrences = 1).intercept {
        top ! RawMessage("testing")
      }
    }
  }

}
