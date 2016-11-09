package com.github.pt.dictionary;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DictionaryRepository extends JpaRepository<DictionaryData, Long> {
    String EXERCISE_NAME = "exercise_name";
    String EXERCISE_CATEGORY_NAME = "exercise_category_name";

    @Query("select d from DictionaryData d where d.dname = ?1 and d.dkey = ?2 "
            + "and d.fromdate <= ?3 and (d.todate is null or d.todate >= ?3)")
    @Cacheable(value = "dictionaryData", key = "#dname + '_' + #dkey")
    List<DictionaryData> findDictionaryValue(
            String dname, String dkey, LocalDateTime currentdate);
}
