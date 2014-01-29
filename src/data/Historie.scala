package data

class Historie {
  var leereZuege: Int = 0
  var letzterZug: String = " "


  def clear() = {
    this.leereZuege = 0
    this.letzterZug = " "
  }

  def istWiederholung(spielzug: String): Boolean = { //trägt außerdem auch den letzten Zug ein!
    if (spielzug == letzterZug) { true }
    else { this.letzterZug = spielzug; false }
  }

}