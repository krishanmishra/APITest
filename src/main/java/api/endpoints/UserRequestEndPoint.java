package api.endpoints;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserRequestEndPoint {	
	
	
	public static Response createRequest(String payload) {		

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
			.when()
				.post(Routes.post_url);

		return response;

	}

	

}
