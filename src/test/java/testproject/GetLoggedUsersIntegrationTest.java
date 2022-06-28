//package testproject;
//
//import com.amr.project.ProjectApplication;
//import com.amr.project.model.entity.User;
//import com.amr.project.service.repository.UserRepository;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import config.TestConfig;
//import io.restassured.RestAssured;
//import io.restassured.authentication.FormAuthConfig;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = { ProjectApplication.class, TestConfig.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
//public class GetLoggedUsersIntegrationTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Value("${local.server.port}")
//    int port = 8888;
//
//    private FormAuthConfig formConfig;
//    private String LOGGED_USERS_URL, SESSION_REGISTRY_LOGGED_USERS_URL;
//
//    //
//
//    @BeforeEach
//    public void init() {
//        User user = userRepository.findByEmail("test@test.com");
//        if (user == null) {
//            user = new User();
//            user.setPassword("test");
//            user.setEmail("test@test.com");
//            user.setActivate(true);
//            user.setUsing2FA(false);
//            userRepository.save(user);
//        } else {
//
//            userRepository.save(user);
//        }
//
//        RestAssured.port = port;
//        RestAssured.baseURI = "http://localhost";
//        LOGGED_USERS_URL = "";
//        SESSION_REGISTRY_LOGGED_USERS_URL = "/user";
//        formConfig = new FormAuthConfig("/login", "username", "password");
//    }
//
//    @Test
//    public void givenLoggedInUser_whenGettingLoggedUsersFromActiveUserStore_thenResponseContainsUser() {
//        final RequestSpecification request = RestAssured.given().auth().form("test@test.com", "test", formConfig);
//
//        final Map<String, String> params = new HashMap<>();
//        params.put("password", "test");
//
//        final Response response = request.with().params(params).get(LOGGED_USERS_URL);
//
//        assertEquals(200, response.statusCode());
//        assertTrue(response.body().asString().contains("test@test.com"));
//    }
//
//    @Test
//    public void givenLoggedInUser_whenGettingLoggedUsersFromSessionRegistry_thenResponseContainsUser() {
//        final RequestSpecification request = RestAssured.given().auth().form("test@test.com", "test", formConfig);
//
//        final Map<String, String> params = new HashMap<>();
//        params.put("password", "test");
//
//        final Response response = request.with().params(params).get(SESSION_REGISTRY_LOGGED_USERS_URL);
//
//        assertEquals(200, response.statusCode());
//        assertTrue(response.body().asString().contains("test@test.com"));
//    }
//
//}
