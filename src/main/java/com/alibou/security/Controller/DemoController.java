package com.alibou.security.Controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/demo-controller")
@Hidden
public class DemoController {

  @GetMapping
  public ResponseEntity<String> sayHello() {
    return ResponseEntity.ok("Hello from secured endpoint");
  }

}
