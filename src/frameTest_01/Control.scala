package frameTest_01

import scala.swing.event._

class Control {
	val view = new View
	val model = new Model
	
	setData()
	
	view.listenTo(view.button)
	view.reactions +={
	  case ButtonClicked(b) => model.noClicks +=1
	  setData()
	  
	}
	
	def setData():Unit = {
			view.label.text = model.noClicks.toString
	}
}