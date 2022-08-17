package facebook.imlementation;

import facebook.service.FacebookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.*;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacebookServiceImpl implements FacebookService {

    private String accessToken;

    @Value("${spring.social.facebook.app-id}")
    private String FacebookAppId;

    @Value("${spring.social.facebook.app-secret}")
    private String FacebookAppSecret;

    private FacebookConnectionFactory createConnection() {
        return new FacebookConnectionFactory(FacebookAppId, FacebookAppSecret);
    }
    @Override
    public String generateFacebookAuthorizeUrl() {
        OAuth2Parameters params = new OAuth2Parameters();
        params.setRedirectUri("http://localhost:8080/facebook");
        params.setScope("email");
        return createConnection().getOAuthOperations().buildAuthenticateUrl(params);
    }

    @Override
    public void generateFacebookAccessToken(String code) {
        accessToken = createConnection().getOAuthOperations().
                exchangeForAccess(code,"http://localhost:8080/facebook", null).
                getAccessToken();
    }

    @Override
    public String getUserData() {
        Facebook facebook = new FacebookTemplate(accessToken);
        String[] fields = {"id", "first_name", "name", "email", "birthday",
                "gender"};
        return facebook.fetchObject("me", String.class, fields);
    }

    @Override
    public void getUserFeed(ModelAndView modelAndView) {
        int x = 0;
        Facebook facebook = new FacebookTemplate(accessToken);
        PagedList<Post> feed = facebook.feedOperations().getFeed();
        FeedOperations feeds=facebook.feedOperations();
        modelAndView.addObject("profileName", feed.get(0).getFrom().getName());
        modelAndView.addObject("posts", feed);
    }

    @Override
    public void getComment() {
        Facebook facebook = new FacebookTemplate(accessToken);
        FeedOperations feeds=facebook.feedOperations();
        List<Post> posts=feeds.getPosts();
        List<Comment> allComment = new ArrayList<>();
        for(Post post:posts){
            List<Comment> comments = facebook.commentOperations().getComments(post.getId());
            for(Comment comment:comments){
                System.out.println("Comment on post id = " + post.getId() + " is = " + comment.getMessage());
                allComment.add(comment);
            }
        }
    }

    @Override
    public PagedList<Album> getUserAlbum() {
        return new FacebookTemplate(accessToken).mediaOperations().getAlbums();
    }

    @Override
    public String postUserFeed(String tag) {
        System.out.println("Inside post");
        Facebook facebook = new FacebookTemplate(accessToken);
        facebook.feedOperations().updateStatus(tag);
        System.out.println("Outside Post");
        return "Hello";
    }
}
