package aed;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Arrays;

class HeapTests {

    ArrayList<Integer> arlstDeArr(int[] nums){
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int n: nums){
            res.add(n);
        }
        return res;
    }

    @Test
    void nuevo_heap() {
        int[] n1 = {0,1,2,3,4,5,6,7,8,9};
        int[] n2 = {5,7,6,0,2,8,3,1,4,9};

        ArrayList<Integer> nums = new ArrayList<Integer>();
        ArrayList<Integer> control = arlstDeArr(n1);

        Heap<Integer> minHeap = new Heap<Integer>(arlstDeArr(n2),-1);
        for (int i=0; i<10; i++){
            nums.add(minHeap.obtener(0));
            minHeap.borrar(0);
        }

        assertTrue(control.equals(nums));

        int[] n3 = {9,8,7,6,5,4,3,2,1,0};

        ArrayList<Integer> nums2 = new ArrayList<Integer>();
        control = arlstDeArr(n3);

        Heap<Integer> maxHeap = new Heap<Integer>(arlstDeArr(n2),1);
        for (int i=0; i<10; i++){
            nums2.add(maxHeap.obtener(0));
            maxHeap.borrar(0);
        }

        assertTrue(control.equals(nums2));
    }
}