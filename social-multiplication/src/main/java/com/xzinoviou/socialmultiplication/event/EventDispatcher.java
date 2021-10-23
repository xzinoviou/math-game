package com.xzinoviou.socialmultiplication.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Handles the communication with the event bus.
 *
 * @author : Xenofon Zinoviou
 */

@Component
public class EventDispatcher {

  private final RabbitTemplate rabbitTemplate;

  private final String multiplicationExchange;

  private final String multiplicationSolvedRoutingKey;

  public EventDispatcher(final RabbitTemplate rabbitTemplate,
                         @Value("${multiplication.exchange}") final String multiplicationExchange,
                         @Value("${multiplication.solved.key}")
                         final String multiplicationSolvedRoutingKey) {
    this.rabbitTemplate = rabbitTemplate;
    this.multiplicationExchange = multiplicationExchange;
    this.multiplicationSolvedRoutingKey = multiplicationSolvedRoutingKey;
  }

  public void send(final MultiplicationSolvedEvent event) {
    rabbitTemplate.convertAndSend(multiplicationExchange, multiplicationSolvedRoutingKey, event);
  }
}
