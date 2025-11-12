package aed;
import java.util.ArrayList;

public class Edr {
    ArrayList<Alumno> alumnos;
    int[] canonico;
    Heap<Alumno> alumnos_menor_nota;
    ListaEnlazada<Alumno> no_se_copiaron;
    int ladoAula;

    public Edr(int LadoAula, int Cant_estudiantes, int[] ExamenCanonico) {
        this.canonico = ExamenCanonico;
        this.ladoAula = LadoAula;
        this.alumnos = new ArrayList<Alumno>(Cant_estudiantes);
        for (int i = 0; i< this.alumnos.size(); i++){
            this.alumnos.add(new Alumno(i, ExamenCanonico.length));
        }
        this.alumnos_menor_nota = new Heap<Alumno>(this.alumnos);
        this.no_se_copiaron = new ListaEnlazada<Alumno>();
    }

//-------------------------------------------------NOTAS--------------------------------------------------------------------------

    public double[] notas(){
        throw new UnsupportedOperationException("Sin implementar");
    }

//------------------------------------------------COPIARSE------------------------------------------------------------------------

    private void copiarseAux(Alumno se_copia, int[] copiado_ex){
        int[] se_copia_examen = se_copia.obtenerExamen();
        int i = 0;
            while (i<se_copia_examen.length || (copiado_ex[i] != -1 && se_copia_examen[i]==-1)){
                i++;
            }
            se_copia.modificarExamen(i, copiado_ex[i]);
    }

    public void copiarse(int estudiante) {
        Alumno se_copia = this.alumnos.get(estudiante);
        int[] se_copia_examen = se_copia.obtenerExamen();

        Alumno v1 = this.alumnos.get(estudiante + 1);
        int[] v1_examen = v1.obtenerExamen();


        Alumno v2 = this.alumnos.get(estudiante - 1);
        int[] v2_examen = v2.obtenerExamen();

        Alumno v3 = null;
        if (this.ladoAula % 2 == 0){
            v3 = this.alumnos.get(estudiante - (this.ladoAula/2));
        }
        else{
            v3 = this.alumnos.get(estudiante - (this.ladoAula/2)+1);
        }
        int[] v3_examen = v3.obtenerExamen();
        
        int contador_v1 = 0;
        int contador_v2 = 0;
        int contador_v3 = 0;
        for (int i=0; i<se_copia_examen.length; i++){
            if (se_copia_examen[i] == -1){
                if (v1_examen[i] != -1){
                    contador_v1 += 1;
                }
                if (v2_examen[i] != -1){
                    contador_v1 += 1;
                }
                if (v3_examen[i] != -1){
                    contador_v1 += 1;
                }
            }
        }

        if ((contador_v1 >= contador_v2 && contador_v2 >= contador_v3) || (contador_v1 >= contador_v3 && contador_v3 >= contador_v2)){
            copiarseAux(se_copia, v1_examen);
            return; 
        }
        if ((contador_v2 >= contador_v1 && contador_v1 >= contador_v3) || (contador_v2 >= contador_v3 && contador_v3 >= contador_v1)){
            copiarseAux(se_copia, v2_examen);
            return; 
        }
        if ((contador_v3 >= contador_v1 && contador_v1 >= contador_v2) || (contador_v3 >= contador_v2 && contador_v2 >= contador_v1)){
            copiarseAux(se_copia, v3_examen);
            return; 
        }
        else{
            return;
        }
    }

//-----------------------------------------------RESOLVER----------------------------------------------------------------




    public void resolver(int estudiante, int NroEjercicio, int res) {
        Alumno estud = this.alumnos.get(estudiante); //O(1)
        estud.modificarExamen(NroEjercicio, res);  // O(1)
        estud.actualizarNota(canonico); // O(1)

        if  (alumnos_menor_nota.obtener(estudiante) != null){
            alumnos_menor_nota.agregar(estud);// O(log(e))
        } else {
            alumnos_menor_nota.actualizar_nota_id(estudiante); //??
        }

    }


//------------------------------------------------CONSULTAR DARK WEB-------------------------------------------------------

    public void consultarDarkWeb(int n, int[] examenDW) {
        throw new UnsupportedOperationException("Sin implementar");
    }
 

//-------------------------------------------------ENTREGAR-------------------------------------------------------------

    public void entregar(int estudiante) {
        throw new UnsupportedOperationException("Sin implementar");
    }

//-----------------------------------------------------CORREGIR---------------------------------------------------------

    public NotaFinal[] corregir() {
        throw new UnsupportedOperationException("Sin implementar");
    }

//-------------------------------------------------------CHEQUEAR COPIAS-------------------------------------------------

    public int[] chequearCopias() {
        throw new UnsupportedOperationException("Sin implementar");
    }
}
