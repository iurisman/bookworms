CREATE TABLE authors (
  id               SERIAL PRIMARY KEY,
  first            VARCHAR(64),
  last             VARCHAR(64) NOT NULL
 );

CREATE TABLE books (
  id               SERIAL PRIMARY KEY,
  isbn             VARCHAR(16) NOT NULL,
  title            VARCHAR(256) NOT NULL,
  pub_date         DATE NOT NULL,
  cover_image_uri  VARCHAR(512) NOT NULL
 );

CREATE TABLE book_authors (
  book_id           INTEGER NOT NULL REFERENCES books(id),
  author_id         INTEGER NOT NULL REFERENCES authors(id)
 );

CREATE TABLE copies (
  id                SERIAL PRIMARY KEY,
  book_id           INTEGER NOT NULL REFERENCES books(id),
  condition         VARCHAR(32),
  price             NUMERIC(10,2),
  location          VARCHAR(64),
  available         BOOLEAN NOT NULL
 );

INSERT INTO AUTHORS (first, last)
  VALUES
    ('Brian', 'Kernighan'),
    ('Dennis', 'Ritchie'),
    ('Donald', 'Knuth'),
    ('Alfred', 'Aho'),
    ('Jeffrey', 'Ullman'),
    ('Ravi', 'Sethi'),
    ('Monica', 'Lam'),
    ('Martin', 'Odersky'),
    ('Lex', 'Spoon'),
    ('Bill', 'Venners'),
    ('Frank', 'Sommers'),
    ('Andrew', 'Tanenbaum'),
    ('Herbert', 'Bos');

INSERT INTO books (isbn, title, pub_date, cover_image_uri)
  VALUES
    ('978-0131103627', 'C Programming Language, 2nd Edition', to_date('March 22, 1988','Month DD, YYYY'),
     'https://m.media-amazon.com/images/I/411ejyE8obL._SX218_BO1,204,203,200_QL40_FMwebp_.jpg'),
    ('978-0201038033', 'The Art of Computer Programming', to_date('1973','YYYY'),
     'https://m.media-amazon.com/images/I/41w-UIseGpL._SX373_BO1,204,203,200_.jpg'),
    ('978-0321486813', 'Compilers: Principles, Techniques, and Tools', to_date('August 31, 2006','Month DD, YYYY'),
     'https://m.media-amazon.com/images/I/51vQlx7ig3L._SY291_BO1,204,203,200_QL40_FMwebp_.jpg'),
    ('978-0997148008', 'Programming in Scala, Fifth Edition', to_date('June 15, 2021','Month DD, YYYY'),
     'https://m.media-amazon.com/images/I/41Kbvtzc99S._SX377_BO1,204,203,200_.jpg'),
    ('978-0133591620', 'Modern Operating Systems', to_date('March 10, 2014','Month DD, YYYY'),
     'https://m.media-amazon.com/images/I/511H8QWUL4L._SX218_BO1,204,203,200_QL40_FMwebp_.jpg');

INSERT INTO book_authors (book_id, author_id)
  VALUES
    ((SELECT id FROM books WHERE title = 'C Programming Language, 2nd Edition'),
     (SELECT id FROM authors WHERE first = 'Brian' AND last = 'Kernighan')),
    ((SELECT id FROM books WHERE title = 'C Programming Language, 2nd Edition'),
     (SELECT id FROM authors WHERE first = 'Dennis' AND last = 'Ritchie')),
    ((SELECT id FROM books WHERE title = 'The Art of Computer Programming'),
     (SELECT id FROM authors WHERE first = 'Donald' AND last = 'Knuth')),
    ((SELECT id FROM books WHERE title = 'Compilers: Principles, Techniques, and Tools'),
     (SELECT id FROM authors WHERE first = 'Alfred' AND last = 'Aho')),
    ((SELECT id FROM books WHERE title = 'Compilers: Principles, Techniques, and Tools'),
     (SELECT id FROM authors WHERE first = 'Jeffrey' AND last = 'Ullman')),
    ((SELECT id FROM books WHERE title = 'Compilers: Principles, Techniques, and Tools'),
     (SELECT id FROM authors WHERE first = 'Ravi' AND last = 'Sethi')),
    ((SELECT id FROM books WHERE title = 'Compilers: Principles, Techniques, and Tools'),
     (SELECT id FROM authors WHERE first = 'Monica' AND last = 'Lam')),
    ((SELECT id FROM books WHERE title = 'Programming in Scala, Fifth Edition'),
     (SELECT id FROM authors WHERE first = 'Martin' AND last = 'Odersky')),
    ((SELECT id FROM books WHERE title = 'Programming in Scala, Fifth Edition'),
     (SELECT id FROM authors WHERE first = 'Lex' AND last = 'Spoon')),
    ((SELECT id FROM books WHERE title = 'Programming in Scala, Fifth Edition'),
     (SELECT id FROM authors WHERE first = 'Bill' AND last = 'Venners')),
    ((SELECT id FROM books WHERE title = 'Programming in Scala, Fifth Edition'),
     (SELECT id FROM authors WHERE first = 'Frank' AND last = 'Sommers')),
    ((SELECT id FROM books WHERE title = 'Modern Operating Systems'),
     (SELECT id FROM authors WHERE first = 'Andrew' AND last = 'Tanenbaum')),
    ((SELECT id FROM books WHERE title = 'Modern Operating Systems'),
     (SELECT id FROM authors WHERE first = 'Herbert' AND last = 'Bos'));

