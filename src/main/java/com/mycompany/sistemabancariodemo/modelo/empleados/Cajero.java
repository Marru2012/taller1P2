package com.mycompany.sistemabancariodemo.modelo.empleados;


 

import com.mycompany.sistemabancariodemo.modelo.abstractas.Empleado;
import com.mycompany.sistemabancariodemo.modelo.cuentas.CuentaCredito;
import com.mycompany.sistemabancariodemo.modelo.enums.TipoDocumento;
import com.mycompany.sistemabancariodemo.modelo.enums.Turno;
import com.mycompany.sistemabancariodemo.modelo.excepciones.*;
import java.time.LocalDate;

public class Cajero extends Empleado {

    private Turno turno;
    private String sucursalAsignada;
    private int transaccionesDia;

    public Cajero(String id, String nombre, String apellido, LocalDate fechaNacimiento, String email,
                  String legajo, LocalDate fechaContratacion, double salarioBase, 
                  Turno turno, String sucursalAsignada) {

        super(id, nombre, apellido, LocalDate.now(), email, legajo, LocalDate.now(), salarioBase, true);

        this.turno = turno;
        this.sucursalAsignada = sucursalAsignada;
        this.transaccionesDia = 0;
    }
    
    @Override
    public void aprobarCredito(CuentaCredito credito){
        throw new PermisoInsuficienteException("El cajero no puede aprobar creditos");
    }
    
    public void registrarTransaccion() {
        transaccionesDia++;
    }


    @Override
    public double calcularSalario() {
        return getSalarioBase() + calcularBono();
    }

    @Override
    public double calcularBono() {
        return transaccionesDia * 1000;
    }



    @Override
    public int calcularEdad() {
        return LocalDate.now().getYear() - getFechaNacimiento().getYear();
    }

    @Override
    public String obtenerTipo() {
        return "Cajero";
    }

    @Override
    public String obtenerDocumentoDeIdentidad() {
        return TipoDocumento.CEDULA.name() + "" + getId();
    }


    public Turno getTurno() {
        return turno;
    }

    public String getSucursalAsignada() {
        return sucursalAsignada;
    }

    public int getTransaccionesDia() {
        return transaccionesDia;
    }

   
   public void setTransaccionDia (int transaccionesDia)throws DatoInvalidoException{
        if(transaccionesDia<0){
            throw new DatoInvalidoException("Transacciones no puede ser un numero negativo","Transacciones dia",transaccionesDia);
        }
        this.transaccionesDia=transaccionesDia;
    }

   
}