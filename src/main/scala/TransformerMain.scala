import actors.SupervisorActor
import akka.actor.{Props, ActorSystem}

object TransformerMain extends App {

  val actorSystem = ActorSystem("transformer")

  val top = actorSystem.actorOf(Props[SupervisorActor], "supervisor")

}
