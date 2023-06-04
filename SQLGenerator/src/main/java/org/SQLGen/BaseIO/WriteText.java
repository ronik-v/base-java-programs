package org.SQLGen.BaseIO;

import java.io.FileWriter;
import java.io.IOException;

public class WriteText {
    public static void toFile(String fileName, String message, boolean append) {
        try (FileWriter writer = new FileWriter(fileName, append)) {
            writer.write(message);
            writer.flush();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            System.exit(1);
        }
    }
}
