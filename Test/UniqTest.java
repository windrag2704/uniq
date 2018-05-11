import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.*;
public class UniqTest {
    Uniq uniq = new Uniq(false, false, false, 0, "output1", "input1");
    @Test
    public void withEmptyFile() {
        uniq.start();
        assertTrue(equalFiles("output1","outputNull"));
    }
    @Test
    public void withUniqFlag() {
        uniq.setFlags(
                false,
                true,
                false,
                0,
                "outputu",
                "input");
        uniq.start();
        assertTrue(equalFiles("outputu", "outputU"));
    }
    @Test
    public void withNumFlag() {
        uniq.setFlags(
                true,
                false,
                false,
                0,
                "outputc",
                "input"
        );
        uniq.start();
        assertTrue(equalFiles("outputc", "outputC"));
    }
    @Test
    public void withCaseFlag() {
        uniq.setFlags(
                false,
                false,
                true,
                0,
                "outputi",
                "input"
        );
        uniq.start();
        assertTrue(equalFiles("outputi", "outputI"));
    }
    @Test
    public void withSkip10Flag() {
        uniq.setFlags(
                false,
                false,
                false,
                10,
                "outputs10",
                "input"
        );
        uniq.start();
        assertTrue(equalFiles("outputs10", "outputS10"));
    }
    @Test
    public void withSkip100Flag() {
        uniq.setFlags(
                false,
                false,
                false,
                100,
                "outputs100",
                "input"
        );
        uniq.start();
        assertTrue(equalFiles("outputs100", "outputS100"));
    }
    @Test
    public void withUCIFlags() {
        uniq.setFlags(
                true,
                true,
                true,
                0,
                "outputuci",
                "input"
        );
        uniq.start();
        assertTrue(equalFiles("outputuci", "outputUCI"));
    }
    @Test
    public void withUCIS15Flags() {
        uniq.setFlags(
                true,
                true,
                true,
                15,
                "outputucis15",
                "input"
        );
        uniq.start();
        assertTrue(equalFiles("outputucis15", "outputUCIS15"));
    }
    public boolean equalFiles(String file1, String file2)  {
        try {
            BufferedReader in1 = new BufferedReader(new FileReader(file1));
            BufferedReader in2 = new BufferedReader(new FileReader(file2));
            String line1 = in1.readLine();
            String line2 = in2.readLine();
            while (line1 != null || line2 != null) {
                if (!line1.equals(line2)) return false;
                line1 = in1.readLine();
                line2 = in2.readLine();
            }
            in1.close();
            in2.close();
            return true;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
