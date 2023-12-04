# 🤿 스쿠버 동아리원 정보 관리 시스템

## 🍳 사용 기술

`Java`, `MySQL`

## 📋 설계 목적

    1. 활동 계획 및 동아리 운영 지원
    2. 회원 정보 일관성 유지

## ⚙️ 기능 목록

### 메뉴 선택 기능
- [x] 메인 화면에서 아래 기능들을 메뉴로 선택 가능하게 출력한다.
- [x] 메뉴 번호를 입력받고 해당 메뉴로 이동한다.
  - 올바른 숫자가 아닐 경우 예외 발생

### 모집 기능

- [x] 지원 내역 출력 `recruitment_application`
  - [x] 이름, 학과, 학번, 학년, 지원목적, 자격증여부, 지원이유
- [x] 합격시킬 사람 학번 입력 시 동아리원 정보 테이블로 이동
  - 이미 명단에 존재할 경우 예외 발생
  - 올바르지 않은 학번일 경우 예외 발생

### 동아리 회원 정보 (인적 사항, 스쿠버 관련 정보, 신체 정보) 조회 기능

- [x] 정보 조회 기능
  - [x] 인적사항 조회 `club_member_information`
  - [x] 스쿠버 관련 정보 조회 `scuba_sxperience`
    - 스쿠버 정보 앞에 이름 같이 출력
  - [x] 신체 정보 조회 `physical_information`
    - 신체 정보 앞에 이름 같이 출력
  - [x] 특정 자격증 인원 조회
  - [x] 자격증 별 인원 수 출력
  - [x] 특정 로그수 넘은 인원 조회
  - 추후 정보 업데이트 기능 구현

### 동아리 활동 기능

- [x] 진행할 활동 장소 선택 `dive_site`
  - 개방수역
    - 자격증 취득
      - 무슨 자격증인지 선택
    - 펀다이빙
  - 제한수역
- [x] 동아리원 목록 출력후 활동 가는 인원 선택
- [x] 활동 장소에 알맞은 필요사항 출력
  - 자격증 비교 후 인솔강사 몇명 함께해야하는지 출력 `certification`
  - 어떤 사이즈의 슈트 몇개 필요한지
  - 어떤 사이즈의 핀 몇개 필요한지
- [x] 활동 완료 시 동아리 회원 스쿠버 활동 업데이트 기능
  - 개방수역
    - 참여자 로그 수 증가
    - 자격증 취득 과정 (오픈, 어드, 레스큐, 마스터)
      - 인원 골라서 자격증 업데이트
  - 제한수역
    - 참여자 수영장 교육 횟수 증가

- 추후 회비 관련 기능 추가

## 📊 테이블 명세
<img width="930" alt="image" src="https://github.com/dgh06175/scuba-member-infomation-management-system/assets/77305722/c5c96979-1792-4d2d-9668-d05b46350e03">

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
</details>

## 클래스 구조

### View

- [x] **메인 화면** : MainMenuView
  - 메인 화면 출력, 메뉴 번호로 입력받고 컨트롤러에게 전달


- [x] **지원서 관리 화면** : RecruitmentView
  - 지원서 목록 전달 받아서 출력하고, 합격 인원의 학번들 입력받아서 반환


- [x] **동아리 회원 정보 조회 화면** : MemberInfoView
  - 회원 정보 메뉴 화면 출력, 메뉴 번호로 입력받고 알맞은 화면 출력


- [x] **스쿠버 활동 계획 화면** : ActivityPlanView
  - 활동 장소 목록 전달 받아서 출력
  - 활동 구분
  - 활동 완료에 따른 후처리


### Controller

- [x] **프로그램 중앙 컨트롤러** : MainController
  - 프로그램 시작
  - 상위 레벨 메뉴 관리
  - 하위 컨트롤러 조정
  - 프로그램 종료
- [x] **지원서 관리 화면 컨트롤러** : RecruitmentController
- [x] **회원 정보 조회 컨트롤러** : MemberController
- [x] **스쿠버 활동 계획 컨트롤러** : ActivityController

### Model

- [x] **DB 정보들** JPA 사용해서 엔티티 정의

### Service

- [x] **비즈니스 로직 담당**

### database

- [x] **데이터베이스 불러오는 기능** : DatabaseManager
- [x] **질의 관련 기능** : QueryExecutor

### util

- [x] **입력 검증** : InputParser
- [x] **재시작 로직** : RetryUtils
