const url = "http://localhost:8080"

export function getBooks() {
  return fetch(`${url}/books`).then(resp => resp.json());
}

export function getBookDetails(bookId) {
  return fetch(`${url}/books/${bookId}`).then(resp => resp.json());
}

export function putCopyHold(copyId) {
  return fetch(`${url}/copies/${copyId}`, {"method": "PUT"}).then(resp => resp.json());
}

export function buyCopy(copy) {
  console.log(copy);
  //return fetch(`${url}/copies/${copyId}`, {"method": "PUT"}).then(resp => resp.json());
}