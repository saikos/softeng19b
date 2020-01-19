package gr.ntua.ece.softeng19b.data.model;

public abstract class AbstractEntsoeRecord {

    private static final String SOURCE = "entso-e";

    enum DataSet {
        ActualTotalLoad,
        AggregatedGenerationPerType,
        DayAheadTotalLoadForecast,
        ActualVSForecastedTotalLoad
    }

    private final DataSet dataSet;
    private String areaName;
    private String areaTypeCode;
    private String mapCode;
    private String resolutionCode;
    private int year;
    private int month;

    AbstractEntsoeRecord(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public String getSource() { return SOURCE; }

    public String getDataSet() {
        return dataSet.name();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaTypeCode() {
        return areaTypeCode;
    }

    public void setAreaTypeCode(String areaTypeCode) {
        this.areaTypeCode = areaTypeCode;
    }

    public String getMapCode() {
        return mapCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    public String getResolutionCode() {
        return resolutionCode;
    }

    public void setResolutionCode(String resolutionCode) {
        this.resolutionCode = resolutionCode;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
