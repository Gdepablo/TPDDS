package organizaciones;

import Notificador.Notificador;
import Notificador.NotificarPorMail;
import Notificador.Contacto;
import Notificador.NotificarPorWhatsApp;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import excepciones.TipoConsumoInexistente;
import trayectos.Direccion;

import javax.mail.MessagingException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Organizacion {

  private String razonSocial;
  private TipoOrganizacion tipoOrganizacion;
  private Direccion ubicacion;
  private final List<Sector> sectores = new ArrayList<>();
  private Clasificacion clasificacion;
  private final List<Medicion> mediciones = new ArrayList<>();
  private final List<Contacto> contactos = new ArrayList<>();
  private final List<Notificador> notificadores = new ArrayList<>();

  public Organizacion(String razonSocial, TipoOrganizacion tipoOrganizacion, Direccion ubicacion, Clasificacion clasificacion) {
    this.razonSocial = razonSocial;
    this.tipoOrganizacion = tipoOrganizacion;
    this.ubicacion = ubicacion;
    this.clasificacion = clasificacion;
    notificadores.add(new NotificarPorMail(System.getenv("user"), System.getenv("pass")));
    notificadores.add(new NotificarPorWhatsApp());
  }

  public void agregarSector(Sector sector) {
    this.sectores.add(sector);
  }

  public void agregarContacto(Contacto contacto) {
    this.contactos.add(contacto);
  }

  public void agregarNotificador(Notificador notificador) {
    this.notificadores.add(notificador);
  }

  public void quitarNotificador(Notificador notificador) {
    this.notificadores.remove(notificador);
  }

  public void cargarMediciones(String path, List<TipoConsumo> tiposExistentes) throws IOException, CsvException {
    CSVReader reader = new CSVReader(new FileReader(path));
    List<String[]> filas = reader.readAll();
    filas.forEach(fila -> {
      TipoConsumo tipoConsumo = tiposExistentes.stream()
          .filter(tipo -> tipo.esMismoTipo(fila[0]))
          .findFirst()
          .orElseThrow(() -> new TipoConsumoInexistente("El Tipo de Consumo leido debe existir en el sistema"));
      agregarMedicion(new Medicion(tipoConsumo, new BigDecimal(fila[1]), fila[2], fila[3]));
    });
    reader.close();
  }

  private void agregarMedicion(Medicion medicion) {
    this.mediciones.add(medicion);
  }

  public double getHCTotal(String unidad) {
    return this.sectores.stream().mapToDouble(sector -> sector.getHuellaCarbono(unidad)).sum();
  }

  public void notificarUnContacto(Contacto contacto, String asunto, String contenido) {
    this.notificadores.forEach(x -> {
      try {
        x.notificar(contacto, asunto, contenido);
      } catch (MessagingException e) {
        e.printStackTrace();
      }
    });
  }

  public void notificarGuiaRecomendaciones() {
    this.contactos.forEach(x -> {
      notificarUnContacto(x, "Guia de recomendaciones", "link");
    });
  }
}
