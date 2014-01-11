package frameTest_01

import swing._
import javax.swing._

object LabelDemo extends SimpleSwingApplication{
  def top = new MainFrame {
    
    title = "Lable Demo"
    contents = new FlowPanel {
      contents += new Label(" <<< GEILER DRECKS BALL!!!",new ImageIcon("files/testicon.png"),Alignment.Right)
    //contents = new Label("Text and Image Label",new ImageIcon("c:\\test\\test.png"),Alignment.Right)
      contents += new Label("Lutsch mir die Eier Bill Gates",new ImageIcon("files/test.png"),Alignment.Left)
    }
  }
}