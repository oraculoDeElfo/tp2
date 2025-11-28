package aed;
import java.util.ArrayList;

public class Heap<T extends Indexable<T>> {
    private ArrayList<T> heap; //N = Largo del array
    private int[] id_a_indiceHeap; //I = Largo del array
    private int longitud;
    private int tipoHeap;//-1 para min-heap, 1 para max-heap
    
    //O(N+I) -> Como sabemos que N>=I -> //O(N)
    public Heap (T[] arr, int t, int maxId){ 
        this.longitud = arr.length;//O(1)
        this.heap = new ArrayList<T>();// O(1)
        this.id_a_indiceHeap = new int[maxId]; // O(1)
        this.tipoHeap = t;// O(1)

        //O(I)
        for (int i = 0; i < maxId; i++) {
            this.id_a_indiceHeap[i] = -1;//O(1)
        }

        //O(N)
        for (int i=0;i<this.longitud;i++){ 
            this.heap.add(arr[i]);//O(1)
            this.id_a_indiceHeap[arr[i].obtenerId()] = i;//O(1)
        }
        ordenInicial();//O(N)
    }
    //O(N+I) -> Como sabemos que N>=I -> //O(N)
    public Heap (ArrayList<T> arr, int t, int maxId){ 
        this.longitud = arr.size();//O(1)
        this.heap = new ArrayList<T>();// O(1)
        this.id_a_indiceHeap = new int[maxId]; // O(1)
        this.tipoHeap = t;// O(1)

        //O(I)
        for (int i = 0; i < maxId; i++) {
            this.id_a_indiceHeap[i] = -1;//O(1)
        }

        //O(N)
        for (int i=0;i<this.longitud;i++){ 
            this.heap.add(arr.get(i));//O(1)
            this.id_a_indiceHeap[arr.get(i).obtenerId()] = i;//O(1)
        }
        ordenInicial();//O(N)
    }

    //O(N)
    private void ordenInicial(){ 
        for (int i = padre(this.longitud-1);i>=0;i--){
            siftDown(i);
        }
    }

    //O(log(N))
    public void agregar(T obj){ 
        int id = obj.obtenerId();
        if (this.id_a_indiceHeap[id] == -1){
            this.longitud++;//O(1)
            this.heap.add(obj);//O(1)
            siftUp(this.longitud-1);//O(log(N)) 
        }   
    }   

    //O(log(N))
    public void borrar_por_id(int id){ 
        if (this.id_a_indiceHeap[id] != -1){
            this.borrar(this.id_a_indiceHeap[id]);
        }
    }

    //O(log(n))
    public void borrar(int indice){
        //O(1)
        T a_borrar = this.heap.get(indice);
        
        //O(1)
        cambiar(indice, this.longitud-1);
        
        //O(1)
        int id_eliminado = a_borrar.obtenerId();
        
        //O(1)
        this.heap.remove(this.longitud-1);
        this.longitud--;

        //O(1)
        this.id_a_indiceHeap[id_eliminado] = -1;
        
        //O(1)
        if (indice == this.longitud){return;}
    
        //O(log N)
        if((indice == 0) || this.tipoHeap * this.heap.get(indice).compareTo(this.heap.get(padre(indice))) > 0){
            siftDown(indice);//O(log N)
        }
        else{
            siftUp(indice);//O(log N)
        }
    }
    //O(log(n))
    public void actualizar_nota_id(int id){
        actualizar_nodo(id_a_indiceHeap[id]);
    }

    //O(log(n))
    private void actualizar_nodo(int indice){
        //O(log N)
        if(indice > 0 && this.tipoHeap * this.heap.get(indice).compareTo(this.heap.get(padre(indice))) > 0){
            siftUp(indice);//O(log N)
        }
        else{
            siftDown(indice);//O(log N)
        }
    }
    

    //O(1)
    private int padre(int i){
        return (i-1)/2;
    }

    //O(1)
    private int izq(int i){
        return 2*i+1;
    }

    //O(1)
    private int der(int i){
        return 2*i+2;
    }
    
    //O(1)
    private void cambiar(int indice1, int indice2){
        T aux = this.heap.get(indice1);
        this.heap.set(indice1, this.heap.get(indice2));
        this.heap.set(indice2, aux);
        actualizaIndice(this.heap.get(indice2), indice2);
        actualizaIndice(this.heap.get(indice1), indice1);
    }

    //O(1)
    private void actualizaIndice(T cosa, int indiceNuevo){
        id_a_indiceHeap[cosa.obtenerId()] = indiceNuevo;
    }

    //O(log(n))
    public void siftDown(int i){
        //O(1)
        if (izq(i) > this.longitud-1){
            return;
        }
        int aux = i;//O(1)
        //O(1)
        if(this.tipoHeap * this.heap.get(aux).compareTo(this.heap.get(izq(i))) < 0){
            aux = izq(i);
        }
        //O(1)
        if((der(i)<=this.longitud-1) && (this.tipoHeap * this.heap.get(aux).compareTo(this.heap.get(der(i))) < 0)){
            aux = der(i);
        }
        //O(log N)
        if (aux != i){
            cambiar(aux,i);
            siftDown(aux);//O(log N)
        }
    }
    //O(log(n))
    public void siftUp(int i){
        if (i==0){return;}
        if (this.tipoHeap * this.heap.get(i).compareTo(this.heap.get(padre(i))) > 0){
            cambiar(i, padre(i));
            siftUp(padre(i));
        }
        return;
    }

    //O(1)
    public T raiz(){
        return this.heap.get(0);
    }

    //O(1)
    public T obtener_con_id(int id){
        int indice = this.id_a_indiceHeap[id];
        return this.obtener(indice);
    }
    
    //O(1)
    public T obtener(int indice){
        return this.heap.get(indice);
    }

}















