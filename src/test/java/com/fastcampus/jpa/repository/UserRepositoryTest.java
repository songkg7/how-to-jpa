package com.fastcampus.jpa.repository;

import com.fastcampus.jpa.domain.User;
import org.assertj.core.util.Lists;
import org.hibernate.criterion.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void crud() {
//        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
//        List<User> users = userRepository.findAllById(Lists.newArrayList(1L, 3L, 5L));
        User user1 = new User("jack", "jack@fastcampus.com");
        User user2 = new User("steve", "steve@fastcampus.com");

        userRepository.saveAll(Lists.newArrayList(user1, user2));

        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);

    }

    @Test
    @Transactional
    void crud2() {

        User user = userRepository.getOne(1L); // getOne 은 Lazy 로딩을 하기 때문에 트랜잭션 안에서 실행되야한다.

        System.out.println("user = " + user);
    }

    @Test
    void crud3() {
        User user = userRepository.findById(1L).orElse(null); // findById 는 옵셔널로 래핑되어 있다.
        System.out.println(user);
    }

    @Test
    void crud4() {
        long count = userRepository.count();
        System.out.println("count = " + count);
    }

    @Test
    void crud5() {
        userRepository.deleteById(1L);
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void crud6() {
        userRepository.deleteAll();
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void crud7_page() {
        // NOTE: page 는 0페이지부터 시작한다.
        Page<User> users = userRepository.findAll(PageRequest.of(0, 3));

        System.out.println("users = " + users);
        System.out.println("users.getTotalElements() = " + users.getTotalElements());
        System.out.println("users.getTotalPages() = " + users.getTotalPages());
        System.out.println("users.getNumberOfElements() = " + users.getNumberOfElements());
        System.out.println("users.getSort() = " + users.getSort());
        System.out.println("users.getSize() = " + users.getSize());
        users.getContent().forEach(System.out::println);
    }

    @Test
    void example() { // NOTE: example 을 사용하는 것보다 querydsl 을 사용하는 것이 더 낫다.
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name")
                .withMatcher("email", endsWith()); // ex) contains(), ...

        Example<User> example = Example.of(new User("ma", "fastcampus.com"), matcher);

        userRepository.findAll(example).forEach(System.out::println);
    }

    @Test
    void update() {
        userRepository.save(new User("david", "david@fastcampus.com"));
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("martin-updated@fastcampus.com");
        userRepository.save(user);
    }

    @Test
    void select() {
        System.out.println(userRepository.findByName("dennis"));

        // NOTE: 아래는 모두 동일한 쿼리가 생성된다.
        System.out.println("findByEmail : " + userRepository.findByEmail("haril@fastcampus.com"));
        System.out.println("getByEmail : " + userRepository.getByEmail("haril@fastcampus.com"));
        System.out.println("readByEmail : " + userRepository.readByEmail("haril@fastcampus.com"));
        System.out.println("queryByEmail : " + userRepository.queryByEmail("haril@fastcampus.com"));
        System.out.println("searchByEmail : " + userRepository.searchByEmail("haril@fastcampus.com"));
        System.out.println("streamByEmail : " + userRepository.streamByEmail("haril@fastcampus.com"));
        System.out.println("findUserByEmail : " + userRepository.findUserByEmail("haril@fastcampus.com"));

        // 상위 개수 가져오기
        System.out.println("findFirst1ByName : " + userRepository.findFirst1ByName("haril"));
        System.out.println("findTop2ByName : " + userRepository.findTop2ByName("haril"));

        System.out.println("findByEmailAndName : " + userRepository.findByEmailAndName("haril@fastcampus.com", "haril"));
        System.out.println("findByEmailOrName : " + userRepository.findByEmailOrName("haril@fastcampus.com", "dennis"));

        System.out.println("findByCreatedAtAfter : " + userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByIdAfter : " + userRepository.findByIdAfter(4L)); // 날짜와 시간이 아니여도 사용가능하지만, 지양하는 것이 좋다.
        System.out.println("findByCreatedAtGreaterThan : " + userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByCreatedAtGreaterThanEqual : " + userRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));

        System.out.println("findByCreatedAtBetween : " + userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L)));
        System.out.println("findByIdBetween : " + userRepository.findByIdBetween(1L, 3L));
        System.out.println("findByIdGreaterThanEqualAndIdLessThanEqual : " + userRepository.findByIdGreaterThanEqualAndIdLessThanEqual(1L, 3L));

    }

    @Test
    void select_valid() {
        System.out.println("findByIdIsNotNull : " + userRepository.findByIdIsNotNull());
//        System.out.println("findByAddressIsNotEmpty : " + userRepository.findByAddressIsNotEmpty());
        // NOTE: In 절을 사용할 때는 성능이슈가 생길 수 있으므로 들어가는 데이터의 길이에 주의해야 한다.
        System.out.println("findByNameIn : " + userRepository.findByNameIn(Lists.newArrayList("martin", "dennis")));

        System.out.println("findByNameStartingWith : " + userRepository.findByNameStartingWith("ha"));
        System.out.println("findByNameEndingWith : " + userRepository.findByNameEndingWith("ril"));
        System.out.println("findByNameContains : " + userRepository.findByNameContains("ari"));
        System.out.println("findByNameLike : " + userRepository.findByNameLike("%ari%"));

    }

    @Test
    void pagingAndSortingTest() {
        System.out.println("findTop1ByNameOrderByDesc : " + userRepository.findTop1ByNameOrderByIdDesc("haril"));
        System.out.println("findFirstByNameOrderByIdDescEmailAsc : " + userRepository.findFirstByNameOrderByIdDescEmailAsc("haril"));
        System.out.println("findFirstByNameWithSortParams : " + userRepository.findFirstByName("haril", Sort.by(Sort.Order.desc("id"))));
        System.out.println("findFirstByNameWithSortParams : " + userRepository.findFirstByName("haril", Sort.by(Sort.Order.desc("id"), Sort.Order.asc("email"))));
        System.out.println("findByNameWithPaging : " + userRepository.findByName("haril", PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")))).getContent());
        System.out.println("findByNameWithPaging : " + userRepository.findByName("haril", PageRequest.of(1, 1, Sort.by(Sort.Order.desc("id")))).getTotalElements());

    }

}