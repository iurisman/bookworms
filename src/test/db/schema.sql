CREATE TABLE authors (
  id               SERIAL PRIMARY KEY,
  first            VARCHAR(64),
  last             VARCHAR(64) NOT NULL
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

CREATE TABLE books (
  id               SERIAL PRIMARY KEY,
  isbn             VARCHAR(16) NOT NULL,
  title            VARCHAR(256) NOT NULL,
  pub_date         DATE NOT NULL
 );

INSERT INTO BOOKS (isbn, title, pub_date)
  VALUES
    ('978-0131103627', 'C Programming Language, 2nd Edition', to_date('March 22, 1988','Month DD, YYYY')),
    ('978-0201038033', 'The Art of Computer Programming', to_date('1973','YYYY')),
    ('978-0321486813', 'Compilers: Principles, Techniques, and Tools', to_date('August 31, 2006','Month DD, YYYY')),
    ('978-0997148008', 'Programming in Scala, Fifth Edition', to_date('June 15, 2021','Month DD, YYYY')),
    ('978-0133591620', 'Modern Operating Systems', to_date('March 10, 2014','Month DD, YYYY'));

CREATE TABLE book_authors (
  book_id           INTEGER NOT NULL REFERENCES books(id),
  author_id         INTEGER NOT NULL REFERENCES authors(id)
 );

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