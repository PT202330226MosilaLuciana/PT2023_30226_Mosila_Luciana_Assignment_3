package presentation;
import dataAccess.ClientDAO;
import model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    public class GUI {
//        private JTable clientsTable;
//        private JTable productsTable;
//
//        public GUI() {
//            // Create the main frame
//            super("Product Order System");
//            setSize(800, 600);
//            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//            // Create the tabs
//            JTabbedPane tabbedPane = new JTabbedPane();
//
//            // Create the clients tab
//            JPanel clientsPanel = new JPanel(new BorderLayout());
//            tabbedPane.addTab("Clients", clientsPanel);
//
//            // Create the clients table
//
//            //String[] clientsColumns = {"ID", "Name", "Address", "Phone"};
//            //Object[][] clientsData = {{"1", "John", "123 Main St", "555-1234"},
//            //        {"2", "Jane", "456 Oak Ave", "555-5678"}};
//
//            DefaultTableModel clientsModel = new DefaultTableModel(clientsData, clientsColumns);
//            clientsTable = new JTable(clientsModel);
//            JScrollPane clientsScrollPane = new JScrollPane(clientsTable);
//            clientsPanel.add(clientsScrollPane, BorderLayout.CENTER);
//
//            // Create the clients button panel
//            JPanel clientsButtonPanel = new JPanel(new GridLayout(1, 3));
//            JButton addClientButton = new JButton("Add Client");
//            JButton editClientButton = new JButton("Update Client");
//            JButton deleteClientButton = new JButton("Delete Client");
//
//            addClientButton.addActionListener(this);
//            editClientButton.addActionListener(this);
//            deleteClientButton.addActionListener(this);
//            clientsButtonPanel.add(addClientButton);
//            clientsButtonPanel.add(editClientButton);
//            clientsButtonPanel.add(deleteClientButton);
//            clientsPanel.add(clientsButtonPanel, BorderLayout.SOUTH);
//
//            // Create the products tab
//            JPanel productsPanel = new JPanel(new BorderLayout());
//            tabbedPane.addTab("Products", productsPanel);
//
//            // Create the products table
//            String[] productsColumns = {"ID", "Name", "Price", "Stock"};
//            Object[][] productsData = {{"1", "Product 1", "10.00", "50"},
//                    {"2", "Product 2", "20.00", "25"}};
//            DefaultTableModel productsModel = new DefaultTableModel(productsData, productsColumns);
//            productsTable = new JTable(productsModel);
//            JScrollPane productsScrollPane = new JScrollPane(productsTable);
//            productsPanel.add(productsScrollPane, BorderLayout.CENTER);
//
//            // Create the products button panel
//            JPanel productsButtonPanel = new JPanel(new GridLayout(1, 3));
//            JButton addProductButton = new JButton("Add Product");
//            JButton editProductButton = new JButton("Edit Product");
//            JButton deleteProductButton = new JButton("Delete Product");
//            addProductButton.addActionListener(this);
//            editProductButton.addActionListener(this);
//            deleteProductButton.addActionListener(this);
//            productsButtonPanel.add(addProductButton);
//            productsButtonPanel.add(editProductButton);
//            productsButtonPanel.add(deleteProductButton);
//            productsPanel.add(productsButtonPanel, BorderLayout.SOUTH);
//
//            // Create the orders tab
//            JPanel ordersPanel = new JPanel(new BorderLayout());
//            tabbedPane.addTab("Orders", ordersPanel);
//
//            // Create the orders panel
//            JPanel ordersFormPanel = new JPanel(new GridLayout(3, 2));
//            JLabel productLabel = new JLabel("Product:");
//            JComboBox<String> productComboBox = new JComboBox<String>(new String[]{"Product 1", "Product 2"});
//            JLabel clientLabel = new JLabel("Client:");
//           // JComboBox<String> clientComboBox = new JComboBox
//
//            // Create the orders button panel
//            JPanel ordersButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//            JButton createOrderButton = new JButton("Create Order");
//            createOrderButton.addActionListener(this);
//            ordersButtonPanel.add(createOrderButton);
//            ordersPanel.add(ordersButtonPanel, BorderLayout.SOUTH);
//
//        // Add the tabs to the main frame
//            add(tabbedPane);
//
//        // Show the main frame
//            setVisible(true);
//        }
//
//        public void actionPerformed(ActionEvent e) {
//            // Handle button clicks
//            if (e.getActionCommand().equals("Add Client")) {
//                // Handle add client button click
//            } else if (e.getActionCommand().equals("Edit Client")) {
//                // Handle edit client button click
//            } else if (e.getActionCommand().equals("Delete Client")) {
//                // Handle delete client button click
//            } else if (e.getActionCommand().equals("Add Product")) {
//                // Handle add product button click
//            } else if (e.getActionCommand().equals("Edit Product")) {
//                // Handle edit product button click
//            } else if (e.getActionCommand().equals("Delete Product")) {
//                // Handle delete product button click
//            } else if (e.getActionCommand().equals("Create Order")) {
//                // Handle create order button click
//                ComboBoxModel<Object> productComboBox=null;
//                String product = (String) productComboBox.getSelectedItem();
//                ComboBoxModel<Object> clientComboBox=null;
//                String client = (String) clientComboBox.getSelectedItem();
//                Label quantityTextField = null;
//                int quantity = Integer.parseInt(quantityTextField.getText());
//
//                // Check if there is enough stock for the selected product
//                int selectedProductRow = productsTable.getSelectedRow();
//                int stock = Integer.parseInt((String) productsTable.getValueAt(selectedProductRow, 3));
//                if (quantity > stock) {
//                    JOptionPane.showMessageDialog(this, "Not enough stock for selected product");
//                } else {
//                    // Decrement the product stock and update the table
//                    int newStock = stock - quantity;
//                    productsTable.setValueAt(String.valueOf(newStock), selectedProductRow, 3);
//
//                    // Handle creating the order
//                }
//            }
//        }


        public static void main(String[] args) {

            ClientDAO c = new ClientDAO();
            Client b = new Client("Luci","Sadu","luci@17.com",23);
            Client d  = new Client("Lucian","SaduG","lucian@17.com",27);
            c.insert(b);
            c.insert(d);
            Client a = c.findById(2);
            System.out.println(a);
        }

    }
