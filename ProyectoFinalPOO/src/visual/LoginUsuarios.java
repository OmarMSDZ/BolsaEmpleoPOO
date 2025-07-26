package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Bolsa;
import logica.Empresa;
import logica.Usuario;
import logica.Persona;
import logica.Obrero;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
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

public class LoginUsuarios extends JDialog {

	private final JPanel contentPanel = new JPanel();

	// Variables para la captura de datos
	private int eleccion = -1;
	private String passwd;
	private String passwdC;
	private JSpinner.DateEditor de_spnFechaNac;
	private JTextField txtCorreo;
	private JPasswordField txtPasswd;
	private JTabbedPane jtpSecciones;
	private JRadioButton rdbtnUniversitario;
	private JRadioButton rdbtnTecnico;
	private JRadioButton rdbtnObrero;
	private JTabbedPane jtpFormacion;
	private JTextField txtApellido;
	private JTextField txtCedula;
	private JTextField txtTecnico;
	private JSpinner spnAniosExp;
	private JSpinner spnFechaNac;
	private JCheckBox chckbxVentas;
	private JCheckBox chckbxMecanica;
	private JCheckBox chckbxOfimatica;
	private JCheckBox chckbxElectricidad;
	private JCheckBox chckbxSeguridad;
	private JCheckBox chckbxMantenimiento;
	private JCheckBox chckbxConduccion;
	private JCheckBox chckbxLimpieza;
	private JRadioButton rdbtnSexoM;
	private JRadioButton rdbtnSexoF;
	private JTextField txtNombre;
	private JPasswordField txtRegPasswd;
	private JPasswordField txtValidarPasswd;
	private JTextField txtRegCorreo;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtMunicipio;
	private JTextField txtRNC;
	private JComboBox cmbCarreras;
	private JComboBox cmbTipoEmpresa;
	private JComboBox cmbProvincia;
	private JTextField txtSector;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoginUsuarios dialog = new LoginUsuarios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					try (ObjectOutputStream bolsaWrite = new ObjectOutputStream(new FileOutputStream("BdLaborea.dat"))) {
						bolsaWrite.writeObject(Bolsa.getInstancia());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoginUsuarios() {
		setResizable(false);
		setTitle("Laborea - \u00A1Inicia sesi\u00F3n o Registrate!");
		setBounds(100, 100, 800, 750);

		// **** SUPERUSUARIO, ELIMINAR DESPUES ****
		// Universitario superusu = new Universitario("SUP-1", "Omar", "Morales", "M",
		// "1234", new Date(), "abc", "8091231234", "super@gmail.com", "Santiago", "Su
		// casa", "Todos", true, true, "Tiempo Completo", "Presencial", false, null,
		// "Ing. en Sistemas");
		// Bolsa.getInstancia().insertarPersona(superusu);
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		jtpSecciones = new JTabbedPane(JTabbedPane.TOP);
		jtpSecciones.setEnabled(false);
		jtpSecciones.setBackground(Color.WHITE);
		jtpSecciones.setBounds(-11, -35, 816, 760);
		contentPanel.add(jtpSecciones);

		JPanel pnlLoginUsuarios = new JPanel();
		pnlLoginUsuarios.setBackground(Color.WHITE);
		jtpSecciones.addTab("New tab", null, pnlLoginUsuarios, null);
		pnlLoginUsuarios.setLayout(null);

		JLabel iconLaborea = new JLabel("");
		iconLaborea.setHorizontalAlignment(SwingConstants.CENTER);
		iconLaborea.setIcon(new ImageIcon(LoginUsuarios.class.getResource("/img/Laborea.png")));
		iconLaborea.setBounds(12, 13, 150, 84);
		pnlLoginUsuarios.add(iconLaborea);

		JLabel lblBienvenido = new JLabel("Bienvenido/a.");
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 24));
		lblBienvenido.setBounds(278, 144, 254, 42);
		pnlLoginUsuarios.add(lblBienvenido);

		JLabel lblIngresaParaComenzar = new JLabel("Ingresa para comenzar");
		lblIngresaParaComenzar.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngresaParaComenzar.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblIngresaParaComenzar.setBounds(12, 176, 787, 42);
		pnlLoginUsuarios.add(lblIngresaParaComenzar);

		txtCorreo = new JTextField();
		txtCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		txtCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtCorreo.setBackground(Color.WHITE);
		txtCorreo.setBounds(99, 312, 600, 35);
		pnlLoginUsuarios.add(txtCorreo);
		txtCorreo.setColumns(10);

		txtPasswd = new JPasswordField();
		txtPasswd.setHorizontalAlignment(SwingConstants.LEFT);
		txtPasswd.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtPasswd.setBackground(Color.WHITE);
		txtPasswd.setBounds(99, 441, 600, 35);
		pnlLoginUsuarios.add(txtPasswd);

		JLabel iconLlaves = new JLabel("");
		iconLlaves.setHorizontalTextPosition(SwingConstants.CENTER);
		iconLlaves.setIcon(new ImageIcon(LoginUsuarios.class.getResource("/img/key-chain.png")));
		iconLlaves.setHorizontalAlignment(SwingConstants.CENTER);
		iconLlaves.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		iconLlaves.setBounds(100, 382, 62, 46);
		pnlLoginUsuarios.add(iconLlaves);

		JLabel iconCorreo = new JLabel("");
		iconCorreo.setIcon(new ImageIcon(LoginUsuarios.class.getResource("/img/letter.png")));
		iconCorreo.setHorizontalTextPosition(SwingConstants.CENTER);
		iconCorreo.setHorizontalAlignment(SwingConstants.CENTER);
		iconCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		iconCorreo.setBounds(100, 253, 62, 46);
		pnlLoginUsuarios.add(iconCorreo);

		JLabel lblCorreoElectronico = new JLabel("Correo electr\u00F3nico:");
		lblCorreoElectronico.setHorizontalTextPosition(SwingConstants.LEFT);
		lblCorreoElectronico.setHorizontalAlignment(SwingConstants.LEFT);
		lblCorreoElectronico.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblCorreoElectronico.setBounds(166, 253, 187, 46);
		pnlLoginUsuarios.add(lblCorreoElectronico);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setHorizontalTextPosition(SwingConstants.LEFT);
		lblContrasea.setHorizontalAlignment(SwingConstants.LEFT);
		lblContrasea.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblContrasea.setBounds(166, 382, 187, 46);
		pnlLoginUsuarios.add(lblContrasea);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(99, 220, 613, 40);
		pnlLoginUsuarios.add(separator);

