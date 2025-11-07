package aed;

interface ColaDePrioridadAlumnos<T> {
    /**
     * Modifica la nota del alumno pasado por parámetro y reordena un heap de tipo nota alumno
     */
    public void cambiarPrioridad(int nota, int idAlumno);

    /**
     * Agrega el elemento a un heap (sin importar si esta repetido)
     */
    public void encolar(T elemento);
}
