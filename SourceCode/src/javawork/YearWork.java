/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javawork;

public class YearWork {

    /**
     * 7. Sum points in triangle, on triangle and out triangle
     *
     * @author Jan Cerny
     * @version 0.6 12/10/22
     */
    public static void main() {
        double v[][] = new double[3][2];
        double points[][] = new double[3][2];
        int inTriangle;
        int onTriangle;

        //Check if want continue
        while (UI.wantContinue()) {
            inTriangle = 0;
            onTriangle = 0;

            /*
            points [0][0] => first point x
            points [0][1] => first point y
            points [1][0] => second point x
            points [1][1] => second point y
            ....
             */
            UI.getPoints(points);

            int numberTestPoints = UI.getNumberTestPoint();
            double testPoints[][] = new double[numberTestPoints][2];

            /*
            testPoints [0][0] => first test point x
            testPoints [0][1] => first test point y
            testPoints [1][0] => second test point x
            testPoints [1][1] => second test point y
            ....
             */
            UI.getTestPoints(testPoints);

            //Sum of points in triangle and on triangle
            for (int i = 0; i < testPoints.length; i++) {
                if (isInside(points[0][0], points[0][1], points[1][0], points[1][1], points[2][0], points[2][1], testPoints[i][0], testPoints[i][1])) {
                    inTriangle++;
                }
            }

            //Get vectors of triangle
            getV(points[0][0], points[0][1], points[1][0], points[1][1], points[2][0], points[2][1], v);

            //Sum of points on tringle
            onTriangle = pointOnTriangle(points, v, testPoints);

            UI.displayOutput(inTriangle, onTriangle, numberTestPoints);
        }
        return;
    }

    /**
     * Calculate area
     * 
     * @param x1 x1
     * @param y1 y1
     * @param x2 x2
     * @param y2 y2
     * @param x3 x3
     * @param y3 y3
     * @return area
     */
    static double area(double x1, double y1, double x2, double y2,
            double x3, double y3) {
        return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1)
                + x3 * (y1 - y2)) / 2.0);
    }

     /**
     * Check if point is in or on triangle
     * 
     * @param x1 x1 of triangle
     * @param y1 y1 of triangle
     * @param x2 x2 of triangle
     * @param y2 y2 of triangle
     * @param x3 x3 of triangle
     * @param y3 y3 of triangle
     * @param x x of point
     * @param y y of point
     * @return true
     * @return false
     */
    static boolean isInside(double x1, double y1, double x2,
            double y2, double x3, double y3, double x, double y) {
        double A = area(x1, y1, x2, y2, x3, y3);
        double A1 = area(x, y, x2, y2, x3, y3);
        double A2 = area(x1, y1, x, y, x3, y3);
        double A3 = area(x1, y1, x2, y2, x, y);
        return (A == A1 + A2 + A3);
    }

   /**
     * Calculate vectors for line of triangle
     * 
     * @param a1 a1 of triangle
     * @param a2 a2 of triangle
     * @param b1 b1 of triangle
     * @param b2 b2 of triangle
     * @param c1 c1 of triangle
     * @param c2 c2 of triangle
     * @param v Array of vectors
     */
    private static void getV(double a1, double a2, double b1, double b2, double c1, double c2, double v[][]) {
        v[0][0] = b1 - a1;
        v[0][1] = b2 - a2;

        v[1][0] = c1 - b1;
        v[1][1] = c2 - b2;

        v[2][0] = c1 - a1;
        v[2][1] = c2 - a2;
    }

    /**
     * Check if point is on triangle
     * 
     * @param points Array of points of triangle
     * @param v Array of vectors
     * @param testPoints Array of points of testing
     * @return Number of points on triangle
     */
    private static int pointOnTriangle(double points[][], double v[][], double testPoints[][]) {
        //EquationXY[0][n] is x = ax + t1 * vx
        //EquationXY[1][n] is y = ay + t2 * vy
        double equationXY[][] = new double[2][3];
        int testedPoint = 0;
        int onTriangle = 0;
        double t[] = new double[2];

        do {
            /*
            Constructing the equation
             */
            equationXY[0][0] = testPoints[testedPoint][0];
            equationXY[1][0] = testPoints[testedPoint][1];
            for (int i = 0; i < 3; i++) {
                if (i == 2) {
                    equationXY[0][1] = points[0][0];
                    equationXY[1][1] = points[0][1];
                } else {
                    equationXY[0][1] = points[i][0];
                    equationXY[1][1] = points[i][1];
                }
                equationXY[0][2] = v[i][0];
                equationXY[1][2] = v[i][1];

                //Check if x equation is infinity or has no solution
                if (isInfinity(v, equationXY, i)) {
                    onTriangle++;
                    break;
                }

                //Calculate t1 and t2
                t[0] = (equationXY[0][0] - equationXY[0][1]) / equationXY[0][2];
                t[1] = (equationXY[1][0] - equationXY[1][1]) / equationXY[1][2];

                //Check if t1 is equate t2. If is, then is on triangle.
                if (t[0] == t[1]) {
                    onTriangle++;
                    break;
                }
            }
            testedPoint++;
        } while (testedPoint != testPoints.length);
        return onTriangle;

    }

    /**
     * Check if result of equation is infinity
     * 
     * @param v Array of vectors of triangle
     * @param equationXY Equations x and y
     * @param numberOfVector Number of vector
     * @return true
     * @return false
     */
    private static boolean isInfinity(double v[][], double equationXY[][], int numberOfVector) {
        // If in x equation t1 is zero, then left side equation result is zero
        if (v[numberOfVector][0] == 0) {
            // if in x equation is left side zero, then x equation has infinity result
            if (equationXY[0][0] - equationXY[0][1] == 0) {
                // Check, if y equation has result. If yes, then point is on triagnle. IF not, then point is not on triagnle
                if (v[numberOfVector][1] == 0) {
                    if (equationXY[1][0] - equationXY[1][1] == 0) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }

        // Same as first if, but first check y equation and then x equation
        if (v[numberOfVector][1] == 0) {
            if (equationXY[1][0] - equationXY[1][1] == 0) {
                if (v[numberOfVector][0] == 0) {
                    if (equationXY[0][0] - equationXY[0][1] == 0) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}
