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



