package data

class Spieler(var name: String) {
  val historie = new Historie

  var punktestand: Int = 0
  var spielerName: String = name
  var spielerSeite: String = _

  override def toString: String = { name }
}