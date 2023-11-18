package models.users;

import lombok.Data;

@Data
public class CreateUserResponseModel {
    String name, job, id, createdAt;
}
