package gr.ntua.ece.softeng19b.data;

/**
 * Helper class to encapsulate ActualDataLoad request params.
 */
public class ActualTotalLoadParams {

    public final String areaName;
    public final String resolution;
    public final Integer year;
    public final Integer month;
    public final Integer day;

    public ActualTotalLoadParams(String areaName, String resolution, Integer year, Integer month, Integer day) {
        this.areaName = areaName;
        this.resolution = resolution;
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
