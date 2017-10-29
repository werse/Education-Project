package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static service.RandomWordService.getRandomWord;
import static service.RandomWordService.getRandomWordsList;

import java.util.List;
import org.junit.Test;

public class RandomWordServiceTest {

  @Test
  public void getRandomWordFromSomeSite() throws Exception {
    String randomWord = getRandomWord();
    assertNotNull(randomWord);
  }

  @Test
  public void randomWordsList() throws Exception {
    List<String> randomWordsList = getRandomWordsList(10);
    assertFalse(randomWordsList.isEmpty());
    assertEquals(10, randomWordsList.size());
  }
}