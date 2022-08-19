package com.example.api.model.Achievement;

import com.example.api.model.User.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity()
@Table(name = "achievements")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, columnDefinition = "enum ('NEW', 'ACC', 'DEC')")
    private AchievementStatus status = AchievementStatus.NEW; // Columns

    //TODO: add necessary fields

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Achievement that = (Achievement) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    } // Equals and hashCode
}
