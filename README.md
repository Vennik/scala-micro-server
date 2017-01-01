# Scala Micro Server
A  simple Scala Micro Server. Routing can be done with regular expressions or literals.

## Example
```
val contact: Regex = """\/contact(?:\/(.+))?""".r
new Server({
  case contact(int(id)) => "Contact int " + id
  case contact(str(name)) => "Contact string " + name
  case contact(_*) => "Contact nothing"
  case "/" => "Home"
}, 80)
```
```
object int {
  def unapply(s: String): Option[Int] = Try(s.toInt).toOption
}
object str {
  def unapply(s: String): Option[String] = Option(s)
}
```
