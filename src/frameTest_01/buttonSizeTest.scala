package frameTest_01

import swing._
import java.awt.Dimension

object ButtonSizeTest extends SimpleSwingApplication{

val s = new Dimension(100, 100)
val f = new Frame {
  contents = new FlowPanel { 
    contents += new Button("huhu") { 
      minimumSize = s
      maximumSize = s
      preferredSize = s
    }
  }
}

f.pack
f.visible = true

def top = new MainFrame{
  f
}
}