package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final int TYPE_COLUMN = 0;
    private static final int AMOUNT_COLUMN = 1;
    private static final String SPLITTER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readData(fromFileName);

        writeData(toFileName, dataFromFile);
    }

    public static String readData(String fromFileName) {
        File fileFrom = new File(fromFileName);
        int supply = 0;
        int buy = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileFrom));
            String line = reader.readLine();

            while (line != null) {
                String[] values = line.split(SPLITTER);
                if (values[TYPE_COLUMN].equals("supply")) {
                    supply += Integer.parseInt(values[AMOUNT_COLUMN]);
                } else {
                    buy += Integer.parseInt(values[AMOUNT_COLUMN]);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("There is no such file as " + fromFileName, e);
        }

        return "supply," + supply
                + System.lineSeparator() + "buy,"
                + buy + System.lineSeparator()
                + "result," + (supply - buy);
    }

    public static void writeData(String toFileName, String report) {
        File fileTo = new File(toFileName);

        try {
            Files.write(fileTo.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
