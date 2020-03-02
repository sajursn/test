package com.travanleo.user.dto;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * Date serializer to conver localdate to json.
 * 
 *
 */
public class LocalDateSerializer extends StdSerializer<LocalDate> {

  /**
  * 
  */
  private static final long serialVersionUID = -5090373806129107640L;

  protected LocalDateSerializer() {
    super(LocalDate.class);
  }

  @Override
  public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider provider)
    throws IOException {
    gen.writeString(value.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));

  }

}
