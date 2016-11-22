package com.github.pt.dictionary;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService {

    private final DictionaryRepository dictionaryRepository;

    DictionaryService(DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    public String getNewDictionaryDataKey(String dName) {
        final List<DictionaryData> allExerciseEnNames = dictionaryRepository.
                findDictionaryAllValues(DictionaryRepository.ENG_LANGUAGE,
                        dName, LocalDateTime.now());
        if (allExerciseEnNames.isEmpty()) {
            return "10";
        }
        final String biggestKey =  allExerciseEnNames.stream()
                .filter(data -> data.getDkey().matches("\\d+"))
                .sorted((d1, d2) ->
                        Integer.compare(Integer.parseInt(d2.getDkey()), Integer.parseInt(d1.getDkey())))
                    .findFirst().get().getDkey();
        return "" + (Integer.parseInt(biggestKey) + 10);
    }

    public void createDictionaryDataKey(String dName, String dKey, String dataNameEn, String dataNameNo) {
        final DictionaryData dataEn = new DictionaryData();
        dataEn.setDlanguage(DictionaryRepository.ENG_LANGUAGE);
        dataEn.setDname(dName);
        dataEn.setDkey(dKey);
        dataEn.setDvalue(dataNameEn);
        dictionaryRepository.save(dataEn);
        final DictionaryData dataNo = new DictionaryData();
        dataNo.setDlanguage(DictionaryRepository.NOR_LANGUAGE);
        dataNo.setDname(dName);
        dataNo.setDkey(dKey);
        dataNo.setDvalue(dataNameNo);
        dictionaryRepository.save(dataNo);
    }

    public String getEnValue(String dName, String dKey, String defaultValue) {
        final List<DictionaryData> dictionaryDatas = dictionaryRepository.
            findDictionaryValue(DictionaryRepository.ENG_LANGUAGE,
                dName, dKey, LocalDateTime.now());
        return dictionaryDatas.isEmpty() ? defaultValue : dictionaryDatas.get(0).getDvalue();
    }

    public String getNoValue(String dName, String dKey, String defaultValue) {
        final List<DictionaryData> dictionaryDatas = dictionaryRepository.
            findDictionaryValue(DictionaryRepository.NOR_LANGUAGE,
                dName, dKey, LocalDateTime.now());
        return dictionaryDatas.isEmpty() ? defaultValue : dictionaryDatas.get(0).getDvalue();
    }

    public void deleteDatas(String dName, String dKey) {
        dictionaryRepository.delete(dictionaryRepository.findDictionaryByKey(
                DictionaryRepository.ENG_LANGUAGE, dName, dKey, LocalDateTime.now()));
        dictionaryRepository.delete(dictionaryRepository.findDictionaryByKey(
                DictionaryRepository.NOR_LANGUAGE, dName, dKey, LocalDateTime.now()));
    }

}
