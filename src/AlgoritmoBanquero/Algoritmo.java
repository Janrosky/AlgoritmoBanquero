/*
Proyecto Sistemas Operativos
Alumno: Alejandro Chacon Garita
II Cuatrimestre 2024

Cardenas, D. (2022). Algoritmo del banquero. https://youtu.be/-wrjpTBcuhs?si=xrJdkFeduJXmPTG- 
Elvira, J. (2020). Sistemas Operativos, Interbloqueo 4 Algoritmo del banquero. Mtro. José Luis Elvira. https://youtu.be/kbXjKKaMUAI?si=6EwVwn-9QHZ7qso_
Gagne, G., Galvin, P., & Silberschatz, A. (2006). Fundamentos de Sistemas Operativos. McGraw-Hill.
Tanenbaum, A. (2009). Sistemas Operativos Modernos. Pearson.
*/

package AlgoritmoBanquero;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;

public class Algoritmo {

    //Se cumple con el Punto 2 del enunciado, se utilizan Matrices y Vectores para implementar la simulación
    
    //Se declaran las variables para guardar el numero de Procesos y Recursos
    private static int numeroProcesos;
    private static int numeroRecursos;
        
    //Se declaran las Matrices
    public static int[][] necesarios;
    public static int[][] asignados; //En Uso
    public static int[][] maximos;
    public static int[] recursosIniciales;
    public static int[] recursosDisponibles;
    
    
    public static void inicializarMatrices(){
    //Se pide la cantidad de procesos y recursos para establecer el tamaño de las matrices
        numeroProcesos = solicitarProcesos();
        numeroRecursos = solicitarRecursos();    
        maximos = new int[numeroProcesos][numeroRecursos];
        asignados = new int[numeroProcesos][numeroRecursos];
        necesarios = new int[numeroProcesos][numeroRecursos];
        recursosIniciales = new int[numeroRecursos];
        recursosDisponibles = new int[numeroRecursos];    
    }

