package aed;

import java.util.ArrayList;

public class Heap<T extends Comparable<T>> {
    
    private ArrayList<T> heap;
    private int[] id_a_indiceHeap;
    private int longitud;
    
    public Heap (ArrayList<T> arr){
        this.longitud = arr.size();     //Asumo que puedo usar esta función y que es O(1), si no pediría la longitud como argumento que es O(1)
        this.heap = new ArrayList<T>();
        this.id_a_indiceHeap = new int[this.longitud];
        for (int i=0;i<this.longitud;i++){
            this.heap.add(arr.get(i));      //Esto es O(n) porque recorre una vez todos los elementos del array
            this.id_a_indiceHeap[i] = i;
        }
        ordenInicial();
    }

    private void ordenInicial(){                                //Esto es O(n) porque es una implementación del algoritmo de Floyd 
        for (int i = padre(this.longitud-1);i>=0;i--){
            siftDown(i);
        }
    }

    public void agregar(T obj){                                 //Esto es O(log(n)) porque recorre una vez cada nivel, que son log2(n) porque tiene estructura de árbol binario.
        this.longitud++;
        this.heap.add(obj);
        siftUp(this.longitud-1);      
    }   

    public void borrar_por_id(int id){
        this.borrar(this.id_a_indiceHeap[id]);
    }

    public void borrar(int indice){                             //Esto es O(log(n)) por la misma razón que agregar()
        cambiar(indice, this.longitud-1);
        this.heap.remove(this.longitud-1);      //Esto es O(1) por consigna
        this.longitud--;
        if (indice == this.longitud){return;}
        if((indice == 0) || this.heap.get(indice).compareTo(this.heap.get(padre(indice))) < 0){
            siftDown(indice);
        } else{
            siftUp(indice);
        }
    }

    public void actualizar_nota_id(int id){
        actualizar_nodo(id_a_indiceHeap[id]);
    }

    private void actualizar_nodo(int indice){
        siftDown(indice);
    }

    private int padre(int i){
        return (i-1)/2;
    }

    private int izq(int i){
        return 2*i+1;
    }

    private int der(int i){
        return 2*i+2;
    }

    private void cambiar(int indice1, int indice2){         //O(1) modifica variables de a una una cantidad fija de veces.
        T aux = this.heap.get(indice1);
        this.heap.set(indice1, this.heap.get(indice2));
        this.heap.set(indice2, aux);
        actualizaIndice(this.heap.get(indice2), indice2);
        actualizaIndice(this.heap.get(indice1), indice1);
    }

    private void actualizaIndice(T cosa, int indiceNuevo){
        
        if(cosa instanceof Alumno){
            Alumno c = (Alumno) cosa;
            id_a_indiceHeap[c.obtenerId()] = indiceNuevo;
        }
        //     if (tipoHeap == 1){
        //       
        // } else if (cosa instanceof Ciudad){
        //     Ciudad c = (Ciudad) cosa;
        //     c.cambiaPosSuperavit(indiceNuevo);
        // }
    }

    public void siftDown(int i){
        if (izq(i) > this.longitud-1){
            return;
        }
        int aux = i;
        if(this.heap.get(aux).compareTo(this.heap.get(izq(i))) < 0){
            aux = izq(i);
        }
        if((der(i)<=this.longitud-1) && (this.heap.get(aux).compareTo(this.heap.get(der(i))) < 0)){
            aux = der(i);
        }
        if (aux != i){
            cambiar(aux,i);
            siftDown(aux);
        }
    }

    public void siftUp(int i){
        if (i==0){return;}
        if (this.heap.get(i).compareTo(this.heap.get(padre(i))) > 0){
            cambiar(i, padre(i));
            siftUp(padre(i));
        }
        return;
    }

    public T raiz(){
        return this.heap.get(0);
    }

    public T obtener_con_id(int id){
        int indice = this.id_a_indiceHeap[id];
        return this.obtener(indice);
    }

    private T obtener(int indice){
        return this.heap.get(indice);
    }

}















