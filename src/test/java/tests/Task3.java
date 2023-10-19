package tests;

import baseTest.TestBaseAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.taskThree.PetRequest.PetCategory;
import org.example.taskThree.PetRequest.PetRequest;
import org.example.taskThree.PetRequest.PetTags;
import org.example.taskThree.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task3 extends TestBaseAPI {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    @Test(description = "1. Verify that allows creating a User")
    public void createUser() throws JsonProcessingException {
        User user = new User();
        user.setId(19943);
        user.setUsername("YI us2er");
        user.setFirstName("qqIvanenko");
        user.setLastName("wwUser");
        user.setEmail("YItesfewst@gmail.com");
        user.setPassword("test123");
        user.setPhone("1234567890");
        user.setUserStatus(900);

        String requestBody = convertToJson(user);
        Response response = RestAssured.given().log().all().contentType("application/json").accept("application/json").body(requestBody).post(BASE_URL + user.CREATE_USER_ENDPOINT);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.body().jsonPath().get("message"), user.getId().toString());

        response = RestAssured.given().log().all().accept("application/json").get(BASE_URL + user.GET_USER_ENDPOINT + user.getUsername());
        Assert.assertEquals(response.getBody().asString(), requestBody);
    }
    @Test(description = "2. Verify that allows login as a User")
    public void loginUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("test123");

        Response response = RestAssured.given().log().all().accept("application/json").get(BASE_URL + user.LOGIN_USER_ENDPOINT + "?username=" + user.getUsername() + "&password=" + user.getPassword());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getBody().jsonPath().get("message").toString().contains("logged in user session"), true);
    }
    @Test(description = "3. Verify that allows creating the list of Users")
    public void createListOfUsers() throws JsonProcessingException {
        User user1 = new User();
        user1.setId(52143);
        user1.setUsername("testwwuser");
        user1.setFirstName("Test");
        user1.setLastName("User");
        user1.setEmail("testusbber@gmail.com");
        user1.setPassword("test123");
        user1.setPhone("1234567890");
        user1.setUserStatus(100);

        User user2 = new User();
        user2.setId(56266);
        user2.setUsername("testuserer");
        user2.setFirstName("Tessdat");
        user2.setLastName("User");
        user2.setEmail("tesdstppuser@gmail.com");
        user2.setPassword("testasdsd123");
        user2.setPhone("123456547890");
        user2.setUserStatus(100);

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        String requestBody = convertToJson(userList);

        Response response = RestAssured.given().log().all().contentType("application/json").accept("application/json").body(requestBody).post(BASE_URL + User.CREATE_USER_LIST_ENDPOINT);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getBody().jsonPath().get("message") .toString(), "ok");
    }
    @Test(description = "4. Verify that allows Log out User")
    public void logoutUser() throws JsonProcessingException {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("test123");

        String requestBody = convertToJson(user);

        Response response = RestAssured.given().log().all().accept("application/json").body(requestBody).get(BASE_URL + user.LOGIN_USER_ENDPOINT + "?username=" + user.getUsername() + "&password=" + user.getPassword());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getBody().jsonPath().get("message").toString().contains("logged in user session"), true);

        response = RestAssured.given().log().all().accept("application/json").get(BASE_URL + user.LOGOUT_USER_ENDPOINT);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getBody().jsonPath().get("message").toString(), "ok");
    }
    @Test(description = "5. Verify that allows adding a new Pet")
    public void createPet() throws JsonProcessingException {
        PetCategory petCategory = new PetCategory(3, "Elephant");
        PetTags petTag = new PetTags(1, "Big");
        List<String> photoUrls = Collections.singletonList("photoUrl1");
        List<PetTags> tags = Collections.singletonList(petTag);

        PetRequest petRequest = new PetRequest();
        petRequest.setId(23);
        petRequest.setPetCategory(petCategory);
        petRequest.setName("Dolphin");
        petRequest.setPhotoUrls(photoUrls);
        petRequest.setTags(tags);
        petRequest.setStatus("available");

        String requestBody = convertToJson(petRequest);

        Response response = RestAssured.given().log().all().contentType("application/json").accept("application/json").body(requestBody).post(BASE_URL + petRequest.CREATE_PET_ENDPOINT);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getBody().asString(), requestBody);
    }
    @Test(description = "6. Verify that allows updating Pet’s image")
    public void updatePetImage() throws JsonProcessingException {
        PetCategory petCategory = new PetCategory(3, "Dog-Cat");
        PetTags petTag = new PetTags(3, "Small");
        List<String> photoUrls = Collections.singletonList("someURL");
        List<String> photoUrlNew = Collections.singletonList("updatedURL");
        List<PetTags> tags = Collections.singletonList(petTag);

        PetRequest petRequestOriginal = new PetRequest(63, petCategory, "pet66", photoUrls, tags, "available");
        String requestBodyOriginal = convertToJson(petRequestOriginal);
        RestAssured.given().contentType("application/json").accept("application/json").body(requestBodyOriginal).post(BASE_URL + petRequestOriginal.CREATE_PET_ENDPOINT);

        PetRequest petRequestNew = new PetRequest(63, petCategory, "pet66", photoUrlNew, tags, "available");
        String requestBodyNew = convertToJson(petRequestNew);
        Response response = RestAssured.given().contentType("application/json").accept("application/json").body(requestBodyNew).put(BASE_URL + petRequestNew.CREATE_PET_ENDPOINT);

        Assert.assertEquals(response.getBody().asString(), requestBodyNew);
    }
    @Test(description = "7. Verify that allows updating Pet’s name and status")
    public void updatePetNameStatus() throws JsonProcessingException {
        PetCategory petCategory = new PetCategory(7, "Fish");
        PetTags petTag = new PetTags(77, "Small");
        List<String> photoUrls = Collections.singletonList("someURL");
        List<PetTags> tags = Collections.singletonList(petTag);

        PetRequest petRequestOriginal = new PetRequest(77, petCategory, "pet77", photoUrls, tags, "available");
        String requestBodyOriginal = convertToJson(petRequestOriginal);
        RestAssured.given().contentType("application/json").accept("application/json").body(requestBodyOriginal).post(BASE_URL + petRequestOriginal.CREATE_PET_ENDPOINT);

        PetRequest petRequestNew = new PetRequest(63, petCategory, "pet66UPDATE", photoUrls, tags, "NOTavailable");
        String requestBodyNew = convertToJson(petRequestNew);
        Response response = RestAssured.given().contentType("application/json").accept("application/json").body(requestBodyNew).put(BASE_URL + petRequestNew.CREATE_PET_ENDPOINT);

        Assert.assertEquals(response.getBody().asString(), requestBodyNew);
    }
    @Test(description = "8. Verify that allows deleting Pet ")
    public void deletePet() throws JsonProcessingException {
        PetCategory petCategory = new PetCategory(8, "Fish");
        PetTags petTag = new PetTags(88, "Small");
        List<String> photoUrls = Collections.singletonList("someURL");
        List<PetTags> tags = Collections.singletonList(petTag);

        PetRequest petRequest = new PetRequest(889, petCategory, "pet8898", photoUrls, tags, "available");
        String requestBody = convertToJson(petRequest);
        Response responseCreate = RestAssured.given().contentType("application/json").accept("application/json").body(requestBody).post(BASE_URL + petRequest.CREATE_PET_ENDPOINT);
        Assert.assertEquals(responseCreate.getBody().asString(), requestBody);

        Response responseDelete = RestAssured.given().accept("application/json").body(requestBody).delete(BASE_URL + petRequest.CREATE_PET_ENDPOINT + "/" + petRequest.getId());
        Assert.assertEquals(responseDelete.getStatusCode(), 200);

        Response responseCheck = RestAssured.given().accept("application/json").get(BASE_URL + petRequest.CREATE_PET_ENDPOINT + "/" + petRequest.getId());
        Assert.assertEquals(responseCheck.getStatusCode(), 404);
        Assert.assertEquals(responseCheck.body().jsonPath().get("message"), "Pet not found", "Pet was not deleted");
    }
}
