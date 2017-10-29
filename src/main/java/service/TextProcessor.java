package service;

import static com.google.common.collect.Lists.charactersOf;

public class TextProcessor {

  static long calculateSumOfNumberInString(String string) {
    return charactersOf(string).stream()
        .filter(Character::isDigit)
        .mapToInt(Character::getNumericValue)
        .sum();
  }
}
