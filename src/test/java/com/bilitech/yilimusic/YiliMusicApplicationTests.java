package com.bilitech.yilimusic;

import com.bilitech.yilimusic.VO.UserVO;
import javax.annotation.Resource;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
/*@RunWith(SpringRunner.class)*/
class YiliMusicApplicationTests {
    @Resource
    private StringEncryptor stringEncryptor;

    @Test
    void contextLoads() {

        String url = stringEncryptor.encrypt(
            "jdbc:mysql://rm-cn-x0r3bw3pl0007to.rwlb.rds.aliyuncs.com/yili_music?characterEncoding=UTF8&userSSL=false");
        String username = stringEncryptor.encrypt("xiaohua");
        String encrypted = stringEncryptor.encrypt("Cxf1230#");
        System.out.println("url: " + "ENC(" + url + ")");
        System.out.println("username: " + "ENC(" + username + ")");
        System.out.println("password: " + "ENC(" + encrypted + ")");
    }



}
