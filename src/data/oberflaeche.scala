package data

import scala.swing._
import javax.swing.UIManager
import swing.event._
//import javax.swing.						//irgendwas fehlt noch.

object Main extends scala.swing.SimpleSwingApplication {
  def top = new MainFrame {

    //Look and Feel
    try {
      UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel);
      //	     } catch {
      //	       case _ =&gt; UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName());
//    Groesse und Position auf dem Bildschirm
      		val framewidth = 640
    		val frameheight = 480
    }

    //Frametitel und Icon
    title = "Main Menu"
    
    //Initialisierung
      contents = new GridPanel(2,2){
      hGap = 20
      vGap = 20
    
      //Begruessungstext  
    var begruessung = new Label("Spieler1, Seite wählen")
    contents += begruessung
    
    //Button ROM
    var buttonRom:Button = new Button {
        text = "Rom"
          reactions += {
          case ButtonClicked(_) => text="Rom gewählt"; buttonKar.text = "Karthago"; 
            }
      }
    contents += buttonRom
    
    //Button Karthago
    var buttonKar:Button = new Button {
      text = "Karthago"
        reactions += {
        case ButtonClicked(_) => text="Karthago gewählt"; buttonRom.text = "Rom";
      }
    }
    contents += buttonKar
    }
      
    
    
    
    //    iconImage = java.awt.Toolkit.getDefaultToolkit().getImage(resourceFromClassloader("files/testicon.png"))

    //Groesse und Position auf dem Bildschirm
/*    val framewidth = 640
    val frameheight = 480
    val screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize()
    location = new java.awt.Point((screenSize.width - framewidth) / 2, (screenSize.height - frameheight) / 2)
    minimumSize = new java.awt.Dimension(framewidth, frameheight)
*/
    //    val diagAction = Action("notify me") {Dialog.showMessage(panel, "Here is a message for you.")} //Problem mit "panel"
  }
}

/*
	/*
	* Main.scala
	*/
	package gui
	 
	import swing._
	import actors._
	import actors.Actor._
	import javax.swing.UIManager
	 
	object Main extends SimpleGUIApplication {
	 
	   def top = new MainFrame {
	 
	      // 1. Define some actions
	 
	      //action using actors
	      val parallelAction = Action("hundred thousand messages"){
	         val reciever: Actor = actor {loop {react {case (increment:Int) =&gt; pbar.value=increment}}}
	         val task=new MyCounter().start
	         task ! reciever
	      }
	      //action displaying a Dialog
	      val diagAction = Action("notify me") {Dialog.showMessage(firstpanel, "Here is a message for you.")}
	      //action exit
	      val quitAction = Action("Quit") {System.exit(0)}
	 
	      
	      // 2. Set up the GUI
	 
	      //Look and Feel
	      try{
	         UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel);
	      } catch {
	         case _ =&gt; UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	      }
	  
	      //window title and icon
	      title="Test"
	      iconImage = java.awt.Toolkit.getDefaultToolkit.getImage(resourceFromClassloader("/gui/img/icon.png"))
	 
	      //arrange size and center frame on screen
	      val framewidth = 640
	      val frameheight = 480
	      val screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize()
	      location=new java.awt.Point((screenSize.width-framewidth)/2, (screenSize.height-frameheight)/2)
	      minimumSize=new java.awt.Dimension(framewidth, frameheight)
	 
	      //the menubar with menuitems connected to actions
	      menuBar = new MenuBar{
	         contents += new Menu("File") {
	            contents += new MenuItem(quitAction)
	         }
	         contents += new Menu("Edit") {
	            contents += new MenuItem(parallelAction)
	         }
	      }
	      
	      //frame contents with buttons connected to actions
	      val firstpanel=new FlowPanel{
	         contents += new Label{text="A Label"}
	         contents += new Button{action=parallelAction}
	      }
	      val pbar=new ProgressBar{labelPainted=true; max=100000; value=0}
	      val secondpanel=new FlowPanel{
	         contents += new Label{text="Another Label"}
	         contents += new Button{action=diagAction}
	         contents+=pbar
	      }
	      contents = new SplitPane(Orientation.Vertical, firstpanel, secondpanel){
	         dividerLocation=250
	         dividerSize=8
	         oneTouchExpandable=true
	      }
	 
	   }
	}
	 
	 
	class MyCounter() extends Actor {
	   def act() {
	      loop {
	         react {
	            //start action and permanently send feedback
	            case (reciever:Actor) =&gt; for(i &lt;- 1 to 100000) reciever ! i
	         }
	      }
	   }
	}
*/ 