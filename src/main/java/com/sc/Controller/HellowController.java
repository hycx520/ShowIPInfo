package com.sc.Controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
@RestController
public class HellowController
{
	static Logger logger = (Logger) LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	public HellowController() {

	}
    @RequestMapping("/login")
    public ModelAndView helloGet() throws Exception
    {
    	
    	logger.info("进入登录页面");
    	ModelAndView mv=new ModelAndView();
    	mv.setViewName("login");
    	return mv;
    }
}