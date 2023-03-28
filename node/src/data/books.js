//import {dataUrl} from '../Router'

export function getBooks() {
    return (
    [
        {
            "id": 1,
            "isbn": "978-0131103627",
            "title": "C Programming Language, 2nd Edition",
            "date": "March 22, 1988",
            "copies": 3,
            "authors": [
                {
                    "id": 1,
                    "name": "Brian Kernighan"
                },
                {
                    "id": 2,
                    "name": "Dennis Rittchie"
                }
            ]
        },
        {
            "id": 2,
            "isbn": "978-0201038033",
            "title": "The Art of Computer Programming",
            "date": "1973",
            "copies": 1,
            "authors": [
                {
                    "id": 3,
                    "name": "Donald Knuth"
                }
            ]
        },
        {
            "id": 3,
            "isbn": "978-0321486813",
            "title": "Compilers: Principles, Techniues, and Tools",
            "date": "August 31, 2006",
            "copies": 9,
            "authors": [
                {
                    "id": 4,
                    "name": "Alfred Aho"
                },
                {
                    "id": 5,
                    "name": "Jeffrey Ullman"
                },
                {
                    "id": 6,
                    "name": "Ravi Sethi"
                },
                {
                    "id": 7,
                    "name": "Monica Lam"
                },
            ]
        },
        {
            "id": 4,
            "isbn": "978-0997148008",
            "title": "Programming in Scala, Fifth Edition",
            "date": "June 15, 2021",
            "copies": 3,
            "authors": [
                {
                    "id": 8,
                    "name": "Martin Odersky"
                },
                {
                    "id": 9,
                    "name": "Lex Spoon"
                },
                {
                    "id": 10,
                    "name": "Bill Venners"
                },
                {
                    "id": 11,
                    "name": "Frank Sommers"
                },
            ]
        },
        {
            "id": 4,
            "isbn": "978-0133591620",
            "title": "Modern Operating Systems",
            "date": "JMarch 10, 2014",
            "copies": 1,
            "authors": [
                {
                    "id": 12,
                    "name": "Andrew Tanenbaum",
                },
                {
                    "id": 13,
                    "name": "Herbert Bos",
                },
            ]
        },
    ]);
}

export function getAuthors() {
    const result =
    [
        {
            "id": 1,
            "firstName": "Brian",
            "lastName": "Kernighan",
        },
        {
            "id": 2,
            "firstName": "Dennis",
            "lastName": "Ritchie",
        },
        {
            "id": 3,
            "firstName": "Donald",
            "lastName": "Knuth",
        },
        {
            "id": 4,
            "firstName": "Alfred",
            "lastName": "Aho",
        },
        {
            "id": 5,
            "firstName": "Jeffrey",
            "lastName": "Ullman",
        },
        {
            "id": 6,
            "firstName": "Ravi",
            "lastName": "Sethi",
        },
        {
            "id": 7,
            "firstName": "Monica",
            "lastName": "Lam",
        },
        {
            "id": 8,
            "firstName": "Martin",
            "lastName": "Odersky",
        },
        {
            "id": 9,
            "firstName": "Lex",
            "lastName": "Spoon",
        },
        {
            "id": 10,
            "firstName": "Bill",
            "lastName": "Venners",
        },
        {
            "id": 11,
            "firstName": "Frank",
            "lastName": "Sommers",
        },
        {
            "id": 12,
            "firstName": "Andrew",
            "lastName": "Tanenbaum",
        },
        {
            "id": 13,
            "firstName": "Herbert",
            "lastName": "Bos",
        },
    ]
    return result;
}

export function getBook(id) {
    const result =
        {
            "id": 4,
            "isbn": "978-0997148008",
            "title": "Programming in Scala, Fifth Edition",
            "date": "June 15, 2021",
            "authors": [
                {
                    "id": 8,
                    "name": "Martin Odersky"
                },
                {
                    "id": 9,
                    "name": "Lex Spoon"
                },
                {
                    "id": 10,
                    "name": "Bill Venners"
                },
                {
                    "id": 11,
                    "name": "Frank Sommers"
                },
            ],
            "copies": 3
        }
    return result;
}