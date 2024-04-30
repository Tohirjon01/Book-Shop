package uz.bookshop.service;

import uz.bookshop.dto.request.CommentRequestDTO;
import uz.bookshop.dto.response.CommentResponseDTO;
import uz.bookshop.dto.response.ResponseDTO;

import java.util.List;

public interface CommentService {
    CommentResponseDTO addComment(CommentRequestDTO commentRequestDTO);
    CommentResponseDTO updateComment(CommentRequestDTO commentRequestDTO , Long id);
    List<CommentResponseDTO> getAllComments();
    List<CommentResponseDTO> getAllCommentByBookId(Long id);
    ResponseDTO deleteOneCommentById(Long id);

}
