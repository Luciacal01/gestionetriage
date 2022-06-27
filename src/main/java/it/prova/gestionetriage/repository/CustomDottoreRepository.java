package it.prova.gestionetriage.repository;

import java.util.List;

import it.prova.gestionetriage.model.Dottore;

public interface CustomDottoreRepository {
	List<Dottore> findByExample(Dottore example);

}
