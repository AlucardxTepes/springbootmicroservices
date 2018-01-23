package com.alucard.commentstore.restapi;

import com.alucard.commentstore.model.CommentModel;
import com.alucard.commentstore.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class WriteController {

  @Autowired
  private CommentService service;

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.OK)
  public @ResponseBody
  String create(@RequestParam("comment") final String comment,
                @RequestParam("pageId") final String pageId,
                @RequestParam("emailAddress") final String email,
                @RequestParam("username") final String username) throws IOException {
    CommentModel model = new CommentModel();
    model.setPageId(pageId);
    model.setEmailAddress(email);
    model.setComment(comment);
    model.setUsername(username);
    String id = service.put(model);
    return id;
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable("id")String id) {
    service.delete(id);
  }
}
