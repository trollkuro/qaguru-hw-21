package models.users;

import lombok.Data;

@Data
public class CreateUserResponseModel {
    private String name, job, id, createdAt;
}
