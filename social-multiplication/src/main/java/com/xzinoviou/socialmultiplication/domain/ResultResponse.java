package com.xzinoviou.socialmultiplication.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * @author : Xenofon Zinoviou
 */
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
public class ResultResponse {
  private final boolean correct;
}
