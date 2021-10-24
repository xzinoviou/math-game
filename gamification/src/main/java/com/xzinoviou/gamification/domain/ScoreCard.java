package com.xzinoviou.gamification.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

/**
 * Binds score with a multiplication attempt.
 *
 * @author : Xenofon Zinoviou
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public class ScoreCard {

  private static final int DEFAULT_SCORE = 10;

  @Id
  @GeneratedValue
  @Column(name = "CARD_ID")
  private final Long id;

  @Column(name = "USER_ID")
  private final Long userId;

  @Column(name = "ATTEMPT_ID")
  private final Long attemptId;

  @Column(name = "SCORE_TS")
  private final long scoreTimestamp;

  @Column(name = "SCORE")
  private final int score;

  //Empty constructor for JSON / JPA
  public ScoreCard() {
    this(null, null, null, 0, 0);
  }

  public ScoreCard(Long userId, Long attemptId) {
    this(null, userId, attemptId, System.currentTimeMillis(), DEFAULT_SCORE);

  }
}
