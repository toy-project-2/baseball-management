# baseball-management
패스트캠퍼스 토이프로젝트 2 - 야구관리프로그램

<br>

### ERD 설계 / DB connection 생성
#### 서용현, 한혜지
  * DBConnection

### 시스템 내 주요 기능 구현
#### 서용현
  - 야구장 등록
  - 야구장 목록 보기
    * Stadium
    * StadiumDAO
    * StadiumService
  - 팀 등록
  - 팀 목록 보기
    * Team
    * TeamDAO
    * TeamService
    *TeamRespDTO
#### 한혜지
  - 선수 등록
  - 팀별 선수 목록 보기
    * Player
    * PlayerDAO
    * PlayerService
  - 선수 퇴출 등록
  - 선수 퇴출 목록
    * OutPlayer
    * OutPlayerDAO
    * OutPlayerRespDTO
  - 포지션별 팀 야구선수 보기
    * PositionRespDTO

### 문자열 파싱받기
#### 서용현
  * BaseBallApp
  * UrlUtil
