package com.widehouse.whatnext.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class TaskController {
    @GetMapping("task")
    public void tasks() {

    }
}
