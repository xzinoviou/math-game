package com.xzinoviou.gamification.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Rest Client configuration.
 *
 * @author : Xenofon Zinoviou
 */
@Configuration
public class RestClientConfiguration {

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

  //TODO: Use WebClient instead.
}
