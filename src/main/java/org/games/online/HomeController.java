package org.games.online;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.games.online.model.player.Player;
import org.games.online.model.player.PlayerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController implements ApplicationContextAware{
	
	private ApplicationContext applicationContext;
	
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is " + locale.toString());

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
//		
//		Object principal = SecurityContextHolder.getContext()
//				.getAuthentication().getPrincipal();
//		String username = null;
//		if (principal instanceof UserDetails) {
//			username = ((UserDetails) principal).getUsername();
//		} else {
//			username = principal.toString();
//		}
//		model.addAttribute("username", username);
//		
//		PlayerManager manager = (PlayerManager) applicationContext.getBean("playerManager");
//		Player p1 = new Player();
//		FakeChannel channel = new FakeChannel();
//		channel.setChannelId(1024);
//		p1.setName("Hitler");
//		p1.setPlayerId(88);
//		p1.setChannel(channel);
//		manager.putPlayer(p1);
//		model.addAttribute("playerManager", manager);
//		
//		Object serverSocketChannelFactory = applicationContext.getBean("serverSocketChannelFactory");
//		model.addAttribute("factory", serverSocketChannelFactory);
//		
		return "home";
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
