package codesquad.domain;

import org.springframework.data.repository.CrudRepository;
// 기본적으로 하는 데이터베이스의 crud는 끝
public interface UserRepository extends CrudRepository<User, Long> {
// 인터페이스 만들면 spring이 다 구현해준다. 쓰기만함녀됟ㄴ다
//    User findByUserId(String userId);
//    User findByEmail(String email);
//    User findByUserIdAndEmail(String userId, String email);
//    이런식의 convention으로 만들어낼수있음. 이걸 자동으로 만들어주니 사용만하면됨.ㅇ
}
