package fr.insee.pogues.rest.utils;

import com.jayway.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.jayway.restassured.RestAssured.expect;

/**
 * Created by acordier on 06/07/17.
 */
public class RestAssuredConfig {

    private static Logger logger = LogManager.getLogger(RestAssuredConfig.class);

    public static String fakejUsername = "D5WQNO";
    public static String fakejPassword = "D5WQNO";
    public static String jUserPermission = "DG75-L120";


    public static void configure() {
        configure(fakejUsername, fakejPassword);
    }

    public static void configure(String username, String password) {
        RestAssured.baseURI = "http://localhost:8080/rmspogfo";
        /* All this boilerplate thing to handle Form auth with tomcat */
        String sessionId;
        sessionId = expect()
                .statusCode(200)
                .log().all()
                .when()
                .get("/notfound")
                .sessionId();
        sessionId = expect().statusCode(302)
                .log().all()
                .given()
                .param("username", username)
                .param("password", password).cookie("JSESSIONID", sessionId)
                .post("/login")
                .sessionId();
        expect()
                .statusCode(404)
                .log().all()
                .given().cookie("JSESSIONID", sessionId)
                .when()
                .get("/notfound");
        RestAssured.sessionId = sessionId;
    }


}
