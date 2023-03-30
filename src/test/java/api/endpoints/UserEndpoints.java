package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndpoints
{
    private UserEndpoints() {}
    static ResourceBundle getURL()
    {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("route");
        return resourceBundle;
    }

    public static Response createUser(User payload)
    {
        String postUrl = getURL().getString("post_url");
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
        .when()
                .post(postUrl);
        return response;
    }

    public static Response readUser(String username)
    {
        String getUrl = getURL().getString("get_url");
        Response response = given()
                .pathParam("username", username)
        .when()
                .get(Routes.getUrl);
        return response;
    }

    public static Response updateUser(String username, User payload)
    {
        String putUrl = getURL().getString("put_url");
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .body(payload)
        .when()
                .put(Routes.updateUrl);
        return response;
    }

    public static Response deleteUser(String username)
    {
        String deleteUser = getURL().getString("delete_user");
        Response response = given()
                .pathParam("username", username)
        .when()
                .delete(Routes.deleteUrl);
        return response;
    }
}
