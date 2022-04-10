import javax.swing.*;
import java.util.ArrayList;

public class Buttons {

    // Positions of the buttons
    final int x[] = {10, 105, 200, 295};
    final int y[] = {210, 280, 350, 420};

    boolean operator_found;
    ArrayList<Double> operands;
    Logic L;
    String sign;
    final String[] btns = {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "+/-", "0", ".", "/"};
    ArrayList<JButton> B;
    StringBuilder sb;
    JButton CE, C, EQUALS, PERCENT, SQRT, FRACTION, SQUARE;

    public Buttons() {
        B = new ArrayList<JButton>();
        sb = new StringBuilder("");
        operands = new ArrayList<>();
        L = new Logic();
    }


    public void createButtons() {
        for (int i = 0; i < 16; i++) {
            B.add(new JButton(btns[i]));
            B.get(i).setBounds(x[i % 4], y[i / 4], 80, 50);
            int num = i;
            B.get(i).addActionListener(e -> {
                if (num == 12) { //     +/- Button
                    double negate = Double.parseDouble(sb.toString());
                    negate = -negate;
                    sb.delete(0, sb.length());
                    if (Logic.isInteger(negate))  sb.append((long) negate);
                    else sb.append(negate);
                    Console.console.setText(sb.toString());
                }
                else if ((num == 3 || num == 7 || num == 11 || num == 15)) { //     +, -, *, / Buttons
                    sign = btns[num];
                    operator_found = true;
                    String next = Logic.calculation(sb, operands, sign);
                    Console.console.setText(next);
                } else { //     Numeric Buttons
                    if (operator_found) sb.delete(0, sb.length());
                    sb.append(btns[num]);
                    Console.console.setText(sb.toString());
                    operator_found = false;
                }
            });
        }

        PERCENT = new JButton("%");
        PERCENT.setBounds(200, 70, 80, 50);
        PERCENT.addActionListener(e -> {
            double percent = Double.parseDouble(sb.toString());
            percent /= 100;
            sb.delete(0, sb.length());
            if (Logic.isInteger(percent)) sb.append((long) percent);
            else sb.append(percent);
            Console.console.setText(sb.toString());
        });

        SQUARE = new JButton("x^2");
        SQUARE.setBounds(295, 70, 80, 50);
        SQUARE.addActionListener(e -> {
            double square = Double.parseDouble(sb.toString());
            square *= square;
            sb.delete(0, sb.length());
            if (Logic.isInteger(square)) sb.append((long) square);
            else sb.append(square);
            Console.console.setText(sb.toString());
        });

        SQRT = new JButton("√");
        SQRT.setBounds(10, 140, 80, 50);
        SQRT.addActionListener(e -> {
            double sqrt = Double.parseDouble(sb.toString());
            sqrt = Math.sqrt(sqrt);
            sb.delete(0, sb.length());
            if (Logic.isInteger(sqrt)) sb.append((long) sqrt);
            else sb.append(sqrt);
            Console.console.setText(sb.toString());
        });

        FRACTION = new JButton("1/x");
        FRACTION.setBounds(105, 140, 80, 50);
        FRACTION.addActionListener(e -> {
            double fraction = Double.parseDouble(sb.toString());
            fraction = 1 / fraction;
            sb.delete(0, sb.length());
            if (Logic.isInteger(fraction)) sb.append((long) fraction);
            else sb.append(fraction);
            Console.console.setText(sb.toString());

        });

        C = new JButton("C");
        C.setBounds(105, 70, 80, 50);
        C.addActionListener(e -> {
            if (sb.length() != 0) sb.deleteCharAt(sb.length() - 1);
            Console.console.setText(sb.toString());
        });

        // Clear Everything
        CE = new JButton("CE");
        CE.setBounds(10, 70, 80, 50);
        CE.addActionListener(e -> {
            reset();
            sb.delete(0, sb.length());
            Console.console.setText(sb.toString());
        });

        EQUALS = new JButton("=");
        EQUALS.setBounds(200, 140, 175, 50);
        EQUALS.addActionListener(e -> {
            operator_found = true;
            String next = Logic.calculation(sb, operands, sign);
            Console.console.setText(next);
            reset();
        });

    }

    void reset() {
        operator_found = false;
        operands.clear();
        sign = null;
    }
}