    //Se cumple con el punto 3 Se utilizan funciones 
    public static void definirMatrices() {
        try {
            //Se define el vector de Recursos Iniciales
            for (int j = 0; j < numeroRecursos; j++) {
                recursosIniciales[j] = asignarPositivo("Ingresa la cantidad Inicial de Recursos de Tipo " + j);
            }

            //Se define la matriz de Máximos
            for (int i = 0; i < numeroProcesos; i++) {
                for (int j = 0; j < numeroRecursos; j++) {
                    maximos[i][j] = asignarPositivo("Ingresa la cantidad Máxima que necesita el Proceso " + i + " del Recurso " + j + ":");
                }
            }
            
            //Se valida que los recursos Máximos solicitados por proceso no sean mayores a los Iniciales
            if (validarMaximos() == false) {
                JOptionPane.showMessageDialog(null, "Vuelva a realizar la Asignación", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //Se define la matriz de Asignados 
            for (int i = 0; i < numeroProcesos; i++) {
                for (int j = 0; j < numeroRecursos; j++) {
                    asignados[i][j] = asignarPositivo("Ingresa la cantidad Asignada que tiene el Proceso " + i + " del Recurso " + j + ":");
                }
            }
            
            //Se calcula el vector de Recursos Disponibles
            calcularRecursosDisponibles();
           
            //Validaciones para que el sistema no consuma más recursos de los disponibles
            
            //Se valida que la matriz de Asignados no sea mayor que la de máximos
            if (validarAsignados() == false) {
                JOptionPane.showMessageDialog(null, "Vuelva a realizar la Asignación", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Se valida que el vector disponibles no sea negativo
            if (validarDisponibles() == false) {
                JOptionPane.showMessageDialog(null, "Vuelva a realizar la Asignación", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //Se calcula la Matriz de Necesarios
            calcularNecesarios();

            //Se manejan errores de formato
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingresa únicamente números. Vuelve a realizar la Asignación", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private static void calcularRecursosDisponibles() {
        //Se comienza inicializando el vector de disponibles con los iniciales
        for (int j = 0; j < numeroRecursos; j++) {
            recursosDisponibles[j] = recursosIniciales[j];
        }

        //Luego se calculan los Disponibles mediante una resta de los Recursos asignados a cada Proceso
        for (int i = 0; i < numeroProcesos; i++) {
            for (int j = 0; j < numeroRecursos; j++) {
                recursosDisponibles[j] -= asignados[i][j];
            }
        }
    }
    
        public static int solicitarProcesos(){
        int numeroProcesos;
        while(true){
            try {
                numeroProcesos = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa la cantidad de Procesos: "));
                return numeroProcesos;
            } catch (HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debes ingresar un número para Continuar.","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static int solicitarRecursos(){
        int numeroRecursos;
        while (true) {
            try {
                numeroRecursos = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa la cantidad de Recursos: "));
                return numeroRecursos;
            } catch (HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debes ingresar un número para Continuar.","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void calcularNecesarios() {
        //Se genera la matriz de necesarios la cual será una resta entre la de máximos menos la de asignados
        for (int i = 0; i < numeroProcesos; i++) {
            for (int j = 0; j < numeroRecursos; j++) {
                necesarios[i][j] = maximos[i][j] - asignados[i][j];
            }
        }
    }
    
    private static int asignarPositivo(String mensaje) {
    int valor;
    do {
        String input = JOptionPane.showInputDialog(null, mensaje);
        if (input == null) {
            return -1; 
        }
        valor = Integer.parseInt(input);
        if (valor < 0) {
            JOptionPane.showMessageDialog(null, "Ingresa unicamente números positivos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } while (valor < 0);
    return valor;
}
    
    public static boolean validarAsignados() {
        //Se valida que la matriz de asignados no sea mayor que la de máximos
        for (int i = 0; i < numeroProcesos; i++) {
            for (int j = 0; j < numeroRecursos; j++) {
                if (asignados[i][j] > maximos[i][j]) {
                    JOptionPane.showMessageDialog(null, "Error Asignación: Las cantidades del recurso: " + j + " en el proceso: " + i +" superan la Matriz de Máximos. Posible número negativo.","Error",JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean validarMaximos() {
        //Se valida que los vectores de la matriz de maximos no superen el vector de recursos iniciales
        for (int i = 0; i < numeroProcesos; i++) {
            for (int j = 0; j < numeroRecursos; j++) {
                if (maximos[i][j] > recursosIniciales[j]) {
                    JOptionPane.showMessageDialog(null, "Error Asignación: Las cantidades del recurso: " + j + " en el proceso: " + i +" superan los recursos Iniciales. Proceso incapaz de terminar.","Error",JOptionPane.ERROR_MESSAGE);
                    return false; 
                }
            }
        }
        return true;
    }
    
    public static boolean validarDisponibles() {
        //Si se identifica que los recursos disponibles son negativos es porque el total de procesos intentó utilizar más de los disponibles.
        for (int j = 0; j < numeroRecursos; j++) {
            if (recursosDisponibles[j] < 0) {
                JOptionPane.showMessageDialog(null, "Error Asignación: Los recursos en uso superaron los Iniciales.","Error",JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
    
    public static String mostrarMatrices() {
        // Crear un String para construir el texto
        String texto = "";

        // Se recorre la matriz de máximos
        texto += "Matriz de Recursos Máximos:\n";
        for (int i = 0; i < numeroProcesos; i++) {
            for (int j = 0; j < numeroRecursos; j++) {
                texto += maximos[i][j] + " ";
            }
            texto += "\n";
        }

        // Se recorre la matriz de Asignados
        texto += "\nMatriz de Recursos Asignados (En Uso):\n";
        for (int i = 0; i < numeroProcesos; i++) {
            for (int j = 0; j < numeroRecursos; j++) {
                texto += asignados[i][j] + " ";
            }
            texto += "\n";
        }

        // Se recorre la matriz de Necesarios
        texto += "\nMatriz de Recursos Necesarios:\n";
        for (int i = 0; i < numeroProcesos; i++) {
            for (int j = 0; j < numeroRecursos; j++) {
                texto += necesarios[i][j] + " ";
            }
            texto += "\n";
        }

        // Se muestra el Vector de Iniciales
        texto += "\nRecursos Iniciales:\n";
        for (int i = 0; i < numeroRecursos; i++) {
            texto += recursosIniciales[i] + " ";
        }
        texto += "\n";

        // Se muestra el vector de recursos Disponibles
        texto += "Recursos Disponibles:\n";
        for (int j = 0; j < numeroRecursos; j++) {
            texto += recursosDisponibles[j] + " ";
        }
        texto += "\n";

        return texto; // Devuelve el texto construido
    }
    
    //Método importante ya que calcula el estado Seguro
    public static boolean estadoSeguro() {
        int[] temporal = new int[numeroRecursos]; //Vector auxiliar que contendra los recursos disponibles
        boolean[] terminado = new boolean[numeroProcesos]; 

        for (int i = 0; i < numeroRecursos; i++) {
            temporal[i] = recursosDisponibles[i];
        }

        int procesosAsignados = 0; //Se inicializan los ProcesosAsignados en 0

        while (procesosAsignados < numeroProcesos) {
            boolean asignado = false;

            //Ciclo que recorre todos los procesos
            for (int i = 0; i < numeroProcesos; i++) {
                //Si no se ha terminado el proceso i entonces verifica si se puede realizar asignacion
                if (!terminado[i] && verificarAsignacion(i, temporal)) {
                    //Si el metodo verifica que es posible realizar asignación entonces se realiza la actualización 
                    for (int j = 0; j < numeroRecursos; j++) {
                        temporal[j] += asignados[i][j];
                    }

                    //Se indica que se asigno un proceso y se simula la asignación
                    JOptionPane.showMessageDialog(null, "En orden se podrá Asignar el Proceso: " + i);
                    asignado = terminado[i] = true;
                    procesosAsignados++; //Se suma uno al contador de asignados
                }
            }

            //Se sale del while si no se logra asignar los procesos
            if (!asignado) {
                break;
            }
        }

        //Verifica que todos y cada uno de los procesos se hayan podido asignar para decir que fue estado seguro
        if (procesosAsignados == numeroProcesos) {
            JOptionPane.showMessageDialog(null, "Se encontró un estado seguro.", "Estado Seguro",JOptionPane.INFORMATION_MESSAGE);
            return true;
        //Caso contrario describe un estado inseguro
        } else {
            JOptionPane.showMessageDialog(null, "No se pueden asignar los Procesos de forma Segura", "Estado Inseguro",JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    //Metodo importante para validar si el Proceso está pidiendo más recursos de los que se encuentran disponibles temporalmente
    private static boolean verificarAsignacion(int proceso, int[] temporal) {
        for (int i = 0; i < numeroRecursos; i++) {
            //Calcula si ese proceso necesita de ese recurso una cantidad mayor a la temporal
            if (necesarios[proceso][i] > temporal[i]) {
                return false;
            }
        }
        return true;
    }
    
    //Método importante que devolvera True si se pueden solicitar Recursos y si el Estado es Seguro
    //Devuelve false si el estado es inseguro o si encuentra un error de solicitud
    public static boolean solicitarRecursos(int proceso, int[] solicitud) {
        //Se verifica que el número de proceso sea válido
        if (proceso < 0 || proceso >= numeroProcesos) {
            JOptionPane.showMessageDialog(null, "Error de solicitud: El numero de proceso no es valido.", "Error de Solicitud", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        //Se valida que el proceso no vaya a exceder la solicitud de los recursos que enrealidad necesita
        for (int j = 0; j < numeroRecursos; j++) {
            if (solicitud[j] > necesarios[proceso][j]) {
                JOptionPane.showMessageDialog(null, "Error de solicitud: El proceso excedio los necesarios.", "Error de Solicitud", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        //Se valida que la solicitud no exceda los recursos disponibles
        for (int j = 0; j < numeroRecursos; j++) {
            if (solicitud[j] > recursosDisponibles[j]) {
                JOptionPane.showMessageDialog(null, "Error de solicitud: Los recursos disponibles no son suficientes.", "Error de Solicitud", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        
        //Se comienza con la asignación de Recursos

        //Se realiza una asignación de los recursos que se solicitan al Proceso que lo solicitó
        for (int j = 0; j < numeroRecursos; j++) {
            recursosDisponibles[j] -= solicitud[j];
            asignados[proceso][j] += solicitud[j];
            necesarios[proceso][j] -= solicitud[j];
        }

        //Se valida el estado del algoritmo despues de la asignación
        if (!estadoSeguro()) {
            //En caso de que no sea segura se cancela la asignación de recursos
            for (int j = 0; j < numeroRecursos; j++) {
                recursosDisponibles[j] += solicitud[j];
                asignados[proceso][j] -= solicitud[j];
                necesarios[proceso][j] += solicitud[j];
            }
            JOptionPane.showMessageDialog(null, "Error de estado: Posibilidad de entrar en estado inseguro.", "Error de Estado", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //Validación para liberar Recursos
        boolean liberar = true;
        
        //Se recorren los recursos del proceso para ver si todavía necesita recursos
        for (int i = 0; i < numeroRecursos; i++) {
            //Si existen todavía recursos necesarios no se libera
            if (necesarios[proceso][i] != 0) {
                liberar = false;
                break;
            }
        }
        //Si se confirma que se puede liberar entonces se llama al metodo liberar recursos
        if (liberar) {
            liberarRecursos(proceso);
        }

        return true;
    }
    
    //Se cumple con el punto 6 del enunciado se liberan los procesos
    public static void liberarRecursos(int proceso) {
        //Se valida que el numero de proceso este en un rango correcto
        if (proceso < 0 || proceso >= numeroProcesos) {
            JOptionPane.showMessageDialog(null, "Error de liberacion: El numero de proceso es invalido.", "Error de Liberacion", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Se liberan los recursos de ese proceso y se reinician los asignados y necesarios
        JOptionPane.showMessageDialog(null, "Se liberaron automaticamente los recursos del proceso: " + proceso);
        for (int j = 0; j < numeroRecursos; j++) {
            recursosDisponibles[j] += asignados[proceso][j];
            asignados[proceso][j] = 0;
            necesarios[proceso][j] = 0;
        }
    }
    
    //Metodo para realizar una solicitud de Recursos desde el Programa Principal
    public static void iniciarSolicitud() {
        try {
        //Se solicita el numero de proceso
        JOptionPane.showMessageDialog(null, "Antes de iniciar la solicitud recuerda que los Números de Proceso comienzan por 0");
        int proceso = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa el número del Proceso"));
        //Se valida que el proceso exista
        if (proceso < 0 || proceso >= numeroProcesos) {
            JOptionPane.showMessageDialog(null, "Error de solicitud: El numero de proceso no es valido.", "Error de Solicitud", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int[] solicitud = new int[numeroRecursos]; //Se crea un vector solicitud del tamaño del número de recursos
        //Se solicitan los recursos
        for (int j = 0; j < numeroRecursos; j++) {
            solicitud[j] = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa la cantidad de recursos del tipo: " + j + " que el proceso número: " + proceso + " necesita."));
        }
        //Se confirma si la solicitud se completó exitosamente
        if (solicitarRecursos(proceso, solicitud)) {
            JOptionPane.showMessageDialog(null, "Recursos asignados exitosamente.","Exito", JOptionPane.INFORMATION_MESSAGE);
        //Caso contrario se entro en estado inseguro o sucedió un error de solicitud
        } else {
            System.out.println("La solicitud no se completo");
            JOptionPane.showMessageDialog(null, "La solicitud no se completo","Error", JOptionPane.INFORMATION_MESSAGE);
        }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La solicitud no se completo","Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
