package gui

import swing._
import scala.swing._
import swing.event._
import data.Dummy
import data.Spieler

object HauptmenuGui extends data.Spiel{
  
  //Wer ist dran? Rückgabe des Spieler-Objekt
  def istDran():Spieler = {
    if(SpielGui.amZug!=true){spieler1}else{spieler2}
  }
  
  //Dumme Hilfsfunktion von wegen Rekursion und so. Pah.
  
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
          spieler1.name = "Rom"
          panel3.spieler2.visible = true
          panel3.spieler2.text = "Spieler2 = Karthago"
          spieler2.name = "Karthago"
          panel5.start.enabled=true

            
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
          spieler1.name = "Karthago"
          panel3.spieler1.text = "Spieler1 = "+spieler1.name
          
          
          panel3.spieler2.visible = true
          spieler2.name = "Rom"
          panel3.spieler2.text = "Spieler2 = "+spieler2.name
          panel5.start.enabled=true 
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
    var loeschen = new Button{
      text = "Eingabe löschen"
        reactions += {
        	case ButtonClicked(_) => {
        	  panel3.spieler1.visible=false
        	  panel2.buttonRom.text = "Rom"
        	  panel3.spieler2.visible=false
        	  panel2.buttonKar.text = "Karthago"
        	  panel5.start.enabled=false
        	    
        	  spieler1.name="Eloe"
        	  spieler2.name="Eloe"
        	}
          
        }
    }
        contents += loeschen
  }
    
    val panel5 = new FlowPanel {
    var start = new Button{
      text = "Spiel starten"
        enabled=false
        reactions += {
        case ButtonClicked(_) => {
          SpielGui.top.open
          //if (Spieler1.name == "Rom") { SpielGUI.amZug = true } else { SpielGUI.amZug = false }
          if (SpielGui.amZug != true){
            SpielGui.panel1.werIstDranLabel.text = "Spieler1 ("+spieler1.name+") ist dran."; SpielGui.amZug=false
            //SpielGUI.amZug=false
            }
          else{
            SpielGui.panel1.werIstDranLabel.text="Spieler2 ("+spieler2.name+") ist dran."; SpielGui.amZug=true
            //SpielGUI.amZug=true
            }
          
        }
      }
    }

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
      contents += panel5;
    }
  }
}