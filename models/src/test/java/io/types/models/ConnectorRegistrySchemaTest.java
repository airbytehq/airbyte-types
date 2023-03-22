/*
 * Copyright (c) 2022 Airbyte, Inc., all rights reserved.
 */

package io.types.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
  final String JAVA_OUTPUT_PACKAGE = "io.airbyte.types.models";

  private static List<String> getJsonFieldNames(final Class<?> clazz) {
    final List<String> fieldNames = new ArrayList<>();
    final Field[] fields = clazz.getDeclaredFields();

    for (final Field field : fields) {
      final JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
      if (jsonProperty != null) {
        fieldNames.add(field.getName());
      }
    }

    return fieldNames;
  }

  private long countFilesAtPath(final Path directoryPath) throws IOException {
    // get all files in resources folder
    return Files.walk(directoryPath)
      .filter(Files::isRegularFile)
      .count();
  }

  private long countClassesAtPackage(final String packageName) {
    final Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
    final Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
    return classes.size();
  }

  @Test
  void testRegistryFields() throws IOException {
    // ensure that sources and destinations set as fields
    final List<String> expectedFieldNames = Arrays.asList("sources", "destinations");
    final List<String> actualFieldNames = getJsonFieldNames(ConnectorRegistry.class);
    assertTrue(expectedFieldNames.containsAll(actualFieldNames) );
  }

  @Test
  void testAllFilesGenerated() throws IOException {
    final long inputYamlFileCount = countFilesAtPath(RESOURCE_DIRECTORY);

    // account for the __init__.py file
    final long outputPythonFileCount = countFilesAtPath(PYTHON_OUTPUT_DIRECTORY) - 1;

    // count how many classes are in  io.airbyte.types.models
    final long outputJavaClassCount = countClassesAtPackage(JAVA_OUTPUT_PACKAGE);

    assertEquals(outputJavaClassCount, inputYamlFileCount);
    assertEquals(outputJavaClassCount, outputPythonFileCount);
  }


}
