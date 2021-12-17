package nullnumber1.controller;

import nullnumber1.entity.Point;

import java.util.Arrays;

public class InputValidator {

    private static final double[] valueX = {-4, -3, -2, -1, 0, 1, 2, 3, 4};
    private static final double[] valueR = {1, 2, 3, 4, 5};

    public static boolean validateSvgEntity(Point p) {
        boolean flagY = p.getY() > -3 && p.getY() < 5;
        boolean flagR = Arrays.binarySearch(valueR, p.getR()) > -1;
        return flagY && flagR;
    }

    public static boolean validateFormEntity(Point p) {
        boolean flagY = p.getY() > -3 && p.getY() < 5;
        boolean flagX = Arrays.binarySearch(valueX, p.getX()) > -1;
        boolean flagR = Arrays.binarySearch(valueR, p.getR()) > -1;
        return flagX && flagY && flagR;
    }
}
