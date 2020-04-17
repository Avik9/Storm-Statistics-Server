/**
 * @author Avik Kadakia
 * email:avik.kadakia@stonybrook.edu
 * Stony Brook ID: 111304945
 *
 * Class: CSE 214.02
 * Recitation: CSE 214 - R.14
 *
 * The class creates a <code>Storm</code> which will serve as the main object for the project.
 */

import java.io.Serializable;

public class Storm implements Serializable
{
    private String name; // name of the storm
    private double precipitation; // precipitation of the storm
    private double windspeed; // Wind speed during the storm
    private String date;// formatted Internet-Style YYYY-MM-DD

    /**
     * Constructor of the Storm class.
     *
     * @param nName
     *      Sets the name of the Storm.
     *
     * @param nPrecipitation
     *      Sets the precipitation of the Storm.
     *
     * @param nWindspeed
     *      Sets the wind speed of the Storm.
     *
     * @param nDate
     *      Sets the date of the Storm.
     *
     */
    public Storm(String nName, double nPrecipitation, double nWindspeed, String nDate)
    {
        this.name = nName;
        this.precipitation = nPrecipitation;
        this.windspeed = nWindspeed;
        this.date = nDate;
    }

    /**
     *  Returns the precipitation of the storm.
     *
     * @return
     *      Returns the precipitation.
     */
    public double getPrecipitation() {
        return precipitation;
    }

    public String getDate() {
        return date;
    }

    /**
     * Returns the windspeed of the class.
     *
     * @return
     *      Returns the windspeed.
     */
    public double getWindspeed() {
        return windspeed;
    }

    /**
     * Sets the value of the precipitation as the new value.
     *
     * @param nPrecipitation
     *      Sets the precipitation.
     */
    public void setPrecipitation(double nPrecipitation) {
        this.precipitation = nPrecipitation;
    }

    /**
     * Sets the value of the wind speed as the new value.
     *
     * @param nWindspeed
     *      Returns the wind speed.
     */
    public void setWindspeed(double nWindspeed) {
        this.windspeed = nWindspeed;
    }

    /**
     * Sets the date of the wind speed as the new value.
     *
     * @param nDate
     *      Sets the date.
     */
    public void setDate(String nDate) {
        if(date.contains("-"))
        {
            this.date = nDate;
        }

        else
        {
            System.out.println("You entered an incorrect date. Please try again!");
        }
    }

    /**
     * Returns a String containing the information about the storm.
     *
     * @return
     *      String containing the information about the storm.
     */
    public String toString()
    {
        return "\nStorm " + this.name + ": Date " + this.date + ", " + this.windspeed + " km/h winds, " +
                this.precipitation + " cm of rain";
    }

    /**
     * Prints out the information about the storm.
     */
    public void print()
    {
        System.out.printf("%-16s %.2f\t\t\t\t %.2f\n", this.name, this.windspeed, this.precipitation);
    }
}
