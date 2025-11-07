package aed;

public class ListaEnlazada<T> {
    private Nodo primero;
    private Nodo ultimo;
    private int largo;

    private class Nodo {
        T valor;
        Nodo sig;
        Nodo ant;

        public Nodo(T elem){ 
            valor = elem;
        }
    }

    public ListaEnlazada() {
        primero = null;
        ultimo = null;
        largo = 0;
    }

    public int longitud() {
        return largo;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (largo == 0){
            primero = nuevo;
            ultimo = nuevo;
        }
        else{
            primero.ant = nuevo;
            nuevo.sig = primero;
            primero = nuevo;
        }
        largo += 1;
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (largo == 0){
            largo = 1;
            primero = nuevo;
            ultimo = nuevo;
        }
        else{
            largo = largo + 1;
            ultimo.sig = nuevo;
            nuevo.ant = ultimo;
            ultimo = nuevo;
        }
    }

    public T obtener(int i) {
        Nodo actual = primero;
        int contador = 0;
        while (contador <  i){
            actual = actual.sig;
            contador = contador +1;
        }
        return actual.valor;
    }

    public void eliminar(int i) {
        if (i == 0){
            Nodo segundo = primero.sig;
            primero = segundo;
        }
        /*Hay que aislar el caso del extremo final porque en el else iterado.sig es null*/
        else if (i == largo-1){
            Nodo anteUltimo = ultimo.ant;
            ultimo = anteUltimo;
        }
        else{
            Nodo iterador = primero;
            int contador = 0;
            while (contador <  i){
                iterador = iterador.sig;
                contador = contador +1;}
            Nodo anterior = iterador.ant;
            Nodo siguiente = iterador.sig;
            anterior.sig = siguiente;
            iterador = null;
        }
        largo = largo -1;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo actual = primero;
        int contador = 0;
        while (contador < indice) {
            actual = actual.sig;
            contador = contador + 1;
        }
        actual.valor = elem;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        Nodo iterador = lista.primero;
        int contador = 0;
        while(contador < lista.longitud()){
            this.agregarAtras(iterador.valor);
            iterador = iterador.sig;
            contador = contador + 1;
        }
    }
    
    @Override
    public String toString() {
        if (this.largo == 0){
            return "[]";
        }
        Nodo iterador = primero;
        int contador = 0;
        String res = "[";
        while (contador < largo-1){
            T elemento = iterador.valor;
            res = res + elemento.toString();
            res = res + ", ";

            iterador = iterador.sig;
            contador = contador + 1;
        }
        T elemento = iterador.valor;
        res = res + elemento.toString();
        res = res + "]";
        return res;
    }

    public class ListaIterador{
    	private Nodo i;

        public ListaIterador(){
            i = new Nodo(null);
            i.sig = primero;
            i.ant = null;
        }

        public boolean haySiguiente() {
            if (i == null){
                return false;
            }
            else{
                return i.sig != null;
            }
        }
        
        public boolean hayAnterior() {
            if (i == null){
                return false;
            }
            else{
                return i.ant != null;
            }
        }

        public T siguiente() {
            Nodo siguiente = i.sig;
            T elemento = siguiente.valor;
            i.ant = siguiente;
            i.sig = siguiente.sig;
            return elemento;
        }
        

        public T anterior() {
            Nodo anterior = i.ant;
            T elemento = anterior.valor;
            i.sig = anterior;
            i.ant = anterior.ant;
            return elemento;
        }
    }

    public ListaIterador iterador() {
        ListaIterador i = new ListaIterador();
        return i;
    }

}
