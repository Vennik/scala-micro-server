import scala.util.Try
import scala.util.matching.Regex

/**
  * @author René Vennik
  * @version 1.0
  * @since 22-12-2016
  */
object Example {

  val contact: Regex = """\/contact(?:\/(.+))?""".r

  def main(args: Array[String]): Unit = {
    new Server({
      case contact(int(id)) => "Contact int " + id
      case contact(str(name)) => "Contact string " + name
      case contact(_*) => "Contact nothing"
      case "/" => "Home"
    }, 80)
  }

}

object int {
  def unapply(s: String): Option[Int] = Try(s.toInt).toOption
}

object str {
  def unapply(s: String): Option[String] = Option(s)
}
