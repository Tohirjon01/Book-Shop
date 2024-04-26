package uz.bookshop.service;

import jakarta.servlet.http.HttpServletRequest;
import uz.bookshop.dto.request.BookRequestDTO;
import uz.bookshop.dto.response.BookResponseDTO;

public interface BookService {

    BookResponseDTO createBook(BookRequestDTO bookRequestDTO , HttpServletRequest servletRequest);
    BookResponseDTO updateBook(BookRequestDTO bookRequestDTO , Long id, HttpServletRequest servletRequest);
    BookResponseDTO deleteBook(Long id , HttpServletRequest httpServletRequest);
    BookResponseDTO getAllBook(HttpServletRequest httpServletRequest);
    BookResponseDTO getBookById(Long id, HttpServletRequest httpServletRequest);

}
