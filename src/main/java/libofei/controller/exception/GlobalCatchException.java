package libofei.controller.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

//全局捕获异常类
@ControllerAdvice
public class GlobalCatchException {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody//返回json格式加body，返回String指定页面
    public Map<String,Object> resultError(){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("page","404");
        map.put("message","系统错误");
        return map;
    }

}
