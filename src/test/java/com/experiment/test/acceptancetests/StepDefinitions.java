package com.experiment.test.acceptancetests;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.experiment.test.model.InputEntity;
import com.experiment.test.model.OutputEntity;

public class StepDefinitions {
	private String BASE_URL = "http://localhost:8082";
	public StepDefinitions() throws IOException {
		try {
		java.io.InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties");
		java.util.Properties properties = new Properties();
		properties.load(inputStream);
		BASE_URL = properties.getProperty("app.url");
		}
		catch(Exception e) {
		FileInputStream inputStream =  new FileInputStream("src/test/resources/app.properties");
		java.util.Properties properties = new Properties();
		properties.load(inputStream);
		BASE_URL = properties.getProperty("app.url");
		}
	}
	Response response;
	@When("I want to add {int} and {int}")
	public void i_want_to_add_and(Integer int1, Integer int2) {
		String endpoint = "/addition";
		InputEntity entity = new InputEntity();
		entity.setA(int1);
		entity.setB(int2);
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
	    request.body(entity);
	    response = request.post(endpoint);
	}
	@Then("Addition of {int} and {int} should be {int}")
	public void addition_of_and_should_be(Integer int1, Integer int2, Integer int3) {
		assertEquals(response.getStatusCode(),200);
		long answer = response.getBody().as(OutputEntity.class).getAnswer();
		assertEquals(Long.valueOf(int3),Long.valueOf(answer));
	}
	
	@When("I want to subtract {int} and {int}")
	public void i_want_to_subtract_and(Integer int1, Integer int2) {
		String endpoint = "/subtraction";
		InputEntity entity = new InputEntity();
		entity.setA(int1);
		entity.setB(int2);
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
	    request.body(entity);
	    response = request.post(endpoint);
	}
	@Then("Subtraction of {int} and {int} should be {int}")
	public void subtraction_of_and_should_be(Integer int1, Integer int2, Integer int3) {
		assertEquals(response.getStatusCode(),200);
		long answer = response.getBody().as(OutputEntity.class).getAnswer();
		assertEquals(Long.valueOf(int3),Long.valueOf(answer));
	}
	
	@When("I want to multiply {int} and {int}")
	public void i_want_to_multiply_and(Integer int1, Integer int2) {
		String endpoint = "/multiplication";
		InputEntity entity = new InputEntity();
		entity.setA(int1);
		entity.setB(int2);
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
	    request.body(entity);
	    response = request.post(endpoint);
	}
	@Then("Multiplication of {int} and {int} should be {int}")
	public void multiplication_of_and_should_be(Integer int1, Integer int2, Integer int3) {
		assertEquals(response.getStatusCode(),200);
		long answer = response.getBody().as(OutputEntity.class).getAnswer();
		assertEquals(Long.valueOf(int3),Long.valueOf(answer));
	}
	
	@When("I want to divide {int} and {int}")
	public void i_want_to_divide_and(Integer int1, Integer int2) {
		String endpoint = "/division";
		InputEntity entity = new InputEntity();
		entity.setA(int1);
		entity.setB(int2);
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
	    request.body(entity);
	    response = request.post(endpoint);
	}
	@Then("Division of {int} and {int} should be {int}")
	public void division_of_and_should_be(Integer int1, Integer int2, Integer int3) {
		assertEquals(response.getStatusCode(),200);
		long answer = response.getBody().as(OutputEntity.class).getAnswer();
		assertEquals(Long.valueOf(int3),Long.valueOf(answer));
	}

	@Then("Division of {int} and {int} should fail")
	public void division_of_and_should_fail(Integer int1, Integer int2) {
		assertEquals(response.getStatusCode(),500);
	}
}
