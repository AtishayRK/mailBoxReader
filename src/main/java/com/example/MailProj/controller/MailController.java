package com.example.MailProj.controller;


import com.example.MailProj.services.MailReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MailController {

    @Autowired
    MailReaderService mailReaderService;

    @RequestMapping(value = "/map",method = RequestMethod.GET,produces = {"application/json"})
 public @ResponseBody String getLastMailId()throws Exception
 {
     String id=mailReaderService.getLastMailId();
     return "hello";
 }

}
