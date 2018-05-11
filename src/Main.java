import org.kohsuke.args4j.*;

import java.io.*;

public class Main {
    @Option(name = "-c",usage="Show numbers")
    private boolean numFlag;

    @Option(name = "-u", usage="Show only uniq string")
    private boolean uniStr;

    @Option(name = "-i", usage = "Ignore case")
    private boolean ignoreCase;

    @Option(name = "-s", usage = "Ignore first N symbols")
    private int numIgnoreSym;

    @Option(name = "-o", usage = "Output file")
    private String outputFile;

    @Argument(usage = "Input file")
    private String inputFile;

    public static void main(String[] args) {
            new Main().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
        }
        Uniq uniq = new Uniq(numFlag, uniStr, ignoreCase, numIgnoreSym, outputFile, inputFile);
        uniq.launch();

    }
}
