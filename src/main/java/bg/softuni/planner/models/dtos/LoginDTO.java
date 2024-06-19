package bg.softuni.planner.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginDTO {
    @NotBlank(message = "Username must not be empty")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 symbols long!")
    private String username;

    @NotBlank(message = "Password must not be empty")
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 symbols long!")
    private String password;

    public LoginDTO() {
    }
    public String getUsername() {
        return username;
    }

    public LoginDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
