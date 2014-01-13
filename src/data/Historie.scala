package data

class Historie {
  var leereZuege: Int = 0

  var letzterZug: Stadt = Dummy.dummytown
  //  var vorletzterZug:Stadt = _

  def clear() = {
    this.leereZuege = 0
    this.letzterZug = Dummy.dummytown
    //    this.vorletzterZug=null
  }

  def wiederholung(spielzug: Stadt): Boolean = {
    if (spielzug.name == letzterZug.name) { false }
    else { this.letzterZug = spielzug; true }
  }

}