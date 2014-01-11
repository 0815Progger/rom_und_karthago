package data

import swing._
import scala.swing._
import swing.event._
import frameTest_01.RadioButtonDemo

object panelCeptionTest extends SimpleSwingApplication {
  val panel1 = new FlowPanel {
    contents += new Label("Spieler1, Wähle deine Seite")
  }
  val panel2 = new GridPanel(1, 2) {
    hGap = 20
    vGap = 20
    var buttonRom: Button = new Button {
      text = "Rom"
      reactions += {
        case ButtonClicked(_) => {
          text = "Rom gewählt"
          buttonKar.text = "Karthago"
          panel3.spieler1.visible = true
          panel3.spieler1.text = "Spieler1 = Rom"
          panel3.spieler2.visible = true
          panel3.spieler2.text = "Spieler2 = Karthago"
        }
      }
    }

    //Button Karthago
    var buttonKar: Button = new Button {
      text = "Karthago"
      reactions += {
        case ButtonClicked(_) => {
          text = "Karthago gewählt"
          buttonRom.text = "Rom"
          panel3.spieler1.visible = true
          panel3.spieler1.text = "Spieler1 = Karthago"
          panel3.spieler2.visible = true
          panel3.spieler2.text = "Spieler2 = Rom"
        }
      }
    }

    contents += buttonRom
    contents += buttonKar
  }
  val panel3 = new GridPanel(1,2) {
    var spieler1 = new Label {
      text = "dummytext"
      visible = false
    }
    var spieler2 = new Label {
      text = "dummytext"
      visible = false
    }
    contents += spieler1
    contents += spieler2
  }
  
  val panel4 = new GridPanel(1,2) {
    hGap = 20
    vGap = 20
    val loeschen = new Button{
      text = "Eingabe löschen"
        reactions += {
        	case ButtonClicked(_) => {
        	  panel3.spieler1.visible=false
        	  panel2.buttonRom.text = "Rom"
        	  panel3.spieler2.visible=false
        	  panel2.buttonKar.text = "Karthago"
        	}
          
        }
    }
    val start = new Button{
      text = "Spiel starten"
        reactions += {
        case ButtonClicked(_) => {
          SpielGUI.top.open
        }
      }
    }
    contents += loeschen
    contents += start
  }

  def top = new MainFrame {

    //    Groesse und Position auf dem Bildschirm
    val framewidth = 640
    val frameheight = 480
    val screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize()
    location = new java.awt.Point((screenSize.width - framewidth) / 2, (screenSize.height - frameheight) / 2)
    minimumSize = new java.awt.Dimension(framewidth, frameheight)

    title = "Startmenu"
    contents = new BoxPanel(Orientation.Vertical) {
      contents += panel1;
      contents += panel2;
      contents += panel3;
      contents += panel4;
    }
  }
}