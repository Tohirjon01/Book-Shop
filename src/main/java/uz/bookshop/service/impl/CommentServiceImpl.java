package uz.bookshop.service.impl;

import uz.bookshop.dto.request.CommentRequestDTO;
import uz.bookshop.dto.response.CommentResponseDTO;
import uz.bookshop.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    @Override
    public CommentResponseDTO addComment(CommentRequestDTO commentRequestDTO) {
        return null;
    }

    @Override
    public List<CommentResponseDTO> getAllComments() {
        return List.of();
    }

    @Override
    public CommentResponseDTO getOneCommentById(Long id) {
        return null;
    }

    @Override
    public CommentResponseDTO updateComment(Long id, CommentRequestDTO commentRequestDTO) {
        return null;
    }

    @Override
    public CommentResponseDTO deleteOneCommentById(Long id) {
        return null;
    }
}
