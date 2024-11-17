package com.example.demo.controller;
import com.example.demo.repository.BookRepository;
import com.example.demo.model.BookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")

public class BookController {

    @Autowired
    BookRepository bookRepository;

    //GET ALL BOOKS FROM THE DB
    //http://localhost:8080/api/getAllBooks
    @GetMapping("/getAllBooks")
    public ResponseEntity<List<BookModel>> getAllBooks()
    {
        try
        {
            List<BookModel> cartList = new ArrayList<>();
            bookRepository.findAll().forEach(cartList::add);

            if (cartList.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(cartList, HttpStatus.OK);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //GET BOOK BY ID
    //http://localhost:8080/api/getBookById/
    @GetMapping("/getBookById/{BookId}")
    public ResponseEntity<BookModel> getBookById(@PathVariable Long BookId)
    {
        Optional<BookModel> BookObj = bookRepository.findById(BookId);
        if (BookObj.isPresent())
        {
            return new ResponseEntity<>(BookObj.get(), HttpStatus.OK);
        } else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //ADDING A BOOK
    //http://localhost:8080/api/addBook
    @PostMapping("/addBook")
    public ResponseEntity<BookModel> addBook(@RequestBody BookModel bookModel)
    {
        try {
            BookModel bookObj = bookRepository.save(bookModel);
            return new ResponseEntity<>(bookObj, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //UPDATING A BOOK
    //http://localhost:8080/api/updateBook/
    @PostMapping("updateBook/{BookId}")
    public ResponseEntity<BookModel> updateBook(@PathVariable Long BookId, @RequestBody BookModel bookModel)
    {
        try
        {
            Optional<BookModel> existingBookData = bookRepository.findById(BookId);
            if (existingBookData.isPresent())
            {
                BookModel updatedBookData = existingBookData.get();
                if (bookModel.getTitle() != null)
                {
                    updatedBookData.setTitle(bookModel.getTitle());
                }
                if (bookModel.getAuthor() != null)
                {
                    updatedBookData.setAuthor(bookModel.getAuthor());
                }
                if (bookModel.getQuantity() != null)
                {
                    updatedBookData.setQuantity(bookModel.getQuantity());
                }
                BookModel updatedBook = bookRepository.save(updatedBookData);
                return new ResponseEntity<>(updatedBook, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //deleting  a book by id
    //http://localhost:8080/api/deleteBookById/
    @DeleteMapping("deleteBookById/{BookId}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable Long BookId)
    {
        try
        {
            bookRepository.deleteById(BookId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //searching the db by  a title
    //http://localhost:8080/api/search_title/?tile=
    @GetMapping("/search_title")
    public ResponseEntity<List<BookModel>> searchBooksByTitle(@RequestParam String title) {
        List<BookModel> books = bookRepository.findByTitleContaining(title);
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    //searching the db by author
    //http://localhost:8080/api/search_author/?author=
    @GetMapping("/search_author")
    public ResponseEntity<List<BookModel>> searchBookByAuthor(@RequestParam String author) {
        List<BookModel> books = bookRepository.findByAuthorContaining(author);
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    //searching the db by genre
    //http://localhost:8080/api/search_genre/?genre=
    @GetMapping("/search_genre")
    public ResponseEntity<List<BookModel>> searchBookByGenre(@RequestParam String genre) {
        List<BookModel> books = bookRepository.findByAuthorContaining(genre);
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }



    }










