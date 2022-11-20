package Controller;

import Service.TaskService;
import Service.UserService;
import View.AdminView;
import View.LoginView;
import View.UserView;

public class Controller {
    public UserService userService;
    public TaskService taskService;

    public Controller() {
        userService = new UserService();
        taskService = new TaskService();
    }

    public void menu() {
        while (true) {
            int choice = LoginView.menuLogin();
            switch (choice) {
                case 0 -> System.out.println("Back menu!");
                case 1 -> optionLogin();
                case 2 -> {
                    System.out.println("Exit!!");
                    System.exit(0);
                }
                default -> System.out.println("No option !! choose again");
            }
        }
    }

    public void optionLogin() {
        if (userService.checkLogin()) {
            System.out.println("Login success!");
            int choice = -1;
            if (UserService.userLogin.getRole().equals("admin")) {
                while (choice != 12) {
                    System.out.println("Hello " + UserService.userLogin.getName() + "!!!");
                    choice = AdminView.menuAdmin();
                    selectFunctionAdmin(choice);
                }
            } else {
                while (choice != 8) {
                    System.out.println("Hello " + UserService.userLogin.getName() + "!!!");
                    choice = UserView.menuUser();
                    selectFunctionUser(choice);
                }
            }
        } else System.out.println("Login fail. Try again!");
    }

    public void selectFunctionAdmin(int choice) {
        switch (choice) {
            case 1 -> userService.addUserToList(userService.createUser());
            case 2 -> userService.deleteUser(userService.findIndex());
            case 3 -> userService.displayUser();
            case 4 -> userService.changeRole(userService.findIndex());
            case 5 -> taskService.addTaskToList(taskService.createTask());
            case 6 -> taskService.editTask(taskService.findIndexTask());
            case 7 -> taskService.deleteTask(taskService.findIndexTask());
            case 8 -> taskService.displayTask(taskService.findTaskByUserName());
            case 9 -> taskService.displayTask(TaskService.taskList);
            case 10 -> userService.changePass();
            case 11 -> userService.editInformation();
            case 12 -> {
                System.out.println("Sign out! Bye " + UserService.userLogin.getName());
                UserService.userLogin = null;
            }
            default -> System.out.println("No option ! choose again");
        }
    }

    public void selectFunctionUser(int choice) {
        switch (choice) {
            case 1 -> taskService.displayTask(taskService.findTaskUserLogin(""));
            case 2 -> taskService.displayTask(taskService.findTaskUserLogin("Done"));
            case 3 -> taskService.displayTask(taskService.findTaskUserLogin("In progress"));
            case 4 -> taskService.displayTask(taskService.findTaskUserLogin("To do"));
            case 5 -> userService.editInformation();
            case 6 -> taskService.optionChangeStatus(taskService.findIndexTask());
            case 7 -> userService.changePass();
            case 8 -> {
                System.out.println("Sign out! Bye " + UserService.userLogin.getName());
                UserService.userLogin = null;
            }
            default -> System.out.println("No option ! choose again");
        }
    }
}

