package service;

import static com.google.common.collect.Lists.newArrayList;
import static com.mashape.unirest.http.Unirest.post;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.List;
import java.util.stream.IntStream;

public class RandomWordService {

  private static final List<String> strings = newArrayList();

  public static String getRandomWord() {
    try {
      return post("http://watchout4snakes.com/wo4snakes/Random/RandomWord").asString().getBody();
    } catch (UnirestException e) {
      throw new RuntimeException("Cannot access resource");
    }
  }


  public static List<String> getRandomWordsList(int size) {
    if (isNotEmpty(strings) || strings.size() != size) {
      return IntStream.range(0, size).mapToObj(i -> getRandomWord()).collect(toList());
    }
    return strings;
  }
}
