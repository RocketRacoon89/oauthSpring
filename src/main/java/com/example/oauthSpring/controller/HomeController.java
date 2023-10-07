package com.example.oauthSpring.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.io.IOException;
import java.util.Map;

@Controller
//@RestController
public class HomeController {


    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal OidcUser principal) {
        System.out.println("YA TUT");
        System.out.println(principal.getClaims());
        System.out.println(model.toString());
        if (principal != null) {
            model.addAttribute("profile", principal.getClaims());
        }
        return "index";
    }


    @GetMapping("/secured")
    public String secured() {
        return "Hello, Secured!";
    }

    @GetMapping("/secured1")
    public String secured1(@AuthenticationPrincipal OidcUser oidcUser) {
        System.out.println(oidcUser.getClaims());

        return "secured";
    }

    @GetMapping("/user")
    public Map<String, Object> getUser(@AuthenticationPrincipal OAuth2User oAuth2User) {
        System.out.println(oAuth2User.getName());

        return oAuth2User.getAttributes();
    }

    @RequestMapping(value = "/login/oauth2/code/auth0", method = RequestMethod.GET)
    protected void getCallback(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
//        handle(req, res);
        System.out.println(req.toString());
        res.sendRedirect("/login/oauth2/code/auth0");
    }

}
