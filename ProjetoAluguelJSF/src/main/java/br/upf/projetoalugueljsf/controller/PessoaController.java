package br.upf.projetoalugueljsf.controller;

import br.upf.projetoalugueljsf.entity.PessoaEntity;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "pessoaController")
@SessionScoped
public class PessoaController implements Serializable {
    
    @EJB
    private br.upf.projetoalugueljsf.facade.PessoaFacade ejbFacade;

    public PessoaController() {
    }

    //objeto que representa uma pessoa
    private PessoaEntity pessoa = new PessoaEntity();
    //objeto que representa uma lista de pessoas
    private List<PessoaEntity> pessoaList = new ArrayList<>();
    public PessoaEntity getPessoa() {
        return pessoa;
    }
    private PessoaEntity selected;

    public PessoaEntity getSelected() {
        return selected;
    }

    public void setSelected(PessoaEntity selected) {
        this.selected = selected;
    }
    
    public void setPessoa(PessoaEntity pessoa) {
        this.pessoa = pessoa;
    }

    public List<PessoaEntity> getPessoaList() {
        return ejbFacade.buscarTodos();
    }

    public void setPessoaList(List<PessoaEntity> pessoaList) {
        this.pessoaList = pessoaList;
    }

    /**
     * Método responsável por gerar um valor para cada registro inserido
     *
     *
     * @return
     */
    private int gerarId() {
        int id = 1;
        if (!pessoaList.isEmpty()) {
            id = pessoaList.size() + 1;
        }
        return id;
    }

    /**
     * Método responsávle por exibir a mensagem no formulário
     */
    private void exibirMensagem() {
        //criando mensagem para exibir...
        String msg = "Contato adicionado: " + pessoa.getNome();
        //capturando mensagem criada...
        FacesMessage fm = new FacesMessage(msg);
        FacesContext.getCurrentInstance().addMessage(msg, fm);
    }

    /**
     * Método responsável por adicionar uma pessoa
     */
    public void adicionarPessoa() {
        ejbFacade.createReturn(pessoa);
        exibirMensagem();
        //limpando os dados da pessoa...
        pessoa = new PessoaEntity();
    }
}
