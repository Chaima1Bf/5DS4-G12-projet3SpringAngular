package com.esprit.examen.services;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.CategorieProduitRepository;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.StockRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProduitServiceImpl implements IProduitService {

	@Autowired
	ProduitRepository produitRepository;
	@Autowired
	StockRepository stockRepository;
	@Autowired
	CategorieProduitRepository categorieProduitRepository;

	@Override
	public List<Produit> retrieveAllProduits() {
		log.info("Retrieving all products");
		List<Produit> produits = (List<Produit>) produitRepository.findAll();
		for (Produit produit : produits) {
			log.debug("Produit: " + produit);
		}
		log.info("Total products retrieved: {}", produits.size());
		return produits;
	}

	@Transactional
	public Produit addProduit(Produit p) {
		log.info("Adding product with details: {}", p);
		try {
			produitRepository.save(p);
			log.info("Product added successfully with ID: {}", p.getIdProduit());
		} catch (Exception e) {
			log.error("Error adding product: {}", e.getMessage());
		}
		return p;
	}

	@Override
	public void deleteProduit(Long produitId) {
		log.info("Deleting product with ID: {}", produitId);
		try {
			produitRepository.deleteById(produitId);
			log.info("Product deleted successfully with ID: {}", produitId);
		} catch (Exception e) {
			log.error("Error deleting product with ID {}: {}", produitId, e.getMessage());
		}
	}

	@Override
	public Produit updateProduit(Produit p) {
		log.info("Updating product with ID: {}", p.getIdProduit());
		try {
			Produit updatedProduct = produitRepository.save(p);
			log.info("Product updated successfully: {}", updatedProduct);
			return updatedProduct;
		} catch (Exception e) {
			log.error("Error updating product with ID {}: {}", p.getIdProduit(), e.getMessage());
			return null;
		}
	}

	@Override
	public Produit retrieveProduit(Long produitId) {
		log.info("Retrieving product with ID: {}", produitId);
		Produit produit = produitRepository.findById(produitId).orElse(null);
		if (produit != null) {
			log.debug("Product retrieved: {}", produit);
		} else {
			log.warn("Product with ID {} not found", produitId);
		}
		return produit;
	}

	@Override
	public void assignProduitToStock(Long idProduit, Long idStock) {
		log.info("Assigning product ID {} to stock ID {}", idProduit, idStock);
		try {
			Produit produit = produitRepository.findById(idProduit).orElse(null);
			Stock stock = stockRepository.findById(idStock).orElse(null);

			if (produit == null) {
				log.warn("Product with ID {} not found", idProduit);
				return;
			}
			if (stock == null) {
				log.warn("Stock with ID {} not found", idStock);
				return;
			}

			produit.setStock(stock);
			produitRepository.save(produit);
			log.info("Product ID {} assigned to stock ID {} successfully", idProduit, idStock);
		} catch (Exception e) {
			log.error("Error assigning product ID {} to stock ID {}: {}", idProduit, idStock, e.getMessage());
		}
	}
}
