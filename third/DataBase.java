package third;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class DataBase extends JFrame {

    JTextField txtName, txtAuthor, txtEditorial, txtPrice, txtID;
    JTextArea area;

    public DataBase() {
        setTitle("Application CRUD - Johan Gonzalez");
        setSize(750, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Este es el panel superior con datos
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Book Information"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        gbc.gridx = 0;
        gbc.gridy = y;
        inputPanel.add(new JLabel("ID (Update/Delete):"), gbc);
        gbc.gridx = 1;
        txtID = new JTextField(10);
        inputPanel.add(txtID, gbc);

        gbc.gridx = 2;
        inputPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 3;
        txtName = new JTextField(15);
        inputPanel.add(txtName, gbc);

        y++;
        gbc.gridx = 0;
        gbc.gridy = y;
        inputPanel.add(new JLabel("Author:"), gbc);
        gbc.gridx = 1;
        txtAuthor = new JTextField(15);
        inputPanel.add(txtAuthor, gbc);

        gbc.gridx = 2;
        inputPanel.add(new JLabel("Editorial:"), gbc);
        gbc.gridx = 3;
        txtEditorial = new JTextField(15);
        inputPanel.add(txtEditorial, gbc);

        y++;
        gbc.gridx = 0;
        gbc.gridy = y;
        inputPanel.add(new JLabel("Price:"), gbc);
        gbc.gridx = 1;
        txtPrice = new JTextField(10);
        inputPanel.add(txtPrice, gbc);

        add(inputPanel, BorderLayout.NORTH);

        // Aqui esta el panel con los botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnInsert = new JButton("Insert");
        JButton btnShow = new JButton("Show");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");

        buttonPanel.add(btnInsert);
        buttonPanel.add(btnShow);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);

        add(buttonPanel, BorderLayout.CENTER);

        // Este es el area de texto
        area = new JTextArea(15, 60);
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));
        area.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Database Output"));
        add(scrollPane, BorderLayout.SOUTH);

        // Las acciones de los botones
        btnInsert.addActionListener(e -> insertBook());
        btnShow.addActionListener(e -> showBooks());
        btnUpdate.addActionListener(e -> updateBook());
        btnDelete.addActionListener(e -> deleteBook());
    }

    void insertBook() {
        try (Connection con = ConnectionBD.conectar()) {
            String sql = "INSERT INTO libros (nombre_libro, editorial, autor, precio) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txtName.getText());
            ps.setString(2, txtEditorial.getText());
            ps.setString(3, txtAuthor.getText());
            ps.setInt(4, Integer.parseInt(txtPrice.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Inserted book");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error to insert: " + ex.getMessage());
        }
    }

    void showBooks() {
        area.setText("");
        try (Connection con = ConnectionBD.conectar()) {
            String sql = "SELECT * FROM libros";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                area.append("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("nombre_libro") +
                        ", Author: " + rs.getString("autor") +
                        ", Editorial: " + rs.getString("editorial") +
                        ", Price: $" + rs.getInt("precio") + "\n");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error to show: " + ex.getMessage());
        }
    }

    void updateBook() {
        try (Connection con = ConnectionBD.conectar()) {
            String sql = "UPDATE libros SET nombre_libro=?, editorial=?, autor=?, precio=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txtName.getText());
            ps.setString(2, txtEditorial.getText());
            ps.setString(3, txtAuthor.getText());
            ps.setInt(4, Integer.parseInt(txtPrice.getText()));
            ps.setInt(5, Integer.parseInt(txtID.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Updated book");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error to update: " + ex.getMessage());
        }
    }

    void deleteBook() {
        try (Connection con = ConnectionBD.conectar()) {
            String sql = "DELETE FROM libros WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtID.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Deleted book");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error to delete: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DataBase().setVisible(true));
    }
}
