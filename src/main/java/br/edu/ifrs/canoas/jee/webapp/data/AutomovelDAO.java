package br.edu.ifrs.canoas.jee.webapp.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import br.edu.ifrs.canoas.jee.webapp.model.Automovel;
import br.edu.ifrs.canoas.jee.webapp.model.Usuario;

public class AutomovelDAO extends BaseDAO<Automovel, Long> implements Serializable {

	private static final long serialVersionUID = 7531663472996113763L;
	
	@SuppressWarnings("unchecked")
	public List<Automovel> busca(Automovel automovel) {

		if (automovel == null)
			return new ArrayList<Automovel>();

		CriteriaQuery<Automovel> query = builder().createQuery(Automovel.class);
		Root<Automovel> from = query.from(Automovel.class);

		Predicate predicate = builder().and();

		if (!StringUtils.isBlank(automovel.getMarca()))
			predicate = builder().and(predicate, builder().like(
					from.get("marca"), automovel.getMarca().trim().toLowerCase()));
		if (!StringUtils.isBlank(automovel.getModelo()))
			predicate = builder().and(predicate,
					builder().like(from.get("modelo"), automovel.getModelo().trim().toLowerCase()));
		if (!StringUtils.isBlank(automovel.getAno()))
			predicate = builder().and(predicate,
					builder().like(from.get("ano"), automovel.getAno().trim().toLowerCase()));
		if (!StringUtils.isBlank(automovel.getPlaca()))
			predicate = builder().and(predicate,
					builder().like(from.get("placa"), automovel.getPlaca().trim().toLowerCase()));
		if(!StringUtils.isBlank(automovel.getRenavan()))
			predicate = builder().and(predicate, builder().like(
					from.get("renavan"), automovel.getRenavan().trim().toLowerCase()));

		return em.createQuery(query
				.select(from)
				.where(predicate)
				.orderBy(builder().asc(from.get("nome"))))
				.getResultList();

	}
	
	@SuppressWarnings("unchecked")
	public List<Automovel> buscaPorCriterio(String criterio){
		return em.createQuery(
		         "SELECT a "
		         + "FROM Automovel a "
		         + "WHERE lower(a.placa) = :placa "
		         + " or lower (a.renavan) = :renavan ")  
		         .setParameter("placa", criterio.trim().toLowerCase())
		         .setParameter("renavan", criterio.trim().toLowerCase())
		         .getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Automovel> buscaPorPlaca(String placa) {
		return em.createQuery("SELECT a " + "FROM Automovel a " + "WHERE lower(a.placa) = :placa ")
				.setParameter("placa", placa).getResultList();
	}
	
	@Override
	public void exclui(Long id){
		@SuppressWarnings("unchecked")
		Object ref = em.getReference(getEntityClass(), id);
		
		TypedQuery<Usuario> user = em.createQuery("select u from Usuario u where u.automovel.id = :id", Usuario.class);
		
		user.setParameter("id", id);
		Usuario u = user.getSingleResult();
		u.setAutomovel(null);
		em.merge(u);
		
		
		em.remove(ref);
	}

}
