package analizador;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;

public class Analizador extends javax.swing.JFrame implements ActionListener {

    public Analizador() {
        this.setTitle("Analizador");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        initComponents();

    }

    static int nError = 1;
    static int nOpe = 1;
    static int nNum = 1;
    static int nVar = 1;
    static String[] noToken;
    static List<Variable> tablaDeVariables = new ArrayList<>();
    static List<Variable> tablaDeErrores = new ArrayList<>();
    static List<String[]> TablaTriplos = new ArrayList<>();
    static List<String[]> archivoTriplosD = new ArrayList<>();
    static List<Triplo> TriplosFile = new ArrayList<>();//AHORA CADA LINEA ES DONDE EMPÍEZA EL TRIPLO
    static String PReservada[] = new String[]{ //ARREGLO DE PALABRAS RESERVADAS
        "=", /// equals
        "String", "string",//1,2 letras
        "Int", "int", "Integer", //3 - oo   nums
        "float", "Float",
        "bool", "boolean",
        "double", "Double", "Decimal",
        "char",
        ",", ";", "{", "}", "else"
    };

    public class Triplo {   //CLASE DEL TRIPLO

        public int ID;
        public int Linea;
        public List<String[]> tabla;

        public Triplo(int id, int linea, List<String[]> tabla) { //CONTRUCTOR DEL TRIPLO
            this.ID = id;
            this.Linea = linea;
            this.tabla = tabla;
        }
    }

