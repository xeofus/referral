package store.zor.referral.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UrlLongRequest {
    private String longUrl;
    private Date expiresDate;
}
