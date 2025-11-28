package aed;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Arrays;

public class HeapNotasTests {
    private NotaFinal[] crearDatosIniciales() {
        NotaFinal n0 = new NotaFinal(7.0, 0); 
        NotaFinal n1 = new NotaFinal(4.0, 1);
        NotaFinal n2 = new NotaFinal(9.0, 2);
        NotaFinal n3 = new NotaFinal(7.0, 3); 
        NotaFinal n4 = new NotaFinal(10.0, 4);
        return new NotaFinal[]{n0, n1, n2, n3, n4};
    }

    int maxId = 4;
    int TIPO_MAX_HEAP = 1;
    int TIPO_MIN_HEAP = -1;

    @Test
    void testInicializacionMaxHeap() {
        NotaFinal[] datos = crearDatosIniciales();
        Heap<NotaFinal> maxHeap = new Heap<>(datos, TIPO_MAX_HEAP,5);
        ArrayList<NotaFinal> esperado = new ArrayList<NotaFinal>();
        esperado.add(new NotaFinal(10.0, 4));
        esperado.add(new NotaFinal(9.0, 2));
        esperado.add(new NotaFinal(7.0, 3));
        esperado.add(new NotaFinal(7.0, 0));
        esperado.add(new NotaFinal(4.0, 1));

        ArrayList<NotaFinal> notas = new ArrayList<NotaFinal>();
        for (int i = 0; i<5; i++){
            notas.add(maxHeap.obtener(0));
            maxHeap.borrar(0);
        }

        assertEquals(esperado, notas);
    }
    
    @Test
    void testInicializacionMinHeap() {
        NotaFinal[] datos = crearDatosIniciales();
        Heap<NotaFinal> minHeap = new Heap<>(datos, TIPO_MIN_HEAP,5);
        ArrayList<NotaFinal> esperado = new ArrayList<NotaFinal>();
        esperado.add(new NotaFinal(4.0, 1));
        esperado.add(new NotaFinal(7.0, 0));
        esperado.add(new NotaFinal(7.0, 3));
        esperado.add(new NotaFinal(9.0, 2));
        esperado.add(new NotaFinal(10.0, 4));

        ArrayList<NotaFinal> notas = new ArrayList<NotaFinal>();
        for (int i = 0; i<5; i++){
            notas.add(minHeap.obtener(0));
            minHeap.borrar(0);
        }

        assertEquals(esperado, notas);
    }

    @Test
    void testAgregarElemento() {
        NotaFinal[] datos = crearDatosIniciales();
        Heap<NotaFinal> maxHeap = new Heap<>(datos, TIPO_MAX_HEAP, 6);

        NotaFinal nuevaNota = new NotaFinal(11.0, 5); 
        maxHeap.agregar(nuevaNota);

        assertEquals(nuevaNota, maxHeap.raiz());
        assertEquals(nuevaNota, maxHeap.obtener_con_id(5));
    }
    @Test
    void testAgregarElemento_min() {
        NotaFinal[] datos = crearDatosIniciales();
        Heap<NotaFinal> minHeap = new Heap<>(datos, TIPO_MIN_HEAP, 6);

        NotaFinal nuevaNota = new NotaFinal(0.0, 5); 
        minHeap.agregar(nuevaNota);

        assertEquals(nuevaNota, minHeap.raiz());
        assertEquals(nuevaNota, minHeap.obtener_con_id(5));
    }

    @Test
    void testBorrarPorId() {
        NotaFinal[] datos = crearDatosIniciales();
        Heap<NotaFinal> maxHeap = new Heap<>(datos, TIPO_MAX_HEAP,5);

        maxHeap.borrar_por_id(3); 
        ArrayList<NotaFinal> esperado = new ArrayList<NotaFinal>();
        esperado.add(new NotaFinal(10.0, 4));
        esperado.add(new NotaFinal(9.0, 2));
        esperado.add(new NotaFinal(7.0, 0));
        esperado.add(new NotaFinal(4.0, 1));

        ArrayList<NotaFinal> notas = new ArrayList<NotaFinal>();
        for (int i = 0; i<4; i++){
            notas.add(maxHeap.obtener(0));
            maxHeap.borrar(0);
        }
        assertEquals(esperado, notas);
    }

    @Test
    void testBorrarPorIdMin() {
        NotaFinal[] datos = crearDatosIniciales();
        Heap<NotaFinal> minHeap = new Heap<>(datos, TIPO_MIN_HEAP,5);

        minHeap.borrar_por_id(3); 
        ArrayList<NotaFinal> esperado = new ArrayList<NotaFinal>();
        esperado.add(new NotaFinal(4.0, 1));
        esperado.add(new NotaFinal(7.0, 0));
        esperado.add(new NotaFinal(9.0, 2));
        esperado.add(new NotaFinal(10.0, 4));

        ArrayList<NotaFinal> notas = new ArrayList<NotaFinal>();
        for (int i = 0; i<4; i++){
            notas.add(minHeap.obtener(0));
            minHeap.borrar(0);
        }
        assertEquals(esperado, notas);
    }

    @Test
    void modificarNota() {
        NotaFinal[] datos = crearDatosIniciales();
        Heap<NotaFinal> maxHeap = new Heap<>(datos, TIPO_MAX_HEAP,5);

        datos[0].actualizarNotar(10.0);
        maxHeap.actualizar_nota_id(0);

        ArrayList<NotaFinal> esperado = new ArrayList<NotaFinal>();
        esperado.add(new NotaFinal(10.0, 4));
        esperado.add(new NotaFinal(10.0, 0));
        esperado.add(new NotaFinal(9.0, 2));
        esperado.add(new NotaFinal(7.0, 3));
        esperado.add(new NotaFinal(4.0, 1));

        ArrayList<NotaFinal> notas = new ArrayList<NotaFinal>();
        for (int i = 0; i<5; i++){
            notas.add(maxHeap.obtener(0));
            maxHeap.borrar(0);
        }
        assertEquals(esperado, notas);
    }

    @Test
    void modificarNotaMin() {
        NotaFinal[] datos = crearDatosIniciales();
        Heap<NotaFinal> minHeap = new Heap<>(datos, TIPO_MIN_HEAP,5);

        datos[0].actualizarNotar(10.0);
        minHeap.actualizar_nota_id(0);

        ArrayList<NotaFinal> esperado = new ArrayList<NotaFinal>();
        esperado.add(new NotaFinal(4.0, 1));
        esperado.add(new NotaFinal(7.0, 3));
        esperado.add(new NotaFinal(9.0, 2));
        esperado.add(new NotaFinal(10.0, 0));
        esperado.add(new NotaFinal(10.0, 4));

        ArrayList<NotaFinal> notas = new ArrayList<NotaFinal>();
        for (int i = 0; i<5; i++){
            notas.add(minHeap.obtener(0));
            minHeap.borrar(0);
        }
        assertEquals(esperado, notas);
    }

    
    
}
