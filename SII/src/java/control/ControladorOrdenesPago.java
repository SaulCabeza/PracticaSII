
package control;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import proyectosii.OrdenesPago;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named(value = "ordenesPago")
@SessionScoped

public class ControladorOrdenesPago implements Serializable{
    
    private ArrayList<OrdenesPago> listOP;
    
    public ControladorOrdenesPago() {
       
    }

    public ArrayList<OrdenesPago> getListarOrdenesPago() {
        listOP = new ArrayList<OrdenesPago>();
        listOP.add(new OrdenesPago(new BigDecimal("001"), new Date(2004, 10, 4), "José Rodríguez"));
        listOP.add(new OrdenesPago(new BigDecimal("002"), new Date(2007, 12, 24), "Luis Sánchez"));
        listOP.add(new OrdenesPago(new BigDecimal("003"), new Date(2012, 8, 13), "Juan Pérez"));
        return listOP;
    }

    public void setListarOrdenesPago(ArrayList<OrdenesPago> listOP) {
        this.listOP = listOP;
    }
    
    

    public void addOrdenPago(OrdenesPago ordPago) {
        
    }
    
    public void deleteOrdenPago(OrdenesPago ordPago) {
        
    }
    
}
