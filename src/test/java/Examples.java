import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class Examples {

  @Test
  public void testString() throws Exception {
    String numbers = "1,2;3.4";
    System.out.println(numbers.concat(",").concat("123"));
    System.out.println(numbers + "," + "123");
    System.out.println(Arrays.toString(numbers.split("[,.;]")));
    String[] split = "Work1Word2Worst4Load5Trabel".split("\\d");
    System.out.println(Arrays.toString(split));
  }

  @Test
  public void testRandomStrings() throws Exception {
    List<String> strings = asList("Work", "Travel", "Sleep");
    List<String> randomStrings = getRandomStrings(strings);
    Set<String> stringsSet = new HashSet<>();

    stringsSet.addAll(randomStrings);
    System.out.println(stringsSet.size());

    System.out.println(randomStrings.stream().distinct().collect(toList()).size());

    List<String> uniqueStrings = new ArrayList<>();
    slowUniqueString(randomStrings, uniqueStrings);
  }

  @Test
  public void countOfRandomStrings() throws Exception {
    List<String> strings = asList("Work", "Travel", "Sleep", "Food");
    List<String> randomStrings = getRandomStrings(strings);
    Map<String, Integer> map = new ConcurrentHashMap<>();

    randomStrings.forEach(string -> map.merge(string, 1, (oldVal, newVal) -> oldVal + newVal));

    for (String string : randomStrings) {
      map.merge(string, 1, (oldVal, newVal) -> oldVal + newVal);
    }
    System.out.println(map.get("Work"));
  }

  @Test
  public void mapExample() throws Exception {
    Map<String, String> map = new HashMap<>();
    map.put("1", "1");
    map.put("2", "2");
    map.merge("1", "2", (oldVal, newVal) -> oldVal + newVal);
    System.out.println(map.get("2"));
    System.out.println(map.get("1").equals("100"));
    System.out.println(map.get("1"));
    System.out.println(map.keySet());
    System.out.println(map.values());
  }


  @Test
  public void java8() throws Exception {
    Function<String, Integer> function = e -> {
      if (e.matches("^\\d+$")) {
        return Integer.parseInt(e);
      }
      return 0;
    };
    System.out.println(function.apply("a"));
  }

  private List<String> getRandomStrings(List<String> strings) {
    AtomicInteger counter = new AtomicInteger(0);
    ArrayList<String> randomStrings = new ArrayList<>();
    for (int i = 0; i < 10000000; i++) {
      String string = strings.get(new Random().nextInt(2));
      if (new Random().nextInt(4) == 0) {
        string += getRandomSymbol();
        if (new Random().nextInt(4) == 1) {
          string += getRandomSymbol();
        }
      }
      if (string.equals("Work")) {
        counter.getAndIncrement();
      }
      randomStrings.add(string);
    }
    System.out.println(counter.get());
    return randomStrings;
  }

  @Test
  public void collectToString() throws Exception {
    List<Integer> integers = asList(1, 2, 3, 34, 535, 25, 253, 523, 5, 23);
    String delimiter = ", ";

    StringBuilder builder = new StringBuilder();
    integers.forEach(val -> builder.append(val).append(delimiter));
    System.out.println(builder.substring(0, builder.length() - delimiter.length()));
  }

  private void slowUniqueString(List<String> randomStrings, List<String> uniqueStrings) {
    AtomicInteger counter = new AtomicInteger(0);
    for (String string : randomStrings) {
      int inner = counter.getAndIncrement();
      if (inner % 100 == 0) {
        System.out.println(inner + " times");
      }
      if (uniqueStrings.stream().noneMatch(uniqueElement -> uniqueElement.equals(string))) {
        uniqueStrings.add(string);
      }
    }
    System.out.println(uniqueStrings.size());
  }

  private String getRandomSymbol() {
    return String.valueOf(new Random().nextInt(600));
  }
}
