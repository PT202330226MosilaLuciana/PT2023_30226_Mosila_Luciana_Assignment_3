package model;

public class Order {
    Client client;
    Product product;
    int quantity;

    public Order(Client c, Product p, int q) {
        this.client = c;
        this.product = p;
        this.quantity = q;
    }

    public Client getClient() {return client;}
    public int getQuantity() {return quantity;}
    public Product getProduct() {return product;}

    public void setClient(Client client) {this.client = client;}
    public void setProduct(Product product) {this.product = product;}
    public void setQuantity(int quantity) {this.quantity = quantity;}
}
