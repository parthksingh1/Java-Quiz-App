import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage extends JFrame {
    private JTextField nameField;

    public WelcomePage() {
        super("Welcome to Quiz App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(173, 216, 230));

        ImageIcon quizAppImage = new ImageIcon("/icons/quiz.jpg"); 
        JLabel imageLabel = new JLabel(new ImageIcon(quizAppImage.getImage().getScaledInstance(600, 250, Image.SCALE_DEFAULT)));
        JLabel welcomeLabel = new JLabel("Welcome to the Quiz App!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel companyNameLabel = new JLabel("Parth");
        companyNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        companyNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("Enter your name:");
        nameField = new JTextField(15);
        nameField.setMaximumSize(new Dimension(150, 30));
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startButton = new JButton("Start Quiz");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = nameField.getText();
                if (!playerName.isEmpty()) {
                    openQuizApp(playerName);
                } else {
                    JOptionPane.showMessageDialog(WelcomePage.this, "Please enter your name.");
                }
            }
        });
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(20));
        panel.add(imageLabel);
        panel.add(companyNameLabel);
        panel.add(welcomeLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(startButton);

        add(panel);
    }

    private void openQuizApp(String playerName) {
        new QuizApp(playerName).setVisible(true);
        this.dispose(); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new WelcomePage().setVisible(true);
            }
        });
    }
}