import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Stream;

public class FileCreatorTest {

    @Test
    public void shouldReadFile(){
        String pathToFile="/Users/lhurley/git/ml_sandbox/rrrr/incentives.csv";
        String dirName="/Users/lhurley/git/ml_sandbox/rrrr/data/incentives";
        try {
            Stream<String> lines = Files.lines(Paths.get(pathToFile));
            lines.forEach((line) -> {
                        String prefix = UUID.randomUUID().toString();
                        File file = new File(dirName + "/" + prefix + ".csv");
                        file.getParentFile().mkdirs();
                        try {
                            PrintWriter writer = new PrintWriter(file);
                            String text = line.substring(line.indexOf(",")+1);
                            writer.print(text);
                            writer.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
        Integer written = numFiles(new File(dirName));
        System.out.println(written + " FILES WRITTEN");

    }

    private Integer numFiles(File directory) {
        Integer count = 0;
        for (File file : directory.listFiles()) {
            if (file.isFile()) {
                count++;
            }
        }
        return count;
    }

}
