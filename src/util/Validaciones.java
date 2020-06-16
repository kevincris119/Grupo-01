package util;

public class Validaciones {
	public static final String TEXTO = "[a-zA-ZáéíóúñüÁÉÍÓÚÑÜ\\s]{2,20}";
	public static final String PASSWORD = "[a-zA-ZáéíóúñüÁÉÍÓÚÑÜ\\d+]{6,15}";
	public static final String DNI = "[0-9]{8}";
	public static final String NUM_HIJOS = "[0-9]|[1][0]";
	public static final String SUELDO = "(\\d+)|(\\d+[.]\\d{1,2})";
	public static final String PREMIO = "(\\d+)|(\\d+[.]\\d{1})";
	public static final String PLACA = "[A-Z]{2}\\d{4}";
	public static final String STOCK = "\\d+";
	public static final String FECHA = "((19|20)\\d\\d)[-/](0?[1-9]|1[012])[-/](0?[1-9]|[12][0-9]|3[01])";
	public static final String CORREO = "[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})"; 
	public static final String SEDE = "\\d{1,2}";
	public static final String ANNO = "\\d{4}";
	public static final String RUC = "\\d{11}";
	public static final String LOGIN="(USUARIO)(\\d+)";
}
