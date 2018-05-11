import java.io.*;

public class Uniq {
    private boolean numFlag;
    private boolean uniStr;
    private boolean ignoreCase;
    private int numIgnoreSym;
    private String inputFile;
    private String outputFile;
    private PrintStream out;
    private BufferedReader in;

    public Uniq(boolean numFlag, boolean uniStr, boolean ignoreCase, int numIgnoreSym, String outputFile, String inputFile) {
        this.numFlag = numFlag;
        this.uniStr = uniStr;
        this.ignoreCase = ignoreCase;
        this.numIgnoreSym = numIgnoreSym;
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        try {
            init();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
    private void init() throws IOException {
        if (outputFile != null) {
            out = new PrintStream(new FileOutputStream(outputFile));
        } else {
            out = new PrintStream(System.out);
        }
        if (inputFile != null) {
                in = new BufferedReader(new FileReader(inputFile));
        } else {
            in = new BufferedReader(new InputStreamReader(System.in));
        }
    }
    private String read() {
        try {
            return in.readLine();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void launch() {
            String line = read();
            String nextLine;
            String lineTemp;
            String nextLineTemp;
            lineTemp = line;
            if (lineTemp != null) {
                if (ignoreCase) {
                    lineTemp = lineTemp.toLowerCase();
                }
                if (numIgnoreSym > 0) {
                    lineTemp = lineTemp.length() < numIgnoreSym ? "" : lineTemp.substring(numIgnoreSym);
                }
            }
            boolean uni = true;
            boolean equal;
            int countEq = 1;
            while (line != null) {
                nextLine = read();
                nextLineTemp = nextLine;
                if (nextLine != null) {
                    if (ignoreCase) {
                        nextLineTemp = nextLine.toLowerCase();
                    }
                    if (numIgnoreSym > 0) {
                        nextLineTemp = nextLineTemp.length() < numIgnoreSym ? "" : nextLineTemp.substring(numIgnoreSym);
                    }
                    equal = lineTemp.equals(nextLineTemp);
                } else {
                    equal = false;
                }
                if (!equal) {
                    if (!uniStr || uni) {
                        if (numFlag) {
                            out.printf("%7d" + " ", countEq);
                        }
                        out.println(line);
                    }
                    line = nextLine;
                    lineTemp = nextLineTemp;
                    uni = true;
                    countEq = 1;
                } else {
                    uni = false;
                    countEq++;
                }
            }
    }
}
