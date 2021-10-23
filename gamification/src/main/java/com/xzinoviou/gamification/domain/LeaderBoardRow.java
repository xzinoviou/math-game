package com.xzinoviou.gamification.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Represents a user row in the leaderboard.
 *
 * @author : Xenofon Zinoviou
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class LeaderBoardRow {

  private final Long userId;

  private final Long totalScore;

  //empty constructor for JSON
  public LeaderBoardRow() {
    this(0L, 0L);
  }
}
