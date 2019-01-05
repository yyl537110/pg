package com.slj.pg.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by yaoyl on 2018/12/27.
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    private DateTimeFormatter formatter;
    private DateTimeFormatter spritFormatter;

    public LocalDateTimeDeserializer() {
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public LocalDateTimeDeserializer(DateTimeFormatter formatter, DateTimeFormatter spritFormatter) {
        this.formatter = formatter;
        this.spritFormatter = spritFormatter;
    }

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext context) throws IOException {
        Long value = p.getValueAsLong();
        if (value != 0) {
            return DateUtils.toDateTime(value);
        } else {
            String str = p.getValueAsString();
            if (StringUtils.isEmpty(str)) {
                return null;
            }
            if (str.contains("/")) {
                return LocalDateTime.parse(str, spritFormatter);
            }
            return LocalDateTime.parse(str, formatter);
        }
    }
}