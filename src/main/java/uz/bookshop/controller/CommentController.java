package uz.bookshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bookshop.dto.request.CommentRequestDTO;
import uz.bookshop.dto.response.CommentResponseDTO;
import uz.bookshop.dto.response.ResponseDTO;
import uz.bookshop.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment/add")
    public ResponseEntity<CommentResponseDTO> createBook(@RequestBody CommentRequestDTO commentRequestDTO) {
        return ResponseEntity.ok(commentService.addComment(commentRequestDTO));
    }
    @GetMapping("/comment/getAll")
    public ResponseEntity<List<CommentResponseDTO>> getAllBooks() {
        return ResponseEntity.ok(commentService.getAllComments());
    }
    @DeleteMapping("/comment/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteBook(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.deleteOneCommentById(id));
    }
    @GetMapping("/comment/books/{id}")
    public ResponseEntity<List<CommentResponseDTO>> getAllCommentsBook(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getAllCommentByBookId(id));
    }
    @PutMapping("/comment/update")
    public ResponseEntity<CommentResponseDTO> updateBook( @RequestBody CommentRequestDTO commentRequestDTO ,
                                                   @RequestParam("comment_id") Long id) {
        return ResponseEntity.ok(commentService.updateComment(commentRequestDTO,id));

    }
    }

