
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookingGUI extends JFrame {
    private JTextField nameField;
    private JTextField ticketNumberField;
    private JComboBox<String> movieComboBox;
    private JComboBox<Integer> seatComboBox;
    private JComboBox<String> paymentMethodComboBox;
    private JTextArea displayArea;
    private List<Customer> bookingList = new ArrayList<>();
    private static final String FILE_NAME = "bookings.txt";
    private JTextArea queueDisplayArea;

    public BookingGUI() {
        initComponents();
    }

    private void initComponents() {
        setTitle("XX1 Movie Ticket Booking");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem resetItem = new JMenuItem("Reset");
        resetItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBookings();
            }
        });
        menu.add(resetItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Create tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Panel for booking form
        JPanel bookingPanel = new JPanel(new GridBagLayout());
        // Panel for displaying queue
        JPanel queuePanel = new JPanel(new BorderLayout());

        // Set background and foreground colors
        Color backgroundColor = Color.WHITE;
        Color foregroundColor = new Color(255, 215, 0); // Gold color

        bookingPanel.setBackground(backgroundColor);
        queuePanel.setBackground(backgroundColor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add logo
        ImageIcon logoIcon = new ImageIcon("path/to/logo.png"); // Update with the path to your logo
        JLabel logoLabel = new JLabel(logoIcon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        bookingPanel.add(logoLabel, gbc);

        JLabel titleLabel = new JLabel("XX1 Movie Ticket Booking", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(foregroundColor);
        gbc.gridy = 1;
        bookingPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        gbc.gridy = 2;
        gbc.gridx = 0;
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(foregroundColor);
        bookingPanel.add(nameLabel, gbc);
        nameField = new JTextField();
        nameField.setBackground(backgroundColor);
        nameField.setForeground(Color.BLACK);
        gbc.gridx = 1;
        bookingPanel.add(nameField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        JLabel ticketNumberLabel = new JLabel("Ticket Number:");
        ticketNumberLabel.setForeground(foregroundColor);
        bookingPanel.add(ticketNumberLabel, gbc);
        ticketNumberField = new JTextField();
        ticketNumberField.setBackground(backgroundColor);
        ticketNumberField.setForeground(Color.BLACK);
        gbc.gridx = 1;
        bookingPanel.add(ticketNumberField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        JLabel movieLabel = new JLabel("Movie:");
        movieLabel.setForeground(foregroundColor);
        bookingPanel.add(movieLabel, gbc);
        movieComboBox = new JComboBox<>(new String[]{"Movie A", "Movie B", "Movie C"});
        movieComboBox.setBackground(backgroundColor);
        movieComboBox.setForeground(Color.BLACK);
        gbc.gridx = 1;
        bookingPanel.add(movieComboBox, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        JLabel seatNumberLabel = new JLabel("Seat Number:");
        seatNumberLabel.setForeground(foregroundColor);
        bookingPanel.add(seatNumberLabel, gbc);
        seatComboBox = new JComboBox<>(getSeatNumbers());
        seatComboBox.setBackground(backgroundColor);
        seatComboBox.setForeground(Color.BLACK);
        gbc.gridx = 1;
        bookingPanel.add(seatComboBox, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        JLabel paymentMethodLabel = new JLabel("Payment Method:");
        paymentMethodLabel.setForeground(foregroundColor);
        bookingPanel.add(paymentMethodLabel, gbc);
        paymentMethodComboBox = new JComboBox<>(new String[]{"Credit Card", "Debit Card", "PayPal"});
        paymentMethodComboBox.setBackground(backgroundColor);
        paymentMethodComboBox.setForeground(Color.BLACK);
        gbc.gridx = 1;
        bookingPanel.add(paymentMethodComboBox, gbc);

        JButton saveButton = new JButton("Save Booking");
        saveButton.setBackground(foregroundColor);
        saveButton.setForeground(Color.BLACK);
        gbc.gridy = 7;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        bookingPanel.add(saveButton, gbc);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveBooking();
            }
        });

        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        displayArea.setBackground(backgroundColor);
        displayArea.setForeground(Color.BLACK);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        gbc.gridy = 8;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        bookingPanel.add(scrollPane, gbc);

        // Queue panel setup
        queueDisplayArea = new JTextArea(20, 40);
        queueDisplayArea.setEditable(false);
        queueDisplayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        queueDisplayArea.setBackground(backgroundColor);
        queueDisplayArea.setForeground(Color.BLACK);
        JScrollPane queueScrollPane = new JScrollPane(queueDisplayArea);
        queuePanel.add(queueScrollPane, BorderLayout.CENTER);

        // Add panels to tabbed pane
        tabbedPane.addTab("Booking", bookingPanel);
        tabbedPane.addTab("Queue", queuePanel);

        // Add tabbed pane to frame
        add(tabbedPane, BorderLayout.CENTER);

        loadBookingsFromFile(); // Load booking data when GUI starts

        // Center the window on the screen
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Integer[] getSeatNumbers() {
        Integer[] seatNumbers = new Integer[10];
        for (int i = 0; i < 10; i++) {
            seatNumbers[i] = i + 1;
        }
        return seatNumbers;
    }

    private void saveBooking() {
        String name = nameField.getText();
        String ticketNumberText = ticketNumberField.getText();
        String movie = (String) movieComboBox.getSelectedItem();
        int seatNumber = (int) seatComboBox.getSelectedItem();
        String paymentMethod = (String) paymentMethodComboBox.getSelectedItem();

        // Input validation
        if (name.isEmpty() || ticketNumberText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int ticketNumber;
        try {
            ticketNumber = Integer.parseInt(ticketNumberText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ticket number", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Show booking summary
        String summary = String.format("Name: %s\nTicket Number: %d\nMovie: %s\nSeat Number: %d\nPayment Method: %s\n\nDo you want to proceed with payment?",
                name, ticketNumber, movie, seatNumber, paymentMethod);

        int confirm = JOptionPane.showConfirmDialog(this, summary, "Confirm Booking", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Process payment
            if (processPayment(paymentMethod)) {
                Customer customer = new Customer(name, ticketNumber, movie, seatNumber, paymentMethod);
                bookingList.add(customer);
                writeToFile(customer.toString());
                displayArea.append(customer + "\n"); // Display the saved data
                queueDisplayArea.append(customer + "\n"); // Display the saved data in queue tab
            }
        }
    }

    private boolean processPayment(String paymentMethod) {
        // Mock payment processing
        JOptionPane.showMessageDialog(this, "Payment processed successfully using " + paymentMethod, "Payment Success", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    private void writeToFile(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(data);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Booking saved successfully", "Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving booking to file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadBookingsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 5) { // Ensure there are exactly 5 parts
                    String namePart = parts[0].split("=")[1];
                    int ticketNumber = Integer.parseInt(parts[1].split("=")[1]);
                    String movie = parts[2].split("=")[1];
                    int seatNumber = Integer.parseInt(parts[3].split("=")[1]);
                    String paymentMethod = parts[4].split("=")[1].replace('}', ' ').trim();
                    Customer customer = new Customer(namePart, ticketNumber, movie, seatNumber, paymentMethod);
                    bookingList.add(customer);
                    displayArea.append(customer + "\n");
                    queueDisplayArea.append(customer + "\n"); // Display the loaded data in queue tab
                } else {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading bookings from file", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            // Handle any parsing errors here
        }
    }

    private void resetBookings() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to reset all bookings?", "Confirm Reset", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Clear the display areas
            displayArea.setText("");
            queueDisplayArea.setText("");

            // Clear the booking list
            bookingList.clear();

            // Delete the file content
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
                // Write nothing to the file to clear its content
                JOptionPane.showMessageDialog(this, "All bookings have been reset", "Info", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error resetting bookings", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BookingGUI();
            }
        });
    }
}