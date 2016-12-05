/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.pt.activecertificate;

import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import static org.junit.Assert.assertThat;

/**
 *
 * @author vko
 */
public class InUserCertificateTest {
    @Test
    public void createAllArgs() {
        assertThat(new InUserCertificate(
                1L, null, null, null, null), notNullValue());
    }
    
}
