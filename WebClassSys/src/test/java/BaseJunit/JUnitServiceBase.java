package BaseJunit;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by JetWang on 2016/10/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
        "classpath:spring-hibernate-test.xml",
        "classpath:spring-mvc-test.xml"})
public class JUnitServiceBase extends AbstractJUnit4SpringContextTests {

}
