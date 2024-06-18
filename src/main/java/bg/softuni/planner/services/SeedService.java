package bg.softuni.planner.services;

import bg.softuni.planner.models.entities.Priority;
import bg.softuni.planner.models.enums.PriorityNameEnum;
import bg.softuni.planner.repositories.PriorityRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SeedService {
    private final PriorityRepository priorityRepository;

    public SeedService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    public void seedPriority() {
        if (this.priorityRepository.count() == 0) {
            Arrays.stream(PriorityNameEnum.values()).forEach(nameEnum -> {
                Priority priority = new Priority();
                priority.setName(nameEnum);
                if (priority.getName().equals(PriorityNameEnum.URGENT)) {
                    priority.setDescription("An urgent problem that blocks the system use until the issue is resolved.");
                }
                if (priority.getName().equals(PriorityNameEnum.IMPORTANT)) {
                    priority.setDescription("A core functionality that your product is explicitly supposed to perform is compromised.");
                }
                if (priority.getName().equals(PriorityNameEnum.LOW)) {
                    priority.setDescription("Should be fixed if time permits but can be postponed.");
                }
                this.priorityRepository.save(priority);
            });
        }
    }
}
