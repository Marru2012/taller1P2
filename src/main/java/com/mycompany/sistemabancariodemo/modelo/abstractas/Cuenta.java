/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.abstractas;
import com.mycompany.sistemabancariodemo.modelo.banco.Transaccion;
import com.mycompany.sistemabancariodemo.modelo.excepciones.*;
import com.mycompany.sistemabancariodemo.modelo.enums.*;
import com.mycompany.sistemabancariodemo.modelo.personas.*;
import java.time.LocalDateTime;

/**
 *
 * @author Dell
 */
public abstract class Cuenta {


    private String numeroCuenta;
    private double saldo;
    private boolean bloqueada;
    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;
    private Transaccion[] historial=new Transaccion[20];
    private int contadorHistorial=0;
    
    public Cuenta(String numeroCuenta,double saldo,boolean bloqueada,LocalDateTime fechaCreacion,LocalDateTime ultimaModificacion,String usuarioModificacion){
        
        setNumeroCuenta(numeroCuenta);
        setSaldo(saldo);
        setBloqueada(bloqueada);
        setFechaCreacionn(fechaCreacion);
        setUltimaModificacion(ultimaModificacion);
        setUsuarioModificacion(usuarioModificacion);
    }
    
    public abstract double calcularInteres();
    public abstract double  getLimiteRetiro();
    public abstract TipoDeCuenta getTipoCuenta();
    public abstract void retirar(double monto) throws SaldoInsuficienteException,CuentaBloqueadaException;
    public abstract void depositar(double monto) throws CuentaBloqueadaException;        
    
    public void  verificarBloqueada()throws CuentaBloqueadaException{
        if(bloqueada){
            throw new CuentaBloqueadaException("La cuenta esta bloqueada");
        }
    }
    public void agregarAlhistorial(Transaccion transaccion)throws CapacidadExcedidaException{
        if(contadorHistorial>=20){
            throw new CapacidadExcedidaException("Historial lleno",20);
        }
        historial[contadorHistorial]=transaccion;
        contadorHistorial++;
    }
    public Transaccion[] getHistorial(){
        Transaccion[]copia=new Transaccion[20];
        System.arraycopy(historial,0,copia,0,contadorHistorial);
        return copia;
    }
    public void transferir(Cuenta destino, double monto)throws SaldoInsuficienteException, CuentaBloqueadaException {

    verificarBloqueada();
    destino.verificarBloqueada();

    this.retirar(monto);   
    destino.depositar(monto);
}
    
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isBloqueada() {
        return bloqueada;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getUltimaModificacion() {
        return ultimaModificacion;
    }

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public int getContadorHistorial() {
        return contadorHistorial;
    }
    
    public void  setSaldo(double saldo)throws DatoInvalidoException{
        if(saldo<0){
            throw new DatoInvalidoException("Saldo no puede ser un numero negativo","saldo",saldo);
        }
        this.saldo=saldo;
    }
    public void setNumeroCuenta(String numeroCuenta)throws  DatoInvalidoException{
        if(numeroCuenta==null || numeroCuenta.isEmpty()){
            throw new DatoInvalidoException("Numero de cuenta no puede ser nulo o vacio","Numero de cuenta",numeroCuenta);
        }
        this.numeroCuenta=numeroCuenta;
    }
    public void setUltimaModificacion(LocalDateTime ultimaModificacion) {
    this.ultimaModificacion = ultimaModificacion;
}
    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
}
    public void setFechaCreacionn(LocalDateTime fechaCreacion)throws DatoInvalidoException{
        if(fechaCreacion==null){
            throw new DatoInvalidoException("La fecha de creacion no puede ser un dato nul","fecha de creacion",fechaCreacion);
        }
    }
    public void setBloqueada(boolean bloqueada){
        this.bloqueada=bloqueada;
    }
        
        
 
        
    }


