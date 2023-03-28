
export function BookCopy({copy}) {
    return (
        <tr key={copy.id}>
            <td>{copy.condition}</td>
            <td style={{"textAlign":"center"}}>{copy.price}</td>
            <td><a href={"/checkout/" + copy.id}>buy</a></td>
        </tr>
    )
}
