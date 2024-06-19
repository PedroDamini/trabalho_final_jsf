/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.upf.projetoalugueljsf.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author Pedro Damini
 */
@Entity
public class ApartamentoEntity implements Serializable{
    
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    
    @Basic(optional = false) //atributo não é opcional
    @NotNull //definido como obrigatório
    @Size(min = 1, max = 10) //quantidade min e max de caracteres
    @Column(name = "apartamento")
    private String apartamento;
    
    @Basic(optional = false) //atributo não é opcional
    @NotNull //definido como obrigatório
    @Column(name = "aluguel")
    private Double aluguel;  
    
    @Basic(optional = false) //atributo não é opcional
    @NotNull //definido como obrigatório
    @Column(name = "condominio")
    private Double condominio;
    
    @Basic(optional = false) //atributo não é opcional
    @NotNull //definido como obrigatório
    @Size(min = 1, max = 50) //quantidade min e max de caracteres
    @Column(name = "endereco")
    private String endereco;
    
    @Basic(optional = false) //atributo não é opcional
    @NotNull //definido como obrigatório
    @Column(name = "metragem")
    private Float metragem;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApartamento() {
        return apartamento;
    }

    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }

    public Double getAluguel() {
        return aluguel;
    }

    public void setAluguel(Double aluguel) {
        this.aluguel = aluguel;
    }

    public Double getCondominio() {
        return condominio;
    }

    public void setCondominio(Double condominio) {
        this.condominio = condominio;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Float getMetragem() {
        return metragem;
    }

    public void setMetragem(Float metragem) {
        this.metragem = metragem;
    }
    
}
