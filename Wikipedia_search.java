package com.company;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


class WikiParser {
    private String url;

    WikiParser(String url) {
        this.url = url;
    }

    public void GetText(String url) {
        try {
            Document doc = Jsoup.connect(url).referrer("http://www.google.com").get();
            System.out.print("{+} Do you want to save text in txt file? (Yes/No) - ");
            Scanner scan = new Scanner(System.in);
            String answer = scan.nextLine();
            if (Objects.equals(answer, "No")) {
                for (Element headline : doc.getElementsByTag("p")) {
                    System.out.println(headline.text());
                }
            }
            else if (Objects.equals(answer, "Yes")) {
                ArrayList<String> buff = new ArrayList<>();
                for (Element headline : doc.getElementsByTag("p")) {
                    buff.add(headline.text());
                }
                AddDataToFile(buff);
            }
            else {
                System.out.println("{-} This program have two answer (Yes/No) you enter - " + answer);
                System.exit(1);
            }
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void AddDataToFile(ArrayList<String> text) {
        try {
            String file_create = "Wiki_text.txt";
            File file = new File(file_create);
            FileWriter fileWriter = new FileWriter(file_create, false);
            for (String str: text) {
                fileWriter.write(str);
            }
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}


public class Main {
    public static String GetUrl() {
        System.out.print("{+} Enter the subject of a Wikipedia article: ");
        Scanner scan = new Scanner(System.in);
        String subject = scan.nextLine();
        return "https://ru.wikipedia.org/wiki/" + subject;
    }
    public static void main(String[] args) {
        while (true) {
            String url = GetUrl();
            com.company.WikiParser wikiParser = new com.company.WikiParser(url);
            wikiParser.GetText(url);
        }
    }
}
