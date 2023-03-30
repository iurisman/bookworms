import { useParams } from "react-router-dom";
import { useEffect, useState } from 'react';
import { putCopyHold, buyCopy } from "../../backend.js";

export function Checkout() {
  const params = useParams();

  const [receipt, setReceipt] = useState(null);
  useEffect(() => {
      const fetchData = async () => {
      const newReceipt = await putCopyHold(params.id);
      setReceipt(newReceipt);
   };
    fetchData();
  }, []);

      if (receipt) {
      const bookDetails = JSON.parse(localStorage.getItem('book'));
      const book = bookDetails.book;
      const copy = bookDetails.availableCopies.find(elem => elem.id == params.id);
      const tax = (copy.price * 0.097).toFixed(2);
      const shipping = (Math.random() * 12).toFixed(2);
      const total = copy.price + tax + shipping;

        return (
          <table className="book-detail">
            <tbody>
                <tr>
                  <td>
                    <img src={book.coverImageUri}/>
                  </td>
                  <td>
                    <h2>{book.title}</h2>
                    <h3>{book.authors.map(a => `${a.first} ${a.last}`).join(', ')}</h3>
                    <h4>ISBN: {book.isbn}</h4>
                    <h4>Publication Date: {bookDetails.book.pubDate}</h4>
                    <br/>
                    Ships from <b>{copy.location}</b> in 2-5 business days
                    <br/>
                    <table className="bill">
                      <tbody>
                        <tr>
                          <td>Item</td>
                          <td>{receipt.price}</td>
                        </tr><tr>
                          <td>Tax</td>
                          <td>{receipt.tax}</td>
                        </tr><tr>
                          <td>Shipping</td>
                          <td>{receipt.shipping}</td>
                        </tr><tr>
                          <td><b>Total</b></td>
                          <td><b>{receipt.total}</b></td>
                        </tr>
                      </tbody>
                    </table>
                    <button onClick={() => buy(copy)}>Buy</button>
                  </td>
                </tr>
            </tbody>
          </table>
        );
    } else {
      return null;
    }
}

function buy(copy) {
  console.log(copy);
  buyCopy(copy);
}

