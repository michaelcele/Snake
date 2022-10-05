import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Snake extends JPanel {
        Color colorsnake = Color.GREEN;
        Color colorComida = Color.RED; 
        int tamax, tam, can, res;
        List<int[]> snake = new ArrayList<>();
        int[] comida = new int[2];
        String direccion = "right";
        String dirreccionproxima = "right";

        Thread hilo;
        Walker camino;

        public Snake(int tamax, int can) {
                this.tamax = tamax;
                this.can = can;
                this.tam = tamax / can;
                this.res = tam % can;
                int [] a = {can/2-1, can/2-1};
                int [] b = {can/2, can/2-1};
                snake.add(a);
                snake.add(b);
                foodgenerator();

                comida[0] = 25;
                comida[1] = 14;

                camino = new Walker(this);
                hilo = new Thread(camino);
                hilo.start();

        }
        @Override
        public void paint(Graphics pintor) {               
                super.paint(pintor);
                //Pintando snake
                pintor.setColor(colorsnake);
                for(int []par:snake) {
                    pintor.fillRect(res/2 + par[0] * tam, res/2 + par[1] * tam, tam - 1, tam -1);
                }
                //Pintando la comida
                pintor.setColor(colorComida);
                pintor.fillRect(res/2 + comida[0] * tam, res/2 + comida[1] * tam, tam - 1, tam -1);
        }
        public void avanzar() {
            igualardir();
            int [] ultimo = snake.get(snake.size() - 1);
            int agregarx = 0;
            int agregary = 0;
            switch(direccion) {
                case "right": agregarx = 1; break;
                case "left": agregarx = -1; break;
                case "up": agregary = 1; break;
                case "down": agregary = -1; break;
            }
            int [] nuevo = {Math.floorMod(ultimo[0] + agregarx, can),  Math.floorMod(ultimo[1] + agregary, can)};
            boolean existe = false;
            for(int i = 0; i < snake.size(); i++) {
                if(nuevo[0] == snake.get(i) [0] && nuevo[1] == snake.get(i)[1]) {
                    existe = true;
                    break;
                }
            }
            if(existe) {
                JOptionPane.showMessageDialog(this, "Has perdido");
            }
            else {
                if(nuevo[0] == comida[0] && nuevo[1] == comida[1]) {
                    snake.add(nuevo);
                    foodgenerator();
                }else {
                    snake.add(nuevo);
                    snake.remove(0);
                }
            }

        }

        public void foodgenerator() {
            boolean existe = false;
            int a = (int) (Math.random()* can);
            int b = (int) (Math.random()* can);
            for(int[] par : snake) {
                if(par[0] == a && par[1] == b) {
                    existe = true;
                    foodgenerator();
                    break;
                }
                if(!existe) {
                    this.comida[0] = a;
                    this.comida[1] = b;
                }
            }
        }
        
        public void directionChange(String dir) {
            if((this.direccion.equals("right") || this.direccion.equals("left") && (dir.equals("up") || dir.equals("down")))) {
                this.direccion = dir;
            }
            if((this.direccion.equals("up") || this.direccion.equals("down") && (dir.equals("left") || dir.equals("right")))) {
                this.direccion = dir;
            }
        }

        public void igualardir() {
            this.direccion = this.dirreccionproxima;
        }
}