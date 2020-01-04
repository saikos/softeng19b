package gr.ntua.ece.softeng19b.cli;

import static picocli.CommandLine.*;

@Command
public class BasicCliArgs {

    @Option(
            names = {"-h", "--help"},
            usageHelp = true,
            description = "display this help message")
    protected boolean usageHelpRequested;

    @Spec
    protected Model.CommandSpec spec;
}
