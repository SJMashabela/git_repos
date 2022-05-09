package com.springapp1.service;

import com.springapp1.controller.Library;
import com.springapp1.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryService {
    @Autowired
    LibraryRepository repository;
    public String buildId(String isbn, int aisle){
        if(isbn.startsWith("Z")){
            return "OLD" +isbn + aisle;
        }
        return isbn + aisle;
    }
    public boolean checkBookAlreadyExist(String id){
        Optional<Library> lib = repository.findById(id);
        if(lib.isPresent())
            return true;
        else
            return false;

    }
}
