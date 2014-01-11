package data

class Historie {
  var leereZuege: Int = 0

  var letzterZug: Stadt = _
  //  var vorletzterZug:Stadt = _

  def clear() = {
    this.leereZuege = 0
    this.letzterZug = new Stadt("dummy", new Spieler("dummy"))
    //    this.vorletzterZug=null
  }

  def wiederholung(spielzug: Stadt): Boolean = {
    if (spielzug.name == letzterZug.name) { false }
    else { this.letzterZug = spielzug; true }
  }

}