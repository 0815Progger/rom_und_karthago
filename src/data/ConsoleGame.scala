package data

import scala.io._
import java.io._

object ConsoleGame {

  var map = new MapReader
  var mapReader = map.aktuelleStaedteliste
  var dummy = new Spieler("Dummy")
  val spieler1: Spieler = new Spieler(seiteWaehlen) //Siehe die oben definierte Methode
  val spieler2: Spieler = new Spieler(if (spieler1.name == "Rom") { "Karthago" } else { "Rom" }) // S2 ist immer das Gegenteil von S1.
  var amZug: Boolean = _ //true ist Spieler1 am Zug, false ist Spieler2 am Zug.
  if (spieler1.name == "Rom") { amZug = true } else { amZug = false } //Wir definieren: Rom beginnt immer.
  //    var mapReader = new MapReader().readMap("/home/michael/Dropbox/workspace_scala/rom_and_karthago/files/map.txt")
  //    var aussetzerInARow = 0 DAS DING IS OBSOLET! :D Wird jetzt via spieler.historie.leerezuege abgedeckt. NiceShice.
  //    var Karte = new Map
  //    Karte.init
  var staedte = mapReader
  var endgame = false //Raushuepf-Variable fuer die Hauptschleife des Spiels (siehe weiter unten im Text).
  //    var aktuellerZug:String = _
  var dran = if (amZug == true) { spieler1 } else { spieler2 }
  spieler1.historie.letzterZug = new Stadt("dummytown", dummy) //Initialisierungsproblem: NullPointerException
  spieler2.historie.letzterZug = new Stadt("dummytown", dummy) //Initialisierungsproblem: NullPointerException

  def main(args: Array[String]) {
    //    	seiteWaehlen(); <-- das wird schon bei der def der val spieler1 aufgerufen, siehe oben.
    spielStart();
    spielEnde();
  }

  /*########################gekasht#####################################*/

  def seiteWaehlen(): String = { //Bestimmt welcher Spieler welche Seite spielt.
    println("Willkommen zu einer lustigen Runde ... Rom und Kathargo")
    var romKarthago = false //als init-Wert, entscheidet darüber wer nachher wer ist.
    var machsRichtigDuTrottel = false //nur ne Prüf-Variable der While-Schleife.

    while (machsRichtigDuTrottel == false) {
      println("Spieler1, Seite wählen (Drücke K+Enter für Karthago, R+Enter für Rom)")
      var line = readLine //Sollte K oder R als String beinhalten
      if (line.toUpperCase() == "R") { romKarthago = true; machsRichtigDuTrottel = true } else {
        if (line.toUpperCase() == "K") { romKarthago = false; machsRichtigDuTrottel = true } else {
          println("Nur die Eingaben 'R' und 'K' werden erkannt. Bitte wiederholen Sie ihre Eingabe.")
        }
      }
    }
    var seite = (if (romKarthago == true) { "Rom" } else ("Karthago"))
    println("Spieler1 waehlte " + seite + ". Spieler2 ist somit " + (if (romKarthago == true) { "Karthago" } else { "Rom" }))
    return seite
  }

  /*#############################################################*/

  def spielerWerIstDran(): String = { //Spieler 1 oder Spieler 2?
    if (amZug == true) { "spieler1" } else { "spieler2" }
  }

  /*########################wirdgenutzt#####################################*/

  def checkStaedte(eingabe: Set[Stadt], stadtname: String): Boolean = {
    //println(eingabe)
    var checkaCheck = eingabe
    var gibtEs = false

    //println("Test(GibtEs?): " + stadtname)

    for (i <- eingabe) if (i.name == stadtname) { gibtEs = true; return gibtEs }
    return gibtEs
    // Wenn es die Stadt in dem Set gibt, dann setzte gibtEs = true
  }

  /*
    def checkStaedte(eingabe: List[Stadt],stadtname:String): Boolean = {
    	var checkaCheck = eingabe
    	var gibtEs = false
    	while (checkaCheck != Nil) {
    	//	println("Test(GibtEs?): " + checkaCheck.head.stadtname + stadtname)
    		if (checkaCheck.head.name == stadtname) { return true } else checkaCheck = checkaCheck.tail
    	}
    	return false
    }
*/
  /*########################wirdgenutzt#####################################*/

  def checkSchonBesetzt(eingabe: Set[Stadt], stadtname: String): Boolean = {
    var check = true
    for (i <- eingabe) if (i.name == stadtname) {
      if (i.besetzer == dummy) {
        check = false
        // falls i.name = gesuchter stadtname und diese stadt dann spieler dummy gehoert => unbesetzt
      } else {
        check = true
        // wenn sie schon jmd gehoert und der heisst nicht dummy => besetzt
      }
    }
    //println("Peniswitze")
    check
  }

  /*#############################################################*/

