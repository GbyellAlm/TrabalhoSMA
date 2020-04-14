/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhosma;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Almeida Gabriel
 */
public class Garçom extends Agent {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    private String pedido;
    
    @Override
    protected void setup() {
        System.out.println("Olá,");
        System.out.println("Sou o garçom " + getLocalName() + ". Qual o seu pedido?");
        
        Object [] args = getArguments();
        
        if (args != null && args.length > 0) {
            pedido = (String) args[0];
            System.out.println("Certo! já vamos ver isso (" + pedido + ").");
            
            addBehaviour (new CyclicBehaviour (this) {
                @Override
                public void action() {
                ACLMessage msg = new ACLMessage (ACLMessage.INFORM);
                    msg.addReceiver (new AID( "Cozinheiro" ,AID.ISLOCALNAME));
                    msg.setLanguage ("Português");
                    msg.setOntology ("Obrigação");
                    msg.setContent (pedido);
                    myAgent.send (msg);
            }
            }); 
        }
        ACLMessage msg = myAgent.receive();
        if () {
            
        }
    }
    
}

