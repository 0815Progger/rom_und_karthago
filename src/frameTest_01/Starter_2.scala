package frameTest_01

import scala.swing.Frame
import scala.swing.TextField
import scala.swing.FlowPanel
import scala.swing.Label

object Starter_2 {
  def main(args: Array[String]): Unit = {
    val test = new TextField7()
    test.visible=true
  }
}

class TextField7() extends Frame {
  title="Lutsch die Pfeife"
    
    contents = new FlowPanel {
	  contents += new Label("Blow my whistle baby")
	  contents += new TextField("Blow me hard!")
  }
}