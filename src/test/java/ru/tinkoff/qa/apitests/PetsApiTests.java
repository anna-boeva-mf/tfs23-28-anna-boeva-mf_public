package ru.tinkoff.qa.apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.tinkoff.qa.hibernate.apimodels.Category;
import ru.tinkoff.qa.hibernate.apimodels.Pet;
import ru.tinkoff.qa.hibernate.apimodels.TagsItem;

import java.util.Arrays;

public class PetsApiTests {
    Pet petRequest;

    @BeforeEach
    public void init() {
        petRequest = new Pet();
        petRequest.setId(2311453);
        petRequest.setName("doggie");
        petRequest.setStatus("available");
        petRequest.setCategory(new Category(0, "string"));
        petRequest.setTags(Arrays.asList(new TagsItem(0, "string")));
        petRequest.setPhotoUrls(Arrays.asList("string"));

        RestAssured.given().contentType(ContentType.JSON)
                .delete("https://petstore.swagger.io/v2/pet/" + petRequest.getId());
    }

    @Test
    public void addTest() {
        Pet petResponse = RestAssured.given().contentType(ContentType.JSON)
                .body(petRequest).post("https://petstore.swagger.io/v2/pet").as(Pet.class);
        Assertions.assertAll(() -> Assertions.assertEquals(petRequest.getName(), petResponse.getName(), "Check name of new added pet"),
                () -> Assertions.assertEquals(petRequest.getStatus(), petResponse.getStatus(), "Check status of new added pet"),
                () -> Assertions.assertEquals(petRequest.getPhotoUrls(), petResponse.getPhotoUrls(), "Check photoUrls of new added pet"),
                () -> Assertions.assertEquals(petRequest.getCategory().getId(), petResponse.getCategory().getId(), "Check category.id of new added pet"),
                () -> Assertions.assertEquals(petRequest.getCategory().getName(), petResponse.getCategory().getName(), "Check category.name of new added pet"),
                () -> Assertions.assertEquals(petRequest.getTags().get(0).getId(), petResponse.getTags().get(0).getId(), "Check tags[0].id of new added pet"),
                () -> Assertions.assertEquals(petRequest.getTags().get(0).getName(), petResponse.getTags().get(0).getName(), "Check tags[0].name of new added pet"));
    }

    @Test
    public void addCodeTest() {
        RestAssured.given().contentType(ContentType.JSON)
                .body(petRequest).post("https://petstore.swagger.io/v2/pet").then().statusCode(200);
    }

    @Test
    public void addIncorrectBodyCodeTest() {
        RestAssured.given().contentType(ContentType.JSON)
                .body("dd").post("https://petstore.swagger.io/v2/pet").then().statusCode(400);
    }

    @Test
    public void deleteTest() {
        addTest();
        RestAssured.given().contentType(ContentType.JSON)
                .delete("https://petstore.swagger.io/v2/pet/" + petRequest.getId())
                .then().statusCode(200);
    }

    @Test
    public void deleteNonExistingTestCode() {
        addTest();
        deleteTest();
        RestAssured.given().contentType(ContentType.JSON)
                .delete("https://petstore.swagger.io/v2/pet/" + petRequest.getId())
                .then().statusCode(404);
    }

    @Test
    public void getTest() {
        addTest();
        RestAssured.given().contentType(ContentType.JSON)
                .get("https://petstore.swagger.io/v2/pet/" + petRequest.getId())
                .then().statusCode(200);
    }

    @Test
    public void getNonExistingPetTest() {
        RestAssured.given().contentType(ContentType.JSON)
                .get("https://petstore.swagger.io/v2/pet/" + petRequest.getId()).then().statusCode(404);
    }

    @Test
    public void putNewNameTest() {
        addTest();
        String newName = "doggie1234";
        petRequest.setName(newName);
        Pet petResponse = RestAssured.given().contentType(ContentType.JSON)
                .body(petRequest).put("https://petstore.swagger.io/v2/pet").as(Pet.class);
        Assertions.assertEquals(newName, petResponse.getName(), "Check name after changing");
    }

    @Test
    public void putNewNameCodeTest() {
        addTest();
        String newName = "doggie1234";
        petRequest.setName(newName);
        RestAssured.given().contentType(ContentType.JSON)
                .body(petRequest).put("https://petstore.swagger.io/v2/pet").then().statusCode(200);
    }

    @Test
    public void putNonExistingPetCodeTest() {
        RestAssured.given().contentType(ContentType.JSON)
                .body(petRequest).put("https://petstore.swagger.io/v2/pet").then().statusCode(200);
    }

    @Test
    public void putUnchangedPetCodeTest() {
        addTest();
        RestAssured.given().contentType(ContentType.JSON)
                .body(petRequest).put("https://petstore.swagger.io/v2/pet").then().statusCode(200);
    }
}
