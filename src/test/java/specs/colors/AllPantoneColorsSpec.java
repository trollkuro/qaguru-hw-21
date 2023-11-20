package specs.colors;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class AllPantoneColorsSpec {

    public static RequestSpecification allColorRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().method();

    public static ResponseSpecification allColorsResponceSpec = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(200)
            .build();
}
