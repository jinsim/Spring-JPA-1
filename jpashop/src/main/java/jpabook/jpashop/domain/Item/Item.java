package jpabook.jpashop.domain.Item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// 상속 관계 매핑이므로, 상속 관계 전략을 부모에 적어준다.
@DiscriminatorColumn(name = "dtype") // 싱글 테이블이므로, 각각의 객체가 저장될 때 구분을 위함
@Getter @Setter
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
}
