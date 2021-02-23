package api;

import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AppController {
	
    @RequestMapping(value = "api", method = RequestMethod.GET)
    public @ResponseBody StoryInfoDelegate getServerInfo(StoryInfoDelegate sid) {
        return sid;
    }
    
}