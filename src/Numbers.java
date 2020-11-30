import javax.swing.*;

public class Numbers {
    int addInput;
    int sum = 0;

    public Numbers(int n1) {
        this.addInput = n1;
    }

    public void updateValues(int n1) {
        this.addInput = n1;
        sum += addInput;
        JOptionPane.showMessageDialog(null, "Your Current Calorie Intake is " + sum);
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int newSum) {
        this.sum = newSum;
    }
}
