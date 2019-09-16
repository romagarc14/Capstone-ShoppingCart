Shopping Cart Application

------------
Requirements
------------
Full-stack, e-commerce type application
Front-end Angular
Back-end Java with Spring Boot and JPA
Backed by a MySQL database, in a schema called shopping_cart
The following information for each item should be stored in the database:

Name
Price
Domestic/Imported
Category

Every item in the database should fit into one of the following categories:

Books
Food
Medical
Music
Luxury Items
Clothes

-----
Rules
-----
Sales tax of 10% applies on all goods except Books, Food, Medical Supplies, because they are exempt.
Import duty is an additional tax applied to all import items at a rate of 5%, no exemptions.
The users should be able to search/filter goods based on name or product category.
Users need to be able to add or remove items in various quantities to and from their shopping cart.
When the user clicks "Purchase",  they should receive a receipt which includes the names of their items and their prices, including tax.  The receipt should also show the total cost of all items, and total amount of sales tax paid.
Rounding rules: A tax of n% on a product that costs p dollars is calculated as n * p / 100. This tax should be rounded up to the nearest 0.05 dollars, or 5 cents, for each item. For example, if a domestic CD cost $15.30, the tax would be 10%. 10 * 15.30 / 100 = 1.53, which we round up to the nearest .05, so the tax for this item is $1.55.


-------------------------
Sample Inputs and Outputs
-------------------------
Input 1:
--------
1 book at 12.49
1 music CD at 14.99
1 chocolate bar at 0.85

--------
Input 2:
--------
1 imported box of chocolates at 10.00
1 imported bottle of perfume at 47.50

--------
Input 3:
--------
1 imported bottle of perfume at 27.99
1 bottle of perfume at 18.99
1 packet of headache pills at 9.75
1 box of imported chocolates at 11.25

---------
Output 1:
---------
1 book: 12.49
1 music CD: 16.49
1 chocolate bar: 0.85
Sales Taxes: 1.50 Total: 29.83

---------
Output 2:
---------
1 imported box of chocolates: 10.50
1 imported bottle of perfume: 54.65
Sales Taxes: 7.65 Total: 65.15

---------
Output 3:
---------
1 imported bottle of perfume: 32.19
1 bottle of perfume: 20.89
1 packet of headache pills: 9.75
1 imported box of chocolates: 11.85
Sales Taxes: 6.70 Total: 74.68

----------------
Database Values
----------------
CREATE DATABASE	produce_db;
USE shopping_cart;
INSERT INTO items
(name, price, is_imported, category, image_url)
VALUES
('The Great Gatsby', 10.61, false, 'Book', '../../assets/images/greatgatsby.jpg'),
('To Kill A Mockingbird', 7.33, false, 'Book', '../../assets/images/tokillamockingbird.jpg'),
('The Alchemist', 10.90, false, 'Book', '../../assets/images/thealchemist.jpg'),
('1984', 8.15, false, 'Book', '../../assets/images/1984.jpg'),
('How To Win Friends And Influence People', 12.22, false, 'Book', '../../assets/images/howtowin.jpg'),
('Of Mice And Men', 10.04, false, 'Book', '../../assets/images/ofmiceandmen.jpg'),
('Pocky Chocolate Biscuit Sticks', 2.99, true, 'Food', '../../assets/images/pockychocolate.jpg'),
('Meiji Hello Panda Strawberry Cookies', 1.19, true, 'Food', '../../assets/images/hellopanda.jpg'),
('Kasugai Fruit Gummy Candy Assortment', 2.99, true, 'Food', '../../assets/images/fruitgummy.jpg'),
('Haribo Gold Gummy Bears', 1.99, false, 'Food', '../../assets/images/gummybears.jpg'),
('Ritter Sport Milk Chocolate With Whole Hazelnuts', 1.99, true, 'Food', '../../assets/images/rittersport.jpg'),
('Walkers Pure Butter Assorted Shortbread Cookies', 3.66, true, 'Food', '../../assets/images/walkersshortbread.jpg'),
('Genuine Bayer Aspirin, Coated Tablets, Pain Reliever', 4.49, false, 'Medicine', '../../assets/images/bayeraspirin.jpg'),
('Claritin 24-Hour Non-Drowsy Antihistamine Tablets', 12.79, false, 'Medicine', '../../assets/images/claritin.jpg'),
('Pepto-Bismol 5 Symptom Relief Liquid Original', 6.19, false, 'Medicine', '../../assets/images/peptobismol.jpg'),
('Tylenol Extra Strength Rapid Release Gels with Acetaminophen', 6.29, false, 'Medicine', '../../assets/images/tylenol.jpg'),
('Robitussin Peak Cold, Cough + Chest Congestion DM Max', 8.99, false, 'Medicine', '../../assets/images/robitussin.jpg'),
('Vicks Nyquil Cold & Flu Liquid', 9.99, false, 'Medicine', '../../assets/images/nyquil.jpg'),
('The Dark Side Of The Moon', 11.71, false, 'Music', '../../assets/images/darksideofthemoon.jpg'),
('Rumours', 9.99, false, 'Music', '../../assets/images/rumours.jpg'),
('Abbey Road', 17.09, false, 'Music', '../../assets/images/abbeyroad.jpg'),
('Back In Black', 9.66, false, 'Music', '../../assets/images/backinblack.jpg'),
('Born In The USA', 8.35, false, 'Music', '../../assets/images/bornintheusa.jpg'),
('Legend', 12.79, false, 'Music', '../../assets/images/legend.jpg'),
('Burberry Giant-Check Cashmere Scarf', 430, true, 'Luxury', '../../assets/images/burberryscarf.jpg'),
('Kate Spade New York Molly Large Leather Tote', 228, false, 'Luxury', '../../assets/images/katespadetote.jpg'),
('Ray-Ban Original Aviator Sunglasses, Golden', 168, false, 'Luxury', '../../assets/images/raybanaviator.jpg'),
('Chanel Coco Mademoiselle Eau De Parfum Intense Spray', 145, true, 'Luxury', '../../assets/images/chanelcoco.jpg'),
('Dior J\'Adore Eau De Parfum', 130, true, 'Luxury', '../../assets/images/diorjadore.jpg'),
('Tory Burch Robinson Small Saffiano Tote Bag', 298, true, 'Luxury', '../../assets/images/toryburchtote.jpg'),
('Calvin Klein Slim-Fit Solid Dress Pants', 95, false, 'Clothes', '../../assets/images/slimfitdresspants.jpg'),
('Free People Eucalyptus Cardigan', 148, false, 'Clothes', '../../assets/images/cardigan.jpg'),
('Free People Leo Henley Thermal Top', 68, false, 'Clothes', '../../assets/images/henleythermaltop.jpg'),
('Perry Ellis Mens V-Neck Sweater', 69.50, false, 'Clothes', '../../assets/images/vnecksweater.jpg'),
('Calvin Klein Pencil Skirt', 54.49, false, 'Clothes', '../../assets/images/pencilskirt.jpg'), 
('Anne Klein Pleated Sleeveless Top', 69, false, 'Clothes', '../../assets/images/ruffledsleevelesstop.jpg');