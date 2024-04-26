package uz.bookshop.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import uz.bookshop.dto.request.BookRequestDTO;
import uz.bookshop.dto.response.BookResponseDTO;
import uz.bookshop.repository.BookRepository;
import uz.bookshop.repository.UserRepository;
import uz.bookshop.service.BookService;

import java.awt.print.Book;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        Book book = new Book();


        return null;
    }

    @Override
    public BookResponseDTO updateBook(BookRequestDTO bookRequestDTO, Long id) {
        return null;
    }

    @Override
    public BookResponseDTO deleteBook(Long id, HttpServletRequest httpServletRequest) {
        return null;
    }

    @Override
    public BookResponseDTO getAllBook(HttpServletRequest httpServletRequest) {
        return null;
    }

    @Override
    public BookResponseDTO getBookById(Long id, HttpServletRequest httpServletRequest) {
        return null;
    }



}
