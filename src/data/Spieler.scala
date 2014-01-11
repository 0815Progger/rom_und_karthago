package data

class Spieler(var name: String) {
  val historie = new Historie
  //Wenn beide Spieler hintereinander jeweils 2 mal aussetzen.
  def staedte: List[Stadt] = { Nil }
  var punktestand: Int = 0

  override def toString: String = { name }
  /*  def macheZug(stadt:Stadt):Unit = {
    
    if(historie.pruefe(staedteliste, stadt)){}
  }

* 
*/

}

