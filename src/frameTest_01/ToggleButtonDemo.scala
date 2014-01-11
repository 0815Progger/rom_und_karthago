package frameTest_01

import scala.swing._
import javax.swing.ImageIcon

object ToggleButtonDemo extends SimpleSwingApplication{

  def top = new MainFrame {
    title = "ToggleButtonDemo"
 
    contents = new FlowPanel() {
      val toggleButton1 = new ToggleButton()
      toggleButton1.icon = new ImageIcon("files/blinky.gif")
      
      val toggleButton2 = new ToggleButton()
      toggleButton2.icon = new ImageIcon("files/holarse.png")
      
      val toggleButton3 = new ToggleButton()
      toggleButton3.icon = new ImageIcon("files/SPS.jpg")
      
      contents += toggleButton1
      contents += toggleButton2
      contents += toggleButton3
      
      new ButtonGroup(toggleButton1,toggleButton2,toggleButton3)
      
      toggleButton1.selected = true
    }    
  } 
}