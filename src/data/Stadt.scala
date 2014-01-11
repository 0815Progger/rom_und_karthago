package data

class Stadt(stadtname: String, besetztVon: Spieler) {
  var name: String = stadtname
  var besetzer: Spieler = besetztVon
  var nachbarSet: Set[Stadt] = Set()

  // add und remove einfach durch set + stadt bzw set - stadt

  //override def toString:String = "Stadt: "+name+" ist besetzt von "+besetztVon.toString
  override def toString: String = name

  def getNachbarn(): Set[Stadt] = { nachbarSet }
  def istUmzingelt(spieler: Spieler): Boolean = {
    for (i <- nachbarSet) {
      if (i.besetzer != spieler) { return false }
    }
    true
  }
}
