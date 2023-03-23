package view;

import java.util.concurrent.Semaphore;

import controller.AeroportoThread;

public class PrincipalExercicioAeroporto {public static void main(String[] args) {

	int permissoes = 2;
	
	Semaphore semaforo = new Semaphore(permissoes);

	for (int i = 1; i <= 12; i++) {
		Thread t = new AeroportoThread(i, semaforo);
		t.start();
	}
	
	
}

}
