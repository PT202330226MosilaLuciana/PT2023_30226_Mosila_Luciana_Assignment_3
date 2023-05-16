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
    private JTable ordersTable;
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
    public JTextArea nameTextAreaProd;

    public JTextArea priceTextArea;
    public JTextArea quantityTextArea;

    public JTextArea idTextArea;
    public JTextArea nameTextArea;
    public JTextArea addressTextArea;
    public JTextArea emailTextArea;
    public JTextArea ageTextArea;
    public JTextArea quantityO;
    public JTextArea idComanda;
    public JComboBox<String> productComboBox;
    public JComboBox<String> clientComboBox;

    public GUI() {
        // Create the main frame
        super("Product Order System");
        setSize(800, 600);
        clientDAO = new ClientDAO();
        productDAO = new ProductDAO();
        orderDAO = new OrderDAO();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the tabs
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create the clients tab
        JPanel clientsPanel = new JPanel(new BorderLayout());
        tabbedPane.addTab("Clients", clientsPanel);

        // Create the clients table
        String[] clientsColumns = {"ID", "Name", "Address", "Email", "Age"};
        Object[][] clientsData = {};
        DefaultTableModel clientsModel = new DefaultTableModel(clientsData, clientsColumns);
        clientsTable = new JTable(clientsModel);
        JScrollPane clientsScrollPane = new JScrollPane(clientsTable);
        clientsPanel.add(clientsScrollPane, BorderLayout.CENTER);

        // Create the clients text areas
        JPanel clientsTextAreaPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(7,2);
        clientsTextAreaPanel.setLayout(gridLayout);
        JLabel idLabel = new JLabel("ID:");
        idTextArea = new JTextArea();
        JLabel nameLabel = new JLabel("Name:");
        nameTextArea = new JTextArea();
        nameTextArea.setBackground(Color.gray);
        nameTextArea.setForeground(Color.white);
        JLabel addressLabel = new JLabel("Address:");
        addressTextArea = new JTextArea();
        JLabel emailLabel = new JLabel("Email:");
        emailTextArea = new JTextArea();
        emailTextArea.setBackground(Color.gray);
        emailTextArea.setForeground(Color.white);
        JLabel ageLable = new JLabel("Age");
        ageTextArea = new JTextArea();

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
        String[] productsColumns = {"ID", "Name", "Price", "Stock"};
        Object[][] productsData = {};
        DefaultTableModel productsModel = new DefaultTableModel(productsData, productsColumns);
        productsTable = new JTable(productsModel);
        JScrollPane productsScrollPane = new JScrollPane(productsTable);
        productsPanel.add(productsScrollPane, BorderLayout.CENTER);

        // Create the products text areas
        JPanel productsTextAreaPanel = new JPanel();
        GridLayout boxLayoutProd = new GridLayout(10,5);
        productsTextAreaPanel.setLayout(boxLayoutProd);
        JLabel idLabelProd = new JLabel("ID:");
        idTextAreaProd = new JTextArea();
        JLabel nameLabelProd = new JLabel("Name:");
        nameTextAreaProd = new JTextArea();
        JLabel priceLabel = new JLabel("Price:");
        priceTextArea = new JTextArea();
        JLabel quantityLabel = new JLabel("Quantity:");
        JLabel quantityLabel2 = new JLabel("Quantity:");
        quantityTextArea = new JTextArea();

        productsTextAreaPanel.add(idLabelProd);
        productsTextAreaPanel.add(idTextAreaProd);
        productsTextAreaPanel.add(nameLabelProd);
        productsTextAreaPanel.add(nameTextAreaProd);
        productsTextAreaPanel.add(priceLabel);
        productsTextAreaPanel.add(priceTextArea);
        productsTextAreaPanel.add(quantityLabel);
        productsTextAreaPanel.add(quantityLabel2);
        productsTextAreaPanel.add(quantityTextArea);


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
        // Create the orders tab
        JPanel ordersPanel = new JPanel(new BorderLayout());
        tabbedPane.addTab("Orders", ordersPanel);

// Create the orders table
        String[] ordersColumns = {"ID", "Product", "Client", "Quantity"};
        Object[][] ordersData = {};
        DefaultTableModel ordersModel = new DefaultTableModel(ordersData, ordersColumns);
        ordersTable = new JTable(ordersModel);
        JScrollPane ordersScrollPane = new JScrollPane(ordersTable);
        ordersPanel.add(ordersScrollPane, BorderLayout.CENTER);

// Create the orders form panel
        JPanel ordersFormPanel = new JPanel(new GridLayout(3, 2));
        JLabel productLabel = new JLabel("Product:");
        ordersFormPanel.add(productLabel);

// Create and populate the product combo box
        productComboBox = new JComboBox<>();
        List<Product> products = productDAO.findAll();
        for (Product product : products) {
            productComboBox.addItem(product.getName());
        }
        ordersFormPanel.add(productComboBox);

        JLabel clientLabel = new JLabel("Client:");
        ordersFormPanel.add(clientLabel);

// Create and populate the client combo box
        clientComboBox = new JComboBox<>();
        List<Client> clients = clientDAO.findAll();
        for (Client client : clients) {
            clientComboBox.addItem(client.getName());
        }
        ordersFormPanel.add(clientComboBox);

        JLabel quantityLb = new JLabel("Quantity:");
        ordersFormPanel.add(quantityLabel);
        quantityO = new JTextArea();
        ordersFormPanel.add(quantityO);






// Create the orders button panel
        JPanel ordersButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton createOrderButton = new JButton("Create Order");
        JButton deleteOrderButton = new JButton("Delete Order");
        createOrderButton.addActionListener(this);
        deleteOrderButton.addActionListener(this);
        ordersButtonPanel.add(createOrderButton);
        ordersButtonPanel.add(deleteOrderButton);
        JLabel idCom = new JLabel("ID (DELETE):");

        ordersButtonPanel.add(idCom);
        idComanda = new JTextArea();
        idComanda.setPreferredSize(new Dimension(40,20));
        ordersButtonPanel.add(idComanda);

        ordersPanel.add(ordersButtonPanel, BorderLayout.SOUTH);


// Clear the existing data in the table and populate the table with the new data
        clientsModel.setRowCount(0);
        for (Client client : clients) {
            clientsModel.addRow(new Object[]{client.getId(), client.getName(), client.getAddress(), client.getEmail(), client.getAge()});
        }

        productsModel.setRowCount(0);
        for (Product product : products) {
            productsModel.addRow(new Object[]{product.getId(), product.getName(), product.getPrice(), product.getQuantity()});
        }
        List<Comanda> comenzi = orderDAO.findAll();
        ordersModel.setRowCount(0);
        for (Comanda c : comenzi) {
            ordersModel.addRow(new Object[]{c.getId(),c.getClient(),
                    c.getProduct(),
                    c.getCantitate()});
        }

// Add the orders form panel to the orders panel
        ordersPanel.add(ordersFormPanel, BorderLayout.NORTH);


        add(tabbedPane);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Handle button clicks
        if (e.getActionCommand().equals("Add Client")) {
            if (!idTextArea.getText().isEmpty()) {
                idClient = Integer.parseInt(idTextArea.getText());
            }
            if (!nameTextArea.getText().isEmpty()) {
                nameClient = nameTextArea.getText();
            }
            if (!addressTextArea.getText().isEmpty()) {
                try {
                    addressClient = addressTextArea.getText();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid address");
                    return;
                }
            }
            if (!ageTextArea.getText().isEmpty()) {
                try {
                    ageClient = Integer.parseInt(ageTextArea.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid age value");
                    return; // Exit the method to prevent further execution
                }
            }

            if (!emailTextArea.getText().isEmpty()) {
                try {
                    emailClient = emailTextArea.getText();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid email");
                    return;
                }
            }
            List<Product> products = productDAO.findAll();


            if (idClient == -1) {
                // Create a new product object
                Client client = new Client(nameClient, addressClient, emailClient, ageClient);
                clientDAO.insert(client);
            } else {
                Client client = new Client(idClient, nameClient, addressClient, emailClient, ageClient);
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
        } else if (e.getActionCommand().equals("Update Client")) {

            if (!idTextArea.getText().isEmpty()) {
                idClient = Integer.parseInt(idTextArea.getText());
                System.out.println(idClient);
            }

            nameClient = nameTextArea.getText();
            addressClient = addressTextArea.getText();

            if (!ageTextArea.getText().isEmpty()) {
                ageClient = Integer.parseInt(ageTextArea.getText());
            }
            emailClient = emailTextArea.getText();

            // Create a new client object
            Client client = new Client(idClient, nameClient, addressClient, emailClient, ageClient);
            clientDAO.update(client);

            List<Client> updateClients = clientDAO.findAll();
            DefaultTableModel clientsModel = (DefaultTableModel) clientsTable.getModel();
            clientsModel.setRowCount(0);

            for (Client c : updateClients) {
                clientsModel.addRow(new Object[]{c.getId(), c.getName(), c.getAddress(), c.getEmail(), c.getAge()});
            }
        } else if (e.getActionCommand().equals("Delete Client")) {
            // Handle delete client button click
            if (!idTextArea.getText().isEmpty()) {
                idClient = Integer.parseInt(idTextArea.getText());
            }

            List<Product> products = productDAO.findAll();


            if (idClient != -1) {

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
            if (!nameTextAreaProd.getText().isEmpty()) {
                nameProd = nameTextAreaProd.getText();
            }
            if (!priceTextArea.getText().isEmpty()) {
                try {
                    priceProd = Integer.parseInt(priceTextArea.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid price value");
                    return; // Exit the method to prevent further execution
                }
            }
            if (!idTextAreaProd.getText().isEmpty()) {
                try {
                    idProd = Integer.parseInt(idTextAreaProd.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid price value");
                    return; // Exit the method to prevent further execution
                }
            }

            // Validate and parse the quantity
            if (!quantityTextArea.getText().isEmpty()) {
                try {
                    quantityProd = Integer.parseInt(quantityTextArea.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid quantity value");
                    return;
                }
            }
            List<Product> products = productDAO.findAll();

            if (idProd == -1) {
                // Create a new product object
                Product product = new Product(nameProd, quantityProd, priceProd);
                productDAO.insert(product);
            } else {
                Product product = new Product(idProd, nameProd, quantityProd, priceProd);
                productDAO.insert(product);
            }

            // Update the products table
            List<Product> updatedProducts = productDAO.findAll();
            DefaultTableModel productsModel = (DefaultTableModel) productsTable.getModel();
            productsModel.setRowCount(0);
            for (Product p : updatedProducts) {
                productsModel.addRow(new Object[]{p.getId(), p.getName(), p.getPrice(), p.getQuantity()});
            }
        } else if (e.getActionCommand().equals("Edit Product")) {
            if (!nameTextAreaProd.getText().isEmpty()) {
                nameProd = nameTextAreaProd.getText();
            }
            if (!priceTextArea.getText().isEmpty()) {
                try {
                    priceProd = Integer.parseInt(priceTextArea.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid price value");
                    return; // Exit the method to prevent further execution
                }
            }


            // Validate and parse the quantity
            if (!quantityTextArea.getText().isEmpty()) {
                try {
                    quantityProd = Integer.parseInt(quantityTextArea.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid quantity value");
                    return;
                }
            }
            if (!idTextAreaProd.getText().isEmpty()) {
                try {
                    idProd = Integer.parseInt(idTextAreaProd.getText());
                    Product product = new Product(idProd, nameProd, quantityProd, priceProd);
                    productDAO.update(product);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid price value");
                    return; // Exit the method to prevent further execution
                }
            }
            List<Product> products = productDAO.findAll();

            // Update the products table
            List<Product> updatedProducts = productDAO.findAll();
            DefaultTableModel productsModel = (DefaultTableModel) productsTable.getModel();
            productsModel.setRowCount(0);
            for (Product p : updatedProducts) {
                productsModel.addRow(new Object[]{p.getId(), p.getName(), p.getPrice(), p.getQuantity()});
            }
            // Handle edit product button click
        } else if (e.getActionCommand().equals("Delete Product")) {

            if (!idTextAreaProd.getText().isEmpty()) {
                try {
                    idProd = Integer.parseInt(idTextAreaProd.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid price value");
                    return; // Exit the method to prevent further execution
                }
            }

            List<Product> products = productDAO.findAll();

            if (idProd != -1) {
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

            DefaultTableModel ordersModel = (DefaultTableModel) ordersTable.getModel();
            // Handle create order button click
            List<Product> productList = productDAO.findAll();
            // Retrieve the selected client, product, and quantity
            String selectedClientName = clientComboBox.getSelectedItem().toString();
            String selectedProductName = productComboBox.getSelectedItem().toString();
            int quantity = Integer.parseInt(quantityO.getText());

            // Find the selected client object using the selected name
            Client selectedClient = null;
            List<Client> c1 = clientDAO.findAll();
            for (Client client : c1) {
                if (client.getName().equals(selectedClientName)) {
                    selectedClient = client;
                    break;
                }
            }
            // Find the selected product object using the selected product ID
            Product selectedProduct = null;
            for (Product product : productList) {
                if (selectedProductName.equals(product.getName())) {
                    selectedProduct = product;
                    break;
                }
            }

            selectedProduct.setQuantity(selectedProduct.getQuantity() - quantity);
            productDAO.update(selectedProduct);
            Comanda order = new Comanda(selectedClient.getName(), selectedProduct.getName(), quantity);
            orderDAO.insert(order);
            // Handle the created order as needed (e.g., add it to the table model)
            List<Comanda> orderList = orderDAO.findAll();
            ordersModel.setRowCount(0);
            for (Comanda order1 : orderList) {
                if (order1 != null) {
                    Object[] rowData = {
                            order1.getId(),
                            order1.getClient(),
                            order1.getProduct(),
                            order1.getCantitate(),
                    };
                    ordersModel.addRow(rowData);
                }
            }
            List<Product> updatedProducts = productDAO.findAll();
            DefaultTableModel productsModel = (DefaultTableModel) productsTable.getModel();
            productsModel.setRowCount(0);
            for (Product p : updatedProducts) {
                productsModel.addRow(new Object[]{p.getId(), p.getName(), p.getPrice(), p.getQuantity()});
            }

            // Clear the input fields
            clientComboBox.setSelectedIndex(0);
            productComboBox.setSelectedIndex(0);
            quantityO.setText("");
        }
        else if (e.getActionCommand().equals("Delete Order")) {
            DefaultTableModel ordersModel = (DefaultTableModel) ordersTable.getModel();
            // Handle create order button click
            List<Product> productList = productDAO.findAll();
            // Retrieve the selected client, product, and quantity
            int idDel = Integer.parseInt(idComanda.getText());

            // Find the selected client object using the selected name
            Comanda selectedOrder = null;
            List<Comanda> o1 = orderDAO.findAll();
            for (Comanda o : o1) {
                if (idDel==o.getId()) {
                    selectedOrder = o;
                    break;
                }
            }

            // Find the selected product object using the selected product ID
            Product selectedProduct = null;
            for (Product product : productList) {
                if (selectedOrder.getProduct().equals(product.getName())) {
                    selectedProduct = product ;

                    break;
                }
            }
            Product produs = new Product(selectedProduct.getId(),selectedProduct.getName(),selectedProduct.getQuantity()+selectedOrder.getCantitate(), selectedProduct.getPrice());
            productDAO.update(produs);

            orderDAO.delete(idDel);
            // Handle the created order as needed (e.g., add it to the table model)
            List<Comanda> orderList = orderDAO.findAll();
            ordersModel.setRowCount(0);
            for (Comanda order1 : orderList) {
                if (order1 != null) {
                    Object[] rowData = {
                            order1.getId(),
                            order1.getClient(),
                            order1.getProduct(),
                            order1.getCantitate(),
                    };
                    ordersModel.addRow(rowData);
                }
            }
            List<Product> updatedProducts = productDAO.findAll();
            DefaultTableModel productsModel = (DefaultTableModel) productsTable.getModel();
            productsModel.setRowCount(0);
            for (Product p : updatedProducts) {
                productsModel.addRow(new Object[]{p.getId(), p.getName(), p.getPrice(), p.getQuantity()});
            }

            // Clear the input fields

            idComanda.setText("");
        }
    }



    public static void main(String[] args) {
        new GUI();
    }
}