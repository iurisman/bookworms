const url = "http://localhost:8080"

export function getBooks() {
  return fetch(`${url}/books`).then(resp => resp.json());
}

export function getBookDetails(bookId) {
  return fetch(`${url}/books/${bookId}`).then(resp => resp.json());
}