package gr.ntua.ece.softeng19b.data.model;

public class ATLRecordForSpecificYear extends AbstractEntsoeRecord {

    private double actualDataLoadByMonthValue;

    public ATLRecordForSpecificYear() {
        super(DataSet.ActualTotalLoad);
    }

    public double getActualDataLoadByMonthValue() {
        return actualDataLoadByMonthValue;
    }

    public void setActualDataLoadByMonthValue(double actualDataLoadByMonthValue) {
        this.actualDataLoadByMonthValue = actualDataLoadByMonthValue;
    }
}
