package models.colors;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class AllPantoneColorsResponseModel {
    int page;
    @JsonProperty("per_page")
    int perPage;
    int total;
    @JsonProperty("total_pages")
    int totalPages;
    List<DataList> data;
    Support support;

    @Data
    public static class DataList {
        int id;
        String name;
        int year;
        String color;
        @JsonProperty("pantone_value")
        String pantoneValue;
    }

    @Data
    public static class Support {
        String url;
        String text;
    }
}
