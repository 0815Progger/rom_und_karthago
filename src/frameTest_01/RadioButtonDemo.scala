package frameTest_01

import scala.swing._

object RadioButtonDemo extends SimpleSwingApplication {
  
  def top = new MainFrame {
    title = "RadioButtonDemo"
      
      contents = new BoxPanel(Orientation.Vertical) {
    	val radioButton1 = new RadioButton("1")
    	val radioButton2 = new RadioButton("2")
    	val radioButton3 = new RadioButton("Pupsgeräusche")
    	
    	contents += radioButton1
    	contents += radioButton2
    	contents += radioButton3
    	
    	new ButtonGroup(radioButton1,radioButton2,radioButton3)
    	
    	radioButton2 selected = true
    }
  }

}