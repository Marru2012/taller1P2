package com.mycompany.sistemabancariodemo.modelo.empleados;

import com.mycompany.sistemabancariodemo.modelo.abstractas.Empleado;
import com.mycompany.sistemabancariodemo.modelo.enums.*;
import com.mycompany.sistemabancariodemo.modelo.excepciones.*;
import java.time.LocalDate;

public class GerenteSucursal extends Empleado {

    private  double bonoFijoGerencia = 500000;
    private String sucursal;
    private double presupuestoAnual;
    private Empleado[] empleadosAcargo;
    private int contadorEmpleados=0;

    public GerenteSucursal(String id, String nombre, String apellido,
                           LocalDate fechaNacimiento, String email,
                           String legajo, LocalDate fechaContratacion,
                           double salarioBase, boolean activo,
                           String sucursal, double presupuestoAnual) {

        super(id, nombre, apellido, fechaNacimiento, email,
              legajo, fechaContratacion, salarioBase, activo);

        this.sucursal = sucursal;
        this.presupuestoAnual = presupuestoAnual;
        this.empleadosAcargo = new Empleado[30];
    }
    
    public void agregarEmpleado(Empleado empleado)throws CapacidadExcedidaException{
        
        if(contadorEmpleados>=30){
            throw new CapacidadExcedidaException("Capacidad de empleados excedida",30);
        }
        empleadosAcargo[contadorEmpleados]=empleado;
        contadorEmpleados++; 
}
    public Empleado[]getEmpleadosAcargo(){
        Empleado[]copia=new Empleado[contadorEmpleados];
        System.arraycopy(empleadosAcargo, 0, copia, 0, contadorEmpleados);
        return copia;
    }
    public int getContadorEmpleados(){
        return contadorEmpleados;
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase() + calcularBono() + bonoFijoGerencia;
    }

    @Override
    public double calcularBono() {
        return calcularAntiguedad()*1000 ;
    }

    @Override
    public int calcularEdad() {
        return LocalDate.now().getYear() - getFechaNacimiento().getYear();
    }

    @Override
    public String obtenerTipo() {
        return "Gerente de Sucursal";
    }

    @Override
    public String obtenerDocumentoDeIdentidad() {
        return TipoDeDocumento.CEDULA.name()+ " " + getId();
    }
}