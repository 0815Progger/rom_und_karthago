package frameTest_01

import swing._
import javax.swing._

object ButtonDemo extends SimpleSwingApplication {
  def top = new MainFrame {
    title = "ButtonDemo"
      val button = new Button("KLICK MICH!")
    	button.icon = new ImageIcon("files/test.png")
    contents=button
  }
}