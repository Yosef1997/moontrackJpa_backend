package com.yosef.moontrackJpa.language.repository;

import com.yosef.moontrackJpa.language.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Long> {
  List<Language> findByLanguageName(String languageName);
}
