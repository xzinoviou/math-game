package com.xzinoviou.gamification.service;

import com.xzinoviou.gamification.domain.LeaderBoardRow;
import java.util.List;

/**
 * Provides access to LeaderBoard.
 *
 * @author : Xenofon Zinoviou
 */
public interface LeaderBoardService {

  /**
   * Retrieve current leader board with top score users.
   *
   * @return the users with highest-scores.
   */
  List<LeaderBoardRow> getCurrentLeaderBoard();
}
