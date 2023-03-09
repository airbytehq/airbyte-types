/*
 * Copyright (c) 2022 Airbyte, Inc., all rights reserved.
 */

package io.types.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.airbyte.types.models.ConnectorRegistry;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import org.junit.jupiter.api.Test;

class ConnectorRegistrySchemaTest {

  @Test
  void testFile() throws IOException {
    // TODO actually write a test
    assertTrue(true);
  }
//
//  @Test
//  void testFile() throws IOException {
//    final String schema = Files.readString(ConnectorRegistry.PROTOCOL.getFile().toPath(), StandardCharsets.UTF_8);
//    assertTrue(schema.contains("title"));
//  }
//
//  @Test
//  void testPrepareKnownSchemas() {
//    for (final AirbyteProtocolSchema value : AirbyteProtocolSchema.values()) {
//      assertTrue(Files.exists(value.getFile().toPath()));
//    }
//  }
//
//  @Test
//  void testJsonSchemaType() {
//    for (final AirbyteProtocolSchema value : AirbyteProtocolSchema.values()) {
//      assertTrue(Files.exists(value.getFile().toPath()));
//    }
//  }

}
