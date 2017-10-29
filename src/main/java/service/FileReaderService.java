package service;


import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.readAllLines;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.joining;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class FileReaderService {

  public static Optional<String> readFile(String filename) {
    File file = new File("src/main/resources/text/" + filename);
    try {
      List<String> strings = readAllLines(file.toPath(), UTF_8);
      return of(strings.stream().collect(joining()));
    } catch (IOException e) {
      return empty();
    }
  }
}
