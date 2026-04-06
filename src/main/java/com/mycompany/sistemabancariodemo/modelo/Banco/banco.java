package com.mycompany.sistemabancariodemo.modelo.banco;

import com.mycompany.sistemabancariodemo.modelo.abstractas.Cuenta;
import com.mycompany.sistemabancariodemo.modelo.abstractas.Empleado;
import com.mycompany.sistemabancariodemo.modelo.abstractas.Cliente;
import com.mycompany.sistemabancariodemo.modelo.excepciones.CapacidadExcedidaException;
import com.mycompany.sistemabancariodemo.modelo.excepciones.ClienteNoEncontradoException;
import com.mycompany.sistemabancariodemo.modelo.interfaces.*;
import java.time.LocalDateTime;

public class banco implements Auditable {

    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;
    private String nombre;
    private Empleado[] empleados;
    private Cliente[] clientes;
    private Cuenta[] cuentas;
    private int totalEmpleados;
    private int totalClientes;
    private int totalCuentas;

    public banco(String nombre) {
    
    this.nombre = nombre;
    this.fechaCreacion = LocalDateTime.now();
    this.ultimaModificacion = LocalDateTime.now();
    this.usuarioModificacion = "SYSTEM";
    this.empleados = new Empleado[50];
    this.clientes = new Cliente[200];
    this.cuentas = new Cuenta[500];
    this.totalEmpleados = 0;
    this.totalClientes = 0;
    this.totalCuentas = 0;
    }

    public void registrarCliente(Cliente c) throws CapacidadExcedidaException {
        if (totalClientes >= clientes.length) {
            throw new CapacidadExcedidaException("No caben más clientes", clientes.length);
        }

        clientes[totalClientes] = c;
        totalClientes++;
    }

  
    public void registrarEmpleado(Empleado e) throws CapacidadExcedidaException {
        if (totalEmpleados >= empleados.length) {
            throw new CapacidadExcedidaException("No caben más empleados", empleados.length);
        }

        empleados[totalEmpleados] = e;
        totalEmpleados++;
    }


    public Cliente buscarCliente(String id) throws ClienteNoEncontradoException {
        for (int i = 0; i < totalClientes; i++) {
            if (clientes[i].getId().equals(id)) {
                return clientes[i];
            }
        }

        throw new ClienteNoEncontradoException("Cliente no encontrado"+id,id);
    }

    public void abrirCuenta(String idCliente, Cuenta c)
            throws ClienteNoEncontradoException, CapacidadExcedidaException {

        Cliente cliente = buscarCliente(idCliente);

        if (totalCuentas >= cuentas.length) {
            throw new CapacidadExcedidaException("No caben más cuentas", cuentas.length);
        }

        cuentas[totalCuentas] = c;
        totalCuentas++;
    }
    
    

    public double calcularNominaTotal() {
        double total = 0;

        for (int i = 0; i < totalEmpleados; i++) {
            total = total + empleados[i].calcularSalario();
        }

        return total;
    }


    public void calcularInteresesMensuales() {
        for (int i = 0; i < totalCuentas; i++) {
            cuentas[i].calcularInteres();
        }
    
    }
 @Override
public LocalDateTime obetenerFechaDeCreacion() {
    return fechaCreacion;
}

@Override
public LocalDateTime obtenerUltimaModificacion() {
    return ultimaModificacion;
}

@Override
public String obtenerUsuarioModificacion() {
    return usuarioModificacion;
}

@Override
public void registrarModificacion(String usuario) {
    this.ultimaModificacion = LocalDateTime.now();
    this.usuarioModificacion = usuario;
}
      
}

