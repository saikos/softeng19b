package gr.ntua.ece.softeng19b.cli;

import gr.ntua.ece.softeng19b.client.RestAPI;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificDay;
import picocli.CommandLine;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Callable;

import static picocli.CommandLine.Command;

@Command(
    name="ActualTotalLoad"
)
public class ActualTotalLoad extends EnergyCliArgs implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        CommandLine cli = spec.commandLine();

        if (usageHelpRequested) {
            cli.usage(cli.getOut());
            return 0;
        }


        try {
            if (dateArgs.date != null ) {
                List<ATLRecordForSpecificDay> records = new RestAPI().
                        getActualTotalLoad(areaName, timeres.name(), LocalDate.parse(dateArgs.date), format);
                // Do something with the records :)
                System.out.println("Fetched " + records.size() + " records");
                return 0;
            }
            else {
                // Implement the other cases
                System.err.println("Not implemented yet");
                return -1;
            }
        } catch (RuntimeException e) {
            cli.getOut().println(e.getMessage());
            e.printStackTrace(cli.getOut());
            return -1;
        }

    }
}
