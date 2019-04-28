package control;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import proyectosii.Egresos;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@ManagedBean(name = "egresoBean")
@SessionScoped

public class ControladorEgresos {
    
    private ArrayList<Egresos> listarEgresos;
    
    public ControladorEgresos() {
        listarEgresos = new ArrayList<Egresos>();
        listarEgresos.add(new Egresos(new BigDecimal("001"), "Juán Perez", "Alquiler", new BigInteger("230"), new Date(2018,12,10)));
        listarEgresos.add(new Egresos(new BigDecimal("002"), "Luis Sánchez", "Pago de Combustible", new BigInteger("40"), new Date(2019,2,27)));
        listarEgresos.add(new Egresos(new BigDecimal("003"), "José Rodríguez", "Pago de Mantenimiento", new BigInteger("15"), new Date(2019,3,20)));
    }

    public ArrayList<Egresos> getListarEgresos() {
        return listarEgresos;
    }

    public void setListarEgresos(ArrayList<Egresos> listarEgresos) {
        this.listarEgresos = listarEgresos;
    }
    
}
