import java.net.InetSocketAddress

import com.sun.net.httpserver.{HttpExchange, HttpServer}

import scala.util.{Success, Try}

/**
  * @author René Vennik
  * @version 1.0
  * @since 22-12-2016
  */
class Server(val routes: String => Any, val port: Int = 80) {

  val server: HttpServer = HttpServer.create(new InetSocketAddress(port), 0)
  server.createContext("/", (httpExchange: HttpExchange) => {
    Try(routes(httpExchange.getRequestURI.getPath).toString) match {
      case Success(response) => handleResponse(httpExchange, 200, response)
      case _ => handleResponse(httpExchange, 404, "404 - Not Found")
    }
  })
  server.start()

  def handleResponse(httpExchange: HttpExchange, rCode: Int, response: String): Unit = {
    httpExchange.sendResponseHeaders(rCode, response.length)
    val os = httpExchange.getResponseBody
    os.write(response.getBytes)
    os.close()
  }

}

object Int {
  def unapply(s: String): Option[Int] = Try(s.toInt).toOption
}

object String {
  def unapply(s: String): Option[String] = Option(s)
}