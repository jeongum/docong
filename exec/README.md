# ๐ ์ค๋นํ๊ธฐ

1. Git clone ๋ฐ๊ธฐ

```bash
git clone https://lab.ssafy.com/s06-s-project/S06P21S003.git
```



2. ๋ฐ์ดํฐ ๋ฒ ์ด์ค ์ค๋น

- Mariadb ๋ค์ด๋ก๋ [๋ฐ๋ก๊ฐ๊ธฐ](https://mariadb.org/download/?t=mariadb&p=mariadb&r=10.6.7&os=windows&cpu=x86_64&pkg=msi&m=harukasan)

<img width="1000" alt="image" src="https://user-images.githubusercontent.com/44612896/162108219-c211e1f0-d880-44a4-99f2-e3790ef143c1.png">

- ์ฌ์ฉ์์ ์ด์์ฒด์ ์ ๋ง์ถ์ด ๋ค์ด๋ก๋



3. **Backend** application.properties ์ค์ 

```xml
# ๋ก์ปฌ์์ ์ฌ์ฉํ  application yml ์ค์  ํ์ผ
# ':' ๋ค์ ์ค์ ๊ฐ์ ์๋ ฅํ  ๋๋ ๋ฐ๋์ ':' ๋ค์์ ๊ณต๋ฐฑ์ด ํ์
# ์ค์ ํ  ๊ฐ๋ค์ ๋ ๋ฒจ ์ฃผ์ (ex. spring.datasource.url ์ ์๋ ฅํ  ๊ฒฝ์ฐ, datasource: ๋ spring: ๋ณด๋ค ์ฐ์ธก์ผ๋ก ํ ํญ ์ด๋. url: ์ datasource: ๋ณด๋ค ์ฐ์ธก์ผ๋ก ํ ํญ ์ด๋)

# ๊ธฐ๋ณธ ๋ก๊ทธ ๋ ๋ฒจ ์ค์ 
logging:
  level:
    root: info
    com.ssapy.api: debug
    org.hibernate.type.descriptor.sql: trace   # trace

spring:
  profiles:
    # application-aws.yml ์ถ๊ฐ include
    include:
      - aws
  messages:
    basename: i18n/exception
    encoding: UTF-8
  # JWT ํ ํฐ์ ์ฌ์ฉํ  secret ํค๊ฐ (์์์ ๋๋ค ํค๊ฐ)
  jwt:
    secret: DvqcGn8mnFjqSL4a
  # JPA ๊ธฐ๋ณธ ์ค์ 
  jpa:
    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect
    properties.hibernate:
      # ์ฌ์์ ์ JPA Entity(DB ํ์ด๋ธ ๋ฐ์ดํฐ)๋ฅผ ์๋ก ์์ฑํ ์ง ์ฌ๋ถ (create:๊ธฐ์กด๋ฐ์ดํฐ ์ญ์  ํ ์ ๊ท ์์ฑ, udpate:์ ๊ท ๋ฐ์ดํฐ๋ง ์๋ฐ์ดํธ, none:์๋ฌด ์คํ๋ ํ์ง ์์)
      hbm2ddl.auto: update
      format_sql: true
      show_sql: true
      use_sql_comments: true
    generate-ddl: true
    open-in-view: false
  freemarker:
    checkTemplateLocation: false

  # ๋ฐ์ดํฐ ๋ฒ ์ด์ค ์ฐ๊ฒฐ ์ค์ 
  datasource:
		#๋ฐ์ดํฐ๋ฒ ์ด์ค ์ฃผ์ ์ด๋ฆ
    url: jdbc:mariadb://๋ฐ์ดํฐ๋ฒ ์ด์ค์ฃผ์:ํฌํธ๋ฒํธ/๋ฐ์ดํฐ๋ฒ ์ด์ค์ด๋ฆ?characterEncoding=utf-8&createDatabaseIfNotExist=true 
    driver-class-name: org.mariadb.jdbc.Driver
    username: #์์ด๋
    password: #ํจ์ค์๋
  flyway:
    enabled: false
  config:
    activate:
      on-profile: local
    use-legacy-processing: true
  servlet:
    multipart:
      file-size-threshold: 15MB
      # ํ๋ก์ ํธ ํ๊ฒฝ์ upload ํ์ผ์ ์ ์ฅํ  ๊ฒฝ๋ก
      location: C:\Develop\projects\ssafy\upload
      max-file-size: 100MB
      max-request-size: 100MB

  # ๋ฉ์ผ ์ ์ก ์ ์ฌ์ฉํ  ์ค์ ๊ฐ
  mail:
    host: smtp.gmail.com
    port: 587
    username:  #์ด๋ฉ์ผ ์ฃผ์
    to-name:  #์ด๋ฉ์ผ ์ฃผ์
    #docong1234!
    password:  #๋น๋ฐ๋ฒํธ
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true

server:
  # ํ๋ก์ ํธ ํ๊ฒฝ์ ํฌํธ ์ค์ 
  port: 8080 # ํฌํธ ์์  ํ์
  domain: http:127.0.0.1
  servlet:
    session:
      timeout: 43200m      # 60m * 24h * 30d
      cookie:
        max-age: 43200m    # 60m * 24h * 30d
        name: SID
        http-only: true
        secure: true
    context-path: /api
  max-http-header-size: 3145728

aes256:
  key: WZsExuBV3GSQ55Uf

# ํธ์ฌ ์๋ฆผ ์ ์ก ์ ํ์ํ firebase json ํ์ผ์ ์์น
app:
  firebase-config: ssafy-e6f74-firebase-adminsdk-2911y-cfd0f23f96.json

# swagger์์ ํ์คํธ ํ  ๋์ host
swagger:
  host: http://localhost:8080

# notification ๋ถ๋ถ
notification:
  mattermost:
    enabled: true # mmSender๋ฅผ ์ฌ์ฉํ  ์ง ์ฌ๋ถ, false๋ฉด ์๋ฆผ์ด ์ค์ง ์๋๋ค
    webhook-url: # Webhook URL์ ๊ธฐ์
    channel: # ๊ธฐ๋ณธ ์ค์ ํ ์ฑ๋์ด ์๋ ๋ค๋ฅธ ์ฑ๋๋ก ๋ณด๋ด๊ณ  ์ถ์ ๋ ๊ธฐ์ํ๋ค
    pretext: # attachments์ ์๋จ์ ๋์ค๊ฒ ๋๋ ์ผ๋ฐ ํ์คํธ ๋ฌธ์
    color: # attachment์ ์ผ์ชฝ ์ฌ์ด๋ ์ปฌ๋ฌ. default=red
    author-name: Back-End Exception # attachment์ ์๋จ์ ๋์ค๋ ์ด๋ฆ
    author-icon: https://media.vlpt.us/images/ayoung0073/post/2db5c70c-d494-4cca-ad58-7b4eaabc3c9a/springboot.jpeg # author-icon ์ผ์ชฝ์ ๋์ฌ ์์ด์ฝ์ url๋งํฌ
    footer: # attachment์ ํ๋จ์ ๋์ฌ ๋ถ๋ถ. default=ํ์ฌ ์๊ฐ

# ์ํธํ
encrypt:
  keyString: docongjiraapitokenencode
```



4. **Frontend** package.json ๋ง์ง๋ง ๋ถ๋ถ์ ํ๋ก์ ์ถ๊ฐ

```json
{
  "name": "docong",
  "version": "0.1.0",
  "private": true,
  "dependencies": {
    "@asseinfo/react-kanban": "^2.2.0",
    "@date-io/date-fns": "^2.13.1",
    "@mui/icons-material": "^5.5.0",
    "@mui/lab": "^5.0.0-alpha.73",
    "@mui/material": "^5.5.0",
    "@mui/styled-engine-sc": "^5.4.2",
    "@sentry/react": "^6.18.2",
    "@sentry/tracing": "^6.18.2",
    "@testing-library/jest-dom": "^5.16.2",
    "@testing-library/react": "^12.1.3",
    "@testing-library/user-event": "^13.5.0",
    "@types/date-fns": "^2.6.0",
    "@types/jest": "^27.4.1",
    "@types/node": "^17.0.21",
    "@types/react": "^17.0.40",
    "@types/react-dom": "^17.0.13",
    "@types/react-redux": "^7.1.23",
    "@types/styled-components": "^5.1.24",
    "@use-it/interval": "^1.0.0",
    "apexcharts": "^3.33.2",
    "axios": "^0.26.1",
    "jwt-decode": "^3.1.2",
    "node-sass": "^7.0.1",
    "polished": "^4.1.4",
    "react": "^17.0.2",
    "react-apexcharts": "^1.4.0",
    "react-async": "^10.0.1",
    "react-dom": "^17.0.2",
    "react-google-login": "^5.2.2",
    "react-icons": "^4.3.1",
    "react-redux": "^7.2.6",
    "react-router-dom": "^6.2.2",
    "react-scripts": "5.0.0",
    "redux": "^4.1.2",
    "redux-devtools-extension": "^2.13.9",
    "redux-logger": "^3.0.6",
    "redux-persist": "^6.0.0",
    "redux-saga": "^1.1.3",
    "styled-components": "^5.3.3",
    "typesafe-actions": "^5.1.0",
    "typescript": "^4.6.2",
    "web-vitals": "^2.1.4"
  },
  "scripts": {
    "start": "react-scripts start",
    "build": "react-scripts build",
    "test": "react-scripts test",
    "eject": "react-scripts eject"
  },
  "eslintConfig": {
    "extends": [
      "react-app",
      "react-app/jest"
    ]
  },
  "browserslist": {
    "production": [
      ">0.2%",
      "not dead",
      "not op_mini all"
    ],
    "development": [
      "last 1 chrome version",
      "last 1 firefox version",
      "last 1 safari version"
    ]
  },
  "resolutions": {
    "@mui/styled-engine": "npm:@mui/styled-engine-sc@latest"
  },
  "proxy": "http://๋ฐฑ์๋์๋ฒ์ฃผ์:ํฌํธ๋ฒํธ"
}
```



5. **Frontend** ๋ชจ๋ ๋ค์ด๋ก๋

```bash
# frontend/docong ํด๋๋ก ์ด๋
cd frontend/docong

yarn install
```



6. ํ๋ผ์คํฌ ํจํค์น ์ค์น

```bash
ํ์ํ  ๊ฒฝ์ฐ ์ด ๋จ๊ณ์์ ๊ฐ์ํ๊ฒฝ ๋ง๋ญ๋๋ค.(์ค๋ช์ ๊ฑด๋๋๋๋ค.)
pip install virtualenv
virtualenv venv

pip3 install flask
```





# ๐ ์คํํ๊ธฐ



**Back-end**

- [Backend] (Option) Spring boot๋ฅผ build(jar ํ์ผ ์์ฑ)

```plaintext
# backend/docong ํด๋๋ก ์ด๋ํด์
cd backend/docong
mvn -B -DskipTests -f backend
```

- ๋ฐฑ์๋ ์คํ

  - ์์ฑํ jar ํ์ผ ์คํ

    ```plaintext
    java -jar [filename].jar
    ```

  - ํน์ war ํ์ผ ์์ฑํ์ง ์๊ณ  demon์ผ๋ก ๋ก์ปฌ์์ ์คํํ๊ณ  ์ถ๋ค๋ฉด STS์ ๊ฐ์ IDEA์์ Spring boot Run์ ์คํํ๊ฑฐ๋ ์๋ ๋ช๋ น์ด๋ฅผ ํตํด ์คํ

    ```plaintext
    mvn spring-boot:run
    ```



**Front-end**

```bash
# frontend/docong ํด๋๋ก ์ด๋
cd frontend/docong

yarn start
```



**ML**

```
# ml ํด๋๋ก ์ด๋
cd ml

python3 app.py 
```