INSERT INTO copies (book_id, condition, price, location, available)
  VALUES
    ((SELECT id FROM books WHERE title = 'C Programming Language, 2nd Edition'), 'Fair', 34.03, 'California', TRUE),
    ((SELECT id FROM books WHERE title = 'C Programming Language, 2nd Edition'), 'New', 187.60, 'Massachusetts', TRUE),
    ((SELECT id FROM books WHERE title = 'C Programming Language, 2nd Edition'), 'Mint', 23.84, 'United Kingdom', TRUE),
    ((SELECT id FROM books WHERE title = 'C Programming Language, 2nd Edition'), 'Near Mint', 100.00, 'Australia', FALSE),
    ((SELECT id FROM books WHERE title = 'The Art of Computer Programming'), 'Used', 245.00, 'Burkina Faso', TRUE),
    ((SELECT id FROM books WHERE title = 'The Art of Computer Programming'), 'New',  1000.00, 'Mexico', TRUE),
    ((SELECT id FROM books WHERE title = 'The Art of Computer Programming'), 'New', 890.00, 'Florida', FALSE),
    ((SELECT id FROM books WHERE title = 'Compilers: Principles, Techniques, and Tools'), 'New', 300.00, 'Pennsylvania', FALSE),
    ((SELECT id FROM books WHERE title = 'Compilers: Principles, Techniques, and Tools'), 'New', 300.00, 'South Carolina', TRUE),
    ((SELECT id FROM books WHERE title = 'Compilers: Principles, Techniques, and Tools'), 'Fair', 3.80, 'North Carolina', TRUE),
    ((SELECT id FROM books WHERE title = 'Compilers: Principles, Techniques, and Tools'), 'Fine', 45.00, 'Nevada', TRUE),
    ((SELECT id FROM books WHERE title = 'Compilers: Principles, Techniques, and Tools'), 'Fair', 32.50, 'Oregon', TRUE),
    ((SELECT id FROM books WHERE title = 'Compilers: Principles, Techniques, and Tools'), 'Used', 33.50, 'Washington', TRUE),
    ((SELECT id FROM books WHERE title = 'Compilers: Principles, Techniques, and Tools'), 'Used', 28.50, 'Montana', TRUE),
    ((SELECT id FROM books WHERE title = 'Programming in Scala, Fifth Edition'), 'New', 49.99, 'Idaho', TRUE),
    ((SELECT id FROM books WHERE title = 'Programming in Scala, Fifth Edition'), 'New', 49.99, 'Ohio', TRUE),
    ((SELECT id FROM books WHERE title = 'Programming in Scala, Fifth Edition'), 'New', 49.99, 'Canada', TRUE),
    ((SELECT id FROM books WHERE title = 'Programming in Scala, Fifth Edition'), 'New', 49.99, 'Georgia', TRUE),
    ((SELECT id FROM books WHERE title = 'Programming in Scala, Fifth Edition'), 'New', 49.99, 'Tennessee', TRUE),
    ((SELECT id FROM books WHERE title = 'Programming in Scala, Fifth Edition'), 'New', 49.99, 'Alabama', TRUE),
    ((SELECT id FROM books WHERE title = 'Programming in Scala, Fifth Edition'), 'New', 49.99, 'Iowa', TRUE),
    ((SELECT id FROM books WHERE title = 'Programming in Scala, Fifth Edition'), 'New', 49.99, 'Kansas', TRUE),
    ((SELECT id FROM books WHERE title = 'Programming in Scala, Fifth Edition'), 'New', 49.99, 'Arkansas', TRUE),
    ((SELECT id FROM books WHERE title = 'Programming in Scala, Fifth Edition'), 'New', 49.99, 'Wyoming', TRUE),
    ((SELECT id FROM books WHERE title = 'Programming in Scala, Fifth Edition'), 'New', 49.99, 'Maine', TRUE),
    ((SELECT id FROM books WHERE title = 'Programming in Scala, Fifth Edition'), 'Used', 39.00, 'Chile', TRUE),
    ((SELECT id FROM books WHERE title = 'Modern Operating Systems'), 'Used', 39.00, 'France', TRUE),
    ((SELECT id FROM books WHERE title = 'Modern Operating Systems'), 'New', 267.00, 'Nederland', TRUE),
    ((SELECT id FROM books WHERE title = 'Modern Operating Systems'), 'Near Mint', 120.00, 'Japan', TRUE),
    ((SELECT id FROM books WHERE title = 'Modern Operating Systems'), 'Mint', 110.00, 'Korea', FALSE),
    ((SELECT id FROM books WHERE title = 'Modern Operating Systems'), 'Used', 29.59, 'New Zealand', TRUE);
