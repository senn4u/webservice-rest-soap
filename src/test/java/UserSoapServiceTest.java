import com.webservice.soap.UserSoapService;
import junit.framework.Assert;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @author Ben Yasin
*/
public class UserSoapServiceTest {

    private UserSoapService userWebServiceProxy;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserSoapServiceTest.class);

    @Before
    public void creatClient() {
        String address = "http://localhost:8080/soap/userSoapService";

        JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
        proxyFactory.setAddress(address);
        proxyFactory.setServiceClass(UserSoapService.class);
        userWebServiceProxy = (UserSoapService) proxyFactory.create();
    }

    @Test
    public void TestSoap(){
        long result = userWebServiceProxy.countUser();
        LOGGER.info("test soap count user result is " + result);

        Assert.assertNotNull(result);
    }
}
