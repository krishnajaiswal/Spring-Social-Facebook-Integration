package facebook.service;

import org.springframework.social.facebook.api.Album;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.web.servlet.ModelAndView;

public interface FacebookService {
    public String generateFacebookAuthorizeUrl();

    public void generateFacebookAccessToken(String code);

    public String getUserData();

    public void getUserFeed(ModelAndView modelAndView);

    public PagedList<Album> getUserAlbum();

    public String postUserFeed(String tag);

    public void getComment();
}
