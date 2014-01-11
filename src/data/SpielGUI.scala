package data

import swing._
import scala.swing._
import swing.event._
import scala.swing.Alignment._
import java.awt.Dimension._

object SpielGUI extends SimpleSwingApplication {

  val panel1 = new BoxPanel(Orientation.Vertical) {
    val werIstDranLabel = new Label("<<<Hier wird stehen wer gerade dran ist>>>")
    contents += werIstDranLabel

    contents += new Label("Zu erobernde Stadt wählen")
    
    val aussetzenCheck = new CheckBox("Diese Runde aussetzen"){
      selected = false
      reactions += {
        case selected => {
          if (staedteBox.enabled==false){staedteBox.enabled=true}else{staedteBox.enabled=false}  
        }
      } 
    }
    contents += aussetzenCheck

    var staedte = List("Hier", "Stehen", "Später", "Alle", "Städte", "Drin")
    var staedteBox = new ComboBox(staedte)
    contents += staedteBox

    var hinweise = new Label("<<<Hier stehen später Spielhinweise>>>")
    contents += hinweise
  }

  val panel2 = new GridPanel(2, 2) {
    var groesse = new Dimension(120,80)
    minimumSize = groesse
    maximumSize = groesse
    preferredSize = groesse
    
  //  var loeschenButton = new Button {
  //    text = "löschen"
  //  }
    
    var bestaetigenButton = new Button {
      text = "bestätigen"
      hGap = 20
      vGap = 5

        verticalAlignment = Center
    }
    var ZeigePunkteS1 = new GridPanel(1, 2) {
      contents += new Label("Spieler1: ")
      var PunkteS1 = new Label("0")
      contents += PunkteS1
    }
  //  contents += loeschenButton
    contents += bestaetigenButton
    contents += ZeigePunkteS1
  }

  def top = new MainFrame {
    
    //    Groesse und Position auf dem Bildschirm
    val framewidth = 640
    val frameheight = 480
    val screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize()
    location = new java.awt.Point((screenSize.width - framewidth) / 2, (screenSize.height - frameheight) / 2)
    minimumSize = new java.awt.Dimension(framewidth, frameheight)
    
    contents = new BoxPanel(Orientation.Vertical) {
      contents += panel1
      contents += panel2
//      contents += panel3
    }
  }
}