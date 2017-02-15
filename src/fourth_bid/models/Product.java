package fourth_bid.models;

import java.util.ArrayList;

public class Product {

    private int id;
    private String name;
    private String description;
    private double provision;
    private int supplierID;

    private Supplier supplier;

    public void setRelation(ArrayList<Supplier> list) {
        for (Supplier i : list) {
            if (i.getId() == this.supplierID) {
                this.supplier = i;
            }
        }
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public Product(int id, String name, String description, double provision, int supplierID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.provision = provision;
        this.supplierID = supplierID;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getProvision() {
        return provision;
    }

    public void setProvision(int provision) {
        this.provision = provision;
    }

    public String toString(){
        return "Product: " + this.id + ".";
    }
}
