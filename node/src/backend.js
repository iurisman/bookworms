/** We're proxying API calls via Node, rather than going directly.
  * This help avoiding setting up CORS on the server. For that to work,
  * the fetch URIs must not contain server, which fetch() assumes to
  * be the same server where the requesting page came from.
  */
const url = ""

export function getBooks() {
  return fetch(`${url}/books`).then(resp => resp.json());
}

export function getBookDetails(bookId) {
  return fetch(`${url}/books/${bookId}`).then(resp => resp.json());
}

export function putCopyHold(copyId) {
  return fetch(`${url}/copies/${copyId}`, {"method": "PUT"}).then(resp => resp.json());
}

/** Emulate purchase by making the copy unavailable. It will no longer be shown */
export function buyCopy(copy) {
  copy.available = false;
  fetch(
    `${url}/copies`,
    {
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(copy),
      method: "PUT"
    });
}