package com.github.pt.dictionary;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DictionaryServiceTest {

    @Mock
    private DictionaryRepository dictionaryRepository;    
    
    @InjectMocks
    private DictionaryService dictionaryService;

    @Test
    public void getNewDictionaryDataKey() {
        dictionaryService.getNewDictionaryDataKey(DictionaryName.exercise_name);
        verify(dictionaryRepository).findDictionaryAllValues(eq(DictionaryRepository.ENG_LANGUAGE),
                eq("exercise_name"), any());
    }

    @Test
    public void createDictionaryDataKey() {
        dictionaryService.createDictionaryDataKey(DictionaryName.exercise_name, "1", "en", "no");
        verify(dictionaryRepository, times(2)).save(any(DictionaryData.class));
    }

    @Test
    public void deleteDatas() {
        when(dictionaryRepository.findDictionaryByKey(
                anyString(), anyString(), anyString(), any(LocalDateTime.class)))
                .thenReturn(Arrays.asList(new DictionaryData()));
        dictionaryService.deleteDatas(DictionaryName.exercise_name, "1");
        verify(dictionaryRepository, times(2)).delete(any(List.class));
    }
}
