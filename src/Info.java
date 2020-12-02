import javax.swing.*;
import java.awt.*;

public class Info extends JFrame {

    private JButton userButton, calInfoButton, calAddButton, calResetButton, calSubmitButton;
    private JLabel userLabel, calNameLabel, calGoalLabel, calTakenLabel, calAddLabel;
    private JFrame userFrame, calFrame, calAddFrame;
    private JPanel userPanel, calPanel, calAddPanel;
    private JTextField text;
    Numbers model;

    public Info(int n1, Numbers model) {
        /**
         * Components for User Form GUI to hold the user's information
         *
         * Store User's input into a variable
         */
        this.model = model;
        userFrame = new JFrame();
        userButton = new JButton("Submit");
        //button.addActionListener(this);

        String[] sex = new String[]{"Male", "Female"};
        JComboBox<String> gender = new JComboBox<>(sex);

        String[] desire = new String[]{"Gain Weight", "Diet", "Maintain Weight"};
        JComboBox<String> goal = new JComboBox<>(desire);

        userPanel = new JPanel();
        userPanel.setBorder(BorderFactory.createEmptyBorder(20,50,35,50));
        GridLayout layout = new GridLayout(0,1);
        layout.setVgap(2);
        userPanel.setLayout(layout);

        userLabel = new JLabel("USER INFO");
        userLabel.setFont(new Font("Arial", Font.BOLD, 20));
        userPanel.add(userLabel);

        userPanel.add(new JLabel("First Name: "));
        JTextField firstName = new JTextField(40);
        firstName.setText("Enter your first name here");
        userPanel.add(firstName);

        userPanel.add(new JLabel("Last Name: "));
        JTextField lastName = new JTextField(40);
        lastName.setText("Enter your last name here");
        userPanel.add(lastName);

        userPanel.add(new JLabel("Your Gender: "));
        userPanel.add(gender);

        userPanel.add(new JLabel("Age: "));
        JTextField age = new JTextField(40);
        age.setText("Please enter your age in years");
        userPanel.add(age);

        userPanel.add(new JLabel("Height: "));
        JTextField height = new JTextField(40);
        height.setText("Please enter your height in cm");
        userPanel.add(height);

        userPanel.add(new JLabel("Weight: "));
        JTextField weight = new JTextField(40);
        weight.setText("Please enter your weight in kg");
        userPanel.add(weight);

        userPanel.add(new JLabel("Your Goal: "));
        userPanel.add(goal);

        userPanel.add(userButton);

        userFrame.add(userPanel, BorderLayout.CENTER);
        userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userFrame.setTitle("User Info");
        userFrame.pack();
        userFrame.setVisible(true);

        userButton.addActionListener(event -> {
            String first = firstName.getText();
            String last = lastName.getText();
            String yourGender = (String)gender.getSelectedItem();
            int ageNum = Integer.parseInt(age.getText());
            int heightNum = Integer.parseInt(height.getText());
            int weightNum = Integer.parseInt(weight.getText());
            String yourGoal = (String)goal.getSelectedItem();


            /**
             * For debugging purposes
             */
            // System.out.println("Name: " + first + " " + last);
            // System.out.println("Gender: " + yourGender);
            // System.out.println("Age: " + ageNum + " years old");
            // System.out.println("Height: " + heightNum + "cm");
            // System.out.println("Weight: " + weightNum + "kg");
            // System.out.println("Your goal: " + yourGoal);

            /**
             * Formula from Harris Benedict for calculating the calories intake of men
             *
             * @return value of variable calGoal
             */
            int calGoal = 0;
            if(yourGender.equals("Male")){
                calGoal = (int)((13.38*weightNum)+(4.799*heightNum)-(5.677*ageNum)+88.362);
                if(yourGoal.equals("Gain Weight")){
                    calGoal = Math.round(calGoal) + 1000;
                } else if (yourGoal.equals("Diet")) {
                    calGoal = Math.round(calGoal) - 500;
                }
            } else if (yourGender.equals("Female")){
                calGoal = (int)((9.247*weightNum)+(3.098*heightNum)-(4.330*ageNum)+447.593);
                if(yourGoal.equals("Gain Weight")){
                    calGoal = Math.round(calGoal) + 1000;
                } else if (yourGoal.equals("Diet")) {
                    calGoal = Math.round(calGoal) - 500;
                }
            }

            if (calGoal >= 0) {

                /**
                 * Main interactive GUI to determine and store user's calorie intake
                 *
                 */
                //do{
                calFrame = new JFrame();
                calPanel = new JPanel();
                GridLayout layout2 = new GridLayout(0, 1, 3, 3);
                calPanel.setLayout(layout2);

                calPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 35, 30));
                calNameLabel = new JLabel(first + " " + last);
                calNameLabel.setFont(new Font("Arial", Font.BOLD, 22));
                calPanel.add(calNameLabel);
                calFrame.add(calPanel, BorderLayout.CENTER);
                calFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                calFrame.setTitle("Calorie Counter GUI");
                calFrame.setSize(300, 400);
                userFrame.setVisible(false);
                calFrame.setVisible(true);

                /**
                 * Method for Info button to open the User Info
                 */
                calInfoButton = new JButton("Info");
                calPanel.add(calInfoButton);
                calInfoButton.addActionListener(info -> {
                    calFrame.setVisible(false);
                    userFrame.setVisible(true);
                });

                /**
                 * Method to Add food and calories to a variable
                 *
                 * @return the value of variable addInput
                 */
                calAddButton = new JButton("Add");
                calPanel.add(calAddButton);
                int finalCalGoal = calGoal;
                calAddButton.addActionListener(add -> {
                    calAddFrame = new JFrame();
                    calAddPanel = new JPanel();
                    calAddPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));

                    calAddLabel = new JLabel("How Many Calories Did You Take? ");
                    calAddLabel.setFont(new Font("Arial", Font.BOLD, 14));
                    calAddPanel.add(calAddLabel);

                    JTextField calBox = new JTextField(20);
                    calAddPanel.add(calBox);

                    calSubmitButton = new JButton("Submit");
                    calAddPanel.add(calSubmitButton);

                    calAddFrame.add(calAddPanel, BorderLayout.CENTER);
                    calAddFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    calAddFrame.setTitle("Add GUI");
                    calAddFrame.setSize(300, 150);
                    calFrame.setVisible(false);
                    calAddFrame.setVisible(true);


                    calSubmitButton.addActionListener(submit -> {
                        int addInput = Integer.parseInt(calBox.getText());
                        calAddFrame.setVisible(false);
                        calFrame.setVisible(true);
                        model.updateValues(addInput);

                        if (model.getSum() >= finalCalGoal) {
                            JOptionPane.showMessageDialog(null, "Congratulations! You've met your daily calorie goal for today");
                        }
                    });
                });

                /**
                 * This code was suppose to reset your current calorie intake into 0
                 */
                calResetButton = new JButton("Reset");
                calPanel.add(calResetButton);
                calResetButton.addActionListener(add -> {
                    model.setSum(0);
                });

                calGoalLabel = new JLabel("Goal: " + calGoal + " calories");
                calGoalLabel.setFont(new Font("Arial", Font.BOLD, 18));
                calPanel.add(calGoalLabel);

                /**
                 * This suppose to show your current Calorie Intake but the code won't
                 * execute twice and the variable model.getSum() will get stuck on the
                 * GUI showing only 0 regardless if it got updated
                 */
                //calTakenLabel = new JLabel("Taken: " + model.getSum());
                //calTakenLabel.setFont(new Font("Arial", Font.BOLD, 18));
                //calPanel.add(calTakenLabel);
                //} while(model.getSum() < calGoal);
            } else {
                JOptionPane.showMessageDialog(null, "Please fill the form appropriately!");
            }
        });
    }
}
