# schedule_api

## 프로젝트 개요

프로젝트명: 일정 관리 API 구현 Ver2.

본 API는 회원 가입을 기반으로 일정을 등록하고, 조회, 수정, 삭제할 수 있는 기능을 제공합니다.

## Git 컨벤션
| 태그       | 설명                                       |
|------------|--------------------------------------------|
| `feat`     | 새로운 기능 추가                           |
| `fix`      | 버그 수정                                  |
| `refactor` | 코드 리팩토링 (기능 변화 없이 구조 개선)   |
| `docs`     | 문서 수정 (예: README, 주석 등)            |
| `chore`    | 기타 설정, 빌드, 패키지 등                 |

## ERD
![erd.PNG](docs/erd.PNG)

## API 명세서

### 회원가입

| 항목             | 내용                                |
|----------------|-------------------------------------|
| URL            | `/api/v2/members/signup`            |
| **Method**     | `POST`                              |
| **설명**         | 새로운 회원을 등록합니다.             |
| **인증 필요 여부** | 로그인 없이 접근 가능               |
| **요청 Content-Type** | `application/json`                |


#### Request Body

```json
{
  "username": "test1",
  "email": "test1@example.com",
  "password": "ehrbs0903."
}
```


| 필드명   | 타입    | 필수 여부 | 제약 조건                             |
|----------|---------|-------|-----------------------------------|
| username | String  | O     | 2~10자, 공백 불가                      |
| email    | String  | O     | 이메일 형식 준수 (`kimdk0903@naver.com`) |
| password | String  | O     | 8~16자, 영문+숫자+특수문자 1개 이상 포함 필요     |


#### Response Body (201 Created)

```json
{
  "id": 1,
  "username": "test1",
  "email": "test1@example.com",
  "modifiedAt": "2025-05-25T15:00:00"
}
```
| 필드명     | 타입   | 설명         |
|------------|--------|------------|
| id         | Long   | 생성된 유저의 ID |
| username   | String | 유저명        |
| email      | String | 이메일 주소     |
| modifiedAt | String | 마지막 수정 일시  |

#### Error Responses

| 상황                            | 상태코드 | 에러 코드             | 메시지 예시                                     |
|---------------------------------|----------|------------------------|--------------------------------------------------|
| 유효성 검증 실패                | 400      | `VALIDATION_FAILED`    | `"username은 최소 2자 이상이어야 합니다."`       |
| 이메일 형식 오류                | 400      | `VALIDATION_FAILED`    | `"이메일 형식이 올바르지 않습니다."`             |
| 중복 이메일 (이미 존재할 경우) | 409      | `EMAIL_ALREADY_EXISTS` | `"이미 사용 중인 이메일입니다."`                 |

---

### 로그인

| 항목             | 내용                                |
|----------------|-------------------------------------|
| URL            | `/api/v2/members/login`            |
| **Method**     | `POST`                              |
| **설명**         | 이메일과 비밀번호를 통해 로그인합니다.             |
| **인증 필요 여부** | 로그인 없이 접근 가능               |
| **요청 Content-Type** | `application/json`                |


#### Request Body

```json
{
  "email": "test1@example.com",
  "password": "ehrbs0903."
}
```


| 필드명   | 타입    | 필수 여부 | 제약 조건                             |
|----------|---------|-------|-----------------------------------|
| email    | String  | O     | 이메일 형식 준수 (`kimdk0903@naver.com`) |
| password | String  | O     | 8~16자, 영문+숫자+특수문자 1개 이상 포함 필요     |


#### Response Body (200 OK)

```json
{
  "id": 1,
  "username": "test1",
  "email": "test1@example.com"
}
```
| 필드명     | 타입   | 설명         |
|------------|--------|------------|
| id         | Long   | 생성된 유저의 ID |
| username   | String | 유저명        |
| email      | String | 이메일 주소     |

#### Error Responses

| 상황                            | 상태코드 | 에러 코드             | 메시지 예시                                     |
|---------------------------------|----------|-----------------------|--------------------------------------------------|
| 존재하지 않는 이메일                | 401      | `EMAIL_NOT_FOUND`    | `"해당 이메일의 회원이 존재하지 않습니다."`       |
| 비밀번호 불일치                | 401      | `INVALID_PASSWORD`   | `"비밀번호가 일치하지 않습니다."`             |
| 유효성 검증 실패 (@Valid 오류) | 400      | `VALIDATION_FAILED` | `"이메일 형식이 올바르지 않습니다."`                 |

---

## 공통 에러 응답 형식

모든 API에서 오류 발생 시, 아래와 같은 JSON 포맷으로 에러가 응답됩니다.

```json
{
  "code": "에러코드",
  "message": "에러 메시지",
  "status": 401,
  "path": "/api/v2/요청한-경로",
  "errorTime": "2025-05-25T15:05:30"
}
```
| 필드명    | 타입     | 설명                                        |
|-----------|--------|---------------------------------------------|
| code      | String | 에러 코드 (`EMAIL_ALREADY_EXISTS` 등)          |
| message   | String | 사용자에게 보여줄 에러 메시지                |
| status    | int    | HTTP 상태 코드 (`400`, `401`, `409` 등)    |
| path      | String | 요청한 URI 경로                             |
| errorTime | LocalDateTime  | 에러 발생 시각             |
