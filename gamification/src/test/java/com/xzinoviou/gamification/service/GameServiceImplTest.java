package com.xzinoviou.gamification.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.xzinoviou.gamification.domain.ScoreCard;
import com.xzinoviou.gamification.repository.BadgeCardRepository;
import com.xzinoviou.gamification.repository.ScoreCardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author : Xenofon Zinoviou
 */
@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

  private static final Long USER_ID = 1L;
  private static final Long ATTEMPT_ID = 1L;
  private static final boolean IS_NOT_CORRECT = false;
  private static final boolean IS_CORRECT = true;

  @Mock
  private ScoreCardRepository scoreCardRepository;

  @Mock
  private BadgeCardRepository badgeCardRepository;

  @InjectMocks
  private GameServiceImpl testClass;

  @Test
  public void nonCorrectAttemptTest() {
    testClass.newAttemptForUser(USER_ID, ATTEMPT_ID, IS_NOT_CORRECT);

    verify(scoreCardRepository, times(0)).save(any(ScoreCard.class));
  }

  @Test
  public void correctAttemptTest() {
    testClass.newAttemptForUser(USER_ID, ATTEMPT_ID, IS_CORRECT);
    ScoreCard score = new ScoreCard(USER_ID, ATTEMPT_ID);

    verify(scoreCardRepository, times(1)).save(argThat(scorecard ->
        scorecard.getScore() == 10 &&
            scorecard.getAttemptId().equals(ATTEMPT_ID) &&
            scorecard.getUserId().equals(USER_ID) &&
            scorecard.getScoreTimestamp() < System.currentTimeMillis()
    ));
  }

  @Test
  public void wrongAttemptTest() {
    //TODO:pending
  }

  @Test
  void newAttemptForUser() {
    //TODO: pending
  }

  @Test
  void retrieveStatsForUser() {
    //TODO: pending
  }
}