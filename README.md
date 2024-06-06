참고자료
- [baeldung - oauth2 login](https://www.baeldung.com/spring-security-5-oauth2-login)
- [docs.spring.io - oauth2](https://docs.spring.io/spring-security/reference/reactive/oauth2/index.html)

<br/>

3시간 정도는 잡고 해야 하는 일을 1시간 정도로 생각하고 있었다. 퇴근 후 운동 안하고 이거 바로 해야 하는 듯 해보인다. 은근히 자료도 찾아봐야 하고, 디버깅하면서 키/값 매핑이 어떻게 되는지도 일일이 노가다를 해봐야 함.
너무 쉽게 생각했었다!!!
- `응, 뭐 금방하겠지` 하다가 큰코 다쳤음

<br/>

## 2024.06.06
오늘은 현충일!! 오늘 적용한 내용들은 아래와 같다. Spring Security 를 너무 우습게 봤다. 간단한 Helloworld 처럼 금방할줄 알았던 내가 경솔했던 거지.
- r2dbc 환경에서 h2 가 잘 작동되도록 환경설정 + 테스트 코드 추가
- MemberEntity 테스트 코드 
- JwtAuthenticationManager 코드

그냥 id/password 인증을 JWT 기반으로 할 때는 쉬웠찌... OAuth 서비스 연동하려니 이것 저것 복잡하다.<br/>
<br/>

다음 번에 작업을 한번 또는 두세번 더 해야 한다. 다음 번에는 3시간 잡고 3시간 넘어가면 다음 턴으로 넘겨야 할 것 같다. 이런 방식으로 한 작업에 너무 병목되지 않도록 별도의 로스컷 같은 규칙을 세우기로 결정했다.
<br/>

현재 작업 중인 사이드 프로젝트는 OAuth2 인증서버, 게이트웨이 등 백엔드 코드는 모두<br/>
java 기반이 아닌 kotlin 기반으로 진행 예정이다.<br/>

kotlin 이 코드가 더 짧아지기도 하고, lombok 이 없기에 불필요한 고민이 없어진다. 물론 lombok 이 없을 때 직접 작성해야 하는 코드가 늘어나기는 하지만<br/>
lombok 은 어떤 언어에도 존재하지 않는 안티패턴이라는 걸 생각하면 안쓰는게 오히려 낫다.<br/>
<br/>

kotlin 을 사용하면 굳이 webflux 를 사용하지 않아도 코루틴으로 비동기 논블로킹 연산을 동기 연산처럼 사용가능해진다.<br/>
이게 가능한 이유는 밑에 단에서 돌고 있는 CoroutineDispatcher 덕분이다.<br/>
최근 빅테크 회사들은 kotlin 도 쓰고 webflux 를 같이 쓴다. webflux 로 어떤 데이터를 fetch 해올 때 ...block() 과 같이 가져오더라도 코루틴으로 감싸면 비동기적으로 동작하게 된다구. 잊지 말라규.<br/>
<br/>

참고할 자료 모음
- [OAuth2 Webflux](https://docs.spring.io/spring-security/site/docs/5.1.1.RELEASE/reference/html/webflux-oauth2.html)
- [OAuth2 Webflux](https://docs.spring.io/spring-security/reference/reactive/oauth2/index.html#oauth2-client)
- [Spring Security OAuth Login with Webflux](https://www.baeldung.com/spring-oauth-login-webflux)
- [Spring WebFlux and OAuth 2.0](https://rj93.medium.com/spring-webflux-and-oauth-2-0-47e0c32c0a7a)
- [Spring Security 5 - OAuth2 Login](https://www.baeldung.com/spring-security-5-oauth2-login)


