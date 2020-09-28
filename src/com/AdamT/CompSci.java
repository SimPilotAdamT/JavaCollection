package com.AdamT; //Package name root
import java.util.*; //For the Scanner class
import java.lang.*; //For the Math class
import javax.swing.*; //This and the next one is for everything needed in relation to JFrame
import java.awt.*;

/**
 * This program is a collection of methods which I have created as a part of my practicing Java for the OCR A-Level Computer Science course.<br><br>
 *
 * Changelog:<br>
 * 0.1 @ 08/09/2020: Initial release; only method compares 2 numbers and returns which one is larger.<br>
 * 0.1.1 @ 09/09/2020: Added validation for user inputs; allows user to input 2 numbers which are equal.<br>
 * 0.1.2 @ 10/09/2020: Reorganizes methods; actually uses the java.lang.Math class.<br>
 * 0.2 @ 11/09/2020: Removed the java.lang.math class as it caused a bug in NumberComparer; created a menu for use when there are more modules of actions within this program.<br>
 * 0.2.0.1 @ 12/09/2020: Made sca1 a private and constant class variable; made the non-main methods (isNumeric and NumberComparer) private methods.<br>
 * 0.2.0.2 @ 14/09/2020: Made programming a slight bit more time efficient.<br>
 * 0.3a1 @ 21/09/2020: Added some GUI Programming; Added the Oracle SpringUtilities class to the file (for the form window).<br>
 * 0.3 @ 28/09/2020: Abandoned NumberComparerGUI, added simple Rock Paper Scissors game.<br><br>
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
            System.out.println("\nWhat would you like to do with this program?\nYou can choose from:\n1) NumberComparer (Command Line)\n2) Simple GUI\n3) Rock Paper Scissors\n4) Exit");
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
                    System.out.println("\n"+NumberComparer()+"\n");
                    break;
                }
                else if (str1.equals("2"))
                {
                    SimpleGUI();
                    break;
                }
                else if (str1.equals("3"))
                {
                    System.out.println();
                    RockPaperScissors();
                    break;
                }
                else if (str1.equals("4"))
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
            if (isNumeric(str2)){
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

    private static void SimpleGUI()
    {
        JFrame Frame = new JFrame("NumberComparer GUI");
        Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JButton button,button1, button2, button3,button4;
        button = new JButton("left");
        button1 = new JButton("right");
        button2 = new JButton("top");
        button3 = new JButton("bottom");
        button4 = new JButton("center");
        Frame.add(button,BorderLayout.WEST);
        Frame.add(button1, BorderLayout.EAST);
        Frame.add(button2, BorderLayout.NORTH);
        Frame.add(button3, BorderLayout.SOUTH);
        Frame.add(button4, BorderLayout.CENTER);
        Frame.setSize(1024,768);
        Frame.setVisible(true);
    }

    private static void RockPaperScissors()
    {
        Random Choice = new Random();
        int CompChoice = Choice.nextInt(((3-1)+1)+1);
        String Choice1;
        if (CompChoice == 1)
        {
            Choice1 = "Rock";
        }
        else if (CompChoice == 2)
        {
            Choice1 = "Paper";
        }
        else
        {
            Choice1 = "Scissors";
        }
        System.out.print("Please input your Choice of Rock, Paper, or Scissors:");
        String UsrInput = sca1.nextLine();
        System.out.println();
        boolean valid = false;
        while (!valid)
        {
            if (UsrInput.equalsIgnoreCase("Rock") || UsrInput.equalsIgnoreCase("Paper") || UsrInput.equalsIgnoreCase("Scissors"))
            {
                valid = true;
            }
            else
            {
                System.out.println("Error! Your input was not one of the three options!");
                System.out.print("Please input your Choice of Rock, Paper, or Scissors:");
                UsrInput = sca1.nextLine();
                System.out.println();
            }
        }
        if (UsrInput.equalsIgnoreCase(Choice1))
        {
            System.out.println("It's a draw!");
        }
        else if ((UsrInput.equalsIgnoreCase("Rock") && Choice1.equals("Paper")) || (UsrInput.equalsIgnoreCase("Paper") && Choice1.equals("Scissors")) || (UsrInput.equalsIgnoreCase("Scissors") && Choice1.equals("Rock")))
        {
            System.out.println("You lose! Better luck next time!");
        }
        else
        {
            System.out.println("You win!");
        }
    }
}