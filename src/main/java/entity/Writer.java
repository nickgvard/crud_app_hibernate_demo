package entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

/**
 * @author Nikita Gvardeev
 * 13.11.2021
 */
@Entity()
@Table(name = "writer", schema = "public", catalog = "postgres")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Writer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "writer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    public void addPost(Post post) {
        posts.add(post);
        post.setWriter(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Writer writer = (Writer) o;
        return id == writer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}