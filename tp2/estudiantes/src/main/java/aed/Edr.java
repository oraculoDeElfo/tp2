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


    private Alumno a_quien_copiar(int id){
        return null;
    }

    public void copiarse(int estudiante) {
        Alumno se_copia = this.alumnos.get(estudiante);
        

        int contador_v1 = 0;
        int contador_v2 = 0;
        int contador_v3 = 0;



        for (int i=0; i<examen_alumno.length; i++){
            if (examen_alumno[i] == null){

                if (examen_v1[i] != null){
                    contador_v1 += 1;
                }
                if (examen_v2[i] != null){
                    contador_v1 += 1;
                }
                if (examen_v2[i] != null){
                    contador_v1 += 1;
                }
            }
        }

        if (contador_v1 >= )
        Alumno v_a_copiar = a_quien_copiar(estudiante, );
    }


//-----------------------------------------------RESOLVER----------------------------------------------------------------




    public void resolver(int estudiante, int NroEjercicio, int res) {
        throw new UnsupportedOperationException("Sin implementar");
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
