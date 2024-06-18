package bg.softuni.planner.web;

import bg.softuni.planner.models.dtos.LoginDTO;
import bg.softuni.planner.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {
    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home() {
        if(!this.userService.isLoggedIn()) {
            return "redirect:/";
        }
        return "home";
    }

}
