package br.com.bsitecnologia.dashboard.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import br.com.bsitecnologia.dashboard.dao.PerfilAcaoDominioDao;
import br.com.bsitecnologia.dashboard.model.Acao;
import br.com.bsitecnologia.dashboard.model.Cliente;
import br.com.bsitecnologia.dashboard.model.Dominio;
import br.com.bsitecnologia.dashboard.model.Perfil;
import br.com.bsitecnologia.dashboard.model.PerfilAcaoDominio;

public class PerfilAcaoDominioService implements Serializable{
	
	private static final long serialVersionUID = -2331294689619917429L;
	
	@Inject
	private PerfilAcaoDominioDao perfilAcaoDominioDao;

	public void buildAcaoDominioTree(TreeNode rootNode, List<Dominio> allDominioFromDB, List<Acao> allAcaoFromDB){
		for(Dominio dominio : allDominioFromDB){
			TreeNode dominioNode = new DefaultTreeNode(Dominio.class.getSimpleName() ,dominio, rootNode);
			for(Acao acao : allAcaoFromDB){
				if(null == acao.getDominio() || acao.getDominio().getId().equals(dominio.getId())){
					new DefaultTreeNode(Acao.class.getSimpleName(), acao,dominioNode);
				}
			}
		}
	}
	
	public List<PerfilAcaoDominio> prepareToSave(TreeNode[] selectedNodes, Cliente cliente, Perfil perfil){
		List<PerfilAcaoDominio> list = new ArrayList<PerfilAcaoDominio>();
		for(TreeNode node : selectedNodes){
			if(node.getType().equals(Acao.class.getSimpleName())){
				list.add(new PerfilAcaoDominio((Acao)node.getData(), perfil, (Dominio)node.getParent().getData(), cliente));
			}
		}
		return list;
	}
	
	public List<PerfilAcaoDominio> buildSelectedAcaoDominioTreeAndReturnSelected(PerfilAcaoDominio perfilAcaoDominioForm, TreeNode root){
		List<PerfilAcaoDominio> lista = perfilAcaoDominioDao.findByPerfilAndCliente(perfilAcaoDominioForm);
		for(TreeNode dominio : root.getChildren()){
			int selectedChildren = 0;
			for(TreeNode acao : dominio.getChildren()){
				if(containsAcaoIdInPerfilAcaoDominioList(lista, ((Acao)acao.getData()).getId(), ((Dominio)dominio.getData()).getId())){
					acao.setSelected(true);
					selectedChildren++;
				}
			}
			if(dominio.getChildCount() == selectedChildren){
				dominio.setSelected(true);
			}
		}
		return lista;
	}
	
	public void resetSelected(TreeNode root){
		for(TreeNode dominio : root.getChildren()){
			dominio.setSelected(false);
			for(TreeNode acao : dominio.getChildren()){
				acao.setSelected(false);
			}
		}
	}
	
	
	private boolean containsAcaoIdInPerfilAcaoDominioList(List<PerfilAcaoDominio> lista, Integer acaoId, Integer dominioId){
		for (PerfilAcaoDominio pad : lista) {
			if(pad.getDominio().getId().equals(dominioId) && pad.getAcao().getId().equals(acaoId)){
				return true;
			}
		}
		return false;
	}
	
	
}