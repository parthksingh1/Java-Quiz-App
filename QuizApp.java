import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApp extends JFrame {
    private JLabel questionLabel, imageLabel, resultLabel;
    private JRadioButton optionARadio, optionBRadio, optionCRadio, optionDRadio;
    private ButtonGroup optionGroup;
    private JButton nextButton, prevButton;
    private int currentQuestionIndex;

private String[] questions = {
        "What does JVM stand for in the context of Java?",
        "Which keyword is used to declare a constant in Java?",
        "What is the main purpose of the 'static' keyword in Java?",
        "In Java, what is the difference between '== ' and '.equals()' when comparing two objects?",
        "What is the Java 'Garbage Collection' responsible for?",
        "Which class is the superclass for all classes in Java?",
        "What is the purpose of the 'final' keyword in Java?",
        "What is the difference between 'ArrayList' and 'LinkedList' in Java?",
        "What is the purpose of the 'throws' clause in a method signature?",
        "Explain the concept of 'inheritance' in Java."
};

private String[][] options = {
        {"Java Virtual Machine", "Java Visual Machine", "Java Variable Machine", "Java Validation Machine"},
        {"const", "final", "static", "var"},
        {"To define a constant", "To indicate that a method cannot be overridden", "To declare a class as abstract", "To access instance variables"},
        {"'==' compares object references, '.equals()' compares object values", "'==' compares primitive values, '.equals()' compares object references", "'==' is used for arithmetic operations, '.equals()' is used for string concatenation", "'==' is used for logical comparisons, '.equals()' is used for bitwise operations"},
        {"Managing and reclaiming memory occupied by unreachable objects", "Optimizing code execution speed", "Handling runtime exceptions", "Managing user input"},
        {"Object", "Main", "Super", "Base"},
        {"To indicate that a variable cannot be modified", "To prevent inheritance of a class", "To declare a method that cannot be overridden", "To define a constant value"},
        {"ArrayList is faster for random access, LinkedList is efficient for insertions and deletions", "ArrayList uses a linked structure, LinkedList uses an array structure", "ArrayList is a resizable array, LinkedList is a fixed-size list", "ArrayList allows duplicate elements, LinkedList does not"},
        {"To specify the exceptions that a method might throw", "To catch exceptions during runtime", "To handle errors within a method", "To declare a method as deprecated"},
        {"It allows a class to inherit properties and behaviors from another class", "It enables one class to access the fields and methods of another class", "It is a way to achieve multiple inheritance in Java", "It is used for code organization and reusability"}
};

private char[] correctAnswers = {'A', 'B', 'B', 'A', 'A', 'A', 'C', 'A', 'A', 'A'};


    private int score;

    private ImageIcon[] images = {
            new ImageIcon("/icons/download.jpeg"),
            new ImageIcon("/icons/download.jpeg"),
            new ImageIcon("/icons/download.jpeg"),
            null,
            null,
            null,
            new ImageIcon("/icons/download.jpeg"),
            null,
            null,
            null
    };

    private ImageIcon celebrationImage = new ImageIcon("/icons/giphy.gif");

    public QuizApp(String playerName) {
        super("Quiz App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        initializeComponents();
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(255, 245, 215));
        centerPanel.add(questionLabel);
        centerPanel.add(imageLabel);
        centerPanel.add(optionARadio);
        centerPanel.add(optionBRadio);
        centerPanel.add(optionCRadio);
        centerPanel.add(optionDRadio);

        add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(135, 206, 250));
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);

        add(buttonPanel, BorderLayout.SOUTH);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new FlowLayout());
        resultLabel = new JLabel();
        resultPanel.add(resultLabel);
        add(resultPanel, BorderLayout.NORTH);

        updateQuestion();

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                currentQuestionIndex++;

                if (currentQuestionIndex < questions.length) {
                    updateQuestion();
                } else {
                    showResult();
                }
            }
        });

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex--;
                    updateQuestion();
                }
            }
        });
    }

    private void initializeComponents() {
        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        imageLabel = new JLabel();
        optionARadio = createOptionButton("A");
        optionBRadio = createOptionButton("B");
        optionCRadio = createOptionButton("C");
        optionDRadio = createOptionButton("D");

        optionGroup = new ButtonGroup();
        optionGroup.add(optionARadio);
        optionGroup.add(optionBRadio);
        optionGroup.add(optionCRadio);
        optionGroup.add(optionDRadio);

        nextButton = createButton("Next", Color.GREEN);
        prevButton = createButton("Previous", Color.RED);
    }

    private JRadioButton createOptionButton(String text) {
        JRadioButton button = new JRadioButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setBackground(new Color(255, 222, 173));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleOptionSelection(text);
            }
        });
        return button;
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(color);  
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        return button;
    }

    private void updateQuestion() {
        resultLabel.setText("Question " + (currentQuestionIndex + 1) + " of " + questions.length);
        questionLabel.setText("<html>" + questions[currentQuestionIndex] + "</html>");

        if (images[currentQuestionIndex] != null) {
            imageLabel.setIcon(resizeImage(images[currentQuestionIndex], 400, 250));
        } else {
            imageLabel.setIcon(null);
        }

        optionARadio.setText("<html>" + options[currentQuestionIndex][0] + "</html>");
        optionBRadio.setText("<html>" + options[currentQuestionIndex][1] + "</html>");
        optionCRadio.setText("<html>" + options[currentQuestionIndex][2] + "</html>");
        optionDRadio.setText("<html>" + options[currentQuestionIndex][3] + "</html>");

        optionGroup.clearSelection();
    }

    private void handleOptionSelection(String selectedOption) {
        if (selectedOption.equals("A")) {
            optionARadio.setSelected(true);
        } else if (selectedOption.equals("B")) {
            optionBRadio.setSelected(true);
        } else if (selectedOption.equals("C")) {
            optionCRadio.setSelected(true);
        } else if (selectedOption.equals("D")) {
            optionDRadio.setSelected(true);
        }
    }

    private void checkAnswer() {
        if (optionARadio.isSelected() && 'A' == correctAnswers[currentQuestionIndex]) {
            score++;
            showCelebration();
        } else if (optionBRadio.isSelected() && 'B' == correctAnswers[currentQuestionIndex]) {
            score++;
            showCelebration();
        } else if (optionCRadio.isSelected() && 'C' == correctAnswers[currentQuestionIndex]) {
            score++;
            showCelebration();
        } else if (optionDRadio.isSelected() && 'D' == correctAnswers[currentQuestionIndex]) {
            score++;
            showCelebration();
        }
    }

    private void showCelebration() {
        ImageIcon icon = new ImageIcon(celebrationImage.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        JOptionPane.showMessageDialog(this, "Correct!\nGreat Job!", "Celebration", JOptionPane.INFORMATION_MESSAGE, icon);
    }

    private void showResult() {
        showCongratulations();
        JOptionPane.showMessageDialog(this, "Quiz completed!\nYour score: " + score + "/" + questions.length,
                "Quiz Result", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private void showCongratulations() {
        ImageIcon congratsIcon = new ImageIcon("/icons/giphy (1).gif");
        JOptionPane.showMessageDialog(this, "Congratulations!\nYou completed the quiz!", "Congratulations",
                JOptionPane.INFORMATION_MESSAGE, congratsIcon);
    }

    private ImageIcon resizeImage(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
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
                new QuizApp("").setVisible(true);
            }
        });
    }
}
