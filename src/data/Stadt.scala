package data

class Stadt(stadtname: String, besetztVon: Spieler) {
  var name: String = stadtname
  var besetzer: Spieler = besetztVon
  var nachbarSet: Set[Stadt] = Set()
  var besetzt: Boolean = false

  //Besetzer von ROM und KARTHAGO direkt am Start machen tun
  def vorkonfiguration() = {
    if (this.name == "Rom") {
      this.besetzt = true
      this.besetzer = {
        if (HauptmenuGUI.spieler1.name == "Rom") { HauptmenuGUI.spieler1 } else { HauptmenuGUI.spieler2 }
      }
    } else {
      if (this.name == "Karthago") {
        this.besetzt = true
        this.besetzer = {
          if (HauptmenuGUI.spieler1.name == "Karthago") { HauptmenuGUI.spieler1 } else { HauptmenuGUI.spieler2 }
        }
      }
    }
  }
 
  
  /*
  def istBesetzt(pruefstadt: Stadt): Boolean = {
    if (pruefstadt.besetzer != Dummy.dummyspieler & pruefstadt.besetzt == true) { true } else { false }
  }
*/
  def wirdBesetzt() = {
    this.besetzer = HauptmenuGUI.IstDran
    this.besetzt = true
  }

  def istBesetzt(): Boolean = {
    if (this.besetzer != Dummy.dummyspieler & this.besetzt == true) { true } else { false }
  }

  def wirdBefreit() = {
    // print(this.besetzer)
    this.besetzer = Dummy.dummyspieler
    this.besetzt = false
    // print(this.besetzer)
  }

  override def toString: String = name

  def getNachbarn(): Set[Stadt] = { nachbarSet }
  def istUmzingelt(spieler: Spieler): Boolean = {
    for (i <- nachbarSet) {
      if (i.besetzer != spieler) { return false }
    }
    true
  }
}
