/**
 * @author Avik Kadakia
 * email:avik.kadakia@stonybrook.edu
 * Stony Brook ID: 111304945
 *
 * Class: CSE 214.02
 * Recitation: CSE 214 - R.14
 *
 * The class creates a <code>PrecipitationComparator</code> which will compare the storms based on their precipitation levels.
 */

import java.util.Comparator;

public class PrecipitationComparator implements Comparator{

    /**
     * Compares the Storms based on their precipitation level.
     *
     * @param a
     *      Storm 1.
     *
     * @param b
     *      Storm 2.
     *
     * @return
     *      Returns -1 if storm 1 has less precipitation than storm 2, returns 1 if storm 1 has more precipitation than
     *      storm 2, and 0 if they have the same amount.
     */
    public int compare(Object a, Object b)
    {
        Storm storm1 = (Storm) a;
        Storm storm2 = (Storm) b;

        if(storm1.getPrecipitation() < storm2.getPrecipitation())
        {
            return -1;
        }

        if(storm1.getPrecipitation() > storm2.getPrecipitation())
        {
            return 1;
        }

        return 0;
    }
}