package com.xzinoviou.gamification.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.xzinoviou.gamification.client.MultiplicationResultAttemptDTODeserializer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Multiplication attempt DTO , differentiating from
 * social-multiplication microservice POJO, in a more fat DTO.
 *
 * @author : Xenofon Zinoviou
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@JsonDeserialize(using = MultiplicationResultAttemptDTODeserializer.class)
public class MultiplicationResultAttemptDTO {

  private final String userAlias;
  private final int multiplicationFactorA;
  private final int multiplicationFactorB;
  private final int resultAttempt;
  private final boolean correct;

  //Empty constructor for JSON/JPA (de)serialization
  public MultiplicationResultAttemptDTO() {
    userAlias = null;
    multiplicationFactorA = -1;
    multiplicationFactorB = -1;
    resultAttempt = -1;
    correct = false;
  }
}
