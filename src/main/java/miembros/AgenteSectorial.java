package miembros;

import organizaciones.SectorTerritorial;

public class AgenteSectorial {
  private SectorTerritorial sectoresTerritoriales;

  public AgenteSectorial(SectorTerritorial sectoresTerritoriales) {
    this.sectoresTerritoriales = sectoresTerritoriales;
  }

  public SectorTerritorial getSector() {
    return this.sectoresTerritoriales;
  }

}
