package com.springapp1.repository;

import com.springapp1.controller.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library,String> {//allows us to access db entries without sql code

}
