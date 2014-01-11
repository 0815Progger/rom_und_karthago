package frameTest_01

import scala.swing._

class View extends Frame {
	val panel = new FlowPanel()
	val button = new Button("Klick mich du geile Sau!")
	val label = new Label("Porno")
	
	panel.contents +=button
	panel.contents +=label
	
	contents = panel
	
	visible = true
}