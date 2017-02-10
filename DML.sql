-- Ni skall sedan skapa DML script för att kunna hantera dessa frågor:
-- Registrera en produkt


DELIMITER //
CREATE PROCEDURE RegistreProduct(NewName VARCHAR(50), NewDescription VARCHAR(200), NewProvision INT)
BEGIN

INSERT INTO Product (Name, Description, Provision) VALUES (NewName, NewDescription , NewProvision);

END //

DELIMITER ;


-- Skapa en auktion utifrån en viss produkt där man kan sätta utgångspris, acceptpris samt start och slutdatum för auktionen.


DELIMITER //
CREATE PROCEDURE RegistreProduct(NewStartingBid INT, NewAcceptOffer INT, NewStartDate DATE, NewEndDate DATE, NewProductID INT)
BEGIN

INSERT INTO Auction (StartingBid, AcceptOffer, StartDate, EndDate, ProductID) VALUES (NewStartingBid, NewAcceptOffer, NewStartDate, NewEndDate, NewProductID);
END //

DELIMITER ;


-- Lista pågående auktioner samt kunna se det högsta budet och vilken kund som lagt det.

CREATE VIEW HigherBid
AS
  SELECT  Auction.ID, Customer.FirstName, Customer.LastName, Product.Name, MAX(Bid.Price)
  FROM Customer
    LEFT JOIN Bid ON Customer.ID = Bid.CustomerID
    LEFT JOIN Auction ON Auction.ID = Bid.AuctionID
    LEFT JOIN Product ON Product.ID = Auction.ProductID
  GROUP BY Product.Name;

SELECT * FROM HigherBid;

