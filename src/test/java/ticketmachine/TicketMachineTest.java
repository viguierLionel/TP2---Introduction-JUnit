package ticketmachine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@Before
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation
	public void priceIsCorrectlyInitialized() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
		assertEquals("Initialisation incorrecte du prix", PRICE, machine.getPrice());
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		assertEquals("La balance n'est pas correctement mise à jour", 10 + 20, machine.getBalance()); // Les montants ont été correctement additionnés               
	}
        @Test
        // S3 : on n’imprime pas le ticket si le montant inséré est insuffisant
        public void printMarchePasSiPriceInfABal(){
            machine.insertMoney(PRICE-5);
            assertFalse("printTiket marche mal",machine.printTicket());
        }
        @Test
        // S4 : on imprime le ticket si le montant inséré est suffisant
        public void printMarcheSiBalanceEstBon(){
            machine.insertMoney(PRICE);
            assertTrue("printTiket dois imprimer mais ne le fait pas",machine.printTicket());
        }
        @Test
        //S5 : Quand on imprime un ticket la balance est décrémentée du prix du ticket
        public void balanceEstDecrementeSiPrint(){
            machine.insertMoney(PRICE+10);
            machine.printTicket();
            assertEquals("Balance pas mise a jours",10,machine.getBalance());
        }
        @Test
        //S6 : le montant collecté est mis à jour quand on imprime un ticket (pas avant)
        public void montantCollecteApres(){
            int x = machine.getTotal();
            machine.insertMoney(PRICE+10);
            assertTrue("total est mis a jours trop tot",x==machine.getTotal());
            machine.printTicket();
            assertTrue("total n'est pas mis a jours",x!=machine.getTotal());
        }
        @Test
        // S7 : refund() rend correctement la monnaie
        public void rendBienLaMonnaie(){
            machine.insertMoney(90);
            assertEquals("ne rend pas la monnaie comme il faut",90,machine.refund());
            
        }
        @Test
        // S8 : refund() remet la balance à zéro
        public void neGardePasLaMonnaie(){
            machine.insertMoney(90);
            machine.refund();
            assertTrue("garde la balance a la meme valeur",0==machine.getBalance());
            
        }
        @Test(expected=IllegalArgumentException.class)
        //S9 : on ne peut pas insérer un montant négatif
        public void pasDeMontantNegatif(){
            machine.insertMoney(-10);
            
        }
        @Test(expected=IllegalArgumentException.class)
        //S10 : on ne peut pas créer de machine qui délivre des tickets dont le prix est négatif
        public void pasDeBilletsNegatifs(){
            TicketMachine machinenegativeBeans = new TicketMachine(-PRICE);
        }
        

}
