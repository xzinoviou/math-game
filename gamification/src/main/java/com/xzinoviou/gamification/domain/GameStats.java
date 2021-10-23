package com.xzinoviou.gamification.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Contains the results of a single game snapshot in a specific timestamp.
 *
 * @author : Xenofon Zinoviou
 */

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class GameStats {

  private final Long userId;
  private final int score;
  private final List<Badge> badges;

  //Empty constructor for JSON
  public GameStats() {
    this.userId = 0L;
    this.score = 0;
    this.badges = new ArrayList<>();
  }

  /**
   * Factory method for producing zero-stat instances.
   *
   * @param userId the given userId.
   * @return a {@link GameStats} object with zero score and no badges.
   */
  public static GameStats emptyStats(final Long userId) {
    return new GameStats(userId, 0, Collections.emptyList());
  }

  /**
   * @return an unmodifiable view of the badge cards list.
   */
  public List<Badge> getBadges() {
    return Collections.unmodifiableList(badges);
  }
}
