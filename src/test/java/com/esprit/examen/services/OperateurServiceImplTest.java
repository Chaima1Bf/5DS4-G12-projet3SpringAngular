package com.esprit.examen.services;

import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.OperateurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OperateurServiceImplTest {

    @Mock
    private OperateurRepository operateurRepository;

    @InjectMocks
    private OperateurServiceImpl operateurService;

    private Operateur operateur;

    @BeforeEach
    void setUp() {
        operateur = new Operateur();
        operateur.setIdOperateur(1L);
        operateur.setNom("TestNom");
        operateur.setPrenom("TestPrenom");
        operateur.setPassword("TestPassword");
    }

    @Test
    void retrieveAllOperateurs() {
        List<Operateur> operateurs = Arrays.asList(operateur);
        when(operateurRepository.findAll()).thenReturn(operateurs);

        List<Operateur> result = operateurService.retrieveAllOperateurs();

        assertEquals(1, result.size());
        assertEquals("TestNom", result.get(0).getNom());
        verify(operateurRepository, times(1)).findAll();
    }

    @Test
    void addOperateur() {
        when(operateurRepository.save(operateur)).thenReturn(operateur);

        Operateur result = operateurService.addOperateur(operateur);

        assertNotNull(result);
        assertEquals("TestNom", result.getNom());
        verify(operateurRepository, times(1)).save(operateur);
    }

    @Test
    void deleteOperateur() {
        Long id = 1L;
        doNothing().when(operateurRepository).deleteById(id);

        operateurService.deleteOperateur(id);

        verify(operateurRepository, times(1)).deleteById(id);
    }

    @Test
    void updateOperateur() {
        when(operateurRepository.save(operateur)).thenReturn(operateur);

        Operateur result = operateurService.updateOperateur(operateur);

        assertNotNull(result);
        assertEquals("TestNom", result.getNom());
        verify(operateurRepository, times(1)).save(operateur);
    }

    @Test
    void retrieveOperateur() {
        Long id = 1L;
        when(operateurRepository.findById(id)).thenReturn(Optional.of(operateur));

        Operateur result = operateurService.retrieveOperateur(id);

        assertNotNull(result);
        assertEquals("TestNom", result.getNom());
        verify(operateurRepository, times(1)).findById(id);
    }
}
