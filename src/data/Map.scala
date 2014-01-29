package data
import scala.io._
import java.io._

class Map {
  val filepath = "files/map.txt"
  var mapRaw = Source.fromFile(filepath)
  var mapRawLines = mapRaw.getLines
  var (lines1_, lines2_) = mapRawLines.duplicate
  var staedteliste: Set[Stadt] = Set()
  var stadt3: Stadt = Dummy.dummytown
  var stadt4: Stadt = Dummy.dummytown

  while (lines2_.hasNext) {
    var zeiger = lines1_.next.split("\\;")
    lines2_.next

    val stadt1: Stadt = new Stadt(zeiger(0), Dummy.dummyspieler)
    val stadt2: Stadt = new Stadt(zeiger(1), Dummy.dummyspieler)
    var stadt1Hinzufuegen = true
    var stadt2Hinzufuegen = true

    if (stadt1.name == stadt2.name) {}
    else {
      for (i <- staedteliste) {
        if (i.name == stadt1.name) { stadt1Hinzufuegen = false }
        if (i.name == stadt2.name) { stadt2Hinzufuegen = false }
      }
      
      if (stadt1Hinzufuegen) { staedteliste = staedteliste + stadt1 }
      if (stadt2Hinzufuegen) { staedteliste = staedteliste + stadt2 }

      stadt3 = zuweisen(stadt1)
      stadt4 = zuweisen(stadt2)
      stadt3.nachbarSet += stadt4
      stadt4.nachbarSet += stadt3
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

  def aktuelleStaedteliste = { 
    staedteliste
    }
  
  def setStaedteliste(set: Set[Stadt]) = { 
    staedteliste = set 
    }
}