package frameTest_01

import scala.swing._

object SimpleListView {
  def main(args:Array[String]) {
    
    val simpleListView = new SimpleListView()
    simpleListView.visible=true
  }
}

class SimpleListView extends Frame {
	val theList = List("2","3","4","53","434","1","k")
	val listView = new ListView(theList)
	val scrollPane = new ScrollPane()
	scrollPane.contents=listView
	contents = scrollPane
//scrollpane "verschönert" die Geschichte nur mit einer, ratet mal, scrollLeiste.
}