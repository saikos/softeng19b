package gr.ntua.ece.softeng19b.cli;

import picocli.CommandLine;
import static picocli.CommandLine.*;


@Command(
    name="energy_TEAM",
    mixinStandardHelpOptions = true,
    version = "energy_TEAM 1.0",
    subcommands = {
        ActualTotalLoad.class,
        AggregatedGenerationPerType.class
    }
)
public class App {

    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine(new App());
        commandLine.setCaseInsensitiveEnumValuesAllowed(true);
        int exitCode = commandLine.execute(args);
        System.exit(exitCode);
    }
}
