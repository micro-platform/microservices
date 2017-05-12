package com.imta.microservices.eshop.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by damien on 12/05/2017.
 */
@RestController
public class CookieController {

    private final HashMap<String, Integer> cpt = new HashMap<>();

    @RequestMapping(value = "/setCookie", method = RequestMethod.GET)
    public String setCookie(HttpServletResponse response) throws IOException {

        final String value = UUID.randomUUID().toString();
        response.addCookie(new Cookie("cookie_test", value));


        cpt.put(value, 0);

        return "Ok";
    }


    @RequestMapping(value = "/testCookie", method = RequestMethod.GET)
    public String testCookie(@CookieValue(value = "cookie_test", required=false) String cookie) throws IOException {
        System.out.println(cookie);

        synchronized (cpt) {
            cpt.put(cookie, cpt.getOrDefault(cookie, 0) + 1);
        }

        System.out.println();
        System.out.println();
        System.out.println(cpt);

        return "Ok";
    }

}
