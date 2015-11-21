package housekeeping

import akka.actor.ActorSystem
import spray.can.Http

trait ShutdownIfNotBound {

  import scala.concurrent.{ExecutionContext, Future}

  def shutdownIfNotBound(f: Future[Any]) //<co id="ch02_shutdownIfNotBound"/>
                        (implicit system: ActorSystem, ec: ExecutionContext) = {
    f.mapTo[Http.Event].map {
      case Http.Bound(address) =>
        println(s"REST interface bound to $address")
      case Http.CommandFailed(cmd) => //<co id="http_command_failed"/>
        println(s"REST interface could not bind: ${cmd.failureMessage}, shutting down.")
        system.shutdown()
    }.recover {
      case e: Throwable =>
        println(s"Unexpected error binding to HTTP: ${e.getMessage}, shutting down.")
        system.shutdown()
    }
  }
}
