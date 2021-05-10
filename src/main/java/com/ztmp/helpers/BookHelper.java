package com.ztmp.helpers;

import com.ztmp.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class BookHelper {

    public ArrayList<Book> books = null;
    public static int minId = 9;

    public BookHelper() {
        books = new ArrayList<>();
        books.add(new Book(1, "Książka 1", "Jan Kowalski", 2021));
        books.add(new Book(2, "Książka 2", "Paweł Kowalski", 2020));
        books.add(new Book(3, "Książka 3", "Jacek Kowalski", 2022));
        books.add(new Book(4, "Książka 4", "Krzysiek Kowalski", 2019));
        books.add(new Book(5, "Książka 5", "Zosia Kowalska", 2002));
        books.add(new Book(6, "Książka 6", "Katarzyna Kowalska", 2018));
        books.add(new Book(7, "Książka 7", "Alicja Kowalska", 2020));
        books.add(new Book(8, "Książka 8", "Maria Kowalska", 2021));
    }

    public Book addBook(Book book) {
        book.setIdBook(minId++);
        books.add(book);
        return book;
    }

    public void removeBooks(int idBook) {
        Optional<Book> b = books.stream().filter(book -> idBook == book.getIdBook()).findAny();
        books.remove(b.get());
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
