package presentation;
import dataAccess.ClientDAO;
import model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI extends JFrame implements ActionListener {
        private JTable clientsTable;
        private JTable productsTable;
    public ClientDAO clientDAO;
        public GUI() {
            // Create the main frame
            super("Product Order System");
            setSize(800, 600);
            clientDAO = new ClientDAO();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create the tabs
            JTabbedPane tabbedPane = new JTabbedPane();

            // Create the clients tab
            JPanel clientsPanel = new JPanel(new BorderLayout());
            tabbedPane.addTab("Clients", clientsPanel);

            // Create the clients table
            String[] clientsColumns = {"ID", "Name", "Address", "Email"};
            Object[][] clientsData = {};
            DefaultTableModel clientsModel = new DefaultTableModel(clientsData, clientsColumns);
            clientsTable = new JTable(clientsModel);
            JScrollPane clientsScrollPane = new JScrollPane(clientsTable);
            clientsPanel.add(clientsScrollPane, BorderLayout.CENTER);

            // Create the clients text areas
            JPanel clientsTextAreaPanel = new JPanel();
            BoxLayout boxLayout = new BoxLayout(clientsTextAreaPanel, BoxLayout.Y_AXIS);
            clientsTextAreaPanel.setLayout(boxLayout);
            JLabel idLabel = new JLabel("ID:");
            JTextArea idTextArea = new JTextArea();
            JLabel nameLabel = new JLabel("Name:");
            JTextArea nameTextArea = new JTextArea();
            JLabel addressLabel = new JLabel("Address:");
            JTextArea addressTextArea = new JTextArea();
            JLabel emailLabel = new JLabel("Email:");
            JTextArea emailTextArea = new JTextArea();

            clientsTextAreaPanel.add(idLabel);
            clientsTextAreaPanel.add(idTextArea);
            clientsTextAreaPanel.add(nameLabel);
            clientsTextAreaPanel.add(nameTextArea);
            clientsTextAreaPanel.add(addressLabel);
            clientsTextAreaPanel.add(addressTextArea);
            clientsTextAreaPanel.add(emailLabel);
            clientsTextAreaPanel.add(emailTextArea);
            clientsTextAreaPanel.add(new JLabel());
            clientsTextAreaPanel.add(new JLabel());

// Add an empty label to create a new line

            clientsPanel.add(clientsTextAreaPanel, BorderLayout.NORTH);

            // Create the clients button panel
            JPanel clientsButtonPanel = new JPanel(new GridLayout(1, 3));
            JButton addClientButton = new JButton("Add Client");
            JButton editClientButton = new JButton("Update Client");
            JButton deleteClientButton = new JButton("Delete Client");

            addClientButton.addActionListener(this);
            editClientButton.addActionListener(this);
            deleteClientButton.addActionListener(this);
            clientsButtonPanel.add(addClientButton);
            clientsButtonPanel.add(editClientButton);
            clientsButtonPanel.add(deleteClientButton);
            clientsPanel.add(clientsButtonPanel, BorderLayout.SOUTH);

            // Create the products tab
            JPanel productsPanel = new JPanel(new BorderLayout());
            tabbedPane.addTab("Products", productsPanel);

            // Create the products table
            String[] productsColumns = {"ID", "Name", "Price", "Stock"};
            Object[][] productsData = {};
            DefaultTableModel productsModel = new DefaultTableModel(productsData, productsColumns);
            productsTable = new JTable(productsModel);
            JScrollPane productsScrollPane = new JScrollPane(productsTable);
            productsPanel.add(productsScrollPane, BorderLayout.CENTER);

            // Create the products button panel
            JPanel productsButtonPanel = new JPanel(new GridLayout(1, 3));
            JButton addProductButton = new JButton("Add Product");
            JButton editProductButton = new JButton("Edit Product");
            JButton deleteProductButton = new JButton("Delete Product");
            addProductButton.addActionListener(this);
            editProductButton.addActionListener(this);
            deleteProductButton.addActionListener(this);
            productsButtonPanel.add(addProductButton);
            productsButtonPanel.add(editProductButton);
            productsButtonPanel.add(deleteProductButton);
            productsPanel.add(productsButtonPanel, BorderLayout.SOUTH);

            // Create the orders tab
            JPanel ordersPanel = new JPanel(new BorderLayout());
            tabbedPane.addTab("Orders", ordersPanel);

            // Create the orders panel
            JPanel ordersFormPanel = new JPanel(new GridLayout(3, 2));
            JLabel productLabel = new JLabel("Product:");
            JComboBox<String> productComboBox = new JComboBox<String>(new String[]{"Product 1", "Product 2"});
            JLabel clientLabel = new JLabel("Client:");
            // JComboBox<String> clientComboBox = new JComboBox

            // Create the orders button panel
            JPanel ordersButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton createOrderButton = new JButton("Create Order");
            createOrderButton.addActionListener(this);
            ordersButtonPanel.add(createOrderButton);
            ordersPanel.add(ordersButtonPanel, BorderLayout.SOUTH);
            List<Client> clients = clientDAO.findAll();
//            DefaultTableModel clientsModel = (DefaultTableModel) clientsTable.getModel();
            // Clear the existing data in the table
            clientsModel.setRowCount(0);
            // Populate the table with the new data
            for (Client client : clients) {
                clientsModel.addRow(new Object[]{client.getId(), client.getName(), client.getAddress(), client.getEmail()});
            }
            // Add the tabs to the main frame
            add(tabbedPane);

            // Show the main frame
            setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
            // Handle button clicks
            if (e.getActionCommand().equals("Add Client")) {

                List<Client> clients = clientDAO.findAll();
                DefaultTableModel clientsModel = (DefaultTableModel) clientsTable.getModel();
                // Clear the existing data in the table
                clientsModel.setRowCount(0);
                // Populate the table with the new data
                for (Client client : clients) {
                    clientsModel.addRow(new Object[]{client.getId(), client.getName(), client.getAddress(), client.getEmail()});
                }

                // Handle add client button click
            } else if (e.getActionCommand().equals("Edit Client")) {
                // Handle edit client button click
            } else if (e.getActionCommand().equals("Delete Client")) {
                // Handle delete client button click
            } else if (e.getActionCommand().equals("Add Product")) {
                // Handle add product button click
            } else if (e.getActionCommand().equals("Edit Product")) {
                // Handle edit product button click
            } else if (e.getActionCommand().equals("Delete Product")) {
                // Handle delete product button click
            } else if (e.getActionCommand().equals("Create Order")) {
                // Handle create order button click
                ComboBoxModel<Object> productComboBox = null;
                String product = (String) productComboBox.getSelectedItem();
                ComboBoxModel<Object> clientComboBox = null;
                String client = (String) clientComboBox.getSelectedItem();
                Label quantityTextField = null;
                int quantity = Integer.parseInt(quantityTextField.getText());

                // Check if there is enough stock for the selected product
                int selectedProductRow = productsTable.getSelectedRow();
                int stock = Integer.parseInt((String) productsTable.getValueAt(selectedProductRow, 3));
                if (quantity > stock) {
                    JOptionPane.showMessageDialog(this, "Not enough stock for selected product");
                } else {
                    // Decrement the product stock and update the table
                    int newStock = stock - quantity;
                    productsTable.setValueAt(String.valueOf(newStock), selectedProductRow, 3);

                    // Handle creating the order
                }
            }
        }


        public static void main(String[] args) {
        new GUI();
        }
    }
