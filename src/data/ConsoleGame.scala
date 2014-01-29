package data

import scala.io._
import java.io._

object ConsoleGame extends Spiel {

  //#####################################################################

  def seiteWaehlen() { //Bestimmt welcher Spieler welche Seite spielt.	//Nur Konsolenrelevant
    var gewaehlteSeite:Char = ' '
    
    def seiteWaehlenEingabe() = {
      println("Spieler1, sind Sie (R)om oder (K)arthago? Buchstabe eingeben & ENTER drücken.")
      gewaehlteSeite = konsolenEingabe.toUpper
    }

    seiteWaehlenEingabe();
    var check = false //nur ne Prüf-Variable der While-Schleife.
        
    while (check == false) {
      if (gewaehlteSeite == 'R') { spieler1.spielerSeite = "Rom"; spieler2.spielerSeite = "Karthago"; check = true } else {
        if (gewaehlteSeite == 'K') { spieler1.spielerSeite = "Karthago"; spieler2.spielerSeite = "Rom"; check = true } else {
          println("Nur die Eingaben 'R' und 'K' werden erkannt. Bitte wiederholen Sie ihre Eingabe.")
          seiteWaehlenEingabe
        }
      }
    }
    println("Spieler1 wählt " + spieler1.spielerSeite + ". Spieler2 ist somit " + spieler2.spielerSeite)
  }

  //#####################################################################
  
  def macheZugInfo = { println("Stadtname eingeben & mit Enter bestaetigen. Nur Enter druecken zum Aussetzen.)\n" + spielerUndSeite + ", welche Stadt soll erobert werden: ") }
  
  //#####################################################################

  def spielStart() = { //Spielzug, wird unterschieden wg. spielzug wiederholen verboten / aussetzen nicht verboten		

    while (endgame != true) { //hier beginnt nach dem Anfangsgeplänkel das Main-Game.
      println(spielerUndSeite+" ist dran.")
      zeigeStaedte	//DEBUG, kommt später weg
      macheZugInfo
      macheZug
      
      aktuellerPunktestand
      println(spieler1.spielerName + " (" + spieler1.spielerSeite + ") : " + spieler1.punktestand + " Punkt(e). " + spieler2.spielerName + " (" + spieler2.spielerSeite + ") : " + spieler2.punktestand + " Punkt(e)."); println
      if (spielzuEnde == true) { endgame = true } // Wenn insgesammt 4 mal ausgesetzt wurde ist schluss!
      spielerwechsel
      
    }
  }

  //#####################################################################

  def zeigeStaedte() = { //Nur Konsolenrelevant
    var staedteIterator = staedte.iterator
    staedteIterator.foreach(i => { print(i.name+"\t");if(i.name.length<8)
      print("\t");print(" " + i.besetzer + "; \t besetzt=" + i.besetzt + ")\t"); print("umzingelt: "+i.umzingelt)
      println })
    println()
  }

  //#####################################################################

  def konsolenEingabe(): Char = { //Nur Konsolenrelevant
    var line: Char = readLine.charAt(0) //Sollte K oder R als String beinhalten
    return line
  }

  /*#############################################################*/

  def checkStaedte(eingabe: Set[Stadt], stadtname: String): Boolean = { //Nur für das Konsolenspiel relevant

    var gibtEs = false
    for (i <- eingabe) if (i.name == stadtname) { gibtEs = true }
    return gibtEs // Wenn es die Stadt in dem Set gibt 
  }

  /*#############################################################*/

  def pruefungVorBesetzen(zuPruefen: String) = { //Nur für Konsole relevant
    var check: Boolean = false
    var pruefen = zuPruefen
    
    while (check == false) {
      if (checkStaedte(staedte, zuPruefen) != true) { println("Nicht korrekte Eingabe des Stadtnamens."); print("Erneut eingeben: "); pruefen = readLine }
      if (checkSchonBesetzt(staedte, zuPruefen) == true) { println("Diese Stadt ist bereits durch einen Spieler besetzt!"); print("Erneut eingeben: "); pruefen = readLine }
      if (spielerDran.historie.istWiederholung(zuPruefen) == true) { println("Dieser Zug ist verboten. Sie versuchen ihren letzten Zug zu wiederholen"); print("Erneut eingeben:"); pruefen = readLine }
      else besetzen(pruefen); check = true
    }
    
  }
    
  /*#############################################################*/

  def spielerUndSeite(): String = { //Nur für Konsolenspiel relevant
    var a = spielerDran.spielerName + " (" + spielerDran.spielerSeite + ")"
    return a
  }

  /*################### wird genutzt ##########################################*/

  def spielzug(zug: String) = { //aktuellerZug: Stadt die besetzt werden soll
    pruefungVorBesetzen(zug)
    leereZuegeAufNull
    
  }

  /*########################### wird genutzt ##################################*/

  def spielzugAussetzenInfo = {
    println(spielerUndSeite + " entscheidet sich dazu diese Runde nichts zu tun.")
  }

  /*######################## wird genutzt #####################################*/

  def macheZug(): Unit = { //Nur für das Konsolenspiel relevant
    var zug = readLine
    if (zug == "") { spielzugAussetzen; spielzugAussetzenInfo } else spielzug(zug)
  }

  /*#############################################################*/

  def spielEndeInfo() = { //Nur für das Konsolenspiel relevant
    println("Das Spiel ist beendet")
  }

  //#####################################################################
  
  def intro() = { println("<<< Rom und Kathargo >>>") }
  
  //########################## MAIN-METHODE #############################

  def main(args: Array[String]) {
    intro();
    seiteWaehlen(); //Spieler1 wählt seine Seite. Spieler2 ist autom. die andere Seite.
    vorverteilung;

    spielStart(); //Der Name ist Programm.
    spielEndeInfo(); //Der Name ist Programm.
  }
}