package frameTest_01

import scala.swing.Frame
import scala.swing.FlowPanel
import scala.swing.ComboBox

object ComboBoxDemo {

  def main(args: Array[String]): Unit = {
    val textFieldDemo = new TextFieldDemo()
    textFieldDemo.visible = true
  }
}

class TextFieldDemo() extends Frame {
  title = "TextFieldDemp"
    
  contents = new FlowPanel {
    val items = List("Suck","My","Handschuhfach","Thorsten!")
    contents += new ComboBox(items)    
  }
}