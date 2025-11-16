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
        this.respuestasCorrectas += 1;//O(1)  
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

    @Override
    //O(1)
    public int compareTo(Alumno alumno2){
        //O(1)
        if (alumno2.respuestasCorrectas > this.respuestasCorrectas){
            return -1;
        }
        //O(1)
        else if (alumno2.respuestasCorrectas < this.respuestasCorrectas) {
            return 1;
        }
        //O(1)
        else {
            if (alumno2.id < this.id){
                return 1;
            }
            else if (alumno2.id > this.id){
                return -1;
            }
            else {
                return 0;
            }
        }
    }
}
