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
 */
public class CuentaCredito extends Cuenta implements Consultable, Transaccionable, Auditable {

    private double limiteCredito;

    public CuentaCredito(String numeroCuenta, double saldo, boolean bloqueada, LocalDateTime fechaCreacion, LocalDateTime ultimaModificacion, String usuarioModificacion, double limiteCredito) throws DatoInvalidoException {

        super(numeroCuenta, saldo, bloqueada, fechaCreacion, ultimaModificacion, usuarioModificacion);
        
        this.limiteCredito = limiteCredito;
    }

    @Override
    public double calcularInteres() {
        return getSaldo() * 0.02;
    }

    @Override
    public double getLimiteRetiro() {
        return limiteCredito;
    }

    @Override
    public String getTipoCuenta() {
        return "Credito";
    }

    @Override
    public void depositar(double monto) {
        try {
            setSaldo(getSaldo() - monto); // paga deuda
        } catch (DatoInvalidoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void retirar(double monto) {
        if (monto > limiteCredito) {
            throw new RuntimeException("Excede el límite de crédito");
         }

        try {
            setSaldo(getSaldo() + monto);
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
        return "Cuenta Crédito - Deuda: " + getSaldo();
    }

    @Override
    public boolean estaActivo() {
        return !isBloqueada();
    }

    @Override
    public String obtenerTipo() {
        return "CuentaCredito";
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