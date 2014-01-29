package gui

import swing._
import scala.swing._

object EndpunktestandGui extends SimpleSwingApplication {
  
  val siegerLabel:Label = new Label()

  def werGewinnt() = {
    if (HauptmenuGui.spieler1.punktestand > HauptmenuGui.spieler2.punktestand) {
      siegerLabel.text = "Spieler 1 gewinnt!"
    } else {
      if (HauptmenuGui.spieler1.punktestand < HauptmenuGui.spieler2.punktestand) {
        siegerLabel.text = "Spieler 2 gewinnt!"
      } else {
        siegerLabel.text = "Es wurde unentschieden gespielt"
      }
    }
  }
  
  val panel1 = new BoxPanel(Orientation.Vertical){
    contents += new Label("Punktestand Spieler1: "+HauptmenuGui.spieler1.punktestand.toString)
    contents += new Label("Punktestand Spieler2: "+HauptmenuGui.spieler2.punktestand.toString)
    werGewinnt()
    contents += siegerLabel
  }
  
  def top = new MainFrame {
    
    title ="Endpunktestand"
    
    val framewidth = 640
    val frameheight = 480
    val screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize()
    location = new java.awt.Point((screenSize.width - framewidth) / 2, (screenSize.height - frameheight) / 2)
    minimumSize = new java.awt.Dimension(framewidth, frameheight)
    
    contents = panel1
  }

}