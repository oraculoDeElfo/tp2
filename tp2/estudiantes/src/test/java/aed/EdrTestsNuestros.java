package aed;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
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
            new NotaFinal(10.0, 4),
            new NotaFinal(10.0, 1),
            new NotaFinal(0.0, 5),
            new NotaFinal(0.0, 3),
            new NotaFinal(0.0, 2),
            new NotaFinal(0.0, 0)
        };

        assertTrue(Arrays.equals(notas_finales_esperadas, notas_finales));
    }

    @Test
    void no_puede_copiarse(){
        edr = new Edr(5, 6, solucion);
        double[] notas;
        double[] notas_esperadas;

        edr.resolver(2, 1, 1);
        edr.copiarse(4);
        notas = edr.notas();
        notas_esperadas = new double[]{0.0, 0.0, 10.0, 0.0, 0.0, 0.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));
        
        for(int alumno = 0; alumno < 6; alumno++){
            edr.entregar(alumno);
        }

        int[] copiones = edr.chequearCopias();
        int[] copiones_esperados = new int[]{};
        assertTrue(Arrays.equals(copiones_esperados, copiones));

        NotaFinal[] notas_finales = edr.corregir();
        NotaFinal[] notas_finales_esperadas = new NotaFinal[]{
            new NotaFinal(10.0, 2),
            new NotaFinal(0.0, 5),
            new NotaFinal(0.0, 4),
            new NotaFinal(0.0, 3),
            new NotaFinal(0.0, 1),
            new NotaFinal(0.0, 0)
        };

        assertTrue(Arrays.equals(notas_finales_esperadas, notas_finales));
    }

    @Test
    void se_puede_copiar_de_cualquiera(){
        edr = new Edr(5, 6, solucion);
        double[] notas;
        double[] notas_esperadas;

        edr.resolver(1, 1, 1);
        edr.resolver(3, 1, 1);
        edr.resolver(5, 1, 1);
        edr.copiarse(4);
        notas = edr.notas();
        notas_esperadas = new double[]{0.0, 10.0, 0.0, 10.0, 10.0, 10.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));
        
        for(int alumno = 0; alumno < 6; alumno++){
            edr.entregar(alumno);
        }

        int[] copiones = edr.chequearCopias();
        int[] copiones_esperados = new int[]{1,3,4,5};
        assertTrue(Arrays.equals(copiones_esperados, copiones));

        NotaFinal[] notas_finales = edr.corregir();
        NotaFinal[] notas_finales_esperadas = new NotaFinal[]{
            new NotaFinal(0.0, 2),
            new NotaFinal(0.0, 0)
        };

        assertTrue(Arrays.equals(notas_finales_esperadas, notas_finales));
    }

    @Test
    void alumno_se_copia_varias_veces() {
        edr = new Edr(5, 4, solucion);
        double[] notas;
        double[] notas_esperadas;

        edr.resolver(0, 0, 0);
        edr.resolver(0, 1, 1);
        edr.resolver(0, 2, 2);
        edr.resolver(1, 3, 3);
        edr.resolver(1, 4, 4);
        edr.resolver(2, 5, 5);
        edr.resolver(2, 6, 6);
        edr.resolver(3, 7, 7);

        notas = edr.notas();
        notas_esperadas = new double[]{30.0, 20.0, 20.0, 10.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));

        edr.copiarse(3);
        notas = edr.notas();
        notas_esperadas = new double[]{30.0, 20.0, 20.0, 20.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));

        edr.copiarse(3);
        notas = edr.notas();
        notas_esperadas = new double[]{30.0, 20.0, 20.0, 30.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));

        edr.copiarse(3);
        notas = edr.notas();
        notas_esperadas = new double[]{30.0, 20.0, 20.0, 40.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));

        for(int alumno = 0; alumno < 4; alumno++){
            edr.entregar(alumno);
        }
}

    @Test
    void copiarse_con_mismas_respuestas() {
        edr = new Edr(5, 4, solucion);
        double[] notas;
        double[] notas_esperadas;


 
        edr.resolver(0, 0, 0);
        edr.resolver(0, 1, 1);
        edr.resolver(0, 2, 2);
  
        edr.resolver(1, 0, 0);
        edr.resolver(1, 1, 1);
        edr.resolver(1, 2, 2);
  
        edr.resolver(2, 0, 0);
        edr.resolver(2, 1, 1);
        edr.resolver(2, 2, 2);

        edr.resolver(3, 0, 0);
        edr.resolver(3, 1, 1);
        edr.resolver(3, 2, 2);


        notas = edr.notas();
        notas_esperadas = new double[]{30.0, 30.0, 30.0, 30.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));


        edr.copiarse(1);
  
        notas = edr.notas();
        notas_esperadas = new double[]{30.0, 30.0, 30.0, 30.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));
    }

    @Test
    void reuelve_la_misma_pregunta(){
        edr = new Edr(5, 6, solucion);
        double[] notas;
        double[] notas_esperadas;

        edr.resolver(1, 1, 1);
        notas = edr.notas();
        notas_esperadas = new double[]{0.0, 10.0, 0.0, 0.0, 0.0, 0.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));

        edr.resolver(1, 1, 9);
        notas = edr.notas();
        notas_esperadas = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));
    }
    
    @Test
    void testConsultarDarkWeb_n_1() {
        d_aula = 3;
        cant_alumnos = 4;
        solucion = new int[]{0, 1, 2};
        edr = new Edr(d_aula, cant_alumnos, solucion);
        
        edr.resolver(1, 0, solucion[0]); 
        edr.resolver(2, 0, solucion[0]); 
        edr.resolver(3, 0, solucion[0]); 
        

        int[] examenDW = solucion; 

        assertEquals(edr.alumnos_menor_nota.obtener(0).obtenerId(),0);
        edr.consultarDarkWeb(1, examenDW); 
        assertEquals(edr.alumnos_menor_nota.obtener(0).obtenerId(),1);
        
        double[] notas = edr.notas();
        double[] notas_esperadas = new double[]{100.0, 33.0, 33.0, 33.0}; 
        
        
        assertTrue(Arrays.equals(notas_esperadas, notas));
            
        edr.consultarDarkWeb(1, examenDW);
        
        notas = edr.notas();
        double[] notas_esperadas_2 = new double[]{100.0, 100.0, 33.0, 33.0};
        assertTrue(Arrays.equals(notas_esperadas_2, notas));
    }

    @Test
    void consultarDarkWeb_n_cero() {
       int[] solucion = new int[]{0,1,2,3};
       Edr edr = new Edr(5, 4, solucion);
       edr.resolver(1, 0, 0);
       double[] antes = edr.notas();
       edr.consultarDarkWeb(0, solucion);
       double[] despues = edr.notas();
       assertTrue(Arrays.equals(antes, despues));
    }

    @Test
    void todxs_consultan_DarkWeb() {
       int[] solucion = new int[]{0,1,2};
       Edr edr = new Edr(3, 3, solucion);
      
       edr.resolver(0, 0, 0);
       edr.resolver(1, 1, 1);
       edr.resolver(2, 2, 2);
      
       for (int i = 0; i < 3; i++) {
           edr.consultarDarkWeb(i, solucion);
       }
      
       double[] notas = edr.notas();
       double[] notas_esperadas = new double[]{100.0, 100.0, 100.0};
      
       assertTrue(Arrays.equals(notas_esperadas, notas));
    }

    @Test
    void consultarDarkWeb_con_estudiantes_entregados() {
        int[] sol = new int[]{0, 1, 2};
        edr = new Edr(3, 3, sol);
  
        edr.resolver(0, 0, 0);
        edr.resolver(1, 1, 1);
        edr.entregar(0);
  
        double[] antes = edr.notas();
        edr.consultarDarkWeb(1, sol);
        double[] despues = edr.notas();
  
        assertFalse(Arrays.equals(antes, despues));
    }

    @Test
    void corregir_sin_no_se_copiaron() {
        int[] solucion = new int[]{0, 1, 2, 3, 4};
        edr = new Edr(5, 3, solucion);
        NotaFinal[] res = edr.corregir();
        assertEquals(0, res.length);
    }
}


