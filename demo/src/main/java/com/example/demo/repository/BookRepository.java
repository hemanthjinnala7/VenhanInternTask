package com.example.demo.repository;

import com.example.demo.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {

    List<BookModel> findByTitleContaining(String title);

    List<BookModel>findByAuthorContaining(String Author);

    List<BookModel>findByGenreContaining(String Genre);



}