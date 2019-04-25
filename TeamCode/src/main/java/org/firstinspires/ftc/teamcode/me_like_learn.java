package org.firstinspires.ftc.teamcode;

public class me_like_learn {

    static int exampleInteger = 42;
    static double exampleDouble = 3.1415;
    static boolean exampleBoolean = true;

    static int counter = 0;

    public static void main(String[] args) {
        exampleInteger = 21;

        exampleBoolean = false;

        exampleDouble = exampleInteger;

        if (exampleInteger == 66) {
            System.out.println(exampleDouble);
        } else {
            System.out.println("Nope.");
        }

        while (counter < 23) {
            counter = counter + 1;
            System.out.println(counter);
        }

        testParameters(5, 6);
        testParameters(6, 1);
        testParameters(50, 76);
        testParameters(-97, 4);
        testParameters(54, 2);
    }

    public static void exampleMethod() {
        System.out.println("test.");
        System.out.println("forty");
        System.out.println("no");
        System.out.println("robot");
        System.out.println("royal blue OP");
    }

    public static void testParameters(double test, double test2) {
        test = test + test2;
        test = test - test2;
        test = test * test2;
        test = test / test2;

        System.out.println(test);
        System.out.println(test2);
    }
}