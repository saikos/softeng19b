package gr.ntua.ece.softeng19b.cli;

import static picocli.CommandLine.Option;

public class DateArgs {
    @Option(names="--date",  description = "where date is in the following format: YYYY-MM-DD.")
    String date;
    @Option(names="--month", description = "where month is in the following format: YYYY-MM.")
    String month;
    @Option(names="--year",  description = "where year is in the following format: YYYY.")
    String year;
}
