package gr.ntua.ece.softeng19b.cli;

import java.util.concurrent.Callable;

import static picocli.CommandLine.*;

@Command(
    name="AggregatedGenerationPerType"
)
public class AggregatedGenerationPerType extends CommonCliArgs implements Callable<Integer> {

    @Option(
        names = "--prodtype",
        required = true,
        description = "the production type."
    )
    protected String productionType;

    @Override
    public Integer call() throws Exception {

        if (usageHelpRequested) {
            spec.commandLine().usage(System.out);
            return 0;
        }

        //Implement business logic
        return 0;
    }
}