package com.osomapps.pt.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@TestPropertySource("/application-test.properties")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class UserResourceWebTest {

    @Test
    public void testFindOne() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Token", "");
        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
            "http://localhost:9090/api/v1/user", HttpMethod.GET, new HttpEntity<>(headers), String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseJson = objectMapper.readTree(response.getBody());

        assertThat(responseJson.isMissingNode(), is(false));
        assertThat(responseJson.toString(), equalTo("{\"id\":null,\"name\":null,"
                + "\"email\":null,\"avatar\":null,\"avatar_dataurl\":null,"
                + "\"gender\":null,\"age\":null,\"birthday\":null,\"level\":null,"
                + "\"goals\":null,\"height\":null,\"weight\":null}"));
    }
}
