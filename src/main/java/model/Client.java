package model;

public class Client {
    public int id;
    public String name;
    public String address;
    public String email;
    public int age;

    public Client() {}

    public Client(int id, String name, String address, String email, int age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.age = age;
    }

    public Client(String name, String address, String email, int age) {

        this.name = name;
        this.address = address;
        this.email = email;
        this.age = age;
    }

    public int getId() { return id;}
    public String getName() {return name;}
    public String getAddress() {return address;}
    public int getAge() {return age;}
    public String getEmail() {return email;}

    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setAddress(String address) {this.address = address;}
    public void setAge(int age) {this.age = age;}
    public void setEmail(String email) {this.email = email;}

    @Override
    public String toString() {
        return "Client [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", age=" + age
                + "]";
    }










}