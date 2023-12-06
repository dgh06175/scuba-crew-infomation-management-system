# 🤿 스쿠버 동아리원 정보 관리 시스템

Scuba Crew Information Management System
> SCIMS

## 🍳 사용 기술

`Java`, `JPA`, `MySQL`

## 📋 설계 목적

    1. 회원 정보 간편하게 조회
    2. 정보 일관성 유지
    3. 활동 계획 지원
    4. 동아리 운영 지원

## ⚙️ 기능 목록

### 메뉴 선택 기능
<br><img width="171" alt="image" src="https://github.com/dgh06175/scuba-member-infomation-management-system/assets/77305722/38d9ac75-7384-4e27-ae78-c32e0f9b0af3"><br>
- [x] 메인 화면에서 아래 기능들을 메뉴로 선택 가능하게 출력한다.
- [x] 메뉴 번호를 입력받고 해당 메뉴로 이동한다.

### 회원 정보 조회

- [x] 정보 조회 기능
  - [x] 인적사항 조회
      <br><img width="290" alt="image" src="https://github.com/dgh06175/scuba-member-infomation-management-system/assets/77305722/ad4673a2-51c1-41cf-9db3-1f472e8f0770"><br>

  - [x] 스쿠버 관련 정보 조회
      <br><img width="439" alt="image" src="https://github.com/dgh06175/scuba-member-infomation-management-system/assets/77305722/5b86cce5-7431-4192-9f78-8a33ffda8fa2"><br>

  - [x] 신체 정보 조회
      <br><img width="551" alt="image" src="https://github.com/dgh06175/scuba-member-infomation-management-system/assets/77305722/c158435b-d689-4a55-8ddb-01bfcc172f3f"><br>

  - [x] 자격증 별 인원 수 출력
      <br><img width="117" alt="image" src="https://github.com/dgh06175/scuba-member-infomation-management-system/assets/77305722/ac571371-dcf3-4c96-9f09-16f0ec18a79c"><br>

  - [x] 특정 로그수 넘은 인원 조회
      <br><img width="280" alt="image" src="https://github.com/dgh06175/scuba-member-infomation-management-system/assets/77305722/f877ca33-2406-43ba-a41b-394c67ddb184"><br>
      
  - [x] 특정 자격증 중에 특정 로그 이상 회원 조회
      <br><img width="245" alt="image" src="https://github.com/dgh06175/scuba-member-infomation-management-system/assets/77305722/06a207cc-01ee-4398-a98d-f7a1fb9124df"><br>

### 스쿠버 활동 계획

- [x] 진행할 활동 장소 선택
  - 개방수역
    - 자격증 취득
      - 인원 골라서 자격증 업데이트
      <br><img width="104" alt="image" src="https://github.com/dgh06175/scuba-member-infomation-management-system/assets/77305722/5ae22407-b704-46df-b4c9-99084ea2f59f"><br>
      <br><img width="105" alt="image" src="https://github.com/dgh06175/scuba-member-infomation-management-system/assets/77305722/9fe02333-d78b-4cd3-8c27-e9b48be7336d"><br>
      - 참여자 로그 수 증가
      <br><img width="217" alt="image" src="https://github.com/dgh06175/scuba-member-infomation-management-system/assets/77305722/3d6dcfd4-e2b2-484b-8be6-7fbf4504bb85"><br>
      <br><img width="113" alt="image" src="https://github.com/dgh06175/scuba-member-infomation-management-system/assets/77305722/7c3d89e3-1921-4065-9302-e89525531932"><br>
    - 펀다이빙
  - 제한수역
    - 참여자 수영장 교육 횟수 증가
    <br><img width="341" alt="image" src="https://github.com/dgh06175/scuba-member-infomation-management-system/assets/77305722/35311a83-2645-4b12-aca5-745233772b76"><br>

### 가두모집 기능

- [x] 지원서 출력
  <br><img width="984" alt="image" src="https://github.com/dgh06175/scuba-member-infomation-management-system/assets/77305722/0fa82de2-7d22-4f4a-905a-b865bf3f84a2"><br>

- [x] 합격시킬 사람 학번 입력 시 동아리원 정보 테이블로 이동
  <br><img width="331" alt="image" src="https://github.com/dgh06175/scuba-member-infomation-management-system/assets/77305722/605c1d52-8d54-4f92-940e-174782f18dcd"><br>

## 📊 테이블 명세
<br><img width="612" alt="image" src="https://github.com/dgh06175/scuba-member-infomation-management-system/assets/77305722/cb06e875-624f-4829-84c0-00c9fd99a348"><br>
<br><img width="930" alt="image" src="https://github.com/dgh06175/scuba-member-infomation-management-system/assets/77305722/c5c96979-1792-4d2d-9668-d05b46350e03"><br>


### 1. 가두모집 지원서

- 이름
- 학과
- 학번 (주요키)
- 학년
- 휴대폰 번호
- 주소
- 가입 목적
- 스쿠버 자격증 ID (외래키)
- 동아리 꼭 가입하고 싶은 이유

