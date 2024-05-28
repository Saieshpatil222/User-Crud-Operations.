package com.main.project.springbootrestfulwebservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Schema(

        description = "UserDto Model Information"
)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;

    @Schema(
            description = "User First Name"
    )
    //user first name should not be null or empty
    @NotEmpty(message = "User First Name should Not Be Null or Empty")
    private String firstName;

    @Schema(
            description = "User Last Name"
    )
    //user last first name should be not null or empty
    @NotEmpty(message = "User Last Name should Not Be Null or Empty")
    private String lastName;

    @Schema(
            description = "User Email Address"
    )
    //User email should not be null or empty
    //Email address should be valid
    @NotEmpty(message = "User Email Should Not Be Empty")
    @Email(message = "Email Address Should Be Valid")
    private String email;

}
