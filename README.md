# Library-System

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />


</head>
<body>

<h1>Library System API Documentation</h1>

<p><strong>Base URL:</strong> <code>http://localhost:8080</code></p>

<hr />

<h2>Tags & Descriptions</h2>
<ul>
  <li><strong>Book</strong>: Book Management API in Library System</li>
  <li><strong>Borrow and Return Book</strong>: Borrowed Record API in Library System</li>
  <li><strong>User</strong>: Member Registration APIs in Library System</li>
</ul>

<hr />

<h2>Endpoints</h2>

<div class="endpoint">
  <h3><span class="http-method POST">POST</span> /book/register</h3>
  <p><strong>Book Registration</strong> - Register a new book in the library.</p>

  <h4>Request Body (application/json):</h4>
  <pre><code>{
  "title": "string",
  "author": "string",
  "isbn": "string"
}</code></pre>

  <h4>Response (200 OK):</h4>
  <pre><code>{
  "ResponseBody": {
    "id": 0,
    "title": "string",
    "author": "string",
    "isbn": "string"
  },
  "Message": "string",
  "Code": "string",
  "NumberOfRecords": "string",
  "TotalPages": "string"
}</code></pre>
</div>

<div class="endpoint">
  <h3><span class="http-method GET">GET</span> /book/all-available-books</h3>
  <p><strong>Retrieve All Available Books</strong> - Retrieve all available books with pagination.</p>

  <h4>Query Parameters:</h4>
  <table>
    <thead><tr><th>Parameter</th><th>Type</th><th>Required</th><th>Description</th></tr></thead>
    <tbody>
      <tr><td>page</td><td>integer</td><td>Yes</td><td>Page number (starting from 0)</td></tr>
      <tr><td>size</td><td>integer</td><td>Yes</td><td>Number of records per page</td></tr>
    </tbody>
  </table>

  <h4>Response (200 OK):</h4>
  <pre><code>{
  "ResponseBody": [
    {
      "id": 0,
      "title": "string",
      "author": "string",
      "isbn": "string"
    }
  ],
  "Message": "string",
  "Code": "string",
  "NumberOfRecords": "string",
  "TotalPages": "string"
}</code></pre>
</div>

<div class="endpoint">
  <h3><span class="http-method POST">POST</span> /member/register</h3>
  <p><strong>Member Registration</strong> - Register a new member/user.</p>

  <h4>Request Body (application/json):</h4>
  <pre><code>{
  "name": "string",
  "email": "string"
}</code></pre>

  <h4>Response (200 OK):</h4>
  <pre><code>{
  "ResponseBody": {
    "id": 0,
    "name": "string",
    "email": "string"
  },
  "Message": "string",
  "Code": "string",
  "NumberOfRecords": "string",
  "TotalPages": "string"
}</code></pre>
</div>

<div class="endpoint">
  <h3><span class="http-method POST">POST</span> /borrowedRecord/borrowBook</h3>
  <p><strong>Borrow Book</strong> - Borrow a book by a registered user.</p>

  <h4>Query Parameters:</h4>
  <table>
    <thead><tr><th>Parameter</th><th>Type</th><th>Required</th><th>Description</th></tr></thead>
    <tbody>
      <tr><td>bookId</td><td>integer</td><td>Yes</td><td>ID of the book to borrow</td></tr>
      <tr><td>borrowerId</td><td>integer</td><td>Yes</td><td>ID of the user borrowing the book</td></tr>
    </tbody>
  </table>

  <h4>Response (200 OK):</h4>
  <pre><code>{
  "ResponseBody": {
    "id": 0,
    "BorrowedBooks": {
      "id": 0,
      "title": "string",
      "author": "string",
      "isbn": "string"
    },
    "BorrowerDetails": {
      "id": 0,
      "name": "string",
      "email": "string"
    },
    "BorrowDate": "2023-01-01T00:00:00Z",
    "returnDate": "2023-01-10T00:00:00Z"
  },
  "Message": "string",
  "Code": "string",
  "NumberOfRecords": "string",
  "TotalPages": "string"
}</code></pre>
</div>

<div class="endpoint">
  <h3><span class="http-method PUT">PUT</span> /borrowedRecord/returnBook/{id}</h3>
  <p><strong>Return Book</strong> - Return a borrowed book.</p>

  <h4>Path Parameter:</h4>
  <table>
    <thead><tr><th>Parameter</th><th>Type</th><th>Required</th><th>Description</th></tr></thead>
    <tbody>
      <tr><td>id</td><td>integer</td><td>Yes</td><td>ID of the borrowed record to return</td></tr>
    </tbody>
  </table>

  <h4>Response (200 OK):</h4>
  <pre><code>{
  "ResponseBody": {
    "id": 0,
    "BorrowedBooks": {
      "id": 0,
      "title": "string",
      "author": "string",
      "isbn": "string"
    },
    "BorrowerDetails": {
      "id": 0,
      "name": "string",
      "email": "string"
    },
    "BorrowDate": "2023-01-01T00:00:00Z",
    "returnDate": "2023-01-10T00:00:00Z"
  },
  "Message": "string",
  "Code": "string",
  "NumberOfRecords": "string",
  "TotalPages": "string"
}</code></pre>
</div>

