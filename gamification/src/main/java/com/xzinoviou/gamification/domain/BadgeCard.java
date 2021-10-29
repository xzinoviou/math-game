package com.xzinoviou.gamification.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Binds a badge with a user.
 *
 * @author : Xenofon Zinoviou
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public class BadgeCard {

  @Id
  @GeneratedValue
  @Column(name = "BADGE_ID")
  private final Long badgeId;

  private final Long userId;

  private final long badgeTimestamp;

  @Enumerated(value = EnumType.STRING)
  private final Badge badge;

  //Empty constructor for JSON / JPA
  public BadgeCard() {
    this(null, null, 0, null);
  }

  public BadgeCard(final Long userId, final Badge badge) {
    this(null, userId, System.currentTimeMillis(), badge);
  }
}
