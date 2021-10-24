package com.xzinoviou.gamification.service;

import com.xzinoviou.gamification.domain.LeaderBoardRow;
import com.xzinoviou.gamification.repository.ScoreCardRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author : Xenofon Zinoviou
 */
@Service
public class LeaderBoardServiceImpl implements LeaderBoardService {

  private final ScoreCardRepository scoreCardRepository;

  public LeaderBoardServiceImpl(
      ScoreCardRepository scoreCardRepository) {
    this.scoreCardRepository = scoreCardRepository;
  }

  @Override
  public List<LeaderBoardRow> getCurrentLeaderBoard() {
    return scoreCardRepository.findFirst10();
  }
}
