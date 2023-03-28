import { useParams } from "react-router-dom";

export function BookDetails({book}) {
  let params = useParams();
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
            {params.id}
        </tbody>
      </table>
    </div>
  );
}
