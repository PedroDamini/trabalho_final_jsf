/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.upf.projetoalugueljsf.controller;

import br.upf.projetoalugueljsf.entity.PessoaEntity;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 *
 * @author Pedro Damini
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    public LoginController() {
    }

    private PessoaEntity pessoa;

    public PessoaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaEntity pessoa) {
        this.pessoa = pessoa;
    }

    public void prepareAutenticarPessoa() {
        pessoa = new PessoaEntity();
    }
    
    @PostConstruct
    public void init() {
        prepareAutenticarPessoa();
    }
    
    public String validarLogin() {
        if (pessoa.getEmail().equals("user@gmail.com")
                && pessoa.getSenha().equals("123")) {
            return "/index.xhtml?faces-redirect=true";
        } else {
            FacesMessage fm = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Falha no Login!",
                    "Email ou senha incorreto!");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return null;
        }
    }    

}
