package com.serene.tests.features.steps;

import org.junit.Assert;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import net.thucydides.core.annotations.Step;


public class PetAPISteps {

    private Response res = null; //Response
    private JsonPath jp = null; //JsonPath
    private RequestSpecification requestSpec;

    @Step
    public void buildRequestGetPetByID(String petID) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBasePath(petID);
        builder.setContentType("application/json");
        //System.out.println("{\"username\":\""+username+"\",\"password\": \""+password+"\"}");
        //builder.setBody("{\"petID\":\""+petID+"\"}");
        requestSpec = builder.build();
        requestSpec = RestAssured.given().spec(requestSpec);
        requestSpec.log().all();

    }

    @Step
    public void sendGetPetByIDRequest() {
        res = requestSpec.when().get();
    }

    @Step
    public void verifyGetPetByIDSuccess() {
        jp = res.jsonPath();
        Assert.assertEquals("Status Check Failed!", 200, res.getStatusCode());
    }



    @Step
    public void buildRequestCreatePet(String petID, String petName, String petStatus) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        // builder.setBasePath();
        builder.setContentType("application/json");
        //System.out.println("{\"username\":\""+username+"\",\"password\": \""+password+"\"}");
        builder.setBody("{\"id\":\"" + petID + "\", \"name\":\"" + petName + "\" , \"status\":\"" + petStatus + "\"}");
        requestSpec = builder.build();
        requestSpec = RestAssured.given().spec(requestSpec);
        requestSpec.log().all();

    }

    @Step
    public void sendPostCreatePetRequest() {
        res = requestSpec.when().post();
    }

    @Step
    public void verifyCreatePetSuccess() {
        jp = res.jsonPath();
        Assert.assertEquals("Status Check Failed!", 200, res.getStatusCode());
    }

}


