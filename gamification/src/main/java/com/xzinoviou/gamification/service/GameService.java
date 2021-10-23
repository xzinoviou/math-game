package com.xzinoviou.gamification.service;

import com.xzinoviou.gamification.domain.GameStats;

/**
 * Includes the gaming logic of the system.
 *
 * @author : Xenofon Zinoviou
 */
public interface GameService {

  /**
   * Process a new attempt.
   *
   * @param userId    the given user id.
   * @param attemptId the given attempt id.
   * @param correct   the attempt correctness verification.
   * @return a {@link GameStats} object with total score and all badges.
   */
  GameStats newAttemptForUser(Long userId, Long attemptId, boolean correct);

  /**
   * Get game stats for a user by given user id.
   *
   * @param userId the given user id.
   * @return a user's total stats.
   */
  GameStats retrieveStatsForUser(Long userId);
}