    int contadorTriplos = 0;
    int contadorLineaT = 1;
    int esDo = -1;
    int esLlaveFin = -1;
    int esLlaveFinIR = -1;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbutton_Analizar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtable_TablaDeVariables = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtable_TablaDeErrores = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButton_Limpiar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_Datos = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea_erroresVar = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Proyecto PARTE I");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbutton_Analizar.setText("Analizar:");
        jbutton_Analizar.addActionListener(this);
        getContentPane().add(jbutton_Analizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 106, -1));

        jtable_TablaDeVariables.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TOKEN", "LEXEMA"
            }
        ));
        jScrollPane3.setViewportView(jtable_TablaDeVariables);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 277, 210));

        jtable_TablaDeErrores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TOKEN", "Descripcion"
            }
        ));
        jScrollPane4.setViewportView(jtable_TablaDeErrores);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 110, 255, 200));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tabla de simbolos");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, 234, -1));

        jButton_Limpiar.setText("Borrar");
        jButton_Limpiar.addActionListener(this);
        getContentPane().add(jButton_Limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 100, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Tabla de errores");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, 221, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ANALIZADOR LEXICO Y SINTACTICO 1.0");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 260, -1));

        jTextArea_Datos.setColumns(20);
        jTextArea_Datos.setRows(5);
        jScrollPane1.setViewportView(jTextArea_Datos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 114, 230, 210));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ingrese las intrucciones EN EL ANALIZADOR");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 340, 30));

        jTextArea_erroresVar.setEditable(false);
        jTextArea_erroresVar.setColumns(20);
        jTextArea_erroresVar.setForeground(new java.awt.Color(255, 0, 0));
        jTextArea_erroresVar.setLineWrap(true);
        jTextArea_erroresVar.setRows(5);
        jScrollPane6.setViewportView(jTextArea_erroresVar);

        getContentPane().add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 360, 380, 200));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Output-Error");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 330, 161, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/analizador/fondo.jpg"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 560));

        pack();
        setLocationRelativeTo(null);
    }

    // Code for dispatching events from components to event handlers.

    public void actionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == jbutton_Analizar) {
            Analizador.this.jbutton_AnalizarActionPerformed(evt);
        }
        else if (evt.getSource() == jButton_Limpiar) {
            Analizador.this.jButton_LimpiarActionPerformed(evt);
        }
    }// </editor-fold>//GEN-END:initComponents

    List<Variable> varD = new ArrayList<>(); //ARRAY LIST DE VARIABLE
    List<Declaracion> declaraciones = new ArrayList<>();    //ARRAY LIST DE DECLARACIONES

    class Declaracion {          //CLASE DE DECLARACION

        public String tipo;
        public String texto;

        public Declaracion(String tipo, String txt) {   //CONSTRUCTOR DE LA DECLARACION
            this.tipo = tipo;
            this.texto = txt;
        }
    }

    List<Asignacion> Asignaciones = new ArrayList<>(); //ARRAY LIST DE ASIGNACIONES

    class Asignacion {   //CLASE ASIGNACION

        public String nombre;
        public String valor;

        public Asignacion(String nombre, String valor) {  //CONSTRUCTOR ASIGNACION
            this.nombre = nombre;
            this.valor = valor;
        }
    }

    private boolean Declarado(String txt) { // METODO DECLADO
        for (Declaracion d : declaraciones) {
            if (d.texto.equalsIgnoreCase(txt)) { // Devolverá true si las cadenas comparadas son iguales. En caso contrario devolverá false.
                return true;
            }
        }
        return false;
    }
    private void jbutton_AnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutton_AnalizarActionPerformed

        //pedir 
        String instruccion = jTextArea_Datos.getText();
        String lineas[] = instruccion.split("\n");            //Agarra la informaion por cada linea
        int lineatemp = 0;
        for (String linea : lineas) {
            lineatemp++;
            List<Declaracion> currentDec = new ArrayList<>();
            boolean declarando = false;

            String form[] = linea.split("\\s");           //LOS DIVIDE POR STRINGS
            if (Pattern.matches("(\\w*)\\s+((\\w*)(,\\s)*)+;", linea)) { //COMPARA LA EXPRESION CON LA LINEA

                Pattern p = Pattern.compile("\\s((\\w*)(\\s)*)+");
                Matcher m = p.matcher(linea);
                while (m.find()) {
                    String v = m.group().trim(); //TRIM PARA ELIMINAR LOS ESPACIO AL PRINCIPIO Y FINAL

                    if (!isRealVar(v)) { //Llamar el metodo y checar si es diferente a la expresion
                        nError++;
                        tablaDeErrores.add(new Variable("ENOMENCLATURA", v, "" + lineatemp));
                        jTextArea_erroresVar.append("ERROR DE NOMENCLATURA para la variable " + v + "\n");

                    } else if (!variableDeclarada(v)) {
                        varD.add(new Variable("VAR" + v.toUpperCase(), v, "1", form[0]));

                    }
                }
            }
            if (esLlaveFin(linea) && esDo == -1) { //LLAMA AL METODO esLlaveFin
                TriplosFile.get(esLlaveFin).tabla.remove(3);
                TriplosFile.get(esLlaveFin).tabla.add(new String[]{
                    "FALSE", (contadorLineaT + 1) + "", "TR1"
                });
                contadorLineaT++;
                esLlaveFin = -1;
            }

            if (esIf(linea)) { //LLAMA AL METODO esIf
                Pattern p = Pattern.compile("[(]\\w+[=<>!]+\\w+[)]"); //IF(  )
                Matcher m = p.matcher(linea);
                while (m.find()) {                    //SI COINCIDE CON LA EXPRESION
                    String limp = m.group().replace("(", "").replace(")", ""); //CAMBIAR LOS PARENTESIS
                    System.out.println(limp.replaceAll("[a-zA-Z0-9]+", "")); //limpio 

                    String comparador = limp.replaceAll("[a-zA-Z0-9]+", "");

                    List<String[]> tripTemp = new ArrayList<>();
                    tripTemp.add(new String[]{
                        "T1", limp.split(comparador)[0], "="  
                    });
                    tripTemp.add(new String[]{
                        "T1", limp.split(comparador)[1], comparador
                    });
                    esLlaveFinIR = contadorLineaT + 2;
                    if (esDo == -1) {
                        tripTemp.add(new String[]{
                            "TRUE", (contadorLineaT + 4) + "", "TR1"
                        });
                        tripTemp.add(new String[]{
                            "FALSE", (esLlaveFin + 1) + "", "TR1"
                        });
                        esLlaveFin = contadorTriplos;
                    }
                    contadorTriplos++;
                    TriplosFile.add(new Triplo(contadorTriplos, contadorLineaT, tripTemp));
                    contadorLineaT += 4;
                    Pattern sP = Pattern.compile("\\w+|>=|<=|<>|!=|==");
                    Matcher sM = sP.matcher(limp);
                    while (sM.find()) {
                        String espacio = sM.group();
                        String rname = obtenerNameRel(espacio); //== EQ
                        if (!rname.equalsIgnoreCase("nosabo")) {
                            if (existe(espacio.replace(",", "").replace(";", "").trim())) //quitar duplicados
                            {
                                continue;                 
                            }
                            Variable data = new Variable(
                                    rname,
                                    espacio.replace(",", "").replace(";", "").trim(),
                                    " "
                            );
                            tablaDeVariables.add(data);
                        }
                        if (isRealVar(espacio.replace(",", "").replace(";", "").trim())) {
                            if (existe(espacio.replace(",", "").replace(";", "").trim())) //quitar duplicados
                            {
                                continue;
                            }
                            Variable data = new Variable(
                                    "VAR" + espacio.replace(",", "").replace(";", "").trim().toUpperCase(),
                                    espacio.replace(",", "").replace(";", "").trim(),
                                    getVarType(espacio.replace(",", "").replace(";", "").trim()).toUpperCase()
                            );
                            tablaDeVariables.add(data);
                        }
                    }
                }
            }
            if (Pattern.matches("(\\w)+\\s*=\\s*[\\w./-]+(\\s*([+/*-]\\s*[\\w./*+-]+)*)*\\s*;", linea))//if(Pattern.matches("(\\w)+\\s*=\\s*(\\w)+\\s*([+/-]\\s*(\\w)+)*\\s*;", linea)) //((\\w*)\\s*=\\s*(\\w*);\\s*)
            {

                Pattern ps = Pattern.compile("(\\w)*\\s=");
                Matcher ms = ps.matcher(linea);
                String Prefijo = "";
                while (ms.find()) {
                    String v = ms.group().replace("=", "").trim(); 
                    Prefijo += ("= " + v) + " ";
                    Prefijo += (OrdenarAritmeticos(linea.replace(v + " =", "").replaceAll("\\s", "").replace(",", "").replace(";", "").replaceAll("[a-zA-Z]+", "").trim()));
                    Prefijo += (linea.replace(v + " =", "").replaceAll("\\s", "").replace(",", "").replace(";", "").replaceAll("[*/+-]+", " ").trim());
                    System.out.println(Prefijo);
                }

                TablaTriplos = new ArrayList<>();
                generarTriplo(Prefijo);
                contadorTriplos++;
                TriplosFile.add(new Triplo(contadorTriplos, contadorLineaT, TablaTriplos));

                int contTri = 0;
                for (String data[] : TablaTriplos) {
                    contTri++;
                    contadorLineaT++;
                }
                int error = 0;
                String vartemp = linea.substring(0, linea.indexOf("=") - 1).replace(";", ";").trim(); 

                linea = linea.substring(linea.indexOf("=") + 1).replace(";", ";").trim();

                Matcher m = Pattern.compile("[+*/-]*\\s*(\\w)+").matcher(linea);
                while (m.find()) {
                    String as = m.group().trim().replace(" ", "").replace("+", "").replace("-", "").replace("/", "").replace("*", "");

                    if (variableDeclarada(as)) {
                        if (!getVarType(as).equalsIgnoreCase(getVarType(vartemp))) {
                            error++;
                        }

                    } else {
                        jTextArea_erroresVar.append("(undefined variable) La variable " + as + " no esta declarada!\n ");
                        tablaDeErrores.add(new Variable("ERROR decla", as, "" + lineatemp));
                        if (!existe(as.replace(",", "").replace(";", "").trim())) {
                            tablaDeVariables.add(new Variable("vAR" + as, as, "" + lineatemp, checkType(as)));
                        }
                    }

                }

                if (error > 0) { 
                    jTextArea_erroresVar.append("ERROR DE TIPOS en la operacion para la variable " + vartemp + "\n");
                    String token_T = "ERROR" + nError;
                    nError++;
                    tablaDeErrores.add(new Variable("ERRORDAT", vartemp, "" + lineatemp));
                }
            }

        }

        for (Variable d : varD) { 

        }
        //agregamos ,;-+ 
        VReservada(instruccion);
        //dividir por tokens en la instruccion
        String[] token = instruccion.split("\\s");
        int contl = 1, conRealL = 1;

        //para checar cada token
        for (String tokens : token) {
            if (tokens.length() < 1) {
                continue;
            }
            tokens = tokens.replace(";", "").replace(",", "");

            String token_T = "";
            if (esArit(tokens.replace(",", "").replace(";", "").trim())) {
                if (existe(tokens)) {
                    continue;
                }
                token_T = "OPAR" + nOpe;
                nOpe++;
                tablaDeVariables.add(new Variable(token_T, tokens, "" + conRealL, checkType(tokens)));
            } else if (esNumero(tokens.replace(",", "").replace(";", "").trim())) {
                if (existe(tokens)) {
                    continue;
                }
                token_T = "Num" + nNum;
                nNum++;
                tablaDeVariables.add(new Variable(token_T, tokens, "" + conRealL, checkType(tokens)));
            } else if (esReservada(tokens.trim())) {
                if (existe(tokens)) {
                    continue;
                }
                token_T = "PReservada" + tokens.toUpperCase();
                tablaDeVariables.add(new Variable(token_T, tokens, "" + conRealL, " "));
            } else if (esIf(tokens.replace(",", "").trim())) {
                if (existe(tokens.replace(",", "").replace(";", "").trim())) //quitar duplicados
                {
                    continue;
                }
                Variable data = new Variable("if", "IF".trim().toUpperCase(), "");
                tablaDeVariables.add(data);
            } else if (esVariable(tokens.replace(",", "").replace(";", "").trim())) {
                if (existe(tokens)) {
                    continue;
                }
                token_T = !esReservada(tokens) ? "VAR" + tokens.replace(",", "").replace(";", "").trim().toUpperCase() : "PReservada";
                tablaDeVariables.add(new Variable(token_T.replace(",", "").replace(";", "").trim(), tokens, "" + conRealL, getVarType(tokens.replace(",", "").replace(";", "").trim()).toUpperCase()));
            } else //verif
            {
                token_T = "ERROR" + nError;
                nError++;

                if (!existeError(tokens.replace(",", "").replace(";", "").trim())) {
                    tablaDeErrores.add(new Variable(token_T, tokens.replace(",", "").replace(";", "").trim(), "" + conRealL));
                }
                if (!existe(tokens.replace(",", "").replace(";", "").trim())) {
                    tablaDeVariables.add(new Variable(token_T, tokens.replace(",", "").replace(";", "").trim(), "" + conRealL, checkType(tokens)));
                }
            }
            if (contl % 3 == 0) {
                conRealL++;
            }
            contl++;
        }

        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Lexema");
        dtm.addColumn("Token");
        dtm.addColumn("Tipo");
        tablaDeVariables.forEach((v) -> {
            dtm.addRow(new Object[]{
                v.desc, v.token, v.tipo
            });
        });
        jtable_TablaDeVariables.setModel(dtm);

        DefaultTableModel dtm2 = new DefaultTableModel();
        dtm2.addColumn("Lexema");
        dtm2.addColumn("Token");
        dtm2.addColumn("Linea");
        tablaDeErrores.forEach((v) -> {
            dtm2.addRow(new Object[]{
                v.desc, v.token, v.linea
            });
        });
        jtable_TablaDeErrores.setModel(dtm2);

        //crear tokens archivo
        crearArchivo(jTextArea_Datos.getText());
        crearArchivoTriplo();


    }//GEN-LAST:event_jbutton_AnalizarActionPerformed

    public boolean esDo(String txt) {
        return Pattern.matches("\\bdo\\b\\s*[{]*", txt);
    }

    public boolean esLlaveFin(String txt) {  //PARA SABER SI ES EL FINAL DE LA LLAVE }
        return Pattern.matches("\\s*[}]\\s*", txt);
    }

    public boolean esIf(String txt) {  //PARA SABER SI ES UN "IF"
        return Pattern.matches("\\bif\\b[(]\\w+\\s*[=<>!]+\\s*\\w+[)]\\s*[{]*", txt);
    }

    public boolean esElse(String txt) { //PARA SABER SI ES UN "ELSE"
        return Pattern.matches("[}]*\\s*\\belse\\b\\s*[{]*", txt);
    }

    public void VReservada(String linea) { ///COMPROBAR SI ES VRESRVADO - + / *
        Pattern p = Pattern.compile("[-/*+=;,.()]");
        Matcher m = p.matcher(linea);
        while (m.find()) {
            String v = m.group().trim();
            if (!existe(v)) {
                tablaDeVariables.add(new Variable("PReservada" + v.toUpperCase(), v, "1", checkType(v)));
                System.out.println(v);
            }
        }
    }
    private void jButton_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LimpiarActionPerformed
        // TODO add your handling code here:
        nError = 1;
        nOpe = 1;
        nNum = 1;
        jTextArea_Datos.setText("");
        jTextArea_erroresVar.setText("");

        tablaDeVariables = new ArrayList<>();
        tablaDeErrores = new ArrayList<>();
        varD.clear();
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Lexema");
        dtm.addColumn("Token");
        dtm.addColumn("Tipo");
        jtable_TablaDeVariables.setModel(dtm);

        DefaultTableModel dtm2 = new DefaultTableModel();
        dtm2.addColumn("Lexema");
        dtm2.addColumn("Token");
        dtm2.addColumn("Linea");
        jtable_TablaDeErrores.setModel(dtm2);

    }//GEN-LAST:event_jButton_LimpiarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Analizador().setVisible(true);
            }
        });
    }

    //CHECADORES PARA VER SI ES UN NUMERO O ES SIMBOLO ARITMETICO
    public boolean esArit(String txt) {
        return Pattern.matches("[+-/*]", txt);
    }

    public boolean isRealVar(String txt) {
        
        return Pattern.matches("(?![iI]nt|[fF]loat|[sS]tring|[dD]ouble)\\b[a-zA-Z][-_*/a-zA-Z0-9]*\\b", txt);//ignoramos las reservadas
    }

    public boolean esVariable(String txt) {
        for (Variable v : varD) {
            if (v.desc.contains(txt)) {
                return true;
            }
        }
        return false;
    }

    public String getVarType(String txt) {
        for (Variable v : varD) {
            if (v.desc.contains(txt)) {
                return v.tipo;
            }
        }
        return "";
    }

    public boolean esNumero(String txt) { 
        if (esInteger(txt) || esFloat(txt) || esInteger(txt)) {
            return true;
        }
        return false;
    }

    public boolean esInteger(String txt) {
        return Pattern.matches("[-+]?[0-9]+", txt);
    }

    public boolean esFloat(String txt) {
        return Pattern.matches("[-+]?[0-9]+[.][0-9]+([e][-][0-9]+)?", txt);
    }

    public boolean esDouble(String txt) {
        return Pattern.matches("[-+]?[0-9]+[.][0-9]+([e][-][0-9]+)?", txt);
    }

    public String checkType(String txt) {
        if (esArit(txt)) {
            return "OPAritmerico";
        } else if (esNumero(txt)) {
            return "NUM";
        } else if (esVariable(txt)) {
            return "VAR";
        } else if (isRealVar(txt)) {
            return txt.toUpperCase();
        } else if (Pattern.matches("[;=]", txt)) {
            return "OASIG";
        } else {
            return "";
        }
    }

    public boolean esReservada(String txt) {
        for (String r : PReservada) {
            if (r.equalsIgnoreCase(txt)) {
                return true;
            }
        }
        return false;
    }

    public String nombrar(String txt) {
        for (Variable v : tablaDeVariables) {
            if (v.desc.equals(txt)) {
                return v.token;
            }
        }
        for (Variable v : varD) {
            if (v.desc.equals(txt)) {
                return v.token;
            }
        }
        return "";
    }

