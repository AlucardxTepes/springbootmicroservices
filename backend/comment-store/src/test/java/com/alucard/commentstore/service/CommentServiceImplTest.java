package com.alucard.commentstore.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;
import java.util.List;
import com.alucard.commentstore.model.CommentModel;


import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceImplTest {

  @Autowired
  private CommentModelRepository repository;

  @Autowired
  private CommentService service;

  private CommentModel model;

  @Before
  public void setUp() throws Exception {
    model = new CommentModel();
    model.setUsername("testuser");
    model.setId("dqe345e456rf34rw");
    model.setPageId("product666");
    model.setEmailAddress("Shepard@normandy.com");
    model.setComment("I am the comment");
    repository.deleteAll();
  }

  @Test
  public void testListNotFound() throws IOException {
    service.put(model);
    List<CommentModel> list = service.list("klk");
    assertTrue(list.isEmpty());
  }
}
