import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

//class drawing extends Canvas {
//    public void paint(Graphics g){
//        // 1st rows
//        g.drawRect(10,10,120,160);
//        g.drawRect(130,10,120,160);
//        g.drawRect(250,10,120,160);
//        g.drawRect(370,10,120,160);
//
//        // 2nd rows
//        g.drawRect(10,170,120,160);
//        g.drawRect(130,170,120,160);
//        g.drawRect(250,170,120,160);
//        g.drawRect(370,170,120,160);
//
//        // 3rd rows
//        g.drawRect(10,330,120,160);
//        g.drawRect(130,330,120,160);
//        g.drawRect(250,330,120,160);
//        g.drawRect(370,330,120,160);
//    }
//}
//class Board{
//    public void setup(){
//        JFrame f = new JFrame("OX Game");
//        drawing d = new drawing();
//
//        f.add(d);
//        f.setSize(500,500);
//        f.setVisible(true);
//        f.setLayout(null);
//        f.setResizable(false);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
//}
public class q3 {
    public static int n;
    public static String[][] board = new String[n][n];
    public static String player = "X";
    public static void showBoard(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = 0;
        System.out.println("\t\t\t\t\t\t\t-------------");
        for(int i=0; i<n;i++){
            for(int j=0;j<n;j++){
                board[i][j] = String.valueOf(k);
                k++;
                System.out.print(board[i][j] + "   ");
            }
            System.out.println("\n");
        }
        System.out.println("\t\t\t\t\t\t\t-------------");
    }
    public static void createPlayer(){
        while (true){
            try{
                Scanner sc = new Scanner(System.in);
                System.out.print("\t\t\t\t\t\t  Put the position Player " + player + ": ");
                int pos = sc.nextInt();
                int after = sc.nextInt();
                if((pos >= 0 && pos<n*n) && board[pos][after] == String.valueOf(Character.forDigit(pos,10))){
                    board[pos][after] = player;
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
    public static  void checkWin(){
        for(int i = 0; i < 9; i++){
            showBoard();
            createPlayer();
            if (((board[0] == board[1]) && (board[1] == board[2])) ||
                    ((board[3] == board[4]) && (board[4] == board[5]))||
                    ((board[6] == board[7]) && (board[7] == board[8]))||
                    ((board[0] == board[3]) && (board[3] == board[6]))||
                    ((board[1] == board[4]) && (board[4] == board[7]))||
                    ((board[2] == board[5]) && (board[5] == board[8]))||
                    ((board[0] == board[4]) && (board[4] == board[8]))||
                    ((board[2] == board[4]) && (board[4] == board[6]))){
                if(player == "X"){
                    showBoard();
                    System.out.println("\t\t\t\t\t\t\tThis round Player O Win.");
                    break;
                }
                else{
                    showBoard();
                    System.out.println("\t\t\t\t\t\t\tThis round Player X Win.");
                    break;
                }
            }
            else if(i == 8){
                showBoard();
                System.out.println("\t\t\t\t\t\t\tDraw");
            }
        }
    }

    public static void main(String[] agrs){
        showBoard();
        createPlayer();
        checkWin();
    }
}
