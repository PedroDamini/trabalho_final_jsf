/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.upf.projetoalugueljsf.controller;

import br.upf.projetoalugueljsf.entity.ApartamentoEntity;
import br.upf.projetoalugueljsf.entity.InquilinoEntity;
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

    public ApartamentoEntity getApartamento() {
        return apartamento;
    }

    public void setApartamento(ApartamentoEntity apartamento) {
        this.apartamento = apartamento;
    }

    public List<ApartamentoEntity> getApartamentoList() {
        return apartamentoList;
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

    private void exibirMensagem() {
        //criando mensagem para exibir...
        String msg = "Apartamento adicionado: " + apartamento.getApartamento();
        //capturando mensagem criada...
        FacesMessage fm = new FacesMessage(msg);
        FacesContext.getCurrentInstance().addMessage(msg, fm);
    }

    public void adicionarApartamento() {
        //adicionando id para o novo registro
        apartamento.setId(gerarId());
        //adicionando um contato dentro da lista de contatos...
        apartamentoList.add(apartamento);
        exibirMensagem();
        //limpando os dados da apartamento...
        apartamento = new ApartamentoEntity();
    }

    public void editarApartamento() {
        int index = apartamentoList.indexOf(selected);
        apartamentoList.set(index, selected);
        selected = null;
        //exibindo mensagem
        FacesMessage fm = new FacesMessage(
                FacesMessage.SEVERITY_INFO,
                "Sucesso!",
                "Registro alterado com sucesso.");
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    /**
     * Método utilizado para deletar um apartamento
     */
    public void deletarApartamento() {
        int index = apartamentoList.indexOf(selected);
        apartamentoList.remove(index);
        selected = null;
        //exibindo mensagem
        FacesMessage fm = new FacesMessage(
                FacesMessage.SEVERITY_INFO,
                "Sucesso!",
                "Registro excluído com sucesso.");
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    @FacesConverter(forClass = InquilinoEntity.class)
    public static class InquilinoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            InquilinoController controller
                    = (InquilinoController) facesContext.getApplication().getELResolver().
                            getValue(facesContext.getELContext(),
                                    null, "inquilinoController");
            return controller.getInquilino(getKey(value));
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
            if (object instanceof InquilinoEntity) {
                InquilinoEntity o = (InquilinoEntity) object;
                return getStringKey(o.getId());
            } else {
                return null;
            }
        }
    }
//    
}
