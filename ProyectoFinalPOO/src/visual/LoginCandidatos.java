package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Bolsa;
import logica.Obrero;
import logica.Persona;
import logica.TecnicoSuperior;
import logica.Universitario;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginCandidatos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCorreoLogin;
	private JPasswordField txtPasswLogin;
	private JTabbedPane jtpSecciones;
	private JRadioButton rdbtnUniversitario;
	private JRadioButton rdbtnTecSuperior;
	private JRadioButton rdbtnObrero;
	private JTabbedPane jtpFormacion;
	private JRadioButton rdbtnLicenciaSi;
	private JRadioButton rdbtnLicenciaNo;
	private JComboBox cbxProvinciaCand;
	private JTextField txtNombreCand;
	private JTextField txtApellidoCand;
	private JTextField txtCedulaCand;
	private JTextField txtDireccionCand;
	private JTextField txtTelefonoCand;
	private JTextField txtCorreoCand;
	private JComboBox cbxTipoEmpleo;
	private JComboBox cbxModalidad;
	private JComboBox cbxCarrera;
	private JTextField txtTecnicoCand;
	private JSpinner spnAniosExp;
	private JPasswordField passwCandidato;
	private JPasswordField passwCandidatoVerificar;
	private JSpinner spnFechaNacCand;
	private JCheckBox chckbxVentas;
	private JCheckBox chckbxMecanica;
	private JCheckBox chckbxOfimtica;
	private JCheckBox chckbxElectricidad;
	private JCheckBox chckbxSeguridad;
	private JCheckBox chckbxMantenimiento;
	private JCheckBox chckbxConduccin;
	private JCheckBox chckbxLimpieza;
	private JRadioButton rdbtnSexoM;
	private JRadioButton rdbtnSexoF;
	private JComboBox cbxDispMovilidad;
	private JComboBox cbxHorario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoginCandidatos dialog = new LoginCandidatos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoginCandidatos() {
		setTitle("Laborea - Inicia sesi\u00F3n o Registrate!");
		setBounds(100, 100, 796, 894);

		//****SUPERUSUARIO, ELIMINAR DESPUES ****
		Universitario superusu = new Universitario("SUP-1", "Omar", "Morales", "M", "1234", new Date(), "abc", "8091231234", "super@gmail.com", "Santiago", "Su casa", "Todos", true, true, "Tiempo Completo", "Presencial", false, null, "Ing. en Sistemas");
		Bolsa.getInstancia().insertarPersona(superusu);
		//***************************************
		
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		jtpSecciones = new JTabbedPane(JTabbedPane.TOP);
		jtpSecciones.setEnabled(false);
		jtpSecciones.setBackground(Color.WHITE);
		jtpSecciones.setBounds(-14, -28, 805, 903);
		contentPanel.add(jtpSecciones);

		JPanel pnlLoginGeneral = new JPanel();
		pnlLoginGeneral.setBackground(Color.WHITE);
		jtpSecciones.addTab("New tab", null, pnlLoginGeneral, null);
		pnlLoginGeneral.setLayout(null);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(LoginCandidatos.class.getResource("/img/Laborea.png")));
		label_1.setBounds(-26, 0, 207, 114);
		pnlLoginGeneral.add(label_1);

		JLabel lblNewLabel_1 = new JLabel("Bienvenido/a.");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(10, 104, 780, 84);
		pnlLoginGeneral.add(lblNewLabel_1);

		JLabel lblIngresaParaComenzar = new JLabel("Ingresa para comenzar");
		lblIngresaParaComenzar.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngresaParaComenzar.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblIngresaParaComenzar.setBounds(10, 165, 780, 59);
		pnlLoginGeneral.add(lblIngresaParaComenzar);

		txtCorreoLogin = new JTextField();
		txtCorreoLogin.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtCorreoLogin.setBackground(Color.WHITE);
		txtCorreoLogin.setBounds(95, 312, 613, 59);
		pnlLoginGeneral.add(txtCorreoLogin);
		txtCorreoLogin.setColumns(10);

		txtPasswLogin = new JPasswordField();
		txtPasswLogin.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtPasswLogin.setBackground(Color.WHITE);
		txtPasswLogin.setBounds(95, 441, 613, 59);
		pnlLoginGeneral.add(txtPasswLogin);

		JLabel lblIngreseSuContrasea = new JLabel("");
		lblIngreseSuContrasea.setHorizontalTextPosition(SwingConstants.LEFT);
		lblIngreseSuContrasea.setIcon(new ImageIcon(LoginCandidatos.class.getResource("/img/key-chain.png")));
		lblIngreseSuContrasea.setHorizontalAlignment(SwingConstants.LEFT);
		lblIngreseSuContrasea.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblIngreseSuContrasea.setBounds(93, 390, 77, 59);
		pnlLoginGeneral.add(lblIngreseSuContrasea);

		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(LoginCandidatos.class.getResource("/img/letter.png")));
		label_2.setHorizontalTextPosition(SwingConstants.LEFT);
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		label_2.setBounds(93, 258, 77, 59);
		pnlLoginGeneral.add(label_2);

		JLabel lblIngreseSuCorreo = new JLabel("Correo electr\u00F3nico");
		lblIngreseSuCorreo.setHorizontalTextPosition(SwingConstants.LEFT);
		lblIngreseSuCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		lblIngreseSuCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblIngreseSuCorreo.setBounds(136, 258, 371, 59);
		pnlLoginGeneral.add(lblIngreseSuCorreo);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setHorizontalTextPosition(SwingConstants.LEFT);
		lblContrasea.setHorizontalAlignment(SwingConstants.LEFT);
		lblContrasea.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblContrasea.setBounds(136, 390, 371, 59);
		pnlLoginGeneral.add(lblContrasea);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(95, 220, 613, 40);
		pnlLoginGeneral.add(separator);

		JPanel btnLoginGeneral = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				// Color de fondo
				g2.setColor(getBackground());

				// Dibuja un rectángulo redondeado (x, y, width, height, arcWidth, arcHeight)
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 80, 80);
			}
		};
		btnLoginGeneral.setOpaque(false);

		btnLoginGeneral.setBackground(new Color(46, 204, 113));
		btnLoginGeneral.setBounds(95, 540, 613, 84);
		pnlLoginGeneral.add(btnLoginGeneral);
		btnLoginGeneral.setLayout(null);

		JLabel btnIniciarSesion = new JLabel("Iniciar Sesi\u00F3n");
		btnIniciarSesion.setToolTipText("");
		btnIniciarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//iniciar sesion
				String email = txtCorreoLogin.getText();
				//obtener valor de passwordfield
				char[] passwdChars = txtPasswLogin.getPassword();
				String passwd = new String(passwdChars);
				//validar login, campos de password y email coinciden con uno de los registros 
				if(Bolsa.getInstancia().validarLoginPersona(email, passwd)) {
					JOptionPane.showMessageDialog(null, "¡Bienvenido/a a Laborea!", "Informacion", JOptionPane.INFORMATION_MESSAGE);
					MenuCandidatos menuCand = new MenuCandidatos(Bolsa.getInstancia().obtLoginPersona(email, passwd));
					menuCand.setVisible(true);
					dispose(); //cerrar esta ventana
				} else {
					JOptionPane.showMessageDialog(null, "Error: no se encuentra usuario registrado con esta informacion!", "Alerta", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnIniciarSesion.setForeground(Color.WHITE);
		btnIniciarSesion.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnIniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		btnIniciarSesion.setBounds(10, 11, 593, 62);
		btnLoginGeneral.add(btnIniciarSesion);

		JPanel btnRegistroGeneral = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				// Color de fondo
				g2.setColor(getBackground());

				// Dibuja un rectángulo redondeado (x, y, width, height, arcWidth, arcHeight)
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 80, 80);
			}
		};
		btnRegistroGeneral.setLayout(null);
		btnRegistroGeneral.setOpaque(false);
		btnRegistroGeneral.setBackground(new Color(41, 128, 185));
		btnRegistroGeneral.setBounds(95, 709, 613, 84);
		pnlLoginGeneral.add(btnRegistroGeneral);

		JLabel lblRegstrate = new JLabel("Reg\u00EDstrate");
		lblRegstrate.setToolTipText("");
		lblRegstrate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//si es el registro de candidato
				jtpSecciones.setSelectedIndex(1);
			}
		});
		lblRegstrate.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegstrate.setForeground(Color.WHITE);
		lblRegstrate.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblRegstrate.setBounds(10, 11, 593, 62);
		btnRegistroGeneral.add(lblRegstrate);

		JLabel lblnoTienesUna = new JLabel("\u00BFNo tienes una cuenta?");
		lblnoTienesUna.setHorizontalTextPosition(SwingConstants.LEFT);
		lblnoTienesUna.setHorizontalAlignment(SwingConstants.CENTER);
		lblnoTienesUna.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblnoTienesUna.setBounds(95, 639, 613, 59);
		pnlLoginGeneral.add(lblnoTienesUna);

		JLabel lblVolverAInicio = new JLabel("");
		lblVolverAInicio.setToolTipText("Volver a inicio");
		lblVolverAInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//para volver a inicio
				Inicio ini = new Inicio();
				ini.setVisible(true);
				dispose();
			}
		});
		lblVolverAInicio.setIcon(new ImageIcon(LoginCandidatos.class.getResource("/img/home.png")));
		lblVolverAInicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblVolverAInicio.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblVolverAInicio.setBounds(679, 26, 92, 67);
		pnlLoginGeneral.add(lblVolverAInicio);

		JPanel pnlRegistroCandidato = new JPanel();
		pnlRegistroCandidato.setBackground(Color.WHITE);
		jtpSecciones.addTab("New tab", null, pnlRegistroCandidato, null);
		pnlRegistroCandidato.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(20, 550, 25, 114);
		pnlRegistroCandidato.add(panel_3);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(770, 548, 20, 123);
		pnlRegistroCandidato.add(panel_2);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LoginCandidatos.class.getResource("/img/Laborea.png")));
		lblNewLabel.setBounds(-29, 0, 207, 114);
		pnlRegistroCandidato.add(lblNewLabel);

		JLabel label_3 = new JLabel("Bienvenido/a.");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 21));
		label_3.setBounds(10, 72, 780, 59);
		pnlRegistroCandidato.add(label_3);

		JLabel lblTeAyudaremosA = new JLabel("Reg\u00EDstrate para postular a ofertas seg\u00FAn tu perfil.");
		lblTeAyudaremosA.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeAyudaremosA.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblTeAyudaremosA.setBounds(20, 112, 780, 59);
		pnlRegistroCandidato.add(lblTeAyudaremosA);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(97, 159, 613, 40);
		pnlRegistroCandidato.add(separator_1);

		JLabel lblNombres = new JLabel("Nombre/s");
		lblNombres.setForeground(Color.BLACK);
		lblNombres.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombres.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNombres.setBounds(51, 182, 220, 59);
		pnlRegistroCandidato.add(lblNombres);

		JLabel lblApellidos = new JLabel("Apellido/s");
		lblApellidos.setHorizontalAlignment(SwingConstants.LEFT);
		lblApellidos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblApellidos.setBounds(296, 182, 130, 59);
		pnlRegistroCandidato.add(lblApellidos);

		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setHorizontalAlignment(SwingConstants.LEFT);
		lblSexo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblSexo.setBounds(562, 182, 130, 59);
		pnlRegistroCandidato.add(lblSexo);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaDeNacimiento.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaDeNacimiento.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblFechaDeNacimiento.setBounds(562, 323, 196, 59);
		pnlRegistroCandidato.add(lblFechaDeNacimiento);

		JLabel lblCdula = new JLabel("C\u00E9dula");
		lblCdula.setHorizontalAlignment(SwingConstants.LEFT);
		lblCdula.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCdula.setBounds(51, 252, 220, 59);
		pnlRegistroCandidato.add(lblCdula);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTelefono.setBounds(296, 252, 130, 59);
		pnlRegistroCandidato.add(lblTelefono);

		JLabel lblCorreoElectrnico = new JLabel("Correo electr\u00F3nico");
		lblCorreoElectrnico.setHorizontalAlignment(SwingConstants.LEFT);
		lblCorreoElectrnico.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCorreoElectrnico.setBounds(562, 253, 130, 59);
		pnlRegistroCandidato.add(lblCorreoElectrnico);

		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setHorizontalAlignment(SwingConstants.LEFT);
		lblDireccin.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDireccin.setBounds(296, 323, 130, 59);
		pnlRegistroCandidato.add(lblDireccin);

		JLabel lblPas = new JLabel("Provincia");
		lblPas.setHorizontalAlignment(SwingConstants.LEFT);
		lblPas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblPas.setBounds(51, 323, 220, 59);
		pnlRegistroCandidato.add(lblPas);

		JLabel lblDisponibilidadDeHorario = new JLabel("Disponibilidad de Horario");
		lblDisponibilidadDeHorario.setHorizontalAlignment(SwingConstants.LEFT);
		lblDisponibilidadDeHorario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDisponibilidadDeHorario.setBounds(51, 393, 220, 59);
		pnlRegistroCandidato.add(lblDisponibilidadDeHorario);

		JLabel lblDisponibilidadDeMovilidad = new JLabel("Disponibilidad de movilidad");
		lblDisponibilidadDeMovilidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblDisponibilidadDeMovilidad.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDisponibilidadDeMovilidad.setBounds(296, 393, 243, 59);
		pnlRegistroCandidato.add(lblDisponibilidadDeMovilidad);

		JLabel lblPoseeLicenciaDe = new JLabel("Posee licencia de conducir");
		lblPoseeLicenciaDe.setHorizontalAlignment(SwingConstants.LEFT);
		lblPoseeLicenciaDe.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblPoseeLicenciaDe.setBounds(573, 401, 201, 59);
		pnlRegistroCandidato.add(lblPoseeLicenciaDe);

		JLabel lblTipoEmpleoDeseado = new JLabel("Tipo Empleo deseado");
		lblTipoEmpleoDeseado.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipoEmpleoDeseado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTipoEmpleoDeseado.setBounds(51, 481, 220, 59);
		pnlRegistroCandidato.add(lblTipoEmpleoDeseado);

		JLabel lblModalidad = new JLabel("Modalidad");
		lblModalidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblModalidad.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblModalidad.setBounds(298, 481, 130, 59);
		pnlRegistroCandidato.add(lblModalidad);

		JLabel lblEscribaUnaContrasea = new JLabel("Escriba una contrase\u00F1a");
		lblEscribaUnaContrasea.setHorizontalAlignment(SwingConstants.LEFT);
		lblEscribaUnaContrasea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblEscribaUnaContrasea.setBounds(51, 662, 185, 59);
		pnlRegistroCandidato.add(lblEscribaUnaContrasea);

		JLabel lblVerifique = new JLabel("Verifique la contrase\u00F1a");
		lblVerifique.setHorizontalAlignment(SwingConstants.LEFT);
		lblVerifique.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblVerifique.setBounds(51, 734, 222, 59);
		pnlRegistroCandidato.add(lblVerifique);

		txtNombreCand = new JTextField();
		txtNombreCand.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtNombreCand.setBounds(51, 234, 220, 20);
		pnlRegistroCandidato.add(txtNombreCand);
		txtNombreCand.setColumns(10);

		txtApellidoCand = new JTextField();
		txtApellidoCand.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtApellidoCand.setColumns(10);
		txtApellidoCand.setBounds(296, 234, 212, 20);
		pnlRegistroCandidato.add(txtApellidoCand);

		txtCedulaCand = new JTextField();
		txtCedulaCand.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtCedulaCand.setColumns(10);
		txtCedulaCand.setBounds(51, 304, 220, 20);
		pnlRegistroCandidato.add(txtCedulaCand);

		txtTelefonoCand = new JTextField();
		txtTelefonoCand.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtTelefonoCand.setColumns(10);
		txtTelefonoCand.setBounds(296, 304, 212, 20);
		pnlRegistroCandidato.add(txtTelefonoCand);

		txtCorreoCand = new JTextField();
		txtCorreoCand.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtCorreoCand.setColumns(10);
		txtCorreoCand.setBounds(562, 304, 212, 20);
		pnlRegistroCandidato.add(txtCorreoCand);

		cbxProvinciaCand = new JComboBox();
		cbxProvinciaCand.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cbxProvinciaCand.setBackground(Color.WHITE);
		cbxProvinciaCand.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Azua", "Bahoruco", "Barahona", "Dajab\u00F3n", "Duarte", "El Seibo", "El\u00EDas Pi\u00F1a", "Espaillat", "Hato Mayor", "Hermanas Mirabal", "Independencia", "La Altagracia", "La Romana", "La Vega", "Mar\u00EDa Trinidad S\u00E1nchez", "Monse\u00F1or Nouel", "Monte Cristi", "Monte Plata", "Pedernales", "Peravia", "Puerto Plata", "Saman\u00E1", "S\u00E1nchez Ram\u00EDrez", "San Crist\u00F3bal", "San Jos\u00E9 de Ocoa", "San Juan", "San Pedro de Macor\u00EDs", "Santiago", "Santiago Rodr\u00EDguez", "Santo Domingo", "Valverde"}));
		cbxProvinciaCand.setBounds(51, 374, 220, 20);
		pnlRegistroCandidato.add(cbxProvinciaCand);

		txtDireccionCand = new JTextField();
		txtDireccionCand.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtDireccionCand.setColumns(10);
		txtDireccionCand.setBounds(296, 374, 212, 20);
		pnlRegistroCandidato.add(txtDireccionCand);

		spnFechaNacCand = new JSpinner();
		spnFechaNacCand.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		spnFechaNacCand.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_YEAR));

		JSpinner.DateEditor de_spnFechaNacCand = new JSpinner.DateEditor(spnFechaNacCand, "dd/MM/yyyy");
		spnFechaNacCand.setEditor(de_spnFechaNacCand);

		spnFechaNacCand.setBounds(562, 374, 212, 20);
		pnlRegistroCandidato.add(spnFechaNacCand);

		cbxTipoEmpleo = new JComboBox();
		cbxTipoEmpleo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cbxTipoEmpleo.setBackground(Color.WHITE);
		cbxTipoEmpleo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Tiempo completo", "Tiempo Parcial", "Temporal", "Freelance"}));
		cbxTipoEmpleo.setBounds(51, 531, 220, 20);
		pnlRegistroCandidato.add(cbxTipoEmpleo);

		cbxModalidad = new JComboBox();
		cbxModalidad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cbxModalidad.setBackground(Color.WHITE);
		cbxModalidad.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Presencial", "Remoto", "Mixto"}));
		cbxModalidad.setBounds(296, 531, 138, 20);
		pnlRegistroCandidato.add(cbxModalidad);

		cbxDispMovilidad = new JComboBox();
		cbxDispMovilidad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cbxDispMovilidad.setBackground(Color.WHITE);
		cbxDispMovilidad.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Puedo movilizarme", "No puedo movilizarme"}));
		cbxDispMovilidad.setBounds(296, 450, 212, 20);
		pnlRegistroCandidato.add(cbxDispMovilidad);

		rdbtnLicenciaSi = new JRadioButton("Si");
		rdbtnLicenciaSi.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rdbtnLicenciaSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnLicenciaSi.setSelected(true);
				rdbtnLicenciaNo.setSelected(false);
			}
		});
		rdbtnLicenciaSi.setBackground(Color.WHITE);
		rdbtnLicenciaSi.setBounds(573, 447, 60, 23);
		pnlRegistroCandidato.add(rdbtnLicenciaSi);

		rdbtnLicenciaNo = new JRadioButton("No");
		rdbtnLicenciaNo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rdbtnLicenciaNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnLicenciaSi.setSelected(false);
				rdbtnLicenciaNo.setSelected(true);
			}
		});
		rdbtnLicenciaNo.setBackground(Color.WHITE);
		rdbtnLicenciaNo.setBounds(670, 447, 60, 23);
		pnlRegistroCandidato.add(rdbtnLicenciaNo);

		JLabel lblFormacin = new JLabel("Formaci\u00F3n");
		lblFormacin.setHorizontalAlignment(SwingConstants.LEFT);
		lblFormacin.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblFormacin.setBounds(459, 481, 130, 59);
		pnlRegistroCandidato.add(lblFormacin);

		rdbtnUniversitario = new JRadioButton("Universitario/a");
		rdbtnUniversitario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rdbtnUniversitario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnUniversitario.setSelected(true);
				rdbtnTecSuperior.setSelected(false);
				rdbtnObrero.setSelected(false);
				jtpFormacion.setSelectedIndex(0);
			}
		});
		rdbtnUniversitario.setBackground(Color.WHITE);
		rdbtnUniversitario.setSelected(true);
		rdbtnUniversitario.setBounds(457, 530, 130, 23);
		pnlRegistroCandidato.add(rdbtnUniversitario);

		rdbtnTecSuperior = new JRadioButton("Tec. Superior");
		rdbtnTecSuperior.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rdbtnTecSuperior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnUniversitario.setSelected(false);
				rdbtnTecSuperior.setSelected(true);
				rdbtnObrero.setSelected(false);
				jtpFormacion.setSelectedIndex(1);
			}
		});
		rdbtnTecSuperior.setBackground(Color.WHITE);
		rdbtnTecSuperior.setBounds(584, 530, 120, 23);
		pnlRegistroCandidato.add(rdbtnTecSuperior);

		rdbtnObrero = new JRadioButton("Obrero");
		rdbtnObrero.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rdbtnObrero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnUniversitario.setSelected(false);
				rdbtnTecSuperior.setSelected(false);
				rdbtnObrero.setSelected(true);
				jtpFormacion.setSelectedIndex(2);
			}
		});
		rdbtnObrero.setBackground(Color.WHITE);
		rdbtnObrero.setBounds(705, 530, 69, 23);
		pnlRegistroCandidato.add(rdbtnObrero);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(40, 550, 744, 30);
		pnlRegistroCandidato.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(40, 639, 744, 20);
		pnlRegistroCandidato.add(panel_1);

		jtpFormacion = new JTabbedPane(JTabbedPane.TOP);
		jtpFormacion.setEnabled(false);
		jtpFormacion.setBounds(40, 550, 744, 100);
		pnlRegistroCandidato.add(jtpFormacion);

		JPanel pnlFormUniv = new JPanel();
		pnlFormUniv.setBackground(Color.WHITE);
		jtpFormacion.addTab("New tab", null, pnlFormUniv, null);
		pnlFormUniv.setLayout(null);

		JLabel lblCarrear = new JLabel("Carrera");
		lblCarrear.setHorizontalAlignment(SwingConstants.LEFT);
		lblCarrear.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCarrear.setBounds(10, 0, 130, 44);
		pnlFormUniv.add(lblCarrear);

		cbxCarrera = new JComboBox();
		cbxCarrera.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cbxCarrera.setBackground(Color.WHITE);
		cbxCarrera.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Medicina", "Derecho", "Ingenier\u00EDa Civil", "Arquitectura", "Psicolog\u00EDa", "Administraci\u00F3n de Empresas", "Contabilidad", "Econom\u00EDa", "Comunicaci\u00F3n Social", "Educaci\u00F3n", "Ingenier\u00EDa Electr\u00F3nica", "Ingenier\u00EDa de Sistemas", "Ingenier\u00EDa Industrial", "Odontolog\u00EDa", "Enfermer\u00EDa", "Farmacia", "Biolog\u00EDa", "Qu\u00EDmica", "F\u00EDsica", "Matem\u00E1ticas", "Sociolog\u00EDa", "Antropolog\u00EDa", "Trabajo Social", "Filosof\u00EDa", "Historia", "Lenguas Modernas", "Traducci\u00F3n e Interpretaci\u00F3n", "Dise\u00F1o Gr\u00E1fico", "Dise\u00F1o Industrial", "Publicidad", "Marketing", "Comercio Internacional", "Turismo", "Gastronom\u00EDa", "Inform\u00E1tica", "Estad\u00EDstica", "Derecho Internacional", "Ingenier\u00EDa Mec\u00E1nica", "Ingenier\u00EDa Ambiental"}));
		cbxCarrera.setBounds(10, 39, 212, 20);
		pnlFormUniv.add(cbxCarrera);

		JPanel pnlFormTec = new JPanel();
		pnlFormTec.setBackground(Color.WHITE);
		jtpFormacion.addTab("New tab", null, pnlFormTec, null);
		pnlFormTec.setLayout(null);

		JLabel lblTcnicoCursado = new JLabel("T\u00E9cnico cursado");
		lblTcnicoCursado.setHorizontalAlignment(SwingConstants.LEFT);
		lblTcnicoCursado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTcnicoCursado.setBounds(10, 0, 130, 42);
		pnlFormTec.add(lblTcnicoCursado);

		JLabel lblAosDeExperiencia = new JLabel("A\u00F1os de experiencia");
		lblAosDeExperiencia.setHorizontalAlignment(SwingConstants.LEFT);
		lblAosDeExperiencia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAosDeExperiencia.setBounds(238, 0, 271, 42);
		pnlFormTec.add(lblAosDeExperiencia);

		spnAniosExp = new JSpinner();
		spnAniosExp.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		spnAniosExp.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spnAniosExp.setBounds(238, 37, 143, 20);
		pnlFormTec.add(spnAniosExp);

		txtTecnicoCand = new JTextField();
		txtTecnicoCand.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtTecnicoCand.setBounds(10, 37, 198, 20);
		pnlFormTec.add(txtTecnicoCand);
		txtTecnicoCand.setColumns(10);

		JPanel pnlFormObrero = new JPanel();
		pnlFormObrero.setBackground(Color.WHITE);
		jtpFormacion.addTab("New tab", null, pnlFormObrero, null);
		pnlFormObrero.setLayout(null);

		JLabel lblHabilidadesQuePosee = new JLabel("Habilidades que posee");
		lblHabilidadesQuePosee.setHorizontalAlignment(SwingConstants.LEFT);
		lblHabilidadesQuePosee.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblHabilidadesQuePosee.setBounds(10, 0, 187, 54);
		pnlFormObrero.add(lblHabilidadesQuePosee);

		chckbxVentas = new JCheckBox("Ventas");
		chckbxVentas.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		chckbxVentas.setBackground(Color.WHITE);
		chckbxVentas.setBounds(197, 14, 87, 23);
		pnlFormObrero.add(chckbxVentas);

		chckbxMecanica = new JCheckBox("Mec\u00E1nica");
		chckbxMecanica.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		chckbxMecanica.setBackground(Color.WHITE);
		chckbxMecanica.setBounds(197, 43, 87, 23);
		pnlFormObrero.add(chckbxMecanica);

		chckbxConduccin = new JCheckBox("Conducci\u00F3n");
		chckbxConduccin.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		chckbxConduccin.setBackground(Color.WHITE);
		chckbxConduccin.setBounds(554, 14, 106, 23);
		pnlFormObrero.add(chckbxConduccin);

		chckbxElectricidad = new JCheckBox("Electricidad");
		chckbxElectricidad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		chckbxElectricidad.setBackground(Color.WHITE);
		chckbxElectricidad.setBounds(298, 43, 119, 23);
		pnlFormObrero.add(chckbxElectricidad);

		chckbxOfimtica = new JCheckBox("Ofim\u00E1tica");
		chckbxOfimtica.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		chckbxOfimtica.setBackground(Color.WHITE);
		chckbxOfimtica.setBounds(298, 14, 119, 23);
		pnlFormObrero.add(chckbxOfimtica);

		chckbxMantenimiento = new JCheckBox("Mantenimiento");
		chckbxMantenimiento.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		chckbxMantenimiento.setBackground(Color.WHITE);
		chckbxMantenimiento.setBounds(419, 43, 119, 23);
		pnlFormObrero.add(chckbxMantenimiento);

		chckbxSeguridad = new JCheckBox("Seguridad");
		chckbxSeguridad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		chckbxSeguridad.setBackground(Color.WHITE);
		chckbxSeguridad.setBounds(419, 14, 119, 23);
		pnlFormObrero.add(chckbxSeguridad);

		chckbxLimpieza = new JCheckBox("Limpieza");
		chckbxLimpieza.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		chckbxLimpieza.setBackground(Color.WHITE);
		chckbxLimpieza.setBounds(554, 43, 119, 23);
		pnlFormObrero.add(chckbxLimpieza);

		passwCandidato = new JPasswordField();
		passwCandidato.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		passwCandidato.setBounds(51, 709, 231, 20);
		pnlRegistroCandidato.add(passwCandidato);

		passwCandidatoVerificar = new JPasswordField();
		passwCandidatoVerificar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		passwCandidatoVerificar.setBounds(51, 781, 231, 20);
		pnlRegistroCandidato.add(passwCandidatoVerificar);

		JPanel pnlRegistrarCand= new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				// Color de fondo
				g2.setColor(getBackground());

				// Dibuja un rectángulo redondeado (x, y, width, height, arcWidth, arcHeight)
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 80, 80);
			}
		};
		pnlRegistrarCand.setOpaque(false);


		pnlRegistrarCand.setBackground(new Color(46, 204, 113));
		pnlRegistrarCand.setBounds(517, 682, 231, 59);
		pnlRegistroCandidato.add(pnlRegistrarCand);
		pnlRegistrarCand.setLayout(null);

		JLabel btnRegistrarCand = new JLabel("Reg\u00EDstrate");
		btnRegistrarCand.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				//OJO: colocar validaciones
				Persona aux = null;

				String codigoGenerado = Bolsa.getInstancia().generarCodigoPersona();
				String nombre = txtNombreCand.getText();
				String apellido = txtApellidoCand.getText();
				String sexo = "";
				//obtener valor de passwordfield
				char[] passwdChars = passwCandidato.getPassword();
				String passwd = new String(passwdChars);

				if(rdbtnSexoM.isSelected()) {
					sexo = "M";
				} else if(rdbtnSexoF.isSelected()) {
					sexo = "F";
				}
				String cedula = txtCedulaCand.getText();
				String telefono = txtTelefonoCand.getText();
				String correo = txtCorreoCand.getText();
				String provi = cbxProvinciaCand.getSelectedItem().toString();
				String direccion = txtDireccionCand.getText();
				Date fechaNacimiento = (Date) spnFechaNacCand.getValue();
				String horarios = cbxHorario.getSelectedItem().toString();
				boolean movilidad = false;
				if(cbxDispMovilidad.getSelectedIndex()==1) {
					movilidad = true;
				}
				boolean licencia = false;
				if(rdbtnLicenciaSi.isSelected()) {
					licencia = true;
				}

				String tipoEmpleo = cbxTipoEmpleo.getSelectedItem().toString();
				String modalidad = cbxModalidad.getSelectedItem().toString();

				if(rdbtnUniversitario.isSelected()) {
					String carrera = cbxCarrera.getSelectedItem().toString();
					aux = new Universitario(codigoGenerado, nombre, apellido, sexo, passwd, fechaNacimiento, cedula, telefono, correo, provi, direccion, horarios, movilidad, licencia, tipoEmpleo, modalidad, false, null, carrera);
				}

				if(rdbtnTecSuperior.isSelected()) {
					String tecnico = txtTecnicoCand.getText();
					int aniosexp = (int) spnAniosExp.getValue();
					aux = new TecnicoSuperior(codigoGenerado, nombre, apellido, sexo, passwd, fechaNacimiento, cedula, telefono, correo, provi, direccion, horarios, movilidad, licencia, tipoEmpleo, modalidad, false, null, tecnico, aniosexp);
				}

				if(rdbtnObrero.isSelected()) {
					ArrayList<String> Habilidades = new ArrayList<String>();

					if(chckbxVentas.isSelected())
						Habilidades.add("Ventas");
					if(chckbxConduccin.isSelected())
						Habilidades.add("Conduccion");
					if(chckbxElectricidad.isSelected())
						Habilidades.add("Electricidad");
					if(chckbxLimpieza.isSelected())
						Habilidades.add("Limpieza");
					if(chckbxMantenimiento.isSelected())
						Habilidades.add("Mantenimiento");
					if(chckbxMecanica.isSelected())
						Habilidades.add("Mecanica");
					if(chckbxOfimtica.isSelected())
						Habilidades.add("Ofimatica");
					if(chckbxSeguridad.isSelected())
						Habilidades.add("Seguridad");
					aux = new Obrero(codigoGenerado, nombre, apellido, sexo, passwd, fechaNacimiento, cedula, telefono, correo, provi, direccion, horarios, movilidad, licencia, tipoEmpleo, modalidad, false, null, Habilidades);

				}

				Bolsa.getInstancia().insertarPersona(aux);
				JOptionPane.showMessageDialog(null, "Registro Satisfactorio! Proceda a iniciar sesión", "Informacion", JOptionPane.INFORMATION_MESSAGE);
				limpiarRegCand();
				jtpSecciones.setSelectedIndex(0); //volver a la pagina de login general
			}
		});
		btnRegistrarCand.setForeground(Color.WHITE);
		btnRegistrarCand.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnRegistrarCand.setHorizontalAlignment(SwingConstants.CENTER);
		btnRegistrarCand.setBounds(0, 11, 231, 37);
		pnlRegistrarCand.add(btnRegistrarCand);

		JPanel pnlVolverLoginCand = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				// Color de fondo
				g2.setColor(getBackground());

				// Dibuja un rectángulo redondeado (x, y, width, height, arcWidth, arcHeight)
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 80, 80);
			}
		};
		pnlVolverLoginCand.setOpaque(false);
		pnlVolverLoginCand.setLayout(null);
		pnlVolverLoginCand.setBackground(new Color(41, 128, 185));
		pnlVolverLoginCand.setBounds(517, 752, 231, 59);
		pnlRegistroCandidato.add(pnlVolverLoginCand);

		JLabel lblIniciarSesin = new JLabel("Inicia Sesi\u00F3n");
		lblIniciarSesin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarRegCand();
				jtpSecciones.setSelectedIndex(0);
			}
		});
		lblIniciarSesin.setHorizontalAlignment(SwingConstants.CENTER);
		lblIniciarSesin.setForeground(Color.WHITE);
		lblIniciarSesin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblIniciarSesin.setBounds(0, 11, 231, 37);
		pnlVolverLoginCand.add(lblIniciarSesin);

		rdbtnSexoM = new JRadioButton("M");
		rdbtnSexoM.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rdbtnSexoM.setBackground(Color.WHITE);
		rdbtnSexoM.setBounds(562, 234, 60, 23);
		pnlRegistroCandidato.add(rdbtnSexoM);

		rdbtnSexoF = new JRadioButton("F");
		rdbtnSexoF.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rdbtnSexoF.setBackground(Color.WHITE);
		rdbtnSexoF.setBounds(650, 234, 60, 23);
		pnlRegistroCandidato.add(rdbtnSexoF);

		cbxHorario = new JComboBox();
		cbxHorario.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Matutino", "Vespertino", "Nocturno", "Matutino y Vespertino", "Vespertino y Nocturno", "Todos"}));
		cbxHorario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cbxHorario.setBackground(Color.WHITE);
		cbxHorario.setBounds(51, 451, 212, 20);
		pnlRegistroCandidato.add(cbxHorario);
	}

	private void limpiarRegCand() {
		// limpiar el registro de candidatos
		txtNombreCand.setText("");
		txtApellidoCand.setText("");

		rdbtnSexoM.setSelected(true);
		rdbtnSexoF.setSelected(true);

		txtCedulaCand.setText("");
		txtCorreoCand.setText("");
		txtTelefonoCand.setText("");
		txtDireccionCand.setText("");
		cbxProvinciaCand.setSelectedIndex(0);
		cbxDispMovilidad.setSelectedIndex(0);

		rdbtnLicenciaSi.setSelected(true);
		rdbtnLicenciaNo.setSelected(true);

		cbxHorario.setSelectedIndex(0);

		cbxCarrera.setSelectedIndex(0);

		txtTecnicoCand.setText("");
		spnAniosExp.setValue(0);

		chckbxVentas.setSelected(false);
		chckbxMecanica.setSelected(false);
		chckbxOfimtica.setSelected(false);
		chckbxElectricidad.setSelected(false);
		chckbxSeguridad.setSelected(false);
		chckbxMantenimiento.setSelected(false);
		chckbxConduccin.setSelected(false);
		chckbxLimpieza.setSelected(false);

		passwCandidato.setText("");
		passwCandidatoVerificar.setText("");
	}


}
