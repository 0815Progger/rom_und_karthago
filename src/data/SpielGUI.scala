package data

import swing._
import scala.swing._
import swing.event._
import scala.swing.Alignment._
import java.awt.Dimension._
//import data.Dummy

object SpielGUI extends SimpleSwingApplication {

  var amZug: Boolean = _

  //Name ist Programm ###################################################################
  def werIstDran = {
    if (HauptmenuGUI.spieler1.name == "Rom") { amZug = true } else { amZug = false }
  }

  //Hinweise ausblenden ###################################################################
  def hinweisWeg() = {
    panel1.hinweise.visible = false
  }

  //Stadt schon besetzt? (Die Methode aus ConsoleGame ruft ConsoleGame auf...) ############
  def checkSchonBesetzt(eingabe: Set[Stadt], stadtname: String): Boolean = {
    if (panel1.staedteBox.selection.item.besetzt == true) {
      panel1.hinweise.text = "Stadt bereits besetzt!";
      panel1.hinweise.visible = true
      true
    } else {
      false
    }
  }

  //Wechsel des Spielers ###################################################################
  def spielerwechsel() = {
    if (SpielGUI.amZug == true) {
      panel1.werIstDranLabel.text = "Spieler1 (" + HauptmenuGUI.spieler1.name + ") ist dran.";
      amZug = false
    } else {
      panel1.werIstDranLabel.text = "Spieler2 (" + HauptmenuGUI.spieler2.name + ") ist dran.";
      amZug = true
    }
  }
  //Hat Spieler seinen Zug wiederholt? ####################################################################
  def spielerWiederholtZug(): Boolean = {
    if (HauptmenuGUI.IstDran.historie.letzterZug.name == panel1.staedteBox.selection.item.toString) {
      panel1.hinweise.text = "Dieser Zug ist verboten. Sie versuchen ihren letzten Zug zu wiederholen"
      panel1.hinweise.visible = true
      true
    } else false
  }

  // Der Name ist Programm ####################################################################
  def spielerhistorieSchreiben() = {
    HauptmenuGUI.IstDran.historie.letzterZug = panel1.staedteBox.selection.item
  }

  //Überprüfung der Nachbarn ALLER Städte #####################################################
  def umzingeltAlle() = {
    var umzingelt = true
    panel1.mapReader.foreach(s => {
      print(s.name + "(" + s.besetzer + " " + s.besetzt + "): ")
      if (s.besetzt == true) {
        s.nachbarSet.foreach(b => {
          print(b.name + "(" + b.besetzer + " " + b.besetzt + "), ")

          if (b.besetzt == true) {
            if (b.besetzer == s.besetzer) { umzingelt = false }
          } else { umzingelt = false }
        })
      } else { umzingelt = false }
      if (umzingelt == true) { println(s.besetzer + " wird aus " + s + " vertrieben."); s.wirdBefreit; println(s.besetzer) }
      println()
    })
  }

  /*
     var check = true
    panel1.mapReader.foreach(s => {
      s.nachbarSet.foreach(b => {
        if ((b.besetzer == (s.besetzer)) & (b.besetzer != Dummy.dummyspieler)) {
          check = false
        }
      })
      //Warum macht er das nicht?! Warum wird s.besetzer nicht "dummy" sondern verbleibt der vorherige Besetzer? Ich raffs nicht.
      if ((check == false) & (s.istBesetzt==true)) {
        println(s.besetzer + " wird aus " + s + " vertrieben.");
        s.wirdBefreit;
        println(s.besetzer)
      }
    })
*/

  //Besatzung lösen Selection ###################################################################
  def armeeVerhungertSelection() = {
    //Überprüfung der Nachbarn ###################################################################
    def umzingeltSelection(): Boolean = {
      var check = true
      panel1.staedteBox.selection.item.getNachbarn.foreach(s => {
        if (s.besetzer != nichtDran) { check = false }
      })
      check
    }
    if (umzingeltSelection == true) {
      println(panel1.staedteBox.selection.item.besetzer + " verhungert in " + panel1.staedteBox.selection.item + " sofort.")
      panel1.staedteBox.selection.item.besetzer = Dummy.dummyspieler;
      panel1.staedteBox.selection.item.besetzt = false
      println(panel1.staedteBox.selection.item.besetzer)
    }
  }

  //Rückgabe des Spielers der NICHT dran ist ##################################################
  def nichtDran(): Spieler = {
    if (SpielGUI.amZug == true) { HauptmenuGUI.spieler1 } else { HauptmenuGUI.spieler2 }
  }

  //Rückgabe des jeweils anderen Spielers #####################################################
  def jewGegner(aktuell: Spieler): Spieler = {
    if (aktuell == HauptmenuGUI.spieler1) { HauptmenuGUI.spieler2 }
    if (aktuell == HauptmenuGUI.spieler2) { HauptmenuGUI.spieler1 }
    else aktuell
  }

