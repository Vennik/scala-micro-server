import java.net.InetSocketAddress

import com.sun.net.httpserver.{HttpExchange, HttpHandler, HttpServer}

import scala.util.Try

/**
  * @author René Vennik
  * @version 1.0
  * @since 22-12-2016
  */
class Server(val routes: String => Any, val port: Int = 80) {

  val server: HttpServer = HttpServer.create(new InetSocketAddress(port), 0)
  server.createContext("/", new Handler())
  server.setExecutor(null)
  server.start()

  class Handler extends HttpHandler {

    def handle(t: HttpExchange): Unit = {
      Try(routes(t.getRequestURI.getPath).toString).toOption match {
        case Some(response) => handleResponse(t, 200, response)
        case _ => handleResponse(t, 404, "404 - Not Found")
      }
    }

    def handleResponse(t: HttpExchange, rCode: Int, response: String): Unit = {
      t.sendResponseHeaders(rCode, response.length())
      val os = t.getResponseBody
      os.write(response.getBytes)
      os.close()
    }

  }

}

object Int {
  def unapply(s: String): Option[Int] = Try(s.toInt).toOption
}

object String {
  def unapply(s: String): Option[String] = Option(s)
}