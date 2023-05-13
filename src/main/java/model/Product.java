package model;

public class Product {

    public int id;
    public String name;
    public int price;
    public int quantity;
    public Product()
    {

    }
    public Product(int productID, String productName, int productQuantity, int productPrice)
    {
        this.id = productID;
        this.name = productName;
        this.price = productPrice;
        this.quantity = productQuantity;
    }
    public Product( String productName, int productQuantity, int productPrice)
    {

        this.name = productName;
        this.price = productPrice;
        this.quantity = productQuantity;
    }


    public int getId() {return id;}
    public String getName() {return name;}
    public int getPrice() {return price;}
    public int getQuantity() {return quantity;}

    public void setId(int productID) {this.id = productID;}
    public void setName(String productName) {this.name = productName;}
    public void setPrice(int productPrice) {this.price = productPrice;}
    public void setQuantity(int productQuantity) {this.quantity = productQuantity;}

}