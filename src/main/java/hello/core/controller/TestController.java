package hello.core.controller;

import hello.core.model.FileDto;
import hello.core.util.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
public class TestController {

    private final FileUpload fileUpload;

    @Autowired
    public TestController(FileUpload fileUpload) {
        this.fileUpload = fileUpload;
    }

    @PostMapping("/fileupload.do")
    public ModelAndView testPost(MultipartFile file) throws IOException {
        ModelAndView view = new ModelAndView("test");
        FileDto fileDto = fileUpload.save(file);
        view.addObject("url", fileDto.getUrl());
        return view;
    }
}
