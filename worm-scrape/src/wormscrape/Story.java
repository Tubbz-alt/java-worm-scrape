/***
 * Created by Chirstopher Dani
 */

package wormscrape;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Story {
    public static ArrayList getStory(String link) throws Exception{
        ArrayList<String> content= new ArrayList<>();
        String url = link;
        URLEncoder.encode(url, "UTF-8");
        Document document = Jsoup.connect(url).get();

        StringBuilder title = new StringBuilder(document.select("h1.entry-title").text());
        content.add(title.toString());
        content.add("");

        Elements story = document.select("div.entry-content").select("p");
        for (Element e : story) {
            if (e.text().startsWith(("Last Chapter")) || e.text().startsWith(("Next Chapter")) || e.text().startsWith(("Brief note from the author"))){


            }
            else{
                content.add(e.text());
            }
        }

        content.add("");
        content.add("");
        content.add("");
        return content;

    }

}
