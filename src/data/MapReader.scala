
package data
import scala.io._
import java.io._

class MapReader {
  val filepath = "files/map.txt"
  // Speicherort von der mapfile!     ggbfalls spaeter mal variabel gestalten
  var mapRaw = Source.fromFile(filepath)
  // iterator der map.txt datei
  var mapRawLines = mapRaw.getLines
  // zeilenweise ausgabe vo map.txt  => stadt1;stadt2
  var (lines1_, lines2_) = mapRawLines.duplicate
  // zum spaeteren durchiterieren. Allerdings kann wenn schon iteriert wurde nicht mehr zurueck um auf altes elemt zuzugreifen
  var staedteliste: Set[Stadt] = Set()
  // dummy der staedteliste die alle staedte der map enthalten soll. Wird weiter unten gefuellt
  var stadt3: Stadt = new Stadt("gibtsBaldNichtMehr", dummySpieler)
  var stadt4: Stadt = new Stadt("gibtsBaldNichtMehr", dummySpieler)
  // Hilfstaedte die in u.A. in den Hilffkt unten ben werden

  val dummySpieler = new Spieler("dummy")
  // Erstelle dummySpieler. Dieser Spieler soll alle Staedte zu Beginn besitzten

  while (lines2_.hasNext) {
    var zeiger = lines1_.next.split("\\;")
    lines2_.next
    //hier wird durchiteriert

    val stadt1: Stadt = new Stadt(zeiger(0), dummySpieler)
    val stadt2: Stadt = new Stadt(zeiger(1), dummySpieler)
    println(stadt1 + ";" + stadt2)
    //Das sind die beiden Staedte die eingelesen werden. Daraus wird jeweils eine Stadt

    var stadt1Hinzufuegen = true
    var stadt2Hinzufuegen = true

    if (stadt1.name == stadt2.name) {}
    // wenn beide staedte gleich sind tue nichts
    else {
      for (i <- staedteliste) {
        if (i.name == stadt1.name) { stadt1Hinzufuegen = false }
        if (i.name == stadt2.name) { stadt2Hinzufuegen = false }
        // wenn stadt1/stadt2 schon im set, dann soll sie nciht mehr eingefuegt werden
      }
      if (stadt1Hinzufuegen) { staedteliste = staedteliste + stadt1 }
      if (stadt2Hinzufuegen) { staedteliste = staedteliste + stadt2 }
      // die beiden staedte werden wenn noch nicht im Set hinzugefuegt

      stadt3 = zuweisen(stadt1)
      stadt4 = zuweisen(stadt2)
      // stadt3 bzw stadt4 werden die namensgleichen staedte von stadt1.name und stadt2.name aus dem set zugeordnet. Vermeidung versch. phys. Speicherorte

      stadt3.nachbarSet += stadt4
      stadt4.nachbarSet += stadt3
      // Einfuegen der jeweils anderen stadt als nachbarstadt auch wenn bereits vorhanden gewesen im Set

    }
  }

  //****************************** Hilffunktionen  ***************************************

  def zuweisen(stadt: Stadt): Stadt = {
    var hilfstadt = stadt
    for (i <- staedteliste) {
      if (i.name == stadt.name) { hilfstadt = i }
    }
    hilfstadt
  }
  // Soll zu dem namen einer Stadt die namensgleiche Stadt aus dem Set ausgeben wenn vorhanden und sonst Stadt selbst!

  def aktuelleStaedteliste = { staedteliste }
  def setStaedteliste(set: Set[Stadt]) = { staedteliste = set }
}

