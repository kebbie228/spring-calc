package org.itstep;

import org.itstep.DataCalc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {

    @RequestMapping("/user")
    public String user(Model model) {
        model.addAttribute("dataCalc", new DataCalc());
        return "user";
    }

    @RequestMapping("/root")
    public String root(Model model) {
        model.addAttribute("dataCalc", new DataCalc());
        return "root";
    }

    @RequestMapping(value = "/user", method =
            RequestMethod.POST
    )
    public String calc(Model model, @ModelAttribute("data") DataCalc dataCalc) {
        int a = dataCalc.getA();
        int b = dataCalc.getB();
        int op = dataCalc.getOp();
        int c = 0;
        switch (op) {
            case 0:
                c = a + b; break;
            case 1:
                c = a - b; break;
        }
        dataCalc.setC(c);
        System.out.printf("a=%d, b=%d, c=%d, op=%d%n", a,b,c,op);
        model.addAttribute("dataCalc", dataCalc);
        return "user";
    }

    @RequestMapping(value = "/root", method =
            RequestMethod.POST
    )
    public String calc2(Model model, @ModelAttribute("data") DataCalc dataCalc) {
        int a = dataCalc.getA();
        int b = dataCalc.getB();
        int op = dataCalc.getOp();
        int c = 0;
        switch (op) {
            case 0:
                c = a + b; break;
            case 1:
                c = a - b; break;
            case 2:
                c = a * b; break;
            case 3:
                c = a / b; break;
        }
        dataCalc.setC(c);
        System.out.printf("a=%d, b=%d, c=%d, op=%d%n", a,b,c,op);
        model.addAttribute("dataCalc", dataCalc);
        return "root";
    }
} 