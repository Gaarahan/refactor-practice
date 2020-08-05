package com.twu.refactoring;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author gaarahan
 */
public class Direction {
    private char direction;
    private final ArrayList<Character> directionsInClockwise = new ArrayList<>(Arrays.asList('N', 'E', 'S', 'W'));

    public Direction(char direction) {
        this.direction = direction;
    }

    public Direction turnRight() {
      if (directionsInClockwise.contains(direction)) {
        int curIndex = directionsInClockwise.indexOf(direction);
        int rightIndex = (curIndex + 1) % directionsInClockwise.size();
        this.direction = directionsInClockwise.get(rightIndex);
        return this;
      }
      throw new IllegalArgumentException();
    }

    public Direction turnLeft() {
      if (directionsInClockwise.contains(direction)) {
        int curIndex = directionsInClockwise.indexOf(direction);
        int leftIndex = (curIndex + directionsInClockwise.size() - 1) % directionsInClockwise.size();
        this.direction = directionsInClockwise.get(leftIndex);
        return this;
      }
      throw new IllegalArgumentException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
          return true;
        }
        if (o == null || getClass() != o.getClass()) {
          return false;
        }

        Direction direction1 = (Direction) o;

      return direction == direction1.direction;
    }

    @Override
    public int hashCode() {
        return direction;
    }

    @Override
    public String toString() {
        return "Direction{direction=" + direction + '}';
    }
}
