# Description

spring boot 기반의 기본 쿼리 생성기 프로젝트 입니다.

# Philosophy

백앤드 비즈니스 로직을 작성할 때 반복적으로 사용하는 쿼리들을 자동 생성해 줌으로써 개발 생산성을 높이기 위함입니다.

# Configurations

이 application 을 실행하기 위해 필요한 설정입니다.

## `application.properties` files

project root 에 application.properties 파일을 생성하거나 환경변수를 통한 설정이 가능합니다.

| Name | Description | Default |
| :--: | -- | :--: |
| MYSQL_DATASOURCE_HOST | 사용할 host url | localhost |
| MYSQL_DATASOURCE_PORT | 사용할 port | 3306 | 
| MYSQL_DATASOURCE_USERNAME | 사용자명 | root | 
| MYSQL_DATASOURCE_PWD | 사용자 비밀번호 | - | 
| MYSQL_DATASOURCE_DBNAME | 데이터베이스명 | - | 

# Getting started

## Run

application.properties 설정 후 실행파일을 생성합니다.

```bash
# jar 파일을 생성합니다.
mvn package
```

```bash
# jar 파일을 실행합니다.
java -jar target/mysql-demo-0.0.1-SNAPSHOT.jar
```

## Docker 이미지 생성 및 실행

```bash
# Dockerfile이 있는 디렉토리에서 다음 명령어를 실행합니다.(맨 뒤에 '.'을 꼭 찍어주세요)
docker build -t mysql-demo:v1.1 .
```

```bash
# 컨테이너를 생성하기 위해서는 아래와 같이 -e 옵션을 추가하여 명령어를 실행합니다. 
# (환경변수에서 알 수 있듯이 이 컨테이너가 제대로 실행되기 위해서는 localhost:3306에 데이터베이스가 셋팅되어 있어야 합니다.)
docker run -d -p 5000:8080 -e MYSQL_DATASOURCE_HOST=localhost -e MYSQL_DATASOURCE_PORT=3306 -e MYSQL_DATASOURCE_USERNAME=root -e MYSQL_DATASOURCE_PWD=password -e MYSQL_DATASOURCE_DBNAME=test_db --name mysql-demo mysql-demo:v1.1
```

