package com.bezkoder.spring.jpa.postgresql.service;

import com.bezkoder.spring.jpa.postgresql.model.Tutorial;
import com.bezkoder.spring.jpa.postgresql.repository.TutorialRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorialServiceImpl implements TutorialService {
    private final TutorialRepository tutorialRepository;

    public TutorialServiceImpl(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;   // 构造器注入
    }
    @Override
    public List<Tutorial> findAll(String title) {
        return (title == null)
                ? tutorialRepository.findAll()
                : tutorialRepository.findByTitleContaining(title);
    }
    @Override
    public Optional<Tutorial> findById(long id) {
        return tutorialRepository.findById(id);
    }
    @Transactional
    @Override
    public Tutorial create(Tutorial t) {
        return tutorialRepository.save(new Tutorial(t.getTitle(), t.getDescription(), false));
    }
    @Transactional
    @Override
    public Optional<Tutorial> update(long id, Tutorial t) {
        return tutorialRepository.findById(id).map(old -> {
            old.setTitle(t.getTitle());
            old.setDescription(t.getDescription());
            old.setPublished(t.isPublished());
            return tutorialRepository.save(old);
        });
    }
    @Transactional
    @Override
    public void delete(long id) {
        tutorialRepository.deleteById(id);
    }
    @Transactional
    @Override
    public void deleteAll() {
        tutorialRepository.deleteAll();
    }
    @Override
    public List<Tutorial> findPublished() {
        return tutorialRepository.findByPublished(true);
    }
}
