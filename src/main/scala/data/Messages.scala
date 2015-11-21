package data

case class LoadFile(filename: String)
case class Client(id: Int, firstName: String, lastName: String)
case class ReadFile(filename: String)
case class WriteFile(filename: String, json:String)
case class RawMessage(message: String)

case object Done
