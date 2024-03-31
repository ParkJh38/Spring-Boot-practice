package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";

    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody                                                   // http에서 Body부분에 직접 데이터를 직접 넣어준다는 의미
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;        // name에 넣은 값이 클라이언트에 그대로 넘어감   Ex) "hello Park!!!"
    }    // page source 보기를 해보면 "hello" + name 으로 적은 것이 그대로 나타남. 소스코드로 안나타남 (Api 사용이 template engine과 다른점)

    @GetMapping("hello-api")
    @ResponseBody                      // 객체로 반환하고 ResponseBody 사용하면 json 방식으로 반환
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {              // getter setter: java bing 표준 방식 / property 접근 방식
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}



