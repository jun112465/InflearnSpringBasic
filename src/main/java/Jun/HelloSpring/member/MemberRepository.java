package Jun.HelloSpring.member;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository {
    void save(Member member);
    Member findById(Long memberId);
}
