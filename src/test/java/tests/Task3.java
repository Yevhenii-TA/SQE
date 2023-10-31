package tests;

import baseTest.TestBaseAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.ConfigReader;
import org.example.pageObject.taskThree.PetCategory;
import org.example.pageObject.taskThree.PetRequest;
import org.example.pageObject.taskThree.PetTags;
import org.example.pageObject.taskThree.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task3 extends TestBaseAPI {

    @Test(description = "1. Verify that allows creating a User")
    public void createUser() throws JsonProcessingException {
        User user = new User();
        user.setId(1941111)
                .setUsername("YIus2erwr")
                .setFirstName("qqIvanetrnko")
                .setLastName("wwUweser")
                .setEmail("YItqazcd23wst@gmail.com")
                .setPassword("test123")
                .setPhone("1234567890")
                .setUserStatus(500);

        String requestBody = convertToJson(user);
        Response response = RestAssured.given().log().all()
                .contentType("application/json")
                .accept("application/json")
                .body(requestBody).post(ConfigReader.getInstance().getProperty("BASE_API_URL") + ConfigReader.getInstance().getProperty("USER_ENDPOINT"));

        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not correct");
        Assert.assertEquals(response.body().jsonPath().get("message"), user.getId().toString(), "Users ID was is not correct in response");

        response = RestAssured.given().log().all()
                .accept("application/json")
                .get(ConfigReader.getInstance().getProperty("BASE_API_URL") + ConfigReader.getInstance().getProperty("USER_ENDPOINT") + "/" + user.getUsername());
        Assert.assertEquals(response.getBody().asString(), requestBody, "Response body has difference in comparison with request body");
    }

    @Test(description = "2. Verify that allows login as a User")
    public void loginUser() {
        User user = new User();
        user.setUsername(ConfigReader.getInstance().getProperty("ApiUserLogin"))
                .setPassword(ConfigReader.getInstance().getProperty("ApiUserPassword"));

        Response response = RestAssured.given().log().all()
                .queryParam("username", user.getUsername(), "password", user.getPassword())
                .accept("application/json")
                .get(ConfigReader.getInstance().getProperty("BASE_API_URL") + ConfigReader.getInstance().getProperty("LOGIN_USER_ENDPOINT"));

        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not correct");
        Assert.assertEquals(response.getBody().jsonPath().get("message").toString().contains("logged in user session"), true, "User was not logged in");
    }

    @Test(description = "3. Verify that allows creating the list of Users")
    public void createListOfUsers() throws JsonProcessingException {
        User user1 = new User();
        user1.setId(62623)
                .setUsername("testwwuser")
                .setFirstName("Test")
                .setLastName("User")
                .setEmail("testusbber@gmail.com")
                .setPassword("test123")
                .setPhone("1234567890")
                .setUserStatus(100);

        User user2 = new User();
        user2.setId(311)
                .setUsername("testuserer")
                .setFirstName("Tessdat")
                .setLastName("User")
                .setEmail("tesdstppuser@gmail.com")
                .setPassword("testasdsd123")
                .setPhone("123456547890")
                .setUserStatus(100);

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        String requestBody = convertToJson(userList);

        Response response = RestAssured.given().log().all()
                .contentType("application/json")
                .accept("application/json")
                .body(requestBody)
                .post(ConfigReader.getInstance().getProperty("BASE_API_URL") + ConfigReader.getInstance().getProperty("LIST_OF_USERS"));

        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not correct");
        Assert.assertEquals(response.getBody().jsonPath().get("message").toString(), "ok", "Response message is not correct or Users were not created");
    }

    @Test(description = "4. Verify that allows Log out User")
    public void logoutUser() throws JsonProcessingException {
        User user = new User();
        user.setUsername(ConfigReader.getInstance().getProperty("ApiUserLogin"))
                .setPassword(ConfigReader.getInstance().getProperty("ApiUserPassword"));

        String requestBody = convertToJson(user);

        Response response = RestAssured.given().log().all()
                .accept("application/json")
                .body(requestBody)
                .get(ConfigReader.getInstance().getProperty("BASE_API_URL") + ConfigReader.getInstance().getProperty("LOGIN_USER_ENDPOINT") + "?username=" + user.getUsername() + "&password=" + user.getPassword());
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not correct");
        Assert.assertEquals(response.getBody().jsonPath().get("message").toString().contains("logged in user session"), true, "User was not logged in");

        response = RestAssured.given().log().all()
                .accept("application/json")
                .get(ConfigReader.getInstance().getProperty("BASE_API_URL") + ConfigReader.getInstance().getProperty("LOGOUT_USER_ENDPOINT"));
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not correct");
        Assert.assertEquals(response.getBody().jsonPath().get("message").toString(), "ok", "Response message is not correct");
    }

    @Test(description = "5. Verify that allows adding a new Pet")
    public void createPet() throws JsonProcessingException {
        PetCategory petCategory = new PetCategory().setId(3).setName("Elephant");
        PetTags petTags = new PetTags().setId(1).setName("Big");

        List<String> photoUrls = Collections.singletonList("photoUrl1");
        List<PetTags> tags = Collections.singletonList(petTags);

        PetRequest petRequest = new PetRequest().setId(23)
                .setPetCategory(petCategory)
                .setName("Dolphin")
                .setPhotoUrls(photoUrls)
                .setTags(tags)
                .setStatus("available");

        String requestBody = convertToJson(petRequest);

        Response response = RestAssured.given().log().all()
                .contentType("application/json")
                .accept("application/json")
                .body(requestBody)
                .post(ConfigReader.getInstance().getProperty("BASE_API_URL") + ConfigReader.getInstance().getProperty("PET_ENDPOINT"));

        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not correct");
        Assert.assertEquals(response.getBody().asString(), requestBody, "Response body has differences in comparison to request body");
    }

    @Test(description = "6. Verify that allows updating Pet’s image")
    public void updatePetImage() throws JsonProcessingException {
        PetCategory petCategory = new PetCategory().setId(32).setName("Dog-Cat");
        PetTags petTags = new PetTags().setId(13).setName("Small");

        List<String> photoUrls = Collections.singletonList("someURL");
        List<String> photoUrlNew = Collections.singletonList("updatedURL");
        List<PetTags> tags = Collections.singletonList(petTags);

        PetRequest petRequestOriginal = new PetRequest()
                .setId(63)
                .setPetCategory(petCategory)
                .setName("pet66")
                .setPhotoUrls(photoUrls)
                .setTags(tags)
                .setStatus("available");

        String requestBodyOriginal = convertToJson(petRequestOriginal);
        RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .body(requestBodyOriginal)
                .post(ConfigReader.getInstance().getProperty("BASE_API_URL") + ConfigReader.getInstance().getProperty("PET_ENDPOINT"));

        PetRequest petRequestNew = new PetRequest()
                .setId(63)
                .setPetCategory(petCategory)
                .setName("pet66")
                .setPhotoUrls(photoUrlNew)
                .setTags(tags)
                .setStatus("available");

        String requestBodyNew = convertToJson(petRequestNew);
        Response response = RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .body(requestBodyNew)
                .put(ConfigReader.getInstance().getProperty("BASE_API_URL") + ConfigReader.getInstance().getProperty("PET_ENDPOINT"));

        Assert.assertEquals(response.getBody().asString(), requestBodyNew, "Response body is not updated");
    }

    @Test(description = "7. Verify that allows updating Pet’s name and status")
    public void updatePetNameStatus() throws JsonProcessingException {
        PetCategory petCategory = new PetCategory().setId(6).setName("Fish");
        PetTags petTags = new PetTags().setId(254).setName("Middle");

        List<String> photoUrls = Collections.singletonList("someURL");
        List<PetTags> tags = Collections.singletonList(petTags);

        PetRequest petRequestOriginal = new PetRequest().setId(77)
                .setPetCategory(petCategory)
                .setName("pet77")
                .setPhotoUrls(photoUrls)
                .setTags(tags)
                .setStatus("available");

        String requestBodyOriginal = convertToJson(petRequestOriginal);
        RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .body(requestBodyOriginal)
                .post(ConfigReader.getInstance().getProperty("BASE_API_URL") + ConfigReader.getInstance().getProperty("PET_ENDPOINT"));

        PetRequest petRequestNew = new PetRequest().setId(63)
                .setPetCategory(petCategory)
                .setName("pet66UPDATE")
                .setPhotoUrls(photoUrls)
                .setTags(tags)
                .setStatus("NOTavailable");

        String requestBodyNew = convertToJson(petRequestNew);
        Response response = RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .body(requestBodyNew)
                .put(ConfigReader.getInstance().getProperty("BASE_API_URL") + ConfigReader.getInstance().getProperty("PET_ENDPOINT"));

        Assert.assertEquals(response.getBody().asString(), requestBodyNew, "Response body is not updated");
    }

    @Test(description = "8. Verify that allows deleting Pet ")
    public void deletePet() throws JsonProcessingException {
        PetCategory petCategory = new PetCategory().setId(99).setName("Birds");
        PetTags petTags = new PetTags().setId(25).setName("In cage");

        List<String> photoUrls = Collections.singletonList("someURL");
        List<PetTags> tags = Collections.singletonList(petTags);

        PetRequest petRequest = new PetRequest()
                .setId(889)
                .setPetCategory(petCategory)
                .setName("pet666111")
                .setPhotoUrls(photoUrls)
                .setTags(tags)
                .setStatus("undefined");

        String requestBody = convertToJson(petRequest);
        Response responseCreate = RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .body(requestBody)
                .post(ConfigReader.getInstance().getProperty("BASE_API_URL") + ConfigReader.getInstance().getProperty("PET_ENDPOINT"));
        Assert.assertEquals(responseCreate.getBody().asString(), requestBody, "Response body is not correct in comparison with request body");

        Response responseDelete = RestAssured.given()
                .accept("application/json")
                .body(requestBody)
                .delete(ConfigReader.getInstance().getProperty("BASE_API_URL") + ConfigReader.getInstance().getProperty("PET_ENDPOINT") + "/" + petRequest.getId());
        Assert.assertEquals(responseDelete.getStatusCode(), 200, "Status code is not correct");

        Response responseCheck = RestAssured.given()
                .accept("application/json")
                .get(ConfigReader.getInstance().getProperty("BASE_API_URL") + ConfigReader.getInstance().getProperty("PET_ENDPOINT") + "/" + petRequest.getId());
        Assert.assertEquals(responseCheck.getStatusCode(), 404, "Status code is not correct");
        Assert.assertEquals(responseCheck.body().jsonPath().get("message"), "Pet not found", "Pet was not deleted");
    }
}
