package uz.bookshop.service.impl;

import org.springframework.stereotype.Service;
import uz.bookshop.dto.request.CommentRequestDTO;
import uz.bookshop.dto.response.CommentResponseDTO;
import uz.bookshop.dto.response.ResponseDTO;
import uz.bookshop.excetpion.BookException;
import uz.bookshop.excetpion.CommentException;
import uz.bookshop.model.Books;
import uz.bookshop.model.Comment;
import uz.bookshop.model.Users;
import uz.bookshop.repository.BookRepository;
import uz.bookshop.repository.CommentRepository;
import uz.bookshop.repository.UserRepository;
import uz.bookshop.service.CommentService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CommentResponseDTO addComment(CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        try {
            Users users = userRepository.findById(commentRequestDTO.getUsers().getId()).orElseThrow(() ->
                    new CommentException("User Not Found"));
            Books book = bookRepository.findById(commentRequestDTO.getBooks().getId()).orElseThrow(() -> new BookException("Book not found"));
            LocalDateTime localDateTime = LocalDateTime.now();

            Comment comment = toEntity(commentRequestDTO, users);
            comment.setText(commentRequestDTO.getText());
            if (book != null) {
                commentResponseDTO.setTitle(book.getTitle());
                commentResponseDTO.setWriter(book.getWriter());
            }
            comment.setDate(localDateTime);
            comment.setBooks(book);
            commentRepository.save(comment);
            return toDto(comment, users);

        } catch (Exception e) {
            throw new CommentException(e.getMessage());
        }

    }

    @Override
    public CommentResponseDTO updateComment(CommentRequestDTO commentRequestDTO ,Long id) {
        try {
            Comment existComment = commentRepository.findById(id)
                    .orElseThrow(() -> new CommentException("Comment not found"));

            if (!existComment.getUsers().getId().equals(commentRequestDTO.getUsers().getId())) {
                throw new CommentException("Only the owner of the comment can update it");
            }
            existComment.setText(commentRequestDTO.getText());
            Comment updateComment = commentRepository.save(existComment);
            return toDto(updateComment, updateComment.getUsers());
        }catch (Exception e){
            throw new CommentException(" Comment " +  e.getMessage());
        }
    }


    @Override
    public List<CommentResponseDTO> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return toDtos(comments);
    }

    @Override
    public List<CommentResponseDTO> getAllCommentByBookId(Long id) {
        try {
            List<Comment> comments = commentRepository.findCommentByBooksId(id);
            return toDtos(comments);
        } catch (Exception e) {
            throw new CommentException(e.getMessage());
        }
    }


        @Override
        public ResponseDTO deleteOneCommentById (Long id){
            try {
                Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentException("Comment Not Found"));
                ResponseDTO responseDTO = new ResponseDTO();
                commentRepository.deleteById(comment.getId());
                responseDTO.setMessage("Successfully deleted comment");
                return responseDTO;
            } catch (Exception e) {
                throw new CommentException(e.getMessage());
            }
        }

        private Comment toEntity (CommentRequestDTO commentRequestDTO, Users users){
            Comment comment = new Comment();
            Books books = new Books();
            comment.setText(commentRequestDTO.getText());
            comment.setDate(LocalDateTime.now());
            comment.setUsers(users);
            comment.setBooks(books);
            return comment;
        }

        private CommentResponseDTO toDto (Comment comment, Users users){
            CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
            commentResponseDTO.setId(comment.getId());
            commentResponseDTO.setText(comment.getText());
            commentResponseDTO.setTitle(comment.getBooks().getTitle());
            commentResponseDTO.setWriter(comment.getBooks().getWriter());
            commentResponseDTO.setAuthor(users.getFirstName() + " " + users.getLastName());
            return commentResponseDTO;
        }


        private List<CommentResponseDTO> toDtos (List < Comment > commentList) {
            List<CommentResponseDTO> commentResponseDTOS = new ArrayList<>();
            for (Comment comment : commentList) {
                CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
                Users users = comment.getUsers();
                commentResponseDTO.setId(comment.getId());
                commentResponseDTO.setText(comment.getText());
                commentResponseDTO.setTitle(comment.getBooks().getTitle());
                commentResponseDTO.setWriter(comment.getBooks().getWriter());


                commentResponseDTO.setAuthor(users.getFirstName() + " " + users.getLastName());
                commentResponseDTOS.add(commentResponseDTO);


            }
            return commentResponseDTOS;
        }

    }
