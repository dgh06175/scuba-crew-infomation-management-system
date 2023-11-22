# 🤿 스쿠버 동아리원 정보 관리 시스템

## 🍳 사용 기술

`Java`, `MySQL`

## 📋 설계 목적

    1. 활동 계획 및 동아리 운영 지원
    2. 회원 정보 일관성 유지


## ⚙️ 기능 목록

### 가두모집 기능

- [ ] 지원 내역을 보고 합격, 탈락 여부 결정 가능
- [ ] 합격 시 동아리원 테이블로 이동

### 동아리 회원 정보 (인적 사항, 스쿠버 관련 정보, 신체 정보)

- [ ] 정보 조회 기능 (세가지 중 골라서 출력)

### 동아리 활동 기능

- [ ] 활동 장소에 알맞은 필요사항 출력
  - 자격증 비교 후 인솔강사 몇명 가야하는지
  - 어떤 사이즈의 슈트 몇개 필요한지
  - 어떤 사이즈의 핀 몇개 필요한지


- [ ] 활동 완료 시 동아리 회원 스쿠버 활동 업데이트 기능
  - 개방수역
    - 참여자 로그 수 증가
    - 자격증 취득 과정 (오픈, 어드, 레스큐, 마스터) 일 경우 자격증 업데이트
  - 제한수역일 경우 참여자 수영장 교육 횟수 증가


## 📊 테이블 명세

<img width="1024" alt="image" src="https://github.com/dgh06175/scuba-member-infomation-management-system/assets/77305722/1d7777ee-809e-43ef-96a9-f8bf31bf55bd">

### 1. 가두모집 지원서

- 이름
- 학과
- 학번 (주요키)
- 학년
- 휴대폰 번호
- 주소
- 가입 목적
- 스쿠버다이빙 자격증 여부
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
- 스쿠버 자격증
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

<details>
<summary>테이블 생성 MySQL 코드 보기</summary>
<div markdown="1">

```mysql
CREATE TABLE recruitment_application (
    name VARCHAR(50) NOT NULL,
    department VARCHAR(100) NOT NULL,
    student_id INT(10) PRIMARY KEY NOT NULL,
    grade INT(1) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    address VARCHAR(200) NOT NULL,
    purpose_of_joining TEXT,
    scuba_certification_name ENUM('NONE', 'OW', 'AOW', 'RESCUE', 'MASTER', 'INSTRUCTOR'),
    reason_for_joining TEXT
);

CREATE TABLE club_member_information (
    name VARCHAR(50) NOT NULL,
    department VARCHAR(100) NOT NULL,
    grade INT(1) NOT NULL,
    student_id INT(10) PRIMARY KEY NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    address VARCHAR(200) NOT NULL
);

CREATE TABLE scuba_experience (
    student_id INT(10) NOT NULL,
    scuba_certification_name ENUM('NONE', 'OW', 'AOW', 'RESCUE', 'MASTER', 'INSTRUCTOR'),
    restricted_water_training_count INT,
    log_count INT,
    FOREIGN KEY (student_id) REFERENCES club_member_information(student_id)
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
</div>
</details>
