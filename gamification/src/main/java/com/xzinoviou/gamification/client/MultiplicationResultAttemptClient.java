package com.xzinoviou.gamification.client;

import com.xzinoviou.gamification.client.dto.MultiplicationResultAttemptDTO;

/**
 * @author : Xenofon Zinoviou
 */
public interface MultiplicationResultAttemptClient {

  MultiplicationResultAttemptDTO retrieveMultiplicationResultAttemptById(
      final Long multiplicationId);
}
