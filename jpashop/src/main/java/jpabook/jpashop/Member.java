package jpabook.jpashop;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {

    // 식별자를 id에 매핑해주고, GeneratedValue로 자동 생성되도록 한다. DB가 자동 생성 해줌.
    @Id @GeneratedValue
    private Long id;
    private String username;

}
