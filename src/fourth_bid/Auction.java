package fourth_bid;


public class Auction {
    private int id;
    private int startingBid;
    private int acceptOffer;
    private String startDate;
    private String endDate;
    private int productID;

    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Auction(int id, int startingBid, int acceptOffer, String startDate, String endDate, int productID) {
        this.id = id;
        this.startingBid = startingBid;
        this.acceptOffer = acceptOffer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.productID = productID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartingBid() {
        return startingBid;
    }

    public void setStartingBid(int startingBid) {
        this.startingBid = startingBid;
    }

    public int getAcceptOffer() {
        return acceptOffer;
    }

    public void setAcceptOffer(int acceptOffer) {
        this.acceptOffer = acceptOffer;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
}
