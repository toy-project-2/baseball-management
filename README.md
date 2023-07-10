# baseball-management
패스트캠퍼스 토이프로젝트 2 - 야구관리프로그램
<br>

### ERD 설계

![image](https://github.com/toy-project-2/baseball-management/assets/35757620/f3ae62fc-361c-419e-a2fe-4c6ea3f4cc66)

#### Stadium
```sql
create table stadium_tb (
 id int primary key auto_increment,
 name varchar(20) not null,
 created_at timestamp default now()
);
```

#### Team
```sql
create table team_tb (
 id int primary key auto_increment,
 stadium_id int not null, 
 name varchar(10) not null,
 created_at timestamp default now(),
 
 foreign key (stadium_id) references stadium_tb (id)
);
```

#### player
```sql
create table player_tb (
 id int primary key auto_increment,
 team_id int,
 name varchar(10) not null,
 position varchar(10) not null,
 created_at timestamp default now(),
 
 foreign key (team_id) references team_tb (id)
);
```

#### OutPlayer
```sql
create table out_player_tb (
 id int primary key auto_increment,
 player_id int not null,
 reason varchar(20) not null,
 created_at timestamp default now(),
 
 foreign key (player_id) references player_tb (id)
); 
```

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
