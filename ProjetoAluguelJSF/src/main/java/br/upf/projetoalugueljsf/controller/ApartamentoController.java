/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.upf.projetoalugueljsf.controller;

import br.upf.projetoalugueljsf.entity.ApartamentoEntity;
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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro Damini
 */
@Named(value = "apartamentoController")
@SessionScoped
public class ApartamentoController implements Serializable {

    /**
     * Creates a new instance of ApartamentoController
     */
    public ApartamentoController() {
    }

    @EJB
    private br.upf.projetoalugueljsf.facade.ApartamentoFacade ejbFacade;
    //objeto que representa um apartamento
    ApartamentoEntity apartamento = new ApartamentoEntity();

    //objeto que representa uma lista de apartamentos
    private List<ApartamentoEntity> apartamentoList = new ArrayList<>();

    private ApartamentoEntity selected;

    public ApartamentoEntity getSelected() {
        return selected;
    }

    public void setSelected(ApartamentoEntity selected) {
        this.selected = selected;
    }

    public List<ApartamentoEntity> apartamentoAll() {
        return ejbFacade.buscarTodos();
    }

    public ApartamentoEntity getApartamento(Integer key) {
        return apartamento;
    }

    public void setApartamento(ApartamentoEntity apartamento) {
        this.apartamento = apartamento;
    }

    public List<ApartamentoEntity> getApartamentoList() {
        return ejbFacade.buscarTodos();
    }

    public void setApartamentoList(List<ApartamentoEntity> apartamentoList) {
        this.apartamentoList = apartamentoList;
    }

    private int gerarId() {
        int id = 1;
        if (apartamentoList.isEmpty()) {
            id = apartamentoList.size() + 1;
        }
        return id;
    }

    public ApartamentoEntity prepareAdicionar() {
        apartamento = new ApartamentoEntity();
        return apartamento;
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
                        ejbFacade.createReturn(apartamento);
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

    private void exibirMensagem() {
        //criando mensagem para exibir...
        String msg = "Apartamento adicionado: " + apartamento.getApartamento();
        //capturando mensagem criada...
        FacesMessage fm = new FacesMessage(msg);
        FacesContext.getCurrentInstance().addMessage(msg, fm);
    }

    public void adicionarApartamento() {
        persist(ApartamentoController.PersistAction.CREATE,
                "Registro incluído com sucesso!");
    }

    public void editarApartamento() {
        persist(ApartamentoController.PersistAction.UPDATE,
                "Registro alterado com sucesso!");
    }

    public void deletarApartamento() {
        persist(ApartamentoController.PersistAction.DELETE,
                "Registro excluído com sucesso!");
    }

    @FacesConverter(forClass = ApartamentoEntity.class)
    public static class ApartamentoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ApartamentoController controller
                    = (ApartamentoController) facesContext.getApplication().getELResolver().
                            getValue(facesContext.getELContext(),
                                    null, "apartamentoController");
            return controller.getApartamento(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext,
                UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ApartamentoEntity) {
                ApartamentoEntity o = (ApartamentoEntity) object;
                return getStringKey(o.getId());
            } else {
                return null;
            }
        }
    }
//    
}
