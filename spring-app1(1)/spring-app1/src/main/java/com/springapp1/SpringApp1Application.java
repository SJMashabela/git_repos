package com.springapp1;

import com.springapp1.controller.Library;
import com.springapp1.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringApp1Application implements CommandLineRunner {
	@Autowired
	LibraryRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(SpringApp1Application.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Library lib = repository.findById("fdsefr343").get();
		System.out.println(lib.getAuthor());
		Library en = new Library();
		en.setAisle(123);
		en.setAuthor("James");
		en.setBook_name("AvtrAire");
		en.setIsbn("lkhs");
		en.setId("lkhs123");
//		repository.save(en);
		List<Library> allrecords = repository.findAll();
		for(Library item: allrecords){
			System.out.println(item.getBook_name());
		}
	}
}
