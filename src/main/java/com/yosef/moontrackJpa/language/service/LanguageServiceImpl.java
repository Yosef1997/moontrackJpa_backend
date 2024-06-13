package com.yosef.moontrackJpa.language.service;

import com.yosef.moontrackJpa.exceptions.applicationException.ApplicationException;
import com.yosef.moontrackJpa.language.entity.Language;
import com.yosef.moontrackJpa.language.repository.LanguageRepository;

import java.util.List;

public class LanguageServiceImpl implements LanguageService{
  private final LanguageRepository languageRepository;

  public LanguageServiceImpl(LanguageRepository languageRepository) {
    this.languageRepository = languageRepository;
  }
  @Override
  public List<Language> getAllLanguages() {
    return languageRepository.findAll();
  }

  @Override
  public Language createLanguage(Language language) {
    var result = languageRepository.findByLanguageName(language.getLanguageName());
    if (!result.isEmpty()) {
      throw new ApplicationException("Language name already exists");
    }
    return languageRepository.save(language);
  }

  @Override
  public String deleteLanguage(Long id) {
    languageRepository.deleteById(id);
    return "Delete Language success";
  }
}
