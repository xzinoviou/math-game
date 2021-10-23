package com.xzinoviou.socialmultiplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.xzinoviou.socialmultiplication.domain.Multiplication;
import org.junit.jupiter.api.Test;

/**
 * @author : Xenofon Zinoviou
 */
public class MultiplicationTest {

  @Test
  void testWhenMultiplicationIsCreatedWithDefaultValues_thenAllFieldsAreEqualToZero() {
    Multiplication result = new Multiplication();
    assertEquals(0, result.getFactorA());
    assertEquals(0, result.getFactorB());
  }

  @Test
  void testWhenMultiplicationIsCreatedWithPositiveValues_thenResultIsPositive() {
    Multiplication result = new Multiplication(10, 10);
    assertEquals(10, result.getFactorA());
    assertEquals(10, result.getFactorB());
  }

  @Test
  void testWhenMultiplicationIsCreatedWithANegativeValue_thenResultIsNegative() {
    Multiplication result = new Multiplication(10, -10);
    assertEquals(10, result.getFactorA());
    assertEquals(-10, result.getFactorB());
  }
}
