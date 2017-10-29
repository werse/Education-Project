package service;

import static com.google.common.collect.Maps.newHashMap;
import static java.math.BigInteger.ONE;
import static java.util.Comparator.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static service.FileReaderService.readFile;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class FileReaderServiceTest {

  @Test
  public void readTxtFile() throws Exception {
    Optional<String> context = readFile("text.txt");
    assertTrue(context.isPresent());
    assertThat(context.get(), is("1,2,3,4,5,6,7,8,9,10"));
  }

  @Test
  public void readJsonFile() throws Exception {
    String json = readFile("example.json").orElse("");
    Type type = new TypeToken<Map<String, List<String>>>() {
    }.getType();
    Map<String, List<String>> result = new Gson().fromJson(json, type);
    assertEquals("[1, 2, 3]", StringUtils.join(result.get("strings")));
    assertEquals(3, result.get("strings").size());
  }

  @Test
  public void readJsonFile2() throws Exception {
    String json = readFile("example.json").orElse("");
    JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
    System.out.println(jsonObject);
    JsonArray strings = jsonObject.get("strings").getAsJsonArray();
    strings.forEach(System.out::println);
  }

  @Test
  public void getRepeatableWords() throws Exception {
    String wordsLine = readFile("words.txt").orElse("");
    Pattern pattern = Pattern.compile("\\b[a-z]+\\b");
    Matcher matcher = pattern.matcher(wordsLine);
    Map<String, BigInteger> words = newHashMap();
    while (matcher.find()) {
      String word = matcher.group();
      words.merge(word, ONE, BigInteger::add);
    }
    words.entrySet().stream()
        .sorted(comparingByValue(reverseOrder()))
        .collect(toList())
        .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
  }
}