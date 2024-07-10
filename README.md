# NKLCB Backend

NKLCB Backend 는 테크 블로그 크롤링 서비스인 "네카라쿠배"의 백엔드 서버입니다.

### [API 명세서 바로가기](http://52.79.94.51/swagger-ui/index.html)

## Stack

- Java 17
- Gradle
- Spring Boot 3
- JPA
- MySql
- Spring Scheduler

## Project Structure

- `client/`: 클라이언트와 관련된 모듈(grpc)
- `core/`: 애플리케이션 핵심 모듈(api 서버, 스케줄러)
- `storage/`: 데이터베이스 및 JPA 관련 모듈
- `script/`: 무중단 배포를 위한 스크립트 파일

## Getting Started

1. Clone the repo
    ```sh
    git clone https://github.com/kimday0326/nklcb-backend.git
    ```
2. Move to the project directory
    ```sh
    cd nklcb-backend
    ```
3. Build the project
    ```sh
    ./gradlew build
    ```
4. Make an application-secret.yaml
    ```sh
    cd core/core-api/src/main/resources
    touch application-secret.yaml
    ```
   application-secret.yaml must have the following format:
    ```yaml
    DATABASE_URL: {DATABASE_URL}
   DATABASE_NAME: {DATABASE_NAME}
   DATABASE_PORT: {DATABASE_PORT}
   DATABASE_USERNAME: {DATABASE_USERNAME}
   DATABASE_PASSWORD: {DATABASE_PASSWORD}
   CRAWLING_GRPC_SERVER_ADDRESS: {CRAWLING_GRPC_SERVER_ADDRESS}
   ```
5. Run the project

## LICENSE

이 프로젝트는 MIT 라이센스 하에 배포됩니다. 자세한 내용은 [LICENSE](LICENSE) 파일을 참조하세요.
