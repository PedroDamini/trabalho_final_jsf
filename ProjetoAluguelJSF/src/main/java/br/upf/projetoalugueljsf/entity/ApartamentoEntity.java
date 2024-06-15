/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.upf.projetoalugueljsf.entity;

import jakarta.persistence.Entity;
import java.io.Serializable;

/**
 *
 * @author Pedro Damini
 */
@Entity
public class ApartamentoEntity implements Serializable{
    
    //atributos
    private int id;
    private String apartamento;
    private Double aluguel;  
    private Double condominio;
    private String endereco;
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
