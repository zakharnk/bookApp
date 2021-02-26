package com.project.bookmanagemet.repository;

import com.project.bookmanagemet.model.Translator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslatorRepository extends JpaRepository<Translator, Long> {

}
