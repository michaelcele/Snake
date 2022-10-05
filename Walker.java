
import java.util.logging.Level;
import java.util.logging.Logger;


public class Walker implements Runnable {

    Snake panel;
    public Walker(Snake panel) {
        this.panel = panel;
        boolean estado = true;
    }

    @Override
    public void run() {
        while(true){
            panel.avanzar();
            panel.repaint();
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(Walker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void parar(){
        this.estado = false;
    }
    
}
