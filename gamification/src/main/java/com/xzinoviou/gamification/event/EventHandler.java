package com.xzinoviou.gamification.event;

import com.xzinoviou.gamification.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Event consumer triggering the associated business logic.
 *
 * @author : Xenofon Zinoviou
 */
@Slf4j
@Component
public class EventHandler {

  @Value("${multiplication.queue}")
  private String multiplicationQueue;

  private final GameService gameService;

  public EventHandler(GameService gameService) {
    this.gameService = gameService;
  }

  @RabbitListener(queues = "${multiplication.queue}")
  void handleMultiplicationSolved(final MultiplicationSolvedEvent event) {
    log.info("MultiplicationSolvedEvent received: {}", event.getMultiplicationResultAttemptId());

    try {
      gameService.newAttemptForUser(event.getUserId(), event.getMultiplicationResultAttemptId(),
          event.isCorrect());
    } catch (final Exception e) {
      log.error("Error when trying to process MultiplicationSolvedEvent", e);

      //do not re-queue the event
      throw new AmqpRejectAndDontRequeueException(e);
    }
  }
}
