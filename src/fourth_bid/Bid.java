package fourth_bid;

import java.util.ArrayList;

public class Bid {

    private int id;
    private String bidDate;
    private String bidTime;
    private String price;
    private int customerID;
    private int auctionID;

    private Customer customer;
    private Auction auction;

    public Bid(int id, String bidDate, String bidTime, String price, int customerID, int auctionID) {

        this.id = id;
        this.bidDate = bidDate;
        this.bidTime = bidTime;
        this.price = price;
        customerID = customerID;
        this.auctionID = auctionID;
    }

    public void setRelation(ArrayList<Auction> list1, ArrayList<Customer> list2) {
        for (Auction i : list1) {
            if (i.getId() == this.auctionID) {
                this.auction = i;
            }
        }

        for (Customer i : list2) {
            if (i.getId() == this.customerID) {
                this.customer = i;
            }
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        customerID = customerID;
    }

    public int getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }
}
