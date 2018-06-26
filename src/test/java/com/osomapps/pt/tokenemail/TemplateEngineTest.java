package com.osomapps.pt.tokenemail;

import java.util.HashMap;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;

public class TemplateEngineTest {

    @Test
    public void constructor() {
        new TemplateEngine();
        new TemplateEngine.TemplateImpl<String, String>("");
    }

    @Test
    public void template() {
        assertThat(TemplateEngine.<String, String>template("Hi, {{test}}!").apply(new HashMap<String, String>() {{
            put("test", "Example");
        }}), equalTo("Hi, Example!"));
    }

    @Test
    public void template2() {
        assertThat(TemplateEngine.<String, String>template("Hi, {{ test }}!").apply(new HashMap<String, String>() {{
            put("test", "Example");
        }}), equalTo("Hi, Example!"));
    }

    @Test
    public void template3() {
        assertThat(TemplateEngine.<String, String>template("Hi, {{ test }}!").apply(new HashMap<>()),
                equalTo("Hi, {{ test }}!"));
    }

}
