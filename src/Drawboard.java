import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Drawboard extends JPanel implements MouseListener, MouseMotionListener {
    int color;
    int size = 1;
    int mode = 1;
    int pastX = -1;
    int pastY = -1;
    String text;

    int pWidth = 100;
    int pixel[][] = new int[pWidth][pWidth];

    public void setSize(int size){
        this.size = size;
    }
   public void setMode(int mode){
       this.mode= mode;
    }

    public void setText(String text){
        this.text = text;
    }
    public Drawboard() {
        for(int i = 0; i<pWidth;i++)
            for(int j = 0; j<pWidth;j++)
                pixel[i][j] = 0;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void clear() {
        for(int i = 0; i<pWidth;i++)
            for(int j = 0; j<pWidth;j++)
                pixel[i][j] = 0;
    }

    public void capImg() {
        BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        this.printAll(g);
        g.dispose();
        try {
            String home = System.getProperty("user.home");
            String filePath = home + File.separator + "Downloads" + File.separator + "painting";
            File file = new File(filePath + ".jpg");
            for (int i = 1; file.exists(); i++) {
                file = new File(filePath + i + ".jpg");
            }
            ImageIO.write(image, "jpg", file);
        } catch (IOException exp) {
            exp.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 600, 600);
            g.drawRect(0, 0, 600, 600);
        int scale = 600/pWidth;
       for(int i = 0;i<pWidth;i++){
           for(int j  = 0;j<pWidth;j++){
               switch(pixel[i][j]){
                   case 0:
                       g.setColor(Color.WHITE);
                       g.drawRect(i*scale,j*scale, scale, scale);
                       g.fillRect(i*scale, j*scale, scale, scale);
                       break;
                   case 1:
                       g.setColor(Color.BLACK);
                       g.drawRect(i*scale,j*scale,scale, scale);
                       g.fillRect(i*scale, j*scale,scale, scale);
                       break;
                   case 2:
                       g.setColor(Color.RED);
                       g.drawRect(i*scale,j*scale,scale, scale);
                       g.fillRect(i*scale, j*scale,scale, scale);
                       break;
                   case 3:
                       g.setColor(Color.GREEN);
                       g.drawRect(i*scale,j*scale,scale, scale);
                       g.fillRect(i*scale, j*scale,scale, scale);
                       break;
                   case 4:
                       g.setColor(Color.BLUE);
                       g.drawRect(i*scale,j*scale,scale, scale);
                       g.fillRect(i*scale, j*scale,scale, scale);
                       break;

               }

               g.setColor(Color.BLACK);
               g.drawString(text,250,300);
           }
       }

    }

    public void mouseDragged(MouseEvent e) {
        int mouseX = e.getX() / (600/pWidth);
        int mouseY = e.getY() / (600/pWidth);
        int m = mode;
        if (this.size % 2 == 1) {
            for (int i = mouseX - ((size - 1) / 2); i <= mouseX + ((size - 1) / 2); i++) {
                for (int j = mouseY - ((size - 1) / 2); j <= mouseY + ((size - 1) / 2); j++) {
                    try {
                        pixel[i][j] = m;
                    } catch (IndexOutOfBoundsException b) {}
                }
            }
        } else {
            for (int i = mouseX - (size / 2) + 1; i <= mouseX + (size / 2); i++) {
                for (int j = mouseY - (size / 2) + 1; j <= mouseY + (size / 2); j++) {
                    try {
                        pixel[i][j] = m;
                    } catch (IndexOutOfBoundsException b) {}
                }
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX()/(600/pWidth);
        int mouseY = e.getY()/(600/pWidth);
        int m = mode;
        if (this.size % 2 == 1) {
            for (int i = mouseX - ((size - 1) / 2); i <= mouseX + ((size - 1) / 2); i++) {
                for (int j = mouseY - ((size - 1) / 2); j <= mouseY + ((size - 1) / 2); j++) {
                    try {
                    pixel[i][j] = m;
                    } catch (IndexOutOfBoundsException b) {}
                }
            }
        } else {
            for (int i = mouseX - (size / 2) + 1; i <= mouseX + (size / 2); i++) {
                for (int j = mouseY - (size / 2) + 1; j <= mouseY + (size / 2); j++) {
                    try {
                    pixel[i][j] = m;
                    } catch (IndexOutOfBoundsException b) {}
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
