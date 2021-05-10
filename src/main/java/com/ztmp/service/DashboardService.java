package com.ztmp.service;

import com.ztmp.helpers.BookHelper;
import com.ztmp.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DashboardService {

    public final BookHelper repository;

    @Autowired
    public DashboardService(BookHelper repository) {
        this.repository = repository;
    }

    public Optional<List<Book>> getBooksList() {
        return Optional.ofNullable(repository.getBooks());
    }

    public Optional<Book> getBookById(int idBook) {
        return repository.getBooks().stream().filter(book -> idBook == book.getIdBook()).findAny();
    }

    public Optional<Book> addBook(Book book) {
        return Optional.ofNullable(repository.addBook(book));
    }

    public void removeBooks(int idBook) {
        repository.removeBooks(idBook);
    }
}
