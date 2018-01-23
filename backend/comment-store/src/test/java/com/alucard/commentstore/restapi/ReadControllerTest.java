package com.alucard.commentstore.restapi;

import com.alucard.commentstore.model.CommentModel;
import com.alucard.commentstore.service.CommentService;
import de.codeboje.springbootbook.spamdetection.SpamDetector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReadController.class)
@AutoConfigureMockMvc
@MockBean({SpamDetector.class, CounterService.class})
public class ReadControllerTest {

  @MockBean
  private CommentService commentService;

  @Autowired
  private MockMvc mvc;

  private DateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");

  @Test
  public void testGetComments() throws Exception {
    CommentModel model = setupDummyModel();
    List<CommentModel> mockReturn = new ArrayList<>();
    mockReturn.add(model);

    when(this.commentService.list(anyString())).thenReturn(mockReturn);

    mvc.perform(get("/list/" + model.getPageId()))
        .andExpect(status().is(200))
        .andExpect(jsonPath("$[0].id", is(model.getId())))
        .andExpect(jsonPath("$[0].created", is(SDF.format(model.getCreationDate().getTime()))))
        .andExpect(jsonPath("$[0].username", is(model.getUsername())))
        .andExpect(jsonPath("$[0].comment", is(model.getComment())))
        .andExpect(jsonPath("$[0].email_address", is(model.getEmailAddress())));
    verify(this.commentService, times(1)).list(anyString());
  }

  private CommentModel setupDummyModel() {
    CommentModel model = new CommentModel();
    model.setId("aid");
    model.setUsername("testuser");
    model.setId("dqe345e456rf34rw");
    model.setPageId("product0815");
    model.setEmailAddress("example@example.com");
    model.setComment("I am the comment");
    model.setCreationDate(Calendar.getInstance());
    return model;
  }
}
