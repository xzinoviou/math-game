package com.xzinoviou.socialmultiplication.controller;

import com.xzinoviou.socialmultiplication.domain.Multiplication;
import com.xzinoviou.socialmultiplication.service.MultiplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Xenofon Zinoviou
 */
@RestController
@RequestMapping("/multiplications")
public class MultiplicationController {

  private final MultiplicationService multiplicationService;

  public MultiplicationController(
      MultiplicationService multiplicationService) {
    this.multiplicationService = multiplicationService;
  }

  @GetMapping("/random")
  public Multiplication getRandomMultiplication() {
    return multiplicationService.createRandomMultiplication();
  }
}
