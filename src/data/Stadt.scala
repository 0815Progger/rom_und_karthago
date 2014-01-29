package data

class Stadt(stadtname: String, besetztVon: Spieler) {
  var name: String = stadtname //Name der Stadt. Nicht mehr. Nicht weniger.
  var besetzer: Spieler = besetztVon //Spieler der die Stadt besetzt hält
  var nachbarSet: Set[Stadt] = Set() //Generiert ein leeres Set (?) (zur späteren Verwendung)
  var besetzt: Boolean = false //Zusätzlicher Kontrollwert: Ist diese Stadt besetzt? Standard: Nein
  var geprueft: Boolean = false
  var umzingelt: Boolean = false

  //#####################################################################

  //Besetzer von ROM und KARTHAGO direkt am Start
  def vorkonfiguration() = {
    if (this.name == "Rom") {
      this.besetzt = true
      this.besetzer = { ConsoleGame.werIstRom }

    } else {
      if (this.name == "Karthago") {
        this.besetzt = true
        this.besetzer = { ConsoleGame.werIstKar }
      }
    }
  }

  //#####################################################################

  //Der Name ist Programm
  def wirdBesetzt(spieler: Spieler) = {
    this.besetzer = spieler
    this.besetzt = true
  }

  //#####################################################################

  //Der Name ist Programm
  def istBesetzt(): Boolean = {
    if (this.besetzer != Dummy.dummyspieler & this.besetzt == true) { true } else { false }
  }

  //#####################################################################

  //Der Name ist Programm
  def wirdBefreit() = {
    // print(this.besetzer)
    this.besetzer = Dummy.dummyspieler
    this.besetzt = false
    // print(this.besetzer)
  }

  //#####################################################################

  //toString gibt lediglich den Stadtnamen zurück
  override def toString: String = name

  //#####################################################################

  //Methode zum Lesen der Nachbarstädte (schätze ich)
  def getNachbarn(): Set[Stadt] = { nachbarSet }

  //#####################################################################

  //Methode zum Erkennen, ob eine Stadt von durch den Gegner besetzten Städten umgeben ist

  def istUmzingelt(spieler: Spieler, nachbarn: Set[Stadt]): Boolean = {
    if (this.geprueft == false) {

      this.geprueft = true
      var rueckgabe: Boolean = false			;println(this.name+" "+this.hatNeutraleNachbarn+"NEUTR")
      if (this.hatNeutraleNachbarn == true) {
        return false
      } else {			println(this.name+" "+this.hatFreundlicheNachbarn(spieler)+"FREUNDL");
        if (this.hatFreundlicheNachbarn(spieler) == true) {
          for (i <- this.nachbarSet) {
            if (i.besetzer == spieler) {
              if (i.istUmzingelt(spieler, i.nachbarSet) == false) { rueckgabe = false } else { rueckgabe = true }

            }
          }
        } else {this.umzingelt = true}
      }
      this.umzingelt = rueckgabe
      rueckgabe
    } else { false }
  }

  /*     
      umzingelt = {
        if (this.hatNeutraleNachbarn == true) {
          false
        } else {
          
          if (this.hatFreundlicheNachbarn(spieler)) {
            var freundlicheNachbarn: Set[Stadt] = Set()
            for (i <- nachbarSet) { freundlicheNachbarn += i }
            freundlicheNachbarn -= this
            if (freundlicheNachbarn.forall(e => e.hatFreundlicheNachbarn(spieler)) == true) {false} else {true}
          
            
            
            
            /*Cgame.dran & geprueft.contains(i) != true) {
            geprueft += i
            umzingelt = istUmzingeltRek(Cgame.dran)
            print("Bereits geprüft: "); for (i <- geprueft) { print(i + " ") } */
          } else true
        }
      }
      umzingelt
    }
    istUmzingeltRek(spieler)
  }
  */
  //#####################################################################

  def hatNeutraleNachbarn(): Boolean = {
    for (i <- nachbarSet) { if (i.besetzer == Dummy.dummyspieler) return true }
    false
  }

  //#####################################################################

  def hatFreundlicheNachbarn(aktuellerSpieler: Spieler): Boolean = {
    for (i <- nachbarSet) { if (i.besetzer == aktuellerSpieler) return true }
    false
  }
}