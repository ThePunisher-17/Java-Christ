public class AlphabetWarGame {
    
    AlphabetWarGame() {}
    
    AlphabetWarGame(String fight) 
    {
        int left = 0;
        int right = 0;
        for(int i = 0; i < fight.length(); i++){
            if(fight.charAt(i) == 'w' || fight.charAt(i) == 'p' || fight.charAt(i) == 'b' || fight.charAt(i) == 's'){
                if(fight.charAt(i) == 'w'){
                    left += 4;
                }
                else if(fight.charAt(i) == 'p'){
                    left += 3;
                }
                else if(fight.charAt(i) == 'b'){
                    left += 2;
                }
                else if(fight.charAt(i) == 's'){
                    left += 1;
                }
            }
            else if(fight.charAt(i) == 'm' || fight.charAt(i) == 'q' || fight.charAt(i) == 'd' || fight.charAt(i) == 'z'){
                if(fight.charAt(i) == 'm'){
                    right += 4;
                }
                else if(fight.charAt(i) == 'q'){
                    right += 3;
                }
                else if(fight.charAt(i) == 'd'){
                    right += 2;
                }
                else if(fight.charAt(i) == 'z'){
                    right += 1;
                }
            }
        }
        if(left > right)
        {
            System.out.println("Left side wins!");
        }
        else if(right > left)
        {
            System.out.println("Right side wins!");
        }
        else
        {
            System.out.println("Let's fight again!");
        }
    }
    

    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);
        // String fight = scanner.nextLine();
        // AlphabetWarGame alphabetWarGame = new AlphabetWarGame(fight);
        String fight = "s";
        AlphabetWarGame alphabetWarGame = new AlphabetWarGame(fight);
    }
}
