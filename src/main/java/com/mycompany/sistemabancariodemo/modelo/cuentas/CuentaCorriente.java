/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.cuentas;
import com.mycompany.sistemabancariodemo.modelo.abstractas.Cuenta;
import com.mycompany.sistemabancariodemo.modelo.interfaces.*;
import com.mycompany.sistemabancariodemo.modelo.excepciones.*;
import com.mycompany.sistemabancariodemo.modelo.enums.*;
import java.time.LocalDateTime;

/**
 *
 * @author LUIS MIGUEL
 */

public class CuentaCorriente extends Cuenta implements Consultable, Transaccionable, Auditable {

    private double limiteDescubierto;
    private double montoSobregiro;
    private double comisionMantenimiento;

    public CuentaCorriente(String numeroCuenta, double saldo, boolean bloqueada, LocalDateTime fechaCreacion, LocalDateTime ultimaModificacion, String usuarioModificacion, double limiteDescubierto,double montoSobregiro,double comisionMantenimiento) throws DatoInvalidoException {
        super(numeroCuenta, saldo, bloqueada, fechaCreacion, ultimaModificacion, usuarioModificacion);
        
        this.limiteDescubierto = limiteDescubierto;
        this.comisionMantenimiento=comisionMantenimiento;
        this.montoSobregiro=montoSobregiro;
        
    }
    public double calcularComisionMensual() {
        return comisionMantenimiento;
    }

    @Override
    public double calcularInteres() {
        return 0;
    }

   

    @Override
    public TipoDeCuenta getTipoCuenta() {
        return TipoDeCuenta.CORRIENTE;
    }
    @Override
    public double getLimiteRetiro(){
        return getSaldo() + montoSobregiro;
    }

    @Override
    public void depositar(double monto)throws CuentaBloqueadaException {
        verificarBloqueada();
        setSaldo(getSaldo()+monto);
    }

    @Override
    public void retirar(double monto)throws CuentaBloqueadaException,SaldoInsuficienteException {
        verificarBloqueada();
        if (monto > getLimiteRetiro() ) {
            throw new SaldoInsuficienteException("Saldo insuficiente",getSaldo(),monto);
        }
        setSaldo(getSaldo()-monto);

    }

    @Override
    public double calcularComision(double monto) {
        return 0;
    }



    @Override
    public String obtenerResumen() {
        return "Cuenta Corriente - Saldo: " + getSaldo();
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