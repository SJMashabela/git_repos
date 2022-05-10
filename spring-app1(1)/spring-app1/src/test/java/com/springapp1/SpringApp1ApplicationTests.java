package com.springapp1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springapp1.controller.AddResponse;
import com.springapp1.controller.Library;
import com.springapp1.controller.LibraryController;
import com.springapp1.repository.LibraryRepository;
import com.springapp1.service.LibraryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SpringApp1ApplicationTests {

	@Autowired
	LibraryController con;
	@MockBean
	LibraryRepository repository;
	@MockBean
	LibraryService libraryService;
	@Autowired
	private MockMvc mockMvc;
	@Test
	void contextLoads() {

	}
	@Test
	public void checkBuildIdLogic(){
		LibraryService lib = new LibraryService();
		String id = lib.buildId("ZMan",24);
		assertEquals(id,"OLDZMan24");
		String id1 = lib.buildId("Man",24);
		assertEquals(id1, "Man24");
	}
	@Test
	public void addBookTest(){


		//use Mockito to mock data from dependencies
		Library lib = buildLibrary();
		when(libraryService.buildId(lib.getIsbn(),lib.getAisle())).thenReturn(lib.getId());
		when(libraryService.checkBookAlreadyExist(lib.getId())).thenReturn(false); // where the book is not yet created
		when(repository.save(any())).thenReturn(lib);
		ResponseEntity response = con.addBookImplementation(buildLibrary());
		System.out.println(response.getStatusCode());
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
		AddResponse ad = (AddResponse) response.getBody();
		ad.getId();
		assertEquals(lib.getId(), ad.getId());
		assertEquals(ad.getMsg(),"Success. Book is added");
	}
	// mockMvc approach - allows you to call a service without running the server
	@Test
	public void addBookControllerTest(){
		Library lib = buildLibrary();
		ObjectMapper map = new ObjectMapper();
		try {
			String jsonString = map.writeValueAsString(lib);

		when(libraryService.buildId(lib.getIsbn(),lib.getAisle())).thenReturn(lib.getId());
		when(libraryService.checkBookAlreadyExist(lib.getId())).thenReturn(false); // where the book is not yet created
		when(repository.save(any())).thenReturn(lib);
		this.mockMvc.perform(post("/addBook")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonString))
				.andExpect(status().isCreated()).andDo(print())
				.andExpect(jsonPath("$.id").value(lib.getId()));
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Test
	public void getBookByAuthorTest(){
		List<Library> li = new ArrayList<Library>();
		li.add(buildLibrary());
		li.add(buildLibrary());

		try{
			when(repository.findAllByAuthor(any())).thenReturn(li);
			this.mockMvc.perform(get("/getBooks/author").param("authorName", "Sefako Mash"))
					.andDo(print()).andExpect(status().isOk())
					.andExpect((ResultMatcher) jsonPath("$.length()",is(2)))
					.andExpect(jsonPath("$.[0].id").value("sef322"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Test
	public void updateBookTest(){
		try{
			Library lib = buildLibrary();
			ObjectMapper map = new ObjectMapper();
			String jsonString = map.writeValueAsString(updateLibrary());
			when(libraryService.getBookById(any())).thenReturn(buildLibrary());

				this.mockMvc.perform(put("/updateBook/"+lib.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(jsonString))
						.andDo(print())
						.andExpect(status().isOk())
						.andExpect(content().json("{\"book_name\":\"SpringBoot\",\"id\":\"sef322\",\"isbn\":\"sef\",\"aisle\":22,\"author\":\"Sefo Mashab\"}"));
		}catch (Exception e){
			System.out.println(e);
		}
	}
	@Test
	public void deleteBookControllerTest(){
		try{
			when(libraryService.getBookById(any())).thenReturn(buildLibrary());
			doNothing().when(repository).delete(buildLibrary());
			this.mockMvc.perform(delete("/deleteBook").contentType(MediaType.APPLICATION_JSON)
					.content("{\"id\" : \"sef22\"}")).andDo(print())
					.andExpect(status().isCreated())
					.andExpect(content().string("Book is deleted"));

		}catch (Exception e){
			System.out.println(e);
		}
	}
	// create a Library object for mock data
	public Library updateLibrary(){
		Library lib = new Library();
		lib.setAisle(22);
		lib.setBook_name("SpringBoot");
		lib.setIsbn("Efa");
		lib.setAuthor("Sefo Mashab");
		lib.setId("sef22");
		return  lib;
	}
	public Library buildLibrary(){
		Library lib = new Library();
		lib.setAisle(322);
		lib.setBook_name("Spring");
		lib.setIsbn("sef");
		lib.setAuthor("Sefako Mash");
		lib.setId("sef322");
		return  lib;
	}
}
