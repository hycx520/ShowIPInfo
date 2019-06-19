package com.sc.Controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class HellowController
{
    @RequestMapping("/say")
    public String helloGet() throws Exception
    {
        return "Hellow World";
    }
}