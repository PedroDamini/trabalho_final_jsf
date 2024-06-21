package br.upf.projetoalugueljsf.facade;

import br.upf.projetoalugueljsf.entity.InquilinoEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless            
public class InquilinoFacade extends AbstractFacade<InquilinoEntity> {

    /**
     * Definindo a unidade de persistencia
     */
    @PersistenceContext(unitName = "ProjetojfaluguelPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Construtor que passa para superclasse a instância de InquilinoEntity
     */
    public InquilinoFacade() {
        super(InquilinoEntity.class);
    }

    private List<InquilinoEntity> entityList;

    public List<InquilinoEntity> buscarTodos() {
        entityList = new ArrayList<>();
        try {
            //utilizando JPQL para construir a query 
            Query query = getEntityManager().createQuery("SELECT p FROM InquilinoEntity p order by p.nome");
            //verifica se existe algum resultado para não gerar excessão
            if (!query.getResultList().isEmpty()) {
                entityList = (List<InquilinoEntity>) query.getResultList();
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return entityList;
    }

}
