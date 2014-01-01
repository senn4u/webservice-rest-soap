import com.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Ben Yasin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class UserRepositoryTest {

    @Autowired
    private MongoTemplate mongoTemplate;
    private static Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Test
    public void test() {

        User user = new User();
        user.setName("Ben Yasin");
        user.setAge(28);
        user.setPosition("manager");
        user.setInterest("program");

        // save
        mongoTemplate.save(user);

        // now user object got the created id.
        LOGGER.info("1. user : " + user);

        // query to search user
        Query searchUserQuery = new Query(Criteria.where("name").is("Ben Yasin"));

        // find the saved user again.
        User savedUser = mongoTemplate.findOne(searchUserQuery, User.class);
        LOGGER.info("2. find - savedUser : " + savedUser);

        // update password
        mongoTemplate.updateFirst(searchUserQuery, Update.update("interest", "computer"), User.class);

        // find the updated user object
        User updatedUser = mongoTemplate.findOne(searchUserQuery, User.class);

        LOGGER.info("3. updatedUser : " + updatedUser);

        // delete
        mongoTemplate.remove(searchUserQuery, User.class);

        // List, it should be empty now.
        //List<User> listUser = mongoTemplate.findAll(User.class);
        //LOGGER.info("4. Number of user = " + listUser.size());
    }

}
