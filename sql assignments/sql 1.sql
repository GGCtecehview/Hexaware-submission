-- task 1

CREATE TABLE Venue (
venue_id INT AUTO_INCREMENT PRIMARY KEY,
venue_name VARCHAR(255) NOT NULL,
address TEXT NOT NULL
);

CREATE TABLE Event (
event_id INT AUTO_INCREMENT PRIMARY KEY,
event_name VARCHAR(255) NOT NULL,
event_date DATE NOT NULL,
event_time TIME NOT NULL,
venue_id INT,
total_seats INT NOT NULL,
available_seats INT NOT NULL,
ticket_price DECIMAL(10, 2) NOT NULL,
event_type ENUM('Movie', 'Sports', 'Concert') NOT NULL,
FOREIGN KEY (venue_id) REFERENCES Venue(venue_id)
);

CREATE TABLE Customer (
customer_id INT AUTO_INCREMENT PRIMARY KEY,
customer_name VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL,
phone_number VARCHAR(20) NOT NULL
);

CREATE TABLE Booking (
booking_id INT AUTO_INCREMENT PRIMARY KEY,
customer_id INT,
event_id INT,
num_tickets INT NOT NULL,
total_cost DECIMAL(10, 2) NOT NULL,
booking_date DATE NOT NULL,
FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
FOREIGN KEY (event_id) REFERENCES Event(event_id)
);


-- Task 2 
-- query 1 

INSERT INTO Venue (venue_name, address) VALUES
('Pune Central Hall', 'Shivajinagar, Pune'),
('Koregaon Park Auditorium', 'Koregaon Park, Pune'),
('Aundh Cultural Centre', 'Aundh, Pune'),
('Baner Multipurpose Arena', 'Baner, Pune'),
('Viman Nagar Event Plaza', 'Viman Nagar, Pune'),
('Kothrud Convention Centre', 'Kothrud, Pune'),
('Hadapsar Amphitheatre', 'Hadapsar, Pune'),
('Pimpri Performance Hall', 'Pimpri-Chinchwad, Pune'),
('Magarpatta City Auditorium', 'Magarpatta, Pune'),
('Wagholi Grand Stage', 'Wagholi, Pune');

select * from venue;

INSERT INTO Event (event_name, event_date, event_time, venue_id, total_seats, available_seats, ticket_price, event_type) VALUES
('Rock Concert', '2024-03-05', '20:00:00', 1, 500, 500, 2000.00, 'Concert'),
('Classical Music Night', '2024-04-10', '18:30:00', 2, 300, 300, 1200.00, 'Concert'),
('Sci-Fi Movie Marathon', '2024-05-15', '17:00:00', 3, 150, 150, 800.00, 'Movie'),
('Historical Drama Play', '2024-06-20', '19:00:00', 4, 350, 350, 1500.00, 'Sports'), -- Assuming 'Play' is categorized under 'Sports'
('Jazz Music Festival', '2024-07-25', '20:30:00', 5, 400, 400, 1800.00, 'Concert'),
('International Film Screening', '2024-08-30', '16:00:00', 6, 200, 200, 1000.00, 'Movie'),
('Live Theatre Comedy', '2024-09-10', '19:00:00', 7, 250, 250, 1300.00, 'Sports'), -- Assuming 'Comedy' is categorized under 'Sports'
('Pop Music Concert', '2024-10-15', '20:00:00', 8, 450, 450, 2200.00, 'Concert'),
('Action Movie Night', '2024-11-20', '18:00:00', 9, 300, 300, 1100.00, 'Movie'); 

select * from event;

alter table event auto_increment = 1;


select * from event;

