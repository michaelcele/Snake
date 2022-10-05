import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel {
        Color colorfondo = Color.gray;
        int tamax, tam, can, res;

        public Panel(int tamax, int can) {
                this.tamax = tamax;
                this.can = can;
                this.tam = tamax / can;
                this.res = tam % can;
        }
        @Override
        public void paint(Graphics pintor) {               
                super.paint(pintor);
                pintor.setColor(colorfondo);
                for(int i = 0; i < can; i++) {
                    for(int j = 0; j < can; j++) {
                        pintor.fillRect(res / 2 + i * tam, res / 2 + j * tam, tam -1, tam - 1);
                    }
                }
        }
}
