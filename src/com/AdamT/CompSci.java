package com.AdamT;
import java.util.*;
import java.lang.*;
import javax.swing.*;
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
 * 0.3 @ 28/09/2020: Abandoned NumberComparerGUI; added simple Rock Paper Scissors game.<br>
 * 0.4 @ 13/10/2020: Added a currency converter (rates accurate as of 1400Z 12/10/2020).<br>
 * 0.4.0.1 @ 17/10/2020: Edited grammar.<br><br>
 *
 * @author Adam Tazul
 * @version 0.4.0.1
 * @since 07/09/2020
 */
public class CompSci
{
    private final static Scanner sca1 = new Scanner(System.in);

    public static void main(String[] args)
    {
        boolean exit = false;
        while (true)
        {
            if (exit) break;
            System.out.println("\nWhat would you like to do with this program?\nYou can choose from:\n1) NumberComparer (Command Line)\n2) Simple GUI\n3) Rock Paper Scissors\n4) Countdown of multiples for 3 from 300 to 3\n5) Currency Converter\n6) Exit");
            System.out.print("Enter selection:");
            String str1 = sca1.nextLine();
            switch (str1)
            {
                case "1" -> System.out.println("\n" + NumberComparer() + "\n");
                case "2" -> SimpleGUI();
                case "3" ->
                {
                    System.out.println();
                    RockPaperScissors();
                    System.out.println();
                }
                case "4" -> ThreeHundredToThree();
                case "5" -> CurrencyConverter();
                case "6" ->
                {
                    System.out.println("\nSee yah!");
                    sca1.close();
                    exit = true;
                }
                default -> System.out.println("\nInvalid input detected!\n");
            }
        }
    }

    private static boolean isNumeric(String str)
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

