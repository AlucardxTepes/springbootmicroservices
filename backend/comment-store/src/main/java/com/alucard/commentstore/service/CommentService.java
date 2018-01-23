package com.alucard.commentstore.service;
import com.alucard.commentstore.model.CommentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface CommentService {

  String put(CommentModel model) throws IOException;
  Page<CommentModel> list(Pageable pageable);
  List<CommentModel> list(String pageId) throws IOException;
  CommentModel get(String id);
  List<CommentModel> listSpamComments(String pageId) throws IOException;
  void delete(String id);

}