  def besetzen(zuBesetzen: String) = {
    // println("Werde ich noch aufgerufen?")

    //Die While-Schleifen rekursivieren sich ins unendliche bei einmaliger Falscheingabe, egal was man danach eingibt.
    //		while (checkStaedte(staedte,zuBesetzen) != true) { println("Nicht korrekte Eingabe des Stadtnamens."); macheZug }
    if (checkStaedte(staedte, zuBesetzen) != true) { println("Nicht korrekte Eingabe des Stadtnamens."); print("Erneut eingeben: "); macheZug }

    //    	while (checkSchonBesetzt(staedte,zuBesetzen) != false) { println("Diese Stadt ist bereits durch einen Spieler besetzt!"); macheZug }
    if (checkSchonBesetzt(staedte, zuBesetzen) == false) { println("Diese Stadt ist bereits durch einen Spieler besetzt!"); print("Erneut eingeben: "); macheZug }
    //println("komm ich noch dran?")
    if (dran.historie.letzterZug.name == zuBesetzen) { println("Dieser Zug ist verboten. Sie versuchen ihren letzten Zug zu wiederholen"); print("Erneut eingeben:"); macheZug }

    var checkaCheck = staedte
    //println("schwanz")
    while (checkaCheck.isEmpty == false) {
      //   println("Kommt das hier an?")
      if (checkaCheck.head.name == zuBesetzen) { checkaCheck.head.besetzer = dran }
      checkaCheck = checkaCheck.tail
    }
    //	println("Das ist eine Testausgabe! Dufte wa?")
  }

  /*###################wirdgenutzt##########################################*/

  def spielzug(aktuellerZug: String) = { //aktuellerZug: Stadt die besetzt werden soll
    besetzen(aktuellerZug)
    //					*zug*.Stadt.besetztVon = spielerWerIstDran

    //					Hier müsste ich jetzt irgendwie auf die Map.scala oder den MapReader oder irgendwas zugreifen. Tüdelü.
    //					Check ob die soeben eroberte Stadt umzingelt ist und ENTFERNE den Spieler falls ja. (IF ELSE)
    //					Check ob die Nachbarstädte der soeben eroberten Stadt umzingelt sind und entferne die dortigen Besetzer falls ja. (IF ELSE)
    dran.historie.leereZuege = 0 //Da ein Zug unternommen wird, wird der Zaehler für Aussetzer zurück auf 0 gestellt.
  }

  /*###########################wirdgenutzt##################################*/

  def spielzugAussetzen() = { //Zum Aussetzen
    if (dran.historie.leereZuege < 2) dran.historie.leereZuege += 1
    println(dran + " entscheidet sich dazu diese Runde nichts zu tun.")
    //println("Testausgabe222")
  }

  /*########################wirdgenutzt#####################################*/

  def macheZug(): Unit = {
    var zug = readLine
    if (zug == "") { spielzugAussetzen } else spielzug(zug)
    //println("testausgabe")
  }

  /*###########################wirdgenutzt##################################*/

  def aktuellerPunktestand() = {

    def punkteS1() = {
      var temp: Int = 0
      var checkaCheck = staedte
      while (checkaCheck.isEmpty == false) {
        if (checkaCheck.head.besetzer == spieler1) { temp = temp + 1 }
        checkaCheck = checkaCheck.tail
      }
      spieler1.punktestand = temp
    }

    def punkteS2() = {
      var temp: Int = 0
      var checkaCheck = staedte
      while (checkaCheck.isEmpty == false) {
        if (checkaCheck.head.besetzer == spieler2) { temp = temp + 1 }
        checkaCheck = checkaCheck.tail
      }
      spieler2.punktestand = temp
    }
    punkteS1()
    punkteS2()
  }

  /*#############################################################*/

  def spielStart() = { //Spielzug, wird unterschieden wg. spielzug wiederholen verboten / aussetzen nicht verboten		

    while (endgame != true) { //hier beginnt nach dem Anfangsgeplänkel das Main-Game.

      println(dran + "(" + (spielerWerIstDran()) + ") ist dran.")
      println(dran + ", welche Stadt soll erobert werden? (Stadtname eingeben & mit Enter bestaetigen. Nur Enter druecken zum Aussetzen.)\n")
      //staedte.foreach(println)
      macheZug()
      aktuellerPunktestand()
      println(spieler1.name + ": " + spieler1.punktestand + " Punkt(e). " + spieler2.name + ": " + spieler2.punktestand + " Punkt(e).")
      if (spieler1.historie.leereZuege == 2 & spieler2.historie.leereZuege == 2) { endgame = true } // Wenn 4mal ausgesetzt wurde ist schluss!
      if (amZug == true) { amZug = false; dran = spieler2 } else { amZug = true; dran = spieler1 } //wechselt jede Runde zwischen S1 und S2, no matter what
    }
  }

  /*#############################################################*/

  def spielEnde() = {
    println("Das Spiel ist beendet")
    // println("Es steht: " + PunkteStandRom + " fuer Rom und " + PunkteStandKar + " fuer Karthago.")
    // if (PunkteStandRom>PunkteStandKar) {println("Rom ist der Gewinner dieses Spiels")} else
    //	if (PunkteStandRom==PunkteStandKar) {println("Es wurde unentschieden gespielt")} else {
    //		println("Karthago ist der Gewinner dieses Spiels")}
  }
}

/*
Bereits aus unserer Anwendungsfallbeschreibung implementiert wurden: "Mehrspielermodus am selben PC".
Dort ging es um das Spielen am selben PC, was zu 100% der Aufgabenstellung UE06A3 entspricht.
Die Aktoren Spieler1 und Spieler2 finden sich bereits als Variablen des Spielfluss wieder.
Da es sich bei UE06A3 explizit nur um den 2Spieler1PC Modus handelt, entfällt die in der Anwendungsfallbeschreibung
Auswahl des HotSeat-Modus.
*/

