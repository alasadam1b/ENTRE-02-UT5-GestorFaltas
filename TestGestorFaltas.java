/**
 * Punto de entrada a la aplicación
 */
public class TestGestorFaltas {
    /**
     * Se acepta como argumento del main() el nº máximo de estudiantes
     * y una vez creado el gestor de faltas se muestra la información solicitada
     * (ver enunciado)
     */
    public static void main(String[] args) {
        if(args.length!=1){
            System.out.println("Error en argumentos\nSintaxis:java TestGestorFaltas <max_estudiantes>");
            return;
        }
        
        GestorFaltas curso = new GestorFaltas(Integer.parseInt(args[0]));
        curso.leerDeFichero();
        System.out.println(curso.toString());
        curso.justificarFaltas("IRISO FLAMARIQUE", 6);
        System.out.println("Ordenado de > a < no de faltas injustificadas");
        curso.ordenar();
        System.out.println(curso.toString());
        curso.anularMatricula();
        System.out.println(curso.toString());
    }

}
