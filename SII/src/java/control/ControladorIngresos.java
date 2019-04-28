package control;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import proyectosii.Ingresos;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "ingresoBean")
@SessionScoped

public class ControladorIngresos {
    
    public ArrayList<Ingresos> listarIngresos;
    
    public ControladorIngresos(){
        listarIngresos = new ArrayList<>();
        listarIngresos.add(new Ingresos(BigDecimal.ONE, Date.valueOf("2019-5-12"), "HOLA", BigInteger.valueOf(81238)));
        listarIngresos.add(new Ingresos(BigDecimal.valueOf(201), Date.valueOf("2019-10-12"), "ADIOS", BigInteger.valueOf(1223134)));
        listarIngresos.add(new Ingresos(BigDecimal.valueOf(358), Date.valueOf("2019-9-9"), "BUENOS DIAS", BigInteger.valueOf(223315)));
    }

    public ArrayList<Ingresos> getListarIngresos() {
        return listarIngresos;
    }

    public void setListaIngresos(ArrayList<Ingresos> listaIngresos) {
        this.listarIngresos = listaIngresos;
    }
    
}
