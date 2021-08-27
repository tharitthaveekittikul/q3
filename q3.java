import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class drawing extends Canvas {
    public int w;
    public int h;
    public void paint(Graphics g){
        int n = Board.N_int;
//        System.out.println(n);
        w = 800 / n;
        h = 800 / n;
        for(int i = 0; i <= 800-w ; i = i + w){
            for(int j =0; j <= 800-h ; j = j + h){
                g.drawRect(i, j, w, h);
//                System.out.println(String.valueOf(w) +"   "+ String.valueOf(h));
            }
        }
    }
}
class Board implements MouseListener{
    public static int N_int;
    public JTextField N;
    public drawing d;
    public JFrame f = new JFrame("OX Game");
    public static int mX, mY;

    public void setup(){
        JFrame menu = new JFrame("OX Game");
        JLabel number = new JLabel("Please put the n");
        JLabel text_check = new JLabel("Please put only numbers");
        N = new JTextField();
        JButton b_n = new JButton("OK");

        number.setBounds(30,50,300,50);
        number.setFont(new Font("Sample glyphs",Font.BOLD, 25));

        text_check.setBounds(65,220,300,50);
        text_check.setFont(new Font("Sample glyphs",Font.BOLD, 15));
        text_check.setVisible(false);

        N.setBounds(30,100,220,35);
        N.setFont(new Font("Sample glyphs",Font.BOLD, 20));

        b_n.setBounds(120,150,70,70);
        b_n.setFont(new Font("Sample glyphs",Font.BOLD, 20));

        menu.setSize(300,300);
        menu.setVisible(true);
        menu.setLayout(null);
        menu.setResizable(false);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.add(number);menu.add(b_n);menu.add(N);menu.add(text_check);

        b_n.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    N_int = Integer.parseInt(N.getText());
//                    System.out.println(N_int);
                    menu.setVisible(false);
                    q3.sizeBoard();
                    showBoard();
                }
                catch (Exception e){
                    text_check.setVisible(true);
                }

            }
        });

    }
    public ArrayList<JLabel> Label_player;
    public void showBoard(){
        f = new JFrame("OX Game");
        d = new drawing();
        f.addMouseListener(this);
        Label_player = new ArrayList<JLabel>();
        for(int i = 0; i < N_int ; i++){
            for(int j =0 ; j < N_int; j++){
                Label_player.add(new JLabel(q3.board[i][j]));

            }
        }
//        Label_player.get(0).setBounds(0,0,140,140);
//        Label_player.get(0).setFont(new Font("Sample glyphs",Font.BOLD, N_int*20));
//        f.add(Label_player.get(0));
        int i = 0;
        for(int j = 0; j <= 800-(800/N_int) ; j = j + (800/N_int)){  //640   //160
            for(int k =0; k <= 800-(800/N_int) ; k = k + 800/N_int){ //640   //160
                Label_player.get(i).setBounds(j+40,k+20,(800/N_int)-55,(800/N_int)-55);
                Label_player.get(i).setFont(new Font("Sample glyphs",Font.BOLD, 250/N_int));
//                Label_player.get(i).setText(q3.board[i][j]);
                f.add(Label_player.get(i));
                i++;
            }
        }

        f.add(d);
        f.setSize(800,800);
        f.setVisible(true);
        f.setLayout(null);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static  boolean checkWin(){
        String[] player_check = new String[q3.n];
        if(q3.player == "X"){
            for(int i = 0; i<q3.n ; i++){
                player_check[i] = "O";
//                System.out.println(player_check[i]);
            }
        }
        else if(q3.player == "O"){
            for(int i = 0; i < q3.n ; i++){
                player_check[i] = "X";
//                System.out.println(player_check[i]);
            }
        }
        String[] check = new String[q3.n];
        for(int i = 0 ; i < q3.n ; i++){ // row check
            for(int j = 0; j < q3.n ; j++){
                check[j] = q3.board[i][j];
//                System.out.println(check[j]);
            }
            if(Arrays.deepEquals(check,player_check)){
                return true;
            }
            for(int j = 0; j < q3.n ; j++){
                check[j] = q3.board[j][i];
            }
            if(Arrays.deepEquals(check,player_check)){
                return true;
            }
        }
        for(int i = 0; i < q3.n ; i++){ // แนวทะเเยงมุมซ้าย
            for(int j =0 ; j < q3.n; j++){
                if(i == j){
                    check[j] = q3.board[i][j];
                    break;
                }
                if(Arrays.deepEquals(check,player_check)){
                    return true;
                }
            }
        }
        for(int i = 0; i<q3.n ; i++){ // แนวทะเเยงมุมขวา่
            for(int j=0; j<q3.n ;j++){
                if(j == q3.n-i-1 ){
                    check[i] = q3.board[i][j];
                }
                if(Arrays.deepEquals(check,player_check)){
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }
    int count = 0;
    @Override
    public void mouseReleased(MouseEvent e) {
        count++;
        mY = e.getX() / (800/N_int);
        mX = e.getY() / (800/N_int);
//        System.out.println(mY);
//        System.out.println(mX);
        int pos = Board.mY;
        int after = Board.mX;
        q3.board[pos][after] = q3.player;
        f.setVisible(false);
        if(count == q3.n*q3.n){
            f.setVisible(false);
            after_win();
        }
        if(q3.player == "X"){
            q3.player = "O";
            showBoard();
            if(checkWin() == true){
                f.setVisible(false);
                after_win();
//                System.out.println("winner");
            }
        }
        else if(q3.player == "O"){
            q3.player = "X";
            showBoard();
            if(checkWin() == true){
                f.setVisible(false);
                after_win();
//                System.out.println("winner!");
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public void after_win(){
        JFrame fexit = new JFrame("OX Game");
        JLabel win = new JLabel("WINNER");
        win.setBounds(250,250,100,100);
        win.setFont(new Font("Sample glyphs",Font.BOLD, 100));

        fexit.add(win);
        fexit.setSize(800,800);
        fexit.setVisible(true);
        fexit.setLayout(null);
        fexit.setResizable(false);
        fexit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if(q3.player == "X"){
            win.setText("O WINNER");
        }
        if(q3.player == "O"){
            win.setText("X WINNER");
        }
        if(count == q3.n*q3.n){
            win.setText("DRAW");
        }
    }


}
public class q3 {
    public static int n;
    public static String[][] board;
    public static String player = "X";

    public static void sizeBoard(){
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Please put n: ");
        n = Board.N_int;
        board = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = String.valueOf(i) + String.valueOf(j);
//                System.out.println(board[i][j]);
            }
        }
    }

    public static void main(String[] agrs){
        Board b = new Board();
        b.setup();
    }
}
