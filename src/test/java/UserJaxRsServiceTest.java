import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import static junit.framework.Assert.assertEquals;

/**
* @author Ben Yasin
*/
public class UserJaxRsServiceTest {

    private Client client;
    private String SERVER_ROOT_URI = "http://localhost:8080/jaxrs/user/";

    private static final Logger LOGGER = LoggerFactory.getLogger(UserJaxRsServiceTest.class);

    @Before
    public void creatClient() {
       client = Client.create();
    }

    @Test
    public void TestGetXML(){
        WebResource webResource = client.resource("http://localhost:8080/jaxrs/user/xml");

        ClientResponse response = webResource.accept(MediaType.TEXT_XML_TYPE).get(ClientResponse.class);

        assertEquals(200, response.getStatus());

        String output = response.getEntity(String.class);
        LOGGER.info("Output from Server .... \n");
        LOGGER.info(output);
    }

    @Test
    public void TestDelete() throws Exception{
        final String nodeEntryPointUri = SERVER_ROOT_URI + "delete/";
        WebResource webResource = client.resource(nodeEntryPointUri);

        ClientResponse response = webResource.path("52c424b4e020ec70d6bd4050")
                .accept(MediaType.APPLICATION_JSON)
                .delete(ClientResponse.class);

        assertEquals(204, response.getStatus());

        LOGGER.info(String.format("succeeded (no content) to [%s], status code [%d], location header [%s]",
                nodeEntryPointUri, response.getStatus(), response.getHeaders() ));
        response.close();

    }

    @Test
    public void TestPostJson() throws Exception{
        final String nodeEntryPointUri = SERVER_ROOT_URI + "post";
        WebResource webResource = client.resource(nodeEntryPointUri);
        String input = "{\"name\":\"Metallica\",\"age\":20,\"position\":\"architect\",\"interest\":\"computer\"}";

        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .entity(input)
                .post(ClientResponse.class);

        assertEquals(201, response.getStatus());

        LOGGER.info(String.format("POST to [%s], status code [%d], location header [%s]",
                nodeEntryPointUri, response.getStatus(), response.getHeaders() ));
        response.close();

    }

    @Test
    public void TestPostForm() throws Exception{
        final String nodeEntryPointUri = SERVER_ROOT_URI + "postForm";
        WebResource webResource = client.resource(nodeEntryPointUri);

        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("name", "ben");
        formData.add("age", "45");
        formData.add("position", "ceo");
        formData.add("interest", "ceo");
        ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                .post(ClientResponse.class, formData);

        assertEquals(200, response.getStatus());

        LOGGER.info(String.format("POST to [%s], status code [%d], location header [%s]",
                nodeEntryPointUri, response.getStatus(), response.getHeaders() ));
        response.close();

    }



}
