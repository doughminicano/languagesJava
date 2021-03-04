package com.Languages.respositories;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Languages.models.Languages;

@Repository
public interface LanguagesRepository extends CrudRepository<Languages, Long> {
	
	Optional<Languages> findById(Long id);

}
