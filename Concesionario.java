package Concesionario;

import java.util.concurrent.Semaphore;

public class Concesionario {

    private final Semaphore semaforo;

    public Concesionario() {
        // semaforo para solucionar los problemas de sincronizacion
        //permito hasta 4 vehiculos al mismo tiempo
        this.semaforo = new Semaphore(4); 
    }

    public void probar(String nombre, int numeroVehiculo) throws InterruptedException {
        try {
            //Creo la seccion critica para que ningun otro hilo pueda acceder
            semaforo.acquire();  
            
            System.out.println(nombre + " probando vehiculo " + numeroVehiculo);
            
            //Mando el hilo de la persona que prueba el coche a dormir
            Thread.sleep(3000); 

            System.out.println(nombre + " termino de probar el vehiculo " + numeroVehiculo);

        } catch (InterruptedException e) {
            System.err.println("Error en la prueba de vehículo de " + nombre + ": " + e.getMessage());
            
        } finally {
            // abro la seccion critica
            semaforo.release();  
            System.out.println(nombre + " ha salido del concesionario");

        }
    }
}
