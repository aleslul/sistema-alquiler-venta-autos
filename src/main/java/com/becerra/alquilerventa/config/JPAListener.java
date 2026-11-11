package com.becerra.alquilerventa.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class JPAListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            JPAUtil.getEntityManagerFactory();
            System.out.println(">> Unidad de persistencia alquilerventaPU iniciada correctamente");
        } catch (Throwable ex) {
            System.err.println(">> ERROR al iniciar JPA en el arranque: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        JPAUtil.cerrar();
    }
}