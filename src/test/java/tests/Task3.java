package tests;

import baseTest.TestBaseAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.taskThree.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Task3 extends TestBaseAPI {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    @Test
    public void createUser() throws JsonProcessingException {
        User user = new User();
        user.setId(62);
        user.setUsername("testuser");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("testuser@gmail.com");
        user.setPassword("test123");
        user.setPhone("1234567890");
        user.setUserStatus(100);

        String requestBody = convertToJson(user);

        Response response = RestAssured.given().log().all()
                .contentType("application/json")
                .body(requestBody)
                .post(BASE_URL + user.CREATE_USER_ENDPOINT);

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(jsonPath.get("message").toString(), user.getId().toString());

    }
}
