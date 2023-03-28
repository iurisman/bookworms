import { useParams } from "react-router-dom";
import { useEffect, useState } from 'react';
import { getBookDetails } from "../../backend.js";

export function BookDetails({book}) {
  let params = useParams();
  const [bookDetails, setBookDetails] = useState(null);
  useEffect(() => {
    const fetchData = async () => {
      const newBookDetails = await getBookDetails(params.id);
        setBookDetails(newBookDetails);
      };
    fetchData();
  }, []);

  if (bookDetails) {
    return (
      <div class="book-detail">
        <h2>{bookDetails.book.title}</h2>
        <h3>{bookDetails.book.authors.map(a => `${a.first} ${a.last}`).join(', ')}</h3>
        <h4>ISBN: {bookDetails.book.isbn}</h4>
        <h4>Publication Date: {bookDetails.book.pubDate}</h4>
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
              {params.id}
          </tbody>
        </table>
      </div>
    );
  } else {
    return null;
  }
}
