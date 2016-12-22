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
      case contact(Int(id)) => "Contact int " + id
      case contact(String(name)) => "Contact string " + name
      case contact(_*) => "Contact nothing"
      case "/" => "Home"
    }, 80)
  }

}
