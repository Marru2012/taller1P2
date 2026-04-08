/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.abstractas;
import com.mycompany.sistemabancariodemo.modelo.excepciones.*;
import com.mycompany.sistemabancariodemo.modelo.cuentas.*;
import java.time.LocalDate;
/**
 *
 * @author Dell
 */
public abstract class Empleado extends Persona{

    private String legajo;
    private LocalDate fechaContratacion;
    private double salarioBase;
    private boolean activo;
    
    public Empleado(String id,String nombre,String apellido,LocalDate fechaNacimiento,String email,String legajo,LocalDate fechaContratacion,double salarioBase,boolean activo) throws DatoInvalidoException {
        super(id,nombre,apellido,fechaNacimiento,email);
        
        if(fechaContratacion.isAfter(LocalDate.now())){
            throw new DatoInvalidoException("La fecha de contratacion no puede ser mayor de la actual","fecha de contratacion",fechaContratacion);
        }
        if(salarioBase<=0){
            throw new DatoInvalidoException("Salario debe ser mayor a 0","Salario base",salarioBase);
        }
        setLegajo(legajo);
        this.fechaContratacion=fechaContratacion;
        setSalarioBase(salarioBase);
        setIsActivo(activo);
    }
    public abstract double calcularSalario();
    public abstract double calcularBono();
    public abstract void aprobarCredito(CuentaCredito cuentas);
    
    public int calcularAntiguedad(){
        return LocalDate.now().getYear()-fechaContratacion.getYear();
    }
    
    
    public String getLegajo() {
        return legajo;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public boolean isActivo() {
        return activo;
    }
    public void setIsActivo(boolean activo){
        this.activo=activo;
}
   public void setSalarioBase(double salarioBase)throws DatoInvalidoException{
       if(salarioBase<=0){
           throw new DatoInvalidoException("Salario no puede ser 0 ni negativo","Salario base",salarioBase);
       }
       this.salarioBase=salarioBase;
   }
   public void setLegajo(String legajo)throws DatoInvalidoException{
       if(legajo==null || legajo.isEmpty()){
           throw new DatoInvalidoException("Legajo no pude ser nulo ni vacio","Legajo",legajo);
       }
       this.legajo=legajo;
   }
}


