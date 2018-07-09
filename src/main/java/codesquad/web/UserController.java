package codesquad.web;

import codesquad.domain.User;
import codesquad.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private List<User> users = new ArrayList<>();

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{index}") // 동적으로 달라지는 부분일떄 이런식으로 쓸수있다.
    public String show(@PathVariable int index, Model model) {
        model.addAttribute("user", users.get(index-1));
        return "user/profile";
    }

    @PostMapping("/users")
    public String create(User user, Model model) { // 변수명은 name 속성이다
        userRepository.save(user);
        return "redirect:/users";
    } // 동적인 화면으로 만들려고 하는것 => 템플릿 앤진 . 그중 handlebar을 사용중

    // => 회원가입 + 보여주기까지 두가지를 한다
    @GetMapping("/users")
    public String list(Model model) { // 변수명은 name 속성이다
        model.addAttribute("users", userRepository.findAll());
        return "/user/list";
    }

    @GetMapping("/userform")
    public String form() {
        return "/user/form";
    }

    @GetMapping("/users/{index}/form") // 동적으로 달라지는 부분일떄 이런식으로 쓸수있다.
    public String editUser(@PathVariable long index, Model model) {
        model.addAttribute("user", userRepository.findById(index).get());
        return "user/updateForm";
    }

    @PutMapping("/users/{index}/update")
    public String update(@PathVariable long index, User newUser) { // 변수명은 name 속성이다
        User user = userRepository.findById(index).get();
        user.update(newUser);
        userRepository.save(user);
        return "redirect:/users";
    } // 동적인 화면으로 만들려고 하는것 => 템플릿 앤진 . 그중 handlebar을 사용중

}
