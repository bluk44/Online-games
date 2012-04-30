package org.games.online;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppletPageController {
	
	@RequestMapping(value = "/game", method = RequestMethod.GET)
	public String appetPage(){
		
		return "game";
	}
	
}
