package com.alucard.commentstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;


import org.springframework.util.StringUtils;
import java.io.IOException;

import java.util.Calendar;
import java.util.List;


import java.util.UUID;
import com.alucard.commentstore.model.CommentModel;
import de.codeboje.springbootbook.spamdetection.SpamDetector;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  private SpamDetector spamDetector;

  @Autowired
  private CommentModelRepository repository;

  @Override
  @Transactional
  public String put(CommentModel model) throws IOException {
    // Create ID if doesnt already exists
    if (StringUtils.isEmpty(model.getId())) {
      model.setId(UUID.randomUUID().toString());
    }

    // Check/mark as spam
    if (spamDetector.containsSpam(model.getUsername()) ||
            spamDetector.containsSpam(model.getEmailAddress()) ||
            spamDetector.containsSpam(model.getComment())) {
      model.setSpam(true);
    }

    final CommentModel dbModel = get(model.getId());
    if (dbModel != null) {
      // Exists in db. Update
      dbModel.setUsername(model.getUsername());
      dbModel.setComment(model.getComment());
      dbModel.setLastModificationDate(Calendar.getInstance());
      repository.save(dbModel);
    } else {
      // Create
      model.setCreationDate(Calendar.getInstance());
      model.setLastModificationDate(Calendar.getInstance());
      repository.save(model);
    }
    return model.getId();
  }

  @Override
  public List<CommentModel> list(String pageId) throws IOException {
    return repository.findByPageId(pageId);
  }

  @Override
  public CommentModel get(String id) {
    return repository.findOne(id);
  }

  @Override
  public List<CommentModel> listSpamComments(String pageId) throws IOException {
    return repository.findByPageIdAndSpamIsTrue(pageId);
  }

  @Override
  public void delete(String id) {
    repository.delete(id);
  }
}
