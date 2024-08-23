import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class buttonView {

    public static void roundbutton(JButton button) {
        // Make the button transparent
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        // Add an ActionListener to handle the blink effect
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Change button's background to white
                button.setBackground(Color.WHITE);
                button.setOpaque(true);
                button.setContentAreaFilled(true);

                // Use a Timer to revert the color after a short delay
                Timer timer = new Timer(100, new ActionListener() { // 100ms delay
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        button.setOpaque(false);
                        button.setContentAreaFilled(false);
                        ((Timer) evt.getSource()).stop(); // Stop the timer after execution
                    }
                });
                timer.setRepeats(false); // Ensure it only runs once
                timer.start();
            }
        });
    }
}
