package com.xzinoviou.gamification.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.xzinoviou.gamification.client.MultiplicationResultAttemptClientImpl;
import com.xzinoviou.gamification.client.dto.MultiplicationResultAttemptDTO;
import com.xzinoviou.gamification.domain.Badge;
import com.xzinoviou.gamification.domain.BadgeCard;
import com.xzinoviou.gamification.domain.ScoreCard;
import com.xzinoviou.gamification.repository.BadgeCardRepository;
import com.xzinoviou.gamification.repository.ScoreCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
  private final int LUCKY_NUMBER = 42;

  @Mock
  private ScoreCardRepository scoreCardRepository;

  @Mock
  private BadgeCardRepository badgeCardRepository;

  @Mock
  private MultiplicationResultAttemptClientImpl attemptClient;

  private GameServiceImpl testClass;

  @BeforeEach
  void setUp() {
    testClass = new GameServiceImpl(
        LUCKY_NUMBER, scoreCardRepository,
        badgeCardRepository, attemptClient);
  }

  @Test
  public void nonCorrectAttemptTest() {
    testClass.newAttemptForUser(USER_ID, ATTEMPT_ID, IS_NOT_CORRECT);

    verify(scoreCardRepository, times(0)).save(any(ScoreCard.class));
  }

  @Test
  public void correctAttemptWithoutLuckyBadgeTest() {
    ScoreCard score = new ScoreCard(USER_ID, ATTEMPT_ID);
    MultiplicationResultAttemptDTO attemptDTO = createAttemptDTO(10, 10, 100);

    when(attemptClient.retrieveMultiplicationResultAttemptById(ATTEMPT_ID)).thenReturn(attemptDTO);

    testClass.newAttemptForUser(USER_ID, ATTEMPT_ID, IS_CORRECT);
    verify(scoreCardRepository, times(1)).save(argThat(scorecard ->
        scorecard.getScore() == 10 &&
            scorecard.getAttemptId().equals(ATTEMPT_ID) &&
            scorecard.getUserId().equals(USER_ID) &&
            scorecard.getScoreTimestamp() < System.currentTimeMillis()
    ));
  }

  @Test
  public void correctAttemptWithLuckyBadgeTest() {
    MultiplicationResultAttemptDTO attemptDTO = createAttemptDTO(42, 10, 420);

    when(attemptClient.retrieveMultiplicationResultAttemptById(ATTEMPT_ID)).thenReturn(attemptDTO);

    testClass.newAttemptForUser(USER_ID, ATTEMPT_ID, IS_CORRECT);

    verify(scoreCardRepository, times(1)).save(argThat(scorecard ->
        scorecard.getScore() == 10 &&
            scorecard.getAttemptId().equals(ATTEMPT_ID) &&
            scorecard.getUserId().equals(USER_ID) &&
            scorecard.getScoreTimestamp() < System.currentTimeMillis()
    ));

    verify(scoreCardRepository, times(1)).getTotalScoreForUser(USER_ID);

    verify(badgeCardRepository, times(1)).findBadgeCardsByUserIdOrderByBadgeTimestampDesc(USER_ID);

    verify(badgeCardRepository, times(1)).save(argThat(arg ->
        arg.getUserId().equals(USER_ID) &&
            arg.getBadge().equals(Badge.LUCKY_NUMBER)
    ));
  }

  private MultiplicationResultAttemptDTO createAttemptDTO(int factorA, int factorB,
                                                          int resultAttempt) {
    return new MultiplicationResultAttemptDTO("user", factorA, factorB, resultAttempt, IS_CORRECT);
  }

  private BadgeCard createBadgeCard(Long userId, Badge badge) {
    return new BadgeCard(userId, badge);
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