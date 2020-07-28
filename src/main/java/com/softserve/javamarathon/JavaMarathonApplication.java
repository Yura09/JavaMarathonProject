package com.softserve.javamarathon;

import com.softserve.javamarathon.entity.Progress;
import com.softserve.javamarathon.entity.enums.TaskStatus;
import com.softserve.javamarathon.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class JavaMarathonApplication implements CommandLineRunner {
    private MarathonService marathonService;
    private ProgressService progressService;
    private SprintService sprintService;
    private TaskService taskService;
    private UserService userService;

    @Autowired
    public JavaMarathonApplication(MarathonService marathonService, ProgressService progressService, SprintService sprintService, TaskService taskService, UserService userService) {
        this.marathonService = marathonService;
        this.progressService = progressService;
        this.sprintService = sprintService;
        this.taskService = taskService;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(JavaMarathonApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
     //   Progress progress = new Progress();
       // progress.setStarted(LocalDate.of(2020, 6, 4));
        //progress.setUpdated(LocalDate.of(2020, 6, 5));
        //progress.setStatus(TaskStatus.PENDING);
     //  progressService.addTaskForStudent(taskService.getTaskById(1L),userService.getUserById(2L));
        //   progressService.addTaskForStudent(taskService.getTaskById(1L),userService.getUserById(2L));
     //   System.out.println(progressService.allProgressByUserIdAndSprintId(1L, 1L));
        //progressService.addTaskForStudent(taskService.getTaskById(1L), userService.getUserById(2L));
        //progressService.addOrUpdateProgress(progress);
        //   User user = new User();
       /* Sprint sprint = new Sprint();
        sprint.setStartDate(LocalDate.of(2020, 4, 1));
        sprint.setFinish(LocalDate.of(2020, 4, 3));
        sprint.setTitle("LINQ");*/
//        Task task = new Task();
//        task.setTitle("Practice Task. LINQ");
//        task.setCreated(LocalDate.of(2020, 4, 2));
//        task.setUpdated(LocalDate.of(2020, 4, 3));
        //taskService.addTaskToSprint(task, sprintService.getSprintById(1L));
        //System.out.println(taskService.getTaskById(1L));
        //	System.out.println(sprintService.getSprintsByMarathonId(1L));
        //	sprintService.addSprintToMarathon(sprint,marathonService.getMarathonById(2L));
        //    System.out.println(userService.addUserToMarathon(user, marathonService.getMarathonById(1L)));
        //do not work	userService.addUserToTask(userService.getUserById(1L),taskService.getTaskById(1L));

    }
}
