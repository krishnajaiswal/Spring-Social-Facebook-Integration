package facebook.controller;

import facebook.service.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Album;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FacebookController {

	@Autowired
	private FacebookService facebookService;

	@GetMapping("/generateFacebookAuthorizeUrl")
	public String generateFacebookAuthorizeUrl() {
		return facebookService.generateFacebookAuthorizeUrl();
	}

	@GetMapping("/")
	public List<String> welcome() {
		List<String> urls = new ArrayList<>();
		urls.add("http://localhost:8080/generateFacebookAuthorizeUrl");
		urls.add("http://localhost:8080/getUserData");
		urls.add("http://localhost:8080/getUserFeeds");
		urls.add("http://localhost:8080/getUserAlbum");
		return urls;
	}

	@GetMapping("/facebook")
	public void generateFacebookAccessToken(@RequestParam("code") String code) {
		facebookService.generateFacebookAccessToken(code);
	}

	@GetMapping("/getUserData")
	public String getUserData() {
		return facebookService.getUserData();
	}

	@GetMapping("/getUserFeeds")
	public ModelAndView getUserFeeds() {
		ModelAndView mv = new ModelAndView("profile");
		facebookService.getUserFeed(mv);
		return mv;
	}

	// For comment  .
	@GetMapping("/getComment")
	public void getAllComment() {
		facebookService.getComment();
	}

	@GetMapping("/getUserAlbum")
	public PagedList<Album> getUserAlbum() {
		return facebookService.getUserAlbum();
	}

	@PostMapping("/post/{name}")
	public String postUserFeed(@PathVariable (value = "name") String tag) {
		System.out.println("A");
		return facebookService.postUserFeed(tag);
	}
}
