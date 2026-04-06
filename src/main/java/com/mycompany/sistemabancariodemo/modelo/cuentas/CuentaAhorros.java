/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.cuentas;

import com.mycompany.sistemabancariodemo.modelo.abstractas.Cuenta;
import com.mycompany.sistemabancariodemo.modelo.interfaces.*;
import com.mycompany.sistemabancariodemo.modelo.excepciones.*;
import java.time.LocalDateTime;

/**
 * 
 * @author LUIS MIGUEL
 **/

public abstract class CuentaAhorros extends Cuenta implements Consultable, Transaccionable, Auditable {

    private double tasaInteres;
    private int retirosMesActual;
    private int maxRetirosMes;

    public CuentaAhorros(String numeroCuenta, double saldo, boolean bloqueada, LocalDateTime fechaCreacion, LocalDateTime ultimaModificacion, String usuarioModificacion, double tasaInteres, int maxRetirosMes) throws DatoInvalidoException {

        super(numeroCuenta, saldo, bloqueada, fechaCreacion, ultimaModificacion, usuarioModificacion);

        this.tasaInteres = tasaInteres;
        this.maxRetirosMes = maxRetirosMes;
        this.retirosMesActual = 0;
    }

    @Override
    public void depositar(double monto) {
        try {
            setSaldo(getSaldo() + monto);
        } catch (DatoInvalidoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void retirar(double monto) {
        if (monto > getSaldo()) {
            throw new RuntimeException("Saldo insuficiente");
        }

        try {
            setSaldo(getSaldo() - monto);
        } catch (DatoInvalidoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public double calculaComision() {
        return getSaldo() * 0.01;
    }

    @Override
    public boolean aceptaNotificaciones() {
        return true;
    }

    @Override
    public String ObtenerResumen() {
        return "Cuenta Ahorros - Saldo: " + getSaldo();
    }

    @Override
    public boolean estaActivo() {
        return !isBloqueada();
    }

    @Override
    public String obtenerTipo() {
        return "CuentaAhorros";
    }

    @Override
    public LocalDateTime obetenerFechaDeCreacion() {
        return getFechaCreacion();
    }

    @Override
    public LocalDateTime obtenerUltimaModificacion() {
        return getUltimaModificacion();
    }

    @Override
    public String obtenerUsuarioModificacion() {
        return getUsuarioModificacion();
    }

    @Override
    public void registrarModificacion(String usuario) {
    }
}