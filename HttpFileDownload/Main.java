package com.company.HttpFileDownload;

import java.net.MalformedURLException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static String scanHostName() {
        System.out.print("{+} Enter host name >> ");
        return scanner.nextLine();
    }
    public static String scanFileName() {
        System.out.print("{+} Enter file name >> ");
        return scanner.nextLine();
    }
    public static String choice() {
        System.out.println("cd dir? (yes/no)");
        return scanner.nextLine();
    }
    public static String scanDir() {
        System.out.print("{+} Enter dir >> ");
        return scanner.nextLine();
    }
    public static void main(String[] args) throws MalformedURLException {
        String host = scanHostName();
        DownloadFunc downloadFunc = new DownloadFunc(host, "");
        downloadFunc.baseDir();
        while (true) {
            String message = choice();
            if (Objects.equals(message, "yes")) {
                String dir = scanDir();
                downloadFunc.getDir(dir);
            } else {
                System.out.println("{+} File scan...");
                String file = scanFileName();
                DownloadFunc download = new DownloadFunc(host, file);
                download.fileDownload();
            }
        }
    }
}
