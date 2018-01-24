package com.alucard.commentstore.restapi;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class LoginController {

  @GetMapping("/authenticate")
  @ResponseStatus(HttpStatus.OK)
  public void authenticate() {
    // no logic
  }
}
