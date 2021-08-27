import java.util.Arrays;
import java.util.Scanner;

public class q3 {
    public static int n;
    public static String[][] board;
    public static String player = "X";
    public static void sizeBoard(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Please put n: ");
        n = sc.nextInt();
        board = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = String.valueOf(i) + String.valueOf(j);
            }
        }
    }
    public static void showBoard(){
//        int k = 0;
        System.out.println("\t\t\t\t\t\t\t-------------");
        for(int i=0; i<n;i++){
            for(int j=0;j<n;j++){
//                k++;
                System.out.print(board[i][j] + "   ");
            }
            System.out.println("\n");
        }
        System.out.println("\t\t\t\t\t\t\t-------------");
    }

    public static void createPlayer(){
        while(true){
            try{
                Scanner sc = new Scanner(System.in);
                System.out.print("\t\t\t\t\t\t  Put the position Player " + player + ": ");
                String input = sc.nextLine();
                String[] finput = input.split(""); // {0,0}
                int pos = Integer.parseInt(finput[0]);
                int after = Integer.parseInt(finput[1]);
                if((pos >= 0 && pos<n) && (after >= 0 && after < n)){
//                    System.out.println(n);
                    board[pos][after] = player;
//                    System.out.println(board[pos][after]);
                    if(player == "X"){
                        player = "O";
                        break;
                    }
                    else if(player == "O"){
                        player = "X";
                        break;
                    }
                }
                else if(board[pos][after] == "X" || board[pos][after] == "O"){
                    System.out.println("\t\t\t\t\t\t\tSame Position.");
                }

            }
            catch (Exception e){
                System.out.println("\t\t\t\t\t\t  Please put right number");
            }
        }

    }
    public static  boolean checkWin(){
        String[] player_check = new String[n];
        if(player == "X"){
            for(int i = 0; i<n ; i++){
                player_check[i] = "O";
//                System.out.println(player_check[i]);
            }
        }
        else if(player == "O"){
            for(int i = 0; i < n ; i++){
                player_check[i] = "X";
//                System.out.println(player_check[i]);
            }
        }
        String[] check = new String[n];
        for(int i = 0 ; i < n ; i++){ // row check
            for(int j = 0; j < n ; j++){
                check[j] = board[i][j];
//                System.out.println(check[j]);
            }
            if(Arrays.deepEquals(check,player_check)){
                return true;
            }
            for(int j = 0; j < n ; j++){
                check[j] = board[j][i];
            }
            if(Arrays.deepEquals(check,player_check)){
                return true;
            }
        }
        for(int i = 0; i < n ; i++){ // แนวทะเเยงมุมซ้าย
            for(int j =0 ; j < n; j++){
                if(i == j){
                    check[j] = board[i][j];
                    break;
                }
                if(Arrays.deepEquals(check,player_check)){
                    return true;
                }
            }
        }
        for(int i = 0; i<n ; i++){ // แนวทะเเยงมุมขวา่
            for(int j=0; j<n ;j++){
                if(j == n-i-1 ){
                    check[i] = board[i][j];
                }
                if(Arrays.deepEquals(check,player_check)){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] agrs){
        sizeBoard();
        while(true){
            showBoard();
            createPlayer();
            if(checkWin() == true){
                break;
            }

        }
        showBoard();
        System.out.println("You Win!");

    }
}
