package club.lemos.common.jackson;

import club.lemos.common.utils.StringPool;
import club.lemos.common.utils.StringUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class JsonUtil {

    public static <T> String toJson(T value) {
        try {
            return getInstance().writeValueAsString(value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @SneakyThrows
    public static byte[] toJsonAsBytes(Object object) {
        return getInstance().writeValueAsBytes(object);
    }

    public static <T> T parse(String content, Class<T> valueType) {
        try {
            return getInstance().readValue(content, valueType);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @SneakyThrows
    public static <T> T parse(String content, TypeReference<T> typeReference) {
        return getInstance().readValue(content, typeReference);
    }

    @SneakyThrows
    public static <T> T parse(byte[] bytes, Class<T> valueType) {
        return getInstance().readValue(bytes, valueType);
    }

    @SneakyThrows
    public static <T> T parse(byte[] bytes, TypeReference<T> typeReference) {
        return getInstance().readValue(bytes, typeReference);
    }

    @SneakyThrows
    public static <T> T parse(InputStream in, Class<T> valueType) {
        return getInstance().readValue(in, valueType);
    }

    @SneakyThrows
    public static <T> T parse(InputStream in, TypeReference<T> typeReference) {
        return getInstance().readValue(in, typeReference);
    }

    public static <T> List<T> parseArray(String content, Class<T> valueTypeRef) {
        try {

            if (!StringUtil.startsWithIgnoreCase(content, StringPool.LEFT_SQ_BRACKET)) {
                content = StringPool.LEFT_SQ_BRACKET + content + StringPool.RIGHT_SQ_BRACKET;
            }

            List<Map<String, Object>> list = getInstance().readValue(content, new TypeReference<List<Map<String, Object>>>() {
            });
            List<T> result = new ArrayList<>();
            for (Map<String, Object> map : list) {
                result.add(toPojo(map, valueTypeRef));
            }
            return result;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static Map<String, Object> toMap(String content) {
        try {
            return getInstance().readValue(content, Map.class);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static <T> Map<String, T> toMap(String content, Class<T> valueTypeRef) {
        try {
            Map<String, Map<String, Object>> map = getInstance().readValue(content, new TypeReference<Map<String, Map<String, Object>>>() {
            });
            Map<String, T> result = new HashMap<>(16);
            for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
                result.put(entry.getKey(), toPojo(entry.getValue(), valueTypeRef));
            }
            return result;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static <T> T toPojo(Map fromValue, Class<T> toValueType) {
        return getInstance().convertValue(fromValue, toValueType);
    }

    @SneakyThrows
    public static JsonNode readTree(String jsonString) {
        return getInstance().readTree(jsonString);
    }

    @SneakyThrows
    public static JsonNode readTree(InputStream in) {
        return getInstance().readTree(in);
    }

    @SneakyThrows
    public static JsonNode readTree(byte[] content) {
        return getInstance().readTree(content);
    }

    @SneakyThrows
    public static JsonNode readTree(JsonParser jsonParser) {
        return getInstance().readTree(jsonParser);
    }

    public static ObjectMapper getInstance() {
        ObjectMapper instance = JacksonHolder.INSTANCE;
        instance.registerModule(new JavaTimeModule());
        return instance;
    }

    private static class JacksonHolder {
        private static final ObjectMapper INSTANCE = new ObjectMapper();
    }

}