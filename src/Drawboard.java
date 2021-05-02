import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Drawboard extends JPanel implements MouseListener, MouseMotionListener {
    int color;
    int size = 1;
    int mode = 1;
    int pastX;
    int pastY;

    int pixel[][] = new int[100][100];
    int pWidth = 100;

    public void setSize(int size){
        this.size = size;
    }
   public void setMode(int mode){
       this.mode= mode;
    }
    public Drawboard() {
        for(int i = 0; i<100;i++)
            for(int j = 0; j<100;j++)
                pixel[i][j] = 0;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void clear() {
        for(int i = 0; i<100;i++)
            for(int j = 0; j<100;j++)
                pixel[i][j] = 0;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
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
                       break;
                   case 2:
                       g.setColor(Color.RED);
                       g.drawRect(i*6,j*6,6, 6);
                       g.fillRect(i*6, j*6,6, 6);
                       break;
                   case 3:
                       g.setColor(Color.GREEN);
                       g.drawRect(i*6,j*6,6, 6);
                       g.fillRect(i*6, j*6,6, 6);
                       break;
                   case 4:
                       g.setColor(Color.BLUE);
                       g.drawRect(i*6,j*6,6, 6);
                       g.fillRect(i*6, j*6,6, 6);
                       break;

               }
//                       if(pastX > -1 && pastY > -1 ){
//                           g2.setStroke(new BasicStroke(3.0f));
//                           g2.drawLine(pastX, pastY, i*6, j*6);
//                       }
               pastX = i*6;
               pastY = j*6;

           }
       }

    }

    public void mouseDragged(MouseEvent e) {
        int mouseX = e.getX() / 6;
        int mouseY = e.getY() / 6;
        int m = mode;
        if (this.size % 2 == 1) {
            for (int i = mouseX - ((size - 1) / 2); i <= mouseX + ((size - 1) / 2); i++) {
                for (int j = mouseY - ((size - 1) / 2); j <= mouseY + ((size - 1) / 2); j++) {
                    pixel[i][j] = m;
                }
            }
        } else {
            for (int i = mouseX - (size / 2) + 1; i <= mouseX + (size / 2); i++) {
                for (int j = mouseY - (size / 2) + 1; j <= mouseY + (size / 2); j++) {
                    pixel[i][j] = m;
                }
            }
        }
    }



    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX()/6;
        int mouseY = e.getY()/6;
        int m = mode;
        if (this.size % 2 == 1) {
            for (int i = mouseX - ((size - 1) / 2); i <= mouseX + ((size - 1) / 2); i++) {
                for (int j = mouseY - ((size - 1) / 2); j <= mouseY + ((size - 1) / 2); j++) {
                    pixel[i][j] = m;
                }
            }
        } else {
            for (int i = mouseX - (size / 2) + 1; i <= mouseX + (size / 2); i++) {
                for (int j = mouseY - (size / 2) + 1; j <= mouseY + (size / 2); j++) {
                    pixel[i][j] = m;
                }
            }
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
        pastX = -1;
        pastY = -1;
    }
}
