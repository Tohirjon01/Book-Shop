package uz.bookshop.service;

import uz.bookshop.dto.request.CommentRequestDTO;
import uz.bookshop.dto.response.CommentResponseDTO;

import java.util.List;

public interface CommentService {
    CommentResponseDTO addComment(CommentRequestDTO commentRequestDTO);
    List<CommentResponseDTO> getAllComments();
    CommentResponseDTO getOneCommentById(Long id);
    CommentResponseDTO updateComment(Long id, CommentRequestDTO commentRequestDTO);
    CommentResponseDTO deleteOneCommentById(Long id);

}