		JPanel btnInicioSesion = new JPanel() {
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
		btnInicioSesion.setOpaque(false);

		btnInicioSesion.setBackground(new Color(60, 179, 113));
		btnInicioSesion.setBounds(278, 522, 250, 50);
		pnlLoginUsuarios.add(btnInicioSesion);

		JLabel btnIniciarSesion = new JLabel("Iniciar sesi\u00F3n");
		btnIniciarSesion.setBackground(Color.WHITE);
		btnIniciarSesion.setBounds(3, 13, 249, 24);
		btnIniciarSesion.setToolTipText("");
		btnIniciarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String email = txtCorreo.getText();
				char[] passwdChars = txtPasswd.getPassword();
				String passwd = new String(passwdChars);
				Usuario usuarioLogin = Bolsa.getInstancia().validarLoginUsuarios(email, passwd);

				if (email.equalsIgnoreCase("correoadmin@gmail.com") && passwd.equalsIgnoreCase("1234")) {
					MenuAdmins nuevoMenuAdmin = new MenuAdmins();
					nuevoMenuAdmin.setModal(true);
					nuevoMenuAdmin.setVisible(true);
					dispose();
				} else if (usuarioLogin != null) {
					JOptionPane.showMessageDialog(null, "¡Bienvenido/a a Laborea!", "Información",
							JOptionPane.INFORMATION_MESSAGE);
					if (usuarioLogin instanceof Persona) {
						Bolsa.setUsuarioActivo(usuarioLogin);
						MenuCandidatos menuCand = new MenuCandidatos();
						menuCand.setVisible(true);
						limpiarInicioSesion();
						 
						dispose();
					} else if (usuarioLogin instanceof Empresa) {
						MenuEmpresas menuEmpr = new MenuEmpresas();
						menuEmpr.setVisible(true);
						limpiarInicioSesion();
						dispose();
					}
					limpiarInicioSesion();
				} else {
					JOptionPane.showMessageDialog(null,
							"¡No se encuentró el usuario registrado con esta información! Verique los datos.",
							"Advertencia", JOptionPane.WARNING_MESSAGE);
					limpiarInicioSesion();
				}
			}
		});
		btnInicioSesion.setLayout(null);
		btnIniciarSesion.setForeground(Color.WHITE);
		btnIniciarSesion.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnIniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		btnInicioSesion.add(btnIniciarSesion);

		JLabel lblPregunta = new JLabel("\u00BFA\u00FAn no te has registrado?");
		lblPregunta.setHorizontalTextPosition(SwingConstants.LEFT);
		lblPregunta.setHorizontalAlignment(SwingConstants.CENTER);
		lblPregunta.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblPregunta.setBounds(299, 601, 213, 31);
		pnlLoginUsuarios.add(lblPregunta);

		JLabel iconVolverInicio = new JLabel("");
		iconVolverInicio.setToolTipText("Volver a inicio");
		iconVolverInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		iconVolverInicio.setIcon(new ImageIcon(LoginUsuarios.class.getResource("/img/home.png")));
		iconVolverInicio.setHorizontalAlignment(SwingConstants.CENTER);
		iconVolverInicio.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		iconVolverInicio.setBounds(707, 13, 92, 84);
		pnlLoginUsuarios.add(iconVolverInicio);

		JLabel lblRegistrate = new JLabel("\u00A1Reg\u00EDstrate aqu\u00ED!");
		lblRegistrate.setBackground(Color.WHITE);
		lblRegistrate.setOpaque(true);
		lblRegistrate.setBounds(281, 634, 249, 31);
		pnlLoginUsuarios.add(lblRegistrate);
		lblRegistrate.setToolTipText("");
		lblRegistrate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object[] opciones = { "Candidato", "Empresa" };
				eleccion = JOptionPane.showOptionDialog(null, "Indique el tipo de usuario:", "Registro de usuarios",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
				if (eleccion == 0 || eleccion == 1) {
					limpiarInicioSesion();
					jtpSecciones.setSelectedIndex(1);
				} else {
					JOptionPane.showMessageDialog(null, "Error: Debe indicar el tipo de usuario", "Advertencia",
							JOptionPane.WARNING_MESSAGE, null);
				}
			}
		});
		lblRegistrate.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrate.setForeground(new Color(0, 0, 205));
		lblRegistrate.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

		JPanel pnlRegistroUsuarios = new JPanel();
		pnlRegistroUsuarios.setBackground(Color.WHITE);
		jtpSecciones.addTab("New tab", null, pnlRegistroUsuarios, null);
		pnlRegistroUsuarios.setLayout(null);

		JLabel iconLaboreaRegUsu = new JLabel("");
		iconLaboreaRegUsu.setIcon(new ImageIcon(LoginUsuarios.class.getResource("/img/Laborea.png")));
		iconLaboreaRegUsu.setHorizontalAlignment(SwingConstants.CENTER);
		iconLaboreaRegUsu.setBounds(12, 13, 150, 84);
		pnlRegistroUsuarios.add(iconLaboreaRegUsu);

		JLabel iconRegresarRegUsu = new JLabel("");
		iconRegresarRegUsu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarRegUsuario();
				jtpSecciones.setSelectedIndex(0);
			}
		});
		iconRegresarRegUsu.setIcon(new ImageIcon(LoginUsuarios.class.getResource("/img/flechaRegresar.png")));
		iconRegresarRegUsu.setHorizontalAlignment(SwingConstants.CENTER);
		iconRegresarRegUsu.setBounds(701, 13, 98, 76);
		pnlRegistroUsuarios.add(iconRegresarRegUsu);

		JLabel lblBienvenidaRegUsuario = new JLabel("Bienvenido/a.");
		lblBienvenidaRegUsuario.setFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
		lblBienvenidaRegUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenidaRegUsuario.setBounds(144, 110, 523, 27);
		pnlRegistroUsuarios.add(lblBienvenidaRegUsuario);

		JLabel lblIngreseDatos = new JLabel("Ingresa tus datos antes de continuar");
		lblIngreseDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseDatos.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblIngreseDatos.setBounds(12, 134, 787, 40);
		pnlRegistroUsuarios.add(lblIngreseDatos);

		JPanel btnContinuar = new JPanel() {
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
		btnContinuar.setBackground(new Color(70, 130, 180));
		btnContinuar.setOpaque(false);
		btnContinuar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String correoIngresado = txtRegCorreo.getText();
				passwd = new String(txtRegPasswd.getPassword());
				passwdC = new String(txtValidarPasswd.getPassword());

				if (validarDatosUsuario()) {
					if (Bolsa.getInstancia().validarCorreo(correoIngresado)) {
						if (confirmarPasswd(passwd, passwdC)) {
							if (eleccion == 0) {
								jtpSecciones.setSelectedIndex(2);
							} else if (eleccion == 1) {
								jtpSecciones.setSelectedIndex(3);
							} else {
								JOptionPane.showMessageDialog(null, "Error: Aún no se ha indicado el tipo de usuario.",
										"Advertencia", JOptionPane.WARNING_MESSAGE, null);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Ambas contraseñas deben ser iguales.", "Advertencia",
									JOptionPane.WARNING_MESSAGE, null);
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"El correo ingresado ya está registrado. Por favor, utiliza otro.", "Advertencia",
								JOptionPane.WARNING_MESSAGE, null);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Todos los campos de datos deben llenarse antes de continuar.",
							"Advertencia", JOptionPane.WARNING_MESSAGE, null);
				}

			}
		});
		btnContinuar.setBounds(599, 638, 175, 60);
		pnlRegistroUsuarios.add(btnContinuar);
		btnContinuar.setLayout(null);

		JLabel iconFeclaContinuar = new JLabel("\r\n");
		iconFeclaContinuar.setBackground(Color.WHITE);
		iconFeclaContinuar.setIcon(new ImageIcon(LoginUsuarios.class.getResource("/img/iconContinuar.png")));
		iconFeclaContinuar.setHorizontalAlignment(SwingConstants.CENTER);
		iconFeclaContinuar.setBounds(12, 13, 74, 34);
		btnContinuar.add(iconFeclaContinuar);

		JLabel lblContinuar = new JLabel("Continuar");
		lblContinuar.setHorizontalAlignment(SwingConstants.CENTER);
		lblContinuar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblContinuar.setBackground(Color.WHITE);
		lblContinuar.setBounds(74, 13, 89, 34);
		btnContinuar.add(lblContinuar);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNombre.setBounds(104, 183, 200, 35);
		pnlRegistroUsuarios.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNombre.setBounds(104, 217, 607, 35);
		pnlRegistroUsuarios.add(txtNombre);
		txtNombre.setColumns(10);

		txtRegPasswd = new JPasswordField();
		txtRegPasswd.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtRegPasswd.setBounds(104, 559, 287, 35);
		pnlRegistroUsuarios.add(txtRegPasswd);

		txtValidarPasswd = new JPasswordField();
		txtValidarPasswd.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtValidarPasswd.setBounds(424, 559, 287, 35);
		pnlRegistroUsuarios.add(txtValidarPasswd);

		JLabel lblRegPasswd = new JLabel("Contrase\u00F1a:");
		lblRegPasswd.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblRegPasswd.setBounds(104, 525, 200, 35);
		pnlRegistroUsuarios.add(lblRegPasswd);

		JLabel lblValidarPasswd = new JLabel("Confirmar contrase\u00F1a:");
		lblValidarPasswd.setHorizontalAlignment(SwingConstants.LEFT);
		lblValidarPasswd.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblValidarPasswd.setBounds(424, 525, 200, 35);
		pnlRegistroUsuarios.add(lblValidarPasswd);

		JLabel lblRegCorreo = new JLabel("Correo electr\u00F3nico:");
		lblRegCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblRegCorreo.setBounds(104, 263, 200, 35);
		pnlRegistroUsuarios.add(lblRegCorreo);

		txtRegCorreo = new JTextField();
		txtRegCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtRegCorreo.setBounds(104, 300, 607, 35);
		pnlRegistroUsuarios.add(txtRegCorreo);
		txtRegCorreo.setColumns(10);

		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTelefono.setBounds(104, 345, 200, 35);
		pnlRegistroUsuarios.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTelefono.setBounds(104, 379, 607, 35);
		pnlRegistroUsuarios.add(txtTelefono);
		txtTelefono.setColumns(10);

		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setHorizontalAlignment(SwingConstants.LEFT);
		lblDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDireccion.setBounds(511, 425, 200, 35);
		pnlRegistroUsuarios.add(lblDireccion);

		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtDireccion.setBounds(511, 462, 200, 35);
		pnlRegistroUsuarios.add(txtDireccion);
		txtDireccion.setColumns(10);

		JLabel lblProvincia = new JLabel("Provincia:");
		lblProvincia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblProvincia.setHorizontalAlignment(SwingConstants.LEFT);
		lblProvincia.setBounds(104, 424, 200, 35);
		pnlRegistroUsuarios.add(lblProvincia);

		cmbProvincia = new JComboBox();
		cmbProvincia.setBackground(Color.WHITE);
		cmbProvincia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cmbProvincia.setModel(new DefaultComboBoxModel(new String[] { "<< Seleccione >>", "Azua", "Bahoruco",
				"Barahona", "Dajab\u00F3n", "Distrito Nacional", "Duarte", "El\u00EDas Pi\u00F1a", "El Seibo",
				"Espaillat", "Hato Mayor", "Hermanas Mirabal", "Independencia", "La Altagracia", "La Romana", "La Vega",
				"Mar\u00EDa Trinidad S\u00E1nchez", "Monse\u00F1or Nouel", "Monte Cristi", "Monte Plata", "Pedernales",
				"Peravia", "Puerto Plata", "Saman\u00E1", "San Crist\u00F3bal", "San Jos\u00E9 de Ocoa", "San Juan",
				"San Pedro de Macor\u00EDs", "S\u00E1nchez Ram\u00EDrez", "Santiago", "Santiago Rodr\u00EDguez",
				"Santo Domingo", "Valverde" }));
		cmbProvincia.setBounds(104, 462, 200, 35);
		pnlRegistroUsuarios.add(cmbProvincia);

		JLabel lblMunicipio = new JLabel("Municipio:");
		lblMunicipio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMunicipio.setBounds(326, 424, 200, 35);
		pnlRegistroUsuarios.add(lblMunicipio);

		txtMunicipio = new JTextField();
		txtMunicipio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMunicipio.setBounds(326, 462, 157, 35);
		pnlRegistroUsuarios.add(txtMunicipio);
		txtMunicipio.setColumns(10);

		JSeparator sptSubrayadoRegUsuario = new JSeparator();
		sptSubrayadoRegUsuario.setBackground(Color.WHITE);
		sptSubrayadoRegUsuario.setForeground(Color.BLACK);
		sptSubrayadoRegUsuario.setBounds(108, 172, 603, 27);
		pnlRegistroUsuarios.add(sptSubrayadoRegUsuario);

		JPanel pnlRegistroCandidato = new JPanel();
		pnlRegistroCandidato.setBackground(Color.WHITE);
		jtpSecciones.addTab("New tab", null, pnlRegistroCandidato, null);
		pnlRegistroCandidato.setLayout(null);

		JLabel iconRegresarRegCand = new JLabel("");
		iconRegresarRegCand.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtpSecciones.setSelectedIndex(1);
			}
		});
		iconRegresarRegCand.setIcon(new ImageIcon(LoginUsuarios.class.getResource("/img/flechaRegresar.png")));
		iconRegresarRegCand.setHorizontalAlignment(SwingConstants.CENTER);
		iconRegresarRegCand.setBounds(701, 13, 98, 76);
		pnlRegistroCandidato.add(iconRegresarRegCand);

		JLabel iconLaboreaRegCand = new JLabel("");
		iconLaboreaRegCand.setHorizontalAlignment(SwingConstants.CENTER);
		iconLaboreaRegCand.setIcon(new ImageIcon(LoginUsuarios.class.getResource("/img/Laborea.png")));
		iconLaboreaRegCand.setBounds(12, 13, 150, 84);
		pnlRegistroCandidato.add(iconLaboreaRegCand);

		JLabel lblRegCand = new JLabel(
				"Reg\u00EDstrate como candidato para crear solicitudes de empleo seg\u00FAn tu perfil");
		lblRegCand.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegCand.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblRegCand.setBounds(12, 93, 787, 59);
		pnlRegistroCandidato.add(lblRegCand);

		JSeparator sptSombreado = new JSeparator();
		sptSombreado.setBackground(Color.WHITE);
		sptSombreado.setForeground(Color.BLACK);
		sptSombreado.setBounds(99, 150, 613, 33);
		pnlRegistroCandidato.add(sptSombreado);

		JLabel lblApellidos = new JLabel("Apellido/s:");
		lblApellidos.setHorizontalAlignment(SwingConstants.LEFT);
		lblApellidos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblApellidos.setBounds(51, 195, 200, 35);
		pnlRegistroCandidato.add(lblApellidos);

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setHorizontalAlignment(SwingConstants.LEFT);
		lblSexo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblSexo.setBounds(512, 195, 200, 35);
		pnlRegistroCandidato.add(lblSexo);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
		lblFechaDeNacimiento.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaDeNacimiento.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblFechaDeNacimiento.setBounds(51, 284, 200, 35);
		pnlRegistroCandidato.add(lblFechaDeNacimiento);

		JLabel lblCedula = new JLabel("C\u00E9dula:");
		lblCedula.setHorizontalAlignment(SwingConstants.LEFT);
		lblCedula.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCedula.setBounds(280, 194, 200, 35);
		pnlRegistroCandidato.add(lblCedula);

		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtApellido.setColumns(10);
		txtApellido.setBounds(51, 227, 200, 35);
		pnlRegistroCandidato.add(txtApellido);

		txtCedula = new JTextField();
		txtCedula.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtCedula.setColumns(10);
		txtCedula.setBounds(280, 232, 200, 35);
		pnlRegistroCandidato.add(txtCedula);

		spnFechaNac = new JSpinner();
		spnFechaNac.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		spnFechaNac.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_YEAR));

		de_spnFechaNac = new JSpinner.DateEditor(spnFechaNac, "dd/MM/yyyy");
		spnFechaNac.setEditor(de_spnFechaNac);

		spnFechaNac.setBounds(51, 322, 429, 35);
		pnlRegistroCandidato.add(spnFechaNac);

		JLabel lblFormacin = new JLabel("Formaci\u00F3n:");
		lblFormacin.setHorizontalAlignment(SwingConstants.LEFT);
		lblFormacin.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblFormacin.setBounds(51, 368, 200, 35);
		pnlRegistroCandidato.add(lblFormacin);

		rdbtnUniversitario = new JRadioButton("Universitario/a");
		rdbtnUniversitario.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnUniversitario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		rdbtnUniversitario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnUniversitario.setSelected(true);
				rdbtnTecnico.setSelected(false);
				rdbtnObrero.setSelected(false);
				jtpFormacion.setSelectedIndex(0);
			}
		});
		rdbtnUniversitario.setBackground(Color.WHITE);
		rdbtnUniversitario.setSelected(true);
		rdbtnUniversitario.setBounds(51, 411, 200, 35);
		pnlRegistroCandidato.add(rdbtnUniversitario);

		rdbtnTecnico = new JRadioButton("T\u00E9c. Superior");
		rdbtnTecnico.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnTecnico.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		rdbtnTecnico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnUniversitario.setSelected(false);
				rdbtnTecnico.setSelected(true);
				rdbtnObrero.setSelected(false);
				jtpFormacion.setSelectedIndex(1);
			}
		});
		rdbtnTecnico.setBackground(Color.WHITE);
		rdbtnTecnico.setBounds(255, 411, 200, 35);
		pnlRegistroCandidato.add(rdbtnTecnico);

		rdbtnObrero = new JRadioButton("Obrero");
		rdbtnObrero.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnObrero.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		rdbtnObrero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnUniversitario.setSelected(false);
				rdbtnTecnico.setSelected(false);
				rdbtnObrero.setSelected(true);
				jtpFormacion.setSelectedIndex(2);
			}
		});
		rdbtnObrero.setBackground(Color.WHITE);
		rdbtnObrero.setBounds(459, 411, 200, 35);
		pnlRegistroCandidato.add(rdbtnObrero);

		JPanel pnlRegistrarCand = new JPanel() {
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
		pnlRegistrarCand.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (validarDatosCand()) {
					Usuario nuevoUsuario = null;

					String codigoUsuario = Bolsa.getInstancia().generarCodigoUsuario();
					String nombre = txtNombre.getText();
					char[] passwdChars = txtRegPasswd.getPassword();
					passwd = new String(passwdChars);
					String telefono = txtTelefono.getText();
					String correo = txtRegCorreo.getText();
					String provincia = cmbProvincia.getSelectedItem().toString();
					String municipio = txtMunicipio.getText();
					String direccion = txtDireccion.getText();
					String apellidos = txtApellido.getText();
					String cedula = txtCedula.getText();
					String sexo;

					if (rdbtnSexoM.isSelected()) {
						sexo = rdbtnSexoM.getText();
					} else {
						sexo = rdbtnSexoF.getText();
					}

					Date fechaNacimiento = (Date) spnFechaNac.getValue();

					if (validarEdad(fechaNacimiento)) {
						if (rdbtnUniversitario.isSelected()) {
							String carrera = cmbCarreras.getSelectedItem().toString();
							nuevoUsuario = new Universitario(codigoUsuario, nombre, passwd, telefono, correo, provincia,
									municipio, direccion, true, apellidos, sexo, fechaNacimiento, cedula, false,
									carrera);
						} else if (rdbtnTecnico.isSelected()) {
							String tecnicoS = txtTecnico.getText();
							int anniosExp = (int) spnAniosExp.getValue();
							nuevoUsuario = new TecnicoSuperior(codigoUsuario, nombre, passwd, telefono, correo,
									provincia, municipio, direccion, true, apellidos, sexo, fechaNacimiento, cedula,
									false, tecnicoS, anniosExp);
						} else {
							boolean conduccion = chckbxConduccion.isSelected();
							boolean electricidad = chckbxElectricidad.isSelected();
							boolean limpieza = chckbxLimpieza.isSelected();
							boolean mantenimiento = chckbxMantenimiento.isSelected();
							boolean mecanica = chckbxMecanica.isSelected();
							boolean ofimatica = chckbxOfimatica.isSelected();
							boolean seguridad = chckbxSeguridad.isSelected();
							boolean ventas = chckbxVentas.isSelected();
							nuevoUsuario = new Obrero(codigoUsuario, nombre, passwd, telefono, correo, provincia,
									municipio, direccion, true, apellidos, sexo, fechaNacimiento, cedula, false, ventas,
									mecanica, ofimatica, seguridad, electricidad, mantenimiento, conduccion, limpieza);
						}
						Bolsa.getInstancia().insertarUsuario(nuevoUsuario);
						JOptionPane.showMessageDialog(null,
								"¡Se ha registrado satisfactoriamente! Regrese a la pantalla principal e inicie sesión.",
								"Información", JOptionPane.INFORMATION_MESSAGE, null);
						limpiarRegUsuario();
						limpiarRegCand();
						jtpSecciones.setSelectedIndex(0);
					} else {
						JOptionPane.showMessageDialog(null, "No es posible registrarse si es menor a 17 años.",
								"Advertencia", JOptionPane.WARNING_MESSAGE, null);
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Todos los datos deben ser proporcionados antes de efectuar el registro. Verique la información",
							"Advertencia", JOptionPane.WARNING_MESSAGE, null);
				}
			}
		});
		pnlRegistrarCand.setOpaque(false);

		pnlRegistrarCand.setBackground(new Color(60, 179, 113));
		pnlRegistrarCand.setBounds(606, 638, 175, 59);
		pnlRegistroCandidato.add(pnlRegistrarCand);
		pnlRegistrarCand.setLayout(null);

		JLabel btnRegistrarCand = new JLabel("Registrar");
		btnRegistrarCand.setBackground(Color.WHITE);

		JLabel iconAddPerson = new JLabel("");
		iconAddPerson.setIcon(new ImageIcon(LoginUsuarios.class.getResource("/img/iconAddPerson.png")));
		iconAddPerson.setBackground(Color.WHITE);
		iconAddPerson.setHorizontalAlignment(SwingConstants.CENTER);
		iconAddPerson.setBounds(23, 13, 55, 37);
		pnlRegistrarCand.add(iconAddPerson);
		btnRegistrarCand.setForeground(Color.BLACK);
		btnRegistrarCand.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnRegistrarCand.setHorizontalAlignment(SwingConstants.CENTER);
		btnRegistrarCand.setBounds(64, 13, 99, 37);
		pnlRegistrarCand.add(btnRegistrarCand);

		rdbtnSexoM = new JRadioButton("M");
		rdbtnSexoM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnSexoM.setSelected(true);
				rdbtnSexoF.setSelected(false);
			}
		});
		rdbtnSexoM.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnSexoM.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		rdbtnSexoM.setBackground(Color.WHITE);
		rdbtnSexoM.setBounds(512, 227, 48, 35);
		pnlRegistroCandidato.add(rdbtnSexoM);

		rdbtnSexoF = new JRadioButton("F");
		rdbtnSexoF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnSexoF.setSelected(true);
				rdbtnSexoM.setSelected(false);
			}
		});
		rdbtnSexoF.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnSexoF.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		rdbtnSexoF.setBackground(Color.WHITE);
		rdbtnSexoF.setBounds(576, 227, 48, 35);
		pnlRegistroCandidato.add(rdbtnSexoF);

		JPanel pnlOcultoSup = new JPanel();
		pnlOcultoSup.setBackground(Color.WHITE);
		pnlOcultoSup.setBounds(35, 448, 764, 33);
		pnlRegistroCandidato.add(pnlOcultoSup);

		JPanel pnlOcultoInf = new JPanel();
		pnlOcultoInf.setBackground(Color.WHITE);
		pnlOcultoInf.setBounds(34, 553, 764, 17);
		pnlRegistroCandidato.add(pnlOcultoInf);

		JPanel pnlOcultoLateralDer = new JPanel();
		pnlOcultoLateralDer.setBounds(772, 447, 25, 123);
		pnlRegistroCandidato.add(pnlOcultoLateralDer);
		pnlOcultoLateralDer.setBackground(Color.WHITE);

		JPanel pnlOcultoLateralIzq = new JPanel();
		pnlOcultoLateralIzq.setBounds(24, 448, 17, 123);
		pnlRegistroCandidato.add(pnlOcultoLateralIzq);
		pnlOcultoLateralIzq.setBackground(Color.WHITE);

		jtpFormacion = new JTabbedPane(JTabbedPane.TOP);
		jtpFormacion.setBorder(null);
		jtpFormacion.setBounds(35, 448, 764, 114);
		pnlRegistroCandidato.add(jtpFormacion);
		jtpFormacion.setEnabled(false);

		JPanel pnlFormUniv = new JPanel();
		jtpFormacion.addTab("New tab", null, pnlFormUniv, null);
		pnlFormUniv.setLayout(null);
		pnlFormUniv.setBackground(Color.WHITE);

		JLabel lblCarrera = new JLabel("Carrera:");
		lblCarrera.setHorizontalAlignment(SwingConstants.LEFT);
		lblCarrera.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCarrera.setBounds(22, 0, 200, 35);
		pnlFormUniv.add(lblCarrera);

		cmbCarreras = new JComboBox();
		cmbCarreras.setModel(new DefaultComboBoxModel(new String[] { "<< Seleccione >>",
				"Administraci\u00F3n de Empresas", "Agronom\u00EDa", "Antropolog\u00EDa", "Animaci\u00F3n Digital",
				"Arquitectura", "Artes Esc\u00E9nicas", "Artes Pl\u00E1sticas", "Astrobiolog\u00EDa", "Astronom\u00EDa",
				"Bellas Artes", "Bioinform\u00E1tica", "Biolog\u00EDa", "Biomedicina", "Biotecnolog\u00EDa",
				"Bioqu\u00EDmica", "Ciencia de Datos", "Ciencia y Tecnolog\u00EDa de Materiales Avanzados",
				"Ciencias Ambientales", "Ciencias de la Computaci\u00F3n", "Ciencias de la Comunicaci\u00F3n",
				"Ciencias de la Tierra", "Ciencias Forenses", "Ciencias Pol\u00EDticas", "Comercio Internacional",
				"Computaci\u00F3n Cu\u00E1ntica", "Contabilidad", "Criminolog\u00EDa", "Ciberseguridad", "Cine",
				"Danza", "Derecho", "Dise\u00F1o de Interiores", "Dise\u00F1o de Moda", "Dise\u00F1o Gr\u00E1fico",
				"Dise\u00F1o Industrial", "Dise\u00F1o Multimedia", "Dise\u00F1o UX-UI",
				"Dise\u00F1o y Gesti\u00F3n de Ciudades Inteligentes", "Econom\u00EDa", "Educaci\u00F3n",
				"Enfermer\u00EDa", "Entretenimiento Digital", "Estad\u00EDstica", "Farmacia", "Filosof\u00EDa",
				"Fisioterapia", "Finanzas", "Foniatr\u00EDa", "Fotograf\u00EDa", "Geograf\u00EDa", "Geof\u00EDsica",
				"Geolog\u00EDa", "Gen\u00E9tica", "Gerontolog\u00EDa", "Gesti\u00F3n Ambiental",
				"Gesti\u00F3n de la Innovaci\u00F3n y Emprendimiento", "Gesti\u00F3n de Riesgos y Desastres",
				"Gesti\u00F3n P\u00FAblica", "Historia", "Imagenolog\u00EDa", "Ingenier\u00EDa Aeroespacial",
				"Ingenier\u00EDa Agr\u00F3noma", "Ingenier\u00EDa Ambiental", "Ingenier\u00EDa Biom\u00E9dica",
				"Ingenier\u00EDa Civil", "Ingenier\u00EDa de Materiales", "Ingenier\u00EDa El\u00E9ctrica",
				"Ingenier\u00EDa Electr\u00F3nica", "Ingenier\u00EDa en Automatizaci\u00F3n y Control",
				"Ingenier\u00EDa en Energ\u00EDas Renovables", "Ingenier\u00EDa en Inteligencia Artificial",
				"Ingenier\u00EDa en Mecatr\u00F3nica", "Ingenier\u00EDa en Minas y Metalurgia",
				"Ingenier\u00EDa en Nanotecnolog\u00EDa", "Ingenier\u00EDa en Sistemas Computacionales",
				"Ingenier\u00EDa en Software", "Ingenier\u00EDa en Telecomunicaciones",
				"Ingenier\u00EDa en Transporte y V\u00EDas", "Ingenier\u00EDa Forestal",
				"Ingenier\u00EDa Gen\u00E9tica", "Ingenier\u00EDa Industrial", "Ingenier\u00EDa Mec\u00E1nica",
				"Ingenier\u00EDa Naval", "Ingenier\u00EDa Petrolera", "Ingenier\u00EDa Qu\u00EDmica",
				"Ingenier\u00EDa en Energ\u00EDa y Sustentabilidad", "Lenguas Modernas", "Ling\u00FC\u00EDstica",
				"Log\u00EDstica y Transporte", "Logopedia", "Laboratorio Cl\u00EDnico", "Matem\u00E1ticas", "Marketing",
				"Medicina", "Medicina Veterinaria", "Mercadotecnia", "M\u00FAsica", "Nanotecnolog\u00EDa Aplicada",
				"Negocios Internacionales", "Neurociencias", "Nutrici\u00F3n", "Oceanograf\u00EDa", "Odontolog\u00EDa",
				"Optometr\u00EDa", "Paleontolog\u00EDa", "Pedagog\u00EDa", "Periodismo", "Producci\u00F3n Audiovisual",
				"Publicidad y Relaciones P\u00FAblicas", "Psicolog\u00EDa", "Psicolog\u00EDa Cl\u00EDnica",
				"Qu\u00EDmica", "Realidad Virtual y Aumentada", "Relaciones Internacionales", "Rob\u00F3tica",
				"Sociolog\u00EDa", "Tecnolog\u00EDa de Alimentos", "Tecnolog\u00EDa M\u00E9dica", "Teatro",
				"Teolog\u00EDa", "Traducci\u00F3n e Interpretaci\u00F3n", "Trabajo Social", "Turismo y Hoteler\u00EDa",
				"Videojuegos", "Zootecnia" }));
		cmbCarreras.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cmbCarreras.setBackground(Color.WHITE);
		cmbCarreras.setBounds(22, 40, 473, 35);
		pnlFormUniv.add(cmbCarreras);

		JPanel pnlFormTec = new JPanel();
		pnlFormTec.setBorder(null);
		pnlFormTec.setBackground(Color.WHITE);
		jtpFormacion.addTab("New tab", null, pnlFormTec, null);
		pnlFormTec.setLayout(null);

		JLabel lblTecnicoCursado = new JLabel("T\u00E9cnico cursado:");
		lblTecnicoCursado.setHorizontalAlignment(SwingConstants.LEFT);
		lblTecnicoCursado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTecnicoCursado.setBounds(10, 0, 200, 35);
		pnlFormTec.add(lblTecnicoCursado);

		txtTecnico = new JTextField();
		txtTecnico.setBackground(Color.WHITE);
		txtTecnico.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTecnico.setBounds(10, 37, 364, 35);
		pnlFormTec.add(txtTecnico);
		txtTecnico.setColumns(10);

		JLabel lblAnnosDeExperiencia = new JLabel("A\u00F1os de experiencia:");
		lblAnnosDeExperiencia.setHorizontalAlignment(SwingConstants.LEFT);
		lblAnnosDeExperiencia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAnnosDeExperiencia.setBounds(384, 0, 200, 35);
		pnlFormTec.add(lblAnnosDeExperiencia);

		spnAniosExp = new JSpinner();
		spnAniosExp.setBackground(Color.WHITE);
		spnAniosExp.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		spnAniosExp.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spnAniosExp.setBounds(384, 37, 200, 35);
		pnlFormTec.add(spnAniosExp);

		JPanel pnlFormObrero = new JPanel();
		pnlFormObrero.setBackground(Color.WHITE);
		jtpFormacion.addTab("New tab", null, pnlFormObrero, null);
		pnlFormObrero.setLayout(null);

		JLabel lblHabilidadesQuePosee = new JLabel("Habilidades que posee:");
		lblHabilidadesQuePosee.setHorizontalAlignment(SwingConstants.LEFT);
		lblHabilidadesQuePosee.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblHabilidadesQuePosee.setBounds(10, 0, 187, 54);
		pnlFormObrero.add(lblHabilidadesQuePosee);

		chckbxVentas = new JCheckBox("Ventas");
		chckbxVentas.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxVentas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		chckbxVentas.setBackground(Color.WHITE);
		chckbxVentas.setBounds(185, 16, 112, 25);
		pnlFormObrero.add(chckbxVentas);

		chckbxMecanica = new JCheckBox("Mec\u00E1nica");
		chckbxMecanica.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxMecanica.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		chckbxMecanica.setBackground(Color.WHITE);
		chckbxMecanica.setBounds(185, 44, 112, 25);
		pnlFormObrero.add(chckbxMecanica);

		chckbxConduccion = new JCheckBox("Conducci\u00F3n");
		chckbxConduccion.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxConduccion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		chckbxConduccion.setBackground(Color.WHITE);
		chckbxConduccion.setBounds(553, 16, 135, 25);
		pnlFormObrero.add(chckbxConduccion);

		chckbxElectricidad = new JCheckBox("Electricidad");
		chckbxElectricidad.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxElectricidad.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		chckbxElectricidad.setBackground(Color.WHITE);
		chckbxElectricidad.setBounds(298, 44, 112, 25);
		pnlFormObrero.add(chckbxElectricidad);

		chckbxOfimatica = new JCheckBox("Ofim\u00E1tica");
		chckbxOfimatica.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxOfimatica.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		chckbxOfimatica.setBackground(Color.WHITE);
		chckbxOfimatica.setBounds(298, 16, 112, 25);
		pnlFormObrero.add(chckbxOfimatica);

		chckbxMantenimiento = new JCheckBox("Mantenimiento");
		chckbxMantenimiento.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxMantenimiento.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		chckbxMantenimiento.setBackground(Color.WHITE);
		chckbxMantenimiento.setBounds(414, 44, 135, 25);
		pnlFormObrero.add(chckbxMantenimiento);

		chckbxSeguridad = new JCheckBox("Seguridad");
		chckbxSeguridad.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxSeguridad.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		chckbxSeguridad.setBackground(Color.WHITE);
		chckbxSeguridad.setBounds(414, 16, 135, 25);
		pnlFormObrero.add(chckbxSeguridad);

		chckbxLimpieza = new JCheckBox("Limpieza");
		chckbxLimpieza.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxLimpieza.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		chckbxLimpieza.setBackground(Color.WHITE);
		chckbxLimpieza.setBounds(553, 44, 135, 25);
		pnlFormObrero.add(chckbxLimpieza);

		JPanel pnlRegistroEmpresas = new JPanel();
		pnlRegistroEmpresas.setBackground(Color.WHITE);
		jtpSecciones.addTab("New tab", null, pnlRegistroEmpresas, null);
		pnlRegistroEmpresas.setLayout(null);

		JLabel iconRegresarRegEmp = new JLabel("");
		iconRegresarRegEmp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtpSecciones.setSelectedIndex(1);
			}
		});
		iconRegresarRegEmp.setIcon(new ImageIcon(LoginUsuarios.class.getResource("/img/flechaRegresar.png")));
		iconRegresarRegEmp.setHorizontalAlignment(SwingConstants.CENTER);
		iconRegresarRegEmp.setBounds(701, 13, 98, 76);
		pnlRegistroEmpresas.add(iconRegresarRegEmp);

		JLabel iconLaboreaRegEmp = new JLabel("");
		iconLaboreaRegEmp.setIcon(new ImageIcon(LoginUsuarios.class.getResource("/img/Laborea.png")));
		iconLaboreaRegEmp.setHorizontalAlignment(SwingConstants.CENTER);
		iconLaboreaRegEmp.setBounds(12, 13, 150, 84);
		pnlRegistroEmpresas.add(iconLaboreaRegEmp);

		JPanel btnRegEmpresa = new JPanel() {
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
		btnRegEmpresa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (validarDatosEmp()) {
					Usuario nuevaEmpresa = null;

					String codigoEmpresa = Bolsa.getInstancia().generarCodigoUsuario();
					String nombre = txtNombre.getText();
					char[] passwdChars = txtRegPasswd.getPassword();
					passwd = new String(passwdChars);
					String telefono = txtTelefono.getText();
					String correo = txtRegCorreo.getText();
					String provincia = cmbProvincia.getSelectedItem().toString();
					String municipio = txtMunicipio.getText();
					String direccion = txtDireccion.getText();
					String rnc = txtRNC.getText();
					String tipoEmpresa = cmbTipoEmpresa.getSelectedItem().toString();
					String sector = txtSector.getText();

					nuevaEmpresa = new Empresa(codigoEmpresa, nombre, passwd, telefono, correo, provincia, municipio,
							direccion, true, rnc, tipoEmpresa, sector);

					Bolsa.getInstancia().insertarUsuario(nuevaEmpresa);
					JOptionPane.showMessageDialog(null,
							"¡Se ha registrado satisfactoriamente! Regrese a la pantalla principal e inicie sesión.",
							"Información", JOptionPane.INFORMATION_MESSAGE, null);
					limpiarRegEmpresa();
					limpiarRegCand();
					jtpSecciones.setSelectedIndex(0);
				} else {
					JOptionPane.showMessageDialog(null,
							"Todos los datos deben ser proporcionados antes de efectuar el registro. Verique la información",
							"Advertencia", JOptionPane.WARNING_MESSAGE, null);
				}
			}
		});
		btnRegEmpresa.setOpaque(false);
		btnRegEmpresa.setBackground(new Color(60, 179, 113));
		btnRegEmpresa.setBounds(609, 641, 175, 60);
		pnlRegistroEmpresas.add(btnRegEmpresa);
		btnRegEmpresa.setLayout(null);

		JLabel lblRegEmpresa = new JLabel("Registrar");
		lblRegEmpresa.setBackground(Color.WHITE);
		lblRegEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegEmpresa.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblRegEmpresa.setBounds(65, 13, 85, 37);
		btnRegEmpresa.add(lblRegEmpresa);

		JLabel iconRegEmpresa = new JLabel("");
		iconRegEmpresa.setBackground(Color.WHITE);
		iconRegEmpresa.setIcon(new ImageIcon(LoginUsuarios.class.getResource("/img/iconAddCompany.png")));
		iconRegEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		iconRegEmpresa.setBounds(24, 13, 43, 37);
		btnRegEmpresa.add(iconRegEmpresa);

		JLabel lblRNC = new JLabel("RNC:");
		lblRNC.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblRNC.setHorizontalAlignment(SwingConstants.LEFT);
		lblRNC.setBounds(180, 225, 174, 34);
		pnlRegistroEmpresas.add(lblRNC);

		txtRNC = new JTextField();
		txtRNC.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtRNC.setBounds(180, 257, 174, 34);
		pnlRegistroEmpresas.add(txtRNC);
		txtRNC.setColumns(10);

		JLabel lblTipoEmpresa = new JLabel("Tipo de empresa:");
		lblTipoEmpresa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTipoEmpresa.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipoEmpresa.setBounds(441, 225, 174, 34);
		pnlRegistroEmpresas.add(lblTipoEmpresa);

		cmbTipoEmpresa = new JComboBox();
		cmbTipoEmpresa.setBackground(Color.WHITE);
		cmbTipoEmpresa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cmbTipoEmpresa.setModel(new DefaultComboBoxModel(new String[] { "<< Seleccione >>", "Agroindustrial",
				"Agropecuaria", "Alimenticia", "Artesanal", "Automotriz", "Aviaci\u00F3n", "Biotecnol\u00F3gica",
				"Cinematogr\u00E1fica", "Comercial", "Comunicaciones", "Constructora", "Consultora", "Cosm\u00E9tica",
				"De Energ\u00EDas Renovables", "De Exportaci\u00F3n e Importaci\u00F3n", "De Log\u00EDstica",
				"De Marketing y Publicidad", "De Medios de Comunicaci\u00F3n", "De Miner\u00EDa", "De Moda y Textil",
				"De Reciclaje", "De Seguridad", "De Servicios Financieros", "De Software", "De Telecomunicaciones",
				"De Transporte", "De Turismo", "De Videojuegos", "Editorial", "Educativa", "Electrodom\u00E9sticos",
				"Electr\u00F3nica", "Energ\u00E9tica", "Farmac\u00E9utica", "Financiera", "Gastron\u00F3mica",
				"Hospitalaria", "Inmobiliaria", "Instituci\u00F3n Bancaria", "Instituci\u00F3n de Seguros",
				"Metal\u00FArgica", "Naviera", "Petrolera", "Qu\u00EDmica", "Retail", "Salud y Bienestar",
				"Sider\u00FArgica", "Tecnol\u00F3gica", "Textil" }));
		cmbTipoEmpresa.setBounds(441, 257, 174, 34);
		pnlRegistroEmpresas.add(cmbTipoEmpresa);

		JLabel lblRegEmpMensaje = new JLabel("Registrate como empresa y p\u00FAblica ofertas de trabajo");
		lblRegEmpMensaje.setBackground(Color.WHITE);
		lblRegEmpMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegEmpMensaje.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblRegEmpMensaje.setBounds(12, 131, 787, 40);
		pnlRegistroEmpresas.add(lblRegEmpMensaje);

		JSeparator sptRegEmpresa = new JSeparator();
		sptRegEmpresa.setBounds(135, 172, 540, 16);
		pnlRegistroEmpresas.add(sptRegEmpresa);

		JLabel lblSector = new JLabel("Sector empresarial:");
		lblSector.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblSector.setHorizontalAlignment(SwingConstants.LEFT);
		lblSector.setBounds(180, 332, 174, 34);
		pnlRegistroEmpresas.add(lblSector);

		txtSector = new JTextField();
		txtSector.setHorizontalAlignment(SwingConstants.LEFT);
		txtSector.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSector.setBounds(180, 369, 174, 34);
		pnlRegistroEmpresas.add(txtSector);
		txtSector.setColumns(10);
	}

	private boolean confirmarPasswd(String passwd, String passwdC) {
		boolean valido = false;
		if (passwd.equalsIgnoreCase(passwdC)) {
			valido = true;
		}
		return valido;
	}

	private boolean validarDatosUsuario() {
		boolean valido = true;

		if (txtNombre.getText().trim().isEmpty() || passwd.trim().isEmpty() || passwdC.trim().isEmpty()
				|| txtTelefono.getText().trim().isEmpty() || txtRegCorreo.getText().trim().isEmpty()
				|| cmbProvincia.getSelectedIndex() == 0 || txtMunicipio.getText().trim().isEmpty()
				|| txtDireccion.getText().trim().isEmpty()) {
			valido = false;
		}
		return valido;
	}

	private boolean validarDatosCand() {
		boolean valido = true;

		if (txtApellido.getText().trim().isEmpty() || txtCedula.getText().trim().isEmpty()
				|| (!rdbtnSexoF.isSelected() && !rdbtnSexoM.isSelected())
				|| (!rdbtnUniversitario.isSelected() && !rdbtnTecnico.isSelected() && !rdbtnObrero.isSelected())) {
			valido = false;
		} else if (rdbtnUniversitario.isSelected()) {
			if (cmbCarreras.getSelectedIndex() == 0) {
				valido = false;
			}
		} else if (rdbtnTecnico.isSelected()) {
			if (txtTecnico.getText().trim().isEmpty()) {
				valido = false;
			}
		} else if (rdbtnObrero.isSelected()) {
			if (!chckbxConduccion.isSelected() && !chckbxElectricidad.isSelected() && !chckbxLimpieza.isSelected()
					&& !chckbxMantenimiento.isSelected() && !chckbxMecanica.isSelected()
					&& !chckbxOfimatica.isSelected() && !chckbxSeguridad.isSelected() && !chckbxVentas.isSelected()) {
				valido = false;
			}
		}

		return valido;
	}

	private boolean validarEdad(Date fechaNacimiento) {
		boolean validarEdad = true;
		LocalDate fechaNac = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		LocalDate fechaActual = LocalDate.now();

		int edad = Period.between(fechaNac, fechaActual).getYears();

		if (edad < 17) {
			validarEdad = false;
		}

		return validarEdad;
	}

	private boolean validarDatosEmp() {
		boolean valido = true;

		if (txtRNC.getText().trim().isEmpty() || cmbTipoEmpresa.getSelectedIndex() == 0
				|| txtSector.getText().trim().isEmpty()) {
			valido = false;
		}

		return valido;
	}

	private void limpiarInicioSesion() {
		txtCorreo.setText("");
		txtPasswd.setText("");
	}

	private void limpiarRegUsuario() {
		txtNombre.setText("");
		txtTelefono.setText("");
		txtRegCorreo.setText("");
		cmbProvincia.setSelectedIndex(0);
		txtMunicipio.setText("");
		txtDireccion.setText("");
		txtRegPasswd.setText("");
		txtValidarPasswd.setText("");
	}

	private void limpiarRegCand() {
		txtApellido.setText("");
		txtCedula.setText("");
		rdbtnSexoM.setSelected(false);
		rdbtnSexoF.setSelected(false);
		txtCedula.setText("");
		spnFechaNac.setEditor(de_spnFechaNac);
		rdbtnUniversitario.setSelected(true);
		rdbtnTecnico.setSelected(false);
		rdbtnObrero.setSelected(false);
		cmbCarreras.setSelectedIndex(0);
		txtTecnico.setText("");
		spnAniosExp.setValue(0);
		chckbxVentas.setSelected(false);
		chckbxMecanica.setSelected(false);
		chckbxOfimatica.setSelected(false);
		chckbxElectricidad.setSelected(false);
		chckbxSeguridad.setSelected(false);
		chckbxMantenimiento.setSelected(false);
		chckbxConduccion.setSelected(false);
		chckbxLimpieza.setSelected(false);
	}

	private void limpiarRegEmpresa() {
		txtRNC.setText("");
		cmbTipoEmpresa.setSelectedIndex(0);
		txtSector.setText("");
	}
}
