/**
 * @author Avik Kadakia
 * email:avik.kadakia@stonybrook.edu
 * Stony Brook ID: 111304945
 *
 * Class: CSE 214.02
 * Recitation: CSE 214 - R.14
 *
 * The class creates a <code>StormStatServer</code> which will serve as the main for the project.
 */

import java.io.*;
import java.util.*;

public class StormStatServer implements Serializable
{
    private static HashMap<String, Storm> database = new HashMap<>(); // HashMap of Storms
    private static Scanner sc = new Scanner(System.in); // Scanner
    private static boolean quitMenu = false; // determines if the code should keep running

    final static String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * Main method for the project.
     */
    public static void main(String[] args)
    {
        readFile();

        while(!quitMenu)
        {
            menu();
        }
    }

    /**
     * Prints the menu.
     */
    public static void menu()
    {
        System.out.println("\nMenu:\n" +
                "\tA) Add A Storm\n" +
                "\tL) Look Up A Storm\n" +
                "\tD) Delete A Storm\n" +
                "\tE) Edit Storm Data\n" +
                "\tR) Print Storms Sorted By Rainfall\n" +
                "\tW) W-Print Storms by Windspeed\n" +
                "\tX) Save and quit\n" +
                "\tQ) Quit and delete saved data\n");

        runMenu();
    }

    /**
     * Runs the main.
     */
    public static void runMenu()
    {
        System.out.print("\nPlease select an option: ");
        char letter = ((sc.next()).toUpperCase()).charAt(0);

        switch (letter)
        {
            case ('A'): addStorm();
                break;

            case ('L'): searchStorm();
                break;

            case ('D'): deleteStorm();
                break;

            case ('E'): editStorm();
                break;

            case ('R'): printStormByRainfall();
                break;

            case ('W'): printStormByWindspeed();
                break;

            case ('X'): saveAndQuit();
                break;

            case ('Q'): quitAndDelete();
                break;

            default: System.out.println("The option selected is incorrect. Please try again."); runMenu();
            break;
        }
    }

    /**
     * Adds a storm to the HashMap.
     */
    public static void addStorm()
    {
        System.out.print("Please enter name: ");
        String name = (sc.next()).toUpperCase();

        System.out.print("Please enter date (YYYY-MM-DD Format): ");
        String date = sc.next();

        if(date.contains("-"))
        {
            System.out.print("Please enter precipitation (cm): ");
            double precipitation = sc.nextDouble();

            System.out.print("Please enter windspeed (km/h): ");
            double windspeed = sc.nextDouble();

            Storm a1 = new Storm(name, precipitation, windspeed, date);

            database.put(name, a1);

            System.out.println(name + " added.");
        }

        else
        {
            System.out.println("You entered an incorrect date. Please try again!");
        }
    }

    /**
     * Searches for a storm in the HashMap.
     */
    public static void searchStorm()
    {
        System.out.print("\nPlease enter a name: ");
        String key = (sc.next()).toUpperCase();

        if(database.get(key) == null)
        {
            System.out.println("\nThe storm with that name was not found.");
        }
        else
        {

            System.out.println(database.get(key));
        }

        runMenu();
    }

    /**
     * Deletes a storm in the HashMap.
     */
    public static void deleteStorm()
    {
        System.out.print("\nPlease enter a name: ");
        String key = (sc.next()).toUpperCase();

        if(database.remove(key) == null)
        {
            System.out.println("\nThe storm with that name was not found.");
        }
        else
        {
            database.remove(key);
            System.out.print("\nStorm " + key + " was removed.");
        }
    }

    /**
     * Edits a storm in the HashMap.
     */
    public static void editStorm()
    {
        Storm edit;

        System.out.print("\nPlease enter a name: ");
        String key = (sc.next()).toUpperCase();

        if(database.get(key) == null)
        {
            System.out.println("\nThe storm with that name was not found.");
        }
        else {
            System.out.println(database.get(key));

            System.out.println("\nIn Edit Mode, press enter without any input to leave data unchanged.\n");
            sc.nextLine();

            System.out.print("Please enter date (YYYY-MM-DD Format): ");
            String date = sc.nextLine();

            if(date.length() < 1)
            {
                date = database.get(key).getDate();
            }

            double precipitation;

            try {
                System.out.print("Please enter precipitation (cm): ");
                precipitation = Double.parseDouble(sc.nextLine() + " ");
            }

            catch (NumberFormatException n)
            {
                precipitation = database.get(key).getPrecipitation();
            }

            double windspeed;

            try {
                System.out.print("Please enter windspeed (km/h): ");
                windspeed = Double.parseDouble(sc.nextLine() + " ");
            }

            catch (NumberFormatException n)
            {
                windspeed = database.get(key).getWindspeed();
            }
            edit = new Storm(key, precipitation, windspeed, date);

            if (date.contains("-")) {
                database.replace(key, edit);
            }

            else
            {
                System.out.println("The date you entered is invalid. Please try again.");
            }
        }
    }

    /**
     * Prints the storms in the HashMap by their precipitation in an increasing order.
     */
    public static void printStormByRainfall()
    {
        ArrayList<Storm> stormList = new ArrayList();

        database.forEach((k,v) ->  { stormList.add(v); });

        Collections.sort(stormList, new PrecipitationComparator());//Collections.reverseOrder(new PrecipitationComparator()));

        printList(stormList);
    }

    /**
     * Prints the storms in the HashMap by their wind speed in an increasing order.
     */
    public static void printStormByWindspeed()
    {
        ArrayList<Storm> stormList = new ArrayList();

        database.forEach((k,v) -> { stormList.add(v); });

        Collections.sort(stormList, new WindSpeedComparator());//Collections.reverseOrder(new WindSpeedComparator()));

        printList(stormList);
    }

    /**
     * Saves the HashMap as an external file for future use and ends the program.
     */
    public static void saveAndQuit()
    {
        try {
            FileOutputStream file = new FileOutputStream("hurricane.ser");

            ObjectOutputStream outStream = new ObjectOutputStream(file);

            outStream.writeObject(database);

            outStream.close();

            System.out.println("File saved to hurricane.ser; feel free to use the weather channel in the meantime.");

            quitMenu = true;
        }

        catch (IOException i)
        {
            System.out.println(i.toString());
        }
    }

    /**
     * Delets the HashMap if saved as an external file for future use and ends the program.
     */
    public static void quitAndDelete()
    {
        File file = new File("hurricane.ser");

        if(file.delete())
        {
            System.out.println("hurricane.ser has been deleted.");

            quitMenu = true;
        }
        else
        {
            System.out.println("No file was to be found!");
        }
    }

    /**
     * Prints the HashMap containing Storms.
     */
    public static void printList(ArrayList a)
    {
        System.out.println("\nName\t\t\tWindspeed\t\t\tRainfall\n-------------------------------------------------------------");

        for(int i = 0; i < a.size(); i++)
        {
            Storm s = (Storm) a.get(i);

            s.print();
        }
    }

    /**
     * Checks if a file containg information about the storms already exists.
     */
    public static void readFile()
    {
        try {
            FileInputStream file = new FileInputStream("hurricane.ser");

            ObjectInputStream inStream = new ObjectInputStream(file);

            database = (HashMap<String, Storm>) inStream.readObject();

            inStream.close();

            System.out.println("Hurricane.ser has been loaded.");
        }
        catch (FileNotFoundException f)
        {
            System.out.println("No file has been found");
        }

        catch (IOException i)
        {
            System.out.println(i.toString());
        }

        catch (ClassNotFoundException c)
        {
            System.out.println(c.toString());
        }
    }
}
