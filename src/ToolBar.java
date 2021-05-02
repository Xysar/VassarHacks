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
    private JButton clearB;

    private int penSize;
    private ClickListener listener;
    private int mode = 1;
    private int color = 1;
    //for tracking which buttons to gray out
    private JButton toolB;
    private JButton drawStyleB;

    private Whiteboard whiteboard;

    public ToolBar(Whiteboard whiteboard) {
        listener = new ClickListener();
        this.whiteboard = whiteboard;

        colorNames = new String[] {"Black", "Red", "Green", "Blue"};

        //component initializations
        pencilB = new JButton("Pencil");
        sizeP = new JPanel();
        sizeL = new JLabel("Brush Size:", JLabel.CENTER);
        sizeDown = new JButton("-");
        size = new JLabel("1", JLabel.CENTER);
        penSize = Integer.parseInt(size.getText());
        sizeUp = new JButton("+");
        eraserB = new JButton("Eraser");
        colorBox = new JComboBox<>(colorNames);
        freeDrawB = new JButton("Free Draw");
        lineB = new JButton("Line");
        clearB = new JButton("Clear");

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

        clearB.setBounds(40, 320, 120, 25);
        clearB.addActionListener(listener);

        //adding components
        this.add(pencilB);
        this.add(sizeP);
        this.add(eraserB);
        this.add(colorBox);
        this.add(freeDrawB);
        this.add(lineB);
        this.add(clearB);

        toolB = pencilB;
        drawStyleB = freeDrawB;
    }

    public void setTool(int tool) {
        if (tool == PENCIL) {
            toolB.setEnabled(true);
            toolB = pencilB;
            toolB.setEnabled(false);
            mode = color;
            System.out.println("Using the pencil");
        }
        else if (tool == ERASER) {
            toolB.setEnabled(true);
            toolB = eraserB;
            toolB.setEnabled(false);
            mode = 0;
            System.out.println("Using the eraser");
        }
    }

    public void decreaseSize() {
        if (Integer.parseInt(size.getText()) <= 1) {
            System.out.println("Brush is at minimum size!");
        } else {
            size.setText((Integer.parseInt(size.getText()) - 1) + "");
            System.out.println("Size decreased");
            penSize--;
        }
    }

    public void increaseSize() {
        if (Integer.parseInt(size.getText()) >= 10) {
            System.out.println("Brush is at maximum size");
        } else {
            size.setText((Integer.parseInt(size.getText()) + 1) + "");
            System.out.println("Size increased" + size.getText());
            penSize++;
        }
    }
    public int getPenSize(){
        return penSize;
    }

    //method to handle a new color selection
    public void setColor() {
        String outC = ((String) colorBox.getSelectedItem()).toLowerCase();
        System.out.println("Using " + ((String) colorBox.getSelectedItem()).toLowerCase());
        if (mode != 0) {
            if (outC.equals("red")) {
                mode = 2;
            } else if (outC.equals("black"))
                mode = 0;
            else if (outC.equals("green"))
                mode = 3;
            else if (outC.equals("blue"))
                mode = 4;
        }
        if (outC.equals("red")) {
            color = 2;
        } else if (outC.equals("black"))
            color = 0;
        else if (outC.equals("green"))
            color = 3;
        else if (outC.equals("blue"))
            color = 4;
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

    public int getMode(){
        return mode;
    }
    private class ClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Pencil")) {
                setTool(PENCIL);
            }else if (e.getActionCommand().equals("Eraser")) {
                setTool(ERASER);
            }else if (e.getActionCommand().equals("-")) {
                decreaseSize();
                //penSize--;
            }
            else if (e.getActionCommand().equals("+")) {
                increaseSize();
                //penSize++;
            } else if (e.getActionCommand().equals("colorSelected"))
                setColor();
            else if (e.getActionCommand().equals("Free Draw"))
                setDrawMethod(FREEDRAW);
            else if (e.getActionCommand().equals("Line"))
                setDrawMethod(LINEDRAW);
            else if (e.getActionCommand().equals("Clear"))
                whiteboard.clear();
        }
    }
}
