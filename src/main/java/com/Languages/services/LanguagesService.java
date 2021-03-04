package com.Languages.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Languages.models.Languages;
import com.Languages.respositories.LanguagesRepository;


@Service
public class LanguagesService {
	private final LanguagesRepository languagesRepository;
	
	public LanguagesService(LanguagesRepository languagesRepository) {
		this.languagesRepository = languagesRepository;	
	}
	
	
	public List<Languages> allLanguages() {
		return (List<Languages>)languagesRepository.findAll();
	}
	
    public Languages findLanguage(Long id) {
        Optional<Languages> optionalLanguages = languagesRepository.findById(id);
        if(optionalLanguages.isPresent()) {
            return optionalLanguages.get();
        }
        else {
            return null;
        }
    }
    
    public Languages addLanguage(Languages x) {
    	return languagesRepository.save(x);
    }
    
    public void deleteLanguage(Long id) {
    	languagesRepository.deleteById(id);
    }
}
