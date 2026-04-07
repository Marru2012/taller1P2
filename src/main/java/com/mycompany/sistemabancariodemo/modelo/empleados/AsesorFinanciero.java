package com.mycompany.sistemabancariodemo.modelo.empleados;

import com.mycompany.sistemabancariodemo.modelo.abstractas.*;
import com.mycompany.sistemabancariodemo.modelo.enums.TipoDeDocumento;
import com.mycompany.sistemabancariodemo.modelo.excepciones.*;

import java.time.LocalDate;

public class AsesorFinanciero extends Empleado {

    private double comisionBase;
    private double metasMensuales;
    private Cliente[] clientesAsignados;
    private int contadorClientesAsignados=0;
    private double ventasMes;

    public AsesorFinanciero(double ventasMes,String id, String nombre, String apellido,
                            LocalDate fechaNacimiento, String email,
                            String legajo, LocalDate fechaContratacion,
                            double salarioBase, boolean activo,
                            double comisionBase, double metasMensuales) {

        super(id, nombre, apellido, fechaNacimiento, email,
              legajo, fechaContratacion, salarioBase, activo);

        this.comisionBase = comisionBase;
        this.metasMensuales = metasMensuales;
        this.clientesAsignados = new Cliente[20];
        this.ventasMes=ventasMes;
    }
    
    public void agregarCliente(Cliente cliente)throws CapacidadExcedidaException{
        if(contadorClientesAsignados>=20){
            throw new CapacidadExcedidaException("Capacidad de clientes asignados excedidas",20);
            
        }
        clientesAsignados[contadorClientesAsignados]=cliente;
        contadorClientesAsignados++;
    }
    
    public Cliente[]getClientesAsignados(){
        Cliente[]copia= new Cliente[contadorClientesAsignados];
        System.arraycopy(clientesAsignados, 0, copia, 0, contadorClientesAsignados);
        return copia;
    }
    
    @Override
    public double calcularSalario() {
        return getSalarioBase() + calcularBono();
    }

    @Override
    public double calcularBono() {
        if (ventasMes >= metasMensuales) return comisionBase; 
        return 0;
    }
    public void registrarVenta(double monto){
        ventasMes += monto;
    }

    @Override
    public int calcularEdad() {
        return LocalDate.now().getYear() - getFechaNacimiento().getYear();
    }

    @Override
    public String obtenerTipo() {
        return "Asesor Financiero";
    }

    @Override
    public String obtenerDocumentoDeIdentidad() {
        return TipoDeDocumento.CEDULA.name()+ " " + getId();
    }
}
