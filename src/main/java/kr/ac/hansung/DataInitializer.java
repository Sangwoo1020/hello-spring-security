package kr.ac.hansung;

import kr.ac.hansung.entity.Product;
import kr.ac.hansung.entity.Role;
import kr.ac.hansung.entity.User;
import kr.ac.hansung.repository.ProductRepository;
import kr.ac.hansung.repository.RoleRepository;
import kr.ac.hansung.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) {
        Role userRole = roleRepository.findByName("ROLE_USER")
            .orElseGet(() -> roleRepository.save(new Role("ROLE_USER")));

        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
            .orElseGet(() -> roleRepository.save(new Role("ROLE_ADMIN")));

        if (!userRepository.existsByEmail("admin@hansung.ac.kr")) {
            User admin = new User();
            admin.setEmail("admin@hansung.ac.kr");
            admin.setPassword(passwordEncoder.encode("admin1234"));
            admin.getRoles().add(userRole);
            admin.getRoles().add(adminRole);
            userRepository.save(admin);
            log.info("초기 관리자 계정 생성: admin@hansung.ac.kr / admin1234");
        }

        if (productRepository.count() == 0) {
            productRepository.save(new Product("Spring Boot 4 완벽 가이드", 35000, "Spring Boot 4 + JPA + Security 실습서", 50));
            productRepository.save(new Product("Spring Security 7 핵심 원리", 28000, "세션·JWT·OAuth2 기반 보안 구현", 30));
            productRepository.save(new Product("JPA 프로그래밍 실전", 32000, "Hibernate 7 기반 ORM 마스터", 25));
            productRepository.save(new Product("Thymeleaf 완전 정복", 22000, "서버사이드 템플릿 엔진 가이드", 40));
            productRepository.save(new Product("React 입문서", 27000, "프론트엔드 기초 프로젝트 실습", 35));
            productRepository.save(new Product("Docker & Kubernetes", 38000, "배포부터 오케스트레이션까지", 15));
            productRepository.save(new Product("Git & GitHub 협업 가이드", 18000, "팀 단위 버전 관리 전략", 60));
            productRepository.save(new Product("MySQL 성능 최적화", 33000, "인덱스·쿼리 튜닝 실전", 20));
            productRepository.save(new Product("Node.js 개발 전략", 31000, "백엔드 서비스 아키텍처", 10));
            productRepository.save(new Product("AWS 클라우드 입문", 42000, "클라우드 인프라 설계와 운영", 5));

            productRepository.save(new Product("자바의 정석 4판", 112000, "전공서적 필수 교재", 100));
            productRepository.save(new Product("현대물리학 개념서", 89000, "공대생 필독서", 45));
            productRepository.save(new Product("미적분학", 180000, "수학과 1학년 필수", 8));
            productRepository.save(new Product("컴퓨터 구조", 299000, "학부 필수 과목 교재", 12));
            productRepository.save(new Product("운영체제", 420000, "컴공 전공 심화", 3));
            productRepository.save(new Product("프리미엄 키보드 X", 123000, "무접점 스위치 입문", 75));
            productRepository.save(new Product("게이밍 마우스 Pro", 159000, "정밀 센서 기반 e스포츠", 65));
            productRepository.save(new Product("기계식 키보드 V5", 89000, "타건감 중심 입문 모델", 38));
            productRepository.save(new Product("핸드폰 스탠드 2P", 45000, "휴대·거치 겸용", 120));
            productRepository.save(new Product("맥북 어댑터 호환 9v", 65000, "맥북 충전용 자재", 55));
            log.info("샘플 상품 20건 생성 완료");
        }
    }
}
