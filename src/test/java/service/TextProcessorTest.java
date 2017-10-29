package service;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TextProcessorTest {

  @Test
  public void calculateSumOfNumberInString() throws Exception {
    long result = TextProcessor.calculateSumOfNumberInString("1,11009a1");
    assertThat(result, is(13L));
  }
}