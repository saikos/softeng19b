package gr.ntua.ece.softeng19b.cli;

import picocli.CommandLine;

import java.util.concurrent.Callable;

import static picocli.CommandLine.*;

@Command(
    name="AggregatedGenerationPerType"
)
public class AggregatedGenerationPerType extends EnergyCliArgs implements Callable<Integer> {

    @Option(
        names = "--prodtype",
        required = true,
        description = "the production type."
    )
    protected String productionType;

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