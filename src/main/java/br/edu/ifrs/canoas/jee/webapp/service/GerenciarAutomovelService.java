package br.edu.ifrs.canoas.jee.webapp.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.edu.ifrs.canoas.jee.webapp.data.AutomovelDAO;
import br.edu.ifrs.canoas.jee.webapp.model.Automovel;
import br.edu.ifrs.canoas.jee.webapp.util.Mensagens;

@Stateless
public class GerenciarAutomovelService {

	@Inject
	private AutomovelDAO automovelDAO;
	
	@Inject
	private Logger log;

	public boolean salvaAutomovel(Automovel automovel) {

		log.info("Salvando " + automovel.getModelo());
		
		if (automovel.getId() != null) {
			automovelDAO.atualiza(automovel);
			Mensagens.define(FacesMessage.SEVERITY_INFO, "Automovel.atualizado.sucesso",automovel.getPlaca());
			return true;
		}
		
		int qtdPlacaCadastrado = this.validaPlaca(automovel);
		
		if (qtdPlacaCadastrado == 0) {
			
				automovelDAO.insere(automovel);
				Mensagens.define(FacesMessage.SEVERITY_INFO, "Automovel.cadastro.sucesso",automovel.getPlaca());
				log.info("Salvo " + automovel.getModelo() + " com id " + automovel.getId());
				return true;
			
		} 
		
		log.info("Problema com placa duplicada do automóvel " + automovel.getModelo() + " - placa " + automovel.getPlaca());
		Mensagens.define(FacesMessage.SEVERITY_ERROR, "Automovel.placa.erro.cadastrado",automovel.getPlaca());
		return false;
	}

	
	
	/**
	 * retorna a quantidade de placas cadastrados no banco iguais ao informado.
	 * @param usuario
	 * @return int
	 */
	private int validaPlaca(Automovel automovel) {
		if (automovel == null || StringUtils.isBlank(automovel.getPlaca()))
			return -1;

		return automovelDAO
				.buscaPorPlaca(automovel.getPlaca().trim().toLowerCase())
				.size();
	}

	@SuppressWarnings("unchecked")
	public List<Automovel> busca(String criterio) {
		if (criterio != null && criterio.length() > 0) 
			return automovelDAO.buscaPorCriterio(criterio);
		else
			return automovelDAO.lista();
	}
	
	public void exclui(Automovel automovel) {
		automovelDAO.exclui(automovel.getId());
		Mensagens.define(FacesMessage.SEVERITY_INFO, "Automovel.excluido.sucesso",automovel.getPlaca());
		log.info("Excluido " + automovel.getModelo() + " com id " + automovel.getId());
	}


	public Automovel get(Long id) {
		return automovelDAO.busca(id);
	}
	
}
