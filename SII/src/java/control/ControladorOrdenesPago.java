
package control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import proyectosii.OrdenesPago;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named(value = "ordenes")
@SessionScoped

public class ControladorOrdenesPago {
    
    private ArrayList<OrdenesPago> listarOrdenesPago;
    
    public ControladorOrdenesPago() {
        listarOrdenesPago = new ArrayList<OrdenesPago>();
        listarOrdenesPago.add(new OrdenesPago(new BigDecimal("001"), new Date(2004, 10, 4), "José Rodríguez"));
        listarOrdenesPago.add(new OrdenesPago(new BigDecimal("002"), new Date(2007, 12, 24), "Luis Sánchez"));
        listarOrdenesPago.add(new OrdenesPago(new BigDecimal("003"), new Date(2012, 8, 13), "Juan Pérez"));
    }
    
    public ArrayList<OrdenesPago> listOrdenPago() {
        return listarOrdenesPago;
    }

    public void addOrdenPago(OrdenesPago ordPago) {
        
    }
    
    public void deleteOrdenPago(OrdenesPago ordPago) {
        
    }
    
}
