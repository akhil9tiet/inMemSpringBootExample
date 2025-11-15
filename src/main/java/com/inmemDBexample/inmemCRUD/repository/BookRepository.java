package com.inmemDBexample.inmemCRUD.repository;

import com.inmemDBexample.inmemCRUD.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

