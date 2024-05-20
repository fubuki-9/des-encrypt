




import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JButton;



public class ButtonClickListener implements MouseListener{

  static Client ge;



  public ButtonClickListener(Client ge){
    this.ge = ge;
    }



  public void mouseClicked(MouseEvent e){
    ge.buttonClicked((JButton)e.getSource());
    }



  public void mouseEntered(MouseEvent e){
    }



  public void mouseExited(MouseEvent e){
    }



  public void mousePressed(MouseEvent e){
    }



  public void mouseReleased(MouseEvent e){
    }


  }