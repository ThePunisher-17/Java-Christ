import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

public class addAttendance extends JFrame {
    private JTextField tName;
    private JTextField tEmail;
    private JTextField tContactNumber;
    private JTextField tCountry;
    private JButton addButton;
    private JButton searchButton;
    private JButton editButton;
    private JButton deleteButton;
    private JTable attendanceTable;
    private JPanel MainPanel;
    private JButton getStatsButton;
    private JTable table2;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private static final String URL = "jdbc:mysql://localhost:3306/javaLab8";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    static int id=0;

    public boolean validateData(){
        String name=tName.getText();
        String email=tEmail.getText();
        String contactNumber=tContactNumber.getText();
        String country=tCountry.getText();

        if ((name.isEmpty() && email.isEmpty() && contactNumber.isEmpty() && country.isEmpty())){
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
            return false;
        }
//        if (!(name.length() >= 7 && name.length() <= 30 && email.length() <= 5 && email.length() <= 30 && contactNumber.length() == 10 && country.length() <= 25 && country.length() >=4)){
//            JOptionPane.showMessageDialog(null, "Please fill all the fields correctly please");
//            return false;
//        }
//        if (!(name.matches("[a-zA-Z\\s]+") && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}$\") && country.matches("[a-zA-Z\\s]+"))){
//            JOptionPane.showMessageDialog(null, "Please fill all the fields correctly");
//            return false;
//        }
        return true;
    }

    private static void populateTable(JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // Query to fetch all data from the table
            String query = "SELECT * FROM Attendance";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();

            // Get column names
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            // Get row data
            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    public addAttendance() {
        populateTable(attendanceTable);
        table2.setVisible(false);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!validateData()) {
                    return;
                }
                String name = tName.getText();
                String email = tEmail.getText();
                String contactNumber = tContactNumber.getText();
                String country = tCountry.getText();

                try {
                    PreparedStatement preparedStatement = (PreparedStatement) DriverManager.getConnection(URL, USER, PASSWORD).prepareStatement("INSERT INTO Attendance (FullName, Email, ContactNumber, Country) VALUES (?,?,?,?)");
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, email);
                    preparedStatement.setString(3, contactNumber);
                    preparedStatement.setString(4, country);

                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Records are added");
                    populateTable(attendanceTable);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        attendanceTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultTableModel model = (DefaultTableModel) attendanceTable.getModel();
                int row = attendanceTable.getSelectedRow();
                id = Integer.parseInt(model.getValueAt(row, 0).toString());
                String name = model.getValueAt(row, 1).toString();
                String email = model.getValueAt(row, 2).toString();
                String contactNumber = model.getValueAt(row, 3).toString();
                String country = model.getValueAt(row, 4).toString();

                tName.setText(name);
                tEmail.setText(email);
                tContactNumber.setText(contactNumber);
                tCountry.setText(country);
                addButton.setEnabled(false);
            }

        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!validateData()){
                    return;
                }
                String name = tName.getText();
                String email = tEmail.getText();
                String contactNumber = tContactNumber.getText();
                String country = tCountry.getText();

                try {
                    PreparedStatement preparedStatement = (PreparedStatement) DriverManager.getConnection(URL, USER, PASSWORD).prepareStatement("UPDATE Attendance set FullName=?, Email=?, ContactNumber=?, Country=? where ID = ?");
                    preparedStatement.setInt(5, id);
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, email);
                    preparedStatement.setString(3, contactNumber);
                    preparedStatement.setString(4, country);

                    preparedStatement.executeUpdate();
                    populateTable(attendanceTable);
                    addButton.setEnabled(true);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }


        });
        attendanceTable.addComponentListener(new ComponentAdapter() {
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!validateData()){
                    return;
                }
                String name = tName.getText();
                String email = tEmail.getText();
                String contactNumber = tContactNumber.getText();
                String country = tCountry.getText();

                try {
                    PreparedStatement preparedStatement = (PreparedStatement) DriverManager.getConnection(URL, USER, PASSWORD).prepareStatement("Delete from Attendance where ID = ?");
                    preparedStatement.setInt(1, id);

                    preparedStatement.executeUpdate();
                    populateTable(attendanceTable);
                    JOptionPane.showMessageDialog(null, "Records are deleted");
                    addButton.setEnabled(true);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (true) {
                    String input = JOptionPane.showInputDialog(null, "Enter an the id to search:", "Input Dialog", JOptionPane.PLAIN_MESSAGE);
                    if (input == null)
                    {
                        // User pressed Cancel
                        JOptionPane.showMessageDialog(null, "Operation canceled."); break; }
                    try
                    {
                        int number = Integer.parseInt(input);
                        PreparedStatement preparedStatement = (PreparedStatement) DriverManager.getConnection(URL, USER, PASSWORD).prepareStatement("SELECT * from Attendance where ID=?");
                        preparedStatement.setInt(1, number);
                        ResultSet resultSet = preparedStatement.executeQuery();
                        if (resultSet.next()){
                            String name = resultSet.getString("FullName");
                            String email = resultSet.getString("Email");
                            String contactNumber = resultSet.getString("ContactNumber");
                            String country = resultSet.getString("Country");

                            tName.setText(name);
                            tEmail.setText(email);
                            tContactNumber.setText(contactNumber);
                            tCountry.setText(country);
                            JOptionPane.showMessageDialog(null, "Attendance found.");
                        }

                        break;
                    } catch (NumberFormatException ee)
                    {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        getStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openNewFrame();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void openNewFrame() throws SQLException {

        if(table2.isVisible()==false){
            table2.setVisible(true);
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)){
            CallableStatement callableStatement = c.prepareCall("SELECT Country, COUNT(*) AS Count FROM Attendance GROUP BY Country;");
            DefaultTableModel model = (DefaultTableModel) table2.getModel();
            ResultSet resultSet = callableStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();

            // Get column names
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            // Get row data
            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                model.addRow(row);
            }
        }
        }
        else{
                table2.setVisible(false);
        }
    }

    public static void main(String[] args) {

        addAttendance frame = new addAttendance();
        frame.setContentPane(frame.MainPanel);
        frame.setTitle("Attendance");
        frame.setSize(800, 600);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}