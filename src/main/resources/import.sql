-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- Inserir livros de teste na tabela "book"

INSERT INTO book (id, title, author, description, isbn, genre, cover, status, publisher, tags, coverUrl)
VALUES
    ('4a71c0bf-d2ef-4df6-8c6d-2216b55a6f65', 'The Great Gatsby', 'F. Scott Fitzgerald', 'A novel set in the Roaring Twenties that examines themes of wealth, class, and the American Dream.', '9780743273565', 'Fiction', 'hardcover', 'AVAILABLE', 'Scribner', 'classic, literature, american', 'https://example.com/cover1.jpg'),
    ('e3072317-0067-489a-ba1e-908c3e05697f', 'To Kill a Mockingbird', 'Harper Lee', 'A novel of racial injustice in the Deep South, focusing on the Finch family.', '9780061120084', 'Fiction', 'paperback', 'AVAILABLE', 'J.B. Lippincott & Co.', 'classic, literature, american', 'https://example.com/cover2.jpg'),
    ('ce7442de-0001-433e-9306-12618c3bb43e', '1984', 'George Orwell', 'A dystopian novel about a totalitarian regime that uses surveillance and propaganda to control the population.', '9780451524935', 'Dystopian', 'hardcover', 'AVAILABLE', 'Secker & Warburg', 'dystopia, classic, political', 'https://example.com/cover3.jpg'),
    ('44f26d86-1e1c-43c1-93bd-9112c1024507', 'Moby Dick', 'Herman Melville', 'The story of Captain Ahab’s obsessive quest to kill the white whale, Moby Dick.', '9781503280786', 'Adventure', 'paperback', 'AVAILABLE', 'Harper & Brothers', 'classic, adventure, sea', 'https://example.com/cover4.jpg'),
    ('602fa27f-a04d-4dbb-877c-d7a53f570735', 'The Catcher in the Rye', 'J.D. Salinger', 'The story of Holden Caulfield, a teenager who has been expelled from multiple prep schools and is wandering New York City.', '9780316769488', 'Fiction', 'hardcover', 'AVAILABLE', 'Little, Brown and Company', 'classic, coming-of-age, american', 'https://example.com/cover5.jpg'),
    ('7a1c844b-8c06-44cd-b8f0-7389789495f2', 'Pride and Prejudice', 'Jane Austen', 'A classic novel about the romantic entanglements of the Bennet family, focusing on the relationship between Elizabeth Bennet and Mr. Darcy.', '9780141439518', 'Romance', 'paperback', 'AVAILABLE', 'T. Egerton', 'classic, romance, british', 'https://example.com/cover6.jpg');
