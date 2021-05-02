import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Whiteboard {
    JFrame board;
    ToolBar toolP;
    JButton pencilB;
    Border loweredetched;
    JTextArea jTextArea1;
    Drawboard draw;
    int boardSize = 600;

    public Whiteboard(){
        this.toolP = new ToolBar(this);
        this.loweredetched = BorderFactory.createEtchedBorder(1);
        this.pencilB = new JButton("Pencil");
        this.draw = new Drawboard();


        this.initialize();
    }

    public void initialize() {
        this.board = new JFrame();
        this.board.setResizable(false);
        this.board.setSize(850, 650);
        this.board.setTitle("White board");
        this.board.setDefaultCloseOperation(3);
        this.board.getContentPane().setLayout(null);
        this.toolP.setBorder(BorderFactory.createTitledBorder(this.loweredetched, "Palette"));
        this.board.getContentPane().add(toolP);
        this.pencilB.setBounds(40, 50, 120, 25);

        this.board.setVisible(true);
        this.draw.setBounds(230, 10, boardSize, boardSize);
        this.board.getContentPane().add(this.draw);
        while (true) {
            draw.repaint();
            this.draw.setMode(this.toolP.getMode());
            this.draw.setSize(this.toolP.getPenSize());
            this.draw.setText(this.toolP.getText());
        }
    }

    public void clear() {
        draw.clear();
        System.out.println("Cleared!");
    }


    public static void main(String[] args){
        new Whiteboard();
    }
}
