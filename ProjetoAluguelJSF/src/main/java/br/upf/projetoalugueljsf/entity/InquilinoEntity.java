/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.upf.projetoalugueljsf.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;

/**
 *
 * @author Pedro Damini
 */
public class InquilinoEntity {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "nome")
    private String nome;
    
    @Basic(optional = false) 
    @NotNull 
    @Size(min = 1, max = 10) 
    @Column(name = "cpf")
    private String cpf;
    
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "datanascimento")
    private Date dataNascimento;
    
    @Basic(optional = false)
    @NotNull 
    @Column(name = "mesinquilino")
    private Float mesinquilino;
    
    @JoinColumn(name = "id_apartamento", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ApartamentoEntity idApartamento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Float getMesinquilino() {
        return mesinquilino;
    }

    public void setMesinquilino(Float mesinquilino) {
        this.mesinquilino = mesinquilino;
    }

    public ApartamentoEntity getIdApartamento() {
        return idApartamento;
    }

    public void setIdApartamento(ApartamentoEntity idApartamento) {
        this.idApartamento = idApartamento;
    }
}
