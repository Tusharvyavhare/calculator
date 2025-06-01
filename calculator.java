import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

 class CalculatorGUI extends JFrame implements ActionListener {
    private JTextField inputField;
    private double num1, num2, result;
    private char operator;

    public CalculatorGUI() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 24));
        inputField.setEditable(false);
        add(inputField, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.matches("[0-9]")) {
            inputField.setText(inputField.getText() + cmd);
        } else if (cmd.matches("[+\\-*/]")) {
            try {
                num1 = Double.parseDouble(inputField.getText());
                operator = cmd.charAt(0);
                inputField.setText("");
            } catch (NumberFormatException ex) {
                inputField.setText("Error");
            }
        } else if (cmd.equals("=")) {
            try {
                num2 = Double.parseDouble(inputField.getText());
                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/':
                        if (num2 == 0) {
                            inputField.setText("Cannot divide by 0");
                            return;
                        }
                        result = num1 / num2;
                        break;
                }
                inputField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                inputField.setText("Error");
            }
        } else if (cmd.equals("C")) {
            inputField.setText("");
        }
    }

    public static void main(String[] args) {
        new CalculatorGUI();
    }
}
