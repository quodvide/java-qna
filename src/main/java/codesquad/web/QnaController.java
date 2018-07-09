package codesquad.web;

import codesquad.domain.Question;
import codesquad.domain.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class QnaController {
    private List<Question> questions = new ArrayList<>();

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/questions")
    public String form() {
        return "/qna/form";
    }

    @PostMapping("/questions")
    public String create(Question question) {
        question.setEnrollTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy. MM. dd. hh:mm:ss")));
        questionRepository.save(question);
        return "redirect:/";
    }
//    @PostMapping("/questions")
//    public String create(String writer, String title, String contents) {
//        Question question = new Question(writer, title, contents);
//        questions.add(question);
//        return "redirect:/";
//    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("question", questionRepository.findAll());
        return "/index";
    }

    @GetMapping("/questions/{index}") // 동적으로 달라지는 부분일떄 이런식으로 쓸수있다.
    public String show(@PathVariable long index, Model model) {
        model.addAttribute("question", questionRepository.findById(index).get());
        return "/qna/show";
    }

    @GetMapping("/questions/{id}/form")
    public String form(Model model, @PathVariable Long id) {
        model.addAttribute("question", questionRepository.findById(id).get());
        return "/qna/updateForm";
    }

    @PutMapping("/questions/{id}")
    public String update(Question newQuestion, @PathVariable Long id) {
        Question question = questionRepository.findById(id).get();
        question.update(newQuestion);
        questionRepository.save(question);
        return "redirect:/";
    }

    @DeleteMapping("/questions/{id}")
    public String delete(@PathVariable long id) {
        questionRepository.delete(questionRepository.findById(id).get());
        return "redirect:/";
    }
}
