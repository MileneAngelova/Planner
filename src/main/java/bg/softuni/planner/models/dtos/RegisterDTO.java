package bg.softuni.planner.models.dtos;

import bg.softuni.planner.validation.FieldsMatch;
import bg.softuni.planner.validation.UniqueUserEmail;
import bg.softuni.planner.validation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@FieldsMatch(first = "password", second = "confirmPassword", message = "Passwords do not match")
public class RegisterDTO {
    @NotBlank(message = "Username must not be empty")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 symbols long!")
    @UniqueUsername(message = "This username is already taken!")
    private String username;
    @NotBlank(message = "Enter your email")
    @Email(message = "Email format is not valid!")
    @UniqueUserEmail(message = "This email is already registered!")
    private String email;
    @NotBlank(message = "Choose your password!")
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 symbols long!")
    private String password;
    @NotBlank
    @Size(min = 3, max = 20)
    private String confirmPassword;

    public RegisterDTO() {
    }

    public String getUsername() {
        return username;
    }

    public RegisterDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public RegisterDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
