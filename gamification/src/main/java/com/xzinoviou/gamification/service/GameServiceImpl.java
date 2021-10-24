package com.xzinoviou.gamification.service;

import com.xzinoviou.gamification.domain.Badge;
import com.xzinoviou.gamification.domain.BadgeCard;
import com.xzinoviou.gamification.domain.GameStats;
import com.xzinoviou.gamification.domain.ScoreCard;
import com.xzinoviou.gamification.repository.BadgeCardRepository;
import com.xzinoviou.gamification.repository.ScoreCardRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author : Xenofon Zinoviou
 */
@Service
@Slf4j
public class GameServiceImpl implements GameService {

  @Value("${lucky.number}")
  private static int LUCKY_NUMBER;

  private final ScoreCardRepository scoreCardRepository;
  private final BadgeCardRepository badgeCardRepository;

  public GameServiceImpl(
      ScoreCardRepository scoreCardRepository,
      BadgeCardRepository badgeCardRepository) {
    this.scoreCardRepository = scoreCardRepository;
    this.badgeCardRepository = badgeCardRepository;
  }

  @Override
  public GameStats newAttemptForUser(Long userId, Long attemptId, boolean correct) {

    if (!correct) {
      return GameStats.emptyStats(userId);
    }

    ScoreCard scoreCard = new ScoreCard(userId, attemptId);
    scoreCardRepository.save(scoreCard);

    log.info(
        "[-- #newAttemptForUser --] User with user id: {} scored: {} points for attempt id: {}",
        userId,
        scoreCard.getScore(),
        attemptId);

    List<BadgeCard> badgeCards = processForBadges(userId, attemptId);

    return new GameStats(
        userId, scoreCard.getScore(),
        badgeCards.stream().map(BadgeCard::getBadge).collect(Collectors.toList())
    );
  }

  private List<BadgeCard> processForBadges(Long userId, Long attemptId) {
    List<BadgeCard> badgeCards = new ArrayList<>();

    int totalScore = scoreCardRepository.getTotalScoreForUser(userId);
    log.info("[-- #processForBadges --] New score for user with id: {} is: {}", userId, totalScore);

    List<ScoreCard> userScoreCards =
        scoreCardRepository.findByUserIdOrderByScoreTimestampDesc(userId);

    List<BadgeCard> userBadgeCards =
        badgeCardRepository.findBadgeCardsByUserIdOrderByBadgeTimestampDesc(userId);

    //Assign Badges


    //check for badge point categories
    checkAndGiveBadgeBasedOnScore(userBadgeCards, Badge.BRONZE_MULTIPLICATION, totalScore, 100,
        userId).ifPresent(userBadgeCards::add);

    checkAndGiveBadgeBasedOnScore(userBadgeCards, Badge.BRONZE_MULTIPLICATION, totalScore, 100,
        userId).ifPresent(userBadgeCards::add);

    checkAndGiveBadgeBasedOnScore(userBadgeCards, Badge.BRONZE_MULTIPLICATION, totalScore, 100,
        userId).ifPresent(userBadgeCards::add);


    //check for first won badge
    if (userScoreCards.size() == 1 &&
        !containsBadge(userBadgeCards, Badge.FIRST_WON)) {
      BadgeCard firstWonBadgeCard = giveBadgeToUser(Badge.FIRST_WON, userId);
      userBadgeCards.add(firstWonBadgeCard);
    }

    return badgeCards;
  }

  private Optional<BadgeCard> checkAndGiveBadgeBasedOnScore(
      final List<BadgeCard> badgeCards, final Badge badge,
      final int score, final int scoreThreshold, final Long userId
  ) {

    if (score >= scoreThreshold && !containsBadge(badgeCards, badge)) {
      return Optional.of(giveBadgeToUser(badge, userId));
    }
    return Optional.empty();
  }

  private boolean containsBadge(final List<BadgeCard> badgeCards,
                                final Badge badge) {
    return badgeCards.stream().anyMatch(b -> b.getBadge().equals(badge));
  }

  private BadgeCard giveBadgeToUser(final Badge badge, final Long userId) {
    BadgeCard badgeCard = new BadgeCard(userId, badge);
    badgeCardRepository.save(badgeCard);
    log.info("[-- #giveBadgeToUser --] User with id: {} has won a new badge: {}.", userId, badge);
    return badgeCard;
  }

  @Override
  public GameStats retrieveStatsForUser(Long userId) {
    int score = scoreCardRepository.getTotalScoreForUser(userId);

    List<BadgeCard> badgeCards =
        badgeCardRepository.findBadgeCardsByUserIdOrderByBadgeTimestampDesc(userId);

    return new GameStats(
        userId, score,
        badgeCards.stream().map(BadgeCard::getBadge).collect(Collectors.toList())
    );
  }
}
