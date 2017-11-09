package br.edu.ifrs.canoas.jee.webapp.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.jee.webapp.model.Automovel;

import br.edu.ifrs.canoas.jee.webapp.service.GerenciarAutomovelService;


@Named
@RequestScoped
public class GerenciarAutomovelMB {

	
	@Inject
    private GerenciarAutomovelService gerenciarAutomovelService;	
	@Inject
	private Automovel automovel;
	
	private List<Automovel> automoveis;
		
	public String salva() {
		gerenciarAutomovelService.salvaAutomovel(automovel);
		this.init();
		return limpa();
	}
	
	@PostConstruct
    public void init() {
		automoveis = gerenciarAutomovelService.busca(null);	
    }
	
	public void exclui() {
		gerenciarAutomovelService.exclui(automovel);
		this.init();
	}
	
	public void edita(Automovel u) {
		this.automovel = u;
	}

	public Automovel getAutomovel() {
		return automovel;
	}

	public void setAutomovel(Automovel automovel) {
		this.automovel = automovel;
	}

	public List<Automovel> getAutomoveis() {
		return automoveis;
	}

	public void setAutomoveis(List<Automovel> automoveis) {
		this.automoveis = automoveis;
	}
	public String limpa() {
		automovel = new Automovel();
		return "/public/automovel.jsf?facesRedirect=true";
	}

}


