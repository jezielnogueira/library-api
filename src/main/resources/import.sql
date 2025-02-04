-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- Inserir livros de teste na tabela "book"

INSERT INTO book (title, author, description, isbn, genre, cover, status, language, publisher, tags, coverUrl)
VALUES
    ('The Great Gatsby', 'F. Scott Fitzgerald', 'A novel set in the Roaring Twenties that examines themes of wealth, class, and the American Dream.', '9780743273565', 'Fiction', 'hardcover', 'available', 'English', 'Scribner', 'classic, literature, american', 'https://example.com/cover1.jpg'),
    ('To Kill a Mockingbird', 'Harper Lee', 'A novel of racial injustice in the Deep South, focusing on the Finch family.', '9780061120084', 'Fiction', 'paperback', 'available', 'English', 'J.B. Lippincott & Co.', 'classic, literature, american', 'https://example.com/cover2.jpg'),
    ('1984', 'George Orwell', 'A dystopian novel about a totalitarian regime that uses surveillance and propaganda to control the population.', '9780451524935', 'Dystopian', 'hardcover', 'checked-out', 'English', 'Secker & Warburg', 'dystopia, classic, political', 'https://example.com/cover3.jpg'),
    ('Moby Dick', 'Herman Melville', 'The story of Captain Ahab’s obsessive quest to kill the white whale, Moby Dick.', '9781503280786', 'Adventure', 'paperback', 'available', 'English', 'Harper & Brothers', 'classic, adventure, sea', 'https://example.com/cover4.jpg'),
    ('The Catcher in the Rye', 'J.D. Salinger', 'The story of Holden Caulfield, a teenager who has been expelled from multiple prep schools and is wandering New York City.', '9780316769488', 'Fiction', 'hardcover', 'available', 'English', 'Little, Brown and Company', 'classic, coming-of-age, american', 'https://example.com/cover5.jpg'),
    ('Pride and Prejudice', 'Jane Austen', 'A classic novel about the romantic entanglements of the Bennet family, focusing on the relationship between Elizabeth Bennet and Mr. Darcy.', '9780141439518', 'Romance', 'paperback', 'available', 'English', 'T. Egerton', 'classic, romance, british', 'https://example.com/cover6.jpg');
