package me.djamelkorei.projecttaskmanagement.web.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexView {

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }
}
