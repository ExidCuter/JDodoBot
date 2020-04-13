package xyz.the_dodo.bot.utils;

import com.github.jreddit.entity.Submission;
import com.github.jreddit.entity.User;
import com.github.jreddit.retrieval.Submissions;
import com.github.jreddit.utils.restclient.HttpRestClient;
import com.github.jreddit.utils.restclient.RestClient;

import java.util.List;
import java.util.Random;

public class RedditUtils {
    private static RestClient restClient = new HttpRestClient();
    private static User user = new User(restClient, "acc", "accPass");

    public static List<Submission> getPosts(String subreddit, int count) {
        Submissions submissions;

        submissions = new Submissions(restClient, user);

        return submissions.ofSubreddit(subreddit, null, -1, count, null, null, true);
    }

    public static String getRandomPost(List<Submission> submissions) {
        Random rand;
        Submission selected;

        rand = new Random();

        selected = submissions.get(rand.nextInt(submissions.size()));

        if (selected != null) {
            return selected.getTitle() + "&" + selected.getURL() + "&" + selected.getPermalink();
        }

        return "";
    }

}
