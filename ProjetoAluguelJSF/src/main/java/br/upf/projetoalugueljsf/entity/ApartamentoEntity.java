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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Pedro Damini
 */
@Entity
@Table(name = "apartamento")
public class ApartamentoEntity implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    
    @Basic(optional = false)
    @NotNull 
    @Size(min = 1, max = 10) 
    @Column(name = "apartamento")
    private String apartamento;
    
    @Basic(optional = false) 
    @NotNull 
    @Column(name = "aluguel")
    private Double aluguel;  
    
    @Basic(optional = false) 
    @NotNull 
    @Column(name = "condominio")
    private Double condominio;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "endereco")
    private String endereco;
    
        
    @Basic(optional = false)
    @NotNull 
    @Column(name = "metragem")
    private Float metragem;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Objects.hashCode(this.apartamento);
        hash = 37 * hash + Objects.hashCode(this.aluguel);
        hash = 37 * hash + Objects.hashCode(this.condominio);
        hash = 37 * hash + Objects.hashCode(this.endereco);
        hash = 37 * hash + Objects.hashCode(this.metragem);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ApartamentoEntity other = (ApartamentoEntity) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.apartamento, other.apartamento)) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        if (!Objects.equals(this.aluguel, other.aluguel)) {
            return false;
        }
        if (!Objects.equals(this.condominio, other.condominio)) {
            return false;
        }
        return Objects.equals(this.metragem, other.metragem);
    }
    
    @Override
    public String toString(){
        return apartamento;
    }
    

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
