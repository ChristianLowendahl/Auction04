package fourth_bid;

public class Product {

    private int id;
    private String name;
    private String description;
    private int provision;

    public Product(int id, String name, String description, int provision) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.provision = provision;
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

    public int getProvision() {
        return provision;
    }

    public void setProvision(int provision) {
        this.provision = provision;
    }
}
