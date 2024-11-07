package com.esprit.examen.services;

import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.OperateurRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperateurServiceImpl implements IOperateurService {

	private static final Logger logger = LogManager.getLogger(OperateurServiceImpl.class);

	@Autowired
	OperateurRepository operateurRepository;

	@Override
	public List<Operateur> retrieveAllOperateurs() {
		logger.info("Retrieving all operateurs");
		List<Operateur> operateurs = (List<Operateur>) operateurRepository.findAll();
		logger.debug("Found {} operateurs", operateurs.size());
		return operateurs;
	}

	@Override
	public Operateur addOperateur(Operateur o) {
		logger.info("Adding a new operateur: {}", o);
		Operateur savedOperateur = operateurRepository.save(o);
		logger.debug("Operateur saved with ID: {}", savedOperateur.getIdOperateur());
		return savedOperateur;
	}

	@Override
	public void deleteOperateur(Long id) {
		logger.warn("Deleting operateur with ID: {}", id);
		try {
			operateurRepository.deleteById(id);
			logger.info("Operateur with ID: {} deleted successfully", id);
		} catch (Exception e) {
			logger.error("Error deleting operateur with ID: {}", id, e);
		}
	}

	@Override
	public Operateur updateOperateur(Operateur o) {
		logger.info("Updating operateur: {}", o);
		Operateur updatedOperateur = operateurRepository.save(o);
		logger.debug("Operateur updated with ID: {}", updatedOperateur.getIdOperateur());
		return updatedOperateur;
	}

	@Override
	public Operateur retrieveOperateur(Long id) {
		logger.info("Retrieving operateur with ID: {}", id);
		Operateur operateur = operateurRepository.findById(id).orElse(null);
		if (operateur == null) {
			logger.warn("Operateur with ID: {} not found", id);
		} else {
			logger.debug("Found operateur: {}", operateur);
		}
		return operateur;
	}
}
