package model;

public class Product {

    private int productID;
    private String productName;
    private int productPrice;
    private int productQuantity;

    public Product(int productID, String productName, int productQuantity, int productPrice)
    {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

    public int getID() {return productID;}
    public String getName() {return productName;}
    public int getPrice() {return productPrice;}
    public int getQuantity() {return productQuantity;}

    public void setID(int productID) {this.productID = productID;}
    public void setName(String productName) {this.productName = productName;}
    public void setPrice(int productPrice) {this.productPrice = productPrice;}
    public void setQuantity(int productQuantity) {this.productQuantity = productQuantity;}

}