package ApiEndPoints;
import ApiPayload.user;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserEndPoints
{

public static Response createUser(user payload)
{

    Response response=
            given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(payload)

            .when()
            .post(Routs.post_url);
             return response;

}

    public static Response getUser(String userName)
    {

        Response response=
                given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("username", userName)

                .when()
                .get(Routs.get_url);

        return response;

    }


    public static  Response putUser(String userName, user payload)
    {

        Response response=
                given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("username", userName)
                .body(payload)

                .when()
                .put(Routs.put_url);

        return response;

    }

    public static  Response deletUser(String userName)
    {
        Response response=given()
                .accept(ContentType.JSON)
                .pathParam("username", userName)

                .when()
                .delete(Routs.del_url);

        return response;

    }


}
