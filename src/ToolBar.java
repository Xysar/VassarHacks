import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar extends JPanel{
    public static final int PENCIL = 0;
    public static final int ERASER = 1;

    private JButton pencilB;
    private JPanel sizeP;
    private JButton sizeDown;
    private JLabel size;
    private JButton sizeUp;
    private JButton eraserB;

    private ClickListener listener;

    public ToolBar() {
        listener = new ClickListener();

        pencilB = new JButton("Pencil");
        sizeP = new JPanel();
        sizeDown = new JButton("-");
        size = new JLabel("1", JLabel.CENTER);
        sizeUp = new JButton("+");
        eraserB = new JButton("Eraser");

        //this.setPreferredSize(new Dimension(200,600));
        this.setLayout((LayoutManager)null);
        this.setBounds(10, 10, 200, 600);

        pencilB.setBounds(40,50,120,25);
        pencilB.addActionListener(listener);

        sizeP.setLayout((LayoutManager) null);
        sizeP.setBounds(40, 100, 120, 25);
        sizeDown.setBounds(0, 0, 25, 25);
        sizeDown.addActionListener(listener);
        size.setBounds(25, 0, 70, 25);
        sizeUp.setBounds(95, 0, 25, 25);
        sizeUp.addActionListener(listener);
        sizeP.add(sizeDown);
        sizeP.add(size);
        sizeP.add(sizeUp);

        eraserB.setBounds(40, 150, 120, 25);
        eraserB.addActionListener(listener);

        this.add(pencilB);
        this.add(sizeP);
        this.add(eraserB);
    }

    public void setTool(int tool) {
        if (tool == PENCIL)
            System.out.println("Using the pencil");
        else if (tool == ERASER)
            System.out.println("Using the eraser");
    }

    public void decreaseSize() {
        size.setText((Integer.parseInt(size.getText()) - 1) + "");
        System.out.println("Size decreased");
    }

    public void increaseSize() {
        size.setText((Integer.parseInt(size.getText()) + 1) + "");
        System.out.println("Size increased");
    }

    private class ClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Pencil"))
                setTool(PENCIL);
            else if (e.getActionCommand().equals("Eraser"))
                setTool(ERASER);
            else if (e.getActionCommand().equals("-"))
                decreaseSize();
            else if (e.getActionCommand().equals("+"))
                increaseSize();
        }
    }
}
