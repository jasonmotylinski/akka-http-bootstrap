import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._

import scala.io.StdIn
/**
 * Created by jasonm on 3/28/17.
 */
final case class Person(name: String)

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val personFormat = jsonFormat1(Person)
}

object WebServer extends Directives with JsonSupport {


  def main(args: Array[String]) {


    implicit val system = ActorSystem("api")
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher

    val route =
      path("ok") {
        get {

          complete(Person("Jason"))
        }
      }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done

  }
}
