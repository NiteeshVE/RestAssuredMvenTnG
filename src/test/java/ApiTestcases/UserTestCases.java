package ApiTestcases;

import ApiEndPoints.UserEndPoints;
import ApiPayload.user;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTestCases
{
      Faker faker;
      user reqbody;
      @BeforeClass
        public void generatefakedata()
        {

            faker=new Faker();
            reqbody=new user();

            reqbody.setId(faker.idNumber().hashCode());
            reqbody.setUsername(faker.name().username());
            reqbody.setFirstName(faker.name().firstName());
            reqbody.setLastName(faker.name().lastName());
            reqbody.setEmail(faker.internet().safeEmailAddress());
            reqbody.setPassword(faker.internet().password(5,10));
            reqbody.setPhone(faker.phoneNumber().cellPhone());
            //reqbody.setUserStatus(0);


        }
              @Test(priority = 1)
              public void testcreateuser() {

                  Response response = UserEndPoints.createUser(reqbody);
                  System.out.println("Username used: " + reqbody.getUsername());
                  response.then().log().all();
                  // Validations
                  Assert.assertEquals(response.getStatusCode(), 200);
                  System.out.println("Status code is correct");
              }


                @Test(priority = 2)
                public void testgetuser()
                {
                   UserEndPoints.createUser(reqbody);
                    Response response= UserEndPoints.getUser(this.reqbody.getUsername());
                    System.out.println("Username used: " + reqbody.getUsername());
                    response.then().log().all();

                   //Validation
                   Assert.assertEquals(response.getStatusCode(),200);
                   Response responsepostupdate=UserEndPoints.getUser(this.reqbody.getUsername());
                   System.out.println("After update");
                    responsepostupdate.then().log().body();

                 }


    @Test(priority = 3)
    public void testupdateuser()
    {
        reqbody.setFirstName(faker.name().firstName());
        Response response= UserEndPoints.putUser(this.reqbody.getUsername(),reqbody);

        response.then().log().all();

        //Validation
        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println("User updated sucessfully");

    }

    @Test(priority = 4)
    public void testdeleteuser()
    {

        Response response= UserEndPoints.deletUser(this.reqbody.getUsername());

        response.then().log().all();

        //Validation
        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println("User deleted sucessfully");

    }



}
