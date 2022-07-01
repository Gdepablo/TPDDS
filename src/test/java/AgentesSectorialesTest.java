import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import miembros.AgenteSectorial;
import organizaciones.SectorTerritorial;
import organizaciones.TipoSectorTerritorial;

public class AgentesSectorialesTest {

  SectorTerritorial KISS = new SectorTerritorial(TipoSectorTerritorial.DEPARTAMENTO);
  AgenteSectorial pum = new AgenteSectorial(KISS);

  @Test
  public void testearTipoSector() {
    assertEquals(KISS.getTipoDeSector(), TipoSectorTerritorial.DEPARTAMENTO);
  }

  @Test
  public void testearCalculoHCPorOrganizacion() {
    assertEquals(0, KISS.getCalculoHCTotal("km"));
  }

  @Test
  public void testearTipoSectorNoHappy() {
    assertNotEquals(KISS.getTipoDeSector(), TipoSectorTerritorial.PROVINCIA);
  }

  @Test
  public void testearAgenteSectorial() {
    assertEquals(KISS, pum.getSector());
  }

}
