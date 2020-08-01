
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping(value = "/image")
public class ImageController extends BaseController {


    @Autowired
    ImageService imageService;

    /**
     * 获取验证码相关逻辑入口
     *
     * @return
     */
    @GetMapping(value = "/getImage")
    @ResponseBody
    public Map<String, Object> getImage() {
        Map<String, String> msgTextMap = imageService.getImage();
        if (!msgTextMap.equals("{}") || msgTextMap != null || msgTextMap.size() != 0)
            return result(0, "请求成功", msgTextMap);
        else return result(1, "获取图片验证码失败", null);
    }
}
