package com.ztmp.controller;

import com.ztmp.authGuard.UserDetailsImpl;
import com.ztmp.helpers.UserHelper;
import com.ztmp.model.Book;
import com.ztmp.model.Pass;
import com.ztmp.model.Response;
import com.ztmp.model.User;
import com.ztmp.service.DashboardService;
import com.ztmp.service.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequestMapping("")
@RestController
@Slf4j
public class DashboardController {

    private final DashboardService dashboardService;
    private final UserHelper userService;

    @Autowired
    public DashboardController(DashboardService dashboardService, UserHelper userDetails) {
        this.dashboardService = dashboardService;
        this.userService = userDetails;
    }

    @GetMapping(value = "/dashboard/{id}", produces = "application/json")
    public ResponseEntity<Object> getBookById(@PathVariable("id") int id) {
        log.info("Book id: " + id);
        try {
            Optional<Book> book = dashboardService.getBookById(id);
            log.info((book.get().toString()));
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(404, "There is no book with id: " + id), new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/dashboard", produces = "application/json")
    public ResponseEntity<List<Object>> getAllBooks() {
        log.info("GET!");
        try {
            Optional<List<Book>> books = dashboardService.getBooksList();
            return new ResponseEntity<>(Collections.singletonList(books), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "/dashboard", produces = "application/json")
    public ResponseEntity<Object> postBook(@RequestBody Book book) {
        log.info(book.toString());
        try {
            if(book.getAuthor()== null || book.getTitle() == null || book.getYear() == 0){
                throw new NullPointerException();
            }
            Optional<Book> book_ = dashboardService.addBook(book);
            return new ResponseEntity<>(book_.get(), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new Response(400, "This book cannot be added"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/dashboard/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable("id") int id) {
        log.info("Book id: " + id);
        try {
            dashboardService.removeBooks(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            log.error(exception.toString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/dashboard/rola/{login}")
    public ResponseEntity getRole(@PathVariable String login){
        User user = userService.getUser(login);
        return ResponseEntity.ok(user.getRole());
    }

    @GetMapping(value = "/login")
    public ResponseEntity login(){
        return new ResponseEntity(HttpStatus.OK);
    }
}
