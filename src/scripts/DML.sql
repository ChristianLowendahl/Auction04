-- 4. Se budhistoriken på en viss auktion, samt vilka kunder som lagt buden

SELECT BidDate AS Date, BidTime AS Time, Price, Customer.FirstName AS 'First name', Customer.LastName AS 'Last name' FROM Bid
  INNER JOIN Customer ON Bid.CustomerID = Customer.ID
WHERE AuctionID = 2;

-- 5. Vilka auktioner avslutas under ett visst datumintervall? Samt vad blir provisionen för varje auktion inom det intervallet?

SELECT AuctionHistory.AuctionID AS Auction, Product.Name AS Product, AuctionHistory.EndDate AS 'End date', ROUND((MAX(Price) * Product.Provision/100)) AS Commission FROM BiddingHistory
  INNER JOIN AuctionHistory ON BiddingHistory.AuctionHistoryID = AuctionHistory.AuctionID
  INNER JOIN Product ON AuctionHistory.ProductID = Product.ID
WHERE AuctionHistory.EndDate BETWEEN '2017-01-01' AND '2017-01-31'
GROUP BY Auction;


