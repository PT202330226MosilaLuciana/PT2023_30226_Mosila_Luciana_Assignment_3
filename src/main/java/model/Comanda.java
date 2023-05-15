package model;

public class Comanda {
     public int id;
    public String client;
    public String product;
    public int cantitate;

    public Comanda(){}
    public Comanda(int id, String c, String p, int q) {
        this.id=id;
        this.client = c;
        this.product = p;
        this.cantitate = q;
    }

    public Comanda(String c, String p, int q) {
        this.client = c;
        this.product = p;
        this.cantitate = q;
    }

    public String getClient() {return client;}
    public int getCantitate() {return cantitate;}
    public String getProduct() {return product;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClient(String client) {this.client = client;}
    public void setProduct(String product) {this.product = product;}
    public void setCantitate(int cantitate) {this.cantitate = cantitate;}
}
