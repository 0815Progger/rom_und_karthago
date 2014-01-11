package frameTest_01

import scala.swing._

object CheckBoxDemo extends SimpleSwingApplication {
  def top = new MainFrame {
    title = "CheckBoxDemo ihr Hafermenschen"
      
      contents = new BoxPanel(Orientation.Vertical) {
    	val check1 = new CheckBox("CheckBox1")
    	val check2 = new CheckBox("2")
    	val check3 = new CheckBox("3")
    	val check4 = new CheckBox("FOUR!!!")
    	
    	contents += check1
    	contents += check2
    	contents += check3
    	contents += check4
    	
    	check1 selected = false
    	check2 selected = true
    	check3 selected = true
    	check4 selected = false
    	
    	// CHECKboxen können auch in ButtonGroups gruppiert werden, entgegen der Annahme verhalten sie sich dann aber genauso wie Radio Buttons
    	// (Nur einer kann gecheckt sein, der rest unchecked)
    }
  }
}