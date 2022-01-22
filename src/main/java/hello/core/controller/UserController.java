package hello.core.controller;

import hello.core.service.UserService;
import hello.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.TransactionRolledbackException;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "test";
    }

    @GetMapping("test.do")
    public ModelAndView test(Long no) {
        ModelAndView view = new ModelAndView("test");
        User user = userService.findUserById(no);
        view.addObject("test", user);
        return view;
    }

    @PostMapping("test.do")
    public ModelAndView testPost(User user) throws TransactionRolledbackException {
        ModelAndView view = new ModelAndView("test");
        userService.join(user);
        User joinUser = userService.findUserById(user.getId());
        view.addObject("test", joinUser);
        return view;
    }
}
