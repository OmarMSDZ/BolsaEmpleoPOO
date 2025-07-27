package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Bolsa;
import logica.Empresa;
import logica.Oferta;
import logica.Persona;
import logica.Solicitud;
import logica.Universitario;
import logica.Usuario;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuCandidatos extends JDialog {

	private JPanel contentPane;
	private Dimension dim;
	private JLabel label;
	private JLabel label_1;
	private JPanel pnlOpciones;
	private JLabel lblSalarioSolicitud;
	private JLabel lblTipoEmpleoSolicitud;
	private JLabel lblModalidadSolicitud;
	private JLabel lblLicConducirSolicitud;
	private JLabel lblMovilizarseSolicitud;
	private JTabbedPane jtpDescripcionSolicitud;
	private JLabel lblHorariosSolicitud;

	// Solicitud seleccionada
	private static Solicitud solicitudSelected = null;
	private static Persona persActual = null;

	private JTabbedPane jtpMenus;
	private JRadioButton rdbtnLicenciaNo;
	private JRadioButton rdbtnLicenciaSi;
	private JRadioButton rdbtnMovilidadNo;
	private JRadioButton rdbtnMovilidadSi;
	private JComboBox cbxAreaSolicitud;
	private JLabel lblInfNombreCompleto;
	private JLabel lblInfCedula;
	private JLabel lblInfSexo;
	private JLabel lblInfFechaNacimiento;
	private JLabel lblInfNumeroSolicitudes;
	private JLabel lblInfEstado;
	private JComboBox cbxHorarioSolicitud;
	private JComboBox cbxTipoEmpleo;
	private JComboBox cbxModalidad;
	private JSpinner spnSalarioDeseado;
	private JPanel ContenedorSolicitudes;
	private JLabel lblTituloSolicitud;
	private JLabel lblSolicitanteSolicitud;
	private JLabel lblAreaSolicitud;
	private JLabel lblFechaSolicitud;
	private JLabel lblEstadoSolicitud;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { MenuCandidatos frame = new
	 * MenuCandidatos(); frame.setVisible(true);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public MenuCandidatos(Window parent) {
		
		super(parent, "", ModalityType.APPLICATION_MODAL);
		setResizable(false);


		Persona actual = (Persona) Bolsa.getUsuarioActivo();
		if (actual != null) {
			persActual = actual;
			setTitle("Laborea - ¡Bienvenido " + actual.getNombre() + " " + actual.getApellidos() + "!");
		} else {
			// Para pruebas
			persActual = new Universitario("U-1", "Omar Jadis", "1234", "8091231234", "omarM@gmail.com", "Santiago",
					"Santiago", "Su casa", true, "Morales Diaz", "M", new Date(), "40215233418", false,
					"Ingeniería en Sistemas Computacionales");
			Bolsa.getInstancia().insertarUsuario(persActual);
			Bolsa.setUsuarioActivo(persActual); // insertar y establecer como activo para pruebas
			setTitle("Laborea - Pruebas menú candidatos");
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		addWindowListener(new WindowAdapter() {
//		    @Override
//		    public void windowClosing(WindowEvent e) {
//		        try {
//		            Bolsa.guardarEstado();
//		            System.out.println("Datos guardados al cerrar menu candidatos");
//		        } catch (Exception ex) {
//		            ex.printStackTrace();
//		        }
//		    }
//		});

		setBounds(100, 100, 1100, 687);

		// Poner ventana en centro de pantalla y tamaño máximo
		dim = getToolkit().getScreenSize(); // Obtener dimensiones de la pantalla de la pc
		setSize(1920, 1075);
		setLocationRelativeTo(null);

		

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(250, 120, 19, 915);
		contentPane.add(panel_3);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(257, 1031, 1637, 10);
		contentPane.add(panel_2);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(1885, 64, 19, 971);
		contentPane.add(panel);

		JLabel lblMostrarNombreDe = new JLabel("Nombre");
		lblMostrarNombreDe.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMostrarNombreDe.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMostrarNombreDe.setBounds(1442, 27, 340, 74);
		contentPane.add(lblMostrarNombreDe);

		label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// abrir menu admin perfil candidato
				if (persActual != null) {

					VisualizarPerfilCandidato vpc = new VisualizarPerfilCandidato();
 
					vpc.setModal(true);
					vpc.setVisible(true);

				}
			}
		});
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/user.png")));
		label.setBounds(1792, 27, 102, 74);
		contentPane.add(label);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(250, 64, 1644, 59);
		contentPane.add(panel_1);

		// mostrar nombre completo persona en la esquina de la pantalla
		if (persActual != null) {
			lblMostrarNombreDe.setText(persActual.getNombre() + " " + persActual.getApellidos());
		}

		label_1 = new JLabel("");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/Laborea.png")));
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		label_1.setBounds(12, 13, 199, 59);
		contentPane.add(label_1);

		// hacer que el panel tenga bordes redondeados, usando paintcomponent
		pnlOpciones = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				// Color de fondo
				g2.setColor(getBackground());

				// Dibuja un rect�ngulo redondeado (x, y, width, height, arcWidth, arcHeight)
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 80, 80);
			}
		};
		pnlOpciones.setOpaque(false);
		pnlOpciones.setBackground(new Color(45, 45, 60));
		pnlOpciones.setBounds(-31, 120, 278, 948);
		contentPane.add(pnlOpciones);
		pnlOpciones.setLayout(null);

		JPanel pnlBtnCrearSolicitudes = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				// Color de fondo
				g2.setColor(getBackground());

				// Dibuja un rect�ngulo redondeado (x, y, width, height, arcWidth, arcHeight)
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 80, 80);
			}
		};
		pnlBtnCrearSolicitudes.setOpaque(false);

		pnlBtnCrearSolicitudes.setBackground(new Color(100, 110, 130));
		pnlBtnCrearSolicitudes.setBounds(0, 62, 268, 80);
		pnlOpciones.add(pnlBtnCrearSolicitudes);
		pnlBtnCrearSolicitudes.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Crear Solicitud");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtpMenus.setSelectedIndex(0);// volver a ofertas
			}
		});
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(22, 11, 236, 58);
		pnlBtnCrearSolicitudes.add(lblNewLabel_3);

		JPanel pnlBtnSolicitudes = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				// Color de fondo
				g2.setColor(getBackground());

				// Dibuja un rect�ngulo redondeado (x, y, width, height, arcWidth, arcHeight)
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 80, 80);
			}
		};

		pnlBtnSolicitudes.setOpaque(false);
		pnlBtnSolicitudes.setBackground(new Color(100, 110, 130));
		pnlBtnSolicitudes.setLayout(null);
		pnlBtnSolicitudes.setBounds(0, 169, 268, 80);
		pnlOpciones.add(pnlBtnSolicitudes);

		JLabel lblMisSolicitudes = new JLabel("Mis Solicitudes");
		lblMisSolicitudes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtpMenus.setSelectedIndex(1);// ir a mis solicitudes
			}
		});
		lblMisSolicitudes.setForeground(Color.WHITE);
		lblMisSolicitudes.setHorizontalAlignment(SwingConstants.CENTER);
		lblMisSolicitudes.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMisSolicitudes.setBounds(22, 11, 236, 58);
		pnlBtnSolicitudes.add(lblMisSolicitudes);

		JPanel pnlSalir = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				// Color de fondo
				g2.setColor(getBackground());

				// Dibuja un rect�ngulo redondeado (x, y, width, height, arcWidth, arcHeight)
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 80, 80);
			}
		};
		pnlSalir.setOpaque(false);
		pnlSalir.setBackground(new Color(100, 110, 130));
		pnlSalir.setLayout(null);
		pnlSalir.setBounds(0, 791, 268, 80);
		pnlOpciones.add(pnlSalir);

		JLabel lblSalir = new JLabel("Salir");
		lblSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Bolsa.setUsuarioActivo(null); // quitar usuario activo de la clase
				dispose(); // cerrar ventana
			}
		});
		lblSalir.setForeground(Color.WHITE);
		lblSalir.setIcon(null);
		lblSalir.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalir.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblSalir.setBounds(22, 11, 236, 58);
		pnlSalir.add(lblSalir);

		jtpMenus = new JTabbedPane(JTabbedPane.TOP);
		jtpMenus.setEnabled(false);
		jtpMenus.setBounds(260, 93, 1634, 948);
		contentPane.add(jtpMenus);

		JPanel pnlSolicitudes = new JPanel();
		pnlSolicitudes.setBackground(Color.WHITE);
		jtpMenus.addTab("New tab", null, pnlSolicitudes, null);
		pnlSolicitudes.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Crear solicitud");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		lblNewLabel_4.setBounds(10, 11, 260, 51);
		pnlSolicitudes.add(lblNewLabel_4);

		JLabel lblHorarioDeseado = new JLabel("Horario");
		lblHorarioDeseado.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblHorarioDeseado.setBounds(782, 338, 260, 51);
		pnlSolicitudes.add(lblHorarioDeseado);

		JLabel lblTipoDeEmpleo = new JLabel("Tipo de empleo");
		lblTipoDeEmpleo.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblTipoDeEmpleo.setBounds(782, 164, 260, 51);
		pnlSolicitudes.add(lblTipoDeEmpleo);

		JLabel lblModalidadDeseada = new JLabel("Modalidad");
		lblModalidadDeseada.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblModalidadDeseada.setBounds(1242, 168, 260, 51);
		pnlSolicitudes.add(lblModalidadDeseada);

		JLabel lblSalarioDeseado = new JLabel("Salario deseado");
		lblSalarioDeseado.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblSalarioDeseado.setBounds(1242, 255, 260, 51);
		pnlSolicitudes.add(lblSalarioDeseado);

		JLabel lbldisponeDeLicencia = new JLabel("\u00BFDispone de licencia de conducir?");
		lbldisponeDeLicencia.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lbldisponeDeLicencia.setBounds(782, 430, 395, 51);
		pnlSolicitudes.add(lbldisponeDeLicencia);

		JLabel lblDisponibilidadDeMovilidad = new JLabel("Disponibilidad de movilidad");
		lblDisponibilidadDeMovilidad.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblDisponibilidadDeMovilidad.setBounds(1242, 338, 313, 51);
		pnlSolicitudes.add(lblDisponibilidadDeMovilidad);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(10, 54, 1609, 8);
		pnlSolicitudes.add(separator_2);

		cbxTipoEmpleo = new JComboBox();
		cbxTipoEmpleo.setModel(new DefaultComboBoxModel(
				new String[] { "<Seleccione>", "Tiempo completo", "Tiempo Parcial", "Temporal", "Freelance" }));
		cbxTipoEmpleo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		cbxTipoEmpleo.setBackground(Color.WHITE);
		cbxTipoEmpleo.setBounds(782, 210, 395, 39);
		pnlSolicitudes.add(cbxTipoEmpleo);

		cbxModalidad = new JComboBox();
		cbxModalidad
				.setModel(new DefaultComboBoxModel(new String[] { "<Seleccione>", "Presencial", "Remoto", "Mixto" }));
		cbxModalidad.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		cbxModalidad.setBackground(Color.WHITE);
		cbxModalidad.setBounds(1242, 214, 313, 39);
		pnlSolicitudes.add(cbxModalidad);

		cbxHorarioSolicitud = new JComboBox();
		cbxHorarioSolicitud.setModel(new DefaultComboBoxModel(new String[] { "<Seleccione>", "Matutino", "Vespertino",
				"Nocturno", "Matutino y Vespertino", "Vespertino y Nocturno", "Todos" }));
		cbxHorarioSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		cbxHorarioSolicitud.setBackground(Color.WHITE);
		cbxHorarioSolicitud.setBounds(782, 387, 395, 39);
		pnlSolicitudes.add(cbxHorarioSolicitud);

		spnSalarioDeseado = new JSpinner();
		spnSalarioDeseado.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		spnSalarioDeseado.setBackground(Color.WHITE);
		spnSalarioDeseado.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spnSalarioDeseado.setBounds(1242, 302, 313, 39);
		pnlSolicitudes.add(spnSalarioDeseado);

		rdbtnLicenciaSi = new JRadioButton("Si");
		rdbtnLicenciaSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnLicenciaSi.setSelected(true);
				rdbtnLicenciaNo.setSelected(false);

			}
		});
		rdbtnLicenciaSi.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		rdbtnLicenciaSi.setBackground(Color.WHITE);
		rdbtnLicenciaSi.setBounds(782, 488, 109, 23);
		pnlSolicitudes.add(rdbtnLicenciaSi);

		rdbtnLicenciaNo = new JRadioButton("No");
		rdbtnLicenciaNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnLicenciaSi.setSelected(false);
				rdbtnLicenciaNo.setSelected(true);
			}
		});
		rdbtnLicenciaNo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		rdbtnLicenciaNo.setBackground(Color.WHITE);
		rdbtnLicenciaNo.setBounds(944, 488, 109, 23);
		pnlSolicitudes.add(rdbtnLicenciaNo);

		rdbtnMovilidadSi = new JRadioButton("Puedo movilizarme/viajar");
		rdbtnMovilidadSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnMovilidadSi.setSelected(true);
				rdbtnMovilidadNo.setSelected(false);
			}
		});
		rdbtnMovilidadSi.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		rdbtnMovilidadSi.setBackground(Color.WHITE);
		rdbtnMovilidadSi.setBounds(1242, 387, 260, 23);
		pnlSolicitudes.add(rdbtnMovilidadSi);

		rdbtnMovilidadNo = new JRadioButton("No puedo movilizarme/viajar");
		rdbtnMovilidadNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnMovilidadSi.setSelected(false);
				rdbtnMovilidadNo.setSelected(true);
			}
		});
		rdbtnMovilidadNo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		rdbtnMovilidadNo.setBackground(Color.WHITE);
		rdbtnMovilidadNo.setBounds(1242, 421, 260, 23);
		pnlSolicitudes.add(rdbtnMovilidadNo);

		JLabel lblDatosSolicitante = new JLabel("Datos Solicitante");
		lblDatosSolicitante.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblDatosSolicitante.setBounds(10, 97, 260, 51);
		pnlSolicitudes.add(lblDatosSolicitante);

		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.BLACK);
		separator_3.setBounds(10, 145, 650, 8);
		pnlSolicitudes.add(separator_3);

		JLabel lblNombreCompleto = new JLabel("Nombre completo:");
		lblNombreCompleto.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreCompleto.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblNombreCompleto.setBounds(10, 168, 260, 51);
		pnlSolicitudes.add(lblNombreCompleto);

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setHorizontalAlignment(SwingConstants.LEFT);
		lblSexo.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblSexo.setBounds(10, 292, 260, 51);
		pnlSolicitudes.add(lblSexo);

		JLabel lblFechaNacimiento = new JLabel("Fecha nacimiento:");
		lblFechaNacimiento.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaNacimiento.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblFechaNacimiento.setBounds(10, 358, 260, 51);
		pnlSolicitudes.add(lblFechaNacimiento);

		JLabel lblCedula = new JLabel("C\u00E9dula:");
		lblCedula.setHorizontalAlignment(SwingConstants.LEFT);
		lblCedula.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblCedula.setBounds(10, 230, 260, 51);
		pnlSolicitudes.add(lblCedula);

		JLabel lblnumsoli = new JLabel("N\u00FAmero solicitudes:");
		lblnumsoli.setHorizontalAlignment(SwingConstants.LEFT);
		lblnumsoli.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblnumsoli.setBounds(10, 429, 260, 51);
		pnlSolicitudes.add(lblnumsoli);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.LEFT);
		lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblEstado.setBounds(10, 501, 260, 51);
		pnlSolicitudes.add(lblEstado);

		JLabel lblDatosSolicitud = new JLabel("Datos Solicitud");
		lblDatosSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblDatosSolicitud.setBounds(782, 97, 260, 51);
		pnlSolicitudes.add(lblDatosSolicitud);

		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.BLACK);
		separator_4.setBounds(658, 145, 658, 8);
		pnlSolicitudes.add(separator_4);

		lblInfNombreCompleto = new JLabel("Nombre Completo");
		lblInfNombreCompleto.setHorizontalAlignment(SwingConstants.LEFT);
		lblInfNombreCompleto.setForeground(Color.DARK_GRAY);
		lblInfNombreCompleto.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblInfNombreCompleto.setBounds(247, 168, 468, 51);
		pnlSolicitudes.add(lblInfNombreCompleto);

		lblInfCedula = new JLabel("Cedula");
		lblInfCedula.setHorizontalAlignment(SwingConstants.LEFT);
		lblInfCedula.setForeground(Color.DARK_GRAY);
		lblInfCedula.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblInfCedula.setBounds(247, 230, 468, 51);
		pnlSolicitudes.add(lblInfCedula);

		lblInfSexo = new JLabel("Sexo");
		lblInfSexo.setHorizontalAlignment(SwingConstants.LEFT);
		lblInfSexo.setForeground(Color.DARK_GRAY);
		lblInfSexo.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblInfSexo.setBounds(247, 292, 468, 51);
		pnlSolicitudes.add(lblInfSexo);

		lblInfFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblInfFechaNacimiento.setHorizontalAlignment(SwingConstants.LEFT);
		lblInfFechaNacimiento.setForeground(Color.DARK_GRAY);
		lblInfFechaNacimiento.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblInfFechaNacimiento.setBounds(247, 358, 468, 51);
		pnlSolicitudes.add(lblInfFechaNacimiento);

		lblInfNumeroSolicitudes = new JLabel("N\u00FAmero Solicitudes");
		lblInfNumeroSolicitudes.setHorizontalAlignment(SwingConstants.LEFT);
		lblInfNumeroSolicitudes.setForeground(Color.DARK_GRAY);
		lblInfNumeroSolicitudes.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblInfNumeroSolicitudes.setBounds(247, 429, 468, 51);
		pnlSolicitudes.add(lblInfNumeroSolicitudes);

		lblInfEstado = new JLabel("Estado");
		lblInfEstado.setHorizontalAlignment(SwingConstants.LEFT);
		lblInfEstado.setForeground(Color.DARK_GRAY);
		lblInfEstado.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblInfEstado.setBounds(247, 501, 468, 51);
		pnlSolicitudes.add(lblInfEstado);

		// cargar datos de persona al iniciar esta pantalla
		cargarDatosPersona();

		JButton btnRegistrarSolicitud = new JButton("Registrar solicitud");
		btnRegistrarSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (persActual != null) {
					if (validar()) {

						// registrar solicitud
						String codigoGenerado = Bolsa.getInstancia().generarCodigoSolicitud();
						String dispHorario = cbxHorarioSolicitud.getSelectedItem().toString();
						String tipoEmpleo = cbxTipoEmpleo.getSelectedItem().toString();
						String modalidad = cbxModalidad.getSelectedItem().toString();
						String area = cbxAreaSolicitud.getSelectedItem().toString();
						boolean dispMovilidad = false;
						if (rdbtnMovilidadSi.isSelected()) {
							dispMovilidad = true;
						}

						boolean licencia = false;
						if (rdbtnLicenciaSi.isSelected()) {
							licencia = true;
						}
						float salarioEsperado = (Float) spnSalarioDeseado.getValue();

						Solicitud soli = new Solicitud(codigoGenerado, persActual, dispHorario, dispMovilidad, licencia,
								tipoEmpleo, modalidad, area, salarioEsperado, new Date(), "ACTIVA");
						Bolsa.getInstancia().insertarSolicitud(soli);
						JOptionPane.showMessageDialog(null, "¡Solicitud procesada con éxito!", "Informaci�n",
								JOptionPane.INFORMATION_MESSAGE);
						clear();
					} else {
						JOptionPane.showMessageDialog(null, "¡Complete los campos correctamente!", "Alerta",
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "¡No hay usuario activo en esta vista!", "Alerta",
							JOptionPane.WARNING_MESSAGE);

				}
			}

		});
		btnRegistrarSolicitud.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnRegistrarSolicitud.setBackground(Color.WHITE);
		btnRegistrarSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnRegistrarSolicitud.setBounds(1242, 808, 313, 66);
		pnlSolicitudes.add(btnRegistrarSolicitud);

		JLabel lblObtenidoAutomticamente = new JLabel("* Completado automáticamente *");
		lblObtenidoAutomticamente.setHorizontalAlignment(SwingConstants.CENTER);
		lblObtenidoAutomticamente.setForeground(Color.GRAY);
		lblObtenidoAutomticamente.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblObtenidoAutomticamente.setBounds(312, 97, 232, 51);
		pnlSolicitudes.add(lblObtenidoAutomticamente);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(692, 145, 23, 764);
		pnlSolicitudes.add(separator_1);

		JLabel lblArea = new JLabel("\u00C1rea");
		lblArea.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblArea.setBounds(782, 255, 260, 51);
		pnlSolicitudes.add(lblArea);

		cbxAreaSolicitud = new JComboBox();
		cbxAreaSolicitud.setModel(new DefaultComboBoxModel(new String[] { "<< Seleccione >>",
				"Tecnolog\u00EDa / Desarrollo de Software", "Marketing y Publicidad", "Ventas y Comercio",
				"Administraci\u00F3n / Oficina", "Recursos Humanos", "Finanzas / Contabilidad",
				"Log\u00EDstica y Distribuci\u00F3n", "Ingenier\u00EDa", "Salud / Medicina",
				"Educaci\u00F3n / Capacitaci\u00F3n", "Atenci\u00F3n al Cliente / Call Center",
				"Dise\u00F1o Gr\u00E1fico / UX/UI", "Legal / Jur\u00EDdico", "Producci\u00F3n / Manufactura",
				"Turismo y Hoteler\u00EDa", "Construcci\u00F3n / Arquitectura", "Investigaci\u00F3n y Desarrollo",
				"Servicios Generales / Mantenimiento", "Compras y Abastecimiento", "Calidad / Seguridad Industrial" }));
		cbxAreaSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		cbxAreaSolicitud.setBackground(Color.WHITE);
		cbxAreaSolicitud.setBounds(782, 304, 395, 39);
		pnlSolicitudes.add(cbxAreaSolicitud);

		JPanel pnlMisSolicitudes = new JPanel();
		pnlMisSolicitudes.setBackground(Color.WHITE);
		jtpMenus.addTab("New tab", null, pnlMisSolicitudes, null);
		pnlMisSolicitudes.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 11, 339, 895);
		pnlMisSolicitudes.add(scrollPane);

		ContenedorSolicitudes = new JPanel();
		ContenedorSolicitudes.setBackground(Color.WHITE);
		scrollPane.setViewportView(ContenedorSolicitudes);
		ContenedorSolicitudes.setLayout(new BoxLayout(ContenedorSolicitudes, BoxLayout.Y_AXIS)); // para que muestre los
		// elementos vertical
		cargarSolicitudes();

		JPanel pnlDescSolicitudes = new JPanel();
		pnlDescSolicitudes.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlDescSolicitudes.setBackground(Color.WHITE);
		pnlDescSolicitudes.setBounds(359, 11, 1263, 895);
		pnlMisSolicitudes.add(pnlDescSolicitudes);
		pnlDescSolicitudes.setLayout(null);

		jtpDescripcionSolicitud = new JTabbedPane(JTabbedPane.TOP);
		jtpDescripcionSolicitud.setBorder(null);
		jtpDescripcionSolicitud.setBounds(0, -32, 1276, 927);
		pnlDescSolicitudes.add(jtpDescripcionSolicitud);

		JPanel pnlMensajeSolicitud = new JPanel();
		pnlMensajeSolicitud.setBackground(Color.WHITE);
		jtpDescripcionSolicitud.addTab("New tab", null, pnlMensajeSolicitud, null);
		pnlMensajeSolicitud.setLayout(null);

		JLabel lblNewLabel = new JLabel("Seleccione una solicitud para ver sus detalles");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		lblNewLabel.setBounds(0, 348, 1238, 113);
		pnlMensajeSolicitud.add(lblNewLabel);

		JPanel pnlVistaSolicitud = new JPanel();
		pnlVistaSolicitud.setBackground(Color.WHITE);
		jtpDescripcionSolicitud.addTab("New tab", null, pnlVistaSolicitud, null);
		pnlVistaSolicitud.setLayout(null);

		lblTituloSolicitud = new JLabel("Solicitud # - NombrePersona");
		lblTituloSolicitud.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 26));
		lblTituloSolicitud.setBounds(10, 11, 1047, 61);
		pnlVistaSolicitud.add(lblTituloSolicitud);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(10, 83, 1248, 13);
		pnlVistaSolicitud.add(separator);

		lblSalarioSolicitud = new JLabel("SalarioSolicitud");
		lblSalarioSolicitud.setForeground(Color.DARK_GRAY);
		lblSalarioSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblSalarioSolicitud.setBounds(230, 199, 480, 47);
		pnlVistaSolicitud.add(lblSalarioSolicitud);

		lblTipoEmpleoSolicitud = new JLabel("TipoEmpleoSolicitud");
		lblTipoEmpleoSolicitud.setForeground(Color.DARK_GRAY);
		lblTipoEmpleoSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblTipoEmpleoSolicitud.setBounds(230, 249, 480, 47);
		pnlVistaSolicitud.add(lblTipoEmpleoSolicitud);

		lblModalidadSolicitud = new JLabel("ModalidadSolicitud");
		lblModalidadSolicitud.setForeground(Color.DARK_GRAY);
		lblModalidadSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblModalidadSolicitud.setBounds(230, 304, 480, 47);
		pnlVistaSolicitud.add(lblModalidadSolicitud);

		JLabel lblLicenciaDeConducir = new JLabel("Licencia de conducir:");
		lblLicenciaDeConducir.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/driving-licence.png")));
		lblLicenciaDeConducir.setForeground(Color.BLACK);
		lblLicenciaDeConducir.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblLicenciaDeConducir.setBounds(10, 432, 210, 47);
		pnlVistaSolicitud.add(lblLicenciaDeConducir);

		lblLicConducirSolicitud = new JLabel("LicConducirSolicitud");
		lblLicConducirSolicitud.setForeground(Color.DARK_GRAY);
		lblLicConducirSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblLicConducirSolicitud.setBounds(230, 432, 480, 47);
		pnlVistaSolicitud.add(lblLicConducirSolicitud);

		JLabel lblDisponibilidadDeMovilizarse = new JLabel("Disp. de movilizarse:");
		lblDisponibilidadDeMovilizarse.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/position.png")));
		lblDisponibilidadDeMovilizarse.setForeground(Color.BLACK);
		lblDisponibilidadDeMovilizarse.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblDisponibilidadDeMovilizarse.setBounds(10, 501, 210, 47);
		pnlVistaSolicitud.add(lblDisponibilidadDeMovilizarse);

		lblMovilizarseSolicitud = new JLabel("MovilizarseSolicitud");
		lblMovilizarseSolicitud.setForeground(Color.DARK_GRAY);
		lblMovilizarseSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblMovilizarseSolicitud.setBounds(230, 501, 480, 47);
		pnlVistaSolicitud.add(lblMovilizarseSolicitud);

		JLabel lblNewLabel_2 = new JLabel("Salario deseado:");
		lblNewLabel_2.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/money.png")));
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 199, 182, 40);
		pnlVistaSolicitud.add(lblNewLabel_2);

		JLabel lblTipo = new JLabel("Tipo empleo:");
		lblTipo.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/briefcase.png")));
		lblTipo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblTipo.setBounds(10, 259, 182, 27);
		pnlVistaSolicitud.add(lblTipo);

		JLabel lblModalidad = new JLabel("Modalidad:");
		lblModalidad.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/building.png")));
		lblModalidad.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblModalidad.setBounds(10, 314, 129, 27);
		pnlVistaSolicitud.add(lblModalidad);

		JLabel lblHorario = new JLabel("Horario:");
		lblHorario.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/clock.png")));
		lblHorario.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblHorario.setBounds(10, 378, 129, 27);
		pnlVistaSolicitud.add(lblHorario);

		lblHorariosSolicitud = new JLabel("HorarioSolicitud");
		lblHorariosSolicitud.setForeground(Color.DARK_GRAY);
		lblHorariosSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblHorariosSolicitud.setBounds(230, 368, 480, 47);
		pnlVistaSolicitud.add(lblHorariosSolicitud);

		JLabel btnCerrarVistaOferta = new JLabel("");
		btnCerrarVistaOferta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtpDescripcionSolicitud.setSelectedIndex(0);
				// solicitudSelected = null;
			}
		});
		btnCerrarVistaOferta.setHorizontalAlignment(SwingConstants.CENTER);
		btnCerrarVistaOferta.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/close.png")));
		btnCerrarVistaOferta.setBounds(1171, 11, 77, 73);
		pnlVistaSolicitud.add(btnCerrarVistaOferta);

		JLabel lblSolicitante = new JLabel("Solicitante:");
		lblSolicitante.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/user2.png")));
		lblSolicitante.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblSolicitante.setBounds(10, 104, 129, 27);
		pnlVistaSolicitud.add(lblSolicitante);

		lblSolicitanteSolicitud = new JLabel("SolicitanteSolicitud");
		lblSolicitanteSolicitud.setForeground(Color.DARK_GRAY);
		lblSolicitanteSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblSolicitanteSolicitud.setBounds(230, 94, 480, 47);
		pnlVistaSolicitud.add(lblSolicitanteSolicitud);

		JLabel lblFechaRealizacin = new JLabel("Fecha realizaci\u00F3n:");
		lblFechaRealizacin.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/calendar.png")));
		lblFechaRealizacin.setForeground(Color.BLACK);
		lblFechaRealizacin.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblFechaRealizacin.setBounds(10, 572, 210, 47);
		pnlVistaSolicitud.add(lblFechaRealizacin);

		lblFechaSolicitud = new JLabel("FechaSolicitud");
		lblFechaSolicitud.setForeground(Color.DARK_GRAY);
		lblFechaSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblFechaSolicitud.setBounds(230, 572, 480, 47);
		pnlVistaSolicitud.add(lblFechaSolicitud);

		JButton btnNewButton = new JButton("Notificaci\u00F3n");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/bell.png")));
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnNewButton.setBounds(856, 807, 182, 73);
		pnlVistaSolicitud.add(btnNewButton);

		JButton btnCancelarSolicitud = new JButton("Cancelar Solicitud");
		btnCancelarSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// cancelar solicitud
				int option = JOptionPane.showConfirmDialog(null, "Desea cancelar esta solicitud?", "Cancelar",
						JOptionPane.WARNING_MESSAGE);
				if (option == JOptionPane.OK_OPTION) {
					Bolsa.getInstancia().eliminarSolicitud(solicitudSelected);
					cargarSolicitudes();
					jtpDescripcionSolicitud.setSelectedIndex(0);
				}
			}
		});
		btnCancelarSolicitud.setBackground(Color.WHITE);
		btnCancelarSolicitud.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCancelarSolicitud.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCancelarSolicitud.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/close1.png")));
		btnCancelarSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnCancelarSolicitud.setBounds(1066, 807, 182, 73);
		pnlVistaSolicitud.add(btnCancelarSolicitud);

		JLabel lblEstadoSolicitud2 = new JLabel("Estado solicitud:");
		lblEstadoSolicitud2.setBackground(Color.WHITE);
		lblEstadoSolicitud2.setForeground(Color.BLACK);
		lblEstadoSolicitud2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblEstadoSolicitud2.setBounds(10, 810, 210, 47);
		pnlVistaSolicitud.add(lblEstadoSolicitud2);

		lblEstadoSolicitud = new JLabel("Estado Solicitud");
		lblEstadoSolicitud.setForeground(Color.DARK_GRAY);
		lblEstadoSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblEstadoSolicitud.setBounds(230, 810, 448, 47);
		pnlVistaSolicitud.add(lblEstadoSolicitud);

		JLabel lblActivarSoloSi = new JLabel(
				"<html>Activar btn de notificacion solo si se hace el macheo y <br>se envia la notificacion a esta solicitud, desactivar el de cancelar <br>hasta que no acepte o niegue</html>");
		lblActivarSoloSi.setForeground(new Color(255, 0, 0));
		lblActivarSoloSi.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblActivarSoloSi.setBackground(Color.WHITE);
		lblActivarSoloSi.setBounds(652, 704, 219, 142);
		pnlVistaSolicitud.add(lblActivarSoloSi);

		JLabel lblArea_1 = new JLabel("\u00C1rea:");
		lblArea_1.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/quality.png")));
		lblArea_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblArea_1.setBounds(10, 148, 182, 40);
		pnlVistaSolicitud.add(lblArea_1);

		lblAreaSolicitud = new JLabel("Area Solicitud");
		lblAreaSolicitud.setForeground(Color.DARK_GRAY);
		lblAreaSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblAreaSolicitud.setBounds(230, 145, 480, 47);
		pnlVistaSolicitud.add(lblAreaSolicitud);
	}

	private void cargarDatosPersona() {
		if (persActual != null) {

			lblInfNombreCompleto.setText(persActual.getNombre() + " " + persActual.getApellidos());

			lblInfCedula.setText(persActual.getCedula());

			if (persActual.getSexo().equalsIgnoreCase("M")) {
				lblInfSexo.setText("Masculino");
			} else if (persActual.getSexo().equalsIgnoreCase("F")) {
				lblInfSexo.setText("Femenino");
			}
			Date fechaSolicitud = persActual.getFechaNacimiento();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String formattedDate = dateFormat.format(fechaSolicitud);
			lblInfFechaNacimiento.setText(formattedDate);

			lblInfNumeroSolicitudes.setText(String.valueOf(persActual.getMisSolicitudes().size()));

			if (persActual.isEstadoEmpleado()) {
				lblInfEstado.setText("Empleado");
			} else {
				lblInfEstado.setText("Desempleado");
			}

		}
	}

	// limpiar form solicitudes
	private void clear() {
		cbxHorarioSolicitud.setSelectedIndex(0);
		cbxTipoEmpleo.setSelectedIndex(0);
		cbxModalidad.setSelectedIndex(0);
		cbxAreaSolicitud.setSelectedIndex(0);
		rdbtnMovilidadSi.setSelected(false);
		rdbtnMovilidadNo.setSelected(false);
		rdbtnLicenciaSi.setSelected(false);
		spnSalarioDeseado.setValue(0);
		cargarDatosPersona();
		cargarSolicitudes();
	}

	// validar creacion de solicitudes
	private boolean validar() {
		boolean valido = true;

		if (cbxHorarioSolicitud.getSelectedIndex() == 0 || cbxTipoEmpleo.getSelectedIndex() == 0
				|| cbxModalidad.getSelectedIndex() == 0
				|| (!rdbtnMovilidadNo.isSelected() && !rdbtnMovilidadSi.isSelected())
				|| (!rdbtnLicenciaSi.isSelected() && !rdbtnLicenciaNo.isSelected())
				|| (float) spnSalarioDeseado.getValue() == 0) {
			valido = false;
		}
		return valido;
	}

	// cargar elementos de solicitud en contenedor
	private void cargarSolicitudes() {
		if (persActual != null) {

			ContenedorSolicitudes.removeAll(); // limpiar el contenedor

			for (Solicitud aux : persActual.getMisSolicitudes()) {
				ElementoSolicitud eleSol = new ElementoSolicitud(aux);

				eleSol.addMouseListener(new java.awt.event.MouseAdapter() {
					@Override
					public void mouseClicked(java.awt.event.MouseEvent e) {
						solicitudSelected = eleSol.getSolicitud();
						cargarVistaPreviaSolicitud(solicitudSelected); // cargar datos de solicitud en vista previa
						jtpDescripcionSolicitud.setSelectedIndex(1);
					}
				});

				ContenedorSolicitudes.add(eleSol);
				ContenedorSolicitudes.add(Box.createVerticalStrut(10));
			}

			ContenedorSolicitudes.revalidate();
			ContenedorSolicitudes.repaint();
		}
	}

	private void cargarVistaPreviaSolicitud(Solicitud soli) {
		if (soli != null) {
			lblTituloSolicitud.setText("Solicitud #" + soli.getCodigo() + " " + soli.getSolicitante().getNombre() + " "
					+ soli.getSolicitante().getApellidos());
			lblSolicitanteSolicitud
					.setText(soli.getSolicitante().getNombre() + " " + soli.getSolicitante().getApellidos());
			lblAreaSolicitud.setText(soli.getArea());
			lblSalarioSolicitud.setText(String.valueOf(soli.getSalarioDeseado()));
			lblTipoEmpleoSolicitud.setText(soli.getTipoEmpleo());
			lblModalidadSolicitud.setText(soli.getModalidad());
			lblHorariosSolicitud.setText(soli.getDispHorarios());
			if (soli.isLicencia()) {
				lblLicConducirSolicitud.setText("Posee licencia de conducir");
			} else {
				lblLicConducirSolicitud.setText("No posee licencia de conducir");
			}
			if (soli.isDispMovilidad()) {
				lblMovilizarseSolicitud.setText("Puede movilizarse en caso de ser necesario");
			} else {
				lblMovilizarseSolicitud.setText("No puede movilizarse");
			}
			Date fechaSolicitud = soli.getFechaSolicitud();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = dateFormat.format(fechaSolicitud);
			lblFechaSolicitud.setText(formattedDate);
			lblEstadoSolicitud.setText(soli.getEstadoSolicitud());
		}
	}
}
