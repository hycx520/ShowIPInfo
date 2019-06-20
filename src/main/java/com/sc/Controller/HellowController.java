package com.sc.Controller;


import org.springframework.web.bind.annotation.*;

@RestController
public class HellowController
{
	public HellowController() {
		System.out.println("---");
	}
    @RequestMapping("/login")
    public String helloGet() throws Exception
    {
    	System.out.println("--");
        return "index.jsp";
    }
}