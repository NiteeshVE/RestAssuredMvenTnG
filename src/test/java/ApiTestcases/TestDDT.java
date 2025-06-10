package ApiTestcases;

import ApiEndPoints.UserEndPoints;
import ApiPayload.user;
import ApiUtilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestDDT

{
    @Test(priority = 1, dataProvider ="AllData", dataProviderClass = DataProviders.class)
    public void testcreateuser(String userId, String userName, String fName, String lName,String eMail, String password, String phone)
    {
          // this test case will run 3 time

        user reqbody = new user();

        reqbody.setId(Integer.parseInt(userId));
        reqbody.setUsername(userName);
        reqbody.setFirstName(fName);
        reqbody.setLastName(lName);
        reqbody.setEmail(eMail);
        reqbody.setPassword(password);
        reqbody.setPhone(phone);

        Response response = UserEndPoints.createUser(reqbody);
        response.then().log().all();
        // Validations
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println("Test case 1 success !!");
    }
    @Test(priority = 2, dataProvider = "UserNamesData", dataProviderClass = DataProviders.class)
    public void testgetuser(String username)
    {
// this test case have some issue and will get ignored
        Response response=UserEndPoints.getUser(username);

        System.out.println(username);

        //logs
        response.then().log().all();

        //Validation
        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println("Test case 2 success !!");

    }



}


