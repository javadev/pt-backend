package com.osomapps.pt.dictionary;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DictionaryRepository extends JpaRepository<DictionaryData, Long> {
    String ENG_LANGUAGE = "en";
    String NOR_LANGUAGE = "nb";

    @Query(
            "select d from DictionaryData d where d.dlanguage = 'en' and d.dname = ?1 and d.dkey = ?2 "
                    + "and d.fromdate <= ?3 and (d.todate is null or d.todate >= ?3) order by d.id desc")
    @Cacheable(value = "dictionaryData", key = "{'en', #p0, #p1}")
    List<DictionaryData> findDictionaryValue(String dname, String dkey, LocalDateTime currentdate);

    @Query(
            "select d from DictionaryData d where d.dlanguage = ?1 and d.dname = ?2 and d.dkey = ?3 "
                    + "and d.fromdate <= ?4 and (d.todate is null or d.todate >= ?4) order by d.id desc")
    @Cacheable(value = "dictionaryData", key = "{#p0, #p1, #p2}")
    List<DictionaryData> findDictionaryValue(
            String dlanguage, String dname, String dkey, LocalDateTime currentdate);

    @Caching(
            evict = {
                @CacheEvict(
                        value = "dictionaryData",
                        key = "{#p0.dlanguage, #p0.dname, #p0.dkey}",
                        beforeInvocation = true),
                @CacheEvict(
                        value = "dictionaryAllData",
                        key = "{#p0.dlanguage, #p0.dname}",
                        beforeInvocation = true)
            })
    @Override
    <S extends DictionaryData> S save(S service);

    @Query(
            "select d from DictionaryData d where d.dlanguage = ?1 and d.dname = ?2 "
                    + "and d.fromdate <= ?3 and (d.todate is null or d.todate >= ?3) order by d.id desc")
    @Cacheable(value = "dictionaryAllData", key = "{#p0, #p1}")
    List<DictionaryData> findDictionaryAllValues(
            String dlanguage, String dname, LocalDateTime currentdate);

    @Query(
            "select d from DictionaryData d where d.dlanguage = ?1 and d.dname = ?2 and d.dkey = ?3 "
                    + "and d.fromdate <= ?4 and (d.todate is null or d.todate >= ?4)")
    List<DictionaryData> findDictionaryByKey(
            String dlanguage, String dname, String dkey, LocalDateTime currentdate);
}