<hr />
  <h2>Available API Endpoints</h2>
  <table>
    <thead>
      <tr>
        <th>Method</th>
        <th>Endpoint</th>
        <th>Description</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>POST</td>
        <td><code>localhost:8080/member/register</code></td>
        <td>Register a new user</td>
      </tr>
      <tr>
        <td>POST</td>
        <td><code>localhost:8080/book/register</code></td>
        <td>Register a new book</td>
      </tr>
      <tr>
        <td>GET</td>
        <td><code>localhost:8080/book/all-available-books?page=2&size=3</code></td>
        <td>Retrieve all books</td>
      </tr>
      <tr>
        <td>POST</td>
        <td><code>localhost:8080/borrowedRecord/borrowBook?bookId={id}&borrowerId={id}</code></td>
        <td>Borrow a book by providing userId and bookId</td>
      </tr>
      <tr>
        <td>PUT</td>
        <td><code>localhost:8080/borrowedRecord/returnBook/{id}</code></td>
        <td>Return a book by providing borrowed record ID</td>
      </tr>
    </tbody>
  </table>
<hr/>
<h2>Data Models</h2>

<h3>ER Diagram</h3>
<img width="920" height="616" alt="er" src="https://github.com/user-attachments/assets/6cd846cc-b45b-4450-827d-9598a96e5759" />


<h3>BookRegistrationRequest</h3>
<h3>BookRegistrationRequest</h3>
<pre><code>{
  "title": "string",
  "author": "string",
  "isbn": "string"
}</code></pre>

<h3>BookRegistrationResponse</h3>
<pre><code>{
  "id": 0,
  "title": "string",
  "author": "string",
  "isbn": "string"
}</code></pre>

<h3>UserRegistration</h3>
<pre><code>{
  "name": "string",
  "email": "string"
}</code></pre>

<h3>UserRegistrationResponse</h3>
<pre><code>{
  "id": 0,
  "name": "string",
  "email": "string"
}</code></pre>

<h3>BorrowedBookResponse</h3>
<pre><code>{
  "id": 0,
  "BorrowedBooks": {
    "id": 0,
    "title": "string",
    "author": "string",
    "isbn": "string"
  },
  "BorrowerDetails": {
    "id": 0,
    "name": "string",
    "email": "string"
  },
  "BorrowDate": "2023-01-01T00:00:00Z",
  "returnDate": "2023-01-10T00:00:00Z"
}</code></pre>

<hr />

  <h1>Library System API – Assumptions and Justifications</h1>

  <h2>General Assumptions</h2>
  <ul>
    <li><strong>Authentication:</strong> No authentication mechanism is implemented. The system assumes open access or external protection.</li>
    <li><strong>Email Uniqueness:</strong> User emails must be unique, while usernames (names) can be duplicated.</li>
    <li><strong>Error Handling:</strong> System uses a unified structure for success and error responses using defined codes and messages.</li>
    <li><strong>Date Format:</strong> All date and time fields follow the ISO 8601 standard in UTC.</li>
    <li><strong>Pagination:</strong> Endpoints that support pagination use <code>page</code> and <code>size</code> parameters, both required and zero-based.</li>
  </ul>

  <h2>Book and Borrowing Logic</h2>
  <ul>
    <li>Each book can only be borrowed by one user at a time.</li>
    <li>A user can borrow multiple books.</li>
    <li>A borrow record is uniquely identified by its ID and is used to return the corresponding book.</li>
  </ul>

  <h2>Response Metadata</h2>
  <ul>
    <li>Standard response includes <code>Message</code>, <code>Code</code>, <code>NumberOfRecords</code>, and <code>TotalPages</code>.</li>
    <li>Exact formats or allowed values for these fields are assumed to follow internal conventions.</li>
  </ul>

  <h2>User and Book Registration Constraints</h2>
  <ul>
    <li>All fields are mandatory in registration endpoints.</li>
    <li>No advanced field validation rules (like regex for email) are applied beyond uniqueness and presence.</li>
    <li>ISBN is assumed to be a unique identifier for books (though not explicitly validated in schema).</li>
  </ul>

  <h2>Justification for Choosing PostgreSQL</h2>
  <p>
    PostgreSQL is chosen as the database for the Library System because the application consists of only three tables—<code>User</code>, <code>Book</code>, and <code>BorrowedRecord</code>. These tables are well-structured and have clear relationships:
  </p>
  <ul>
    <li>Each book is associated with a user when borrowed.</li>
    <li>Borrowed records link users and books in a normalized way.</li>
  </ul>
  <p>
   Therefore, I use PostgreSQL because it supports foreign key constraints, indexing, and transactions, all of which are needed for this system.
  </p>

<hr/>
<h2>Notes</h2>
<ul>
  <li>All endpoints return a JSON response with metadata fields: <code>Message</code>, <code>Code</code>, <code>NumberOfRecords</code>, and <code>TotalPages</code>.</li>
  <li>Dates are in ISO 8601 format (<code>yyyy-MM-dd'T'HH:mm:ss'Z'</code>).</li>
</ul>

</body>
</html>
