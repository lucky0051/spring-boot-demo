package com.ks.practice.service.impl;

import com.ks.practice.service.UrlShortnerService;
import com.opsmatters.bitly.Bitly;
import com.opsmatters.bitly.api.model.v4.CreateBitlinkResponse;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BitlyServiceImpl implements UrlShortnerService {
    private Bitly bitlyClient;
    @Value("${bitly.client.token}")
    private String bitlyToken;

    @PostConstruct
    public void setup() {
        bitlyClient = new Bitly(bitlyToken);
    }

    @Override
    public String getShortUrl(String longUrl) {
        String result = null;
        try {
            //free limit 10/month-left with 8
            CreateBitlinkResponse response = bitlyClient.bitlinks().shorten(longUrl).get();
            result = response.getLink();

        } catch (Exception e) {
            log.error("Exception occurred during url shortening", e);
            throw new RuntimeException("Exception occurred during url shortening");
        }
        return result;
    }

}
