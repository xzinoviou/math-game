package com.xzinoviou.gamification.controller;

import com.xzinoviou.gamification.domain.LeaderBoardRow;
import com.xzinoviou.gamification.service.LeaderBoardService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * LeaderBoard API.
 *
 * @author : Xenofon Zinoviou
 */
@RestController
@RequestMapping("/leaders")
public class LeaderBoardController {

  private final LeaderBoardService leaderBoardService;

  public LeaderBoardController(
      LeaderBoardService leaderBoardService) {
    this.leaderBoardService = leaderBoardService;
  }

  @GetMapping
  public List<LeaderBoardRow> getLeaderBoard() {
    return leaderBoardService.getCurrentLeaderBoard();
  }
}
