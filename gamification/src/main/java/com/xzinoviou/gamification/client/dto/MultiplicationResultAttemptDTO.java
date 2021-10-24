package com.xzinoviou.gamification.client.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author : Xenofon Zinoviou
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class MultiplicationResultAttemptDTO {

  private final String userAlias;
  private final int multiplicationFactorA;
  private final int multiplicationFactorB;
  private final int resultAttempt;
  private final boolean correct;

  //Empty constructor for JSON (de)serialization
  public MultiplicationResultAttemptDTO() {
    userAlias = null;
    multiplicationFactorA = -1;
    multiplicationFactorB = -1;
    resultAttempt = -1;
    correct = false;
  }
}
