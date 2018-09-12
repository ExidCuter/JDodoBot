package xyz.the_dodo.tests;

import net.dean.jraw.RedditClient;
import net.dean.jraw.Version;
import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;
import net.dean.jraw.models.SubredditSort;
import net.dean.jraw.models.TimePeriod;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;
import net.dean.jraw.pagination.DefaultPaginator;
import net.dean.jraw.pagination.Paginator;
import org.junit.Ignore;
import org.junit.Test;

public class GeneralTests
{
	@Test
	@Ignore
	public void redditTest() {
		// You'll want to change this for your specific OAuth2 app
		Credentials credentials = Credentials.script("ExidCuter", "", "P4rIjsbI4afQyA", "2-Xhx0xZMvotFXPo6exZFxkUff4");

		// Construct our NetworkAdapter
		UserAgent userAgent = new UserAgent("bot", "DodoBot", Version.get(), "ExidCuter");
		NetworkAdapter http = new OkHttpNetworkAdapter(userAgent);

		// Authenticate our client
		RedditClient reddit = OAuthHelper.automatic(http, credentials);

		// Browse through the top posts of the last month, requesting as much data as possible per request
		DefaultPaginator<Submission> paginator = reddit.frontPage()
				.limit(Paginator.RECOMMENDED_MAX_LIMIT)
				.sorting(SubredditSort.TOP)
				.timePeriod(TimePeriod.MONTH)
				.build();

		// Request the first page
		Listing<Submission> firstPage = paginator.next();

		for (Submission post : firstPage) {
			if (post.getDomain().contains("imgur.com")) {
				System.out.println(String.format("%s (/r/%s, %s points) - %s",
						post.getTitle(), post.getSubreddit(), post.getScore(), post.getUrl()));
			}
		}

	}
}
