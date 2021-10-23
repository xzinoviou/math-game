package com.xzinoviou.socialmultiplication.service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author : Xenofon Zinoviou
 */
@ExtendWith(MockitoExtension.class)
public class RandomGeneratorServiceImplTest {

  private RandomGeneratorService testClass;

  @BeforeEach
  void init() {
    testClass = new RandomGeneratorServiceImpl();
  }

  @Test
  void testRandomGeneratorIsWithinValidLimits() {
    Set<Integer> randomFactors = IntStream.range(0, 1000)
        .map(i -> testClass.generateRandomFactor())
        .boxed().collect(Collectors.toSet());

    Set<Integer> validFactors = IntStream.rangeClosed(11, 1000)
        .boxed().collect(Collectors.toSet());

    Assertions.assertTrue(validFactors.containsAll(randomFactors));
  }

}
