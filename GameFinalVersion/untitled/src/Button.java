import java.util.Random;
public class Button {
    private final int digit;
    private boolean isActive;

    public Button() {
        Random rand = new Random();
        this.digit = rand.nextInt(9) + 1;
        this.isActive = true;
    }

    public int getDigit() {
        return digit;
    }

    public boolean isActive() {
        return isActive;
    }
    public boolean isActive2() {
        return isActive=true;
    }

    public void deactivate() {
        isActive = false;
    }
}
