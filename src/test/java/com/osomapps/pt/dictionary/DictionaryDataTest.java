package com.osomapps.pt.dictionary;

import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import static org.junit.Assert.assertThat;

public class DictionaryDataTest {
    
    @Test
    public void createAllArgs() {
        assertThat(new  DictionaryData(
                1L, null, null, null, null, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        DictionaryData dictionaryData = new DictionaryData()
            .setId(null)
            .setCreated(null)
            .setDlanguage(null)
            .setValid(null)
            .setFromdate(null)
            .setTodate(null)
            .setDname(null)
            .setDkey(null)
            .setDvalue(null);
        assertThat(dictionaryData, notNullValue());
    }

    @Test
    public void getters() {
        DictionaryData dictionaryData = new DictionaryData();
        dictionaryData.getId();
        dictionaryData.getCreated();
        dictionaryData.getDlanguage();
        dictionaryData.getValid();
        dictionaryData.getFromdate();
        dictionaryData.getTodate();
        dictionaryData.getDname();
        dictionaryData.getDkey();
        dictionaryData.getDvalue();
        assertThat(dictionaryData, notNullValue());
    }

}
