/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.interfaces;

import com.mycompany.sistemabancariodemo.modelo.excepciones.*;

/**
 *
 * @author Dell
 */

//2.INTEFACE-TRANSACCIONABLE

public interface Transaccionable {
    
    public void depositar(double monto)throws CuentaBloqueadaException;
    
    public void retirar(double monto)throws CuentaBloqueadaException,SaldoInsuficienteException;
    
    public double calcularComision(double monto);
    
    
}
