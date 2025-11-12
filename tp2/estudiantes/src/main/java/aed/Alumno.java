package aed;

public class Alumno implements Comparable<Alumno>{
    private int id;
    private boolean entregado;
    private int nota;
    private int[] examen;
    private int handle;

    public Alumno(int id, int tamañoExamen) {
        this.id = id;
        this.entregado = false;
        this.nota = 0;
        this.examen = new int[tamañoExamen];
        this.handle = id;
    }

    public void actualizarNota (int[] canonico){
        int notaNueva = 0;
        for (int i = 0; i < examen.length; i++){
            if (examen[i] == canonico[i]){
                notaNueva += 1;
            }
        }
        this.nota = notaNueva;
    }

    public void modificarExamen(int NroEjercicio, int res){
        this.examen[NroEjercicio] = res;
    }

    private void entregarExamen(){
        this.entregado = true;
    }

    public int obtenerId(){
        return this.id;
    }

    public int[] obtenerExamen(){
        return this.examen;
    }

    private int obtenerHandle(){
        return this.handle;
    }

    public void modificarHandle (int nuevoHandle){
        handle = nuevoHandle;
    }

    @Override
    public int compareTo(Alumno alumno2){

        if (alumno2.nota < this.nota){
            return -1;
        }
        else if (alumno2.nota > this.nota) {
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
