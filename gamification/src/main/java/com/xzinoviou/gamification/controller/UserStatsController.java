package com.xzinoviou.gamification.controller;

import com.xzinoviou.gamification.domain.GameStats;
import com.xzinoviou.gamification.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * User stats API.
 *
 * @author : Xenofon Zinoviou
 */

@RestController
@RequestMapping("/stats")
public class UserStatsController {

  private final GameService gameService;

  public UserStatsController(GameService gameService) {
    this.gameService = gameService;
  }

  @GetMapping
  public GameStats getStatsForUser(@RequestParam("userId") final Long userId) {
    return gameService.retrieveStatsForUser(userId);
  }
}
