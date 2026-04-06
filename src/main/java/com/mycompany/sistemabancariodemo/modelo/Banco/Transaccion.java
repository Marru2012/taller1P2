/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.Banco;
import com.mycompany.sistemabancariodemo.modelo.abstractas.*;
import com.mycompany.sistemabancariodemo.modelo.enums.*;
import com.mycompany.sistemabancariodemo.modelo.excepciones.*;
import java.time.LocalDateTime;
/**
 *
 * @author Dell
 */
public class Transaccion {
    
    private String id;
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;
    private double monto;
    private EstadoTransaccion estado;
    private LocalDateTime fecha;
    private String descripcion;

    public Transaccion(String id, Cuenta cuentaOrigen, Cuenta cuentaDestino, double monto, EstadoTransaccion estado, LocalDateTime fecha, String descripcion)throws  DatoInvalidoException {
        setId(id);
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        setMonto(monto);
        this.estado = estado;
        this.fecha = LocalDateTime.now();
        setDescripcion(descripcion);
    }
    
    public void cambiarEstado(EstadoTransaccion nuevo)throws EstadoTransaccionInvalidoException {
    if (estado == EstadoTransaccion.PENDIENTE) {
        if (nuevo == EstadoTransaccion.PROCESANDO || nuevo == EstadoTransaccion.RECHAZADA) {
            estado = nuevo;
            return;
        }
    }

    if (estado == EstadoTransaccion.PROCESANDO) {
        if (nuevo == EstadoTransaccion.COMPLETADA || nuevo == EstadoTransaccion.RECHAZADA) {
            estado = nuevo;
            return;
        }
    }

    if (estado == EstadoTransaccion.COMPLETADA) {
        if (nuevo == EstadoTransaccion.REVERTIDO) {
            estado = nuevo;
            return;
        }
    }

    throw new EstadoTransaccionInvalidoException (estado,nuevo);
}
    public String generaComprobante(){
        return "ID: "+ id +
                "\nMonto: $" + monto +
                "\nEstado: " + estado +
                "\nFecha: " + fecha +
                "\nDescripcion: " +descripcion;
    }
    
        public String getId() {
        return id;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public double getMonto() {
        return monto;
    }

    public EstadoTransaccion getEstado() {
        return estado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    
    public void setId(String id)throws DatoInvalidoException{
        if(id==null||id.isEmpty() ){
        throw new DatoInvalidoException("id no puede ser nulo o vacio","id",id); 
    }
        this.id=id;
    }
    
    public void setDescripcion(String descripcion)throws DatoInvalidoException {
        if(descripcion==null||descripcion.isEmpty()){
            throw new DatoInvalidoException("decripcion no puede ser nula ni vacia","Descripcion",descripcion);
        }
        this.descripcion = descripcion;
    }
    public void setMonto(double monto)throws DatoInvalidoException{
        if(monto<=0){
            throw new DatoInvalidoException("monto no puede ser cero 0 menor a 0","monto",monto);
        }
        this.monto=monto;
    }
    
    
}
