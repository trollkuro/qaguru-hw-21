package tests;

import io.qameta.allure.Owner;
import models.users.CreateUserBodyModel;
import models.users.CreateUserResponseModel;
import models.users.UpdateUserBodyModel;
import models.users.UpdateUserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.users.CreateUserSpec.createRequestSpec;
import static specs.users.CreateUserSpec.createResponseSpec;
import static specs.users.UpdateUserSpec.updateRequestSpec;
import static specs.users.UpdateUserSpec.updateResponseSpec;

public class UserTests extends TestBase {

    @Test
    @Owner("kegorova")
    @DisplayName("successful user creation")
    void successfulUserCreateTest(){
        String path = "/users";

        CreateUserBodyModel requestCreateUser = new CreateUserBodyModel();
        requestCreateUser.setName("morpheus");
        requestCreateUser.setJob("leader");

        CreateUserResponseModel responseCreateUser = step("Make create user request", () ->
                given(createRequestSpec)
                .body(requestCreateUser)
                .when()
                    .post(path)
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
    @Owner("kegorova")
    @DisplayName("successful user update")
    void successfulUserUpdateTest(){
        String path = "/users/2";

        UpdateUserBodyModel requestUpdateUser = new UpdateUserBodyModel();
        requestUpdateUser.setName("morpheus");
        requestUpdateUser.setJob("dancer");

        UpdateUserResponseModel responseUpdateUser = step("Make update user request", () ->
                given(updateRequestSpec)
                .body(requestUpdateUser)
                .when()
                    .patch(path)
                .then()
                    .spec(updateResponseSpec)
                    .extract().as(UpdateUserResponseModel.class)
        );

        step("Verify response", () ->
            assertEquals("dancer", responseUpdateUser.getJob())
        );
    }

}
