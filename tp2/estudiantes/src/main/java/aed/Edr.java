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
        for (int i = 0; i< Cant_estudiantes; i++){
            this.alumnos.add(new Alumno(i, ExamenCanonico.length));
        }
        this.alumnos_menor_nota = new Heap<Alumno>(this.alumnos,-1);
        this.no_se_copiaron = new ArrayList<Alumno>();
    }

//-------------------------------------------------NOTAS--------------------------------------------------------------------------

    public double[] notas(){
        double[] res = new double[this.alumnos.size()];
        for (int i=0; i<this.alumnos.size(); i++){
            Alumno al = this.alumnos.get(i);
            res[i] = al.obtenerNota();//(double) (100* al.obtenerNota()/this.canonico.length);
        }
        return res;
    }

//------------------------------------------------COPIARSE------------------------------------------------------------------------

    private void copiarseAux(Alumno se_copia, int[] copiado_ex){
        int[] se_copia_examen = se_copia.obtenerExamen();
        int i = 0;
        while (i<se_copia_examen.length && (copiado_ex[i] == -1 || se_copia_examen[i] != -1)){
        //while (i<se_copia_examen.length || (copiado_ex[i] != -1 && se_copia_examen[i]==-1)){
            i++;
        }
        se_copia.modificarExamen(i, copiado_ex[i]);
    }

    public void copiarse(int estudiante) {
        Alumno se_copia = this.alumnos.get(estudiante);
        int[] se_copia_examen = se_copia.obtenerExamen();

        int[] dummy = new int[this.canonico.length];
        for (int i = 0; i < this.canonico.length; i++){
            dummy[i] = -1;                  
        }

        int[] vDer_examen = dummy;
        int[] vIzq_examen = dummy;
        int[] vAde_examen = dummy;

        if (((2 * estudiante) % this.ladoAula != 0) && ((2 * estudiante) % this.ladoAula != 1)){
            vIzq_examen = this.alumnos.get(estudiante - 1).obtenerExamen();
        }

        if(!(((2 * estudiante) % this.ladoAula + 2 >= this.ladoAula) || (estudiante == this.alumnos.size()-1))){ //arreglar comparacio
            vDer_examen = this.alumnos.get(estudiante + 1).obtenerExamen();
        }

        if((2 * estudiante) > this.ladoAula){
            if (this.ladoAula % 2 == 0){
                vAde_examen = this.alumnos.get(estudiante - ((this.ladoAula + 1)/2)).obtenerExamen();
            }
            else{
                vAde_examen = this.alumnos.get(estudiante - ((this.ladoAula + 1)/2)).obtenerExamen();//cambiamos el +1 por -1
            }
        }
        
        int contador_vDer = 0;
        int contador_vIzq = 0;
        int contador_vAde = 0;

        for (int i=0; i<se_copia_examen.length; i++){
            if (se_copia_examen[i] == -1){
                if (vDer_examen[i] != -1){
                    contador_vDer += 1;
                }
                if (vIzq_examen[i] != -1){
                    contador_vIzq += 1;
                }
                if (vAde_examen[i] != -1){
                    contador_vAde += 1;
                }
            }
        }

        if ((contador_vDer >= contador_vIzq && contador_vIzq >= contador_vAde) || (contador_vDer >= contador_vAde && contador_vAde >= contador_vIzq)){
            copiarseAux(se_copia, vDer_examen);
            return; 
        }
        if ((contador_vIzq >= contador_vDer && contador_vDer >= contador_vAde) || (contador_vIzq >= contador_vAde && contador_vAde >= contador_vDer)){
            copiarseAux(se_copia, vIzq_examen);
            return; 
        }
        if ((contador_vAde >= contador_vDer && contador_vDer >= contador_vIzq) || (contador_vAde >= contador_vIzq && contador_vIzq >= contador_vDer)){
            copiarseAux(se_copia, vAde_examen);
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
        Alumno[] als = new Alumno[n];
        for (int i=0; i<n; i++){
            Alumno k_i = this.alumnos_menor_nota.obtener(0);//O(1)
            als[i] = k_i;
            this.alumnos_menor_nota.borrar(0);//O(log e)
            //this.alumnos_menor_nota.anularId(k_i.obtenerId());
            k_i.modificarExamenCompleto(examenDW); //O(1)
            k_i.actualizarNota(this.canonico); //O(R)
        }
        for (Alumno a : als){
            this.alumnos_menor_nota.agregar(a);
        }
    }
 

//-------------------------------------------------ENTREGAR-------------------------------------------------------------

    public void entregar(int estudiante) {
        Alumno estud = this.alumnos.get(estudiante);
        alumnos_menor_nota.borrar_por_id(estudiante);
        estud.entregarExamen();
    }

//-----------------------------------------------------CORREGIR---------------------------------------------------------

    public NotaFinal[] corregir() {
        NotaFinal[] res = new NotaFinal[this.no_se_copiaron.size()];
        for (int i=0; i<this.no_se_copiaron.size();i++){
            Alumno al = this.no_se_copiaron.get(i);
            res[i] = new NotaFinal(al.obtenerNota(), al.obtenerId());
        }
        Heap<NotaFinal> heap = new Heap<NotaFinal>(res,1);
        for (int i=0; i<this.no_se_copiaron.size();i++){
            res[i] = heap.obtener(0);
            heap.borrar(0);
        }

        return res;
    }

//-------------------------------------------------------CHEQUEAR COPIAS-------------------------------------------------

    public int[] chequearCopias() {
        ArrayList<Integer> pre_res = new ArrayList<Integer>();  

        int[][] preguntas_respuestas = new int[this.canonico.length][10];
        for (int i=0; i<this.alumnos.size();i++){
            int[] examen_i = this.alumnos.get(i).obtenerExamen();
            for (int p=0; p<this.canonico.length;p++){
                if (examen_i[p]!=-1){
                    preguntas_respuestas[p][examen_i[p]] += 1;
                }
            }
        }
        int unCuartoDelAula = (this.alumnos.size() -2)/4 + 1;  //ver que no se rompa si hay un solo estudiante

        for (int i=0; i<this.alumnos.size();i++){
            int[] examen_i = this.alumnos.get(i).obtenerExamen();
            int cantPregsCopiadas = 0;
            int cantPregsRespondidas = 0;
            for (int p=0; p<this.canonico.length;p++){
                if (examen_i[p]!=-1){
                    cantPregsRespondidas+=1;
                    int cantRespuestasIguales = preguntas_respuestas[p][examen_i[p]] - 1;
                    if (cantRespuestasIguales >= unCuartoDelAula){
                        cantPregsCopiadas += 1;
                    }
                     
                }
            }
            if ((cantPregsCopiadas==cantPregsRespondidas) && (cantPregsRespondidas > 0)){
                pre_res.add(i);
            }
            else{
                this.no_se_copiaron.add(this.alumnos.get(i));
            }
                   
        } 
        int[] res = new int[pre_res.size()];
        for (int i=0; i<pre_res.size();i++){
            res[i] = pre_res.get(i);
        }
        return res;
    }


















}