///SI EXISTE LA VARIABLE EN AL TABLA
    public boolean variableDeclarada(String txt) {
        for (Variable v : varD) {
            if (v.desc.equals(txt)) {
                return true;
            }
        }
        return false;
    }

    public boolean existeError(String txt) {
        for (Variable v : tablaDeErrores) {
            if (v.desc.equals(txt)) {
                return true;
            }
        }
        return false;
    }

    public boolean existe(String txt) {
        for (Variable v : tablaDeVariables) {
            if (v.desc.equals(txt)) {
                return true;
            }
        }
        return false;
    }

///CLASE DE VARIABLES
    public static class Variable {

        public String token;
        public String tipo;
        public String valor;
        public String desc;
        public String linea;

        public Variable() {

        }

        public Variable(String token, String descripcion, String linea) {
            this.token = token;
            this.desc = descripcion;
            this.linea = linea;
        }

        public Variable(String token, String descripcion, String linea, String tipo) {
            this.token = token;
            this.desc = descripcion;
            this.linea = linea;
            this.tipo = tipo;
        }

        public Variable(String token, String descripcion, String linea, String tipo, String valor) {
            this.token = token;
            this.desc = descripcion;
            this.linea = linea;
            this.tipo = tipo;
            this.valor = valor;
        }

    }

    public String OrdenarAritmeticos(String linea) {
        int multi = 0, div = 0, sumas = 0, restas = 0;
        Pattern p = Pattern.compile("[*/+-]");
        Matcher m = p.matcher(linea);

        String formateo = "";
        List<String> asd = new ArrayList<>();
        while (m.find()) {
            String oper = m.group().trim();
            if (oper.equalsIgnoreCase("*")) { // Devolverá true si las cadenas comparadas son iguales. En caso contrario devolverá false.
                multi++;
            } else if (oper.equalsIgnoreCase("/")) {
                div++;
            } else if (oper.equalsIgnoreCase("+")) {
                sumas++;
            } else if (oper.equalsIgnoreCase("-")) {
                restas++;
            }
            asd.add(oper + " ");
        }
        for (int i = asd.size() - 1; i >= 0; i--) {
            formateo += asd.get(i);
        }
        return formateo;
    }
