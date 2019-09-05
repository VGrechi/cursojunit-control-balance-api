CREATE TABLE Balance (
    BalanceId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    OrderId INT NOT NULL,
    BalanceValue DECIMAL(15,2) NOT NULL DEFAULT 0
);

CREATE TABLE BalanceContract (
    BalanceContractId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    BalanceId INT NOT NULL,
    EventId INT NOT NULL,
    BalanceValue DECIMAL(15,2) NOT NULL
);

ALTER TABLE BalanceContract ADD FOREIGN KEY (BalanceId) REFERENCES Balance (BalanceId);