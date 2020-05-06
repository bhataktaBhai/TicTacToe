package TicTacToe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Choose whom you wish to face.");
        System.out.println("1. Alice, the Novice: The mind of an infant, her plays are completely random.");
        System.out.println("2. Bob, the Casual: Without any strategy, he looks but one move ahead.");
        System.out.println("3. Charlie, the Expert: Try what you may, but he is invincible.");
        System.out.println("I await your choice, Challenger.");
        System.out.println("Are you too afraid to tackle any of these? Press 0 if that's the case.");
        System.out.println("Or are you not one, but two? Press 10 to see who is worthier.");
        int choice = scan.nextInt();
        if(choice>=0 && choice<=3) System.out.print("You have chosen ");
        switch (choice) {
            case 1:
                System.out.println("Alice.");
                Noob.main();
                break;
            case 2:
                System.out.println("Bob.");
                Casual.main();
                break;
            case 3:
                System.out.println("Charlie, the Expert.");
                Expert.main();
                break;
            case 10:
                System.out.println("May the better player win.");
                Player.main();
                break;
            case 0:
                System.out.println("wisely, Challenger.");
                System.exit(choice);
            default:
                System.out.println("Do you know how to read?");
        }
        scan.close();
    }
}