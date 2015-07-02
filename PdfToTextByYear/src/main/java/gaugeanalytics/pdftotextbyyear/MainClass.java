/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gaugeanalytics.pdftotextbyyear;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 *
 * @author Abhay
 */
public class MainClass {

    static int counter;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Folder path...");
        String tmp = sc.next();
        fileHandeler(tmp);
        System.out.println(counter + " Pdf file is converted");
    }

    public static void fileHandeler(String myDirectoryPath) {
        File dir = new File(myDirectoryPath);
        File[] directoryListing = dir.listFiles();
        for (File child : directoryListing) {
            String name = child.getName().substring(0, child.getName().length() - 4).trim();
            String textData = pdfToText(child);
            String path = filePath(textData) + "/" + name + ".txt";
            saveFile(textData, path);
            //System.out.println(name);
            counter++;
        }
    }

    public static String pdfToText(File file) {
        PDFParser parser;
        String Text = null;
        PDFTextStripper pdfStripper = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        try {
            parser = new PDFParser(new FileInputStream(file));
        } catch (IOException e) {
            System.err.println("Unable to open PDF Parser. " + e.getMessage());
            return null;
        }
        try {
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            pdfStripper.setStartPage(1);
            pdfStripper.setEndPage(5);
            Text = pdfStripper.getText(pdDoc);
        } catch (Exception e) {
            System.out.println("Something wrong in this pdf document");
            Text = "";
        } finally {
            try {
                if (cosDoc != null) {
                    cosDoc.close();
                }
                if (pdDoc != null) {
                    pdDoc.close();
                }
            } catch (Exception e) {
            }
        }
        return Text;
    }

    public static String filePath(String textData) {
        TreeSet<Integer> t = new TreeSet();
        String basePath = "/media/nitin/9E3CEE5E3CEE3147/Abhay/TaskData/Text-Data/Bombay-Aurangabad/";
        String regexp = "19\\d{2}|20\\d{2}";
        Pattern pattern = Pattern.compile(regexp);
        String year;
        try {
            if (textData.contains("DATE")) {
                int templ = textData.indexOf("DATE");
                String tmp = textData.substring(templ, templ + 100);
                Matcher m = pattern.matcher(tmp);
                while (m.find()) {
                    String yr = m.group();
                    int y = Integer.parseInt(yr.trim());
                    if (y <= 2015) {
                        t.add(y);
                    }
                }
                year = t.last().toString();

            } else if (textData.contains("DATED")) {
                int templ = textData.indexOf("DATED");
                String tmp = textData.substring(templ, templ + 100);
                Matcher m = pattern.matcher(tmp);
                while (m.find()) {
                    String yr = m.group();
                    int y = Integer.parseInt(yr.trim());
                    if (y <= 2015) {
                        t.add(y);
                    }
                }
                year = t.last().toString();

            } else if (textData.contains("Date")) {
                int templ = textData.indexOf("Date");
                String tmp = textData.substring(templ, templ + 100);
                Matcher m = pattern.matcher(tmp);
                while (m.find()) {
                    String yr = m.group();
                    int y = Integer.parseInt(yr.trim());
                    if (y <= 2015) {
                        t.add(y);
                    }
                }
                year = t.last().toString();

            } else {
                year = t.last().toString();
            }
            System.out.println("First try working " + t);
        } catch (Exception e3) {
            
            try {
                Matcher m = pattern.matcher(textData);
                while (m.find()) {
                    String yr = m.group();
                    int y = Integer.parseInt(yr.trim());
                    if (y <= 2015) {
                        t.add(y);
                    }
                }
                year = t.last().toString();
                System.out.println("Second try working " + t);
            } catch (Exception e) {
                year = "CorruptedPdf";
            }
        }
        //System.out.println(t);
        String path;
        try {
            path = basePath + t.last().toString();
            //System.out.println(t);
        } catch (Exception e) {
            System.out.println(textData);
            System.out.println("TreeSet - " + t);
            try {
                path = basePath + year;
            } catch (Exception e2) {
                path = basePath + "/" + "Year";
            }
        }
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    public static void saveFile(String data, String path) {
        try {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "utf-8"))) {
                writer.write(data);
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println(path + "This File not found");
        }
    }

}
