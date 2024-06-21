package br.upf.projetoalugueljsf.facade;

import br.upf.projetoalugueljsf.entity.ApartamentoEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless            
public class ApartamentoFacade extends AbstractFacade<ApartamentoEntity> {

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
     * Construtor que passa para superclasse a instância de ApartamentoEntity
     */
    public ApartamentoFacade() {
        super(ApartamentoEntity.class);
    }

    private List<ApartamentoEntity> entityList;

    public List<ApartamentoEntity> buscarTodos() {
        entityList = new ArrayList<>();
        try {
            Query query = getEntityManager().createQuery("SELECT p FROM ApartamentoEntity p order by p.id");
            if (!query.getResultList().isEmpty()) {
                entityList = (List<ApartamentoEntity>) query.getResultList();
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return entityList;
    }

}
