import { useParams } from "react-router-dom";
import { useEffect, useState } from 'react';
import { getBookDetails } from "../../backend.js";
import { BookCopy } from './BookCopy'

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
      <table className="book-detail">
        <tr>
          <td>
            <img src={bookDetails.book.coverImageUri}/>
          </td>
          <td>
            <h2>{bookDetails.book.title}</h2>
            <h3>{bookDetails.book.authors.map(a => `${a.first} ${a.last}`).join(', ')}</h3>
            <h4>ISBN: {bookDetails.book.isbn}</h4>
            <h4>Publication Date: {bookDetails.book.pubDate}</h4>
          </td>
          <td>
            <table>
              <thead>
                <tr>
                  <th>Condition</th>
                  <th>Price</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                  {bookDetails.availableCopies.map(copy => <BookCopy key={copy.id} copy={copy} />)}
              </tbody>
            </table>
          </td>
        </tr>
      </table>
    );
  } else {
    return null;
  }

}
