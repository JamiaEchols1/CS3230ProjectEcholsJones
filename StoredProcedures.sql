-- Rental Transaction --
DELIMITER $$
CREATE DEFINER=`jechols5`@`%` PROCEDURE `uspCreateRentalTransaction`(IN `transactionId` INT, IN `dueDate` DATE, IN `transactionDate` DATE, IN `customerId` INT, IN `employeeId` INT, IN `rentalItems` TEXT)
    NO SQL
BEGIN
DECLARE sql_error tinyint default false;
DECLARE Continue HANDLER FOR SQLEXCEPTION
set sql_error = true;
start transaction;
insert into rental_transaction (transactionId, dueDate, transactionDate, customerId, employeeId) values (transactionId, dueDate, transactionDate, customerId, employeeId);
   Set @sql = CONCAT("INSERT into rental_item (rentalId, furnitureId, quantity) values ", rentalItems);
   PREPARE stmt from @sql;
   EXECUTE stmt;
   UPDATE furniture, rental_item set furniture.quantity = furniture.quantity - rental_item.quantity where rental_item.rentalId = transactionId and furniture.furnitureId = rental_item.furnitureId;
if sql_error = false then
commit;
else
rollback;
end if;
END$$
DELIMITER ;

-- Return Transaction --
DELIMITER $$
CREATE DEFINER=`jechols5`@`%` PROCEDURE `uspCreateReturnTransaction`(IN `transactionId` INT, IN `returnDate` DATE, IN `customerId` INT, IN `employeeId` INT, IN `returnItems` TEXT)
    NO SQL
BEGIN
DECLARE sql_error tinyint default false;
DECLARE Continue HANDLER FOR SQLEXCEPTION
set sql_error = true;
start transaction;
insert into return_transaction (transactionId, returnDate, customerId, employeeId) values (transactionId, returnDate, customerId, employeeId);
   Set @sql = CONCAT("INSERT into return_item (rentalId, returnId, returnDate, furnitureId, quantity) values ", returnItems);
   PREPARE stmt from @sql;
   EXECUTE stmt;
   Update furniture, return_item set furniture.quantity = furniture.quantity + return_item.quantity where furniture.furnitureId = return_item.furnitureId and return_item.returnId = transactionId;
   update rental_item, return_item set rental_item.quantity = rental_item.quantity - return_item.quantity where rental_item.rentalId = return_item.rentalId and rental_item.furnitureId = return_item.furnitureId and return_item.returnId = transactionId;
if sql_error = false then
commit;
else
rollback;
end if;
 END$$
DELIMITER ;

-- edit Employee --
DELIMITER $$
CREATE DEFINER=`jechols5`@`%` PROCEDURE `uspEditEmployee`(IN `employeeId` INT, IN `firstname` VARCHAR(50), IN `lastname` VARCHAR(50), IN `address` VARCHAR(50), IN `zipcode` VARCHAR(50), IN `state` VARCHAR(50), IN `city` VARCHAR(50), IN `phonenumber` VARCHAR(10), IN `username` VARCHAR(50))
    NO SQL
BEGIN
update employee
SET
employee.firstName = firstname,
employee.lastName = lastname,
employee.address1 = address,
employee.zipcode = zipcode,
employee.city = city,
employee.state = state,
employee.phoneNumber = phonenumber,
employee.username = username
where 
employee.employeeId = employeeId;
END$$
DELIMITER ;

-- edit customer --
DELIMITER $$
CREATE DEFINER=`rjones47`@`%` PROCEDURE `uspEditCustomer`(IN memberID int, firstName varchar(50), lastName varchar(50), gender varchar(8), address1 varchar(50), state varchar(50), zipcode varchar(50), city varchar(50), phoneNumber char(10), birthday date)
BEGIN
UPDATE customer c
SET  c.firstName = firstName, c.lastName = lastName, c.gender = gender, c.address1 = address1, c.state = state, c.zipcode = zipcode, c.city = city, c.phoneNumber = phoneNumber, c.birthday = birthday
WHERE c.memberID = memberID;
END$$
DELIMITER ;
