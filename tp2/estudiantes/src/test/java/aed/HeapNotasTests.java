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

    private NotaFinal[] crearDatosIniciales_sin_n2() {
        NotaFinal n0 = new NotaFinal(7.0, 0); 
        NotaFinal n1 = new NotaFinal(4.0, 1);
        NotaFinal n2 = new NotaFinal(9.0, 2);
        NotaFinal n3 = new NotaFinal(7.0, 3); 
        NotaFinal n4 = new NotaFinal(10.0, 4);
        return new NotaFinal[]{n0, n1, n3, n4};
    }
    int maxId = 4+1;
    int TIPO_MAX_HEAP = 1;
    int TIPO_MIN_HEAP = -1;

    @Test
    void testInicializacionMaxHeap() {
        NotaFinal[] datos = crearDatosIniciales();
        Heap<NotaFinal> maxHeap = new Heap<>(datos, TIPO_MAX_HEAP);
        NotaFinal esperada = new NotaFinal(10, 4); 
        assertEquals(esperada, maxHeap.raiz());
    }
    @Test
    void testInicializacionMaxHeap_2() {
        NotaFinal[] datos = crearDatosIniciales_sin_n2();
        Heap<NotaFinal> maxHeap = new Heap<>(datos, TIPO_MAX_HEAP,maxId);
        NotaFinal esperada = new NotaFinal(10, 4); 
        assertEquals(esperada, maxHeap.raiz());
    }
    @Test
    void testInicializacionMinHeap() {
        NotaFinal[] datos = crearDatosIniciales();
        Heap<NotaFinal> maxHeap = new Heap<>(datos, TIPO_MIN_HEAP);
        NotaFinal esperada = new NotaFinal(4.0, 1); 
        assertEquals(esperada, maxHeap.raiz());
    }
    @Test
    void testInicializacionMinHeap_2() {
        NotaFinal[] datos = crearDatosIniciales_sin_n2();
        Heap<NotaFinal> maxHeap = new Heap<>(datos, TIPO_MIN_HEAP,maxId);
        NotaFinal esperada = new NotaFinal(4, 1); 
        assertEquals(esperada, maxHeap.raiz());
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
        Heap<NotaFinal> maxHeap = new Heap<>(datos, TIPO_MIN_HEAP, 6);

        NotaFinal nuevaNota = new NotaFinal(0.0, 5); 
        maxHeap.agregar(nuevaNota);

        assertEquals(nuevaNota, maxHeap.raiz());
        assertEquals(nuevaNota, maxHeap.obtener_con_id(5));
    }

    @Test
    void testBorrarPorId() {
        NotaFinal[] datos = crearDatosIniciales();
        Heap<NotaFinal> maxHeap = new Heap<>(datos, TIPO_MAX_HEAP);
        assertEquals(new NotaFinal(10.0, 4), maxHeap.raiz());

        maxHeap.borrar_por_id(4); 
        NotaFinal nuevaRaizEsperada = new NotaFinal(9.0, 2);
        assertEquals(nuevaRaizEsperada, maxHeap.raiz());
        assertEquals(2, maxHeap.raiz().obtenerId());
    }
}
