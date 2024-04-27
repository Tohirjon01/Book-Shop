package uz.bookshop.service;

import uz.bookshop.dto.request.BookRequestDTO;
import uz.bookshop.dto.response.BookResponseDTO;

import java.util.List;

public interface BookService {

    BookResponseDTO createBook(BookRequestDTO bookRequestDTO );
    BookResponseDTO updateBook(BookRequestDTO bookRequestDTO , Long id );
    BookResponseDTO deleteBook(Long id  );
    List<BookResponseDTO> getAllBook();
    BookResponseDTO getBookById(Long id);



}