### 2. 동아리 회원 인적사항

- 이름
- 학과
- 학년
- 학번 (주요키)
- 전화번호
- 주소

### 3. 동아리 회원 스쿠버 경험 정보
- 학번 (외래키)
- 스쿠버 자격증 ID (외래키)
- 수영장 교육 횟수
- 로그 수

### 4. 동아리 회원 신체정보
- 학번 (외래키)
- 나이
- 성별
- 키
- 몸무게
- 발사이즈
- 건강 유의사항

### 5. 스쿠버 장소
- 장소 ID (주요키)
- 장소 이름
- 개방/제한수역

### 6. 자격증 정보
- 자격증 ID (주요키)
- 자격증 이름
- 강사 필요 여부
- 개방수역 인원 제한
- 제한수역 인원 제한

### 테이블 생성 MySQL 코드

```mysql
CREATE TABLE recruitment_application (
    name VARCHAR(50) NOT NULL,
    department VARCHAR(100) NOT NULL,
    student_id INT(10) PRIMARY KEY NOT NULL,
    grade INT(1) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    address VARCHAR(200) NOT NULL,
    purpose_of_joining TEXT,
    scuba_certification_id int,
    reason_for_joining TEXT
    FOREIGN KEY (certification_id) REFERENCES certification(certification_id)
);

CREATE TABLE club_member_information (
    name VARCHAR(50) NOT NULL,
    department VARCHAR(100) NOT NULL,
    grade INT(1) NOT NULL,
    student_id INT(10) PRIMARY KEY NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    address VARCHAR(200) NOT NULL
);

CREATE TABLE scuba_experience_information (
    student_id INT(10) NOT NULL,
    scuba_certification_id int,
    restricted_water_training_count INT,
    log_count INT,
    FOREIGN KEY (student_id) REFERENCES club_member_information(student_id),
    FOREIGN KEY (certification_id) REFERENCES certification(certification_id)
);

CREATE TABLE physical_information (
    student_id INT(10) NOT NULL,
    age INT NOT NULL,
    gender ENUM('MALE', 'FEMALE') NOT NULL,
    height INT NOT NULL,
    weight INT NOT NULL,
    shoe_size INT NOT NULL,
    health_notes TEXT,
    FOREIGN KEY (student_id) REFERENCES club_member_information(student_id)
);

CREATE TABLE dive_site (
    site_id INT PRIMARY KEY NOT NULL,
    site_name VARCHAR(100) NOT NULL,
    area_status ENUM('open', 'restricted') NOT NULL
);

CREATE TABLE certification (
    certification_id INT PRIMARY KEY NOT NULL,
    certification_name ENUM('NONE', 'OW', 'AOW', 'RESCUE', 'MASTER', 'INSTRUCTOR'),
    instructor_requirement BOOLEAN NOT NULL,
    open_water_limit INT NOT NULL,
    restricted_water_limit INT NOT NULL
);
```

## 클래스 구조 및 역할

```
├── Application.java
├── controller
│   ├── ActivityController.java : 스쿠버 활동 계획 컨트롤러
│   ├── MainController.java : 프로그램 중앙 컨트롤러
│   ├── MemberController.java : 회원 정보 조회 컨트롤러
│   └── RecruitmentController.java : 가두모집 화면 컨트롤러
├── database
│   ├── DatabaseManager.java : 데이터베이스 불러오고 업데이트 하는 기능
│   └── QueryExecutor.java : 질의 관련 기능
├── model
│   ├── Certification.java : certification 테이블과 매핑된 클래스
│   ├── ClubMemberInformation.java : club_member_information 테이블과 매핑된 클래스
│   ├── DiveSite.java : dive_site 테이블과 매핑된 클래스
│   ├── PhysicalInformation.java : physical_information 테이블과 매핑된 클래스
│   ├── RecruitmentApplication.java : recruitment_application 테이블과 매핑된 클래스
│   └── ScubaExperienceInformation.java : scuba_experience_information 테이블과 매핑된 클래스
├── service
│   ├── ActivityService.java : 스쿠버 활동 비즈니스 로직
│   ├── MemberService.java : 멤버 정보 조회 비즈니스 로직
│   └── RecruitmentService.java : 가두모집 비즈니스 로직
├── util
│   ├── InputUtil.java: 입력 도구
│   └── RetryUtils.java : 재시작 로직
└── view
    ├── ActivityView.java : 스쿠버 활동 계획 화면 출력, 메뉴를 선택하여 서비스 기능 실행
    ├── MainMenuView.java : 메인 화면 출력, 메뉴를 선택하여 알맞은 화면으로 이동
    ├── MemberInfoView.java : 회원 정보 메뉴 화면 출력, 메뉴를 선택하여 서비스 기능 (정보 조회) 실행
    ├── ErrorOutputView.java : 에러 메세지 출력
    └── RecruitmentView.java : 지원서 목록 전달 받아서 출력하고, 합격 인원의 학번들 입력받아서 반환
```
