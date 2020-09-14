package com.AdamT; //Package name root
import java.util.Scanner; //For the Scanner class

/**
 * This program is a collection of methods which I have created as a part of my practicing Java for the OCR A-Level Computer Science course.<br><br>
 *
 * Changelog:<br>
 * 0.1 @ 08/09/2020: Initial release; only method compares 2 numbers and returns which one is larger.<br>
 * 0.1.1 @ 09/09/2020: Added validation for user inputs; allows user to input 2 numbers which are equal.<br>
 * 0.1.2 @ 10/09/2020: Reorganizes methods; actually uses the java.lang.Math class.<br>
 * 0.2 @ 11/09/2020: Removed the java.lang.math class as it caused a bug in NumberComparer; created a menu for use when there are more modules of actions within this program.<br>
 * 0.2.0.1 @ 12/09/2020: Made sca1 a private and constant class variable; made the non-main methods (isNumeric and NumberComparer) private methods.<br><br>
 * 0.2.0.2 @ 14/09/2020:
 *
 * @author Adam Tazul
 * @version 0.2.0.1
 * @since 07/09/2020
 */

public class CompSci //The main class
{
    private final static Scanner sca1 = new Scanner(System.in); //Sets up keyboard input to console

    public static void main(String[] args)
    {
        boolean exit = false;
        while (true)
        {
            if (exit)
            {
                break;
            }
            System.out.println("\nWhat would you like to do with this program?\nYou can choose from:\n1) NumberComparer\n2) Exit");
            System.out.print("Enter selection:");
            String str1 = sca1.nextLine();

            while (true) //validation
            {
                if (!isNumeric(str1))
                {
                    System.out.print("\nInvalid input detected!\nPlease input a valid number:");
                    str1 = sca1.nextLine();
                    System.out.println();
                }
                if (str1.equals("1"))
                {
                    System.out.println("\n"+NumberComparer());
                    System.out.println();
                    break;
                }
                else if (str1.equals("2"))
                {
                    System.out.println("\nSee yah!");
                    sca1.close();
                    exit = true;
                    break;
                }
                else
                {
                    System.out.print("\nInvalid input detected!\nPlease input a valid number:");
                    str1 = sca1.nextLine();
                    System.out.println();
                }
            }
        }
    }

    private static boolean isNumeric(String str) //Subroutine to check if the user inputs a valid number
    {
        try
        {
            Double.parseDouble(str);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }

    private static String NumberComparer() //Main method
    {
        System.out.print("Enter a random number:"); //Asks for input
        String str1 = sca1.nextLine(); //Takes input

        while (true) //validation
        {
            if (isNumeric(str1))
            {
                break;
            }
            else
            {
                System.out.print("\nInvalid input detected!\nPlease input a valid number:");
                str1 = sca1.nextLine();
                System.out.println();
            }
        }

        System.out.print("\nEnter a random number:"); //Asks for input
        String str2 = sca1.nextLine(); //Takes input

        while (true) //validation
        {
            if (isNumeric(str2))
            {
                break;
            }
            else
            {
                System.out.print("\nInvalid input detected!\nPlease input a valid number:");
                str2 = sca1.nextLine();
                System.out.println();
            }
        }

        double num1 = Double.parseDouble(str1);
        double num2 = Double.parseDouble(str2);

        if (num1 > num2)
        {
            return("\n"+num1+" is bigger than "+num2+".");
        }
        else if (num2 > num1)
        {
             return("\n"+num2+" is bigger than "+num1+".");
        }
        else
        {
            return("\nThe two numbers ("+num1+") are equal.");
        }
    }
}