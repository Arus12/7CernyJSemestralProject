/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javawork;

/**
 * ChristmasWork - Output of this work is christmas present with collored ribbon
 *
 * @author Jan Cerny
 * @version 0.4
 */
public class ChristmasWork {

    static String resetColor = "\u001B[30m";

    public static void main() {
        int color;
        int size;
        size = UI.getSize();
        size = UI.checkSize(size);
        color = UI.getIntColor();
        color = UI.checkColor(color);
        String sColor = UI.getColor(color);
        Present(size, sColor);
    }

    /**
     * @param size
     * @param sColor
     */
    public static void Present(int size, String sColor) {
        int rowRibbon = (size / 2) - 3;
        int body = (size / 2) + 1;

        String present = presentRibbon(size, 4, rowRibbon, sColor);

        //Get body of present
        for (int i = 1; i != body + 1; i++) {
            if (i == 1) {
                for (int y = 0; y != size; y++) {
                    present += "-";
                }
                present += "\n";
            } else if (i == body) {
                for (int y = 0; y != size; y++) {
                    present += "-";
                }
                present += "\n";
            }

            if (i == (body / 2) + 1 || body % 2 == 0 && i == body / 2) {
                for (int y = 1; y != size + 1; y++) {
                    if (y == 1 || y == size) {
                        present += "|";
                    } else if (y == size / 2 && size % 2 == 0 || y == (size / 2) + 1) {
                        present += sColor + "|" + resetColor;
                    } else {
                        present += sColor + "~" + resetColor;
                    }
                }
                present += "\n";
            } else if (i != 2 && i != body) {
                for (int y = 1; y != size + 1; y++) {
                    if (y == 1 || y == size) {
                        present += "|";
                    } else if (y == size / 2 && size % 2 == 0 || y == (size / 2) + 1) {
                        present += sColor + "|" + resetColor;
                    } else {
                        present += "*";
                    }
                }
                present += "\n";
            }
        }

        System.out.printf(present);
    }

    /**
     *
     * @param size
     * @param spacesStart
     * @param rowRibbon
     * @param sColor
     * @return ribbon of present
     */
    private static String presentRibbon(int size, int spacesStart, int rowRibbon, String sColor) {
        String ribbon = "";
        int spacesMiddle = (size - 10) + 2;
        for (int i = 1; i != rowRibbon + 1; i++) {
            for (int y = 1; y != 2 + spacesMiddle + spacesStart; y++) {
                if (y == spacesStart) {
                    ribbon += (sColor + "\\" + resetColor);
                } else if (y == spacesMiddle + spacesStart + 1) {
                    ribbon += (sColor + "/" + resetColor);
                } else {
                    ribbon += (" ");
                }
            }
            ribbon += "\n";
            spacesMiddle = spacesMiddle - 2;
            spacesStart++;
        }

        return ribbon;
    }
}
