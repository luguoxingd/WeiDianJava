package com.weidian.open.sdk.util;

import com.db.CommonJdbc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.oauth.OAuth;
import com.weidian.open.sdk.response.oauth.OAuthResponse;
import com.weidian.open.sdk.response.oauth.OAuthResult;

@Component
public class TokenTask {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(TokenTask.class);

    private OAuth oauth = OAuth.getInstance();

    @Scheduled(cron = "0 0 6 * * ?")
    public void setTokenStringTask() {
        String update_time = SystemPropertyUtils
                .getKeyValue(SystemConfig.TOKEN_REFRESH_DATE);
        long currentTime = System.currentTimeMillis();
        if (update_time == null || "".equals(update_time)) {
            setProperties(currentTime);
        } else {
            long recordTime = Long.parseLong(update_time);
            if (currentTime - recordTime > 24 * 3600) {
                setProperties(currentTime);
            }
        }
    }

    public void setProperties(long currentTime) {
        OAuthResponse response;
        try {
            response = oauth.getPersonalToken();
            LOGGER.debug("response:{}\n", response.toString());
            if (response.getStatus().getStatusCode() == 0) {

                OAuthResult result = response.getResult();
                String token = result.getAccessToken();
                LOGGER.debug("update_time:" + currentTime);
                System.out.println("update_time:" + currentTime);
                LOGGER.debug("update_token:" + token);
                System.out.println("update_token:" + token);

                SystemPropertyUtils.writeProperties(SystemConfig.TOKEN,
                        result.getAccessToken());
                SystemPropertyUtils.writeProperties(
                        SystemConfig.TOKEN_REFRESH_DATE,
                        Long.toOctalString(currentTime));

                CommonJdbc jdbc = new CommonJdbc();
                String sql = "update WIKI_TOKEN set token='" + SystemPropertyUtils
                        .getKeyValue(SystemConfig.TOKEN) + "',update_time=" + SystemPropertyUtils
                        .getKeyValue(SystemConfig.TOKEN_REFRESH_DATE) + " where id=1";
                System.out.println(sql);
                jdbc.updateBySql(sql);
                System.out.println(SystemPropertyUtils
                        .getKeyValue(SystemConfig.TOKEN_REFRESH_DATE));
                System.out.println(SystemPropertyUtils
                        .getKeyValue(SystemConfig.TOKEN));
            }
        } catch (OpenException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TokenTask().setTokenStringTask();
    }
}
