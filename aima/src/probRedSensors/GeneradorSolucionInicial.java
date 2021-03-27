package probRedSensors;

//Author: Ginesta Basart

public class GeneradorSolucionInicial {

	
	public GeneradorSolucionInicial(DefinicionEstado e) {
		
	}
	
	//Implementa la generacion de la solucion de partida para hill climbing
	
	public void generaSolucionInicial1(DefinicionEstado e) {
		//Reparte los sensores entre los centros de datos
		
		int numCentros = e.numCentros();
		int numSensores = e.numSensores();
		
		int div = numSensores/numCentros;
		int mod = numSensores%numCentros;
		
		if (numSensores - 25*div > mod/numSensores/2) {
			System.out.print("No empezamos bien: Hay demasiados sensores, y no se pueden respetar las restricciones de conexion.");
		}
		else {
			
			for (int i = 0; i < numCentros; i++) {
				for (int j = 0; j < div; j++) {
					e.nuevaConexion(numSensores+i, j+div*i); //offset de sensores ya asignados a un centro de datos
					if(e.sumaConexiones(numSensores+i) > 25) {
						System.out.print("No vamos nada bien: los sensores no se estan repartiendo entre los centros de datos respetando la limitación de conexiones.");
						mod = 0;
						j = div;
						i = numCentros;
					}
				}
			}
			
			if (mod != 0) {
				//hay más de 25*numCentros sensores. Hay que repartirlos entre los sensores que ya estan conectados a algun centro (solo tendran una conexion)
				for (int i = 0; i < mod; i++) {
					for (int j = 0; 25*numCentros+1+i <= numSensores && j < 2; j++) {
						//los asignamos de dos en dos para llegar al tope de conexiones posibles con los sensores ya concectados
						e.nuevaConexion(25*numCentros+1+i, j+2*i);
						if (e.sumaConexiones(25*numCentros+1+i) > 3) {
							System.out.print("No vamos nada bien: los sensores restantes no se estan repartiendo entre los sensores asignados respetando la limitación de conexiones.");
							j = 2;
							i = mod;
						}
					}
				}
			}
		}
		
	}
	
	public void generaSolucionInicial2(DefinicionEstado e) {
		RedSensor rs = e.getRedSensor();
		
		
	}
	
	
}
