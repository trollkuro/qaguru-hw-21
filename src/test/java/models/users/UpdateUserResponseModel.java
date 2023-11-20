package models.users;

import lombok.Data;

@Data
public class UpdateUserResponseModel {
    private String name, job, updatedAt;
}
