package api.test;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserRequestEndPoint;
import io.restassured.response.Response;

public class APITests {

	public Logger logger;

	@BeforeClass
	public void setup() {
		// logs
		logger = LogManager.getLogger(this.getClass());
	}

	@Test
	public void testApiWithRandomInputs() {

		logger.info("=================Test Started================");

		for (int i = 0; i < 100; i++) {

			logger.info("Generate a random input between 1 and 200");
			int input = new Random().nextInt(200) + 1;

			String requestBody = "{ \"variable\": \"" + input + "\" }";

			Response response = UserRequestEndPoint.createRequest(requestBody);
			response.then().log().all();

			int expectedStatusCode;

			if (input >= 1 && input <= 100) {
				expectedStatusCode = 200;
			} else if (input >= 101 && input <= 200) {
				expectedStatusCode = 201;
			} else {
				expectedStatusCode = 401;
			}

			logger.info(" Verify the response status code using assertion");
			response.then().statusCode(expectedStatusCode);		

		}
		logger.info("================Test finished================");
	}
}
