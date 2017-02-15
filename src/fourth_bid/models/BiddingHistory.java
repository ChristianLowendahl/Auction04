package fourth_bid.models;

import java.util.ArrayList;

public class BiddingHistory {

    private int bidID;
    private int price;
    private String bidDate;
    private String bidTime;
    private int auctionHistoryID;
    private int customerID;

    private Bid bid;
    private AuctionHistory auctionHistory;
    private Customer customer;

    public BiddingHistory(int bidID, int price, String bidDate, String bidTime, int auctionHistoryID, int customerID) {
        this.bidID = bidID;
        this.price = price;
        this.bidDate = bidDate;
        this.bidTime = bidTime;
        this.auctionHistoryID = auctionHistoryID;
        this.customerID = customerID;
    }

    public void setRelation(ArrayList<Bid> list1, ArrayList<AuctionHistory> list2, ArrayList<Customer> list3) {
        for (Bid i : list1) {
            if (i.getId() == this.bidID) {
                this.bid = i;
            }
        }

        for (AuctionHistory i : list2) {
            if (i.getAuctionID() == this.auctionHistoryID) {
                this.auctionHistory = i;
            }
        }

        for (Customer i : list3) {
            if (i.getId() == this.customerID) {
                this.customer = i;
            }
        }
    }

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public AuctionHistory getAuctionHistory() {
        return auctionHistory;
    }

    public void setAuctionHistory(AuctionHistory auctionHistory) {
        this.auctionHistory = auctionHistory;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public String toString(){
        return "BidHistory: " + this.bidID + ".";
    }
}
