import dao.RoomDAO;
import model.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class HostelUI extends JFrame {

    private JTextField guestNameField;
    private JTextField roomIdField;
    private RoomDAO dao;

    public HostelUI() {
        dao = new RoomDAO();

        setTitle("Smart Hostel Management System");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Input panel
        JLabel guestLabel = new JLabel("Guest Name:");
        guestNameField = new JTextField(20);
        JButton checkInBtn = new JButton("Check-In");

        JLabel roomLabel = new JLabel("Room ID to Check-Out:");
        roomIdField = new JTextField(5);
        JButton checkOutBtn = new JButton("Check-Out");

        JButton viewBtn = new JButton("View All Rooms");

        add(guestLabel);
        add(guestNameField);
        add(checkInBtn);
        add(roomLabel);
        add(roomIdField);
        add(checkOutBtn);
        add(viewBtn);

        // Button Listeners
        checkInBtn.addActionListener(this::handleCheckIn);
        checkOutBtn.addActionListener(this::handleCheckOut);
        viewBtn.addActionListener(this::handleViewRooms);
    }

    private void handleCheckIn(ActionEvent e) {
        String name = guestNameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter guest name.");
            return;
        }

        Room r = new Room(0, name, "Occupied");
        dao.checkIn(r);
        guestNameField.setText("");
    }

    private void handleCheckOut(ActionEvent e) {
        try {
            int roomId = Integer.parseInt(roomIdField.getText().trim());
            dao.checkOut(roomId);
            roomIdField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid Room ID.");
        }
    }

    private void handleViewRooms(ActionEvent e) {
        List<Room> rooms = dao.getAllRooms();

        String[] columns = {"Room ID", "Guest Name", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        for (Room r : rooms) {
            Object[] row = {
                    r.getRoomId(),
                    r.getGuestName() != null ? r.getGuestName() : "-",
                    r.getStatus()
            };
            tableModel.addRow(row);
        }

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JFrame tableFrame = new JFrame("Room Status");
        tableFrame.add(scrollPane);
        tableFrame.setSize(400, 300);
        tableFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HostelUI().setVisible(true));
    }
}