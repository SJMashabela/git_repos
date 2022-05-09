package com.springapp1.repository;

import com.springapp1.controller.Library;

import java.util.List;

public interface LibraryRepositoryCustom {
    List<Library> findAllByAuthor(String authorName);
}
