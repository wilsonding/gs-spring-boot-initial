package hello;

import org.springframework.web.bind.annotation.RestController;

import static org.hamcrest.CoreMatchers.nullValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class HelloController {
	
	@Autowired
	private UsersRepository userRepository;
    
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
    @RequestMapping("/user/{userName}")
    public @ResponseBody User getUserName(@PathVariable String userName) {
    	User user = null;
    user = userRepository.findByName(userName);
        return user;
    }
    
}
