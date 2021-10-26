package com.xzinoviou.gamification.client;

import com.xzinoviou.gamification.client.dto.MultiplicationResultAttemptDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author : Xenofon Zinoviou
 */
@Component
public class MultiplicationResultAttemptClientImpl implements MultiplicationResultAttemptClient {

  private final RestTemplate restTemplate;

  private final String multiplicationHost;

  public MultiplicationResultAttemptClientImpl(
      final RestTemplate restTemplate,
      @Value("${multiplicationHost}") final String multiplicationHost) {
    this.restTemplate = restTemplate;
    this.multiplicationHost = multiplicationHost;
  }

  @Override
  public MultiplicationResultAttemptDTO retrieveMultiplicationResultAttemptById(
      Long resultAttemptId) {
    return restTemplate.getForObject(multiplicationHost + "/results/" +
        resultAttemptId, MultiplicationResultAttemptDTO.class);
  }
}
