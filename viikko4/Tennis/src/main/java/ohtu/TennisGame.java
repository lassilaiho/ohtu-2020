package ohtu;

public class TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    private static final int endGameLimit = 4;
    private static final int winMargin = 2;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            player1Score++;
        } else {
            player2Score++;
        }
    }

    public String getScore() {
        if (player1Score == player2Score) {
            return getEqualScores();
        } else if (player1Score >= endGameLimit || player2Score >= endGameLimit) {
            return getEndGameScores();
        }
        return getMidGameScores();
    }

    private String getEqualScores() {
        var word = getScoreWord(player1Score);
        if (player1Score < endGameLimit) {
            word += "-All";
        }
        return word;
    }

    private String getMidGameScores() {
        return getScoreWord(player1Score) + "-" + getScoreWord(player2Score);
    }

    private String getEndGameScores() {
        var winningPlayer = player1Name;
        if (player2Score > player1Score) {
            winningPlayer = player2Name;
        }
        if (Math.abs(player1Score - player2Score) < winMargin) {
            return "Advantage " + winningPlayer;
        }
        return "Win for " + winningPlayer;
    }

    private String getScoreWord(int score) {
        switch (score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                return "Deuce";
        }
    }

}
