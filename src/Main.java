import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main implements ActionListener {

    //Declaring JFrame and other parts for GUI
    JFrame frame;
    JTextField textfield;
    //Creating buttons for calculator
    JButton[] numButtons = new JButton[10];
    JButton[] funButtons = new JButton[10];
    JButton addButton, subButton, mulButton, divButton, modButton;
    JButton decButton, equButton, delButton, clrButton, calcButton;
    JButton modeButton; // JButton for dropdown
    JPopupMenu modeMenu; // Popup menu for dropdown options
    JPanel panel;

    //declaring font as 'myfont' for using in the whole program
    Font myFont = new Font("digital-7", Font.PLAIN, 60);

    double num1=0, num2=0, result=0;
    String operator;

    Main(){

        //Creating GUI window
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450,600);
        frame.setLayout(null);

        //Creating calc screen using JTextField
        textfield = new JTextField();
        textfield.setBounds(25, 10, 400, 80);
        textfield.setFont(new Font("digital-7", Font.PLAIN, 80));
        textfield.setHorizontalAlignment(SwingConstants.RIGHT);
        textfield.setOpaque(false);
        textfield.setBorder(null);
        textfield.setEditable(false);
        textfield.setFocusable(false);

        //instantiating function Buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");
        modButton = new JButton("%");
        calcButton = new JButton("C");

        // Creating the dropdown button
        modeButton = new JButton();
        ImageIcon icon = new ImageIcon("calc.png");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        modeButton.setIcon(icon);
        modeButton.setFont(new Font("digital-7", Font.PLAIN, 20));
        modeButton.setFocusable(false);
        modeButton.addActionListener(e -> modeMenu.show(modeButton, 0, modeButton.getHeight()));

        // Creating a popup menu for dropdown options
        modeMenu = new JPopupMenu();
        JMenuItem basicMode = new JMenuItem("Basic");
        JMenuItem scientificMode = new JMenuItem("Scientific");

        // Adding action listeners to the menu items
        basicMode.addActionListener(e -> textfield.setText("Basic Mode"));
        scientificMode.addActionListener(e -> textfield.setText("Scientific Mode"));
        modeMenu.add(basicMode);
        modeMenu.add(scientificMode);

        //adding fun Buttons to array
        funButtons[0] = addButton;
        funButtons[1] = subButton;
        funButtons[2] = mulButton;
        funButtons[3] = divButton;
        funButtons[4] = decButton;
        funButtons[5] = equButton;
        funButtons[6] = delButton;
        funButtons[7] = clrButton;
        funButtons[8] = modButton;
        funButtons[9] = modeButton;

        //loop to iterate through funButtons to add AL & change font
        for(int i=0; i<10;i++){
            funButtons[i].addActionListener(this);
            funButtons[i].setFont(myFont);
            funButtons[i].setFocusable(false);
        }

        // loop to iterate through numButtons adding values 0 to 9
        for(int i=0; i<10;i++){
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].setFont(myFont);
            numButtons[i].setFocusable(false);
            numButtons[i].addActionListener(this);
        }

        panel = new JPanel();
        panel.setBounds(25, 120, 400,400);
        panel.setLayout(new GridLayout(5,4,5,10));

        panel.add(delButton);
        panel.add(clrButton);
        panel.add(modButton);
        panel.add(divButton);
        panel.add(numButtons[7]);
        panel.add(numButtons[8]);
        panel.add(numButtons[9]);
        panel.add(mulButton);
        panel.add(numButtons[4]);
        panel.add(numButtons[5]);
        panel.add(numButtons[6]);
        panel.add(subButton);
        panel.add(numButtons[1]);
        panel.add(numButtons[2]);
        panel.add(numButtons[3]);
        panel.add(addButton);
        panel.add(modeButton);
        panel.add(numButtons[0]);
        panel.add(decButton);
        panel.add(equButton);

        frame.add(textfield);
        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        Main main = new Main();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i=0; i<10; i++){
            if(e.getSource() == numButtons[i]){
                textfield.setText(textfield.getText() + i);

            }
        }

        if(e.getSource() == decButton){
            textfield.setText(textfield.getText() + ".");
        }

        if(e.getSource() == addButton){
            num1 = Double.parseDouble((textfield.getText()));
            operator = "+";
            textfield.setText("");
        }

        if(e.getSource() == subButton){
            num1 = Double.parseDouble((textfield.getText()));
            operator = "-";
            textfield.setText("");
        }

        if(e.getSource() == divButton){
            num1 = Double.parseDouble((textfield.getText()));
            operator = "/";
            textfield.setText("");
        }

        if(e.getSource() == mulButton){
            num1 = Double.parseDouble((textfield.getText()));
            operator = "*";
            textfield.setText("");
        }

        if(e.getSource() == modButton){
            num1 = Double.parseDouble((textfield.getText()));
            operator = "%";
            textfield.setText("");
        }

        if(e.getSource() == equButton){
            num2 = Double.parseDouble(textfield.getText());

            switch(operator){
                case"+":
                    result = num1 + num2;
                    break;

                case"-":
                    result = num1 - num2;
                    break;

                case"*":
                    result = num1 * num2;
                    break;

                case"/":
                    result = num1 / num2;
                    break;

                case"%":
                    result = num1 % num2;
                    break;
            }

            textfield.setText(String.valueOf(result));
            num1 = result;
        }

        if(e.getSource() == clrButton){
            textfield.setText("");
        }

        if(e.getSource() == delButton){
            String string = textfield.getText();
            textfield.setText("");
            for(int i=0; i<string.length()-1; i++){
                textfield.setText(textfield.getText() + string.charAt(i));
            }
        }
    }
}
