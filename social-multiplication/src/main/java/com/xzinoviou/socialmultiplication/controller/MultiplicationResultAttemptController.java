package com.xzinoviou.socialmultiplication.controller;

import com.xzinoviou.socialmultiplication.domain.MultiplicationResultAttempt;
import com.xzinoviou.socialmultiplication.service.MultiplicationService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Xenofon Zinoviou
 */
@RestController
@RequestMapping("/results")
public class MultiplicationResultAttemptController {

  private final MultiplicationService multiplicationService;

  public MultiplicationResultAttemptController(
      MultiplicationService multiplicationService) {
    this.multiplicationService = multiplicationService;
  }

  @PostMapping
  public ResponseEntity<MultiplicationResultAttempt> postResult(
      @RequestBody MultiplicationResultAttempt multiplicationResultAttempt) {
    boolean isCorrect = multiplicationService.checkAttempt(multiplicationResultAttempt);
    MultiplicationResultAttempt attemptCopy = new MultiplicationResultAttempt(
        multiplicationResultAttempt.getUser(),
        multiplicationResultAttempt.getMultiplication(),
        multiplicationResultAttempt.getResultAttempt(),
        isCorrect
    );

    return ResponseEntity.ok(attemptCopy);
  }

  @GetMapping
  public ResponseEntity<List<MultiplicationResultAttempt>> getStatisticsByUserAlias(
      @RequestParam("alias") String alias) {
    return ResponseEntity.ok(multiplicationService.getStatsForUser(alias));
  }

  @GetMapping("/{id}")
  public ResponseEntity<MultiplicationResultAttempt> getResultById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(multiplicationService.getResultById(id));
  }
}
