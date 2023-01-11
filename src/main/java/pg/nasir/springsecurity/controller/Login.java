package pg.nasir.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pg.nasir.springsecurity.model.Role;
import pg.nasir.springsecurity.model.User;
import pg.nasir.springsecurity.repository.UserRepository;
import pg.nasir.springsecurity.service.RoleService;
import pg.nasir.springsecurity.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Login {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    public Login() {
    }

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value={"/index"}, method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        List<Role> listRoles = roleService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        modelAndView.addObject("listRoles", listRoles);
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userEamilExists = userService.findUserByEmail(user.getEmail());
        User userNameExists = userService.findUserByName(user.getName());
        if (userEamilExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Email has already been taken"
                                    + " Check your details!");
        }
        if(userNameExists!=null){
            bindingResult.rejectValue("name", "error.user",
                            "Name has already been taken"
                                    + " Check your details!");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "Registration Successful.");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();

        //-----------------------------------------
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loginUser = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("control", loginUser.getRoles());//Authentication for NavBar
        modelAndView.addObject("auth", loginUser);
        modelAndView.setViewName("home");
        return modelAndView;
    }

}
