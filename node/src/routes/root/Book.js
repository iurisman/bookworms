import { Link } from "react-router-dom";

export function Book({book}) {
    return (
        <tr key={book.id}>
            <td><a href={"/books/" + book.id}>{book.title}</a></td>
            <td>{book.authors.map(a => `${a.first} ${a.last}`).join(', ')}</td>
            <td>{book.pubDate}</td>
            <td style={{"textAlign":"center"}}>{book.copies}</td>
        </tr>
    )
}
