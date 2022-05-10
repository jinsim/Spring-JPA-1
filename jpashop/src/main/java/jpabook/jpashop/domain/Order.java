package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id") // 테이블명 + _id
    private Long id;

    @ManyToOne // 다대일 관계
    @JoinColumn(name = "member_id") // 매핑을 뭐로 할거냐. foreign key의 이름이 member_id가 된다.
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    // private Date data를 사용하면 날짜 관련된 애너테이션 매핑을 해야한다.
    // 자바 8부터는 LocalDateTime를 사용하면 하이버네이트가 알아서 지원해준다.
    private LocalDateTime orderDate; // 주문 시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문 상태 [ORDER, CANCEL]
}
