package aed;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Arrays;

class EdrTestsNuestros {
    Edr edr;
    int d_aula;
    int cant_alumnos;
    int[] solucion;

    @BeforeEach
    void setUp(){
        d_aula = 5;
        cant_alumnos = 4;
        solucion = new int[]{0,1,2,3,4,5,6,7,8,9};

        edr = new Edr(d_aula, cant_alumnos, solucion);
    }

    @Test
    void alumno_se_copia_adelante(){
    edr = new Edr(5, 4, solucion);
    double[] notas;
    double[] notas_esperadas;

    edr.resolver(0, 0, 0);
    edr.resolver(1, 1, 1);
    edr.resolver(2, 2, 2);
    edr.resolver(3, 3, 3);

    notas = edr.notas();
    notas_esperadas = new double[]{10.0, 10.0, 10.0, 10.0};
    assertTrue(Arrays.equals(notas_esperadas, notas));

    edr.copiarse(3);
        
    notas = edr.notas();
    notas_esperadas = new double[]{10.0, 10.0, 10.0, 20.0};
    assertTrue(Arrays.equals(notas_esperadas, notas));

    for(int alumno = 0; alumno < 4; alumno++){
        edr.entregar(alumno);
    }

    int[] copiones = edr.chequearCopias();
    int[] copiones_esperados = new int[]{0};
    assertTrue(Arrays.equals(copiones_esperados, copiones));

        NotaFinal[] notas_finales = edr.corregir();
        NotaFinal[] notas_finales_esperadas = new NotaFinal[]{
            new NotaFinal(20.0, 3),
            new NotaFinal(10.0, 2),
            new NotaFinal(10.0, 1)

        };

        assertTrue(Arrays.equals(notas_finales_esperadas, notas_finales));

    }


    @Test
    void alumno_se_copia_solo_adelante(){
        edr = new Edr(5, 6, solucion);
        double[] notas;
        double[] notas_esperadas;

        //edr.resolver(0, 0, 0);
        edr.resolver(1, 1, 1);
        //edr.resolver(2, 2, 2);
        //edr.resolver(3, 3, 3);

        
        notas = edr.notas();
        notas_esperadas = new double[]{0.0, 10.0, 0.0, 0.0, 0.0, 0.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));
        
        edr.copiarse(4);
        
       
        
        notas = edr.notas();
        notas_esperadas = new double[]{0.0, 10.0, 0.0, 0.0, 10.0, 0.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));


        for(int alumno = 0; alumno < 6; alumno++){
            edr.entregar(alumno);
        }

        int[] copiones = edr.chequearCopias();
        int[] copiones_esperados = new int[]{};
        assertTrue(Arrays.equals(copiones_esperados, copiones));

        NotaFinal[] notas_finales = edr.corregir();
        NotaFinal[] notas_finales_esperadas = new NotaFinal[]{
            new NotaFinal(0.0, 5),
            new NotaFinal(10.0, 4),
            new NotaFinal(0.0, 3),
            new NotaFinal(0.0, 2),
            new NotaFinal(10.0, 1),
            new NotaFinal(0.0, 0)
        };

        assertTrue(Arrays.equals(notas_finales_esperadas, notas_finales));
    }
}