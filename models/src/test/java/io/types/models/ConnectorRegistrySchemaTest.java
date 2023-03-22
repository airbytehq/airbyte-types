/*
 * Copyright (c) 2022 Airbyte, Inc., all rights reserved.
 */

package io.types.models;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.airbyte.types.models.ConnectorRegistry;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import org.junit.jupiter.api.Test;


import org.reflections.Reflections;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.reflections.scanners.SubTypesScanner;


class ConnectorRegistrySchemaTest {
  final Path RESOURCE_DIRECTORY = Paths.get("src","main","resources");
  final Path PYTHON_OUTPUT_DIRECTORY = Paths.get("python","airbyte_types","models");

  private static List<String> getJsonFieldNames(Class<?> clazz) {
    List<String> fieldNames = new ArrayList<>();
    Field[] fields = clazz.getDeclaredFields();

    for (Field field : fields) {
      JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
      if (jsonProperty != null) {
        fieldNames.add(field.getName());
      }
    }

    return fieldNames;
  }

  private long countFilesAtPath(Path directoryPath) throws IOException {
    // get all files in resources folder
    long fileCount = Files.walk(directoryPath)
      .filter(Files::isRegularFile)
      .count();

    return fileCount;
  }

  private long countClassesAtPackage(String packageName) throws IOException {
    Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
    Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
    return classes.size();
  }

  @Test
  void testRegistryFields() throws IOException {
    // ensure that sources and destinations set as fields
    List<String> expectedFieldNames = Arrays.asList("sources", "destinations");
    List<String> actualFieldNames = getJsonFieldNames(ConnectorRegistry.class);
    assertTrue(expectedFieldNames.containsAll(actualFieldNames) );
  }

  @Test
  void testAllFilesGenerated() throws IOException {
    long inputYamlFileCount = countFilesAtPath(RESOURCE_DIRECTORY);

    // account for the __init__.py file
    long outputPythonFileCount = countFilesAtPath(PYTHON_OUTPUT_DIRECTORY) - 1;

    // count how many classes are in  io.airbyte.types.models
    long outputJavaClassCount = countClassesAtPackage("io.airbyte.types.models");

    assertTrue(outputJavaClassCount == inputYamlFileCount);
    assertTrue(outputJavaClassCount == outputPythonFileCount);
  }


}
