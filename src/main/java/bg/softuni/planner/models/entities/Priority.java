package bg.softuni.planner.models.entities;

import bg.softuni.planner.models.enums.PriorityNameEnum;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "priorities")
public class Priority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private PriorityNameEnum name;
    @Column(nullable = false)
    private String description;
    @OneToMany(mappedBy = "priority", targetEntity = Task.class)
    private Set<Task> tasks;

    public Priority() {
    }

    public Priority(Set<Task> tasks) {
        this.tasks = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public Priority setId(Long id) {
        this.id = id;
        return this;
    }

    public PriorityNameEnum getName() {
        return name;
    }

    public Priority setName(PriorityNameEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Priority setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public Priority setTasks(Set<Task> tasks) {
        this.tasks = tasks;
        return this;
    }
}
