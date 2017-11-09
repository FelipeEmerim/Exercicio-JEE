package br.edu.ifrs.canoas.jee.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.jee.webapp.model.Automovel;
import br.edu.ifrs.canoas.jee.webapp.model.Usuario;
import br.edu.ifrs.canoas.jee.webapp.service.GerenciarAutomovelService;
import br.edu.ifrs.canoas.jee.webapp.service.GerenciarUsuarioService;

@Named
@RequestScoped
public class GerenciarUsuarioMB {

	@Inject
    private GerenciarUsuarioService gerenciarUsuarioService;
	
	@Inject
    private GerenciarAutomovelService gerenciarAutomovelSercice;
	
	@Inject
	private Usuario usuario;
	
	private Long automovel_id;
	
	private List<Automovel> automoveis; 
	
	private List<Usuario> usuarios;
		
	public String salva() {
		usuario.setAutomovel(gerenciarAutomovelSercice.get(automovel_id));
		gerenciarUsuarioService.salvaUsario(usuario);
		this.init();
		return limpa();
	}
	
	@PostConstruct
    public void init() {
		usuarios = gerenciarUsuarioService.busca(null);	
		
		automoveis = gerenciarAutomovelSercice.busca(null);
    }
	
	public void exclui() {
		gerenciarUsuarioService.exclui(usuario);
		this.init();
	}
	
	public void edita(Usuario u) {
		this.usuario = u;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public String limpa() {
		usuario = new Usuario();
		return "/public/usuario.jsf?facesRedirect=true";
	}

	public List<Automovel> getAutomoveis() {
		return automoveis;
	}

	public void setAutomoveis(List<Automovel> automoveis) {
		this.automoveis = automoveis;
	}

	public Long getAutomovel_id() {
		return automovel_id;
	}

	public void setAutomovel_id(Long automovel_id) {
		this.automovel_id = automovel_id;
	}

}