  // AussetzFunktion
  def aussetzen() = { //Zum Aussetzen
    if (HauptmenuGUI.IstDran.historie.leereZuege < 2) { HauptmenuGUI.IstDran.historie.leereZuege += 1 }
    panel1.hinweise.text = (HauptmenuGUI.IstDran + " entscheidet sich dazu diese Runde nichts zu tun.")
    //println(HauptmenuGUI.spieler1.historie.leereZuege); println(HauptmenuGUI.spieler2.historie.leereZuege) //<-- TEST
    //println("Testausgabe222") //<-- TEST
  }

  //Rundenende = Spielende? Eine Prüfmethode
  def spielEnde(): Boolean = {
    if (HauptmenuGUI.spieler1.historie.leereZuege + HauptmenuGUI.spieler2.historie.leereZuege == 4) { true } else { false }
  }

  //Abschlusstatistik aufrufen
  def EndpunktestandZeigen() = {
    if (spielEnde == true) { EndpunktestandGUI.top.open }

  }

  //############################################################################################
  //##################################### PANELDEFINITION ######################################
  //############################################################################################
  val panel1 = new BoxPanel(Orientation.Vertical) {

    var werIstDranLabel = new Label("<<<Hier wird stehen wer gerade dran ist>>>")
    contents += werIstDranLabel

    contents += new Label("Zu erobernde Stadt wählen")

    val aussetzenCheck = new CheckBox("Diese Runde aussetzen") {
      selected = false
      reactions += {
        case selected => {
          if (staedteBox.enabled == false) { staedteBox.enabled = true } else { staedteBox.enabled = false }
        }
      }
    }
    contents += aussetzenCheck

    var map = new MapReader
    var mapReader = map.aktuelleStaedteliste

    mapReader.foreach(f => {
      f.vorkonfiguration
    })

    //var staedte = List()

    var staedteBox = new ComboBox(mapReader.toList)
    contents += staedteBox

    var hinweise = new Label("<<<Hier stehen später Spielhinweise>>>")
    hinweise.visible = false
    contents += hinweise
  }

  val panel2 = new GridBagPanel {

    val gbc = new Constraints()
    gbc.gridx = 0
    gbc.gridy = 0

    //var groesse = new Dimension(140,80)
    //minimumSize = groesse
    //maximumSize = groesse
    //preferredSize = groesse

    //  var loeschenButton = new Button {
    //    text = "löschen"
    //  }

    var bestaetigenButton = new Button {
      text = "bestätigen"
      reactions += {
        case ButtonClicked(_) => {
          if (panel1.aussetzenCheck.selected == true) {
            aussetzen
            panel1.aussetzenCheck.selected = false
            panel1.staedteBox.enabled = true
            EndpunktestandZeigen
            spielerwechsel
          } else { //Spieler nimmt Stadt ein
            panel1.mapReader.foreach(e => {
              if (e.name.toString == panel1.staedteBox.selection.item.toString) {
                if (checkSchonBesetzt(panel1.mapReader, panel1.staedteBox.selection.item.toString) == false & spielerWiederholtZug == false) {
                  e.besetzer = HauptmenuGUI.IstDran;
                  println(e.besetzer + " " + e.name + " " + panel1.staedteBox.selection.item + " " + HauptmenuGUI.IstDran);
                  e.besetzt = true
                  hinweisWeg;
                  println(e.nachbarSet);
                  armeeVerhungertSelection;
                  umzingeltAlle;
                  panel1.mapReader.foreach(p => {
                    if (p.besetzer == HauptmenuGUI.IstDran) {
                      HauptmenuGUI.IstDran.punktestand += 1
                    }
                  })
                  ZeigePunkteS1.PunkteS1.text = HauptmenuGUI.spieler1.punktestand.toString
                  ZeigePunkteS2.PunkteS2.text = HauptmenuGUI.spieler2.punktestand.toString
                  spielerhistorieSchreiben; //println(HauptmenuGUI.IstDran.historie.letzterZug) //<-- TEST
                  //EndpunktestandZeigen
                  spielerwechsel;
                }
              }
            })
          }
        }
      }

      horizontalAlignment = Left
    }
    var ZeigePunkteS1 = new GridPanel(1, 2) {
      contents += new Label("Spieler1: ")
      var PunkteS1 = new Label(HauptmenuGUI.spieler1.punktestand.toString)
      contents += PunkteS1
    }

    var ZeigePunkteS2 = new GridPanel(1, 2) {
      contents += new Label("Spieler2: ")
      var PunkteS2 = new Label(HauptmenuGUI.spieler2.punktestand.toString)
      contents += PunkteS2

    }
    //  contents += loeschenButton
    add(bestaetigenButton, gbc)
    gbc.gridx = 0; gbc.gridy = 1
    add(ZeigePunkteS1, gbc)
    gbc.gridx = 1; gbc.gridy = 1
    add(ZeigePunkteS2, gbc)
  }

  def top = new MainFrame {

    //    Groesse und Position auf dem Bildschirm
    val framewidth = 640
    val frameheight = 480
    val screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize()
    location = new java.awt.Point((screenSize.width - framewidth) / 2, (screenSize.height - frameheight) / 2)
    minimumSize = new java.awt.Dimension(framewidth, frameheight)

    title = "Rom und Karthago"

    contents = new BoxPanel(Orientation.Vertical) {
      contents += panel1
      contents += panel2
      //      contents += panel3
    }
  }

}