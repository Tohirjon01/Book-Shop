package uz.bookshop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uz.bookshop.Enums.RoleName;
import uz.bookshop.dto.request.BookRequestDTO;
import uz.bookshop.dto.response.BookResponseDTO;
import uz.bookshop.excetpion.BookException;
import uz.bookshop.excetpion.UserException;
import uz.bookshop.model.Books;
import uz.bookshop.model.Users;
import uz.bookshop.repository.BookRepository;
import uz.bookshop.repository.UserRepository;
import uz.bookshop.service.BookService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }


    @Override
    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {

        try {
            Users user = userRepository.findById(bookRequestDTO.getUsers().getId()).orElseThrow(() ->
                    new UserException("User not found"));
            if (user.getRoles().getName().equals(RoleName.AUTHOR.name())) {
                Books newBook = toEntity(bookRequestDTO, user);
                newBook = bookRepository.save(newBook);
                BookResponseDTO bookResponseDTO = toDto(newBook, user);
                log.info("New book created: ");
                return bookResponseDTO;
            } else {
                Books existingBook = bookRepository.getBooksByTitleAndUsersId(bookRequestDTO.getTitle(), user.getId());


                if (existingBook != null) {
                    existingBook.setQuantity(bookRequestDTO.getQuantity() + existingBook.getQuantity());
                    existingBook.setPrice(bookRequestDTO.getPrice() + existingBook.getPrice());
                    bookRepository.save(existingBook);
                    return toDto(existingBook, user);

                } else {
                    Books newBook = toEntity(bookRequestDTO, user);
                    newBook = bookRepository.save(newBook);
                    BookResponseDTO bookResponseDTO = toDto(newBook, user);
                    log.info("New book created: ");
                    return bookResponseDTO;
                }
            }
        }catch(UserException e){
                throw e;


            } catch(Exception e){
                throw new BookException("Book is not created: " + e.getMessage());
            }
        }



    @Override
    public BookResponseDTO updateBook(BookRequestDTO bookRequestDTO, Long id) {
        try {
            Books books  =bookRepository.findById(id).orElseThrow(() -> new BookException("Book not found"));
            Users users = userRepository.findById(books.getUsers().getId()).orElseThrow(() -> new UserException("User not found"));

            updateFromDto(bookRequestDTO,books);
            books = bookRepository.save(books);

            BookResponseDTO bookResponseDTO = toDto(books , users);
            bookResponseDTO.setId(users.getId());
            log.info("user updated" + bookResponseDTO);
            return bookResponseDTO;
        } catch (Exception e){
            throw new BookException(e.getMessage());
        }
    }

    @Override
    public BookResponseDTO deleteBook(Long id) {
        Books books = bookRepository.findById(id)
                .orElseThrow(() -> new BookException("Book not found"));

        bookRepository.delete(books);
        return null;
    }

    @Override
    public List<BookResponseDTO> getAllBook() {
        List<Books> books = bookRepository.findAll();

        return toDtoS(books);
    }

    @Override
    public BookResponseDTO getBookById(Long id) {
     Books books = bookRepository.findById(id).orElseThrow(() -> new BookException("Book id not found"));
        Users users = userRepository.findById(books.getId())
                .orElseThrow(() -> new UserException("User not found"));

        return toDto(books , users);

    }

    private Books toEntity(BookRequestDTO bookRequestDTO, Users user) {
        Books book = new Books();
        book.setTitle(bookRequestDTO.getTitle());
        book.setWriter(bookRequestDTO.getWriter());
        book.setQuantity(bookRequestDTO.getQuantity());
        book.setPrice(bookRequestDTO.getPrice());
        book.setUsers(user);
        return book;
    }

    private BookResponseDTO toDto(Books books, Users user) {
        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        bookResponseDTO.setId(books.getId());
        bookResponseDTO.setTitle(books.getTitle());
        bookResponseDTO.setWriter(books.getWriter());
        bookResponseDTO.setPrice(books.getPrice());
        bookResponseDTO.setQuantity(books.getQuantity());
        bookResponseDTO.setAuthor(user.getFirstName() + " " + user.getLastName());
        return bookResponseDTO;

    }
    private List<BookResponseDTO> toDtoS(List<Books> booksList) {
        List<BookResponseDTO> bookResponseDTOS = new ArrayList<>();
        for (int i = 0; i < booksList.size(); i++) {
            BookResponseDTO bookResponseDTO = new BookResponseDTO();
            Users users = userRepository.findById(booksList.get(i).getUsers().getId()).get();
            bookResponseDTO.setId(booksList.get(i).getId());
            bookResponseDTO.setTitle(booksList.get(i).getTitle());
            bookResponseDTO.setWriter(booksList.get(i).getWriter());
            bookResponseDTO.setPrice(booksList.get(i).getPrice());
            bookResponseDTO.setQuantity(booksList.get(i).getQuantity());
            bookResponseDTO.setAuthor(users.getFirstName() + " " + users.getLastName());
            bookResponseDTO.setId(users.getId());
            bookResponseDTOS.add(bookResponseDTO);

        }
        return bookResponseDTOS;
    }

    private void  updateFromDto (BookRequestDTO bookRequestDTO , Books books){

        if (bookRequestDTO.getTitle() != null){
            books.setTitle(bookRequestDTO.getTitle());
        }
        if (bookRequestDTO.getWriter() != null){
            books.setWriter(bookRequestDTO.getWriter());
        }
        if (bookRequestDTO.getPrice() != null){
            books.setPrice(bookRequestDTO.getPrice());
        }
        if (bookRequestDTO.getUsers() != null){
            books.setUsers(bookRequestDTO.getUsers());
        }


    }
}
