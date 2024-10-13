import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.*;

public class Main implements ActionListener {
    // Declaring JFrame and other parts for GUI
    JFrame frame;
    JTextField textfield;
    JTextField scitextfield;
    // Creating buttons for calculator
    JButton[] numButtons = new JButton[10];
    JButton[] funButtons = new JButton[10];
    JButton[] scifunButtons = new JButton[9];
    JButton addButton, subButton, mulButton, divButton, modButton;
    JButton decButton, equButton, delButton, clrButton, calcButton;
    JButton sinButton, cosButton, tanButton, sqrtButton, powButton, sqrt3Button, facButton, logButton, piButton;
    JButton modeButton; // JButton for dropdown
    JPopupMenu modeMenu; // Popup menu for dropdown options
    JPanel panel, scipanel;
    Font myFont = new Font("Helvetica", Font.PLAIN, 45);
    Font sciFont = new Font("Helvetica", Font.PLAIN, 25);
    double num1 = 0, num2 = 0, result = 0;
    Point flocation;
    String operator;

    Main() {
        BasicCalculator();
    }

    private void BasicCalculator() {
        if (frame != null) {
            flocation = frame.getLocation();
            frame.getContentPane().removeAll();
            frame.repaint();
        } else {
            frame = new JFrame("Calculator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(450, 600);
            frame.setLayout(null);
            frame.setResizable(false);
        }

        if (flocation != null) {
            frame.setLocation(flocation);
        } else {
            frame.setLocationRelativeTo(null); // Center if no previous location
        }

        // Creating calc screen using JTextField
        textfield = new JTextField();
        textfield.setBounds(25, 40, 400, 100);
        textfield.setFont(new Font("Helvetica", Font.PLAIN, 80));
        textfield.setHorizontalAlignment(SwingConstants.LEADING);
        textfield.setOpaque(false);
        textfield.setBorder(null);
        textfield.setEditable(false);
        textfield.setFocusable(false);

        // Add document listener to adjust font size based on input length
        textfield.getDocument().addDocumentListener(new DocumentListener() {
            private void adjustFontSize() {
                int length = textfield.getText().length();
                if (length > 15) {
                    textfield.setFont(new Font("Helvetica", Font.PLAIN, 30)); // Decrease font size
                }
                else if (length > 13) {
                    textfield.setFont(new Font("Helvetica", Font.PLAIN, 40));
                }
                else if (length > 11) {
                    textfield.setFont(new Font("Helvetica", Font.PLAIN, 50));
                }
                else if (length > 8) {
                    textfield.setFont(new Font("Helvetica", Font.PLAIN, 60));
                }
                else {
                    textfield.setFont(new Font("Helvetica", Font.PLAIN, 80)); // Default font size
                }
                textfield.revalidate();
                textfield.repaint();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                adjustFontSize();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                adjustFontSize();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                adjustFontSize();
            }
        });

        // Instantiating function buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("CE");
        clrButton = new JButton("AC");
        modButton = new JButton("%");
        calcButton = new JButton("C");

        // Creating the dropdown button
        modeButton = new JButton();
        ImageIcon icon = new ImageIcon("calc.png");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        modeButton.setIcon(icon);
        modeButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
        modeButton.setFocusable(false);
        modeButton.addActionListener(e -> modeMenu.show(modeButton, 0, modeButton.getHeight()));

        // Creating a popup menu for dropdown options
        modeMenu = new JPopupMenu();
        JMenuItem basicMode = new JMenuItem("Basic");
        JMenuItem scientificMode = new JMenuItem("Scientific");

        // Adding action listeners to the menu items
        basicMode.addActionListener(e -> BasicCalculator());
        scientificMode.addActionListener(e -> SciCalculator());
        modeMenu.add(basicMode);
        modeMenu.add(scientificMode);

        // Adding function buttons to array
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

        // Loop to iterate through funButtons to add action listeners & change font
        for (int i = 0; i < 10; i++) {
            funButtons[i].addActionListener(this);
            funButtons[i].setFont(myFont);
            funButtons[i].setFocusable(false);
        }

        // Loop to iterate through numButtons adding values 0 to 9
        for (int i = 0; i < 10; i++) {
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].setFont(myFont);
            numButtons[i].setFocusable(false);
            numButtons[i].addActionListener(this);
        }

        panel = new JPanel();
        panel.setBounds(25, 145, 400, 400);
        panel.setLayout(new GridLayout(5, 4, 5, 10));

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
        frame.setVisible(true);
        frame.revalidate();
        frame.repaint();
    }

