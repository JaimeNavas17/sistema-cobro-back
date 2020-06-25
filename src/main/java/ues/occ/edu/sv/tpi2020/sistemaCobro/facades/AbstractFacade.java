/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.tpi2020.sistemaCobro.facades;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public abstract class AbstractFacade<T> {

    private final Class<T> entityClass;

    /**
     * Este método obtiene la clase de la entidad
     *
     * @param entityClass
     */
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    /**
     * Este método crea un objeto de la clase
     *
     * @param entity Es la entidad a crear
     * @return <b>True</b>: Si se pudo crear <br><b>False</b>: Si hubo algún
     * error o algo es nulo
     */
    public boolean create(T entity) {
        EntityManager em = getEntityManager();
        try {
            if (entity != null && em != null) {
                em.persist(entity);
                return true;
            } else {
                System.out.println("Entity manager es nulo o la entidad es nula");
                return false;
            }
        } catch (Exception e) {
            beanValidation(entity);
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            return false;
        }

    }

    /**
     * Este método es para editar algún objeto
     *
     * @param entity es la entidad
     * @return <b>True</b>: Si se pudo editar <br><b>False</b>: Si hubo algún
     * error o algo es nulo
     */
    public boolean edit(T entity) {
        EntityManager em = getEntityManager();
        try {
            if (entity != null && em != null) {
                em.merge(entity);
                return true;
            } else {
                System.out.println("Entity manager es nulo o la entidad es nula");
                return false;
            }
        } catch (Exception e) {
            beanValidation(entity);
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            return false;
        }
    }

    /**
     * Este método es para eliminar un objeto
     *
     * @param entity es la entidad
     * @return <b>True</b>: Si se pudo eliminar <br><b>False</b>: Si hubo algún
     * error o algo es nulo
     */
    public boolean remove(T entity) {
        EntityManager em = getEntityManager();
        try {
            if (em != null && entity != null) {
                em.remove(em.merge(entity));
                return true;
            } else {
                System.out.println("Entity manager es nulo o la entidad es nula");
                return false;
            }
        } catch (Exception e) {
            beanValidation(entity);
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            return false;
        }
    }

    /**
     * Encuentra un objeto por su id (el de la clase)
     *
     * @param id es el id del objeto
     * @return El objeto si se encuentra. <br>Sino, devuelve null
     */
    public T find(Object id) {
        EntityManager em = getEntityManager();
        try {
            if (em != null && id != null) {
                return em.find(entityClass, id);
            } else {
                System.out.println("Entity manager es nulo o la entidad es nula");
                return null;
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

    /**
     * Este método devuelve todos los objetos de una clase
     *
     * @return Una lista de objetos de la clase si todo va bien<br> Si no
     * devuelve una lista vacía
     */
    public List<T> findAll() {
        EntityManager em = getEntityManager();
        try {
            if (em != null) {
                javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
                cq.select(cq.from(entityClass));
                return em.createQuery(cq).getResultList();
            } else {
                System.out.println("Entity manager es nulo");
                return Collections.emptyList();
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            return Collections.emptyList();
        }

    }

    /**
     * Este método sirve para paginar y devuelve un rango de entidades
     *
     * @param desde entero que indica desde donde comenzará
     * @param cuantosReg La cantidad de registros que va a traer
     * @return una lista de entidades si todo va bien <br> sino devuelve una
     * lista vacía
     */
    public List<T> findRange(int desde, int cuantosReg) {
        EntityManager em = getEntityManager();
        try {
            if (em != null && String.valueOf(desde) != null && String.valueOf(cuantosReg) != null) {
                javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
                cq.select(cq.from(entityClass));
                javax.persistence.Query q = em.createQuery(cq);
                q.setMaxResults(cuantosReg);
                q.setFirstResult(desde);
                return q.getResultList();
            } else {
                System.out.println("ALGO ES NULO");
                return Collections.emptyList();
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            return Collections.emptyList();
        }

    }

    /**
     * Este método cuenta cuantas entidades hay en la bd
     *
     * @return un entero con la cantidad o 0
     */
    public int count() {
        EntityManager em = getEntityManager();
        try {
            if (em != null) {
                javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
                javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
                cq.select(em.getCriteriaBuilder().count(rt));
                javax.persistence.Query q = em.createQuery(cq);
                return ((Long) q.getSingleResult()).intValue();
            } else {
                System.out.println("ALGO ES NULO");
                return 0;
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            return 0;
        }

    }

    /**
     * Este método se encarga de validar los datos que se registran en el bean,
     * y verifica que sean válidos. Cómo por ejemplo, si En alguna tabla hay un
     * dato que no tiene que ser mayor a 20 y tiene que ser un string, y el dato
     * ingresado es mayor (en longitud) a 20 o es de otro tipo, arrojará un
     * error donde da la razón y el campo que violó la validación, ya sea porque
     * es de otro dato o porque es mayor a 20
     *
     * @param entity Es la entidad que se está evaluando
     */
    public void beanValidation(T entity) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
        if (constraintViolations.size() > 0) {
            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<T> cv = iterator.next();
                System.err.println(cv.getRootBeanClass().getName() + "." + cv.getPropertyPath() + " " + cv.getMessage());
            }
        }
    }

}
