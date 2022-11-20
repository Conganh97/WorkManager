package View;

import java.util.Scanner;

public class AdminView {
   static Scanner sc = new Scanner(System.in);
    public static int menuAdmin (){
        while (true){
            try {
                System.out.println("--------Menu admin-------");
                System.out.println("1.|Create user");
                System.out.println("2.|Delete user");
                System.out.println("3.|Display user");
                System.out.println("4.|Change role user");
                System.out.println("5.|Create task");
                System.out.println("6.|Edit task");
                System.out.println("7.|Delete task");
                System.out.println("8.|Check task by user name");
                System.out.println("9.|Display all task");
                System.out.println("10.|Change password");
                System.out.println("11.|Change information");
                System.out.println("12.|Sign out");
                return Integer.parseInt(sc.nextLine());
            }
            catch (Exception e) {
                System.err.println("Please enter number");
            }
        }
    }

    public static boolean confirm(String name){
        int choice = -1;
        while (choice != 2){
            try {
                System.out.println("--------Confirm "+ name+ "-------");
                System.out.println("1.|Yes");
                System.out.println("2.|No");
                 choice = Integer.parseInt(sc.nextLine());
                if (choice == 1){
                    return true;
                }
            }
            catch (Exception e) {
                System.err.println("Please enter number");
            }
        }
        return false;
    }


}
