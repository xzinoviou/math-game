package com.xzinoviou.gamification.service;

import com.xzinoviou.gamification.repository.ScoreCardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author : Xenofon Zinoviou
 */
@ExtendWith(MockitoExtension.class)
class LeaderBoardServiceImplTest {

  @Mock
  private ScoreCardRepository scoreCardRepository;

  @InjectMocks
  private LeaderBoardServiceImpl testClass;

  @Test
  void getCurrentLeaderBoard() {
    testClass.getCurrentLeaderBoard();

    Mockito.verify(scoreCardRepository, Mockito.times(1)).findFirst10();
  }
}