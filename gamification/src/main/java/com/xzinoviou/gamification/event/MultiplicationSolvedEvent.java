package com.xzinoviou.gamification.event;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Initially declared in social-multiplication microservice, its duplication is needed for the preservation of the microservice isolation.
 *
 * @author : Xenofon Zinoviou
 */

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class MultiplicationSolvedEvent implements Serializable {

  private final Long multiplicationResultAttemptId;
  private final Long userId;
  private final boolean correct;
}
