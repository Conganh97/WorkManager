
package Service;

import Model.User;
import Validate.Validate;
import View.AdminView;

import java.util.ArrayList;
import java.util.Date;

public class UserService {
    public static ArrayList<User> userList = new ArrayList<>();

    public static User userLogin;

    public UserService() {
        if (userList.isEmpty()) {

            userList.add(new User(new Date(), "Admin", "Admin", "admin1", "admin1", "admin@gmail.com", "0394935883", "admin"));
            userList.add(new User(new Date(), "Admin", "Cong Anh", "conganh", "conganh", "conganh@gmail.com", "0394935883", "user"));
        }
    }

    public User createUser() {
        String user;
        System.out.println("Enter name: (Name must have at least 1 uppercase letter and no number) ");
        String name = Validate.getRegexString(Validate.REGEX_STRING);
        while (true) {
            System.out.println("Enter user name: (User name must have 6-10 letter include letters and numbers)");
            user = Validate.getRegexString(Validate.REGEX_ACCOUNT);
            if (checkUserName(user)) {
                break;
            } else {
                System.err.println("User name is exist");
            }
        }
        System.out.println("Enter pass word : (Password must have 6-10 letter include letters and numbers)");
        String pass = Validate.getRegexString(Validate.REGEX_ACCOUNT);
        System.out.println("Enter email: (Email must be in the form: abcd@abc.com/vn)");
        String email = Validate.getRegexString(Validate.REGEX_EMAIL);
        System.out.println("Enter phone number : (Phone number must have 10 numbers and start by 0)");
        String phone = Validate.getRegexPhone(Validate.REGEX_PHONE);
        return new User(new Date(), userLogin.getName(), name, user, pass, email, phone, "user");
    }

    public void addUserToList(User user) {
        userList.add(user);
        System.out.println("Add account " + user.getUserName() + " success!");

    }

    public void deleteUser(int index) {
        if (index != -1) {
            if (AdminView.confirm("Delete")) {
                userList.remove(index);
                System.out.println("Delete account success");
            }

        } else System.out.println("This account not exist");
    }


    public void editInformation() {
        System.out.println("Enter new name: (Name must have at least 1 uppercase letter and no number) ");
        String name = Validate.getRegexString(Validate.REGEX_STRING);
        System.out.println("Enter new email: (Email must be in the form: abcd@abc.com/vn)");
        String email = Validate.getRegexString(Validate.REGEX_EMAIL);
        System.out.println("Enter new phone number : (Phone number must have 10 numbers and start by 0)");
        String phone = Validate.getRegexPhone(Validate.REGEX_PHONE);
        userLogin.setName(name);
        userLogin.setEmail(email);
        userLogin.setPhone(phone);
        System.out.println("Edit information success");
        userLogin.setDateUpdated(new Date());
        userLogin.setEditor(userLogin.getName());
    }

    public boolean checkLogin() {
        System.out.println("Enter user name: (User name must have 6-10 letter include letters and numbers)");
        String user = Validate.getRegexString(Validate.REGEX_ACCOUNT);
        System.out.println("Enter pass word : (Password must have 6-10 letter include letters and numbers)");
        String pass = Validate.getRegexString(Validate.REGEX_ACCOUNT);

        for (User acc : userList) {
            if (acc.getUserName().equals(user) && acc.getPassword().equals(pass)) {
                userLogin = acc;
                return true;
            }
        }
        return false;
    }

    public boolean checkUserName(String userName) {
        for (User acc : userList) {
            if (acc.getUserName().equals(userName)) {
                return false;
            }
        }
        return true;
    }

    public int findIndex() {
        System.out.println("Enter user name need search (User name must have 6-10 letter include letters and numbers)");
        String user = Validate.getRegexString(Validate.REGEX_ACCOUNT);
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserName().equals(user)) return i;
        }
        return -1;
    }

    public void changeRole(int index) {
        if (index != -1) {
            if (userList.get(index).getRole().equals("admin")) {
                if (AdminView.confirm("change role to user"))
                    userList.get(index).setRole("user");
            } else {
                if (AdminView.confirm("change role to admin"))
                    userList.get(index).setRole("admin");
            }
        } else System.out.println("This account not exist");
    }

    public void displayUser() {
        System.out.println("-------------------------------User list-------------------------------");
        System.out.printf("| %-10s| %-10s| %-10s| %-20s| %-12s| %-20s| %-12s| %-20s| %-12s|", "Name", "User", "Role", "Email", "Phone", "Created At", "Creator", "Update At", "Editor");
        System.out.println();
        System.out.println("--------------------------------------------------------------------------");
        for (User acc : userList) {
            System.out.printf("| %-10s| %-10s| %-10s| %-20s| %-12s| %-20s| %-12s| %-20s| %-12s|"
                    , acc.getName(), acc.getUserName(), acc.getRole(), acc.getEmail(), acc.getPhone()
                    , acc.displayDateCreated(), acc.getCreator(), acc.displayDateEdited(), acc.getEditor());
            System.out.println();
            System.out.println("--------------------------------------------------------------------------");
        }
    }

    public boolean checkPassword(String pass) {
        return userLogin.getPassword().equals(pass);
    }

    public void changePass() {
        while (true) {
            System.out.println("Enter old pass word : (Password must have 6-10 letter include letters and numbers)");
            String oldPass = Validate.getRegexString(Validate.REGEX_ACCOUNT);
            if (checkPassword(oldPass)) {
                System.out.println("Enter new pass word : (Password must have 6-10 letter include letters and numbers)");
                String newPass = Validate.getRegexString(Validate.REGEX_ACCOUNT);
                userLogin.setPassword(newPass);
                System.out.println("Change password successfully!!");
                userLogin.setDateUpdated(new Date());
                userLogin.setEditor(userLogin.getName());
                break;
            } else {
                System.err.println("Entered wrong password,Please re-enter!");
            }
        }
    }

}
