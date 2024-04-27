package uz.bookshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bookshop.dto.request.BookRequestDTO;
import uz.bookshop.dto.response.BookResponseDTO;
import uz.bookshop.service.BookService;

@RestController
@RequestMapping("/api")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book/create")
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookRequestDTO bookRequestDTO) {
        return ResponseEntity.ok(bookService.createBook(bookRequestDTO));
    }
    @DeleteMapping("book/delete/{id}")
    public ResponseEntity<BookResponseDTO> deleteBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.deleteBook(id ));
    }
    @GetMapping("/book/getOne/{id}")
    public ResponseEntity<?> getOneBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id  ));
    }
    @GetMapping("/book/getAll")
    public ResponseEntity<?> getAllBook() {
        return  ResponseEntity.ok(bookService.getAllBook());
    }
    @PutMapping("/book/update")
    public ResponseEntity<BookResponseDTO> updateBook(@RequestBody BookRequestDTO bookRequestDTO,
                                                      @RequestParam("bookId") Long id) {
        return ResponseEntity.ok(bookService.updateBook(bookRequestDTO,id));
    }
}
