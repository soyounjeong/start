package com.koreait.day6.controller.page;

import com.koreait.day6.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages")   // http://127.0.0.1:9090/pages
// 화면을 구성하는 코드
public class PageController {
    // get&set에 의해 객체에 의해 주입할 수 있음
    @Autowired
    private AdminMenuService adminMenuService;


    // http://127.0.0.1:9090/pages 이걸치고 엔터를 치게 되면 여기로 들어오게 됨.
    @RequestMapping(path={""})
    // ModelAndView : 스프링에 있는 리턴타입 아무것도 치지 않으면 index 메소드를 호출하고 화면이 나가게 됨.
    public ModelAndView index(){
        // ModelAndView라는 객체를 만들어서 페이지를 띄움
        return new ModelAndView("/pages/index")
                // 이름은 menuList이지만 실제값은 adminMenuService.getAdminMenu
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "main");
    }


    // user 페이지
    @RequestMapping(path = {"/user"})
    public ModelAndView user(){
        return new ModelAndView("/pages/user")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "main");
    }

    // category 페이지
    @RequestMapping(path = {"/category"})
    public ModelAndView category(){
        return new ModelAndView("/pages/category")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "main");
    }

    // item
    @RequestMapping(path = {"/item"})
    public ModelAndView item(){
        return new ModelAndView("/pages/item")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "main");
    }

    //orderGroup
    @RequestMapping(path = {"/orderGroup"})
    public ModelAndView orderGroup(){
        return new ModelAndView("/pages/orderGroup")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "main");
    }

    // partner
    @RequestMapping(path = {"/partner"})
    public ModelAndView partner(){
        return new ModelAndView("/pages/partner")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "main");
    }

    // adminuser
    @RequestMapping(path = {"/adminuser"})
    public ModelAndView adminuser(){
        return new ModelAndView("/pages/adminuser")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "main");
    }
}

/*
    ModelAndView
    - Web MVC FrameWord에서 model과 view 둘 다 저장 가능
    - Handler가 return한 model과 view를 나타내며, dispatcherServlet에 의해 분석됨
    - view는 viewResolver 객체에 의해 명시된 String view name을 가져와 사용
    - ModelAndView는 model과 같이 interface가 아니라 class로 구현
*/
