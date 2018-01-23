package com.alucard.commentstore.restapi;

import com.alucard.commentstore.model.CommentModel;
import com.alucard.commentstore.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class WriteControllerTest {

  @Autowired
  private WebApplicationContext context;

  @Autowired
  private CommentService service;

  @Autowired
  private MockMvc mockMvc;


  @Test
  public void testPost() throws Exception {
    // Create dummy comment
    CommentModel model = setupDummyModel();

    // Perform POST request
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/create")
        .param("comment", model.getComment())
        .param("pageId", model.getPageId())
        .param("emailAddress", model.getEmailAddress())
        .param("username", model.getUsername()))
        .andExpect(status().is(200))
        .andReturn();

    // Find created comment in DB
    String id = result.getResponse().getContentAsString();
    CommentModel dbModel = service.get(id);
    assertNotNull(dbModel);
    assertEquals(model.getComment(), dbModel.getComment());
    assertEquals(model.getPageId(), dbModel.getPageId());
    assertEquals(model.getUsername(), dbModel.getUsername());
    assertEquals(model.getEmailAddress(), dbModel.getEmailAddress());
    assertNotNull(dbModel.getLastModificationDate());
    assertNotNull(dbModel.getCreationDate());
    assertFalse(model.isSpam());
  }

  private CommentModel setupDummyModel() {
    CommentModel model = new CommentModel();
    model.setUsername("testuser");
    model.setId("dqe345e456rf34rw");
    model.setPageId("product0815");
    model.setEmailAddress("example@example.com");
    model.setComment("I am the comment");
    return model;
  }
}