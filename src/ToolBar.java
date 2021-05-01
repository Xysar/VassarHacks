import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar extends JPanel{
    public static final int PENCIL = 0;
    public static final int ERASER = 1;
    public static final int FREEDRAW = 0;
    public static final int LINEDRAW = 1;
    private final String[] colorNames;

    private JButton pencilB;
    private JPanel sizeP;
    private JLabel sizeL;
    private JButton sizeDown;
    private JLabel size;
    private JButton sizeUp;
    private JButton eraserB;
    private JComboBox<String> colorBox;
    private JButton freeDrawB;
    private JButton lineB;

    private ClickListener listener;

    //for tracking which buttons to gray out
    private JButton toolB;
    private JButton drawStyleB;

    public ToolBar() {
        listener = new ClickListener();

        colorNames = new String[] {"Red", "Green", "Blue", "Black"};

        //component initializations
        pencilB = new JButton("Pencil");
        sizeP = new JPanel();
        sizeL = new JLabel("Brush Size:", JLabel.CENTER);
        sizeDown = new JButton("-");
        size = new JLabel("1", JLabel.CENTER);
        sizeUp = new JButton("+");
        eraserB = new JButton("Eraser");
        colorBox = new JComboBox<>(colorNames);
        freeDrawB = new JButton("Free Draw");
        lineB = new JButton("Line");

        //panel setup
        this.setLayout(null);
        this.setBounds(10, 10, 200, 600);

        pencilB.setBounds(40,50,120,25);
        pencilB.addActionListener(listener);
        pencilB.setEnabled(false);

        eraserB.setBounds(40, 80, 120, 25);
        eraserB.addActionListener(listener);

        sizeP.setLayout(null);
        sizeP.setBounds(40, 130, 120, 50);
        sizeL.setBounds(0, 0, 120, 25);
        sizeDown.setBounds(0, 25, 30, 25);
        sizeDown.addActionListener(listener);
        size.setBounds(30, 25, 60, 25);
        sizeUp.setBounds(90, 25, 30, 25);
        sizeUp.addActionListener(listener);
        sizeP.add(sizeL);
        sizeP.add(sizeDown);
        sizeP.add(size);
        sizeP.add(sizeUp);



        colorBox.setBounds(40, 190, 120, 25);
        colorBox.addActionListener(listener);
        colorBox.setActionCommand("colorSelected");

        freeDrawB.setBounds(40, 240, 120, 25);
        lineB.setBounds(40, 270, 120, 25);
        freeDrawB.addActionListener(listener);
        lineB.addActionListener(listener);
        freeDrawB.setEnabled(false);

        //adding components
        this.add(pencilB);
        this.add(sizeP);
        this.add(eraserB);
        this.add(colorBox);
        this.add(freeDrawB);
        this.add(lineB);

        toolB = pencilB;
        drawStyleB = freeDrawB;
    }

    public void setTool(int tool) {
        if (tool == PENCIL) {
            toolB.setEnabled(true);
            toolB = pencilB;
            toolB.setEnabled(false);
            System.out.println("Using the pencil");
        }
        else if (tool == ERASER) {
            toolB.setEnabled(true);
            toolB = eraserB;
            toolB.setEnabled(false);
            System.out.println("Using the eraser");
        }
    }

    public void decreaseSize() {
        size.setText((Integer.parseInt(size.getText()) - 1) + "");
        System.out.println("Size decreased");
    }

    public void increaseSize() {
        size.setText((Integer.parseInt(size.getText()) + 1) + "");
        System.out.println("Size increased");
    }

    //method to handle a new color selection
    public void setColor() {
        System.out.println("Using " + ((String) colorBox.getSelectedItem()).toLowerCase());
    }

    //method to handle setting draw method
    public void setDrawMethod(int draw) {
        if (draw == FREEDRAW) {
            drawStyleB.setEnabled(true);
            drawStyleB = freeDrawB;
            drawStyleB.setEnabled(false);
            System.out.println("Drawing style set to free draw");
        }
        else if (draw == LINEDRAW) {
            drawStyleB.setEnabled(true);
            drawStyleB = lineB;
            drawStyleB.setEnabled(false);
            System.out.println("Drawing style set to line drawing");
        }
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
            else if (e.getActionCommand().equals("colorSelected"))
                setColor();
            else if (e.getActionCommand().equals("Free Draw"))
                setDrawMethod(FREEDRAW);
            else if (e.getActionCommand().equals("Line"))
                setDrawMethod(LINEDRAW);
        }
    }
}
