package com.company.HttpFileDownload;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class DownloadFunc implements DownloadModel {
    static Logger logger = Logger.getLogger(DownloadFunc.class.getName());
    private String fileName;
    private String hostName;
    private static final int port = 8000;

    public DownloadFunc(String hostName, String fileName) {
        this.hostName = hostName;
        this.fileName = fileName;
    }

    @Override
    public void fileDownload() throws MalformedURLException {
        URL url = new URL("http://" + this.hostName + ":" + port + "//" + fileName);
        System.out.println(url);
        try (InputStream in = url.openStream()) {
            Files.copy(in, Paths.get(fileName));
            logger.setLevel(Level.FINE);
            System.out.println("File: " + this.fileName + " is download");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void baseDir() {
        try {
            Document doc = Jsoup.connect("http://" + this.hostName + ":" + port + "//").referrer("http://www.google.com").get();
            for (Element tagElement: doc.getElementsByTag("li"))  {
                System.out.println(tagElement.text());
            }
            logger.setLevel(Level.FINE);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void getDir(String dir) {
        try {
            Document doc = Jsoup.connect("http://" + this.hostName + ":" + port + "//" + dir).referrer("http://www.google.com").get();
            for (Element tagElement: doc.getElementsByTag("li"))  {
                System.out.println(tagElement.text());
            }
            logger.setLevel(Level.FINE);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
