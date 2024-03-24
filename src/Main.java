import java.util.Scanner;

/**
 * Marked Assignment 3 - Mathematic Methods
 * 1.
 * 2.
 * 3.
 * 4.
 * 5.
 * 6.
 * 7.
 * @author Jonas Wennberg (wenjon-3)
 */
public class Main {
    //Creation of scanner object.
    private static Scanner userInputScanner = new Scanner(System.in);

    //Constants
    static final int QUIT = -1;
    static final double EXPONENT = 2;
    static final int DENOMINATOR = 3;
    static final int ZERO = 0;

    /**
     * This method should be used only for unit testing on CodeGrade. Do not change this method!
     * Do not remove this method!
     * Swaps userInputScanner with a custom scanner object bound to a test input stream
     *
     * @param inputScanner - test scanner object
     */
    public static void injectInput(final Scanner inputScanner) {
        userInputScanner = inputScanner;
    }

    /**
     * Main method to run the program
     *
     * @param args - Takes optional parameters from user when running app
     */
    public static void main(final String[] args) {
        int radius = 0;
        int height = 0;
        int numerator = 0;
        int denominator = 0;

        //Print the header of the program for area and volume.
        System.out.println("----------------------------------");
        System.out.println("# Test of area and volume methods");
        System.out.println("----------------------------------");

        // While loop that runs until user enters "q" for area and volume.

        while (true) {

            radius = input();
            if (radius == QUIT) {
                break;
            }

            height = input();
            if (height == QUIT) {
                break;
            }

            System.out.println("r = " + radius + ", h = " + height);
            System.out.printf("Circle area: %.2f %n", area(radius));
            System.out.printf("Cone area: %.2f %n", area(radius, height));
            System.out.printf("Cone volume: %.2f %n", volume(radius, height));
        }

        //Print the header of the program for area and volume.
        System.out.println("----------------------------------");
        System.out.println("# Test of the fractional methods");
        System.out.println("----------------------------------");


        // While loop that runs until user enters "q" for the fraction part
        while (true) {

            numerator = input();
            if (numerator == QUIT) {
                break;
            }

            denominator = input();
            if (denominator == QUIT) {
                break;
            }

            System.out.printf("%d/%d = ", numerator, denominator);
            printFraction(fraction(numerator, denominator));
        }
    }
    /**
     * Method calculating Area of a circle
     * @param radius
     * @return area
     */
    public static double area(final int radius) {
        double area = 0.0;

        // Calculate the area of a circle with PI, radius in exponent of 2 (
        area = Math.PI * (Math.pow(radius, EXPONENT));
        return area;
    }
    /**
     * Method calculating Area of a cone
     * @param radius - Radius of the surface of the cone
     * @param height - Height of the cone
     * @return area
     */
    public static double area(final int radius, final int height) {
        double area = 0.0;
        double s = 0.0;

        // Calculate the area of a cone with PI, radius and s from pythagoras
        s = pythagoras(radius, height);
        area = Math.PI * radius * s;
        return area;
    }
    /**
     * Method calculating the hypotenuse using pythagoras theorem
     * @param sideA - Side A of the triangle
     * @param sideB - Side B of the triangle
     * @return s
     */
    public static double pythagoras(final int sideA, final int sideB) {
        double c = 0.0;

        // Make sure the sides are positive

        // Calculate hypotenuse a2 + b2 = c2
        c = (Math.pow(sideA, EXPONENT) + Math.pow(sideB, EXPONENT));
        c = Math.sqrt(c);
        return c;
    }
    /**
     * Method calculating the volume
     * @param radius - Radius of the surface of the cone
     * @param height - Height of the cone
     * @return volume
     */
    public static double volume(final int radius, final int height) {
        double volume = 0.0;

        // Make sure radius and height are positive numbers

        // Calculate volume
        volume = (Math.PI * Math.pow(radius, EXPONENT) * height) / DENOMINATOR;
        return volume;
    }
    /**
     * Method for calculating fraction
     * @param numerator - Numerator of the equation
     * @param denominator - Denominator of the equation
     * @return int[] - Returning an integer array of size 3
     */
    public static int[] fraction(final int numerator, final int denominator) {
        int num = 0;
        int denom = 0;
        int integer = 0;

        if(denominator == ZERO) {
            return null;
        } else if(numerator == ZERO) {
            return new int[]{ZERO, ZERO, ZERO};
        }
        integer = numerator / denominator;
        num = numerator % denominator;
        denom = denominator;

        // Call gcd to shorten fraction
        int gcd = gcd(numerator, denominator);
        num = num / gcd;
        denom = denom / gcd;

        return new int[]{integer, num, denom};
    }

    /**
     * Method for greatest common divider (Euclides algorithm)
     * @param a - Integer of the numerator
     * @param b - Integer of the denominator
     * @return internalA
     */
    public static int gcd(final int a, final int b) {

        int internalA = a;
        int internalB = b;
        int internalC = 0;

        // Swap if B is greater than A
        if (internalB > internalA) {
            internalC = internalA;
            internalA = internalB;
            internalB = internalC;
        }

        // Find the gcd
        while(internalB != ZERO) {
            internalC = internalA % internalB;
            internalA = internalB;
            internalB = internalC;
        }

        return internalA;
    }
    /**
     * Method for printing the fraction
     * @param parts - Integer array with the 3 parts to print
     */
    public static void printFraction(final int[] parts) {
        if(parts == null) {
            System.out.println("Error");
        } else if(parts[2] == ZERO) {
            System.out.printf("%d%n", ZERO);
        } else if(parts[1] == ZERO) {
            System.out.printf("%d%n", parts[0]);
        } else if(parts[0] == ZERO) {
            System.out.printf("%d/%d%n", parts[1], parts[2]);
        } else {
            System.out.printf("%d %d/%d%n", parts[0], parts[1], parts[2]);
        }
    }
    public static int input() {
        int userInput = 0;

        while(true) {
            if(userInputScanner.hasNextInt()) {
                userInput = userInputScanner.nextInt();
                if(userInput >= ZERO) {
                    break;
                } else {
                    userInput = Math.abs(userInput);
                    break;
                }
            } else if(userInputScanner.hasNext()) {
                String inString = userInputScanner.next();
                if (inString.equalsIgnoreCase("q")) {
                    userInput = -1;
                    break;
                }
            }
        }
        return userInput;
    }


}
