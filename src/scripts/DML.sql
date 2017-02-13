-- Ni skall sedan skapa DML script för att kunna hantera dessa frågor:
-- 1 Registrera en produkt


DELIMITER //
CREATE PROCEDURE RegistreProduct(NewName VARCHAR(50), NewDescription VARCHAR(200), NewProvision INT)
BEGIN

INSERT INTO Product (Name, Description, Provision) VALUES (NewName, NewDescription , NewProvision);

END //

DELIMITER ;


-- 2 Skapa en auktion utifrån en viss produkt där man kan sätta utgångspris, acceptpris samt start och slutdatum för auktionen.


DELIMITER //
CREATE PROCEDURE RegistreProduct(NewStartingBid INT, NewAcceptOffer INT, NewStartDate DATE, NewEndDate DATE, NewProductID INT)
BEGIN

INSERT INTO Auction (StartingBid, AcceptOffer, StartDate, EndDate, ProductID) VALUES (NewStartingBid, NewAcceptOffer, NewStartDate, NewEndDate, NewProductID);
END //

DELIMITER ;


-- 3 Lista pågående auktioner samt kunna se det högsta budet och vilken kund som lagt det.

DROP VIEW IF EXISTS HigherBid;

CREATE VIEW HigherBid
AS
  SELECT  Auction.ID, Customer.FirstName, Customer.LastName, Product.Name AS Product, MAX(Bid.Price) AS HigherBid
  FROM Auction
    INNER JOIN Bid ON Bid.AuctionID = Auction.ID
    INNER JOIN Customer ON Customer.ID = Bid.CustomerID
    INNER JOIN Product ON Product.ID = Auction.ProductID
  GROUP BY Auction.ID;

SELECT * FROM HigherBid;

-- 4.4	Visa budhistorik
-- 4.4.1	Se budhistoriken på en viss pågående auktion, samt vilka kunder som lagt buden.


SELECT BidDate AS Date, BidTime AS Time, Price, Customer.FirstName AS 'First name', Customer.LastName AS 'Last name' FROM Bid
  INNER JOIN Customer ON Bid.CustomerID = Customer.ID
WHERE AuctionID = 2;


-- 4.5	Visa avslutade auktioner
-- 4.5.1	Visa vilka auktioner som avslutas under ett visst datumintervall samt provisionen för varje auktion inom det intervallet.

SELECT AuctionHistory.AuctionID AS Auction, Product.Name AS Product, AuctionHistory.EndDate AS 'End date', ROUND((MAX(Price) * Product.Provision/100)) AS Commission FROM BiddingHistory
  INNER JOIN AuctionHistory ON BiddingHistory.AuctionHistoryID = AuctionHistory.AuctionID
  INNER JOIN Product ON AuctionHistory.ProductID = Product.ID
WHERE AuctionHistory.EndDate BETWEEN '2017-01-01' AND '2017-01-31'
GROUP BY Auction;

-- 4.6	Skicka epost när auktion avslutas utan bud
--  4.6.1	Skicka epost till Gunnar om en auktion avslutas utan något bud.
SELECT * FROM AuctionHistory;



-- Visa en kundlista på alla kunder som köpt något, samt vad deras totala ordervärde är.

CREATE VIEW CustomersValue
  AS
  SELECT Customer.FirstName, Customer.LastName, SUM(BiddingHistory.FinalOffer) AS TotalorderValue FROM Customer
  INNER JOIN BiddingHistory ON BiddingHistory.CustomerID = Customer.ID WHERE FinalOffer IS NOT NULL
  GROUP BY FirstName, LastName;

SELECT * FROM CustomersValue;

-- Vad den totala provisionen är per månad.

DROP VIEW IF EXISTS TotalCommissionPerMonth;
CREATE VIEW TotalCommissionPerMonth
  AS
  SELECT MONTH(Auction.EndDate) AS Month, SUM(Product.Provision) AS TotalProvision FROM Auction
  INNER JOIN Product ON Auction.ProductID = Product.ID
  GROUP BY MONTH(Auction.EndDate);

SELECT * FROM TotalCommissionPerMonth;

