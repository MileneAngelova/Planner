package bg.softuni.planner.web;

import bg.softuni.planner.models.dtos.LoginDTO;
import bg.softuni.planner.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("loginModel")
    public LoginDTO initLogin() {
        return new LoginDTO();
    }

    @GetMapping("/login")
    public String login() {
        if (this.userService.isLoggedIn()) {
            return "redirect:/home";
        }
        return "/login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginDTO loginModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (this.userService.isLoggedIn()) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginModel", loginModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginModel", bindingResult);

            return "redirect:/login";
        }

        boolean success = userService.login(loginModel);

        if (!success) {
            redirectAttributes.addFlashAttribute("loginError", true);

            return "redirect:/login";
        }
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }
}
