package com.xzinoviou.socialmultiplication.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Handles the communication with the event bus.
 *
 * @author : Xenofon Zinoviou
 */

@Component
@Slf4j
public class EventDispatcher {

  private final RabbitTemplate rabbitTemplate;

  private final String multiplicationExchange;

  private final String multiplicationSolvedRoutingKey;

  private final ObjectMapper objectMapper;

  public EventDispatcher(final RabbitTemplate rabbitTemplate,
                         @Value("${multiplication.exchange}") final String multiplicationExchange,
                         @Value("${multiplication.solved.key}")
                         final String multiplicationSolvedRoutingKey,
                         ObjectMapper objectMapper) {
    this.rabbitTemplate = rabbitTemplate;
    this.multiplicationExchange = multiplicationExchange;
    this.multiplicationSolvedRoutingKey = multiplicationSolvedRoutingKey;
    this.objectMapper = objectMapper;
  }

  public void send(final MultiplicationSolvedEvent event) {
    String serializedEvent = null;
    try {
      serializedEvent = objectMapper.writeValueAsString(event);
    } catch (Exception e) {
      log.error("Object mapper failed to convert: ", event);
    }

    rabbitTemplate.convertAndSend(multiplicationExchange, multiplicationSolvedRoutingKey, event);

    log.info("[--- {} - event send to RabbitMQ : object -> {}", this.getClass().getSimpleName(),
        serializedEvent);
    log.info("[--- {} - event send to RabbitMQ : exchange -> {}", this.getClass().getSimpleName(),
        multiplicationExchange);
    log.info("[--- {} - event send to RabbitMQ : routingKey -> {}", this.getClass().getSimpleName(),
        multiplicationSolvedRoutingKey);
  }
}
