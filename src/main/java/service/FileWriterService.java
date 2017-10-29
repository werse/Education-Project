package service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterService {

  public static void write(String string, String filename) {
    try {
      File file = new File("src/main/resources/text/" + filename);
      new FileWriter(file).write(string);
    } catch (IOException e) {
      throw new IllegalArgumentException("Something happens with file");
    }
  }
}
