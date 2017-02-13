package fourth_bid;

public class Supplier {

    private int id;
    private String name;
    private String email;
    private String address;
    private String city;

    public Supplier(int id, String name, String email, String address, String city) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.city = city;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String toString(){
        return "Supplier: " + this.id + ".";
    }
}