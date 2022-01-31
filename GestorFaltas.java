import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Un objeto de esta clase permite registrar estudiantes de un
 * curso (leyendo la informaci�n de un fichero de texto) y 
 * emitir listados con las faltas de los estudiantes, justificar faltas, 
 * anular matr�cula dependiendo del n� de faltas, .....
 *
 */
public class GestorFaltas {
    Estudiante[] estudiantes;
    int total;

    public GestorFaltas(int n) {
        estudiantes = new Estudiante[n];
        total = 0;
    }

    /**
     * Devuelve true si el array de estudiantes est� completo,
     * false en otro caso
     */
    public boolean cursoCompleto() {
        return total == estudiantes.length;
    }

    /**
     *    A�ade un nuevo estudiante solo si el curso no est� completo y no existe ya otro
     *    estudiante igual (con los mismos apellidos). 
     *    Si no se puede a�adir se muestra los mensajes adecuados 
     *    (diferentes en cada caso)
     *    
     *    El estudiante se a�ade de tal forma que queda insertado en orden alfab�tico de apellidos
     *    (de menor a mayor)
     *    !!OJO!! No hay que ordenar ni utilizar ning�n algoritmo de ordenaci�n
     *    Hay que insertar en orden 
     *    
     */
    public void addEstudiante(Estudiante nuevo) {
        String apellidos = nuevo.getApellidos();
        if(!cursoCompleto() && buscarEstudiante(apellidos)==-1){
            int i=total-1;
            while(i>=0 && estudiantes[i].getApellidos().compareTo(apellidos)>0){
                estudiantes[i+1]=estudiantes[i];
                i--;
            }
            estudiantes[i+1] = nuevo;
            total++;
        }
        else if(cursoCompleto()){
            System.out.println("El curso est� completo");
        }
        else if(buscarEstudiante(apellidos)!=-1){
            System.out.println("El alumno ya existe");
        }
    }

    /**
     * buscar un estudiante por sus apellidos
     * Si est� se devuelve la posici�n, si no est� se devuelve -1
     * Es indiferente may�sculas / min�sculas
     * Puesto que el curso est� ordenado por apellido haremos la b�squeda m�s
     * eficiente
     *  
     */
    public int buscarEstudiante(String apellidos) {
        String[] copia = new String[estudiantes.length];
        for(int i=0;i<total;i++){
            copia[i] = estudiantes[i].getApellidos();
        }
        int bus = Arrays.binarySearch(copia, 0, total, apellidos);
        if(total != 0 && bus>=0){
            return bus;
        }
        return -1;
    }

    /**
     * Representaci�n textual del curso
     * Utiliza StringBuilder como clase de apoyo.
     *  
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Relaci�n de estudiantes (" + total +")\n\n");
        for(int i=0;i<total;i++){
            sb.append(estudiantes[i].toString() + "\n");
        }
        return sb.toString();
    }

    /**
     *  Se justifican las faltas del estudiante cuyos apellidos se proporcionan
     *  El m�todo muestra un mensaje indicando a qui�n se ha justificado las faltas
     *  y cu�ntas
     *  
     *  Se asume todo correcto (el estudiante existe y el n� de faltas a
     *  justificar tambi�n)
     */
    public void justificarFaltas(String apellidos, int faltas) {
        int pos = buscarEstudiante(apellidos); 
        estudiantes[pos].setFaltasNoJustificadas(estudiantes[pos].getFaltasNoJustificadas()-faltas);
        estudiantes[pos].setFaltasJustificadas(estudiantes[pos].getFaltasJustificadas()+faltas);
        System.out.println("Justificadas " + faltas + " faltas a "+ estudiantes[pos].getApellidos() +", " + estudiantes[pos].getNombre());
    }

    /**
     * ordenar los estudiantes de mayor a menor n� de faltas injustificadas
     * si coinciden se tiene en cuenta las justificadas
     * M�todo de selecci�n directa
     */
    public void ordenar() {
        Estudiante[] orden = estudiantes.clone();
        for(int i=0;i<total;i++){
            int posmin = i;
            for(int j=i+1; j<total; j++){
                if (orden[j].getFaltasNoJustificadas()> orden[posmin].getFaltasNoJustificadas()){
                    posmin = j;
                }
            }
            Estudiante aux = orden[posmin];
            orden[posmin] = orden[i];
            orden[i] = aux;
        }
        estudiantes = orden;
    }

    /**
     * anular la matr�cula (dar de baja) a 
     * aquellos estudiantes con 30 o m�s faltas injustificadas
     */
    public void anularMatricula() {
        for(int i=0;i<total;i++){
            if(estudiantes[i].getFaltasNoJustificadas()>=30){
                System.arraycopy(estudiantes, i+1, estudiantes, i, total-i-1);
                total--;
            }
        }
    }

    /**
     * Lee de un fichero de texto los datos de los estudiantes
     *   con ayuda de un objeto de la  clase Scanner
     *   y los guarda en el array. 
     */
    public void leerDeFichero() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("estudiantes.txt"));
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                Estudiante estudiante = new Estudiante(linea);
                this.addEstudiante(estudiante);

            }

        }
        catch (IOException e) {
            System.out.println("Error al leer del fichero");
        }
        finally {
            if (sc != null) {
                sc.close();
            }
        }

    }

}
