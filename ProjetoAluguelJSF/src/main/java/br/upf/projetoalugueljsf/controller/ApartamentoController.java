/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.upf.projetoalugueljsf.controller;

import br.upf.projetoalugueljsf.entity.ApartamentoEntity;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
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
    
    private int gerarId(){
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
    
}
