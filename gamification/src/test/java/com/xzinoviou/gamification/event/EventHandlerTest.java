package com.xzinoviou.gamification.event;

import static org.mockito.Mockito.verify;

import com.xzinoviou.gamification.service.GameService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author : Xenofon Zinoviou
 */
@Disabled
@ExtendWith(MockitoExtension.class)
class EventHandlerTest {

  private final Long USER_ID = 1L;
  private final Long ATTEMPT_ID = 2L;
  private final boolean IS_CORRECT = true;

  @Mock
  private GameService gameService;

  @InjectMocks
  private EventHandler testClass;

  @Test
  void handleMultiplicationSolved() {

    MultiplicationSolvedEvent event =
        new MultiplicationSolvedEvent(ATTEMPT_ID, USER_ID, IS_CORRECT);

    testClass.handleMultiplicationSolved(event);
    verify(gameService, Mockito.times(1)).newAttemptForUser(
        event.getUserId(), event.getMultiplicationResultAttemptId(),
        event.isCorrect()
    );
  }
}