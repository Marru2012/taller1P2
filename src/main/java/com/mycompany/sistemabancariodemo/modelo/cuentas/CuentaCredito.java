/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.cuentas;
import com.mycompany.sistemabancariodemo.modelo.abstractas.Cuenta;
import com.mycompany.sistemabancariodemo.modelo.enums.TipoDeCuenta;
import com.mycompany.sistemabancariodemo.modelo.interfaces.*;
import com.mycompany.sistemabancariodemo.modelo.excepciones.*;
import java.time.LocalDateTime;

/**
 *
 * @author LUIS MIGUEL
 */
public class CuentaCredito extends Cuenta implements Consultable, Transaccionable, Auditable {

    private double limiteCredito;
    private double tasaInteres;
    private double deudaActual;

    public CuentaCredito(String numeroCuenta, double saldo, boolean bloqueada, LocalDateTime fechaCreacion, LocalDateTime ultimaModificacion, String usuarioModificacion, double limiteCredito,double tasaInteres,double deudaActual) throws DatoInvalidoException {
        super(numeroCuenta, saldo, bloqueada, fechaCreacion, ultimaModificacion, usuarioModificacion);
        this.limiteCredito = limiteCredito;
        this.tasaInteres=tasaInteres;
        this.deudaActual=deudaActual;
    }

    @Override
    public double calcularInteres() {
        return (deudaActual * tasaInteres)/12;
    }

    @Override
    public double getLimiteRetiro() {
        return limiteCredito-deudaActual;
    }

    @Override
    public TipoDeCuenta getTipoCuenta() {
        return TipoDeCuenta.CREDITOS;
    }

    @Override
public void depositar(double monto) throws CuentaBloqueadaException {
    verificarBloqueada();
    deudaActual -= monto;
}

    
    @Override
    public void retirar(double monto) throws CuentaBloqueadaException {
    verificarBloqueada();
    if (monto > getLimiteRetiro()) {
        throw new RuntimeException("Excede el límite de crédito");
    }
    deudaActual += monto;
}


    @Override
    public double calcularComision(double monto) {
        return deudaActual * 0.01;
    }

  
    @Override
    public String obtenerResumen() {
        return "Cuenta Crédito - Deuda: " + deudaActual;
    }

    @Override
    public boolean estaActivo() {
        return !isBloqueada();
    }

    @Override
    public String obtenerTipo() {
        return getTipoCuenta().name();
    }

    @Override
    public LocalDateTime obtenerFechaCreacion() {
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
        setUltimaModificacion(LocalDateTime.now()); 
        setUsuarioModificacion(usuario);
    }
}