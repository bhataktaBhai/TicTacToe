package TicTacToe;

public class Expert
{
    static final int S = 3;

    public static void main()
    {
        int[][] arr = new int[S][S];    //the board
        final int ORDER = 1;//(int) (Math.random() + 0.5);
        //ORDER == 0 implies player first, ORDER == 1 implies algorithm first
        if (ORDER == 0)   //displays board if player goes first
            printer(arr);
        for (int move = 1; move <= S * S && !win(arr); move++)
        {
            if (move % 2 == ORDER)  //algorithm's turn
                mover(move, true, ORDER, arr);
            else                    //player's turn
                reader(-1, arr);
            printer(arr);
        }
        if(win(arr))    //there's a winner, means it's Charlie (the algorithm) #confidence
            System.out.println("The result is as expected. Charlie stands winner.");
        else            //no winner == tie
            System.out.println("Congratulations, Challenger. You have drawn with the Expert.");
    }

    static boolean win(int[][] arr)
    {
        int d1 = 0, d2 = 0, down, across;
        for (int i = 0; i < S; d1 += arr[i][i], d2 += arr[i][(S - 1) - i], i++)
        { /*d1 becomes sum of diagonal y = x, d2 becomes sum of y = -x + 2*/ }
        if (d1 * d1 == S * S || d2 * d2 == S * S)
            return true;
        //The entries are 0, 1 or -1, if all entries in a diagonal are equal, sum is
        //0, S or -S. 0 means no move played in the diagonal, so no winner.
        //S means all 'x', -S means all 'o'. In either case, their square will be S*S
        for (int i = 0, j = 0; i < S; j = 0, i++) {
            for (down = 0, across = 0; j < S; down += arr[i][j], across += arr[j][i], j++)
            { /*sums up i-th row and column*/ }
            if (down * down == S * S || across * across == S * S) //same logic as before
                return true;
        }
        return false;
    }

    static int winner(int[][] arr, int ORDER)
    {
        int x = 0, o = 0;
        for (int i = 0; i < S; i++) {
            for (int j = 0; j < S; j++) {
                switch (arr[i][j]) {
                    case 1:
                        x++;
                        break;
                    case -1:
                        o++;
                }
            }
        }
        if (x - ORDER == o)
            return 1;
        //player goes 1st (ORDER == 0), x == o implies x moved last
        //player goes 2nd (ORDER == 1), x > o implies x moved last
        //if x moved last and there is a win, x is the winner
        //x is Charlie, x winning is indicated by 1
        return -1;
    }

    static int[] alert(int player, int[][] arr)
    {
        for (int h = 0; h <= 1; player *= -1, h++) {
            //first checks with player, to complete any potential win
            //then checks with the opponent, to stop any potential loss
            int d1 = 0, d2 = 0, down, across;
            for (int i = 0; i < S; d1 += arr[i][i], d2 += arr[i][S - i - 1], i++)
            {/*sums up the two diagonals*/}
            if (d1 == (S - 1) * player)
                for (int i = 0; i < S; i++)
                    if (arr[i][i] == 0)
                        return new int[]{i, i};
            if (d2 == (S - 1) * player)
                for (int i = 0; i < S; i++)
                    if (arr[i][(S - 1) - i] == 0)
                        return new int[]{i, (S - 1) - i};
            //checks if any diagonal has two same player's tokens and one empty
            //return a list containing the two co-ordinates of the empty spot
            for (int i = 0, j = 0; i < S; j = 0, i++)
            {
                for (down = 0, across = 0; j < S; down += arr[j][i], across += arr[i][j], j++)
                {}
                if (down == (S - 1) * player)
                    for (int k = 0; k < S; k++)
                        if (arr[k][i] == 0)
                            return new int[]{k, i};
                if (across == (S - 1) * player)
                    for (int k = 0; k < S; k++)
                        if (arr[i][k] == 0)
                            return new int[]{i, k};
                //checks each of the rows and columns and follows same procedure as diagonals
            }
        }
        return new int[]{-1};
        //indicates no alert
    }

    static int[] mover(int move, boolean real, int ORDER, int[][] arr)
    {
        int player = move % 2 == ORDER ? 1 : -1;
        if (win(arr))
            return winner(arr, ORDER) == player ? new int[]{100,100} : new int[]{0,0};
        if (move == S * S + 1)
            return new int[]{50,50};
        int[] alert = alert(player, arr);
        if (alert[0] != -1)
        {
//          if (real)
//                System.out.println("ALERT!");
            arr[alert[0]][alert[1]] = player;
            //performs the alert operation
            int[] result = mover(move + 1, false, ORDER, arr);
            //stores the result as 0 (loss), 50 (draw) or 100 (win)
            if (!real)
                arr[alert[0]][alert[1]] = 0;
            return new int[]{100 - result[0], 100 - result[1]};
        }
        int max, r, c, result[], sum, avg;
        max = r = c = sum = 0;
        loop:
        for (int x = 0; x < S; x++) {
            for (int y = 0; y < S; y++) {
                if (arr[x][y] == 0) {
                    arr[x][y] = player;
                    result = mover(move + 1, false, ORDER, arr);
                    //if(real || move<=5) System.out.printf("%d %d %d%c %d\n", move, x , y , ':' , result);
                    //if(move==3) System.exit(0);
                    if(result[0] - 100 > max)// || (result[1] == max && Math.random() < 0.5))
                    {
                        r = x; c = y;
                        max = result[0];
                    }
                    arr[x][y] = 0;
                    if (max == 100) {
                        break loop;
                    }
                }
            }
        }
        if (real)
        {
            arr[r][c] = player;
        }
        return new int[]{max, 0};
    }

    static void reader(int player, int[][] arr) {
        while (true) {
            int r, c;
            try {
                java.util.Scanner in = new java.util.Scanner(System.in);
                r = in.nextInt() - 1;
                c = in.nextInt() - 1;
            } catch (Exception e) {
                //System.out.println(e);
                System.out.printf("%s\n\n", "I thought you would know what a number is, Challenger.");
                continue;
            }
            try {
                if (arr[r][c] == 0) {
                    arr[r][c] = player;
                    break;
                }
                System.out.printf("%s\n%s\n\n", "That slot's not empty.", "Try again.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.printf("%s\n%s\n\n", "Those co-ordinates don't exist, Challenger.", "Try again.");
            }
        }
    }

    static void printer(int[][] arr) {
        char c;
        for (int i = 0; i < S; i++) {
            for (int j = 0; j < S; j++) {
                switch (arr[i][j]) {
                    case -1:
                        c = 'o';
                        break;
                    case 1:
                        c = 'x';
                        break;
                    default:
                        c = ' ';
                }
                System.out.printf("%c%c", '|', c);
            }
            System.out.println('|');
        }
        System.out.println();
    }
}