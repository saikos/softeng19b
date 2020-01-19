package gr.ntua.ece.softeng19b.cli;

import gr.ntua.ece.softeng19b.client.Format;

import static picocli.CommandLine.*;

@Command
public class EnergyCliArgs extends BasicCliArgs {

    public enum TimeRes {
        PT15M,
        PT30M,
        PT60M
    }

    @Option(
        names = "--area",
        required = true,
        description = "the name of the area."
    )
    protected String areaName;

    @Option(
        names = "--timeres",
        required = true,
        description = "where timeres is one of the following: ${COMPLETION-CANDIDATES}."
    )
    protected TimeRes timeres;

    @ArgGroup(exclusive = true, multiplicity = "1")
    protected DateArgs dateArgs;

    @Option(
        names = "--format",
        defaultValue = "JSON",
        fallbackValue = "JSON",
        description = "the format of the response; supported values: ${COMPLETION-CANDIDATES} (default is ${DEFAULT-VALUE})."
    )
    protected Format format;

}
