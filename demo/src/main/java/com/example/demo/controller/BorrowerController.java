package com.example.demo.controller;


import com.example.demo.model.BookModel;
import com.example.demo.model.BorrowerModel;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.awt.print.Book;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")

public class BorrowerController {

    @Autowired
    BorrowerRepository borrowerRepository;

    @Autowired
    BookRepository bookRepository;

    //get all borrowers list from db
    //http://localhost:8080/api/getAllBorrowers
    @GetMapping("/getAllBorrowers")
    public ResponseEntity<List<BorrowerModel>> getAllBorrowers() {
        try {
            List<BorrowerModel> BorrowerList = new ArrayList<>();
            borrowerRepository.findAll().forEach(BorrowerList::add);
            if (BorrowerList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(BorrowerList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Adding a Borrower
    //http://localhost:8080/api/addBorrower
    @PostMapping("/addBorrower")
    public ResponseEntity<BorrowerModel> addBorrower(@RequestBody BorrowerModel borrowerModel) {
        try {
            BorrowerModel borrowerObj = borrowerRepository.save(borrowerModel);
            return new ResponseEntity<>(borrowerObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //deleting a borrower by id
    //http://localhost:8080/api/deleteBorrowerById/
    @DeleteMapping("deleteBorrowerById/{BorrowerId}")
    public ResponseEntity<HttpStatus> deleteBorrowerById(@PathVariable Long BorrowerId) {
        try {
            borrowerRepository.deleteById(BorrowerId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    //adding a book_id to borrowers list
    //after succesfully adding we should decreasing the quantity by 1
    //if the quantity of a book is 0 then we will delete the book from db
    //http://localhost:8080/api/borrowingBookByBrwId/
    @PutMapping("borrowingBookByBrwId/{MembershipId}/{BookId}")
    public ResponseEntity<HttpStatus> Booking(@PathVariable Long MembershipId, @PathVariable Long BookId) {

        try {
            Optional<BorrowerModel> BorrowerObj = borrowerRepository.findById(MembershipId);
            Optional<BookModel> BookObj = bookRepository.findById(BookId);
            if (BorrowerObj.isPresent() && BookObj.isPresent()) {
                BorrowerModel borrower = BorrowerObj.get();
                borrower.getBorrowedBookIds().add(BookId);
                BookModel book = BookObj.get();
                book.setQuantity(book.getQuantity() - 1);
                bookRepository.save(book);
                if (book.getQuantity() == 0) {
                    bookRepository.delete(book);
                }
                borrowerRepository.save(borrower);
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return null;
    }

    //updating the borrowers info through memid
    //http://localhost:8080/api/updateBorrower/
    @PutMapping("updateBorrower/{MembershipId}")
    public ResponseEntity<HttpStatus> updateBorrower(@PathVariable Long MembershipId, @RequestBody BorrowerModel borrowerDetails) {
        try {
            Optional<BorrowerModel> BorrowerObj = borrowerRepository.findById(MembershipId);
            if (BorrowerObj.isPresent()) {
                BorrowerModel updatedBorrower = BorrowerObj.get();
                if (borrowerDetails.getContact() != null) {
                    updatedBorrower.setContact(borrowerDetails.getContact());
                }
                if (borrowerDetails.getName() != null) {
                    updatedBorrower.setName(borrowerDetails.getName());
                }
                if (borrowerDetails.getBorrowedBookIds() != null) {
                    updatedBorrower.setBorrowedBookIds(borrowerDetails.getBorrowedBookIds());
                }
                borrowerRepository.save(updatedBorrower);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }





}








}
