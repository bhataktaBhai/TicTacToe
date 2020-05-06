package TicTacToe;

public class Player{
    public static void main()
    {
        int[][] arr = new int[3][3];
        int player=1;
        Expert.printer(arr);
        for(int i=1; i<=9 && !Expert.win(arr); player = player==1?-1:1, i++)
        {
            Expert.reader(player, arr);
            Expert.printer(arr);
        }
        if(Expert.win(arr)) 
            System.out.println("The winner is " + (Expert.winner(arr, 1)==1?'x':'o') + "!");
        else
            System.out.println("It was a match of equals.");
    }
}
