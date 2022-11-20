package Service;

import Model.Task;
import Model.User;
import Validate.Validate;
import View.AdminView;
import View.UserView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TaskService {

    public static ArrayList<Task> taskList = new ArrayList<>();
    public Scanner scanner = new Scanner(System.in);


    public int findIndexTask() {
        System.out.println("Enter task code: (Task code must have 1-3 letter include letters)");
        String taskCode = Validate.getRegexString(Validate.REGEX_ID_FAD);
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getTaskCode().equals(taskCode)) return i;
        }
        return -1;
    }

    public void editTask(int index) {
        Task taskEdit = taskList.get(index);
        if (index != -1) {
            System.out.println("Enter new time estimate : (time estimate must be 1-4 number");
            int timeEstimate = Validate.isNumber(Validate.REGEX_NUMB);
            taskEdit.setTimeEstimate(timeEstimate);
            System.out.println("Enter description:");
            String description = scanner.nextLine();
            taskEdit.setDescription(description);
            while (true) {
                System.out.println("Enter user name need search (User name must have 6-10 letter include letters and numbers)");
                String user = Validate.getRegexString(Validate.REGEX_ACCOUNT);
                User worker = findWorker(user);
                if (worker == null) {
                    System.err.println("Worker is not exist");
                } else {
                    taskEdit.setWorker(worker);
                    break;
                }
            }


            System.out.println("Edit task success!");
        } else System.out.println("This account not exist");
        taskEdit.setDateUpdated(new Date());
        taskEdit.setEditor(UserService.userLogin.getName());
    }

    public Task createTask() {
        String taskCode;
        while (true) {
            System.out.println("Enter task code: (Task code must have 1-3 letter include letters)");
            taskCode = Validate.getRegexString(Validate.REGEX_ID_FAD);
            if (checkTaskCode(taskCode)) {
                break;
            } else {
                System.err.println("Task code is exist");
            }
        }
        System.out.println("Enter name task: (Name task have at least 1 uppercase letter and no number) ");
        String nameTask = Validate.getRegexString(Validate.REGEX_STRING);
        System.out.println("Enter description:");
        String description = scanner.nextLine();
        System.out.println("Enter time estimate: (time estimate must be 1-4 number");
        int timeEstimate = Validate.isNumber(Validate.REGEX_NUMB);

        while (true) {
            System.out.println("Enter user name need search (User name must have 6-10 letter include letters and numbers)");
            String user = Validate.getRegexString(Validate.REGEX_ACCOUNT);
            User worker = findWorker(user);
            if (worker == null) {
                System.err.println("Worker is not exist");
            } else {
                return new Task(new Date(), UserService.userLogin.getName(), taskCode, nameTask, description, timeEstimate, worker);
            }
        }
    }

    public void addTaskToList(Task task) {
        taskList.add(task);
        System.out.println("Create task success");
    }

    public void deleteTask(int index) {
        if (index != -1) {
            if (AdminView.confirm("Delete Task")) {
                taskList.remove(index);
                System.out.println("Delete task success");
            }

        } else System.out.println("This task not exist");
    }

    public boolean checkTaskCode(String taskCode) {
        for (Task task : taskList) {
            if (task.getTaskCode().equals(taskCode)) {
                return false;
            }
        }
        return true;
    }

    public void displayTask(List<Task> taskList) {
        System.out.println("-------------------------------User list-------------------------------");
        System.out.printf("| %-10s| %-10s| %-10s| %-15s| %-12s| %-20s| %-12s| %-20s| %-12s|", "Task Code", "Name Task", "Estimate", "Worker", "Status", "Created At", "Creator", "Update At", "Editor");
        System.out.println();
        System.out.println("--------------------------------------------------------------------------");
        for (Task acc : taskList) {
            System.out.printf("| %-10s| %-10s| %-10s| %-15s| %-12s| %-20s| %-12s| %-20s| %-12s|"
                    , acc.getTaskCode(), acc.getNameTask(), acc.getTimeEstimate(), acc.getWorker().getName(), acc.getStatus()
                    , acc.displayDateCreated(), acc.getCreator(), acc.displayDateEdited(), acc.getEditor());
            System.out.println();
            System.out.println("Description: " + acc.getDescription());
            System.out.println();
            System.out.println("--------------------------------------------------------------------------");
        }
    }

    public List<Task> findTaskByUserName() {
        System.out.println("Enter user name need search (User name must have 6-10 letter include letters and numbers)");
        String userName = Validate.getRegexString(Validate.REGEX_ACCOUNT);
        List<Task> list = new ArrayList<>();
        for (Task t : taskList) {
            if (t.getWorker().getUserName().equals(userName)) {
                list.add(t);
            }
        }
        return list;
    }

    public User findWorker(String name) {
        for (User u : UserService.userList) {
            if (u.getUserName().equals(name)) {
                return u;
            }
        }
        return null;
    }

    public List<Task> findTaskUserLogin(String status) {
        List<Task> list = new ArrayList<>();
        for (Task t : taskList) {
            if (t.getWorker().getUserName().equals(UserService.userLogin.getUserName())) {
                if (status.equals("")) {
                    list.add(t);
                } else if (t.getStatus().equals(status)) {
                    list.add(t);
                }
            }
        }
        return list;
    }

    public void doneTask(int index) {
        if (index != -1) {
            if (AdminView.confirm("Task completion confirmation")) {
                taskList.get(index).setStatus("Done");
                System.out.println("Task " + taskList.get(index).getNameTask() + " is done!");
            }

        } else System.out.println("This account not exist");
        taskList.get(index).setStatus("Done");

    }

    public void optionChangeStatus(int index) {
        if (index != -1) {
            Task task = taskList.get(index);
            int choice = -1;
            Option: while (choice != 3) {
                choice = UserView.changeStatusTask();
                switch (choice) {
                    case 1 -> {
                        changeStatus(task, "In progress");
                    break Option;
                    }
                    case 2 ->{
                        changeStatus(task, "Done");
                        break Option;
                    }
                    case 3 -> {}
                    default -> System.out.println("No option ! choose again");
                }

            }

        } else System.out.println("This account not exist");
    }

    public void changeStatus(Task task, String status) {
        task.setStatus(status);
        System.out.println("Task " + task.getNameTask() + " is " + status);
    }
}
