package com.springapp1.controller;

import com.springapp1.repository.LibraryRepository;
import com.springapp1.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class LibraryController {
    @Autowired
    LibraryRepository repository;
    @Autowired
    LibraryService libraryService;
    private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);
    @PostMapping("/addBook")
    public ResponseEntity addBookImplementation(@RequestBody Library library){


        String id = library.getIsbn() + library.getAisle();
        AddResponse ad = new AddResponse();
       if (!libraryService.checkBookAlreadyExist(id)) {
            logger.info("Book does not exist. Create one");
           library.setId(id);
           repository.save(library);
           HttpHeaders headers = new HttpHeaders();
           headers.add("unique", id);
           ad.setMsg("Success. Book is added");
           ad.setId(id);
           return new ResponseEntity<>(ad, headers, HttpStatus.CREATED);
       }else{
           logger.info("Book exist. Skip creation");
           ad.setMsg("Book already exists");
           ad.setId(id);
           return new ResponseEntity<>(ad,  HttpStatus.ACCEPTED);
       }
    }
    @GetMapping("/getBooks/{id}")
    public Library getBookById(@PathVariable(value="id") String id){
        try {
            logger.info("Id is found. Get the book");
            Library lib = repository.findById(id).get();
            return lib;
        }catch (Exception e){
            logger.info("The id is not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("getBooks/author")
    public List<Library> getBookByAuthorName(@RequestParam(value="authorName") String authorName){
          logger.info("use author name to search for the book in the database");
          return  repository.findAllByAuthor(authorName);
    }
    @PutMapping("/updateBook/{id}")
    public ResponseEntity<Library> updateBook(@PathVariable(value="id") String id, @RequestBody Library library){

        try{
            logger.info("Book is found. Update using request body data");
            Library existingBook = repository.findById(id).get();
            existingBook.setAisle(library.getAisle());
            existingBook.setAuthor(library.getAuthor());
            existingBook.setBook_name(library.getBook_name());
            repository.save(existingBook);
            return new ResponseEntity<Library>(existingBook, HttpStatus.OK);
        }catch (Exception e){
            logger.info("Book not found. Display http status");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/deleteBook")
    public ResponseEntity<String> deleteBookById(@RequestBody Library library){
        try{
            logger.info("Use request body id to find and delete book.");
            Library libdelete =repository.findById(library.getId()).get();
             repository.delete(libdelete);
             return new ResponseEntity<>("Book is deleted",HttpStatus.CREATED);
        }catch (Exception e){
            logger.info("Id is not found. Display http status");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("getAllBooks")
    public List<Library> getAllBooks(){
        logger.info("Display all users in the database.");
        return repository.findAll();
    }
}
