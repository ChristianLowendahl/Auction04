DROP DATABASE IF EXISTS Auction04;
CREATE DATABASE Auction04;
Use Auction04;

CREATE TABLE Customer(
  ID INT AUTO_INCREMENT PRIMARY KEY,
  FirstName VARCHAR(50),
  LastName VARCHAR(50),
  Phone CHAR(15),
  Email VARCHAR(50),
  Address VARCHAR(50),
  City VARCHAR(50)
);

CREATE TABLE Supplier(
  ID INT AUTO_INCREMENT PRIMARY KEY,
  Name VARCHAR(50),
  Email VARCHAR(50),
  Address VARCHAR(50),
  City VARCHAR(50)
);

CREATE TABLE Product(
  ID INT AUTO_INCREMENT PRIMARY KEY,
  Name VARCHAR(50),
  Description VARCHAR(200),
  Provision INT
);

CREATE TABLE Auction(
  ID Int AUTO_INCREMENT PRIMARY KEY,
  StartingBid INT,
  AcceptOffer INT,
  StartDate DATE,
  EndDate DATE,
  ProductID INT,
  FOREIGN KEY (ProductID) REFERENCES Product(ID)
);

CREATE TABLE Bid(
  ID INT AUTO_INCREMENT PRIMARY KEY,
  BidDate DATE,
  BidTime TIME,
  Price INT,
  CustomerID INT,
  AuctionID INT,
  FOREIGN KEY (CustomerID) REFERENCES Customer (ID),
  FOREIGN KEY (AuctionID) REFERENCES Auction (ID)
);

CREATE TABLE AuctionHistory(
  AuctionID INT PRIMARY KEY,
  StartingBid INT,
  AcceptOffer INT,
  StartDate DATE,
  EndDate DATE,
  ProductID INT,
  CustomerID INT,
  FOREIGN KEY (ProductID) REFERENCES Product (ID),
  FOREIGN KEY (CustomerID) REFERENCES Customer (ID)
);

CREATE TABLE BiddingHistory(
  BidID INT PRIMARY KEY,
  Price INT,
  BidDate DATE,
  BidTime TIME,
  AuctionHistoryID INT,
  CustomerID INT,
  FOREIGN KEY (AuctionHistoryID) REFERENCES AuctionHistory (AuctionID),
  FOREIGN KEY (CustomerID) REFERENCES Customer (ID)
);

INSERT INTO Customer (FirstName, LastName, Phone, Email, Address, City) VALUES ('Anna', 'Andersson', '0701111111', 'annas@mail.se', 'gatan 7', 'Stockholm');
INSERT INTO Customer (FirstName, LastName, Phone, Email, Address, City) VALUES ('Jessica', 'Svensson', '0732345678', 'Jessica@mail.se', 'Näckrosgatan 11', 'Stockholm');
INSERT INTO Customer (FirstName, LastName, Phone, Email, Address, City) VALUES ('Rickard', 'Asplund', '0708564345', 'rickard@mail.se', 'Storgatan 10', 'Göteborg');
INSERT INTO Customer (FirstName, LastName, Phone, Email, Address, City) VALUES ('Veronika', 'Beckman', '0703458907', 'veronika@mail.se', 'Stormgatan 56', 'Malmö');
INSERT INTO Customer (FirstName, LastName, Phone, Email, Address, City) VALUES ('Emilio', 'Vargas', '0736093467', 'emilio@mail.se', 'Brinellvägen 70', 'Uppsala');
INSERT INTO Customer (FirstName, LastName, Phone, Email, Address, City) VALUES ('Erik', 'Berglund', '0709345276', 'erik@mail.se', 'Floragatan 12', 'Lund');
INSERT INTO Customer (FirstName, LastName, Phone, Email, Address, City) VALUES ('Hans', 'Fors', '0703452309', 'hans@mail.se', 'Flintbacken 20', 'Kalmar');
INSERT INTO Customer (FirstName, LastName, Phone, Email, Address, City) VALUES ('Frank', 'Holmer', '0736540934', 'frank@mail.se', 'Fridhemsgatan 1', 'Karlskrona');
INSERT INTO Customer (FirstName, LastName, Phone, Email, Address, City) VALUES ('Hanna', 'Jensen', '0731239856', 'hanna@mail.se', 'Fyrverkarbacken 31', 'Uppsala');
INSERT INTO Customer (FirstName, LastName, Phone, Email, Address, City) VALUES ('Bim', 'Levin', '0709874523', 'bim@mail.se', 'Nobelgatan 6', 'Kirurna');

INSERT INTO Supplier (Name, Email, Address, City) VALUES ('Prime Quality', 'primequality@mail.com', 'Storgatan 56', 'Stockholm');
INSERT INTO Supplier (Name, Email, Address, City) VALUES ('Fine Funiture', 'finefuniture@mail.com', 'Västra svängen 128 ', 'Piteå');
INSERT INTO Supplier (Name, Email, Address, City) VALUES ('Goods', 'goods@mail.com', 'Ängensbacken 9', 'Ängleholm');
INSERT INTO Supplier (Name, Email, Address, City) VALUES ('History Dealers','hd@mail.com','Järnvägsviken 5B','Spånga');
INSERT INTO Supplier (Name, Email, Address, City) VALUES ('Antique Shoppers','antiqueshoppers@mail.com','Bromsens klippa 4','Västerås');
INSERT INTO Supplier (Name, Email, Address, City) VALUES ('Perfect Finds','perfectfinds@mail.com','Klingens väg 67','Göteborg');

