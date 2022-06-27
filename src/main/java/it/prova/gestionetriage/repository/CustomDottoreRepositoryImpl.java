package it.prova.gestionetriage.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import it.prova.gestionetriage.model.Dottore;

public class CustomDottoreRepositoryImpl implements CustomDottoreRepository {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Dottore> findByExample(Dottore example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select d from Dottore d where d.id = d.id ");

		if (StringUtils.isNotEmpty(example.getNome())) {
			whereClauses.add(" d.nome  like :nome ");
			paramaterMap.put("nome", "%" + example.getNome() + "%");
		}
		if (StringUtils.isNotEmpty(example.getCognome())) {
			whereClauses.add(" d.cognome = :cognome ");
			paramaterMap.put("cognome", example.getCognome());
		}
		if (StringUtils.isNotEmpty(example.getCodiceDipendente())) {
			whereClauses.add(" d.codiceDipendente = :codiceDipendente ");
			paramaterMap.put("codiceDipendente", example.getCodiceDipendente());
		}
//		if (example.getPazienteAttualmenteInVisita().getId() != null) {
//			whereClauses.add(" d.pazienteAttualmenteInVisita.id = :pazienteAttualmenteInVisita ");
//			paramaterMap.put("pazienteAttualmenteInVisita", example.getPazienteAttualmenteInVisita().getId());
//		}
	
		
		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Dottore> typedQuery = entityManager.createQuery(queryBuilder.toString(), Dottore.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

}
