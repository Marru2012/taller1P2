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

    public CuentaAhorros(String numeroCuenta, double saldo, double tasaInteres, int maxRetirosMes) throws DatoInvalidoException {

        super(numeroCuenta, saldo, false,LocalDateTime.now(), LocalDateTime.now(), "SYSTEM");

        setTasaInteres(tasaInteres);
        setMaxRetirosMes(maxRetirosMes);
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
    

    
    @Override
    public void depositar(double monto)throws CuentaBloqueadaException {
       
        verificarBloqueada();
        
        if(monto<=0){
            throw new DatoInvalidoException("Monto no puede ser negativo o igual a 0","Monto",monto);
        }
       setSaldo(getSaldo()+monto);
       
        
    }

    @Override
    public void retirar(double monto)throws CuentaBloqueadaException,SaldoInsuficienteException {
        verificarBloqueada();
        
        if(monto<=0){
            throw new DatoInvalidoException("Monto a retirar no puede ser negativo o igual a 0","Monto",monto);
        }
        if(monto>getSaldo()){
            throw new SaldoInsuficienteException("Saldo insuficiente",getSaldo(),monto);
        }
        if(monto>getLimiteRetiro()){
            throw new SaldoInsuficienteException("Supera el limite de retiro",getSaldo(),monto);
        
        }
        if(retirosMesActual >= maxRetirosMes){
            throw new SaldoInsuficienteException("Limite mensual alcanzado",getSaldo(),monto);
        }
        setSaldo(getSaldo()-monto);
        retirosMesActual++;
        
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
    
    public void setTasaInteres(double tasaInteres){
        if(tasaInteres<0){
            throw new DatoInvalidoException("Valor de la tasa de interes no puede ser negativo","Tasa de interes",tasaInteres);
        }
        this.tasaInteres=tasaInteres;
    }
    public void setMaxRetirosMes(int maxRetirosMes){
        if(maxRetirosMes<0){
            throw new DatoInvalidoException("Valor de maximo retiros del mes no pueden ser negativo","Maximo retiro del mes",maxRetirosMes);
        }
        this.maxRetirosMes=maxRetirosMes;
    }
    
}