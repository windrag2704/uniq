import org.kohsuke.args4j.*;

import java.io.*;

public class Main {
    @Option(name = "-c",usage="Show numbers")
    private boolean numFlag;

    @Option(name = "-u", usage="Unikalnie stroki")
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
            e.getMessage();
        }
        PrintStream out = new PrintStream(System.out);
        if (outputFile != null) {
            try {
                out = new PrintStream(new FileOutputStream(outputFile));
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        if (inputFile != null) {
            try {
                in = new BufferedReader(new FileReader(inputFile));
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }
        try {
            String line = in.readLine();
            String nextLine;
            String lineTemp;
            String nextLineTemp;
            boolean uni = true;
            boolean equal;
            int countEq = 1;
            do {
                nextLine = in.readLine();
                if (nextLine != null) {
                    if (ignoreCase) {
                        line = line.toLowerCase();
                        nextLine = nextLine.toLowerCase();
                    }
                    equal = line.equals(nextLine);
                    if (numIgnoreSym > 0) {
                        if (line.length() < numIgnoreSym) {
                            lineTemp = "";
                        } else {
                            lineTemp = line.substring(numIgnoreSym);
                        }
                        if (nextLine.length() < numIgnoreSym) {
                            nextLineTemp = "";
                        } else {
                            nextLineTemp = nextLine.substring(numIgnoreSym);
                        }
                        equal = lineTemp.equals(nextLineTemp);
                    }
                } else {
                    equal = false;
                }
                if (!equal) {
                    if (numFlag) out.printf("%7d" + " ", countEq);
                    if (uniStr) {
                        if (uni) out.println(line);
                    } else {
                        out.println(line);
                    }
                    line = nextLine;
                    uni = true;
                    countEq = 1;
                } else {
                    uni = false;
                    countEq++;
                }
            } while (nextLine != null);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
