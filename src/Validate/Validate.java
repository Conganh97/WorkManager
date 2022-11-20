package Validate;

import java.util.Scanner;

public class Validate {
    public static final String REGEX_STRING = "[A-Z][\\sA-Za-z]+";

    public static final String REGEX_ID_FAD = "[A-Z]{3}";
    public static final String REGEX_ACCOUNT = "[A-Za-z0-9]{6,10}";
    public static final String REGEX_EMAIL = "[A-Za-z0-9]+@[a-z]+.[com |vn]+";
    public static final String REGEX_NUMB = "[0-9]{1,4}";
    public static final String REGEX_PHONE = "(0)+([0-9]{9})\\b";
    static Scanner scanner = new Scanner(System.in);


    public static int isNumber(String regex) {
        while (true) {
            String choice = scanner.nextLine();
            if (choice.matches(regex)) {
                return Integer.parseInt(choice);
            } else System.err.println("Enter the wrong format, re-enter :");
        }
    }

    public static String getRegexString(String regex) {
        while (true) {
            String name = scanner.nextLine();
            if (name.matches(regex)) {
                return name;
            }
            System.err.println("Enter the wrong format, re-enter :");
        }
    }




    public static String getRegexPhone(String regex){
        while (true) {
            String phone = scanner.nextLine();
            if (phone.matches(regex)) {
                return phone;
            }
            System.err.println("Enter the wrong format, re-enter :");
        }
    }
}
