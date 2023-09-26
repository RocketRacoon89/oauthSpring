package com.example.oauthSpring.controller;

import com.example.oauthSpring.dto.TokenRequest;
import com.example.oauthSpring.entity.Status;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal OidcUser principal) {
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

    @GetMapping("/api/auth/callback")
    public String callback() {

        return "callback";
    }

}
