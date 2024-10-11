import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main implements ActionListener {

    JFrame frame;
    JTextField textfield;
    JButton[] numButtons = new JButton[10];
    JButton[] funButtons = new JButton[8];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, clrButton;
    JPanel panel;

    Font myFont = new Font("Lato", Font.BOLD, 20);

    double num1=0, num2=0, result=0;
    char operator;

    Main(){

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450,600);
        frame.setLayout(null);

        textfield = new JTextField();
        textfield.setBounds(25, 20, 400, 100);
        textfield.setFont(myFont);

        frame.add(textfield);
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