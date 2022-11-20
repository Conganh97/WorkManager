package View;

import java.util.Scanner;

public class UserView {
     static Scanner sc = new Scanner(System.in);
    public static int menuUser (){
        while (true){
            try {
                System.out.println("--------Menu staff-------");
                System.out.println("1.|Display all task");
                System.out.println("2.|Display completed task ");
                System.out.println("3.|Display in progress task ");
                System.out.println("4.|Display todo task ");
                System.out.println("5.|Edit information");
                System.out.println("6.|Change status task");
                System.out.println("7.|Change password");
                System.out.println("8.|Sign out");
                return Integer.parseInt(sc.nextLine());
            }
            catch (Exception e) {
                System.err.println("Please enter number");
            }
        }
    }
    public static int changeStatusTask (){
        while (true){
            try {
                System.out.println("--------Menu staff-------");
                System.out.println("1.|Change task in progress");
                System.out.println("2.|Change task in done");
                System.out.println("3.|Exit");
                return Integer.parseInt(sc.nextLine());
            }
            catch (Exception e) {
                System.err.println("Please enter number");
            }
        }
    }
}
