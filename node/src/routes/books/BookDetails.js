import { useParams } from "react-router-dom";
import { useEffect, useState } from 'react';
import { getBookDetails } from "../../backend.js";
import { BookCopy } from './BookCopy'

export function BookDetails() {
  let params = useParams();
  const [bookDetails, setBookDetails] = useState(null);
  useEffect(() => {
    const fetchData = async () => {
      const newBookDetails = await getBookDetails(params.id);
      setBookDetails(newBookDetails);
      localStorage.setItem('book', JSON.stringify(newBookDetails))
    };
    fetchData();
  }, []);

  if (bookDetails) {
    return (
      <table className="book-detail">
        <tbody>
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
                <h3>Available Copies:</h3>
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
        </tbody>
      </table>
    );
  } else {
    return null;
  }

}
