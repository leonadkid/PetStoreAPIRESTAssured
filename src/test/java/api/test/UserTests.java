package api.test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests
{
    Faker faker;
    User userPayload;
    Logger logger;
    @BeforeClass
    public void setup()
    {
        logger = LogManager.getLogger(this.getClass());

        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test(priority = 1)
    public void testPostUser()
    {
        logger.info("************** Creating User **********************");
        Response response = UserEndpoints.createUser(userPayload);
        Assert.assertEquals(response.statusCode(), 200);
        logger.info("************** User is Created **********************");
    }

    @Test(priority = 2)
    public  void testGetUser()
    {
        logger.info("************** Reading User Info **********************");
        Response response = UserEndpoints.readUser(this.userPayload.getUsername());
        Assert.assertEquals(response.statusCode(), 200);
        logger.info("************** User info is displayed **********************");
    }

    @Test(priority = 3)
    public void testUpdateDataByUser()
    {
        logger.info("************** Updating User **********************");
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response updateResponse = UserEndpoints.updateUser(this.userPayload.getUsername(), userPayload);
        Assert.assertEquals(updateResponse.statusCode(), 200);

        //Check the fName, lName, email is update correctly
        Response readResponse = UserEndpoints.readUser(this.userPayload.getUsername());

        Assert.assertTrue(readResponse.getBody().asString().contains(this.userPayload.getFirstName()));
        Assert.assertTrue(readResponse.getBody().asString().contains(this.userPayload.getLastName()));
        Assert.assertTrue(readResponse.getBody().asString().contains(this.userPayload.getEmail()));
        logger.info("************** User is updated **********************");
    }

    @Test(priority = 4)
    public void testDeleteUserByUsername()
    {
        logger.info("************** Deleting User **********************");
        Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.statusCode(), 200);
        logger.info("************** User is deleted **********************");
    }
}
