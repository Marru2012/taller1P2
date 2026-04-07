/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.cuentas;

import com.mycompany.sistemabancariodemo.modelo.abstractas.*;
import com.mycompany.sistemabancariodemo.modelo.interfaces.*;
import com.mycompany.sistemabancariodemo.modelo.excepciones.*;
import com.mycompany.sistemabancariodemo.modelo.enums.*;
import java.time.LocalDateTime;

/**
 * 
 * @author LUIS MIGUEL
 **/

public  class CuentaAhorros extends Cuenta implements Consultable, Transaccionable, Auditable {

    

    private double tasaInteres;
    private int retirosMesActual;
    private int maxRetirosMes;

    public CuentaAhorros(String numeroCuenta, double saldo, boolean bloqueada, LocalDateTime fechaCreacion, LocalDateTime ultimaModificacion, String usuarioModificacion, double tasaInteres, int maxRetirosMes) throws DatoInvalidoException {

        super(numeroCuenta, saldo, bloqueada, fechaCreacion, ultimaModificacion, usuarioModificacion);

        this.tasaInteres = tasaInteres;
        this.maxRetirosMes = maxRetirosMes;
        this.retirosMesActual = 0;
    }

    
    //Metodos abstractos heredados-Cuenta
    @Override
    public double calcularInteres(){
        return (getSaldo()*tasaInteres)/12;
    }
    @Override
    public  TipoDeCuenta getTipoCuenta(){
        return TipoDeCuenta.AHORROS;
    }
    @Override
    public double getLimiteRetiro(){
        return getSaldo();
    }
    

    //Metodos abstracto heredable-Transaccionable
    @Override
    public void depositar(double monto)throws CuentaBloqueadaException {
       verificarBloqueada();
       try{
           setSaldo(monto+getSaldo());
       }catch(DatoInvalidoException e){
            throw new RuntimeException(e.getMessage());
       }
       
        
    }

    @Override
    public void retirar(double monto)throws CuentaBloqueadaException,SaldoInsuficienteException {
        verificarBloqueada();
        try{
         if (monto > getSaldo()) {
            throw new SaldoInsuficienteException("Saldo insuficiente",getSaldo(),monto);
        }
        setSaldo(getSaldo()-monto);   
        }catch (DatoInvalidoException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public double calcularComision(double monto) {
        return getSaldo() * 0.01;
    }

   

    @Override
    public String obtenerResumen() {
        return "Cuenta Ahorros - Saldo: " + getSaldo();
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
    
    public double getTasaInteres() {
        return tasaInteres;
    }

    public int getRetirosMesActual() {
        return retirosMesActual;
    }

    public int getMaxRetirosMes() {
        return maxRetirosMes;
    }
    
}