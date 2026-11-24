package com.becerra.alquilerventa.dao.impl;

import com.becerra.alquilerventa.config.JPAUtil;
import com.becerra.alquilerventa.dao.PersonaDAO;
import com.becerra.alquilerventa.modelo.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PersonaDAOImpl implements PersonaDAO {

    @Override
    @SuppressWarnings("unchecked")
    public List<Persona> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Persona> lista = new ArrayList<>();
        try {
            StoredProcedureQuery sp = em.createStoredProcedureQuery("sp_listar_personas");
            List<Object[]> filas = sp.getResultList();
            for (Object[] r : filas) {
                String tipoReg = str(r[10]);
                Persona p = switch (tipoReg != null ? tipoReg : "") {
                    case "Trabajador" -> {
                        Trabajador t = new Trabajador();
                        t.setCodigoEmpleado(str(r[9]));
                        yield t;
                    }
                    case "Cliente Natural" -> new ClienteNatural();
                    case "Cliente Jurídico" -> new ClienteJuridico();
                    default -> new Persona();
                };

                p.setPersonaId(entero(r[0]));
                p.setTipoDocumentoId(entero(r[1]));
                p.setNombreTipoDocumento(str(r[2]));
                p.setNumeroDocumento(str(r[3]));
                p.setCorreo(str(r[4]));
                p.setTelefono(str(r[5]));
                p.setDireccion(str(r[6]));
                p.setEstadoUsuario(str(r[7]));
                p.setPasswordHash(str(r[8]));
                p.setTipoRegistro(tipoReg);
                p.setNombreCompleto(str(r[11]));
                lista.add(p);
            }
            return lista;
        } finally {
            em.close();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Persona obtenerPorId(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            StoredProcedureQuery sp = em.createStoredProcedureQuery("sp_obtener_persona");
            entrada(sp, 1, Integer.class, id);
            List<Object[]> filas = sp.getResultList();
            if (filas.isEmpty()) return null;

            Object[] r = filas.get(0);
            
            String codigo = str(r[8]);
            Persona p = switch (codigo != null ? codigo : "") {
                case Persona.CLIENTE_NATURAL -> {
                    ClienteNatural c = new ClienteNatural();
                    c.setNombres(str(r[9]));
                    c.setApellidos(str(r[10]));
                    c.setFechaNacimiento(fecha(r[11]));
                    c.setLicenciaConducir(str(r[12]));
                    c.setFechaVencimientoLicencia(fecha(r[13]));
                    yield c;
                }
                case Persona.CLIENTE_JURIDICO -> {
                    ClienteJuridico c = new ClienteJuridico();
                    c.setRazonSocial(str(r[14]));
                    c.setRepresentanteLegal(str(r[15]));
                    c.setGiroNegocio(str(r[16]));
                    yield c;
                }
                case Persona.TRABAJADOR -> {
                    Trabajador t = new Trabajador();
                    t.setSucursalId(entero(r[17]));
                    t.setRolTrabajadorId(entero(r[18]));
                    t.setNombres(str(r[19]));
                    t.setApellidos(str(r[20]));
                    t.setCodigoEmpleado(str(r[21]));
                    yield t;
                }
                default -> new Persona();
            };

            p.setPersonaId(entero(r[0]));
            p.setTipoDocumentoId(entero(r[1]));
            p.setNumeroDocumento(str(r[2]));
            p.setCorreo(str(r[3]));
            p.setTelefono(str(r[4]));
            p.setDireccion(str(r[5]));
            p.setEstadoUsuario(str(r[6]));
            p.setPasswordHash(str(r[7]));
            return p;
        } finally {
            em.close();
        }
    }

    @Override
    public void insertar(Persona p) {
        enTransaccion(em -> {
            StoredProcedureQuery sp = em.createStoredProcedureQuery("sp_insertar_persona");
            entrada(sp, 1, Integer.class, p.getTipoDocumentoId());
            entrada(sp, 2, String.class, p.getNumeroDocumento());
            entrada(sp, 3, String.class, p.getCorreo());
            entrada(sp, 4, String.class, p.getTelefono());
            entrada(sp, 5, String.class, p.getDireccion());
            entrada(sp, 6, String.class, p.getEstadoUsuario());
            entrada(sp, 7, String.class, p.getPasswordHash());
            sp.registerStoredProcedureParameter(8, Integer.class, ParameterMode.OUT);
            sp.execute();
            p.setPersonaId((Integer) sp.getOutputParameterValue(8));

            guardarRol(em, p);
        });
    }

    @Override
    public void actualizar(Persona p) {
        enTransaccion(em -> {
            StoredProcedureQuery sp = em.createStoredProcedureQuery("sp_actualizar_persona");
            entrada(sp, 1, Integer.class, p.getPersonaId());
            entrada(sp, 2, Integer.class, p.getTipoDocumentoId());
            entrada(sp, 3, String.class, p.getNumeroDocumento());
            entrada(sp, 4, String.class, p.getCorreo());
            entrada(sp, 5, String.class, p.getTelefono());
            entrada(sp, 6, String.class, p.getDireccion());
            entrada(sp, 7, String.class, p.getEstadoUsuario());
            entrada(sp, 8, String.class, p.getPasswordHash());
            sp.execute();

            StoredProcedureQuery spLimpiar = em.createStoredProcedureQuery("sp_limpiar_roles_persona");
            entrada(spLimpiar, 1, Integer.class, p.getPersonaId());
            entrada(spLimpiar, 2, String.class, p.getCodigoTipo());
            spLimpiar.execute();

            guardarRol(em, p);
        });
    }

    @Override
    public void eliminar(int id) {
        enTransaccion(em -> {
            StoredProcedureQuery sp = em.createStoredProcedureQuery("sp_eliminar_persona");
            entrada(sp, 1, Integer.class, id);
            sp.execute();
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TipoDocumento> listarTiposDocumento() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createStoredProcedureQuery("sp_listar_tipos_documento", TipoDocumento.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RolTrabajador> listarRolesTrabajador() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createStoredProcedureQuery("sp_listar_roles_trabajador", RolTrabajador.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Sucursal> listarSucursales() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Sucursal> lista = new ArrayList<>();
        try {
            StoredProcedureQuery sp = em.createStoredProcedureQuery("sp_listar_sucursales_combo");
            List<Object[]> filas = sp.getResultList();
            for (Object[] r : filas) {
                Sucursal s = new Sucursal();
                s.setSucursalId(entero(r[0]));
                s.setNombre(str(r[1]));
                lista.add(s);
            }
            return lista;
        } finally {
            em.close();
        }
    }

    private void guardarRol(EntityManager em, Persona p) {
        if (p instanceof ClienteNatural c) {
            StoredProcedureQuery sp = em.createStoredProcedureQuery("sp_guardar_cliente_natural");
            entrada(sp, 1, Integer.class, c.getPersonaId());
            entrada(sp, 2, String.class, c.getNombres());
            entrada(sp, 3, String.class, c.getApellidos());
            entrada(sp, 4, LocalDate.class, c.getFechaNacimiento());
            entrada(sp, 5, String.class, c.getLicenciaConducir());
            entrada(sp, 6, LocalDate.class, c.getFechaVencimientoLicencia());
            sp.execute();
        } else if (p instanceof ClienteJuridico c) {
            StoredProcedureQuery sp = em.createStoredProcedureQuery("sp_guardar_cliente_juridico");
            entrada(sp, 1, Integer.class, c.getPersonaId());
            entrada(sp, 2, String.class, c.getRazonSocial());
            entrada(sp, 3, String.class, c.getRepresentanteLegal());
            entrada(sp, 4, String.class, c.getGiroNegocio());
            sp.execute();
        } else if (p instanceof Trabajador t) {
            StoredProcedureQuery sp = em.createStoredProcedureQuery("sp_guardar_trabajador");
            entrada(sp, 1, Integer.class, t.getPersonaId());
            entrada(sp, 2, Integer.class, t.getSucursalId());
            entrada(sp, 3, Integer.class, t.getRolTrabajadorId());
            entrada(sp, 4, String.class, t.getNombres());
            entrada(sp, 5, String.class, t.getApellidos());
            entrada(sp, 6, String.class, t.getCodigoEmpleado());
            sp.execute();
        }
    }

    private void entrada(StoredProcedureQuery sp, int posicion, Class<?> tipo, Object valor) {
        sp.registerStoredProcedureParameter(posicion, tipo, ParameterMode.IN);
        sp.setParameter(posicion, valor);
    }

    private void enTransaccion(Consumer<EntityManager> operacion) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            operacion.accept(em);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    private String str(Object o) {
        return o == null ? null : o.toString();
    }

    private int entero(Object o) {
        return o == null ? 0 : ((Number) o).intValue();
    }

    private LocalDate fecha(Object o) {
        if (o == null) return null;
        if (o instanceof LocalDate ld) return ld;
        if (o instanceof Date d) return d.toLocalDate();
        return LocalDate.parse(o.toString());
    }
}