INSERT INTO Product (Name, Description, Provision) VALUES ('Antika örhängen', 'Antika örhängen i silver med dekor av 18k guld', 10); -- 500, 1000, 1050
INSERT INTO Product (Name, Description, Provision) VALUES ('Vitrinskåp', 'Rokokostil vitrnskåp mahogny från 1900-tal', 30); -- 1500, 2500, 3000
INSERT INTO Product (Name, Description, Provision) VALUES ('Porslin bordsur', 'Antik bordsur från Tyskland från 1900-talet', 8); -- 400, 800, 800
INSERT INTO Product (Name, Description, Provision) VALUES ('Antik klocka', 'Antika klocka från 1900-talet', 7); -- 400, 800, 700
INSERT INTO Product (Name, Description, Provision) VALUES ('Oljemålning', 'Figurkomposition, olja på duk, signerad', 9); -- 800, 1700, 950
INSERT INTO Product (Name, Description, Provision) VALUES ('Pälsjacka', 'Mink från 1960/70-tal.', 10);
INSERT INTO Product (Name, Description, Provision) VALUES ('Portfölj', 'Läder portfölj från 1900-talet', 10);
INSERT INTO Product (Name, Description, Provision) VALUES ('Porslin vaser', 'Ett par porslin och silver vaser från England', 10);
INSERT INTO Product (Name, Description, Provision) VALUES ('Ljusplåtar', 'ett par ljusplåtar av mässing från 1900-tal.', 10);
INSERT INTO Product (Name, Description, Provision) VALUES ('Kaffeservis', '37 delar kaffeservis, Rörstrand från 1900-talets mitt', 10);
INSERT INTO Product (Name, Description, Provision) VALUES ('Kalle Anka serietidningar', '2 st, nr. 1 januari 1951 och 1952', 10);
INSERT INTO Product (Name, Description, Provision) VALUES ('Ånglok', 'MÄRKLIN ånglok SK800 med tender', 10);
INSERT INTO Product (Name, Description, Provision) VALUES ('Blunddocka', 'porslinshuvud, armar och ben från 1900-talets första kvartal', 10);
INSERT INTO Product (Name, Description, Provision) VALUES ('Jordglob', 'Jordglob av glas från 1900-talets andra hälft', 10);
INSERT INTO Product (Name, Description, Provision) VALUES ('Tavla', 'Skogsmotiv, olja på duk, signerad', 10);

INSERT INTO Auction (StartingBid, AcceptOffer, StartDate, EndDate, ProductID) VALUES (700, 1050,'2017-01-01','2017-01-04',1);
INSERT INTO Auction (StartingBid, AcceptOffer, StartDate, EndDate, ProductID) VALUES (1500, 3000,'2017-01-05','2017-01-08',2);
INSERT INTO Auction (StartingBid, AcceptOffer, StartDate, EndDate, ProductID) VALUES (400, 800,'2017-01-06','2017-01-09',8);
INSERT INTO Auction (StartingBid, AcceptOffer, StartDate, EndDate, ProductID) VALUES (600, 700,'2017-01-08','2017-01-11',4);
INSERT INTO Auction (StartingBid, AcceptOffer, StartDate, EndDate, ProductID) VALUES (800, 8000,'2017-01-10','2017-01-10',5);


INSERT INTO Bid (BidDate, BidTime, Price, CustomerID, AuctionID) VALUES ('2017-01-01','09:00',750, 1, 1);
INSERT INTO Bid (BidDate, BidTime, Price, CustomerID, AuctionID) VALUES ('2017-01-02', '11:00', 1050, 2, 1);
INSERT INTO Bid (BidDate, BidTime, Price, CustomerID, AuctionID) VALUES ('2017-01-05', '10:30', 1800, 3, 2);
INSERT INTO Bid (BidDate, BidTime, Price, CustomerID, AuctionID) VALUES ('2017-01-08', '14:15', 3000, 4, 2);
INSERT INTO Bid (BidDate, BidTime, Price, CustomerID, AuctionID) VALUES ('2017-01-06', '03:40', 600, 5, 3);
INSERT INTO Bid (BidDate, BidTime, Price, CustomerID, AuctionID) VALUES ('2017-01-07', '20:05', 800, 6, 3);
INSERT INTO Bid (BidDate, BidTime, Price, CustomerID, AuctionID) VALUES ('2017-01-08', '14:40', 700, 7, 4);
INSERT INTO Bid (BidDate, BidTime, Price, CustomerID, AuctionID) VALUES ('2017-01-10', '16:50', 950, 8, 5);
INSERT INTO Bid (BidDate, BidTime, Price, CustomerID, AuctionID) VALUES ('2017-01-10', '16:51', 1000, 1, 5);
INSERT INTO Bid (BidDate, BidTime, Price, CustomerID, AuctionID) VALUES ('2017-01-10', '16:52', 2000, 2, 5);
INSERT INTO Bid (BidDate, BidTime, Price, CustomerID, AuctionID) VALUES ('2017-01-10', '16:53', 3000, 3, 5);
INSERT INTO Bid (BidDate, BidTime, Price, CustomerID, AuctionID) VALUES ('2017-01-10', '16:54', 3500, 8, 5);
INSERT INTO Bid (BidDate, BidTime, Price, CustomerID, AuctionID) VALUES ('2017-01-10', '16:55', 4000, 2, 5);
INSERT INTO Bid (BidDate, BidTime, Price, CustomerID, AuctionID) VALUES ('2017-01-10', '16:56', 6000, 8, 5);
INSERT INTO Bid (BidDate, BidTime, Price, CustomerID, AuctionID) VALUES ('2017-01-10', '16:58', 8000, 3, 5);



