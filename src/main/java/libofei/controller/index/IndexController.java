package libofei.controller.index;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {


    @RequestMapping("/index")
    public String helloWorld(Map<String,Object> resultMap) {
        resultMap.put("name","李博飞");
        resultMap.put("age",0);
        List<String> list = new ArrayList<String>();
        for(int i = 0;i<list.size();i++){
            list.add(i+"");
        }
        resultMap.put("list",list);
        return "/_index.html";
    }

}
