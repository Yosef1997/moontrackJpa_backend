package com.yosef.moontrackJpa.language.service;

import com.yosef.moontrackJpa.language.entity.Language;

import java.util.List;

public interface LanguageService {
  List<Language> getAllLanguages();

  Language createLanguage(Language language);

  String deleteLanguage(Long id);
}