INSERT INTO Customer (customer_name, email, phone_number) VALUES
('Rohit Sharma', 'rohit.sharma@email.com', '9822012345'),
('Priya Desai', 'priya.desai@email.com', '9938012345'),
('Ankit Raj', 'ankit.raj@email.com', '9822098765'),
('Sara Khan', 'sara.khan@email.com', '9922012345'),
('Vivek Singh', 'vivek.singh@email.com', '9938019876'),
('Anjali Patil', 'anjali.patil@email.com', '9822087654'),
('Rajesh Kulkarni', 'rajesh.kulkarni@email.com', '9922098765'),
('Neha Goyal', 'neha.goyal@email.com', '9938012346'),
('Amit Verma', 'amit.verma@email.com', '9822012347'),
('Divya Mehra', 'divya.mehra@email.com', '9922012348');

select * from customer;


select * from booking;

-- sql query 2 
 
select event_name,event_date,event_time,available_seats,ticket_price from event;

-- sql query 3

select event_name from event
where available_seats > 0;

-- sql query 4
select * from venue;
delete from customer where customer_id >= 11;



INSERT INTO Event (event_name, event_date, event_time, venue_id, total_seats, available_seats, ticket_price, event_type) VALUES
('Champions Cup Final', '2024-07-15', '18:00:00', 1, 500, 500, 2500.00, 'Sports'),
('Intercontinental Cup', '2024-08-20', '19:00:00', 2, 300, 300, 3000.00, 'Sports'),
('World Cup Qualifiers', '2024-09-05', '17:00:00', 3, 400, 400, 2000.00, 'Sports');


select * from event 
where event_name LIKE '%cup%';

-- query 5
SELECT *
FROM Event_1
WHERE ticket_price BETWEEN 1000 AND 2500;

-- query 6 
SELECT *
FROM Event_1
WHERE event_date BETWEEN '2024-05-07' AND '2024-12-12';

-- query 7
SELECT *
FROM Event
WHERE available_seats > 0 AND event_type = 'Concert';

-- query 8
SELECT * FROM Customer
ORDER BY customer_id
LIMIT 5 OFFSET 5;

-- query 9
SELECT *
FROM Booking
WHERE num_tickets > 4;

-- query 10 
SELECT *
FROM Customer
WHERE phone_number LIKE '%000';



select * from event;


-- query 11
SELECT *
FROM Event
WHERE available_seats > 15000
ORDER BY total_seats ASC;


-- query 12
SELECT *
FROM Event
WHERE event_name NOT LIKE 'x%' AND event_name NOT LIKE 'y%' AND event_name NOT LIKE 'z%';


select * from customer;


-- task 3 
-- query 1
select * from event;

SELECT event_Type, AVG(ticket_price) AS average_price
FROM Event
GROUP BY event_type;

-- query 2 (total revenue of all events )
select * from booking;
SELECT SUM(total_cost) AS total_revenue
FROM Booking;

-- query 2 (total revenue of each event )
SELECT e.event_name, SUM(b.ticket_sold) AS total_revenue
FROM Booking b
JOIN Event e ON b.event_id = e.event_id
GROUP BY e.event_id, e.event_name;

-- query 3
SELECT event_id, SUM(num_tickets) AS total_tickets_sold
FROM Booking 
GROUP BY event_id
ORDER BY total_tickets_sold DESC
LIMIT 1;

-- query 4
SELECT e.event_name, SUM(b.num_tickets) AS total_tickets_sold
FROM Booking b
JOIN Event e ON b.event_id = e.event_id
GROUP BY e.event_id, e.event_name;


-- query 5
SELECT e.event_id, e.event_name
FROM Event e
LEFT JOIN Booking b ON e.event_id = b.event_id
WHERE b.event_id IS NULL;

-- query 6
SELECT customer_id, SUM(num_tickets) AS total_tickets
FROM Booking
GROUP BY customer_id
ORDER BY total_tickets DESC
LIMIT 1;

-- query 7
SELECT e.event_name, MONTH(b.booking_date) AS month, SUM(b.num_tickets) AS tickets_sold
FROM Booking b
JOIN Event e ON b.event_id = e.event_id
GROUP BY e.event_name, MONTH(b.booking_date);

select * from event;


