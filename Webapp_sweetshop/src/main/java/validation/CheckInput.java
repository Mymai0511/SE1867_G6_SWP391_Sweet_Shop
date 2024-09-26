package validation;

public class CheckInput {
    public static float checkInputIsFloat(String input) {
        float number = 0.0f;
        if (input != null && !input.isEmpty()) {
            number = Float.parseFloat(input);
        }
        return number;
    }

    public static double checkInputIsDouble(String input) {
        double number = 0.0d;
        if (input != null && !input.isEmpty()) {
            number = Double.parseDouble(input);
        }
        return number;
    }


}
