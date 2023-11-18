package tests;

import models.users.CreateUserBodyModel;
import models.users.CreateUserResponseModel;
import models.users.UpdateUserBodyModel;
import models.users.UpdateUserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.CreateUserSpec.createRequestSpec;
import static specs.CreateUserSpec.createResponseSpec;
import static specs.UpdateUserSpec.updateRequestSpec;
import static specs.UpdateUserSpec.updateResponseSpec;

public class UserTests extends TestBase {

    @Test
    @DisplayName("successful user creation")
    void successfulUserCreateTest(){
        CreateUserBodyModel requestCreateUser = new CreateUserBodyModel();
        requestCreateUser.setName("morpheus");
        requestCreateUser.setJob("leader");

        CreateUserResponseModel responseCreateUser = step("Make create user request", () ->
                given(createRequestSpec)
                .body(requestCreateUser)
                .when()
                    .post("/users")
                .then()
                    .spec(createResponseSpec)
                    .extract().as(CreateUserResponseModel.class)
        );

        step("Verify response", () -> {
            assertEquals("morpheus", responseCreateUser.getName());
            assertEquals("leader", responseCreateUser.getJob());
        });
    }


    @Test
    @DisplayName("successful user update")
    void successfulUserUpdateTest(){
        UpdateUserBodyModel requestUpdateUser = new UpdateUserBodyModel();
        requestUpdateUser.setName("morpheus");
        requestUpdateUser.setJob("dancer");

        UpdateUserResponseModel responceUpdateUser = step("Make update user request", () ->
                given(updateRequestSpec)
                .body(requestUpdateUser)
                .when()
                    .patch("/users/2")
                .then()
                    .spec(updateResponseSpec)
                    .extract().as(UpdateUserResponseModel.class)
        );

        step("Verify response", () ->
            assertEquals("dancer", responceUpdateUser.getJob())
        );
    }

}
