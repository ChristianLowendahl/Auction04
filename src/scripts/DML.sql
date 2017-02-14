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
CREATE PROCEDURE RegistreAuction(NewStartingBid INT, NewAcceptOffer INT, NewStartDate DATE, NewEndDate DATE, NewProductID INT)
BEGIN

INSERT INTO Auction (StartingBid, AcceptOffer, StartDate, EndDate, ProductID) VALUES (NewStartingBid, NewAcceptOffer, NewStartDate, NewEndDate, NewProductID);
END //

DELIMITER ;

-- Extra Procedure

USE Auction;

DELIMITER //
CREATE PROCEDURE AddSupplier(NewName VARCHAR(50), NewEmail VARCHAR(50), NewAdress VARCHAR(50), NewCity VARCHAR(50))
  BEGIN

    INSERT INTO Supplier (Name, Email, Address, City) VALUES (NewName, NewEmail , NewAdress, NewCity);

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

DROP EVENT IF EXISTS Event_Finished_Auction;

SET sql_mode = '';

CREATE EVENT Event_Finished_Auction
  ON SCHEDULE EVERY 1 DAY
  STARTS '2017-01-01 00:00:00'
DO
  CALL Finish_Auctions();


DELIMITER //

DROP PROCEDURE IF EXISTS Archive_Auctions //
CREATE PROCEDURE Archive_Auctions()

  BEGIN

    INSERT INTO AuctionHistory (AuctionID, StartingBid, AcceptOffer, FinalOffer, StartDate, EndDate, ProductID, CustomerID)
      SELECT Auction.ID, StartingBid, AcceptOffer, MAX(Bid.Price) AS FinalOffer, Startdate, Enddate, ProductID, Bid.CustomerID FROM Auction
        INNER JOIN Bid ON Auction.ID = Bid.AuctionID
      WHERE Auction.EndDate < CURRENT_DATE AND Bid.Price IS NOT NULL
      GROUP BY ProductID, Bid.CustomerID;

    /*
    INSERT INTO FailedAuctionHistory (AuctionID, StartingBid, AcceptOffer, StartDate, EndDate, ProductID)
    SELECT * FROM Auction
    WHERE Auction.EndDate < CURRENT_DATE AND Bid.Price IS NULL;

    DELETE FROM Auction WHERE Auction.ID IN (SELECT AuctionID FROM auction_history);

    */

  END//

DELIMITER ;

CALL Archive_Auctions();



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

