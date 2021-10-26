package com.xzinoviou.gamification.client;

import com.xzinoviou.gamification.client.dto.MultiplicationResultAttemptDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

/**
 * @author : Xenofon Zinoviou
 */
class MultiplicationResultAttemptClientImplTest {

  public final String MULTIPLICATION_HOST = "http://127.0.0.1:8080";

  private RestTemplate restTemplate;

  private MultiplicationResultAttemptClientImpl testClass;

  @BeforeEach
  void setUp() {
    restTemplate = Mockito.mock(RestTemplate.class);
    testClass = new MultiplicationResultAttemptClientImpl(restTemplate, MULTIPLICATION_HOST);
  }

  @Test
  void retrieveMultiplicationResultAttemptById() {
    Long attemptId = 1L;
    testClass.retrieveMultiplicationResultAttemptById(attemptId);

    Mockito.verify(restTemplate, Mockito.times(1))
        .getForObject(MULTIPLICATION_HOST + "/results/" + attemptId,
            MultiplicationResultAttemptDTO.class);

  }
}