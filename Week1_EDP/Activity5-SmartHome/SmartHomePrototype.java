import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class SmartHomePrototype extends JFrame {

    // System states
    private boolean lightsOn = false;
    private boolean securityActive = false;
    private boolean doorOpen = false;
    private int temperature = 22;

    // Interface components
    private JLabel lightsStatus;
    private JLabel securityStatus;
    private JLabel doorStatus;
    private JLabel temperatureStatus;
    private JTextArea eventLog;

    public SmartHomePrototype() {

        setTitle("Smart Home Controller");
        setSize(650, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        // Main heading
        JLabel heading = new JLabel(
                "SMART HOME CONTROLLER",
                SwingConstants.CENTER
        );

        heading.setFont(new Font("Arial", Font.BOLD, 22));
        add(heading, BorderLayout.NORTH);

        // Main control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(5, 2, 10, 10));
        controlPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        // Lights controls
        JButton lightsButton = new JButton("Toggle Lights");
        lightsStatus = new JLabel("Lights: OFF");

        lightsButton.addActionListener(e -> handleLightsChanged());

        controlPanel.add(lightsButton);
        controlPanel.add(lightsStatus);

        // Security controls
        JButton securityButton = new JButton("Toggle Security");
        securityStatus = new JLabel("Security: INACTIVE");

        securityButton.addActionListener(
                e -> handleSecurityModeChanged()
        );

        controlPanel.add(securityButton);
        controlPanel.add(securityStatus);

        // Door controls
        JButton openDoorButton = new JButton("Open Door");
        JButton closeDoorButton = new JButton("Close Door");

        doorStatus = new JLabel("Door: CLOSED");

        openDoorButton.addActionListener(e -> handleDoorOpened());
        closeDoorButton.addActionListener(e -> handleDoorClosed());

        controlPanel.add(openDoorButton);
        controlPanel.add(closeDoorButton);

        // Temperature controls
        JSlider temperatureSlider = new JSlider(16, 30, 22);

        temperatureSlider.setMajorTickSpacing(2);
        temperatureSlider.setPaintTicks(true);
        temperatureSlider.setPaintLabels(true);

        temperatureStatus = new JLabel("Temperature: 22°C");

        temperatureSlider.addChangeListener(
                e -> handleTemperatureChanged(
                        temperatureSlider.getValue()
                )
        );

        controlPanel.add(temperatureSlider);
        controlPanel.add(temperatureStatus);

        // Reset controls
        JButton resetButton = new JButton("Reset System");
        resetButton.addActionListener(e -> {
            temperatureSlider.setValue(22);
            handleSystemReset();
        });

        controlPanel.add(resetButton);
        controlPanel.add(doorStatus);

        add(controlPanel, BorderLayout.CENTER);

        // Event log
        eventLog = new JTextArea(8, 40);
        eventLog.setEditable(false);
        eventLog.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(eventLog);

        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.setBorder(
                BorderFactory.createTitledBorder("System Event Log")
        );

        logPanel.add(scrollPane, BorderLayout.CENTER);

        add(logPanel, BorderLayout.SOUTH);

        logEvent("Smart Home Controller started.");
        logEvent("All devices are in their default state.");

        setVisible(true);
    }

    // Event handler for lights
    private void handleLightsChanged() {

        lightsOn = !lightsOn;

        lightsStatus.setText(
                "Lights: " + (lightsOn ? "ON" : "OFF")
        );

        logEvent("Lights switched " + (lightsOn ? "ON" : "OFF"));
    }

    // Event handler for security mode
    private void handleSecurityModeChanged() {

        securityActive = !securityActive;

        securityStatus.setText(
                "Security: " + (securityActive ? "ACTIVE" : "INACTIVE")
        );

        logEvent(
                "Security mode " +
                (securityActive ? "activated." : "deactivated.")
        );
    }

    // Event handler for opening the door
    private void handleDoorOpened() {

        if (doorOpen) {
            logEvent("Door is already open.");
            return;
        }

        doorOpen = true;
        doorStatus.setText("Door: OPEN");

        logEvent("Door opened.");

        if (securityActive) {
            logEvent("SECURITY ALERT: Door opened while security is active!");
        }
    }

    // Event handler for closing the door
    private void handleDoorClosed() {

        if (!doorOpen) {
            logEvent("Door is already closed.");
            return;
        }

        doorOpen = false;
        doorStatus.setText("Door: CLOSED");

        logEvent("Door closed.");
    }

    // Event handler for temperature changes
    private void handleTemperatureChanged(int newTemperature) {

        temperature = newTemperature;

        temperatureStatus.setText(
                "Temperature: " + temperature + "°C"
        );

        logEvent("Temperature adjusted to " + temperature + "°C.");
    }

    // Event handler for resetting the system
    private void handleSystemReset() {

        lightsOn = false;
        securityActive = false;
        doorOpen = false;
        temperature = 22;

        lightsStatus.setText("Lights: OFF");
        securityStatus.setText("Security: INACTIVE");
        doorStatus.setText("Door: CLOSED");
        temperatureStatus.setText("Temperature: 22°C");

        logEvent("System successfully reset to default settings.");
    }

    // Records system events with timestamps
    private void logEvent(String message) {

        String time = LocalTime.now().format(
                DateTimeFormatter.ofPattern("HH:mm:ss")
        );

        eventLog.append("[" + time + "] " + message + "\n");
    }

    // Main method
    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> new SmartHomePrototype()
        );
    }
}
