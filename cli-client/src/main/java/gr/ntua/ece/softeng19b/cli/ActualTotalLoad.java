package gr.ntua.ece.softeng19b.cli;

import picocli.CommandLine;

import java.util.concurrent.Callable;

import static picocli.CommandLine.*;

@Command(
    name="ActualTotalLoad"
)
public class ActualTotalLoad extends EnergyCliArgs implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        if (usageHelpRequested) {
            CommandLine cli = spec.commandLine();
            cli.usage(cli.getOut());
            return 0;
        }

        //Implement business logic
        return 0;
    }
}
