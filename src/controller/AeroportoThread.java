package controller;

import java.util.concurrent.Semaphore;

public class AeroportoThread extends Thread{
	
	
	
	private Integer idAviao;
	public Integer tempoManobra=0;
	private Integer tempoTaxiar;
	//private Integer tempoDecolagem;
	private Integer tempoAfastamentoAereo;
	private Integer tempoDecolagemTotal=0;
	private Integer tempoDecolagem=0;
	private Semaphore semaforo;
	
	private Integer tempoTotalDecolagem;
	
	public AeroportoThread (int idAviao,Semaphore semaforo) {
		
		
				
		this.idAviao = idAviao;
		this.semaforo = semaforo; 
		
	}

	
private void aviaoManobrando() {
		
		int distanciaPercorrida = 0;
		while (distanciaPercorrida < 200) {
	
			int rodada = (int) (40 + Math.random() * 60);
			distanciaPercorrida += rodada;
			
			tempoManobra= numeroAleatorioEntre(3,7);
			tempoDecolagemTotal += tempoManobra;
			
			System.out.println("O Avião ID " + idAviao + " Manobrou em, " + tempoManobra + " ms");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	
	
	private void aviaoTaxiando() {	
		
		tempoTaxiar= numeroAleatorioEntre(5,10);
		tempoDecolagemTotal += tempoTaxiar;
		
		System.out.println("O Avião ID " + idAviao + " Taxiou em, " + tempoTaxiar + " ms");
	}
	
	private void aviaoDecolando() {
			
		tempoDecolagem= numeroAleatorioEntre(1,4);
		tempoDecolagemTotal += tempoDecolagem;
		
		System.out.println("O Avião ID " + idAviao + " Decolou em, " + tempoDecolagem + " ms");
		try {
			Thread.sleep(tempoDecolagem);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void aviaoAfastando() {
		
		tempoAfastamentoAereo= numeroAleatorioEntre(3,8);
		tempoDecolagemTotal += tempoAfastamentoAereo;
		
		System.out.println("O Avião ID " + idAviao + " Decolou em, " + tempoDecolagem + " ms");
		try {
			Thread.sleep(tempoDecolagem);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
private void tempoTotal() {
		
		System.out.println("O Avião ID " + idAviao + " levou, " + tempoDecolagem + " ms no processo de decolagem");
		try {
			Thread.sleep(tempoDecolagem);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		aviaoManobrando();
		aviaoTaxiando();
		
		try {
			semaforo.acquire();
			aviaoDecolando();
			aviaoAfastando();
			tempoTotal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			
			semaforo.release();
		}
		
		
	
	
	}
		
	
	public int numeroAleatorioEntre(Integer min,Integer max) {
		Integer numeroAleatorio = min + (int)(Math.random() * (max-min));
		return numeroAleatorio;
    }
	


}
