package fourth_bid;

/**
 * Created by Katia on 2017-02-13.
 */
public class AuctionHistory {

    private int auctionID;
    private String startingBid;
    private int acceptOffer;
    private String startDate;
    private String endDate;
    private int productID;
    private int customerID;

    public AuctionHistory(int auctionID, String startingBid, int acceptOffer, String startDate, String endDate, int productID, int customerID) {
        this.auctionID = auctionID;
        this.startingBid = startingBid;
        this.acceptOffer = acceptOffer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.productID = productID;
        this.customerID = customerID;
    }

    public int getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }

    public String getStartingBid() {
        return startingBid;
    }

    public void setStartingBid(String startingBid) {
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

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