    // New method to switch to the Scientific Calculator
    private void SciCalculator() {
        if (frame != null) {
            flocation = frame.getLocation();
            frame.getContentPane().removeAll(); // Clear components instead of disposing
            frame.repaint();
        }

        // Creating GUI window
        frame.setTitle("Scientific Calculator");

        // Creating calc screen using JTextField
        scitextfield = new JTextField();
        scitextfield.setBounds(25, 10, 400, 100);
        scitextfield.setFont(new Font("Helvetica", Font.PLAIN, 80));
        scitextfield.setHorizontalAlignment(SwingConstants.LEADING);
        scitextfield.setOpaque(false);
        scitextfield.setBorder(null);
        scitextfield.setEditable(false);
        scitextfield.setFocusable(false);

        // Add document listener to adjust font size based on input length
        scitextfield.getDocument().addDocumentListener(new DocumentListener() {
            private void adjustFontSize() {
                int length = scitextfield.getText().length();
                if (length > 15) {
                    scitextfield.setFont(new Font("Helvetica", Font.PLAIN, 30)); // Decrease font size
                }
                else if (length > 13) {
                    scitextfield.setFont(new Font("Helvetica", Font.PLAIN, 40));
                }
                else if (length > 11) {
                    scitextfield.setFont(new Font("Helvetica", Font.PLAIN, 50));
                }
                else if (length > 8) {
                    scitextfield.setFont(new Font("Helvetica", Font.PLAIN, 60));
                }
                else {
                    scitextfield.setFont(new Font("Helvetica", Font.PLAIN, 80));
                }
                scitextfield.revalidate();
                scitextfield.repaint();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                adjustFontSize();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                adjustFontSize();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                adjustFontSize();
            }
        });

        // Creating the dropdown button
        modeButton = new JButton();
        ImageIcon icon = new ImageIcon("calc.png");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        modeButton.setIcon(icon);
        modeButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
        modeButton.setFocusable(false);
        modeButton.addActionListener(e -> modeMenu.show(modeButton, 0, modeButton.getHeight()));

        // Creating a popup menu for dropdown options
        modeMenu = new JPopupMenu();
        JMenuItem basicMode = new JMenuItem("Basic");
        JMenuItem scientificMode = new JMenuItem("Scientific");

        // Adding action listeners to the menu items
        basicMode.addActionListener(e -> BasicCalculator());
        scientificMode.addActionListener(e -> SciCalculator());
        modeMenu.add(basicMode);
        modeMenu.add(scientificMode);

        // Instantiating function buttons
        sinButton = new JButton("sin");
        cosButton = new JButton("cos");
        tanButton = new JButton("tan");
        sqrtButton = new JButton("√");
        sqrt3Button = new JButton("3√");
        powButton = new JButton("^");
        facButton = new JButton("!");
        logButton = new JButton("log");
        piButton = new JButton("π");

        // Adding scientific function buttons to array
        scifunButtons[0] = sinButton;
        scifunButtons[1] = cosButton;
        scifunButtons[2] = tanButton;
        scifunButtons[3] = sqrtButton;
        scifunButtons[4] = sqrt3Button;
        scifunButtons[5] = powButton;
        scifunButtons[6] = facButton;
        scifunButtons[7] = logButton;
        scifunButtons[8] = piButton;

        for (int i = 0; i < 9; i++) {
            scifunButtons[i].addActionListener(this);
            scifunButtons[i].setFont(new Font("Helvetica", Font.PLAIN, 20));
            scifunButtons[i].setFocusable(false);
        }

        // Loop to iterate through numButtons adding values 0 to 9
        for (int i = 0; i < 10; i++) {
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].setFont(sciFont);
            numButtons[i].setFocusable(false);
            numButtons[i].addActionListener(this);
        }

        // Adding function buttons to array
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

