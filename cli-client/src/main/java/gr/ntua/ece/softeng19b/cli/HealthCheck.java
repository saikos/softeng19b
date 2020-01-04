package gr.ntua.ece.softeng19b.cli;

import picocli.CommandLine;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Callable;

import static picocli.CommandLine.Command;

@Command(
    name="HealthCheck"
)
public class HealthCheck extends BasicCliArgs implements Callable<Integer> {

    public static final String URL = App.BASE_URL + "/HealthCheck";

    @Override
    public Integer call() throws Exception {

        CommandLine cli = spec.commandLine();

        if (usageHelpRequested) {
            cli.usage(cli.getOut());
            return 0;
        }

        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL)).build();
            HttpResponse<String> response = App.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            cli.getOut().println(response.statusCode());
            cli.getOut().println(response.body());
            return 0;
        } catch (Exception e) {
            cli.getOut().println(e.getMessage());
            e.printStackTrace(cli.getOut());
            return -1;
        }        
    }
}
