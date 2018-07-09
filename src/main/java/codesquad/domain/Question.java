package codesquad.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Question {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String writer;

    @Column
    private String title;

    @Column
    private String contents;

    @Column
    private String enrollTime;

    public Question() {
    }

    public Question(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
//        this.enrollTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy. mm. dd. hh:mm:ss"));
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getEnrollTime() {
        return enrollTime;
    }

    public void setEnrollTime(String enrollTime) {
        this.enrollTime = enrollTime;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void update(Question newQuestion) {
        this.writer = newQuestion.getWriter();
        this.title = newQuestion.getTitle();
        this.contents = newQuestion.getContents();
    }
}
