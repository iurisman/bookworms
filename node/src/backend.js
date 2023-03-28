const url = "http://localhost:8080"

export function getBooks() {
  return fetch(`${url}/books`).then(resp => resp.json());
};