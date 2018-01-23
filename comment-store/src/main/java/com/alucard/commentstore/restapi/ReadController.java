package com.alucard.commentstore.restapi;

import com.alucard.commentstore.model.CommentModel;
import com.alucard.commentstore.service.CommentService;
import de.codeboje.springbootbook.commons.CommentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@RestController
public class ReadController {
  private static final Logger LOGGER = LoggerFactory.getLogger(ReadController.class);

  @Autowired
  private CommentService service;

  @GetMapping("/list/{id}")
  public List<CommentDTO> getComments(@PathVariable("id") String pageId) throws IOException {
    List<CommentModel> r = service.list(pageId);
    if (r.isEmpty()) {
      throw new FileNotFoundException("/list/" + pageId);
    }
    return transformToDTO(r);
  }

  @GetMapping("/listspam/{id}")
  public List<CommentDTO> getSpamComments(@PathVariable("id") String pageId) throws IOException {
    List<CommentModel> r = service.listSpamComments(pageId);
    if (r.isEmpty()) {
      throw new FileNotFoundException("/listspam/" + pageId);
    }
    return transformToDTO(r);
  }

  @ExceptionHandler(FileNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public void handle404(Exception e, Locale l) {
    LOGGER.debug("Resource not found {}", e.getMessage());
  }

  private List<CommentDTO> transformToDTO(List<CommentModel> r) {
    List<CommentDTO> result = new LinkedList<>();

    for (CommentModel m : r) {
      CommentDTO dto = new CommentDTO();
      dto.setId(m.getId());
      dto.setUsername(m.getUsername());
      dto.setEmailAddress(m.getEmailAddress());
      dto.setComment(m.getComment());
      dto.setCreated(m.getCreationDate());
      result.add(dto);
    }
    return result;
  }
}
