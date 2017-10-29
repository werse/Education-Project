package service;

import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;
import static service.FileWriterService.write;

import java.util.List;
import java.util.Random;
import org.junit.Test;

public class FileWriterServiceTest {

  @Test
  public void writeSimpleString() throws Exception {
    List<String> strings = RandomWordService.getRandomWordsList(100);

    String collect = range(0, 5000)
        .mapToObj(val -> strings.get(new Random().nextInt(100)))
        .collect(joining(" "));

    String wordsFilename = "words.txt";
    write(collect, wordsFilename);
  }
}