        // Loop to iterate through funButtons to add action listeners & change font
        for (int i = 0; i < 10; i++) {
            funButtons[i].addActionListener(this);
            funButtons[i].setFont(sciFont);
            funButtons[i].setFocusable(false);
        }

        // Loop to iterate through numButtons adding values 0 to 9
        for (int i = 0; i < 10; i++) {
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].setFont(sciFont);
            numButtons[i].setFocusable(false);
            numButtons[i].addActionListener(this);
        }

        panel = new JPanel();
        panel.setBounds(25, 260, 400, 300);
        panel.setLayout(new GridLayout(5, 4, 5, 10));

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

        scipanel = new JPanel();
        scipanel.setBounds(25, 105, 400, 150);
        scipanel.setLayout(new GridLayout(3, 3, 5, 10));

        scipanel.add(sinButton);
        scipanel.add(cosButton);
        scipanel.add(tanButton);
        scipanel.add(sqrtButton);
        scipanel.add(sqrt3Button);
        scipanel.add(powButton);
        scipanel.add(facButton);
        scipanel.add(logButton);
        scipanel.add(piButton);

        frame.add(scitextfield);
        frame.add(scipanel);
        frame.add(panel);
        frame.setVisible(true);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numButtons[i]) {
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
                scitextfield.setText(scitextfield.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decButton) {
            textfield.setText(textfield.getText().concat("."));
            scitextfield.setText(scitextfield.getText().concat("."));
        }
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = "+";
            textfield.setText("");
            scitextfield.setText("");
        }
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = "-";
            textfield.setText("");
            scitextfield.setText("");
        }
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = "*";
            textfield.setText("");
            scitextfield.setText("");
        }
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = "/";
            textfield.setText("");
            scitextfield.setText("");
        }
        if (e.getSource() == modButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = "%";
            textfield.setText("");
            scitextfield.setText("");
        }
        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textfield.getText());
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
                case "%":
                    result = num1 % num2;
                    break;
            }
            textfield.setText(String.valueOf(result));
            scitextfield.setText(String.valueOf(result));
            num1 = result;
        }
        if (e.getSource() == clrButton) {
            textfield.setText("");
            scitextfield.setText("");
        }
        if (e.getSource() == delButton) {
            String string = textfield.getText();
            textfield.setText("");
            scitextfield.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                textfield.setText(textfield.getText() + string.charAt(i));
                scitextfield.setText(scitextfield.getText() + string.charAt(i));
            }
        }
        // Implement additional action handling for scientific functions here
        if (e.getSource() == sinButton) {
            num1 = Double.parseDouble(scitextfield.getText());
            result = Math.sin(Math.toRadians(num1));
            scitextfield.setText(String.valueOf(result));
        }
        if (e.getSource() == cosButton) {
            num1 = Double.parseDouble(scitextfield.getText());
            result = Math.cos(Math.toRadians(num1));
            scitextfield.setText(String.valueOf(result));
        }
        if (e.getSource() == tanButton) {
            num1 = Double.parseDouble(scitextfield.getText());
            result = Math.tan(Math.toRadians(num1));
            scitextfield.setText(String.valueOf(result));
        }
        if (e.getSource() == sqrtButton) {
            num1 = Double.parseDouble(scitextfield.getText());
            result = Math.sqrt(num1);
            scitextfield.setText(String.valueOf(result));
        }
        if (e.getSource() == sqrt3Button) {
            num1 = Double.parseDouble(scitextfield.getText());
            result = Math.cbrt(num1);
            scitextfield.setText(String.valueOf(result));
        }
        if (e.getSource() == powButton) {
            num1 = Double.parseDouble(scitextfield.getText());
            operator = "^";
            scitextfield.setText("");
        }
        if (e.getSource() == facButton) {
            num1 = Double.parseDouble(scitextfield.getText());
            result = factorial((int) num1);
            scitextfield.setText(String.valueOf(result));
        }
        if (e.getSource() == logButton) {
            num1 = Double.parseDouble(scitextfield.getText());
            result = Math.log10(num1);
            scitextfield.setText(String.valueOf(result));
        }
        if (e.getSource() == piButton) {
            result = Math.PI;
            scitextfield.setText(String.valueOf(result));
        }
    }

    public double factorial(int n) {
        if (n == 0)
            return 1;
        return n * factorial(n - 1);
    }


}

