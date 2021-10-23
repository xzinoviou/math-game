package com.xzinoviou.gamification.repository;

import com.xzinoviou.gamification.domain.BadgeCard;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Xenofon Zinoviou
 */
public interface BadgeCardRepository extends JpaRepository<BadgeCard, Long> {

  /**
   * Retrieves all badge cards for a given user.
   *
   * @param userId the given userId.
   * @return the list of badge cards, sorted my most recent.
   */
  List<BadgeCard> findBadgeCardsByUserIdOrderByBadgeTimestampDesc(final Long userId);
}
