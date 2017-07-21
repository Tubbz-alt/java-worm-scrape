package wormscrape;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Main {
    //Exceptions: Epilogue E.2's link is out of order and E.X's link do not have "https://"

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();

        PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
        System.setOut(out);
        ArrayList<String> chaptersList = Chapters.getChapters();

        //Get the chapters and their contents and print it out.
        for (String chapter : chaptersList){
            ArrayList<String> content = Story.getStory(chapter);
            for (String s : content){
                System.out.println(s);
            }
        }

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("The program ran for " + (totalTime/1000)/60 +" minutes, and " + (totalTime/1000)%60 + " seconds.");


    }

}
