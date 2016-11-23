package com.github.pt;

import org.junit.Test;
import org.springframework.beans.factory.BeanCreationException;

public class AppTest {

    @Test(expected = BeanCreationException.class)
    public void main() {
        App.main(new String[] {});
    }
}
