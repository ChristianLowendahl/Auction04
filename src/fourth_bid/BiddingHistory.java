package fourth_bid;

/**
 * Created by Katia on 2017-02-13.
 */
public class BiddingHistory {

    private int bidID;
    private int price;
    private String bidDate;
    private String bidTime;
    private int auctionHistoryID;
    private int customerID;

    public BiddingHistory(int bidID, int price, String bidDate, String bidTime, int auctionHistoryID, int customerID) {
        this.bidID = bidID;
        this.price = price;
        this.bidDate = bidDate;
        this.bidTime = bidTime;
        this.auctionHistoryID = auctionHistoryID;
        this.customerID = customerID;
    }

    public int getBidID() {
        return bidID;
    }

    public void setBidID(int bidID) {
        this.bidID = bidID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBidDate() {
        return bidDate;
    }

    public void setBidDate(String bidDate) {
        this.bidDate = bidDate;
    }

    public String getBidTime() {
        return bidTime;
    }

    public void setBidTime(String bidTime) {
        this.bidTime = bidTime;
    }

    public int getAuctionHistoryID() {
        return auctionHistoryID;
    }

    public void setAuctionHistoryID(int auctionHistoryID) {
        this.auctionHistoryID = auctionHistoryID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
