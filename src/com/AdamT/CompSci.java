package com.AdamT; //Package name root
import java.util.*; //For the Scanner class
import javax.swing.*; //This and the next one is for everything needed in relation to JFrame
import java.awt.*;

/**
 * A 1.4 file that provides utility methods for
 * creating form- or grid-style layouts with SpringLayout.
 * These utilities are used by several programs, such as
 * SpringBox and SpringCompactGrid.
 */
class SpringUtilities {
    /**
     * A debugging utility that prints to stdout the component's
     * minimum, preferred, and maximum sizes.
     */
    public static void printSizes(Component c) {
        System.out.println("minimumSize = " + c.getMinimumSize());
        System.out.println("preferredSize = " + c.getPreferredSize());
        System.out.println("maximumSize = " + c.getMaximumSize());
    }

    /**
     * Aligns the first <code>rows</code> * <code>cols</code>
     * components of <code>parent</code> in
     * a grid. Each component is as big as the maximum
     * preferred width and height of the components.
     * The parent is made just big enough to fit them all.
     *
     * @param rows number of rows
     * @param cols number of columns
     * @param initialX x location to start the grid at
     * @param initialY y location to start the grid at
     * @param xPad x padding between cells
     * @param yPad y padding between cells
     */
    public static void makeGrid(Container parent,
                                int rows, int cols,
                                int initialX, int initialY,
                                int xPad, int yPad) {
        SpringLayout layout;
        try {
            layout = (SpringLayout)parent.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }

        Spring xPadSpring = Spring.constant(xPad);
        Spring yPadSpring = Spring.constant(yPad);
        Spring initialXSpring = Spring.constant(initialX);
        Spring initialYSpring = Spring.constant(initialY);
        int max = rows * cols;

        //Calculate Springs that are the max of the width/height so that all
        //cells have the same size.
        Spring maxWidthSpring = layout.getConstraints(parent.getComponent(0)).
                getWidth();
        Spring maxHeightSpring = layout.getConstraints(parent.getComponent(0)).
                getHeight();
        for (int i = 1; i < max; i++) {
            SpringLayout.Constraints cons = layout.getConstraints(
                    parent.getComponent(i));

            maxWidthSpring = Spring.max(maxWidthSpring, cons.getWidth());
            maxHeightSpring = Spring.max(maxHeightSpring, cons.getHeight());
        }

        //Apply the new width/height Spring. This forces all the
        //components to have the same size.
        for (int i = 0; i < max; i++) {
            SpringLayout.Constraints cons = layout.getConstraints(
                    parent.getComponent(i));

            cons.setWidth(maxWidthSpring);
            cons.setHeight(maxHeightSpring);
        }

        //Then adjust the x/y constraints of all the cells so that they
        //are aligned in a grid.
        SpringLayout.Constraints lastCons = null;
        SpringLayout.Constraints lastRowCons = null;
        for (int i = 0; i < max; i++) {
            SpringLayout.Constraints cons = layout.getConstraints(
                    parent.getComponent(i));
            if (i % cols == 0) { //start of new row
                lastRowCons = lastCons;
                cons.setX(initialXSpring);
            } else { //x position depends on previous component
                cons.setX(Spring.sum(lastCons.getConstraint(SpringLayout.EAST),
                        xPadSpring));
            }

            if (i / cols == 0) { //first row
                cons.setY(initialYSpring);
            } else { //y position depends on previous row
                cons.setY(Spring.sum(lastRowCons.getConstraint(SpringLayout.SOUTH),
                        yPadSpring));
            }
            lastCons = cons;
        }

        //Set the parent's size.
        SpringLayout.Constraints pCons = layout.getConstraints(parent);
        pCons.setConstraint(SpringLayout.SOUTH,
                Spring.sum(
                        Spring.constant(yPad),
                        lastCons.getConstraint(SpringLayout.SOUTH)));
        pCons.setConstraint(SpringLayout.EAST,
                Spring.sum(
                        Spring.constant(xPad),
                        lastCons.getConstraint(SpringLayout.EAST)));
    }

