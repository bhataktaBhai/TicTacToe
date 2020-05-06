package TicTacToe;

public class Casual {
    public static void main()
    {
        final int S = 3;
        int[][] arr = new int[S][S];
        final int ORDER = (int)(Math.random()+0.5);
        if(ORDER==0) Expert.printer(arr);
        for (int i = 1; i <= S*S && !Expert.win(arr); i++) {
            if(i%2==ORDER)    
                while(true) {
                    int alert[] = Expert.alert(1, arr);
                    if(alert[0]>=0) {
                        arr[alert[0]][alert[1]]=1;
                        break;
                    }
                    int r = (int)(Math.random()*S);
                    int c = (int)(Math.random()*S);
                    if(arr[r][c]==0) {arr[r][c]=1; break;}
                }
            else
                Expert.reader(-1, arr);
            Expert.printer(arr);
        }
        if(Expert.win(arr))
            if(Expert.winner(arr, ORDER)==-1)
                System.out.println("Congratulations, Challenger. You have won.");
            else
                System.out.println("I had thought you would do better, Challenger.");
        else
            System.out.println("A tie, though not commendable, is better than a loss.");
    }
}
