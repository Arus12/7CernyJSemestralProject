/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javawork;

import java.util.Locale;
import java.util.Scanner;

/**
 * 7. Sum points in triangle, on triangle and out triangle
 *
 * @author Jan Cerny
 * @version 0.5 12/10/22
 */
public class UI {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        sc.useLocale(Locale.US);
        int choice;
        boolean end = true;
        do {
            UI.displayMenu();
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    ChristmasWork.mainStart();
                    break;
                case 2:
                    YearWork.mainStart();
                    break;
                case 0:
                    end = false;
                    break;
                default:
                    UI.displayBadChoice();
            }
        } while (end);
    }

    public static void displayMenu() {
        System.out.println("Vitejte ve vyberu projektu:");
        System.out.println("1. Vanocni projekt (Projekt s vanocnim darkem)");
        System.out.println("2. Semestralni prace");
        System.out.println("0. Konec");
    }

    /*
    -------------------------------------------------
    UI for YearWork
    -------------------------------------------------
     */
    public static void getPoints(double points[][]) {
        sc.useLocale(Locale.FRENCH);
        UI.displayPoints();
        for (int i = 0; i < points.length; i++) {
            points[i][0] = sc.nextDouble();
            points[i][1] = sc.nextDouble();
        }
    }

    public static boolean wantContinue() {
        Character response;
        do {
            UI.displayContinue();
            response = sc.next().charAt(0);
        } while (!response.equals('a') && !response.equals('A') && !response.equals('n') && !response.equals('N'));
        return (!response.equals('n') || response.equals('N'));
    }

    public static int getNumberTestPoint() {
        UI.displayNumberTestedPoints();
        int testPoint = sc.nextInt();
        while (testPoint < 1) {
            UI.displayBadNumberTestedPoints();
            testPoint = sc.nextInt();
        }
        return testPoint;
    }

    public static void getTestPoints(double testPoints[][]) {
        sc.useLocale(Locale.FRENCH);
        UI.displayTestedPoints();
        int point = 0;
        do {
            testPoints[point][0] = sc.nextDouble();
            testPoints[point][1] = sc.nextDouble();
            point++;
        } while (point != testPoints.length);
    }

    public static void displayBadChoice() {
        System.out.println("\n\nNeplatna volba");
    }

    public static void displayContinue() {
        System.out.println("Pokracovat ve zpracovani (a/n):");
    }

    public static void displayNumberTestedPoints() {
        System.out.println("Zadej pocet testovanych bodu (cele cislo):");
    }

    public static void displayBadNumberTestedPoints() {
        System.out.println("Zadejte hodnotu vetsi jak nula:");
    }

    public static void displayTestedPoints() {
        System.out.println("Zadej souradnice x y bodu (cele cislo/desetinne cislo s desetinnou carkou):");
    }

    public static void displayPoints() {
        System.out.println("Zadej souradnice x y tri vrcholu trojuhelnika (cele cislo/desetinne cislo s desetinnou carkou):");
    }

    public static void displayOutput(int inTriangle, int onTriangle, int numberTestPoints) {
        System.out.println("Uvnitr trojuhelnika lezi " + (inTriangle - onTriangle) + " bodu");
        System.out.println("Na hranici trojuhelnika lezi " + onTriangle + " bodu");
        System.out.println("Vne trojuhelnika lezi " + (numberTestPoints - (inTriangle - onTriangle) - onTriangle) + " bodu");
        System.out.printf("\n");
    }

    /*
    -------------------------------------------------
    UI for ChristmasWork
    -------------------------------------------------
     */
    public static int getSize() {
        System.out.println("Zadejte velikost vanocniho darku v rozmezi od 10-50");
        return sc.nextInt();
    }

    public static int checkSize(int size) {
        if (size < 10 || size > 50) {
            System.out.println("Zadali jste spatnou hodnotu!");
            size = getSize();
            checkSize(size);
        }
        return size;
    }

    public static int checkColor(int color) {
        if (color < 1 || color > 6) {
            System.out.println("Zadali jste spatnou hodnotu!");
            color = getIntColor();
            checkColor(color);
        }
        return color;
    }

    public static int getIntColor() {
        System.out.println("Vyberte barvu stuhy na darek:");
        System.out.println("1. \u001B[34mModra\u001B[30m");
        System.out.println("2. \u001B[31mCervena\u001B[30m");
        System.out.println("3. \u001B[32mZelena\u001B[30m");
        System.out.println("4. \u001B[33mZluta\u001B[30m");
        System.out.println("5. \u001B[35mFialova\u001B[30m");
        System.out.println("6. \u001B[36mTyrkysova\u001B[30m");
        return sc.nextInt();
    }

    public static String getColor(int color) {
        String Scolor = "";
        switch (color) {
            case 1:
                Scolor = "\u001B[34m";
                break;
            case 2:
                Scolor = "\u001B[31m";
                break;
            case 3:
                Scolor = "\u001B[32m";
                break;
            case 4:
                Scolor = "\u001B[33m";
                break;
            case 5:
                Scolor = "\u001B[35m";
                break;
            case 6:
                Scolor = "\u001B[36m";
                break;
        }
        return Scolor;
    }
}
