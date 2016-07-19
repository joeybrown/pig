import java.util.Scanner;
import java.util.Random;

public class Pig
{
    private static int Player1 = 1;
    private static int Player2 = 2;
    private static int WinPoints = 21;
    
    private int PlayerTurn;
    
    private int Player1TotalPoints;
    private int Player2TotalPoints;
    
    private String OverallGameState() {
        return "It is Player " + PlayerTurn + "'s turn. Player 1 has " +
                Player1TotalPoints + " points and Player 2 has " +
                Player2TotalPoints + " points.";
    }
    
    private void Roll(int roundPoints){
        System.out.println("Enter 0 to roll or 1 to hold.");
        Scanner reader = new Scanner(System.in);
        int decision = reader.nextInt();
        if (decision == 0) {
            Random rand = new Random();
            int dieResult = rand.nextInt(6) + 1;
            if (dieResult == 1) {
                System.out.println("Player " + PlayerTurn + 
                "rolled 1 and busted.");
            } else {
                System.out.println("Player " + PlayerTurn + 
                " rolled " + dieResult);
                int newRoundPoints = roundPoints + dieResult;
                Roll(newRoundPoints);
            }
        } else {
            if (PlayerTurn == 1) {
                Player1TotalPoints = Player1TotalPoints + roundPoints;
            } else {
                Player2TotalPoints = Player2TotalPoints + roundPoints;
            }
            
            System.out.println("Player " + PlayerTurn + "earned " +
             roundPoints + " points.");
        }
    }
    
    public static void TestRandom(){
        Random rand = new Random();
        int dieResult = rand.nextInt(4) + 1;
        System.out.println(dieResult);
    }
    
    private void Round() {
        System.out.println(OverallGameState());
        Roll(0);
        PlayerTurn = 2;
        System.out.println("Player 2's turn!");
        Roll(0);
        PlayerTurn = 1;
        System.out.println("Player 1's turn!");
    }
    
    private boolean IsGameOver(){
        boolean player1Won = Player1TotalPoints >= WinPoints;
        boolean player2Won = Player2TotalPoints >= WinPoints;
        return player1Won || player2Won;
    }

 
    public Pig()
    {
        PlayerTurn = 1;
        Player1TotalPoints = 0;
        Player2TotalPoints = 0;
        
        while (!IsGameOver()) {
            Round();
        }
        
    }
}
