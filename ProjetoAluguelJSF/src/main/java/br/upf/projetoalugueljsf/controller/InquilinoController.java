package br.upf.projetoalugueljsf.controller;

import br.upf.projetoalugueljsf.entity.ApartamentoEntity;
import br.upf.projetoalugueljsf.entity.InquilinoEntity;
import br.upf.projetoalugueljsf.facade.InquilinoFacade;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named(value = "inquilinoController")
@SessionScoped
public class InquilinoController implements Serializable {

    @EJB
    private br.upf.projetoalugueljsf.facade.InquilinoFacade ejbFacade;

    public InquilinoController() {
    }

    private InquilinoEntity inquilino = new InquilinoEntity();
    private List<InquilinoEntity> inquilinoList = new ArrayList<>();

    public InquilinoFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(InquilinoFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public InquilinoEntity getInquilino(Integer key) {
        return inquilino;
    }
    private InquilinoEntity selected;

    public InquilinoEntity getSelected() {
        return selected;
    }

    public void setSelected(InquilinoEntity selected) {
        this.selected = selected;
    }

    public void setInquilino(InquilinoEntity inquilino) {
        this.inquilino = inquilino;
    }

    public List<InquilinoEntity> getInquilinoList() {
        return ejbFacade.buscarTodos();
    }

    public void setInquilinoList(List<InquilinoEntity> inquilinoList) {
        this.inquilinoList = inquilinoList;
    }

    /**
     * Método responsável por gerar um valor para cada registro inserido
     *
     *
     * @return
     */
    private int gerarId() {
        int id = 1;
        if (!inquilinoList.isEmpty()) {
            id = inquilinoList.size() + 1;
        }
        return id;
    }

    public InquilinoEntity prepareAdicionar() {
        selected = new InquilinoEntity();
        return selected;
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
                        ejbFacade.createReturn(selected);
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
        String msg = "Contato adicionado: " + inquilino.getNome();
        //capturando mensagem criada...
        FacesMessage fm = new FacesMessage(msg);
        FacesContext.getCurrentInstance().addMessage(msg, fm);
    }

    public void adicionarInquilino() {
        persist(InquilinoController.PersistAction.CREATE, 
                "Registro incluído com sucesso!");
    }

    public void editarInquilino() {
        persist(InquilinoController.PersistAction.UPDATE, 
                "Registro alterado com sucesso!");
    }

    public void deletarInquilino() {
        persist(InquilinoController.PersistAction.DELETE, 
                "Registro excluído com sucesso!");
    }
}
