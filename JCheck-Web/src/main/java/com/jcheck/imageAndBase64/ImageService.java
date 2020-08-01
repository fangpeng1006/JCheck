
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**

 * @Author: AnthonyChen
 * @CreateTime: 2019-10-08 09:31
 * @Description: 图片验证码获取 存储 验证。使用Redis，此处可以换成session
 */
@Service("ImageService")
public class ImageService {

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 三分钟自动删除
     *
     * @return
     */
    public Map<String, String> getImage() {
        String stringRandom = MakeImageBase64.getStringRandom(4);
        String image = null;
        try {
            image = MakeImageBase64.imageToBase64(114, 38, stringRandom);
        } catch (Exception e) {
            return null;
        }
        String idString = UUID.randomUUID().toString() + System.currentTimeMillis();
        redisTemplate.opsForValue().set(idString, stringRandom, 60 * 3, TimeUnit.SECONDS);
        HashMap<String, String> returnMap = new HashMap<>();
        returnMap.put("image", image);
        returnMap.put("idString", idString);
        return returnMap;
    }


    public Boolean verifyImage(String idString,String checkCode) {
        String valueString = null;
        try {
            valueString = redisTemplate.opsForValue().get(idString).toString();
            redisTemplate.delete(idString);
        } catch (Exception e) {
            return false;
        }
        if (valueString == null || valueString.length() == 0 || "".equals(valueString)) {
            return false;
        } else {
            if (checkCode.equalsIgnoreCase(valueString)){
                return true;
            }else {
                return false;
            }
        }

    }
}
