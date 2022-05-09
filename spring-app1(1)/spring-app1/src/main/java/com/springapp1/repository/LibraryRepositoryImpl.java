package com.springapp1.repository;

import com.springapp1.controller.Library;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class LibraryRepositoryImpl implements LibraryRepositoryCustom{
    @Autowired
    LibraryRepository repository;
    @Override
    public List<Library> findAllByAuthor(String authorName) {
        List<Library> booksWithAuthor = new ArrayList<Library>();
        List<Library> books = repository.findAll();
        for(Library item:books){
            if(item.getAuthor().equalsIgnoreCase(authorName)){
                booksWithAuthor.add(item);
            }
        }
        return booksWithAuthor;
    }

}
