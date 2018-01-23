package com.alucard.commentstore.service;
import com.alucard.commentstore.model.CommentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentModelRepository extends CrudRepository<CommentModel, String>{

  List<CommentModel> findByPageId(String pageId);
  List<CommentModel> findByPageIdAndSpamIsTrue(String pageId);
  Page<CommentModel> findAll(Pageable pageable);

}
