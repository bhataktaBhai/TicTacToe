package TicTacToe;

public class Noob {
    public static void main()
    {
        int[][] arr = new int[3][3];
        final int ORDER = (int)(Math.random()+0.5);
        if(ORDER == 0) Expert.printer(arr);
        for (int i = 1; i <= 9 && !Expert.win(arr); i++) {
            if(i % 2 == ORDER)
                while(true) {
                    int r = (int)(Math.random() * 3);
                    int c = (int)(Math.random() * 3);
                    if(arr[r][c] == 0) {
                        arr[r][c] = 1;
                        break;
                    }
                }
            else {
                Expert.reader(-1, arr);
            }
            Expert.printer(arr);
        }
        if(Expert.win(arr))
            if(Expert.winner(arr, ORDER)==-1)
                System.out.println("A win against a noob is a win all the same. Congratulations, Challenger.");
            else
                System.out.println("You are extraordinarily untalented, Challenger.");
        else
            System.out.println("A tie, though not commendable, is better than a loss.");
    }
}
