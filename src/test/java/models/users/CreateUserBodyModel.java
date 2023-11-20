package models.users;

import lombok.Data;

@Data
public class CreateUserBodyModel {
    private String name, job;
}
