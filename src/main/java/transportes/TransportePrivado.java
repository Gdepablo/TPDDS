package transportes;

import API.Geodds;
import API.Geolocalizacion;
import organizaciones.FactorEmision;
import trayectos.Punto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public abstract class TransportePrivado extends Transporte {
  @Transient
  Geolocalizacion api;

  @OneToOne(cascade = CascadeType.ALL)
  protected FactorEmision factorEmision;

  public TransportePrivado() {
    this.api = new Geodds();
  }

  @Override
  public double getDistancia(Punto puntoInicio, Punto puntoFin) {
    return api.getDistancia(puntoInicio.getDireccion(), puntoFin.getDireccion());
  }

  @Override
  public FactorEmision getFactorEmision() {
    return this.factorEmision;
  }

  public void setApi(Geolocalizacion api) {
    this.api = api;
  }
}
