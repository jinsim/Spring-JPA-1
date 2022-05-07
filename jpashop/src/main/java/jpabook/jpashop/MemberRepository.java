package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// 리포지토리는 엔티티를 찾아주는 것 DAO랑 유사하다.
@Repository // 컴포넌트 스캔의 대상이 된다.
public class MemberRepository {

    // JPA를 사용하기 때문에, 엔티티 매니저가 있어야 한다.
    @PersistenceContext
    // 스프링 부트를 사용하기 때문에, 스프링 컨테이너 위에서 모든 것이 동작한다.
    // 해당 애너테이션이 있으면, 앤티티매니저를 주입해준다.
    // spring starter data jpa를 등록하면서, yml 설정 파일을 읽어서 엔티티매니저나 팩토리 관련 코드들이 생성된다.
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
        // 멤버를 반환하는 게 아니라 ID를 반환하는 이유?
        // 커맨드와 쿼리를 분리해라. 저장은 커맨드성이므로 반환값은 만들지 않는 것이 좋다.
        // 아이디가 있으면 다음에 다시 조회할 수 있으니 이거만 넘기면 된다.
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
