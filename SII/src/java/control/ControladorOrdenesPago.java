package control;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import proyectosii.OrdenesPago;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@ManagedBean(name = "ordenPagoBean")
@SessionScoped

public class ControladorOrdenesPago implements Serializable{
    
    private ArrayList<OrdenesPago> listarOrdenesPago;
    
    public ControladorOrdenesPago() {
        listarOrdenesPago = new ArrayList<OrdenesPago>();
        listarOrdenesPago.add(new OrdenesPago(new BigDecimal("001"), new Date(2004, 10, 4), "José Rodríguez"));
        listarOrdenesPago.add(new OrdenesPago(new BigDecimal("002"), new Date(2007, 12, 24), "Luis Sánchez"));
        listarOrdenesPago.add(new OrdenesPago(new BigDecimal("003"), new Date(2012, 8, 13), "Juan Pérez"));
        listarOrdenesPago.add(new OrdenesPago(new BigDecimal("004"), new Date(2015, 9, 23), "Antonio Romero"));
    }

    public ArrayList<OrdenesPago> getListarOrdenesPago() {
        return listarOrdenesPago;
    }

    public void setListarOrdenesPago(ArrayList<OrdenesPago> listOP) {
        this.listarOrdenesPago = listOP;
    }
    
}
