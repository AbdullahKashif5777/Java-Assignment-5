import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        //  To Create JFrame
        JFrame jFrame = new JFrame("Scientific Calculator");
        ImageIcon icon = new ImageIcon("calculator.png");
        jFrame.setIconImage(icon.getImage());
        jFrame.setBounds(550, 130, 450, 600);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create main panel with gradient background
        GradientPanel mainPanel = new GradientPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create display panel with gradient background
        GradientPanel displayPanel = new GradientPanel();
        displayPanel.setLayout(new BorderLayout());
        displayPanel.setPreferredSize(new Dimension(380, 100));

        // Create text field
        JTextField jTextField = new JTextField();
        jTextField.setEditable(false);
        jTextField.setHorizontalAlignment(JTextField.RIGHT);
        jTextField.setPreferredSize(new Dimension(380, 100));
        jTextField.setFont(new Font("Arial", Font.BOLD, 30));

        // Create button panel with GridLayout
        GradientPanel buttonPanel = new GradientPanel();
        buttonPanel.setLayout(new GridLayout(7, 4, 4, 4));
        buttonPanel.setPreferredSize(new Dimension(380, 500));

        // Define button labels
        String[] buttonLabels = {
                "sqrt", "pow", "sin", "cos",
                "tan","log", "(", ")",
                "/","*","-", "+",
                "9", "8", "7","Del",
                "6", "5", "4", "CE",
                "3", "2", "1", "0",
                ".",",",".00","=",
        };

        // Create buttons and add them to the button panel
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.addActionListener(new buttonFunctions(jTextField));
            if(label.equals("=")){
                button.setBackground(new Color(29, 185, 84));
            }
            else {
                buttonView.roundbutton(button);
            }
            buttonPanel.add(button);
        }

        // Add components to the display panel
        displayPanel.add(jTextField, BorderLayout.NORTH);
        displayPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add the display panel to the main panel
        mainPanel.add(displayPanel, BorderLayout.CENTER);

        // Add the main panel to the frame
        jFrame.add(mainPanel);

        // Make the frame visible
        jFrame.setVisible(true);
    }
}
