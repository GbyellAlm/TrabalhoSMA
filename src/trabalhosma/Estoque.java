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
public class Estoque extends Agent{
    int massa = 2;
    int molho = 5;
    int frango = 0;
    int queijo = 1;
    
    @Override
    protected void setup() {
        addBehaviour (new CyclicBehaviour (this) {
            
            @Override
            public void action() {
                ACLMessage msgEstoque = myAgent.receive();
                
                System.out.println("O cozinheiro " + msgEstoque.getSender().getName() + "me mandou um pedido. Vou verificar se há ingredientes para faze-lo.");
                
                if ("Pizza de frango".equals(msgEstoque.getContent())) {
                    if (frango > 0 && massa > 0 && molho > 0) {
                        frango = frango - 1;
                        massa = massa - 1;
                        molho = molho - 1;
                        
                        ACLMessage msgPos = new ACLMessage (ACLMessage.INFORM);
                            msgPos.addReceiver(new AID ("Cozinheiro", AID.ISLOCALNAME));
                            msgPos.setLanguage("Português");
                            msgPos.setOntology ("Obrigação");
                            msgPos.setContent("Ingredientes disponíveis");
                            myAgent.send(msgPos);
                        
                        System.out.println("Ingredientes disponíveis");
                    } else {
                        ACLMessage msgNeg = new ACLMessage (ACLMessage.INFORM);
                            msgNeg.addReceiver(new AID ("Cozinheiro", AID.ISLOCALNAME));
                            msgNeg.setLanguage("Português");
                            msgNeg.setOntology ("Obrigação");
                            msgNeg.setContent("Ingredientes indisponíveis");
                            myAgent.send(msgNeg);
                            
                        System.out.println("Ingredientes indisponíveis");
                    }
                }
            }
        });
        
        
    }
}
