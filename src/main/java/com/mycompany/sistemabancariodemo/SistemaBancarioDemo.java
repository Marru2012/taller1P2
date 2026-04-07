/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistemabancariodemo;

import com.mycompany.sistemabancariodemo.modelo.banco.Banco;
import com.mycompany.sistemabancariodemo.modelo.personas.*;
import com.mycompany.sistemabancariodemo.modelo.empleados.*;
import com.mycompany.sistemabancariodemo.modelo.cuentas.*;
import com.mycompany.sistemabancariodemo.modelo.enums.*;
import com.mycompany.sistemabancariodemo.modelo.excepciones.*;
import com.mycompany.sistemabancariodemo.modelo.abstractas.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Dell
 */

public class SistemaBancarioDemo {

    public static void main(String[] args) {
        
        Banco banco = new Banco("GomezYasociados");
        
        try{
            //1.regsitrar clientes 
            
            ClienteNatural clienteN1= new ClienteNatural("1","Jorge","Gomez",LocalDate.of(2006, 1, 1),"jorgegomez2012@gmail.com",TipoDeDocumento.CEDULA,"1043");
            
            ClienteNatural clienteN2= new ClienteNatural("2","Ricardo","Malo",LocalDate.of(1998, 5, 10),"RicardoMalo1998@gmail.com",TipoDeDocumento.CEDULA,"1143");
            
            ClienteEmpresarial clienteE3= new ClienteEmpresarial("3","EmpresaSH","SAS",LocalDate.of(2005, 1, 1),"SamuelHernandez@2005gmil.com","900123","EmpresaSH","Samuel");
            
            banco.registrarCliente(clienteN1);
            banco.registrarCliente(clienteN2);
            banco.registrarCliente(clienteE3);
            //2.crear cuenta papuss
            
            CuentaCorriente corriente=new CuentaCorriente("001",1000,500,50);
            
            CuentaAhorros ahorros=new CuentaAhorros("002",2000,0.03,3);
            
            CuentaCredito credito=new CuentaCredito("003",0,5000,0.1,0);
            
            banco.abrirCuenta("1", corriente);
            banco.abrirCuenta("2", ahorros);
            banco.abrirCuenta("3", credito);
            
            //3 depositar y bloquear la cuenta
            
            corriente.depositar(500);
            
            corriente.setBloqueada(true);
            try{
                corriente.depositar(100);
            }catch (CuentaBloqueadaException e){
                System.out.println(e.getMessage());
            }
            corriente.setBloqueada(false);
            
            //4.retiro y saldo insuficiente
            corriente.retirar(200);
            try{
                corriente.retirar(1000);
            }catch(SaldoInsuficienteException e){
                System.out.println("Saldo actual: " + e.getSaldoActual());
                System.out.println(e.getMessage());
            }
            //5.tranferir
            corriente.transferir(ahorros, 100);
            
            //6.polimorfismo empleados
            Empleado[]empleados=new Empleado[3];
            
            empleados[0]= new Cajero("10", "Luis", "Lopez",LocalDate.of(1995, 2, 2), "l@mail.com","L1", LocalDate.now(), 1000, true,Turno.MAÑANA, "Sucursal 1");
            
            empleados[1] = new AsesorFinanciero(2000, "023", "Miguel","Oñate", LocalDate.of(1984,5,4),"MiguelOñate@gmail.com", "L2",LocalDate.of(2000, 12, 2), 35000,true,0.23,2000);
            
            empleados[2] = new GerenteSucursal("456","Ana","Gomez",LocalDate.of(1988, 2, 20),"ana@email.com","GS001",LocalDate.of(2015, 6, 10),4000000,true,"Sucursal 3",1500000);
            
            for (Empleado e : empleados) {
                System.out.println(e.calcularSalario());
            }
            
             // 7. Polimorfismo cuentas
            Cuenta[] cuentas = {corriente, ahorros, credito};

            for (Cuenta c : cuentas) {
                System.out.println(c.calcularInteres());
            }
            //  8. Transacción inválida
            Transaccion t = new Transaccion("T001",credito,ahorros,50000,EstadoTransaccion.PENDIENTE,LocalDateTime.now(),"Tranferenci"); 

        } catch (DatoInvalidoException e){
            System.out.println(e.getMessage());
        }
        
    }
}
