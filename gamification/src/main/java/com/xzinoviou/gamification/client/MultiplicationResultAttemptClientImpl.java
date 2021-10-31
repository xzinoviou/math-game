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

  private final String gatewayHost;

  public MultiplicationResultAttemptClientImpl(
      final RestTemplate restTemplate,
      @Value("${gatewayHost}") final String gatewayHost) {
    this.restTemplate = restTemplate;
    this.gatewayHost = gatewayHost;
  }

  @Override
  public MultiplicationResultAttemptDTO retrieveMultiplicationResultAttemptById(
      Long resultAttemptId) {
    return restTemplate.getForObject(gatewayHost + "/results/" +
        resultAttemptId, MultiplicationResultAttemptDTO.class);
  }
}
