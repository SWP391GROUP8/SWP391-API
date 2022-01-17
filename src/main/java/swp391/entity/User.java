package swp391.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
@Table(name = "table_user")
public class User {
    @Id
    @Column(unique = true)
    private String email;
    @Column
    private String name;
    @Column
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthDay;
    @Column
    private String phone;
    @Column
    private String address;
    @Column
    private String password;
    @Column
    private String status;

    // relationship role - user: 1-N
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")

    private Role role;
}
