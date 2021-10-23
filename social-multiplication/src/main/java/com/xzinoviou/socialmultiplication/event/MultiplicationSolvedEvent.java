package com.xzinoviou.socialmultiplication.event;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * It models a {@link com.xzinoviou.socialmultiplication.domain.Multiplication} solution
 * supplied to the system and provides context information about it.
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
