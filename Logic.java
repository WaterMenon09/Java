import java.util.ArrayList;

public class Logic {

    static boolean isInteger(double val) {
        return (long) val == val;
    }

    static String calculation(StringBuilder sb, ArrayList<Double> operands, String sign) {
        if (sign == null || sb.length() == 0) return sb.toString();

        String next = sb.toString();
        operands.add(Double.parseDouble(sb.toString()));
        sb.delete(0, sb.length());
        double res = operands.get(0);
        if (operands.size() == 2) {

            if (sign.compareTo("+") == 0) {
                res = operands.get(0) + operands.get(1);
            }
            if (sign.compareTo("-") == 0) {
                res = operands.get(0) - operands.get(1);
            }
            if (sign.compareTo("*") == 0) {
                res = operands.get(0) * operands.get(1);
            }
            if (sign.compareTo("/") == 0) {
                res = operands.get(0) / operands.get(1);
            }
            operands.clear();
            operands.add(res);
            if (sb.length() > 0) sb.delete(0, sb.length());

            if (isInteger(res)) sb.append((long) res);
            else sb.append(res);

            next = sb.toString();
        }
        if (sb.length() > 0) sb.delete(0, sb.length());
        if (isInteger(res)) sb.append((long) res);
        else sb.append(res);
        return next;
    }
}
