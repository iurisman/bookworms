CREATE TABLE authors (
  id               SERIAL,
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
