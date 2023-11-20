package tests;

import io.qameta.allure.Owner;
import models.colors.AllPantoneColorsResponseModel;
import models.colors.OnePantoneColorResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.colors.AllPantoneColorsSpec.allColorRequestSpec;
import static specs.colors.AllPantoneColorsSpec.allColorsResponceSpec;
import static specs.colors.OnePantoneColorSpec.oneColorRequestSpec;
import static specs.colors.OnePantoneColorSpec.oneColorResponseSpec;

public class PantoneColorsTests extends TestBase {
    @Test
    @Owner("kegorova")
    @DisplayName("Check GET-request for the one pantone color")
    void getOnePantoneColorTest(){
        String path = "/unknown/2";

        OnePantoneColorResponseModel responseOneColor = step("Make the GET request", () ->
                            given(oneColorRequestSpec)
                            .when()
                                    .get(path)
                                    .then()
                                        .spec(oneColorResponseSpec)
                                        .extract().as(OnePantoneColorResponseModel.class)
        );
        step("Verify response in the [data] object", () -> {
            assertEquals(2, responseOneColor.getData().getId());
            assertEquals("fuchsia rose", responseOneColor.getData().getName());
            assertEquals(2001, responseOneColor.getData().getYear());
            assertEquals("#C74375", responseOneColor.getData().getColor());
            assertEquals("17-2031", responseOneColor.getData().getPantoneValue());
        });
    }

    @Test
    @Owner("kegorova")
    @DisplayName("Check GET-request for the list of pantone colors")
    void getAllPantoneColorsTest(){
        String path = "/unknown";

        AllPantoneColorsResponseModel responseAllColors = step("Make GET request", () ->
                        given(allColorRequestSpec)
                        .when()
                            .get(path)
                            .then()
                                .spec(allColorsResponceSpec)
                                .extract().as(AllPantoneColorsResponseModel.class)
        );
        step("Verify response", () ->{
            assertEquals(1, responseAllColors.getPage());
            assertEquals(6, responseAllColors.getPerPage());
            assertEquals(12, responseAllColors.getTotal());
            assertEquals(2, responseAllColors.getTotalPages());
        });
        step("Verify that per_page is equal quantity of data objects", () ->{
            assertEquals(responseAllColors.getData().size(), responseAllColors.getPerPage());
        });
    }
}
