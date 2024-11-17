LibraryBookManagement

Description
This project provides a Library Book Management System that manages books and borrowers in a library. It includes functionality to add, update, delete books, and track borrowed books by borrowers. The system automatically updates book quantities when books are borrowed. If the quantity reaches zero, the book is deleted from the system.

Features

Book Management
1. Get All Books: Retrieve a list of all available books.
2. Get Book by ID: Retrieve a book by its unique ID.
3. Add New Book: Add a new book to the library collection.
4. Update Book: Modify the details of an existing book (e.g., title, author, quantity).
5. Delete Book: Delete a book by its ID.
6. Search Books: Search for books by title, author, or genre.

Borrower Management
1. Add New Borrower: Add a new borrower to the system.
2. Get Borrower by ID: Retrieve borrower details by their unique ID.
3. Update Borrower: Modify a borrower's details (e.g., name, contact number).
4. Delete Borrower: Delete a borrower from the system.
5. Borrow a Book: When a borrower borrows a book, the book's quantity is decremented. If the quantity reaches zero, the book is removed from the system.

Book Borrowing Logic
- When a borrower books a book, the book quantity is reduced by 1.
- If the book quantity becomes zero, it is deleted from the system.

Installation

Prerequisites
Ensure that you have the following installed on your machine:
- Java 11 or higher
- Spring Boot (version 2.5.x or higher)
- MySQL Database
- Maven or Gradle

Setting up the Project

1. Clone the repository:
    git clone https://github.com/yourusername/LibraryBookManagement.git

2. Navigate to the project directory:
    cd LibraryBookManagement

3. Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).

4. Configure application.properties with your database connection settings:
    spring.datasource.url=jdbc:mysql://localhost:3306/library
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

5. Run the application using Maven or Gradle:
    ./mvnw spring-boot:run

6. Access the application at http://localhost:8080/.

API Endpoints

Book Endpoints
1. Get All Books: GET /api/getAllBooks
2. Get Book by ID: GET /api/getBookById/{BookId}
3. Add New Book: POST /api/addBook
4. Update Book: POST /api/updateBook/{BookId}
5. Delete Book by ID: DELETE /api/deleteBookById/{BookId}
6. Search Books by Title: GET /api/search_title?title={title}
7. Search Books by Author: GET /api/search_author?author={author}
8. Search Books by Genre: GET /api/search_genre?genre={genre}

Borrower Endpoints
1. Add Borrower: POST /api/addBorrower
2. Get Borrower by ID: GET /api/getBorrowerById/{MembershipId}
3. Update Borrower: POST /api/updateBorrower/{MembershipId}
4. Delete Borrower: DELETE /api/deleteBorrowerById/{MembershipId}
5. Borrow Book: POST /api/borrowBook/{MembershipId}/{BookId}

Example Usage

Borrower Management
- Add Borrower:
    POST /api/addBorrower
    {
      "Name": "John Doe",
      "Contact": 1234567890,
      "borrowedBookIds": []
    }
- Update Borrower:
    POST /api/updateBorrower/1
    {
      "Name": "John Smith",
      "Contact": 9876543210
    }

- Delete Borrower:
    DELETE /api/deleteBorrowerById/1

Book Borrowing Process
- Borrow a Book:
    - When a borrower borrows a book, the quantity of the book is reduced by 1.
    - If the quantity becomes zero, it is deleted.
    POST /api/borrowBook/1/2  # Borrower with ID 1 borrows book with ID 2

Database Schema

Book Table
- bookId (Primary Key)
- title (String)
- author (String)
- isbn (String)
- genre (String)
- quantity (Long)

Borrower Table
- MembershipId (Primary Key)
- Name (String)
- Contact (Long)
- borrowedBookIds (List<Long>)

Additional Notes
- Ensure that your database is running and accessible before starting the application.
- You can add additional business logic for other library operations as needed, such as sending notifications when a book is borrowed.
- This system does not handle user authentication. You may consider adding Spring Security or other authentication methods if necessary.

License
This project is licensed under the MIT License - see the LICENSE file for details.