    private static String NumberComparer()
    {
        System.out.print("Enter a random number:");
        String str1 = sca1.nextLine();

        while (true)
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

        System.out.print("\nEnter a random number:");
        String str2 = sca1.nextLine();

        while (true)
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

    private static void ThreeHundredToThree()
    {
        for(int i=300;i>0;i-=3)
        {
            System.out.println(i);
        }
    }

    private static void CurrencyConverter()
    {
        System.out.println("Welcome to Adam's currency converter!");
        boolean exit = false;
        while (!exit)
        {
            System.out.println("Which currency would you like to convert to (from GBP)?\nNOTE: rates accurate as of 1400Z 12/10/2020.");
            System.out.println("1) USD \n2) AUD \n3) EUR \n4) TRY \n5) CHF \n6) BTC \n7) ETH \n8) руб \n9) SAR \n10) QAR\n11) MYR \n12) KGS \n13) Exit Program");
            System.out.print("Enter selection:");
            String str1 = sca1.nextLine();
            switch (str1)
            {
                case "1" -> System.out.println("\n"+USD()+"\n");
                case "2" -> System.out.println("\n"+AUD()+"\n");
                case "3" -> System.out.println("\n"+EUR()+"\n");
                case "4" -> System.out.println("\n"+TRY()+"\n");
                case "5" -> System.out.println("\n"+CHF()+"\n");
                case "6" -> System.out.println("\n"+BTC()+"\n");
                case "7" -> System.out.println("\n"+ETH()+"\n");
                case "8" -> System.out.println("\n"+руб()+"\n");
                case "9" -> System.out.println("\n"+SAR()+"\n");
                case "10" -> System.out.println("\n"+QAR()+"\n");
                case "11" -> System.out.println("\n"+MYR()+"\n");
                case "12" -> System.out.println("\n"+KGS()+"\n");
                case "13" ->
                        {
                            System.out.println("\nSee yah!");
                            exit = true;
                        }
                default -> System.out.println("\nInvalid input detected!\n");
            }
        }
    }

    private static String USD()
    {
        System.out.println("How much money do you want to convert?");
        System.out.print("Enter selection:");
        String str1 = sca1.nextLine();
        boolean valid = false;
        while (!valid)
        {
            if (isNumeric(str1))
            {
                valid = true;
            }
            else
            {
                System.out.println("\nError! Value is not a number.");
                System.out.println("How much money do you want to convert?");
                System.out.print("Enter selection:");
                str1 = sca1.nextLine();
            }
        }
        double num1 = Double.parseDouble(str1);
        return("\nThat's $"+(num1*1.3));
    }

    private static String AUD()
    {
        System.out.println("How much money do you want to convert?");
        System.out.print("Enter selection:");
        String str1 = sca1.nextLine();
        boolean valid = false;
        while (!valid)
        {
            if (isNumeric(str1))
            {
                valid = true;
            }
            else
            {
                System.out.println("\nError! Value is not a number.");
                System.out.println("How much money do you want to convert?");
                System.out.print("Enter selection:");
                str1 = sca1.nextLine();
            }
        }
        double num1 = Double.parseDouble(str1);
        return("\nThat's $"+(num1*1.81));
    }

    private static String EUR()
    {
        System.out.println("How much money do you want to convert?");
        System.out.print("Enter selection:");
        String str1 = sca1.nextLine();
        boolean valid = false;
        while (!valid)
        {
            if (isNumeric(str1))
            {
                valid = true;
            }
            else
            {
                System.out.println("\nError! Value is not a number.");
                System.out.println("How much money do you want to convert?");
                System.out.print("Enter selection:");
                str1 = sca1.nextLine();
            }
        }
        double num1 = Double.parseDouble(str1);
        return("\nThat's €"+(num1*1.1));
    }

    private static String TRY()
    {
        System.out.println("How much money do you want to convert?");
        System.out.print("Enter selection:");
        String str1 = sca1.nextLine();
        boolean valid = false;
        while (!valid)
        {
            if (isNumeric(str1))
            {
                valid = true;
            }
            else
            {
                System.out.println("\nError! Value is not a number.");
                System.out.println("How much money do you want to convert?");
                System.out.print("Enter selection:");
                str1 = sca1.nextLine();
            }
        }
        double num1 = Double.parseDouble(str1);
        return("\nThat's ₺"+(num1*10.27));
    }

    private static String CHF()
    {
        System.out.println("How much money do you want to convert?");
        System.out.print("Enter selection:");
        String str1 = sca1.nextLine();
        boolean valid = false;
        while (!valid)
        {
            if (isNumeric(str1))
            {
                valid = true;
            }
            else
            {
                System.out.println("\nError! Value is not a number.");
                System.out.println("How much money do you want to convert?");
                System.out.print("Enter selection:");
                str1 = sca1.nextLine();
            }
        }
        double num1 = Double.parseDouble(str1);
        return("\nThat's FR"+(num1*1.19));
    }

    private static String BTC()
    {
        System.out.println("How much money do you want to convert?");
        System.out.print("Enter selection:");
        String str1 = sca1.nextLine();
        boolean valid = false;
        while (!valid)
        {
            if (isNumeric(str1))
            {
                valid = true;
            }
            else
            {
                System.out.println("\nError! Value is not a number.");
                System.out.println("How much money do you want to convert?");
                System.out.print("Enter selection:");
                str1 = sca1.nextLine();
            }
        }
        double num1 = Double.parseDouble(str1);
        return("\nThat's ฿"+(num1*0.00011));
    }

    private static String ETH()
    {
        System.out.println("How much money do you want to convert?");
        System.out.print("Enter selection:");
        String str1 = sca1.nextLine();
        boolean valid = false;
        while (!valid)
        {
            if (isNumeric(str1))
            {
                valid = true;
            }
            else
            {
                System.out.println("\nError! Value is not a number.");
                System.out.println("How much money do you want to convert?");
                System.out.print("Enter selection:");
                str1 = sca1.nextLine();
            }
        }
        double num1 = Double.parseDouble(str1);
        return("\nThat's Ξ"+(num1*0.0034));
    }

    private static String руб()
    {
        System.out.println("How much money do you want to convert?");
        System.out.print("Enter selection:");
        String str1 = sca1.nextLine();
        boolean valid = false;
        while (!valid)
        {
            if (isNumeric(str1))
            {
                valid = true;
            }
            else
            {
                System.out.println("\nError! Value is not a number.");
                System.out.println("How much money do you want to convert?");
                System.out.print("Enter selection:");
                str1 = sca1.nextLine();
            }
        }
        double num1 = Double.parseDouble(str1);
        return("\nThat's ₽"+(num1*100.6));
    }

    private static String SAR()
    {
        System.out.println("How much money do you want to convert?");
        System.out.print("Enter selection:");
        String str1 = sca1.nextLine();
        boolean valid = false;
        while (!valid)
        {
            if (isNumeric(str1))
            {
                valid = true;
            }
            else
            {
                System.out.println("\nError! Value is not a number.");
                System.out.println("How much money do you want to convert?");
                System.out.print("Enter selection:");
                str1 = sca1.nextLine();
            }
        }
        double num1 = Double.parseDouble(str1);
        return("\nThat's SR"+(num1*4.89));
    }

    private static String QAR()
    {
        System.out.println("How much money do you want to convert?");
        System.out.print("Enter selection:");
        String str1 = sca1.nextLine();
        boolean valid = false;
        while (!valid)
        {
            if (isNumeric(str1))
            {
                valid = true;
            }
            else
            {
                System.out.println("\nError! Value is not a number.");
                System.out.println("How much money do you want to convert?");
                System.out.print("Enter selection:");
                str1 = sca1.nextLine();
            }
        }
        double num1 = Double.parseDouble(str1);
        return("\nThat's QR"+(num1*4.75));
    }

    private static String MYR()
    {
        System.out.println("How much money do you want to convert?");
        System.out.print("Enter selection:");
        String str1 = sca1.nextLine();
        boolean valid = false;
        while (!valid)
        {
            if (isNumeric(str1))
            {
                valid = true;
            }
            else
            {
                System.out.println("\nError! Value is not a number.");
                System.out.println("How much money do you want to convert?");
                System.out.print("Enter selection:");
                str1 = sca1.nextLine();
            }
        }
        double num1 = Double.parseDouble(str1);
        return("\nThat's RM"+(num1*5.35));
    }

    private static String KGS()
    {
        System.out.println("How much money do you want to convert?");
        System.out.print("Enter selection:");
        String str1 = sca1.nextLine();
        boolean valid = false;
        while (!valid)
        {
            if (isNumeric(str1))
            {
                valid = true;
            }
            else
            {
                System.out.println("\nError! Value is not a number.");
                System.out.println("How much money do you want to convert?");
                System.out.print("Enter selection:");
                str1 = sca1.nextLine();
            }
        }
        double num1 = Double.parseDouble(str1);
        return("\nThat's Лв"+(num1*102.97));
    }
}