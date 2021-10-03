package com.example.sinch.utils;

import static java.lang.String.format;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

public final class ResourceFile {
  private ResourceFile() {}

  public static String readContent(String fileName) {
    try {
      var is = ResourceFile.class.getResourceAsStream(fileName);
      return IOUtils.toString(is, StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new IllegalStateException(
          format("Can not read from file %s. Error: %s", fileName, e.getMessage()), e);
    }
  }
}
