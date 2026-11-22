package com.bezkoder.spring.jpa.postgresql.service;

import com.bezkoder.spring.jpa.postgresql.model.Tutorial;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface TutorialService {
    List<Tutorial> findAll(String title);

    Optional<Tutorial> findById(long id);

    @Transactional
    Tutorial create(Tutorial t);

    @Transactional
    Optional<Tutorial> update(long id, Tutorial t);

    @Transactional
    void delete(long id);

    @Transactional
    void deleteAll();

    List<Tutorial> findPublished();
}
