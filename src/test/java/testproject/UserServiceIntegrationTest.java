//package testproject;
//
//
//
//import com.amr.project.model.entity.User;
//import com.amr.project.service.abstracts.UserService;
//import com.amr.project.service.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//@Configuration
//@SpringBootConfiguration
//@EntityScan("com.amr.project.*")
//@ComponentScan("com.amr.project.*")
//@EnableJpaRepositories("com.amr.project.service.repository")
//public class UserServiceIntegrationTest {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//
//    @Test
//    public void givenNewUser_whenRegistered_thenCorrect()  {
//        User user = new User();
//        user.setPassword("test");
//        user.setEmail("test@test.com");
//        user.setActivate(true);
//        user.setUsing2FA(false);
//        userService.persist(user);
//
//        assertNotNull(user);
//        assertNotNull(user.getEmail());
//        assertEquals(user, user.getEmail());
//        assertNotNull(user.getId());
//    }
//
//
//
//
//
//
//
//}
