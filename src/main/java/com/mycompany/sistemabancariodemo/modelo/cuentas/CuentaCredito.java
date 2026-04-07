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

    public CuentaCredito(String numeroCuenta, double saldo,  double limiteCredito,double tasaInteres,double deudaActual) throws DatoInvalidoException {
        super(numeroCuenta, saldo, false, LocalDateTime.now(), LocalDateTime.now(), "SYSTEM");
        
        setLimiteCredito(limiteCredito);
        setTasaInteres(tasaInteres);
        setDeudaActual(deudaActual);
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
    
    if(monto<=0){
        throw new DatoInvalidoException("El monto a depositar no puede ser negativo ni 0","Monto",monto);
    }
    deudaActual -= monto;
    if(deudaActual<0){
        deudaActual=0;
    }
}

    
    @Override
    public void retirar(double monto) throws CuentaBloqueadaException,SaldoInsuficienteException {
    verificarBloqueada();
    
    if(monto<=0){
        throw new DatoInvalidoException("Monto a retirar no puede ser neativo ni 0","Monto",monto);
    }
    double disponible=limiteCredito-deudaActual;
   
    if(monto > disponible){
        throw new SaldoInsuficienteException("Credito insuficiente",disponible,monto);
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
    public void setLimiteCredito(double limiteCredito){
        if(limiteCredito<0){
            throw new DatoInvalidoException("El limite de credito no puede ser un valor negativo","Limite de credito",limiteCredito);
        }
        this.limiteCredito=limiteCredito;
    }
    
        public void setTasaInteres(double tasaInteres){
        if(tasaInteres<0){
            throw new DatoInvalidoException("La tasa de interes no puede ser un valor negativo","Tasa de interes",tasaInteres);
        }
        this.tasaInteres=tasaInteres;
    }
            public void setDeudaActual(double deudaActual){
        if(deudaActual<0){
            throw new DatoInvalidoException("Deuda actual no puede ser un valor negativo","deuda actual",deudaActual);
        }
        this.deudaActual=deudaActual;
    }
}