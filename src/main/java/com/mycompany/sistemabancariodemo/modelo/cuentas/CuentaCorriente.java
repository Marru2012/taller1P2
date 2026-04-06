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

public class CuentaCorriente extends Cuenta implements Consultable, Transaccionable, Auditable {

    private double limiteDescubierto;

    public CuentaCorriente(String numeroCuenta, double saldo, boolean bloqueada, LocalDateTime fechaCreacion, LocalDateTime ultimaModificacion, String usuarioModificacion, double limiteDescubierto) throws DatoInvalidoException {

        super(numeroCuenta, saldo, bloqueada, fechaCreacion, ultimaModificacion, usuarioModificacion);
        this.limiteDescubierto = limiteDescubierto;
    }

    @Override
    public double calcularInteres() {
        return 0;
    }

    @Override
    public double getLimiteRetiro() {
        return getSaldo() + limiteDescubierto;
    }

    @Override
    public String getTipoCuenta() {
        return "Corriente";
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
        if (monto > getSaldo() + limiteDescubierto) {
            throw new RuntimeException("Excede límite permitido");
        }

        try {
            setSaldo(getSaldo() - monto);
        } catch (DatoInvalidoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public double calculaComision() {
        return 0;
    }

    @Override
    public boolean aceptaNotificaciones() {
        return true;
    }

    @Override
    public String ObtenerResumen() {
        return "Cuenta Corriente - Saldo: " + getSaldo();
    }

    @Override
    public boolean estaActivo() {
        return !isBloqueada();
    }

    @Override
    public String obtenerTipo() {
        return "CuentaCorriente";
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