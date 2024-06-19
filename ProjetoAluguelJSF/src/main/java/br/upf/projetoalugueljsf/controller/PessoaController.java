package br.upf.projetoalugueljsf.controller;

import br.upf.projetoalugueljsf.entity.PessoaEntity;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

    public PessoaEntity prepareAdicionar() {
        pessoa = new PessoaEntity();
        return pessoa;
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public static enum PersistAction {
        CREATE,
        DELETE,
        UPDATE
    }

    private void persist(PersistAction persistAction, String successMessage) {
        try {
            if (null != persistAction) {
                switch (persistAction) {
                    case CREATE:
                        ejbFacade.createReturn(pessoa);
                        break;
                    case UPDATE:
                        ejbFacade.edit(selected);
                        selected = null;
                        break;
                    case DELETE:
                        ejbFacade.remove(selected);
                        selected = null;
                        break;
                    default:
                        break;
                }
            }
            addSuccessMessage(successMessage);
        } catch (EJBException ex) {
            String msg = "";
            Throwable cause = ex.getCause();
            if (cause != null) {
                msg = cause.getLocalizedMessage();
            }
            if (msg.length() > 0) {
                addErrorMessage(msg);
            } else {
                addErrorMessage(ex.getLocalizedMessage());
            }
        } catch (Exception ex) {
            addErrorMessage(ex.getLocalizedMessage());
        }
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

    public void adicionarPessoa() {
        persist(PessoaController.PersistAction.CREATE, 
                "Registro incluído com sucesso!");
    }

    public void editarPessoa() {
        persist(PessoaController.PersistAction.UPDATE, 
                "Registro alterado com sucesso!");
    }

    public void deletarPessoa() {
        persist(PessoaController.PersistAction.DELETE, 
                "Registro excluído com sucesso!");
    }
}
