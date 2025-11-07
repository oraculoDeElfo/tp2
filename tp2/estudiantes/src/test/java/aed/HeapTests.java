package aed;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Arrays;

class HeapTests {

    @Test
    void nuevo_heap() {
        ArrayList<Integer> nums = new ArrayList<Integer>(){{
            add(2);
            add(4);
            add(3);
            add(7);
            add(9);
            add(0);
            add(5);
            add(8);
            add(6);
            add(1);
        }};

        ArrayList<Integer> nums2 = new ArrayList<Integer>(){{
            add(9);
            add(8);
            add(7);
            add(6);
            add(5);
            add(4);
            add(3);
            add(2);
            add(1);
            add(0);
        }};

        ArrayList<Integer> control = new ArrayList<Integer>();
        Heap<Integer> heap = new Heap<Integer>(nums);
        for (int i=0; i<10; i++){
            control.add(heap.obtener(0));
            heap.borrar(0);
        }

        //assertTrue()
        assertTrue(control.equals(nums2));
    }
}
