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
    }
}
