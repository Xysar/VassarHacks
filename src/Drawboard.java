import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Drawboard extends JPanel implements MouseListener, MouseMotionListener {
    int color;
    int size;
    int mode = 1;

    int pixel[][] = new int[100][100];
    int pWidth = 100;

    public void setSize(int size){
        this.size = size;
    }
   public void setColor(int color){
       this.color = color;
    }
    public Drawboard() {
        for(int i = 0; i<100;i++)
            for(int j = 0; j<100;j++)
                pixel[i][i] = 0;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 600, 600);
            g.drawRect(0, 0, 600, 600);
       for(int i = 0;i<100;i++){
           for(int j  = 0;j<100;j++){
               switch(pixel[i][j]){
                   case 0:
                       g.setColor(Color.WHITE);
                       g.drawRect(i*6,j*6,6, 6);
                       g.fillRect(i*6, j*6,6, 6);
                       break;
                   case 1:
                       g.setColor(Color.BLACK);
                       g.drawRect(i*6,j*6,6, 6);
                       g.fillRect(i*6, j*6,6, 6);
               }

           }
       }


    }

    public void mouseDragged(MouseEvent e) {
        int mouseX = e.getX()/6;
        int mouseY = e.getY()/6;
if(mode == 1) {
        pixel[mouseX][mouseY] = 1;
}
    }


    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
}
