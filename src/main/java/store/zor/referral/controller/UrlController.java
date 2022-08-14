package store.zor.referral.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import store.zor.referral.service.UrlService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@Controller
public class UrlController {

    private final UrlService urlService;

    @Value("${referrer.url}")
    private String url;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @RequestMapping("/create/{userId}")
    public String convertToShortUrl(@PathVariable("userId") long userId, Model model, HttpServletRequest request) {
        String shortUrl = urlService.convertToShortUrl(userId, request);
        model.addAttribute("shortUrl", url + shortUrl);
        return "create";
    }

    @RequestMapping("/{shortUrl}")
    @Cacheable(value = "urls", key = "#shortUrl", sync = true)
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl, HttpServletRequest request) {
        var url = urlService.getOriginalUrl(shortUrl, request);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }
}
