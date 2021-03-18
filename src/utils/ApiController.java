package utils;

import org.springframework.web.bind.annotation.*;
// import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin
public class ApiController {
	
    @RequestMapping(value = "story-{storyIndex:[0-9]+}", method = RequestMethod.GET)
    public @ResponseBody StoryInfoDelegate getServerInfo(@PathVariable("storyIndex") final StoryInfoDelegate sid) {
        return sid;
    }
    
}