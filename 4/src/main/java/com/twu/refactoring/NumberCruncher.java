package com.twu.refactoring;

import java.util.Arrays;

public class NumberCruncher {
  private final int[] numbers;

  public NumberCruncher(int... numbers) {
    this.numbers = numbers;
  }

  public int countEven() {
    return (int) Arrays.stream(numbers).filter(this::isEven).count();
  }

  public int countOdd() {
    return (int) Arrays.stream(numbers).filter(this::isOdd).count();
  }

  public int countPositive() {
    return (int) Arrays.stream(numbers).filter(this::isPositive).count();
  }

  public int countNegative() {
    return (int) Arrays.stream(numbers).filter(this::isNegative).count();
  }

  private boolean isEven(int num) {
    return num % 2 == 0;
  }

  private boolean isOdd(int num) {
    return num % 2 == 1;
  }

  private boolean isPositive(int num) {
    return num >= 0;
  }

  private boolean isNegative(int num) {
    return num < 0;
  }
}
