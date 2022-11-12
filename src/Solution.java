import java.util.Arrays;

public class Solution {
    public static final char CLEAR_SQUARE = '.';
    public static final char BLOCKED_SQUARE = 'X';
    private static int sum = 0;
    private static int numVisit = 47;

    public static int solution(String[] R) {
        if (R.length == 0 || R[0].length() == 0) return 0;
        char[][] tab = stringToTab(R);
        int i = 0, j = 0;
        moveRight(tab, i, j);
        return sum;
    }

    private static char[][] stringToTab(String[] R) {
        char[][] tab = new char[R.length][R[0].length()];
        for (int i = 0; i < R.length; i++) {
            char[] ch = R[i].toCharArray();
            System.arraycopy(ch, 0, tab[i], 0, ch.length);
        }
        return tab;
    }

    private static void moveRight(char[][] ch, int i, int j) {
        checkForInfiniteLoop(ch, i, j);
        incrementSum(ch, i, j);
        if (canMoveRight(ch, i, j)) moveRight(ch, i, ++j);
        if (canMoveDown(ch, i, j)) moveDown(ch, ++i, j);
        if (canMoveLeft(ch, i, j)) moveLeft(ch, i, --j);
        if (canMoveUp(ch, i, j)) moveUp(ch, --i, j);
    }

    private static void moveDown(char[][] ch, int i, int j) {
        checkForInfiniteLoop(ch, i, j);
        incrementSum(ch, i, j);
        if (canMoveDown(ch, i, j)) moveDown(ch, ++i, j);
        if (canMoveLeft(ch, i, j)) moveLeft(ch, i, --j);
        if (canMoveUp(ch, i, j)) moveUp(ch, --i, j);
        if (canMoveRight(ch, i, j)) moveRight(ch, i, ++j);
    }

    private static void moveUp(char[][] ch, int i, int j) {
        checkForInfiniteLoop(ch, i, j);
        incrementSum(ch, i, j);
        if (canMoveUp(ch, i, j)) moveUp(ch, --i, j);
        if (canMoveRight(ch, i, j)) moveRight(ch, i, ++j);
        if (canMoveDown(ch, i, j)) moveDown(ch, ++i, j);
        if (canMoveLeft(ch, i, j)) moveLeft(ch, i, --j);
    }

    private static void moveLeft(char[][] ch, int i, int j) {
        checkForInfiniteLoop(ch, i, j);
        incrementSum(ch, i, j);
        if (canMoveLeft(ch, i, j)) moveLeft(ch, i, --j);
        if (canMoveUp(ch, i, j)) moveUp(ch, --i, j);
        if (canMoveRight(ch, i, j)) moveRight(ch, i, ++j);
        if (canMoveDown(ch, i, j)) moveDown(ch, ++i, j);
    }

    private static void checkForInfiniteLoop(char[][] ch, int i, int j) {
        if ((char) numVisit == ch[i][j]) { // if infinite loop block all square
            blockAllSquare(ch);
        }
    }

    private static void incrementSum(char[][] ch, int i, int j) {
        if (isNotVisited(ch, i, j)) {
            sum++;
            setVisited(ch, i, j);
        }
    }

    private static void blockAllSquare(char[][] ch) {
        for (char[] chars : ch) {
            Arrays.fill(chars, BLOCKED_SQUARE);
        }
    }

    private static boolean canMoveUp(char[][] tab, int i, int j) {
        return i != 0 && tab[i - 1][j] != BLOCKED_SQUARE;
    }

    private static boolean canMoveDown(char[][] tab, int i, int j) {
        return i != tab.length - 1 && tab[i + 1][j] != BLOCKED_SQUARE;
    }

    private static boolean canMoveLeft(char[][] tab, int i, int j) {
        return j != 0 && tab[i][j - 1] != BLOCKED_SQUARE;
    }

    private static boolean canMoveRight(char[][] tab, int i, int j) {
        return j != tab[i].length - 1 && tab[i][j + 1] != BLOCKED_SQUARE;
    }

    private static boolean isNotVisited(char[][] tab, int i, int j) {
        return tab[i][j] == CLEAR_SQUARE;
    }

    private static void setVisited(char[][] tab, int i, int j) {
        numVisit++;
        tab[i][j] = (char) numVisit;
    }
}
