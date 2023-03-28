import { Link } from "react-router-dom";
import { useEffect, useState } from 'react';
import { Book } from "./Book";
import { getBooks } from "../../backend.js";

/*
 * Set to http://localhost:9000 or wherever your API server is running for
 * live data. If unset, the data will come from literals defined in ./data
 * const dataUrl = "http://localhost:9000";
 */

export function Books() {
  const [books, setBooks] = useState(null);
  useEffect(() => {
    const fetchData = async () => {
      const newBooks = await getBooks();
      setBooks(newBooks);
    };
    fetchData();
  }, []);

  if (books) {
    return (
    <div>
      <table>
        <thead>
          <tr>
            <th>Title</th>
            <th>Authors</th>
            <th>Pub Year</th>
            <th>Copies</th>
          </tr>
        </thead>
        <tbody>
          {books.map(book => <Book key={book.id} book={book} />)}
        </tbody>
      </table>
    </div>
    );
  } else {
    return null;
  }
}

