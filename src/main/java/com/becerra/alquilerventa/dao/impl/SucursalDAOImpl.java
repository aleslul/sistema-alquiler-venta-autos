package com.becerra.alquilerventa.dao.impl;

import com.becerra.alquilerventa.config.JPAUtil;
import com.becerra.alquilerventa.dao.SucursalDAO;
import com.becerra.alquilerventa.modelo.Empresa;
import com.becerra.alquilerventa.modelo.Sucursal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.sql.SQLException;
import java.util.List;

public class SucursalDAOImpl implements SucursalDAO {

    @Override
    @SuppressWarnings("unchecked")
    public List<Sucursal> listar() throws SQLException {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // Llama al procedimiento almacenado y mapea automáticamente a la Entidad Sucursal
            return em.createStoredProcedureQuery("sp_listar_sucursales", Sucursal.class)
                     .getResultList();
        } catch (Exception e) {
            throw new SQLException("Error en JPA al listar sucursales: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public Sucursal obtenerPorId(int id) throws SQLException {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Sucursal.class, id);
        } catch (Exception e) {
            throw new SQLException("Error en JPA al obtener sucursal: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public void insertar(Sucursal s) throws SQLException {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            // Obtiene la referencia de la Empresa por su ID sin hacer un SELECT adicional
            Empresa refEmpresa = em.getReference(Empresa.class, s.getEmpresaId());
            s.setEmpresa(refEmpresa);
            em.persist(s);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new SQLException("Error en JPA al insertar sucursal: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(Sucursal s) throws SQLException {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Empresa refEmpresa = em.getReference(Empresa.class, s.getEmpresaId());
            s.setEmpresa(refEmpresa);
            em.merge(s);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new SQLException("Error en JPA al actualizar sucursal: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            // remove() exige que la entidad esté administrada (Managed), por eso primero hacemos find()
            Sucursal s = em.find(Sucursal.class, id);
            if (s != null) {
                em.remove(s);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new SQLException("No se puede eliminar la sucursal porque tiene registros asociados.", e);
        } finally {
            em.close();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Empresa> listarEmpresas() throws SQLException {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createStoredProcedureQuery("sp_listar_empresas", Empresa.class)
                     .getResultList();
        } catch (Exception e) {
            throw new SQLException("Error en JPA al listar empresas: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
}