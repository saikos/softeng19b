package gr.ntua.ece.softeng19b.cli;

import java.util.concurrent.Callable;

import static picocli.CommandLine.*;

@Command(
    name="ActualTotalLoad"
)
public class ActualTotalLoad extends CommonCliArgs implements Callable<Integer> {

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
