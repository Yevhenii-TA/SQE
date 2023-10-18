package baseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestBaseAPI {
    private final ObjectMapper objectMapper;

    public TestBaseAPI() {
        objectMapper = new ObjectMapper();
    }

    public String convertToJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

}
