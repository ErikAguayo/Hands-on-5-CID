
package mLinearMatrix;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;


public class agentMRL extends Agent {

    private double predictX1 = 0.0;
    private double predictX2 = 0.0;
    private double predictX3 = 0.0;
    private static final matrixMLR train = new matrixMLR();
    
    private static double bethas [] = new double[4];
    protected void setup() {
        System.out.println("Agent " + getLocalName() + " started.");

        
            addBehaviour(new Train());
            predictX1 = 152211.77;
            predictX2 = 120542.52;
            predictX3 = 148718.95;
            System.out.println("x1 to predict = " + predictX1);
            System.out.println("x2 to predict = " + predictX2);
            System.out.println("x3 to predict = " + predictX3);
      

    }

    private class Train extends OneShotBehaviour{
        public void action(){
            bethas=train.train();
        }
        public int onEnd() {
            StringBuilder s = new StringBuilder();
            s.append(String.format("%.2f + %.2fx1+%.2fx2+ %.2fx3", bethas[0], bethas[1], bethas[2], bethas[3]));
            System.out.print("\nSimple linear regression equation: " + s.toString());
            double predicted_y = (bethas[1] * predictX1)+(bethas[2] * predictX2)+(bethas[3] * predictX3) + bethas[0];
            System.out.print(String.format("\nx1: %.2f | x2: %.2f | x3: %.2f |predicted y: %.2f\n", predictX1, predictX2, predictX3, predicted_y));
            
            return super.onEnd();
        }
    }
}
