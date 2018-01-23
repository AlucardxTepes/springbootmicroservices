package com.alucard.commentstore.service;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import com.alucard.commentstore.model.CommentModel;

public interface CommentModelRepository extends CrudRepository<CommentModel, String>{

  List<CommentModel> findByPageId(String pageId);
  List<CommentModel> findByPageIdAndSpamIsTrue(String pageId);

}
