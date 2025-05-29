import javax.swing.*;

public class Board {

    private final Button[][] buttons;
    private int targetValue;
    private int currentSum;
    private int movesLeft;
    public int var;
    public int var2;
    public int var3;

    public Board(int size, int targetValue, int moves) {
        this.buttons = new Button[size][size];
        this.targetValue = targetValue;
        this.movesLeft = moves;
        initializeButtons();
        this.var=moves;
        this.var2=targetValue;
        this.var3=size;
    }

    private void initializeButtons() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j] = new Button();
            }
        }
    }

    public Button[][] getButtons() {
        return buttons;
    }

    public int getTargetValue() {
        return targetValue;
    }

    public int getCurrentSum() {
        return currentSum;
    }

    public int getMovesLeft() {
        return movesLeft;
    }

    public void setTargetValue(int targetValue) {
        this.targetValue = targetValue;
    }

    public void setCurrentSum(int currentSum) {
        this.currentSum = currentSum;
    }

    public void setMovesLeft(int movesLeft) {
        this.movesLeft = movesLeft;
        if(movesLeft==0){
            JOptionPane.showMessageDialog(null,"No more moves","Loser",JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }
}
