public class AlphabetWarGame {

    private int wStrength = 4;
    private int pStrength = 3;
    private int bStrength = 2;
    private int sStrength = 1;
    private int mStrength = 4;
    private int qStrength = 3;
    private int dStrength = 2;
    private int zStrength = 1;

    public AlphabetWarGame() {
    }

    public AlphabetWarGame(int wStrength, int pStrength, int bStrength, int sStrength, int mStrength, int qStrength,
            int dStrength, int zStrength) {
        this.wStrength = wStrength;
        this.pStrength = pStrength;
        this.bStrength = bStrength;
        this.sStrength = sStrength;
        this.mStrength = mStrength;
        this.qStrength = qStrength;
        this.dStrength = dStrength;
        this.zStrength = zStrength;
    }

    public String AlphabetWar(String word) {
        return determineWinner(word, word);
    }

    public String AlphabetWar(String leftSideWord, String rightSideWord) {
        return determineWinner(leftSideWord, rightSideWord);
    }

    private String determineWinner(String leftSideWord, String rightSideWord) {
        int leftScore = 0;
        int rightScore = 0;

        for (char c : leftSideWord.toCharArray()) {
            switch (c) {
                case 'w':
                    leftScore += wStrength;
                    break;
                case 'p':
                    leftScore += pStrength;
                    break;
                case 'b':
                    leftScore += bStrength;
                    break;
                case 's':
                    leftScore += sStrength;
                    break;
            }
        }

        for (char c : rightSideWord.toCharArray()) {
            switch (c) {
                case 'm':
                    rightScore += mStrength;
                    break;
                case 'q':
                    rightScore += qStrength;
                    break;
                case 'd':
                    rightScore += dStrength;
                    break;
                case 'z':
                    rightScore += zStrength;
                    break;
            }
        }

        if (leftScore > rightScore) {
            return "Left side wins!";
        } else if (rightScore > leftScore) {
            return "Right side wins!";
        } else {
            return "Let's fight again!";
        }
    }

    public static void main(String[] args) {

        AlphabetWarGame game1 = new AlphabetWarGame();
        System.out.println(game1.AlphabetWar("z"));
        System.out.println(game1.AlphabetWar("zdqmwpbs"));
        System.out.println(game1.AlphabetWar("wwwwwwz"));

        AlphabetWarGame game2 = new AlphabetWarGame(2, 1, 3, 5, 1, 2, 1, 7);
        System.out.println(game2.AlphabetWar("z"));
        System.out.println(game2.AlphabetWar("wwwwwwz"));
    }
}