-- query 8
SELECT v.venue_name, AVG(e.ticket_price) AS average_ticket_price
FROM Event e
JOIN Venue v ON e.venue_id = v.venue_id
GROUP BY v.venue_name;

-- query 9
SELECT e.event_type, SUM(b.num_tickets) AS total_tickets_sold
FROM Booking b
JOIN Event e ON b.event_id = e.event_id
GROUP BY e.event_type;

-- query 10
SELECT YEAR(b.booking_date) AS year, SUM(b.total_cost) AS total_revenue
FROM Booking b
GROUP BY YEAR(b.booking_date);

-- query 11
SELECT customer_id
FROM Booking
GROUP BY customer_id
HAVING COUNT(DISTINCT event_id) > 1;

-- query 12
SELECT customer_id, SUM(total_cost) AS total_revenue
FROM Bookingmember
GROUP BY customer_id;

-- quert 13
SELECT e.event_type, v.venue_name, AVG(e.ticket_price) AS average_ticket_price
FROM Event e
JOIN Venue v ON e.venue_id = v.venue_id
GROUP BY e.event_type, v.venue_name;


-- task 4
-- query 1 
-- Calculates the average ticket price for each venue
SELECT venue_name, 
       (SELECT AVG(ticket_price) 
        FROM event 
        WHERE event.venue_id = venue.venue_id) AS avg_ticket_price 
FROM venue;


-- query 2
-- Selects events where more than half of the tickets have been sold
SELECT * 
FROM event
WHERE (SELECT SUM(num_tickets) 
       FROM booking 
       WHERE booking.event_id = event.event_id) > 0.5 * total_seats;


-- query 3
-- Summarizes the total number of tickets sold for each event
SELECT event_id, event_name, 
       (SELECT SUM(num_tickets) 
        FROM booking 
        WHERE booking.event_id = event.event_id) AS total_tickets_sold 
FROM event;

-- query 4
-- Lists all customers who have not made any bookings
SELECT * 
FROM customer
WHERE NOT EXISTS (SELECT * 
                  FROM booking 
                  WHERE booking.customer_id = customer.customer_id);

-- query 5
-- Identifies events that have not had any ticket sales
SELECT * 
FROM event
WHERE event_id NOT IN (SELECT event_id 
                       FROM booking);
                       
-- query 6
-- Determines the total tickets sold grouped by event type
SELECT event_type, SUM(num_tickets) AS total_tickets_sold
FROM (SELECT event.event_id, event_type, num_tickets 
      FROM event 
      LEFT JOIN booking ON event.event_id = booking.event_id) AS combined_data
GROUP BY event_type;

-- query 7
-- Selects events where the ticket price is above the overall average
SELECT * 
FROM event
WHERE ticket_price > (SELECT AVG(ticket_price) 
                      FROM event);
-- query 8
-- Calculates the total revenue generated by each customer through bookings
SELECT customer_id, customer_name, 
       (SELECT SUM(total_cost) 
        FROM booking 
        WHERE booking.customer_id = customer.customer_id) AS total_revenue 
FROM customer;

-- query 9
-- Lists customers who have booked tickets for events in a specific venue
SELECT * 
FROM customer
WHERE customer_id IN (SELECT DISTINCT customer_id 
                      FROM booking 
                      WHERE event_id IN (SELECT event_id 
                                         FROM event 
                                         WHERE venue_id = 1));

-- query 10
-- Summarizes tickets sold for each event category
SELECT e.event_type, COALESCE(SUM(b.num_tickets), 0) AS total_tickets_sold 
FROM event e
LEFT JOIN booking b ON e.event_id = b.event_id
GROUP BY e.event_type;

-- query 11
-- Lists customers who have booked tickets for events in a specific venue
SELECT * 
FROM customer
WHERE customer_id IN (SELECT DISTINCT customer_id 
                      FROM booking 
                      WHERE event_id IN (SELECT event_id 
                                         FROM event 
                                         WHERE venue_id = 1));


