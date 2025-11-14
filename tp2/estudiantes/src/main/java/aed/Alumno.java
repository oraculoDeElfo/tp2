package aed;

public class Alumno implements Indexable<Alumno>{
    private int id;
    private boolean entregado;
    private int respuestasCorrectas;
    private int[] examen;
    private int handle;

    public Alumno(int id, int tamañoExamen) { //O(R)
        this.id = id;
        this.entregado = false;
        this.respuestasCorrectas = 0;
        this.examen = new int[tamañoExamen];
        this.handle = id;
        for (int i = 0; i < tamañoExamen; i++){ //O(R)
            this.examen[i] = -1;                  
        }
    }

    public void actualizarNota (int[] canonico){ //O(R)
        int notaNueva = 0;
        for (int i = 0; i < examen.length; i++){
            if (examen[i] == canonico[i]){
                notaNueva += 1;
            }
        }
        this.respuestasCorrectas = notaNueva;
    }

    public void modificarExamenCompleto (int[] ex){ //O(1)
        this.examen = ex.clone(); 
    }

    public void modificarExamen(int NroEjercicio, int res){ //O(1)
        this.examen[NroEjercicio] = res;
        this.respuestasCorrectas += 1;     
    }
 
    //O(1)
    public double obtenerNota(){ 
        return (double) (100 * this.respuestasCorrectas / this.examen.length); 
    }
    //O(1)
    public void entregarExamen(){
        this.entregado = true;
    }
    //O(1)
    public int obtenerId(){
        return this.id;
    }
    //O(1)
    public int[] obtenerExamen(){
        return this.examen;
    }
    //O(1)
    private int obtenerHandle(){
        return this.handle;
    }
    //O(1)
    public void modificarHandle (int nuevoHandle){
        handle = nuevoHandle;
    }

    @Override
    public int compareTo(Alumno alumno2){

        if (alumno2.respuestasCorrectas > this.respuestasCorrectas){
            return -1;
        }
        else if (alumno2.respuestasCorrectas < this.respuestasCorrectas) {
            return 1;
        }
        else {
            if (alumno2.id < this.id){
                return -1;
            }
            else if (alumno2.id > this.id){
                return 1;
            }
            else {
                return 0;
            }
        }
    }

}