    /* Used by makeCompactGrid. */
    private static SpringLayout.Constraints getConstraintsForCell(
            int row, int col,
            Container parent,
            int cols) {
        SpringLayout layout = (SpringLayout) parent.getLayout();
        Component c = parent.getComponent(row * cols + col);
        return layout.getConstraints(c);
    }

    /**
     * Aligns the first <code>rows</code> * <code>cols</code>
     * components of <code>parent</code> in
     * a grid. Each component in a column is as wide as the maximum
     * preferred width of the components in that column;
     * height is similarly determined for each row.
     * The parent is made just big enough to fit them all.
     *
     * @param rows number of rows
     * @param cols number of columns
     * @param initialX x location to start the grid at
     * @param initialY y location to start the grid at
     * @param xPad x padding between cells
     * @param yPad y padding between cells
     */
    public static void makeCompactGrid(Container parent,
                                       int rows, int cols,
                                       int initialX, int initialY,
                                       int xPad, int yPad) {
        SpringLayout layout;
        try {
            layout = (SpringLayout)parent.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeCompactGrid must use SpringLayout.");
            return;
        }

        //Align all cells in each column and make them the same width.
        Spring x = Spring.constant(initialX);
        for (int c = 0; c < cols; c++) {
            Spring width = Spring.constant(0);
            for (int r = 0; r < rows; r++) {
                width = Spring.max(width,
                        getConstraintsForCell(r, c, parent, cols).
                                getWidth());
            }
            for (int r = 0; r < rows; r++) {
                SpringLayout.Constraints constraints =
                        getConstraintsForCell(r, c, parent, cols);
                constraints.setX(x);
                constraints.setWidth(width);
            }
            x = Spring.sum(x, Spring.sum(width, Spring.constant(xPad)));
        }

        //Align all cells in each row and make them the same height.
        Spring y = Spring.constant(initialY);
        for (int r = 0; r < rows; r++) {
            Spring height = Spring.constant(0);
            for (int c = 0; c < cols; c++) {
                height = Spring.max(height,
                        getConstraintsForCell(r, c, parent, cols).
                                getHeight());
            }
            for (int c = 0; c < cols; c++) {
                SpringLayout.Constraints constraints =
                        getConstraintsForCell(r, c, parent, cols);
                constraints.setY(y);
                constraints.setHeight(height);
            }
            y = Spring.sum(y, Spring.sum(height, Spring.constant(yPad)));
        }

        //Set the parent's size.
        SpringLayout.Constraints pCons = layout.getConstraints(parent);
        pCons.setConstraint(SpringLayout.SOUTH, y);
        pCons.setConstraint(SpringLayout.EAST, x);
    }
}

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
 * 0.3a1 @ 21/09/2020: Added some GUI Programming; Added the Oracle SpringUtilities class to the file (for the form window).<br><br>
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
            System.out.println("\nWhat would you like to do with this program?\nYou can choose from:\n1) NumberComparer (Command Line)\n2) Simple GUI\n3) NumberComparerGUI\n4) Exit");
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
                    System.out.println("\n"+SimpleGUI()+"\n");
                    break;
                }
                else if (str1.equals("3"))
                {
                    NumberComparerGUI();
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

    private static String SimpleGUI()
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
        return("");
    }

    private static void NumberComparerGUI()
    {
        String[] Labels = {"First Number","Second Number"};
        int NumPairs = Labels.length;
        JPanel Panel = new JPanel (new SpringLayout());
        for (int i = 0; i < NumPairs; i++)
        {
            JLabel Label = new JLabel(Labels[i]);
            Panel.add(Label);
            JTextField textField = new JTextField(10);
            Label.setLabelFor(textField);
            Panel.add(textField);
        }
        SpringUtilities.makeCompactGrid(Panel, NumPairs, 2, 6, 6, 6, 6);
        JFrame Frame = new JFrame("NumberComparer GUI");
        Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Frame.setSize(1024,768);
        Frame.add(Panel);
        Frame.pack();
        Frame.setVisible(true);
    }
}