INSERT INTO AuctionHistory (AuctionID, StartingBid, AcceptOffer, StartDate, EndDate, ProductID, CustomerID) VALUES (1, 700, 1050,'2017-01-01','2017-01-04',1, 1);
INSERT INTO AuctionHistory (AuctionID, StartingBid, AcceptOffer, StartDate, EndDate, ProductID, CustomerID) VALUES (2, 1500, 3000,'2017-01-05','2017-01-08', 2, 3);
INSERT INTO AuctionHistory (AuctionID, StartingBid, AcceptOffer, StartDate, EndDate, ProductID, CustomerID) VALUES (3, 400, 800,'2017-01-06','2017-01-09', 8, 5);
INSERT INTO AuctionHistory (AuctionID, StartingBid, AcceptOffer, StartDate, EndDate, ProductID, CustomerID) VALUES (4, 600, 700,'2017-01-08','2017-01-11', 4, 7);
INSERT INTO AuctionHistory (AuctionID, StartingBid, AcceptOffer, StartDate, EndDate, ProductID, CustomerID) VALUES (5, 800, 8000,'2017-01-10','2017-01-10', 5, 8);


INSERT INTO BiddingHistory (BidID, Price, BidDate, BidTime, AuctionHistoryID, CustomerID) VALUES (1, 750,'2017-01-01','09:00', 1, 1);
INSERT INTO BiddingHistory (BidID, Price, BidDate, BidTime, AuctionHistoryID, CustomerID) VALUES (2, 1050, '2017-01-02','11:00', 1, 2);
INSERT INTO BiddingHistory (BidID, Price, BidDate, BidTime, AuctionHistoryID, CustomerID) VALUES (3, 1800, '2017-01-05', '10:30', 2, 3);
INSERT INTO BiddingHistory (BidID, Price, BidDate, BidTime, AuctionHistoryID, CustomerID) VALUES (4, 3000, '2017-01-08', '14:15', 2, 4);
INSERT INTO BiddingHistory (BidID, Price, BidDate, BidTime, AuctionHistoryID, CustomerID) VALUES (5, 600, '2017-01-06', '03:40', 3, 5);
INSERT INTO BiddingHistory (BidID, Price, BidDate, BidTime, AuctionHistoryID, CustomerID) VALUES (6, 800, '2017-01-07', '20:05', 3, 6);
INSERT INTO BiddingHistory (BidID, Price, BidDate, BidTime, AuctionHistoryID, CustomerID) VALUES (7, 700, '2017-01-08', '14:40', 4, 7);
INSERT INTO BiddingHistory (BidID, Price, BidDate, BidTime, AuctionHistoryID, CustomerID) VALUES (8, 950, '2017-01-10', '16:50', 5, 8);
INSERT INTO BiddingHistory (BidID, Price, BidDate, BidTime, AuctionHistoryID, CustomerID) VALUES (9, 1000, '2017-01-10', '16:51', 5, 1);
INSERT INTO BiddingHistory (BidID, Price, BidDate, BidTime, AuctionHistoryID, CustomerID) VALUES (10, 2000, '2017-01-10', '16:52', 5, 2);
INSERT INTO BiddingHistory (BidID, Price, BidDate, BidTime, AuctionHistoryID, CustomerID) VALUES (11, 3000, '2017-01-10', '16:53', 5, 3);
INSERT INTO BiddingHistory (BidID, Price, BidDate, BidTime, AuctionHistoryID, CustomerID) VALUES (12, 3500, '2017-01-10', '16:54', 5, 8);
INSERT INTO BiddingHistory (BidID, Price, BidDate, BidTime, AuctionHistoryID, CustomerID) VALUES (13, 4000, '2017-01-10', '16:55', 5, 2);
INSERT INTO BiddingHistory (BidID, Price, BidDate, BidTime, AuctionHistoryID, CustomerID) VALUES (14, 6000, '2017-01-10', '16:56', 5, 8);
INSERT INTO BiddingHistory (BidID, Price, BidDate, BidTime, AuctionHistoryID, CustomerID) VALUES (15, 8000, '2017-01-10', '16:58', 5, 3);