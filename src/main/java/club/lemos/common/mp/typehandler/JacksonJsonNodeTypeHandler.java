package club.lemos.common.mp.typehandler;

import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.io.IOException;

@Slf4j
@MappedTypes({JsonNode.class})
@MappedJdbcTypes(value=JdbcType.VARCHAR)
public class JacksonJsonNodeTypeHandler extends AbstractJsonTypeHandler<JsonNode> {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final Class<JsonNode> type = JsonNode.class;

    @Override
    protected JsonNode parse(String json) {
        try {
            return objectMapper.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected String toJson(JsonNode obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
