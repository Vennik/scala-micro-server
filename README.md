# Scala Micro Server
A  simple Scala Micro Server. Routing can be done with regular expressions or literals.

## Example
```
new Server({
  case contact(Int(id)) => "Contact int " + id
  case contact(String(name)) => "Contact string " + name
  case contact(_*) => "Contact nothing"
  case "/" => "Home"
}, 80)
```
