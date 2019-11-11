package hr.fer.opp.projekt.audioVodic.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import hr.fer.opp.projekt.audioVodic.model.MuseumObject;
import hr.fer.opp.projekt.audioVodic.repository.MuseumObjectRepository;

@Service
@Transactional
public class MuseumObjectService {

	private final MuseumObjectRepository museumObjectRepository;
	
	public MuseumObjectService(MuseumObjectRepository museumObjectRepository) {
		this.museumObjectRepository = museumObjectRepository;
	}

	public List<MuseumObject> getAllObjects() {
		List<MuseumObject> museumObjects = new ArrayList<MuseumObject>();
		museumObjectRepository.findAll().forEach(museumObjects::add);
		return museumObjects;
	}
	
	public MuseumObject getMuseumObject(int id) {
		return museumObjectRepository.findById(id).orElse(null);
	}
	
}
