import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main implements ActionListener {

    //Declaring JFrame and other parts for GUI
    JFrame frame;
    JTextField textfield;
    //Creating buttons for calculator
    JButton[] numButtons = new JButton[10];
    JButton[] funButtons = new JButton[8];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton;
    JPanel panel;

    //declaring font as 'myfont' for using in the whole program
    Font myFont = new Font("digital-7", Font.PLAIN, 60);

    double num1=0, num2=0, result=0;
    char operator;

    Main(){

        //Creating GUI window
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450,600);
        frame.setLayout(null);

        //Creating calc screen using JTextField
        textfield = new JTextField();
        textfield.setBounds(25, 10, 400, 80);
        textfield.setFont(myFont);
        textfield.setEditable(false);

        //instantiating function Buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");

        //adding fun Buttons to array
        funButtons[0] = addButton;
        funButtons[1] = subButton;
        funButtons[2] = mulButton;
        funButtons[3] = divButton;
        funButtons[4] = decButton;
        funButtons[5] = equButton;
        funButtons[6] = delButton;
        funButtons[7] = clrButton;

        //loop to iterate through funButtons to add AL & change font
        for(int i=0; i<8;i++){
            funButtons[i].addActionListener(this);
            funButtons[i].setFont(myFont);
            funButtons[i].setFocusable(false);
        }

        delButton.setBounds(25,490,190,80);
        clrButton.setBounds(235,490,190,80);

        // loop to iterate through numButtons adding values 0 to 9
        for(int i=0; i<10;i++){
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].setFont(myFont);
            numButtons[i].setFocusable(false);
        }

        panel = new JPanel();
        panel.setBounds(25, 90, 400,400);
        panel.setLayout(new GridLayout(4,4,10,10));

        panel.add(numButtons[1]);
        panel.add(numButtons[2]);
        panel.add(numButtons[3]);
        panel.add(addButton);
        panel.add(numButtons[4]);
        panel.add(numButtons[5]);
        panel.add(numButtons[6]);
        panel.add(subButton);
        panel.add(numButtons[7]);
        panel.add(numButtons[8]);
        panel.add(numButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        frame.add(textfield);
        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        Main main = new Main();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}