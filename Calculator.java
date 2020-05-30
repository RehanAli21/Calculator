import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Main extends JFrame
{
    JPanel panel;
    JTextField pri, sec;
    JButton[] opBtn = new JButton[10];
    JButton[] nums = new JButton[10];

    public static void main(String[] args)
    {
        new Main();
    }

    public Main()
    {
        this.setSize(400, 400); //set the size of frame
        Toolkit tk = Toolkit.getDefaultToolkit(); //have functions
        Dimension dim = tk.getScreenSize(); //To get dimensions
        int xPos = dim.width/2 - this.getWidth()/2; //get center point in horizontal
        int yPos = dim.height/2 - this.getHeight()/2; //get center point in vertical
        this.setLocation(xPos, yPos); //set the location of frame at center
        this.setResizable(false); //disable the resize of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Calculator"); //to set title

        panel = new JPanel(); //to create a panel
        ListenToButton ltb = new ListenToButton(); //action listener

        //Assigning textFields with 23 columns
        pri = new JTextField( 23);
        sec = new JTextField(23);

        //making textFields read-only
        pri.setEditable(false);
        sec.setEditable(false);

        //Assigning Operational buttons
        String[] opBtnNames = {"Square","C","CE","/","+","-","x",".","=","color"};
        for (int i=0; i<opBtn.length; i++)
            opBtn[i] = new JButton(opBtnNames[i]);

        //Assigning number buttons
        for (int i = 0; i<nums.length; i++)
            nums[i] = new JButton(""+i);

        //adding actionListners
        for (int i =0; i<10; i++)
        {
            nums[i].addActionListener(ltb);
            opBtn[i].addActionListener(ltb);
        }

        //This method controls UI
        componentUIMethod(false);

        //Adding textFields
        panel.add(pri);
        panel.add(sec);

        //Adding buttons in order
        for (int i = 0; i<4; i++) panel.add(opBtn[i]);
        for (int i = 7; i<10; i++) panel.add(nums[i]);
        panel.add(opBtn[4]);
        for (int i = 4; i<7; i++) panel.add(nums[i]);
        panel.add(opBtn[5]);
        for (int i = 1; i<4; i++) panel.add(nums[i]);
        panel.add(opBtn[6]);
        panel.add(nums[0]);
        for (int i = 7; i<10; i++) panel.add(opBtn[i]);

        this.add(panel); //add panel to Jframe
        this.setVisible(true); //to show frame
    }

    private void componentUIMethod(Boolean dark)
    {
        panel.setBackground(dark ? Color.BLACK :Color.WHITE);

        pri.setBackground(dark ? Color.BLACK :Color.WHITE);
        sec.setBackground(dark ? Color.BLACK :Color.WHITE);

        pri.setForeground(dark ? Color.WHITE :Color.BLACK);
        sec.setForeground(dark ? Color.WHITE :Color.BLACK);

        pri.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        sec.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));

        pri.setMargin(new Insets(10,5,10,5));
        sec.setMargin(new Insets(10,5,10,5));

        for (JButton num : nums)
        {
            num.setPreferredSize(new Dimension(this.getWidth() / 5, 40));
            num.setBackground(dark ? Color.BLACK :Color.WHITE);
            num.setForeground(dark ? Color.WHITE :Color.BLACK);
            num.setBorder(new LineBorder((dark)? Color.WHITE :Color.BLACK, 2));
        }

        for (JButton jButton : opBtn)
        {
            jButton.setPreferredSize(new Dimension(this.getWidth()/5, 40));
            jButton.setBackground(dark ? Color.BLACK :Color.WHITE);
            jButton.setForeground(dark ? Color.WHITE :Color.BLACK);
            jButton.setBorder(new LineBorder(dark ? Color.WHITE :Color.BLACK, 2));
        }

        opBtn[9].setBackground(dark ? Color.WHITE :Color.BLACK);
        opBtn[9].setForeground(dark ? Color.BLACK :Color.WHITE);
        opBtn[9].setBorder(new LineBorder(dark ? Color.BLACK :Color.WHITE, 2));

    }

    class ListenToButton implements ActionListener
    {
        Boolean dark = false;
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == nums[0])
                pri.setText(pri.getText() + "0");
            else if (e.getSource() == nums[1])
                pri.setText(pri.getText()+"1");
            else if (e.getSource() == nums[2])
                pri.setText(pri.getText()+"2");
            else if (e.getSource() == nums[3])
                pri.setText(pri.getText()+"3");
            else if (e.getSource() == nums[4])
                pri.setText(pri.getText()+"4");
            else if (e.getSource() == nums[5])
                pri.setText(pri.getText()+"5");
            else if (e.getSource() == nums[6])
                pri.setText(pri.getText()+"6");
            else if (e.getSource() == nums[7])
                pri.setText(pri.getText()+"7");
            else if (e.getSource() == nums[8])
                pri.setText(pri.getText()+"8");
            else if (e.getSource() == nums[9])
                pri.setText(pri.getText()+"9");
            else if (e.getSource() == opBtn[7])
                pri.setText(pri.getText()+".");
            else if (e.getSource() == opBtn[4])
                calculate("+");
            else if (e.getSource() == opBtn[5])
                calculate("-");
            else if (e.getSource() == opBtn[6])
                calculate("*");
            else if (e.getSource() == opBtn[3])
                calculate("/");
            else if (e.getSource() == opBtn[8])
                isEqualTo();
            else if (e.getSource() == opBtn[0])
                square();
            else if (e.getSource() == opBtn[1])
                clear();
            else if (e.getSource() == opBtn[2])
                clearEach();
            else if (e.getSource() == opBtn[9])
            {
                dark = !dark;
                componentUIMethod(dark);
            }
        }

        private void calculate(String operator)
        {
            String value = pri.getText();
            String his = sec.getText();

            if (!value.isEmpty() && !his.isEmpty())
            {
                String[] a = his.split(" ");
                double[] b = {Double.parseDouble(a[0]), Double.parseDouble(value)};
                switch (a[1])
                {
                    case "+" -> pri.setText("" + (b[0] + b[1]));
                    case "-" -> pri.setText("" + (b[0] - b[1]));
                    case "*" -> pri.setText("" + (b[0] * b[1]));
                    case "/" -> pri.setText("" + (b[0] / b[1]));
                }

                sec.setText("");
            }
            else if(!value.isEmpty())
            {
                sec.setText(pri.getText()+" "+operator);
                pri.setText("");
            }
        }

        private void isEqualTo()
        {
            String value = pri.getText();
            String his = sec.getText();

            if (!value.isEmpty() && !his.isEmpty())
            {
                String[] a = his.split(" ");
                double[] b = {Double.parseDouble(a[0]), Double.parseDouble(value)};
                switch (a[1])
                {
                    case "+" -> pri.setText("" + (b[0] + b[1]));
                    case "-" -> pri.setText("" + (b[0] - b[1]));
                    case "*" -> pri.setText("" + (b[0] * b[1]));
                    case "/" -> pri.setText("" + (b[0] / b[1]));
                }

                sec.setText("");
            }
        }

        private void square()
        {
            double value = Double.parseDouble(pri.getText());
            pri.setText(""+value*value);
        }

        private void clear()
        {
            pri.setText("");
            sec.setText("");
        }

        private void clearEach()
        {
            String value = pri.getText();
            pri.setText("");
            for (int i = 0; i<value.length()-1; i++)
                pri.setText(pri.getText()+value.charAt(i));
        }
    }
}