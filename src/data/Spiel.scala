package data

abstract class Spiel {
  var zugAnzahl: Int = 0
  var dummy: Spieler = Dummy.dummyspieler
  var spieler1: Spieler = new Spieler("Spieler1") //init
  var spieler2: Spieler = new Spieler("Spieler2")
  var spielerDran: Spieler = spieler1
  var endgame = false //Raushuepf-Variable fuer Spielschleife
  var staedte = { var map = new Map; map.aktuelleStaedteliste }
  
  //#####################################################################

  def nichtDran = if (spielerDran == spieler1) { spieler2 } else { spieler1 }

  //#####################################################################
  
  def vorverteilung() = {
    for (i <- staedte) {
      i.vorkonfiguration
    }
  }
  
  //#####################################################################

  def spielerwechsel = {
    if (spielerDran == spieler1) spielerDran = spieler2 else spielerDran = spieler1
  }

  //#####################################################################

  def werIstRom(): Spieler = {
    if (spieler1.spielerSeite == "Rom") spieler1 else spieler2
  }

  //#####################################################################

  def werIstKar(): Spieler = {
    if (spieler1.spielerSeite == "Karthago") spieler1 else spieler2
  }

  //#####################################################################

  def spielerDranName(): String = { spielerDran.spielerName }

  //#####################################################################

  def checkSchonBesetzt(eingabe: Set[Stadt], stadtname: String): Boolean = {
    var check = true //Anfangsvermutung: Stadt besetzt
    for (i <- eingabe) {
      if (i.name == stadtname) {
        check = i.istBesetzt
      }
    }
    check
  }

  //#####################################################################

  def leereZuegeAufNull() = {
    spielerDran.historie.leereZuege = 0 //Da ein Zug unternommen wird, wird der Zaehler für Aussetzer zurück auf 0 gestellt.
  }

  //#####################################################################

  def aktuellerPunktestand() = {

    def punkteSpieler1() = {
      var temp: Int = 0
      var checkaCheck = staedte
      while (checkaCheck.isEmpty == false) {
        if (checkaCheck.head.besetzer == spieler1) { temp = temp + 1 }
        checkaCheck = checkaCheck.tail
      }
      spieler1.punktestand = temp
    }

    def punkteSpieler2() = {
      var temp: Int = 0
      var checkaCheck = staedte
      while (checkaCheck.isEmpty == false) {
        if (checkaCheck.head.besetzer == spieler2) { temp = temp + 1 }
        checkaCheck = checkaCheck.tail
      }
      spieler2.punktestand = temp
    }

    punkteSpieler1()
    punkteSpieler2()

  }

  //#####################################################################

  def spielzuEnde(): Boolean = {
    if (spieler1.historie.leereZuege == 2 & spieler2.historie.leereZuege == 2) true else false
  }

  //#####################################################################

  def spielzugAussetzen() = { //Zum Aussetzen
    if (spielerDran.historie.leereZuege < 2) spielerDran.historie.leereZuege += 1
  }

  //#####################################################################

  def pruefungNachbarn(nachbarn: Set[Stadt]) = {
    var umzingelteNachbarn = Set[Stadt]()
    nachbarn.foreach(i => if (i.istUmzingelt(nichtDran, i.nachbarSet) == true) { umzingelteNachbarn += i })
    umzingelteNachbarn.foreach(i => { i.besetzer = Dummy.dummyspieler; i.besetzt = false })
  }

  //#####################################################################

  def pruefungSelbst(geradeErobert: Stadt) = {
    if (geradeErobert.istUmzingelt(spielerDran, geradeErobert.nachbarSet) == true) {
      println(geradeErobert.besetzer + " hat das umzingelte " + geradeErobert + " besetzen wollen. Seine Armeen verhungern sofort.")
      geradeErobert.besetzer = Dummy.dummyspieler
      geradeErobert.besetzt = false
    }
  }

  //#####################################################################

  def besetzen(zuBesetzen: String) = {
    staedte.foreach(i => {
      if (i.name == zuBesetzen) {
        i.besetzer = spielerDran; i.besetzt = true
        println(i.besetzer+" "+i.besetzt)
        pruefungNachbarn(i.nachbarSet)
        pruefungSelbst(i)
      }
    })
    
  }
}