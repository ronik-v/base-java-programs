package org.SQLGen.BaseIO;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ReadText {
    public static String toFile(String fileName) {
        char[] buffer = new char[10240];
        try (FileReader reader = new FileReader(fileName)) {
            int c;
            while((c = reader.read(buffer)) > 0) {

                if(c < 10240) {
                    buffer = Arrays.copyOf(buffer, c);
                }
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            System.exit(1);
        }
        return String.valueOf(buffer);
    }
}
