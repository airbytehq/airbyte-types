/*
 * Copyright (c) 2022 Airbyte, Inc., all rights reserved.
 */

package io.types.models;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.airbyte.types.models.ConnectorRegistry;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import org.junit.jupiter.api.Test;



import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

class ConnectorRegistrySchemaTest {
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

  @Test
  void testFile() throws IOException {
    // ensure that sources and destinations set as fields
    List<String> expectedFieldNames = Arrays.asList("sources", "destinations", "C");
    List<String> actualFieldNames = getJsonFieldNames(ConnectorRegistry.class);
    assertTrue(expectedFieldNames.containsAll(actualFieldNames) );
  }
  //
  // @Test
  // void testFile() throws IOException {
  // final String schema = Files.readString(ConnectorRegistry.PROTOCOL.getFile().toPath(),
  // StandardCharsets.UTF_8);
  // assertTrue(schema.contains("title"));
  // }
  //
  // @Test
  // void testPrepareKnownSchemas() {
  // for (final AirbyteProtocolSchema value : AirbyteProtocolSchema.values()) {
  // assertTrue(Files.exists(value.getFile().toPath()));
  // }
  // }
  //
  // @Test
  // void testJsonSchemaType() {
  // for (final AirbyteProtocolSchema value : AirbyteProtocolSchema.values()) {
  // assertTrue(Files.exists(value.getFile().toPath()));
  // }
  // }

}
