/**
 * Un objeto de esta clase guarda la información de un estudiante
 *
 */
public class Estudiante {
    private final static String SEPARADOR = ",";
    private String nombre = "";
    private String apellidos;
    private int faltasNoJustificadas;
    private int faltasJustificadas;
    private TipoApercibimiento apercibimiento;
    
    /**
     *  
     *  Inicializa los atributos a partir de la información recibida
     *  Esta información se encuentra en lineaDatos
     *  (ver enunciado) 
     *  
     */
    public Estudiante(String lineaDatos) {
        String[] tokens = lineaDatos.strip().split(SEPARADOR);
        String[] tokens2;
        tokens2 = tokens[0].strip().split(" ");
        for(int i=0; i<tokens2.length-1; i++){
            if(!tokens2[i].equalsIgnoreCase("")){
                nombre += tokens2[i].toUpperCase().charAt(0) + ". ";
            }
        }
        StringBuilder sb = new StringBuilder(tokens2[tokens2.length-1]);
        sb.setCharAt(0, tokens2[tokens2.length-1].toUpperCase().charAt(0));
        nombre += sb.toString();
        apellidos = tokens[1].toUpperCase().strip();
        apercibimiento = TipoApercibimiento.DIEZ;
        faltasNoJustificadas =  Integer.parseInt(tokens[2].strip());
        faltasJustificadas = Integer.parseInt(tokens[3].strip());
    }

    /**
     * accesor para el nombre completo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * mutador para el nombre
     *  
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * accesor para los apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     *  mutador para los apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos.toUpperCase();
    }

    /**
     * accesor para el nº de faltas no justificadas
     */
    public int getFaltasNoJustificadas() {
        return faltasNoJustificadas;
    }

    /**
     * mutador para el nº de faltas no justificadas
     */
    public void setFaltasNoJustificadas(int faltasNoJustificadas) {
        this.faltasNoJustificadas = faltasNoJustificadas;
    }

    /**
     * accesor para el nº de faltas justificadas
     */
    public int getFaltasJustificadas() {
        return faltasJustificadas;
    }

    /**
     *  mutador para el nº de faltas justificadas
     */
    public void setFaltasJustificadas(int faltasJustificadas) {
        this.faltasJustificadas = faltasJustificadas;
    }

    /**
     *  se justifican n faltas que hasta el momento eran injustificadas
     *  Se asume n correcto
     */
    public void justificar(int n) {
        this.faltasNoJustificadas -= n;
        this.faltasJustificadas += n;
    }

    /**
     * Representación textual del estudiante
     * (ver enunciado)
     */
    public String toString() {
        String lineaFormateada = "";
        lineaFormateada += String.format("%-25s%s\n", "Apellidos y Nombre:", apellidos + ", " + nombre);
        lineaFormateada += String.format("%-25s%s\n", "Faltas No Justificadas:", faltasNoJustificadas);
        lineaFormateada += String.format("%-25s%s\n", "Faltas Justificadas:", faltasJustificadas);
        lineaFormateada += String.format("%-25s%s\n", "Apercibimientos:", apercibimiento);
        lineaFormateada += String.format("%-25s\n", "--------------------");
        return lineaFormateada;
    }

    public static void main(String[] args) {
        Estudiante e1 = new Estudiante("  ander ibai  ,  Ruiz Sena , 12, 23");
        System.out.println(e1);
        System.out.println();
        Estudiante e2 = new Estudiante(
                " pedro josé   andrés  ,  Troya Baztarrica , 42, 6 ");
        System.out.println(e2);
        System.out.println();
        Estudiante e3 = new Estudiante("  Javier  ,  Suescun  Andreu , 39, 9 ");
        System.out.println(e3);
        System.out.println();
        Estudiante e4 = new Estudiante("julen, Duque Puyal, 5, 55");
        System.out.println(e4);
        System.out.println();
        

        System.out.println("---------------------------------");
        e1.justificar(3);
        System.out.println(e1);
        System.out.println();

        System.out.println("---------------------------------");

        e3.justificar(12);
        System.out.println(e3);
        System.out.println();

    }
}
