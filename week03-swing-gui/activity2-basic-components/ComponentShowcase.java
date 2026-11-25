
import javax.swing.*;
import java.awt.*;

public class ComponentShowcase {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShow());
    }

    private static void createAndShow() {

        JFrame frame = new JFrame("Component Showcase");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 550);
        frame.setLocationRelativeTo(null);

        frame.setLayout(new GridLayout(8, 1, 10, 10));

        // 1. JLabel customization
        JLabel titleLabel = new JLabel(
            "Component Showcase", SwingConstants.CENTER
        );
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.BLUE);
        frame.add(titleLabel);

        // 2. JTextField customization
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row1.add(new JLabel("Username:"));

        JTextField usernameField = new JTextField(20);
        usernameField.setEditable(false);
        row1.add(usernameField);

        frame.add(row1);

        // 3. JPasswordField
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row2.add(new JLabel("Password:"));
        row2.add(new JPasswordField(20));
        frame.add(row2);

        // 4. JCheckBox
        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row3.add(new JCheckBox("Remember me"));
        row3.add(new JCheckBox("Subscribe to newsletter"));
        frame.add(row3);

        // 5. JRadioButtons
        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row4.add(new JLabel("Gender:"));

        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");
        JRadioButton other = new JRadioButton("Other");

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(other);

        row4.add(male);
        row4.add(female);
        row4.add(other);
        frame.add(row4);

        // 6. JComboBox customization
        JPanel row5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row5.add(new JLabel("Country:"));

        JComboBox<String> countryBox = new JComboBox<>(
            new String[]{"Kenya", "Tanzania", "Uganda"}
        );

        countryBox.addItem("Rwanda");
        countryBox.addItem("Ethiopia");

        row5.add(countryBox);
        frame.add(row5);

        // 7. JTextArea customization
        JPanel row6 = new JPanel(new BorderLayout());
        row6.add(new JLabel("Comments:"), BorderLayout.NORTH);

        JTextArea commentsArea = new JTextArea(3, 40);
        commentsArea.setLineWrap(true);
        commentsArea.setWrapStyleWord(true);
        commentsArea.setEditable(true);

        row6.add(new JScrollPane(commentsArea), BorderLayout.CENTER);
        frame.add(row6);

        // 8. JButton customization
        JPanel row7 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));

        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(Color.GREEN);
        submitButton.setOpaque(true);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.LIGHT_GRAY);
        cancelButton.setOpaque(true);

        row7.add(submitButton);
        row7.add(cancelButton);
        frame.add(row7);

        frame.setVisible(true);

        System.out.println("Components displayed on: "
            + Thread.currentThread().getName());
    }
}
