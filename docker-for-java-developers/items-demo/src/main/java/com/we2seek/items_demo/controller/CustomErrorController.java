package com.we2seek.items_demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        String message = "An unexpected error has occurred...";
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (statusCode != null) {
            switch (statusCode) {
                case 404:
                    message = "Page not found";
                    break;
                case 403:
                    message = "Access denied";
                    break;
                case 500:
                    message = "Internal server error";
                    break;
            }
        }

        model.addAttribute("message", message);
        model.addAttribute("statusCode", statusCode);

        return "error";
    }

}
