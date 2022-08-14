package store.zor.referral.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import store.zor.referral.entity.Hits;
import store.zor.referral.entity.Url;
import store.zor.referral.repository.HitsRepository;
import store.zor.referral.repository.UrlRepository;
import org.springframework.stereotype.Service;
import store.zor.referral.utils.UAgentInfo;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class UrlService {
    private final UrlRepository urlRepository;
    private final HitsRepository hitsRepository;
    private final BaseConversion conversion;

    @Value("${referrer.baseurl}")
    private String baseUrl;

    @Value("${referrer.androidUrl}")
    private String androidUrl;

    @Value("${referrer.iosUrl}")
    private String iosUrl;

    public String convertToShortUrl(Long userId, HttpServletRequest request) {
        var url = Url.builder()
                .userId(userId)
                .longUrlWeb(baseUrl)
                .longUrlAndroid(androidUrl)
                .longUrlIos(iosUrl)
                .createdDate(LocalDateTime.now())
                .remoteAddr(request.getRemoteAddr())
                .build();
        var entity = urlRepository.save(url);

        return conversion.encode(entity.getId());
    }

    public String getOriginalUrl(String shortUrl, HttpServletRequest request) {
        var userAgent = new UAgentInfo(request);
        var id = conversion.decode(shortUrl);
        var entity = urlRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shortUrl));

        var hits = Hits.builder()
                .urlId(id)
                .userAgent(userAgent.getUserAgent())
                .hitDateTime(LocalDateTime.now())
                .remoteAddr(request.getRemoteAddr())
                .build();
        hitsRepository.save(hits);

        if (userAgent.detectAndroid()){
            return entity.getLongUrlAndroid();
        }
        if (userAgent.detectIphoneOrIpod()) {
            return entity.getLongUrlIos();
        }

        return entity.getLongUrlWeb();
    }
}
