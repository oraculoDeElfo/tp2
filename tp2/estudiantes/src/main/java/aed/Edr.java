package aed;
import java.util.ArrayList;

public class Edr {
    ArrayList<Alumno> alumnos;
    int[] canonico;
    Heap<Alumno> alumnos_menor_nota;
    ArrayList<Alumno> no_se_copiaron;
    int ladoAula;

    public Edr(int LadoAula, int Cant_estudiantes, int[] ExamenCanonico) {
        this.canonico = ExamenCanonico;
        this.ladoAula = LadoAula;
        this.alumnos = new ArrayList<Alumno>(Cant_estudiantes);
        for (int i = 0; i< this.alumnos.size(); i++){
            this.alumnos.add(new Alumno(i, ExamenCanonico.length));
        }
        this.alumnos_menor_nota = new Heap<Alumno>(this.alumnos);
        this.no_se_copiaron = new ArrayList<Alumno>();
    }

//-------------------------------------------------NOTAS--------------------------------------------------------------------------

    public double[] notas(){
        double[] res = new double[this.alumnos.size()];
        for (int i=0; i<this.alumnos.size(); i++){
            Alumno al = this.alumnos.get(i);
            res[i] = (double) (al.obtenerNota()/this.canonico.length);
        }
        return res;
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
            throw new UnsupportedOperationException("casos sin contemplar ");
        }
    }

//-----------------------------------------------RESOLVER----------------------------------------------------------------




    public void resolver(int estudiante, int NroEjercicio, int res) {
        Alumno estud = this.alumnos.get(estudiante); //O(1)
        estud.modificarExamen(NroEjercicio, res);  // O(1)
        estud.actualizarNota(canonico); // O(1)

        if  (alumnos_menor_nota.obtener_con_id(estudiante) == null){
            alumnos_menor_nota.agregar(estud);// O(log(e))
        } else {
            alumnos_menor_nota.actualizar_nota_id(estudiante); //O(log(e))
        }

    }


//------------------------------------------------CONSULTAR DARK WEB-------------------------------------------------------

    public void consultarDarkWeb(int n, int[] examenDW) {
        for (int i=0; i<n; i++){
            Alumno k_i = this.alumnos_menor_nota.obtener(0);//O(1)
            this.alumnos_menor_nota.borrar(0);//O(log e)
            k_i.modificarExamenCompleto(examenDW); //O(1)
            k_i.actualizarNota(this.canonico); //O(R)
        }
    }
 

//-------------------------------------------------ENTREGAR-------------------------------------------------------------

    public void entregar(int estudiante) {
    Alumno estud = this.alumnos.get(estudiante);
    estud.entregarExamen();
    alumnos_menor_nota.borrar_por_id(estudiante);
    }

//-----------------------------------------------------CORREGIR---------------------------------------------------------

    public NotaFinal[] corregir() {
        NotaFinal[] res = new NotaFinal[this.no_se_copiaron.size()];
        Heap<Alumno> heap = new Heap<Alumno>(this.no_se_copiaron);
        for (int i=0; i<this.no_se_copiaron.size();i++){
            Alumno al_i = heap.obtener(0);
            heap.borrar(0);

            res[i] = new NotaFinal(al_i.obtenerNota(), i);
        }

        return res;
    }

//-------------------------------------------------------CHEQUEAR COPIAS-------------------------------------------------

    public int[] chequearCopias() {
        ArrayList<Integer> pre_res = new ArrayList<Integer>();  

        int[][] preguntas_respuestas = new int[10][this.canonico.length];
        for (int i=0; i<this.alumnos.size();i++){
            int[] examen_i = this.alumnos.get(i).obtenerExamen();
            for (int p=0; p<this.canonico.length;p++){
                if (examen_i[p]!=-1){
                    preguntas_respuestas[examen_i[p]][p] += 1;//ver si esta bien el orden
                }
            }
        }
        int se_copio = (this.alumnos.size() -1)/4;

        for (int i=0; i<this.alumnos.size();i++){
            int[] examen_i = this.alumnos.get(i).obtenerExamen();
            int cantPregsCopiadas = 0;
            int cantPregsRespondidas = 0;
            for (int p=0; p<this.canonico.length;p++){
                if (examen_i[p]!=-1){
                    cantPregsCopiadas+=1;
                    int cant = preguntas_respuestas[examen_i[p]][p];
                    if (cant > se_copio){
                        cantPregsCopiadas += 1;
                    }
                     
                }
            if (cantPregsCopiadas==cantPregsRespondidas){
                pre_res.add(i);
            }
            else{
                this.no_se_copiaron.add(this.alumnos.get(i));
            }
            }        
        } 
        int[] res = new int[pre_res.size()];
        for (int i=0; i<pre_res.size();i++){
            res[i] = pre_res.get(i);
        }
        return res;
    }


















}
