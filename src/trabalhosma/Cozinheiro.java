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
public class Cozinheiro extends Agent {
    @Override
    protected void setup() {
        addBehaviour (new CyclicBehaviour (this) {
            @Override
            public void action() {
                ACLMessage msg = myAgent.receive();
                if (msg.getContent().equals("Ingredientes disponíveis")) {
                    String content = msg.getContent();
                    
                    ACLMessage msgEstoque = new ACLMessage(ACLMessage.INFORM);
                        msgEstoque.addReceiver(new AID("Garçom", AID.ISLOCALNAME));
                        msgEstoque.setLanguage("Português");
                        msgEstoque.setOntology ("Obrigação");
                        msg.setContent (content);
                        myAgent.send(msgEstoque);
                } else if (msg.getContent().equals("Ingredientes indisponíveis")) {
                    String content = msg.getContent();
                    
                    ACLMessage msgEstoque = new ACLMessage(ACLMessage.INFORM);
                        msgEstoque.addReceiver(new AID("Garçom", AID.ISLOCALNAME));
                        msgEstoque.setLanguage("Português");
                        msgEstoque.setOntology ("Obrigação");
                        msg.setContent (content);
                        myAgent.send(msgEstoque);
                } else {
                    String content = msg.getContent();
                    
                    System.out.println("Certo! Vou verificar se os ingredientes desse pedidos existem em estoque.");
                    
                    ACLMessage msgEstoque = new ACLMessage(ACLMessage.INFORM);
                        msgEstoque.addReceiver(new AID("Estoque", AID.ISLOCALNAME));
                        msgEstoque.setLanguage("Português");
                        msgEstoque.setOntology ("Obrigação");
                        msg.setContent (content);
                        myAgent.send(msgEstoque);
                }
            }
        });
    }
}
