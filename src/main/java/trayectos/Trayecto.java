package trayectos;

import java.util.List;

public class Trayecto {

  private final List<Tramo> tramos;

  public Trayecto(List<Tramo> tramos) {
    this.tramos = tramos;
  }

  public void agregarTramo(Tramo tramo) {
    this.tramos.add(tramo);
  }

  public double distanciaTotal() {
    return tramos.stream().mapToDouble(tramo -> tramo.distancia()).sum();
  }

  public boolean puedoCompartir() {
    return tramos.stream().allMatch(tramo -> tramo.esVehiculoParticularOServicioContratado());
  }
 
}

/*
 * Cálculo de distancia entre tramos Para calcular la distancia entre los
 * distintos tramos que conforman un trayecto se debe tener en cuenta que:
 * 
 * - Si el medio de transporte es vehículo particular, servicio contratado o
 * bicicleta/pie, entonces la distancia entre el punto de inicio y el punto de
 * llegada debe ser calculado utilizando un Servicio Externo.
 * 
 * 
 * - Si el medio de transporte es público entonces cada parada/estación debe
 * conocer cuál es su distancia a la próxima. Es necesario tener en cuenta que
 * no siempre los transportes públicos realizan los mismos recorridos a la ida y
 * a la vuelta. La información de las paradas debe poder ser precargada por un
 * operador del sistema y luego utilizada por cualquier usuario, sin importar su
 * organización.
 */