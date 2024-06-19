package bg.softuni.planner.web;

import bg.softuni.planner.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddTaskController {
    private final UserService userService;

    public AddTaskController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/task-add")
    public String addTask() {
        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }
        return "task-add";
    }

}
