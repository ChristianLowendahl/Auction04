-- APP Lägga in en ny kund

DELIMITER //
CREATE PROCEDURE AddCustomer(NewFirstName VARCHAR(50), NewLastName VARCHAR(50), NewPhone CHAR(15), NewEmail VARCHAR(50), NewAddress VARCHAR(50), NewCity VARCHAR(50))
  BEGIN

    INSERT INTO Customer (FirstName, LastName, Phone, Email, Address, City) VALUES (NewFirstName, NewLastName, NewPhone, NewEmail, NewAddress, NewCity);

  END //

DELIMITER ;

-- APP Extra tillägg, Gunnar vill ha extra säkerhet.

DELIMITER //
CREATE PROCEDURE AddUser(NewUserName VARCHAR(50), NewEmail VARCHAR(50), NewPassword VARCHAR(4))
  BEGIN

    INSERT INTO User (UserName, Email, Password) VALUES (NewUserName, NewEmail , NewPassword);

  END //

DELIMITER ;

--  APP Lägga in en ny leverantör

DELIMITER //
CREATE PROCEDURE AddSupplier(NewName VARCHAR(50), NewEmail VARCHAR(50), NewAdress VARCHAR(50), NewCity VARCHAR(50))
  BEGIN

    INSERT INTO Supplier (Name, Email, Address, City) VALUES (NewName, NewEmail , NewAdress, NewCity);

  END //

DELIMITER ;

-- 1. Lägg till ny produkt

DELIMITER //
CREATE PROCEDURE AddProduct(NewName VARCHAR(50), NewDescription VARCHAR(200), NewProvision INT)
  BEGIN

    INSERT INTO Product (Name, Description, Provision) VALUES (NewName, NewDescription , NewProvision);

  END //

DELIMITER ;


-- 2. Skapa en auktion utifrån en viss produkt där man kan sätta utgångspris, acceptpris samt start och slutdatum för auktionen.

DELIMITER //
CREATE PROCEDURE AddAuction(NewStartingBid INT, NewAcceptOffer INT, NewStartDate DATE, NewEndDate DATE, NewProductID INT)
  BEGIN

    INSERT INTO Auction (StartingBid, AcceptOffer, StartDate, EndDate, ProductID) VALUES (NewStartingBid, NewAcceptOffer, NewStartDate, NewEndDate, NewProductID);
  END //

DELIMITER ;


-- 3. Lista pågående auktioner samt kunna se det högsta budet och vilken kund som lagt det.

DROP VIEW IF EXISTS HigherBid;
CREATE VIEW HigherBid
AS
  SELECT tempBid.AuctionID, Customer.FirstName, Customer.LastName, Product.Name AS Product, tempBid.HigherBid
  FROM (SELECT Bid.AuctionID, MAX(Bid.Price) AS HigherBid
        FROM Bid
        GROUP BY Bid.AuctionID) AS tempBid
    INNER JOIN Bid ON Bid.AuctionID = tempBid.AuctionID
    INNER JOIN Customer ON Bid.CustomerID = Customer.ID
    INNER JOIN Auction ON Bid.AuctionID = Auction.ID
    INNER JOIN Product ON Auction.ProductID = Product.ID
  WHERE Bid.Price = tempBid.HigherBid;

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

DROP VIEW IF EXISTS AllBid;
CREATE VIEW AllBid
AS
  SELECT  Auction.ID, Customer.FirstName, Customer.LastName, Product.Name AS Product, Bid.Price AS HigherBid
  FROM Auction
    INNER JOIN Bid ON Bid.AuctionID = Auction.ID
    INNER JOIN Customer ON Customer.ID = Bid.CustomerID
    INNER JOIN Product ON Product.ID = Auction.ProductID;



-- 6. Om en auktion avslutas utan något bud så skall denna föras in i en egen tabell
-- 7. När en auktion är avslutad och det finns en köpare så skall auktionen flyttas till en auktionshistoriktabell.

DROP PROCEDURE IF EXISTS Archive_Auctions;
DELIMITER //
CREATE PROCEDURE Archive_Auctions()
  BEGIN

    INSERT INTO AuctionHistory (AuctionID, StartingBid, AcceptOffer, FinalOffer, StartDate, EndDate, ProductID, CustomerID)
      SELECT Auction.ID, Auction.StartingBid, Auction.AcceptOffer, (SELECT MAX(Bid.Price) FROM Bid WHERE Auction.ID = Bid.AuctionID)
        AS Finaloffer, Auction.StartDate, Auction.EndDate, Auction.ProductID, Bid.CustomerID
      FROM Auction
        INNER JOIN Bid ON Auction.ID = Bid.AuctionID
      WHERE Bid.CustomerID IS NOT NULL
      GROUP BY Auction.ID;

    INSERT INTO FailedAuctionHistory (AuctionID, StartingBid, AcceptOffer, StartDate, EndDate, ProductID)
      SELECT Auction.ID, Auction.StartingBid, Auction.AcceptOffer, Auction.StartDate, Auction.EndDate, Auction.ProductID
      FROM Auction
        INNER JOIN Bid ON Auction.ID = Bid.AuctionID
        RIGHT JOIN Product ON Auction.ProductID = Product.ID
      WHERE Bid.Price = 0;

    DELETE FROM Auction WHERE Auction.ID IN (SELECT AuctionID FROM AuctionHistory);
    DELETE FROM Auction WHERE Auction.ID IN (SELECT AuctionID FROM FailedAuctionHistory);

  END//
DELIMITER ;

CALL Archive_Auctions();

SELECT * FROM AuctionHistory;

SELECT * FROM FailedAuctionHistory;

SELECT * FROm Auction;

-- 8. Visa en kundlista på alla kunder som köpt något, samt vad deras totala ordervärde är.

DROP VIEW IF EXISTS CustomersValue;
CREATE VIEW CustomersValue
AS
  SELECT FirstName, LastName, SUM(HigherBid) AS TotalOrderValue FROM HigherBid
  GROUP BY FirstName, LastName;


-- 9. Vad den totala provisionen är per månad?

DROP VIEW IF EXISTS TotalCommissionPerMonth;
CREATE VIEW TotalCommissionPerMonth
AS
  SELECT MONTHNAME(Auction.EndDate) AS Month, SUM(HigherBid.HigherBid)*Product.Provision AS TotalCommission FROM HigherBid
    INNER JOIN Auction ON  Auction.ID = HigherBid.AuctionID
    INNER JOIN Product ON Product.ID = Auction.ProductID
  WHERE YEAR(Auction.EndDate) = (SELECT MAX(YEAR(Auction.EndDate))  FROM Auction)
  GROUP BY MONTHNAME(Auction.EndDate)
  ORDER BY TotalCommission DESC;