//Archivo txt token

    public void crearArchivo(String txt) {
        try {
            String ruta = "Tokens.txt";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            String lineas[] = txt.trim().split("\n");
            PrintWriter writer = new PrintWriter(ruta, "UTF-8");

            writer.println(archivo(lineas));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String archivo(String line[]) {
        String a = "", temp = "";

        for (int k = 0; k < line.length; k++) {
            line[k] = line[k].replace(",", " ,").replace(";", " ;");
            String divi[] = line[k].split("[\\s]");

            for (int p = 0; p < divi.length; p++) {

                for (Variable objeto : tablaDeVariables) {
                    if (objeto.desc.equals(divi[p].trim())) {
                        divi[p] = objeto.token;
                        temp += divi[p] + " ";
                    }
                }
                if (esIf(divi[p])) {

                    Pattern pat = Pattern.compile("[(]\\w+[=<>!]+\\w+[)]");
                    Matcher mat = pat.matcher(divi[p]);
                    while (mat.find()) {
                        String limp = mat.group().replace("(", "").replace(")", "");
                        String comparador = limp.replaceAll("[a-zA-Z0-9]+", "");
                        // System.out.println("EW: "+comparador);
                        Pattern sP = Pattern.compile("\\w+|>=|<=|<>|!=|==");
                        Matcher sM = sP.matcher(limp);
                        while (sM.find()) { //obtener vars
                            String espacio = sM.group();
                            // System.out.println("EW: "+espacio);
                            String rname = obtenerNameRel(espacio);
                            if (!rname.equalsIgnoreCase("NOSABO")) {
                                divi[p] = (divi[p].replace(espacio, " " + rname + " "));
                                System.out.println(espacio + ":COMP:" + rname);
                            }
                            if (Pattern.matches("[0-9]+", espacio)) {
                                divi[p] = divi[p].replace(espacio, "NUM" + espacio);
                                System.out.println(espacio + ":COMP:" + divi[p]);
                            }
                            for (Variable objeto : tablaDeVariables) {
                                if (objeto.desc.equals(espacio)) {
                                    divi[p] = (divi[p].replace(espacio, objeto.token));
                                }
                            }
                            ////
                        }
                    }

                    divi[p] = divi[p].replace("if(", "IF PReservada( ");
                    divi[p] = divi[p].replace(")", " PReservada)");
                    divi[p] = divi[p].replace("}", " PReservada}");
                    divi[p] = divi[p].replace("{", " PReservada{");
                    System.out.println(divi[p]);
                    temp += divi[p] + " ";
                }
            }
            temp += "\n";
            a = temp + "\n";
        }
        return a;
    }

    public void crearArchivoTriplo() {
        try {
            String ruta = "Triplos.txt";
            String ruta2 = "Ensamblador.txt";
            File file = new File(ruta);
            File file2 = new File(ruta2);

            if (!file.exists()) {
                file.createNewFile();
            }
            if (!file2.exists()) {
                file.createNewFile();
            }

            PrintWriter writer = new PrintWriter(ruta, "UTF-8");
            PrintWriter writer2 = new PrintWriter(ruta2, "UTF-8");

            //writer.println("Operador         | Dato Objeto       | Dato Fuente     "); 
            writer.printf("%-5s %-11s | %-11s |%-11s\n", "", "Operador", "Dato Objeto", "Dato Fuente");

            //  System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%");
            int contNum = 1; // ID DEL TRIPLO
            int contFile = 1; //LINEA REAL

            List<String> ensambladorTemp = new ArrayList<>();
            List<Integer> excluidos = new ArrayList<>();
            for (Triplo triplo : TriplosFile) {   //System.out.println("[Data]"+triplo.ID+":"+triplo.Linea);
                for (String data[] : triplo.tabla) {
                    writer.printf("%-5d %-11s | %-11s | %-11s\n", contFile, data[2], data[0], data[1]);
                    String ensam = data[2] + " " + data[0];
                    if (Pattern.matches("(>=|<=|<|>|<>|!=|==)\\s+T1", ensam)) {
                        excluidos.add(Integer.parseInt(triplo.tabla.get(2)[1])); //true
                        for (Triplo trip : getTriplosByRange(Integer.parseInt(triplo.tabla.get(2)[1]), Integer.parseInt(triplo.tabla.get(3)[1]))) {
                            excluidos.add(trip.Linea);
                        }
                    }
                    contFile++;
                }
                contNum++;
            }
            int ultimaEtiqueta = -1;
            for (Triplo triplo : TriplosFile) {
                if (excluidos.contains(triplo.Linea)) {
                    continue;
                }
                String ultEn = "AX";
                for (String data[] : triplo.tabla) {
                    String ensam = data[2] + " " + data[0];
                    if (ensam.equalsIgnoreCase("= T1")) {
                        ultimaEtiqueta++;
                        writer2.printf("ETIQUETA%d:\n\tMOV AX, %s\n", triplo.Linea, data[1]);
                        
                    } else if (Pattern.matches("(>=|<=|<|>|<>|!=|==)\\s+T1", ensam)) {
                        writer2.printf("\tCMP AX, %s\n", data[1]);

                        String etiquetaTrue = "ETIQUETA" + triplo.tabla.get(2)[1];
                        String etiquetaFalse = "ETIQUETA" + triplo.tabla.get(3)[1];
                        writer2.printf("\t%s %s\n", obtenerNameRel(ensam.split(" ")[0]), etiquetaTrue); //   
                        writer2.printf("\tJMP %s\n", etiquetaFalse); //   

                        writer2.printf("%s: \n", etiquetaTrue); //   

                        for (Triplo trip : getTriplosByRange(Integer.parseInt(triplo.tabla.get(2)[1]), Integer.parseInt(triplo.tabla.get(3)[1]))) {
                            for (String dataTr[] : trip.tabla) {
                                String ensamTr = dataTr[2] + " " + dataTr[0];
                                if (ensamTr.equalsIgnoreCase("= T1")) {
                                    writer2.printf("\tMOV AX, %s\n", dataTr[1]);
                                } else if (Pattern.matches("([+]|[*]|/|-|%)\\s+T1", ensamTr)) {
                                    String en = obtenerNameArit(ensamTr.split(" ")[0]);
                                    if (en.equalsIgnoreCase("MUL") || en.equalsIgnoreCase("DIV")) {
                                        writer2.printf("\tMOV BL, %s\n", dataTr[1]); //
                                        writer2.printf("\t%s BL\n", en, dataTr[1]); //  
                                        ultEn = "AX";
                                    } else {
                                        writer2.printf("\t%s AX, %s\n", obtenerNameArit(ensamTr.split(" ")[0]), dataTr[1]); //                               
                                    }
                                } else if (Pattern.matches("=\\s+\\w+", ensamTr)) {
                                    writer2.printf("\tMOV %s, AX\n", ensamTr.split(" ")[1]); //                               
                                }
                            }
                        }
                        ultimaEtiqueta = Integer.parseInt(triplo.tabla.get(3)[1]);

                    } else if (Pattern.matches("([+]|[*]|/|-|%)\\s+T1", ensam)) {
                        String en = obtenerNameArit(ensam.split(" ")[0]);
                        if (en.equalsIgnoreCase("MUL") || en.equalsIgnoreCase("DIV")) {
                            writer2.printf("\tMOV BL, %s\n", data[1]); //
                            writer2.printf("\t%s BL\n", en, data[1]); //  
                            ultEn = "AX";
                        } else {
                            writer2.printf("\t%s AX, %s\n", en, data[1]); //                               
                        }
                    } else if (Pattern.matches("=\\s+\\w+", ensam)) {
                        writer2.printf("\tMOV %s, %s\n", ensam.split(" ")[1], ultEn); //                               
                    }
                    contFile++;
                }
                contNum++;
            }
            writer2.printf("ETIQUETA%d:\n", ultimaEtiqueta); //       
            writer.close();
            writer2.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /////////CREA EL TRIPLOS

    public void generarTriplo(String prefijo) {
        for (String v : prefijo.split("\\s")) {
            if (prefijo.split("\\s").length == 3) {
                String finalStr[] = prefijo.split("\\s");
                String data[] = {
                    finalStr[1], "T1", "="
                };
                TablaTriplos.add(data);
                break;
            }
            String iteracion = iterarTriplo(prefijo);
            prefijo = prefijo.replace(iteracion.trim(), "T1");
        }
    }

    public String iterarTriplo(String entrada) {
        String salida = "";
        String separador[] = entrada.split("\\s");
        int aux = 0;
        for (int i = separador.length - 1; i >= 0; i--) {
            if (esArit(separador[i])) {
                aux = entrada.lastIndexOf(separador[i]);
                break;
            }
        }
        String siguiente = (entrada.substring(aux, entrada.length()));

        String separador2[] = siguiente.split("\\s");
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                salida += (separador2[i]) + " ";
                continue;
            }
            if (i == 1 && TablaTriplos.size() > 0) {
                salida += (separador2[i]) + " ";
                continue;
            }

            String data[] = {
                "T1", separador2[i], (i == 1) ? "=" : separador2[0]
            };
            if (!existePrimTriplo(data)) {
                TablaTriplos.add(data);
            }
            salida += (separador2[i]) + " ";
        }
        return salida;
    }

    public boolean existePrimTriplo(String data[]) {
        for (String v[] : TablaTriplos) {
            if (v.equals(data)) {
                //System.out.println("EXISTE"+data[0]);
                return true;
            }
        }
        return false;
    }

    ////Esanmblador
    public String obtenerNameArit(String arit) {
        switch (arit) {
            case "+":
                return "ADD";
            case "-":
                return "SUB";
            case "/":
                return "DIV";
            case "*":
                return "MUL";
            case "%":
                return "DIV";
        }
        return "NOSABO";
    }

    public String obtenerNameRel(String arit) { //PARA SABER LAS COMPARACIONES Y REMPLAZARLO PARA EL EMSAMBLADOR
        switch (arit) {
            case "==":
                return "EQ";
            case ">":
                return "GT";
            case ">=":
                return "GE";
            case "<=":
                return "LE";
            case "<":
                return "LT";
            case "<>":
                return "NE";
            case "=":
                return "EQ";
            case "!=":
                return "LE";
        }
        return "NOSABO";
    }

    public int getTriploIdByLine(int linea) {
        for (Triplo triplo : TriplosFile) {
            if (linea == triplo.Linea) {
                return triplo.ID;
            }
        }
        return -1;
    }

    public Triplo getTriploByLine(int line) {
        for (Triplo triplo : TriplosFile) {
            if (line == triplo.Linea) {
                return triplo;
            }
        }
        return null;
    }

    public List<Triplo> getTriplosByRange(int desde, int hasta) {
        List<Triplo> temp = new ArrayList<>();
        for (Triplo triplo : TriplosFile) {
            if (triplo.Linea >= desde && triplo.Linea < hasta) {
                temp.add(triplo);
            }
        }
        return temp;
    }

    public Triplo getTriploByID(int id) {
        for (Triplo triplo : TriplosFile) {
            if (id == triplo.ID) {
                return triplo;
            }
        }
        return null;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Limpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextArea jTextArea_Datos;
    private javax.swing.JTextArea jTextArea_erroresVar;
    private javax.swing.JButton jbutton_Analizar;
    private static javax.swing.JTable jtable_TablaDeErrores;
    private static javax.swing.JTable jtable_TablaDeVariables;
    // End of variables declaration//GEN-END:variables
}
