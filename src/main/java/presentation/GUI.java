package presentation;
import dataAccess.ClientDAO;
import dataAccess.OrderDAO;
import dataAccess.ProductDAO;
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
    private ClientDAO clientDAO;
    private ProductDAO productDAO;
    private OrderDAO orderDAO;

    private int idClient = -1;
    private String nameClient;
    private String addressClient;
    private String emailClient;
    private int ageClient;

    private String nameProd;
    private int priceProd;
    private int quantityProd;
    private int idProd = -1;
    public JTextArea idTextAreaProd;
    public JTextArea nameTextAreaProd ;

    public JTextArea priceTextArea ;
    public JTextArea quantityTextArea;

    public JTextArea idTextArea ;
    public JTextArea nameTextArea;
    public JTextArea addressTextArea ;
    public JTextArea emailTextArea;
    public JTextArea ageTextArea;

    public GUI() {
        // Create the main frame
        super("Product Order System");
        setSize(800, 600);
        clientDAO = new ClientDAO();
        productDAO = new ProductDAO();
        orderDAO =new OrderDAO();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the tabs
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create the clients tab
        JPanel clientsPanel = new JPanel(new BorderLayout());
        tabbedPane.addTab("Clients", clientsPanel);

        // Create the clients table
        String[] clientsColumns = {"ID", "Name", "Address", "Email","Age"};
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
        idTextArea = new JTextArea();
        JLabel nameLabel = new JLabel("Name:");
        nameTextArea = new JTextArea();
        JLabel addressLabel = new JLabel("Address:");
        addressTextArea = new JTextArea();
        JLabel emailLabel = new JLabel("Email:");
        emailTextArea = new JTextArea();
        JLabel ageLable= new JLabel("Age");
        ageTextArea =new JTextArea();

        clientsTextAreaPanel.add(idLabel);
        clientsTextAreaPanel.add(idTextArea);
        clientsTextAreaPanel.add(nameLabel);
        clientsTextAreaPanel.add(nameTextArea);
        clientsTextAreaPanel.add(addressLabel);
        clientsTextAreaPanel.add(addressTextArea);
        clientsTextAreaPanel.add(emailLabel);
        clientsTextAreaPanel.add(emailTextArea);
        clientsTextAreaPanel.add(ageLable);
        clientsTextAreaPanel.add(ageTextArea);
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
        String[] productsColumns = {"ID", "Name", "Stock", "Price"};
        Object[][] productsData = {};
        DefaultTableModel productsModel = new DefaultTableModel(productsData, productsColumns);
        productsTable = new JTable(productsModel);
        JScrollPane productsScrollPane = new JScrollPane(productsTable);
        productsPanel.add(productsScrollPane, BorderLayout.CENTER);

        // Create the products text areas
        JPanel productsTextAreaPanel = new JPanel();
        BoxLayout boxLayoutProd = new BoxLayout(productsTextAreaPanel, BoxLayout.Y_AXIS);
        productsTextAreaPanel.setLayout(boxLayoutProd);
        JLabel idLabelProd = new JLabel("ID:");
        idTextAreaProd = new JTextArea();
        JLabel nameLabelProd = new JLabel("Name:");
        nameTextAreaProd = new JTextArea();
        JLabel priceLabel = new JLabel("Price:");
        priceTextArea = new JTextArea();
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityTextArea = new JTextArea();

        productsTextAreaPanel.add(idLabelProd);
        productsTextAreaPanel.add(idTextAreaProd);
        productsTextAreaPanel.add(nameLabelProd);
        productsTextAreaPanel.add(nameTextAreaProd);
        productsTextAreaPanel.add(priceLabel);
        productsTextAreaPanel.add(priceTextArea);
        productsTextAreaPanel.add(quantityLabel);
        productsTextAreaPanel.add(quantityTextArea);
        productsTextAreaPanel.add(new JLabel());
        productsTextAreaPanel.add(new JLabel());

        productsPanel.add(productsTextAreaPanel, BorderLayout.NORTH);

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




/////            // Create the orders tab
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
///////////
        //DefaultTableModel clientsModel = (DefaultTableModel) clientsTable.getModel();


        List<Product> products = productDAO.findAll();
        List<Client> clients = clientDAO.findAll();

        // Clear the existing data in the table and Populate the table with the new data
        clientsModel.setRowCount(0);
        for (Client client : clients) {
            clientsModel.addRow(new Object[]{client.getId(), client.getName(), client.getAddress(), client.getEmail(), client.getAge()});
        }
        productsModel.setRowCount(0);
        for(Product product: products){
            productsModel.addRow(new Object[]{product.getId(), product.getName(), product.getPrice(), product.getQuantity()});
        }



        add(tabbedPane);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Handle button clicks
        if (e.getActionCommand().equals("Add Client")) {
            if(!idTextArea.getText().isEmpty()) {
                idClient = Integer.parseInt(idTextArea.getText());
            }
            if(!nameTextArea.getText().isEmpty()) {
                nameClient = nameTextArea.getText();
            }
            if(!addressTextArea.getText().isEmpty()){
                try {
                    addressClient = addressTextArea.getText();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid address");
                    return;
                }
            }
            if(!ageTextArea.getText().isEmpty()){
                try {
                    ageClient = Integer.parseInt(ageTextArea.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid age value");
                    return; // Exit the method to prevent further execution
                }
            }

            if(!emailTextArea.getText().isEmpty()) {
                try {
                    emailClient =emailTextArea.getText();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid email");
                    return;
                }
            }
            List<Product> products = productDAO.findAll();


            if(idClient==-1) {
                // Create a new product object
                Client client = new Client(nameClient, addressClient, emailClient, ageClient);
                clientDAO.insert(client);
            }
            else{
                Client client = new Client(idClient,nameClient, addressClient, emailClient, ageClient);
                clientDAO.insert(client);
            }

            List<Client> updateClients = clientDAO.findAll();
            DefaultTableModel clientsModel = (DefaultTableModel) clientsTable.getModel();
            // Clear the existing data in the table
            clientsModel.setRowCount(0);
            // Populate the table with the new data
            for (Client c : updateClients) {
                clientsModel.addRow(new Object[]{c.getId(), c.getName(), c.getAddress(), c.getEmail(), c.getAge()});
            }

            // Handle add client button click
        } else if (e.getActionCommand().equals("Edit Client")) {
            // Handle edit client button click
            if(!idTextArea.getText().isEmpty()) {
                idClient = Integer.parseInt(idTextArea.getText());
            }
            if(!nameTextArea.getText().isEmpty()) {
                nameClient = nameTextArea.getText();
            }
            if(!addressTextArea.getText().isEmpty()){
                try {
                    addressClient = addressTextArea.getText();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid address");
                    return;
                }
            }
            if(!ageTextArea.getText().isEmpty()){
                try {
                    ageClient = Integer.parseInt(ageTextArea.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid age value");
                    return; // Exit the method to prevent further execution
                }
            }

            if(!emailTextArea.getText().isEmpty()) {
                try {
                    emailClient =emailTextArea.getText();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid email");
                    return;
                }
            }
            List<Product> products = productDAO.findAll();


            if(idClient!=-1){
                Client client = new Client(idClient,nameClient, addressClient, emailClient, ageClient);
                clientDAO.update(client);
            }

            List<Client> updateClients = clientDAO.findAll();
            DefaultTableModel clientsModel = (DefaultTableModel) clientsTable.getModel();
            // Clear the existing data in the table
            clientsModel.setRowCount(0);
            // Populate the table with the new data
            for (Client c : updateClients) {
                clientsModel.addRow(new Object[]{c.getId(), c.getName(), c.getAddress(), c.getEmail(), c.getAge()});
            }

        } else if (e.getActionCommand().equals("Delete Client")) {
            // Handle delete client button click
            if(!idTextArea.getText().isEmpty()) {
                idClient = Integer.parseInt(idTextArea.getText());
            }

            List<Product> products = productDAO.findAll();


            if(idClient!=-1){

                clientDAO.delete(idClient);
            }

            List<Client> updateClients = clientDAO.findAll();
            DefaultTableModel clientsModel = (DefaultTableModel) clientsTable.getModel();
            // Clear the existing data in the table
            clientsModel.setRowCount(0);
            // Populate the table with the new data
            for (Client c : updateClients) {
                clientsModel.addRow(new Object[]{c.getId(), c.getName(), c.getAddress(), c.getEmail(), c.getAge()});
            }
        } else if (e.getActionCommand().equals("Add Product")) {
            if(!nameTextAreaProd.getText().isEmpty()) {
                nameProd = nameTextAreaProd.getText();
            }
            if(!priceTextArea.getText().isEmpty()){
                try {
                    priceProd = Integer.parseInt(priceTextArea.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid price value");
                    return; // Exit the method to prevent further execution
                }
            }
            if(!idTextAreaProd.getText().isEmpty()){
                try {
                    idProd = Integer.parseInt(idTextAreaProd.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid price value");
                    return; // Exit the method to prevent further execution
                }
            }

            // Validate and parse the quantity
            if(!quantityTextArea.getText().isEmpty()) {
                try {
                    quantityProd = Integer.parseInt(quantityTextArea.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid quantity value");
                    return;
                }
            }
            List<Product> products = productDAO.findAll();

            if(idProd==-1) {
                // Create a new product object
                Product product = new Product(nameProd, priceProd, quantityProd);
                productDAO.insert(product);
            }
            else{
                Product product = new Product(idProd,nameProd,priceProd,quantityProd);
                productDAO.insert(product);
            }

            // Update the products table
            List<Product> updatedProducts = productDAO.findAll();
            DefaultTableModel productsModel = (DefaultTableModel) productsTable.getModel();
            productsModel.setRowCount(0);
            for (Product p : updatedProducts) {
                productsModel.addRow(new Object[]{p.getId(), p.getName(), p.getPrice(), p.getQuantity()});
            }
        }

        else if (e.getActionCommand().equals("Edit Product")) {
            if(!nameTextAreaProd.getText().isEmpty()) {
                nameProd = nameTextAreaProd.getText();
            }
            if(!priceTextArea.getText().isEmpty()){
                try {
                    priceProd = Integer.parseInt(priceTextArea.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid price value");
                    return; // Exit the method to prevent further execution
                }
            }
            if(!idTextAreaProd.getText().isEmpty()){
                try {
                    idProd = Integer.parseInt(idTextAreaProd.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid price value");
                    return; // Exit the method to prevent further execution
                }
            }

            // Validate and parse the quantity
            if(!quantityTextArea.getText().isEmpty()) {
                try {
                    quantityProd = Integer.parseInt(quantityTextArea.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid quantity value");
                    return;
                }
            }
            List<Product> products = productDAO.findAll();

            if(idProd!=-1){
                Product product = new Product(idProd,nameProd,priceProd,quantityProd);
                productDAO.update(product);
            }

            // Update the products table
            List<Product> updatedProducts = productDAO.findAll();
            DefaultTableModel productsModel = (DefaultTableModel) productsTable.getModel();
            productsModel.setRowCount(0);
            for (Product p : updatedProducts) {
                productsModel.addRow(new Object[]{p.getId(), p.getName(), p.getPrice(), p.getQuantity()});
            }
            // Handle edit product button click
        } else if (e.getActionCommand().equals("Delete Product")) {

            if(!idTextAreaProd.getText().isEmpty()){
                try {
                    idProd = Integer.parseInt(idTextAreaProd.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid price value");
                    return; // Exit the method to prevent further execution
                }
            }

            List<Product> products = productDAO.findAll();

            if(idProd!=-1){
                productDAO.delete(idProd);
            }

            // Update the products table
            List<Product> updatedProducts = productDAO.findAll();
            DefaultTableModel productsModel = (DefaultTableModel) productsTable.getModel();
            productsModel.setRowCount(0);
            for (Product p : updatedProducts) {
                productsModel.addRow(new Object[]{p.getId(), p.getName(), p.getPrice(), p.getQuantity()});
            }
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
