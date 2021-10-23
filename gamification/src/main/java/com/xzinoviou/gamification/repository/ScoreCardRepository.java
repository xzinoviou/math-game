package com.xzinoviou.gamification.repository;

import com.xzinoviou.gamification.domain.LeaderBoardRow;
import com.xzinoviou.gamification.domain.ScoreCard;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Handles operations with ScoreCards.
 *
 * @author : Xenofon Zinoviou
 */
public interface ScoreCardRepository extends JpaRepository<ScoreCard, Long> {

  /**
   * Total score for a user by a given user id.
   *
   * @param userId the given user id.
   * @return the total score of the user.
   */
  @Query("SELECT SUM(s.score) FROM ScoreCard s WHERE s.userId = :userId GROUP BY s.userId")
  int getTotalScoreForUser(@Param("userId") final Long userId);

  /**
   * Retrieves a list of {@link com.xzinoviou.gamification.domain.LeaderBoardRow} representing the Leader Board of users and their total scores.
   *
   * @return the the list of total scores ordered by max score.
   */
  @Query("SELECT NEW com.xzinoviou.gamification.domain.LeaderBoardRow(s.userId,SUM(s.score)) " +
      "FROM ScoreCard s GROUP BY s.userId ORDER BY SUM(s.score) DESC")
  List<LeaderBoardRow> findFirst10();

  /**
   * Retrieves all score cards for a user by given user id.
   *
   * @param userId the user id .
   * @return a list with all score cards for given user, sorted by most recent.
   */
  List<ScoreCard> findByUserIdOrderByScoreBadgeTimestampDesc(final Long userId);
}
