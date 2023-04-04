package net.javaguides.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.ExceptionHandler;
@Schema(
        description = "User DTO Model information"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    // User's name and email should not be null or empty
    @Schema(description = "User First Name")
    @NotEmpty(message = "first name should not be empty")
    private String firstName;
    @NotEmpty(message = "last name should not be empty")
    @Schema(description = "User Last Name")
    private String lastName;
    @NotEmpty(message = "email should not be null or empty")
    // the email address should be valid
    @Schema(description = "User's email")
    @Email(message = "email address should be valid")
    private String email;
}
