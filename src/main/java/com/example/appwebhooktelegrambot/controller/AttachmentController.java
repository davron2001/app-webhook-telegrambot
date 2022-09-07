package com.example.appwebhooktelegrambot.controller;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {

    String uploadDir = "images";

    @GetMapping
    public void getFileByName(@RequestParam String name, HttpServletResponse response) throws IOException {
        File file = new File(uploadDir + "/" + name);
        response.setContentType("image/jpeg");
        response.setHeader("Cache-Control", "max-age=86400000");
        response.setHeader("Content-dispositon", "attachment; filename=\"" + file.getName() + "\"");
        InputStream in = new FileInputStream(file);
        FileCopyUtils.copy(in, response.getOutputStream());
    }
}
