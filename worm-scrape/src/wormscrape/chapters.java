package wormscrape;

import java.net.URLEncoder;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * This class gets all of the chapter links from the site: "https://parahumans.wordpress.com/table-of-contents/"
 */
public class chapters {
    //Exceptions: Epilogue E.2's link is out of order and E.X's link do not have "https://"

    /**
     * Get all the chapter links.
     * @return An ArrayList of all of the chapters.
     * @throws Exception
     */
    public static ArrayList getChapters() throws Exception {
        String url = "https://parahumans.wordpress.com/table-of-contents/";
        URLEncoder.encode(url, "UTF-8");
        Document document = Jsoup.connect(url).get();
        Elements links = document.body().select("a[href*=parahumans.wordpress.com]");

        ArrayList<String> dirtyListChapters = new ArrayList<String>();
        ArrayList<String> chapters = new ArrayList<String>();

        //Trim all of the unnecessary links. Only save the chapter links.
        for (Element link : links) {
            //Clean up all of the "junk" texts
            dirtyListChapters.add(link.attr("href"));
        }

        //The naming of the chapter links are a little inconsistent, some has "category" in it and some just has the date of publication, so lots of ugly if statements are needed.
        for (String link : dirtyListChapters) {

            if (link.matches("(.*)comment(.*)") ||
                    link.matches("(.*)reply(.*)") ||
                    link.matches("(.*)share(.*)") ||
                    link.matches("(.*)table(.*)") ||
                    link.matches("(.*)cast(.*)") ||
                    link.matches("(.*)support(.*)") ||
                    link.matches("(.*)fanfiction(.*)") ||
                    link.matches("(.*)gallery(.*)") ||
                    link.matches("https://parahumans.wordpress.com/") ||
                    link.matches("https://parahumans.wordpress.com") ||
                    link.matches("https://parahumans.wordpress.com/2013/11/05/teneral-e-2/")) { //Do not include the actual listed E.2 chapter because it is out of order.
                //Note: Include the F.A.Q. in the finished version.
            }

            else {
                //Add E.X's proper link in.
                if (link.startsWith("parahumans")) {
                    chapters.add("https://parahumans.wordpress.com/2013/11/19/interlude-end/");
                } else {
                    chapters.add(link);
                    //Add E.2's proper link in at its proper position.
                    if (link.endsWith("teneral-e-1/")) {
                        chapters.add("https://parahumans.wordpress.com/2013/11/05/teneral-e-2/");
                    }
                }
            }
        }

        //Extra chapters that are out of order at the end
        chapters.remove(chapters.size() - 1);
        chapters.remove(chapters.size() - 1);

        return chapters;
    }
}
