package probRedSensors;

public class DefinicionEstado {
	
	//Implementacion del estado
	private int[][] estado;
	
	public DefinicionEstado(int s, int c){		
		int dim = s+c;
		estado = new int[dim][dim];
	}
	
	public int[][] actual(){
		return estado;
	}
	
	
	//Implementacion de los operadores
	public void nuevaConexion(/*dos sensores, dos centros de datos o un sensor y un centro de datos*/) {
		//canvia un bit 0 del estado a 1
	}
	
	public void eliminaConexion(/*dos sensores, dos centros de datos o un sensor y un centro de datos*/) {
		//canvia un bit 1 del estado a 0
	}

}
