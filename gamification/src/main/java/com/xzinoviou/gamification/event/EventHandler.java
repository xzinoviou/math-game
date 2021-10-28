package com.xzinoviou.gamification.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xzinoviou.gamification.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
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

  private final ObjectMapper objectMapper;

  public EventHandler(GameService gameService,
                      ObjectMapper objectMapper) {
    this.gameService = gameService;
    this.objectMapper = objectMapper;
  }

  @RabbitListener(queues = "${multiplication.queue}")
  void handleMultiplicationSolved(final Object obj) {
    byte[] serializedBytes = ((Message) obj).getBody();
    MultiplicationSolvedEvent event = null;
    try {
      event = objectMapper.readValue(serializedBytes, MultiplicationSolvedEvent.class);
    } catch (Exception e) {
      throw new RuntimeException("Queue consumer failed");
    }

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
