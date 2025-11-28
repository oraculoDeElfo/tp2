package aed;

public class Alumno implements Indexable<Alumno>{
    private int id;
    private boolean entregado;
    private int respuestasCorrectas;
    private int[] examen;

    //O(R)
    public Alumno(int id, int tamañoExamen) { 
        this.id = id; //O(1)
        this.entregado = false;//O(1)
        this.respuestasCorrectas = 0;//O(1)
        this.examen = new int[tamañoExamen];//O(1)

        //O(R)
        for (int i = 0; i < tamañoExamen; i++){ //O(R)
            this.examen[i] = -1;//O(1)           
        }
    }

    //O(R)
    public void actualizarNota (int[] canonico){
        int notaNueva = 0;//O(1)

        //O(R)
        for (int i = 0; i < examen.length; i++){
            //O(1)
            if (examen[i] == canonico[i]){
                notaNueva += 1;
            }
        }
        this.respuestasCorrectas = notaNueva;//O(1)
    }

    //O(1)
    public void modificarExamenCompleto (int[] ex){
        this.examen = ex.clone(); //O(1)
    }

    //O(1)
    public void modificarExamen(int NroEjercicio, int res){
        this.examen[NroEjercicio] = res;//O(1)
    }

    //O(1)
    public void entregarExamen(){
        this.entregado = true;//O(1)
    }
 
    //O(1)
    public int[] obtenerExamen(){
        return this.examen;//O(1)
    }
    
    //O(1)
    public double obtenerNota(){ 
        return (double) (100 * this.respuestasCorrectas / this.examen.length);//O(1)
    }

    //O(1)
    public int obtenerId(){
        return this.id;//O(1)
    }

    public boolean obtenerEstado(){
        return this.entregado;
    }

    @Override
    public int compareTo(Alumno alumno2){
    // O(1)
    int comparacionNota = Integer.compare(this.respuestasCorrectas, alumno2.respuestasCorrectas);
    
    //O(1)
    if (comparacionNota != 0){
        return comparacionNota;
    } else {
        return Integer.compare(this.id, alumno2.id);
        
    }
}
}
