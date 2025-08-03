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
import server.Servidor;

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
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
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

public class MenuAdmins extends JDialog {

	private JPanel pnlContenido;
	private Dimension dim;
	private JLabel iconAdministrador;
	private JLabel iconLaborea;
	private JPanel pnlOpciones;

	private JTabbedPane jtpMenus;
	private JLabel lblMostrarNombreDePerfil;

	static Socket sfd = null;
	static DataInputStream EntradaSocket;
	static DataOutputStream SalidaSocket;
	private Servidor servidor;
	private JLabel lblMostrarMatcheosDisp;
	private JLabel lblMostrarContratos;
	private JLabel lblMostrarPersonas;
	private JLabel lblMostrarOfertas;
	private JLabel lblMostrarEmpresas;
	private JLabel lblMostrarUniversitario;
	private JLabel lblMostrarObrero;
	private JLabel lblMostrarSolicitudes;
	private JLabel lblMostrarTecnicos;
	private JLabel lblMostrarUsuarios;

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
	public MenuAdmins(Window parent) {

		super(parent, "", ModalityType.APPLICATION_MODAL);
		setTitle("Menú de administradores");
		setResizable(false);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// manejo del matching al ingresar a la pantalla
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				// hacer match al abrir
				Bolsa.getInstancia().iniciarMatchingAutomatico();
//				//iniciar servidor para archivos de respaldo
				// Antes de mostrar la ventana:
				servidor = new Servidor();
				servidor.start(); // Esto arranca el servidor en segundo plano

			}

			@Override
			public void windowClosing(WindowEvent e) {
				Bolsa.getInstancia().detenerMatchingAutomatico();
				if (servidor != null) {
					servidor.detenerServidor();
				}
			}

		});

		setBounds(100, 100, 1100, 687);

		// Poner ventana en centro de pantalla y tamaño máximo
		dim = getToolkit().getScreenSize(); // Obtener dimensiones de la pantalla de la pc
		setSize(1920, 1075);
		setLocationRelativeTo(null);

		pnlContenido = new JPanel();
		pnlContenido.setBackground(Color.WHITE);
		pnlContenido.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlContenido);
		pnlContenido.setLayout(null);

		JPanel pnlOcultoLateralIzquierdo = new JPanel();
		pnlOcultoLateralIzquierdo.setBackground(Color.WHITE);
		pnlOcultoLateralIzquierdo.setBounds(250, 120, 19, 915);
		pnlContenido.add(pnlOcultoLateralIzquierdo);

		JPanel pnlOcultoInferior = new JPanel();
		pnlOcultoInferior.setBackground(Color.WHITE);
		pnlOcultoInferior.setBounds(257, 1031, 1637, 10);
		pnlContenido.add(pnlOcultoInferior);

		JPanel pnlOcultoLateralDerecho = new JPanel();
		pnlOcultoLateralDerecho.setBackground(Color.WHITE);
		pnlOcultoLateralDerecho.setBounds(1885, 64, 19, 971);
		pnlContenido.add(pnlOcultoLateralDerecho);

		lblMostrarNombreDePerfil = new JLabel("Administrador");
		lblMostrarNombreDePerfil.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMostrarNombreDePerfil.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMostrarNombreDePerfil.setBounds(1443, 13, 340, 74);
		pnlContenido.add(lblMostrarNombreDePerfil);

		iconAdministrador = new JLabel("");
		iconAdministrador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		iconAdministrador.setHorizontalAlignment(SwingConstants.CENTER);
		iconAdministrador.setIcon(new ImageIcon(MenuAdmins.class.getResource("/img/admin-panel.png")));
		iconAdministrador.setBounds(1792, 13, 102, 74);
		pnlContenido.add(iconAdministrador);

		JPanel pnlInformacionSuperior = new JPanel();
		pnlInformacionSuperior.setBackground(Color.WHITE);
		pnlInformacionSuperior.setBounds(250, 64, 1644, 59);
		pnlContenido.add(pnlInformacionSuperior);
		pnlInformacionSuperior.setLayout(null);

		iconLaborea = new JLabel("");
		iconLaborea.setHorizontalAlignment(SwingConstants.CENTER);
		iconLaborea.setIcon(new ImageIcon(MenuAdmins.class.getResource("/img/Laborea.png")));
		iconLaborea.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		iconLaborea.setBounds(12, 13, 199, 59);
		pnlContenido.add(iconLaborea);

		// hacer que el panel tenga bordes redondeados, usando paintcomponent
		pnlOpciones = new JPanel() {
			@Override
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
		pnlOpciones.setOpaque(false);
		pnlOpciones.setBackground(new Color(45, 45, 60));
		pnlOpciones.setBounds(-31, 120, 278, 948);
		pnlContenido.add(pnlOpciones);
		pnlOpciones.setLayout(null);

		JPanel pnlBtnCrearSolicitudes = new JPanel() {
			@Override
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
		pnlBtnCrearSolicitudes.setOpaque(false);

		pnlBtnCrearSolicitudes.setBackground(new Color(100, 110, 130));
		pnlBtnCrearSolicitudes.setBounds(0, 62, 268, 80);
		pnlOpciones.add(pnlBtnCrearSolicitudes);
		pnlBtnCrearSolicitudes.setLayout(null);

		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtpMenus.setSelectedIndex(0);// volver a ofertas
			}
		});
		lblInicio.setForeground(Color.WHITE);
		lblInicio.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblInicio.setBounds(22, 11, 236, 58);
		pnlBtnCrearSolicitudes.add(lblInicio);

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

		JLabel lblInformes = new JLabel("Informes");
		lblInformes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtpMenus.setSelectedIndex(1);// ir a mis solicitudes
			}
		});
		lblInformes.setForeground(Color.WHITE);
		lblInformes.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformes.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblInformes.setBounds(22, 11, 236, 58);
		pnlBtnSolicitudes.add(lblInformes);

		JPanel pnlBtnRespaldo = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				// Color de fondo
				g2.setColor(getBackground());

				// Dibuja un rectangulo redondeado (x, y, width, height, arcWidth, arcHeight)
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 80, 80);
			}
		};
		pnlBtnRespaldo.setLayout(null);
		pnlBtnRespaldo.setOpaque(false);
		pnlBtnRespaldo.setBackground(new Color(100, 110, 130));
		pnlBtnRespaldo.setBounds(0, 379, 268, 80);
		pnlOpciones.add(pnlBtnRespaldo);

		JLabel lblRespaldo = new JLabel("Respaldo");
		lblRespaldo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtpMenus.setSelectedIndex(3);
			}
		});
		lblRespaldo.setHorizontalAlignment(SwingConstants.CENTER);
		lblRespaldo.setForeground(Color.WHITE);
		lblRespaldo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblRespaldo.setBounds(22, 11, 236, 58);
		pnlBtnRespaldo.add(lblRespaldo);

		JPanel pnlBtnMatcheo = new JPanel() {
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
		pnlBtnMatcheo.setLayout(null);
		pnlBtnMatcheo.setOpaque(false);
		pnlBtnMatcheo.setBackground(new Color(100, 110, 130));
		pnlBtnMatcheo.setBounds(0, 273, 268, 80);
		pnlOpciones.add(pnlBtnMatcheo);

		JLabel lblMatcheo = new JLabel("Matcheo");
		lblMatcheo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtpMenus.setSelectedIndex(2);
			}
		});
		lblMatcheo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatcheo.setForeground(Color.WHITE);
		lblMatcheo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMatcheo.setBounds(22, 11, 236, 58);
		pnlBtnMatcheo.add(lblMatcheo);

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
		pnlContenido.add(jtpMenus);

		JPanel pnlInicio = new JPanel();
		pnlInicio.setBackground(Color.WHITE);
		jtpMenus.addTab("New tab", null, pnlInicio, null);
		pnlInicio.setLayout(null);

		JLabel lblResumenInfo = new JLabel("Resumen");
		lblResumenInfo.setHorizontalAlignment(SwingConstants.LEFT);
		lblResumenInfo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblResumenInfo.setBounds(10, 2, 260, 51);
		pnlInicio.add(lblResumenInfo);

		JButton btnRecargarDatos = new JButton("Recargar");
		btnRecargarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarInformacion();
			}
		});
		btnRecargarDatos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnRecargarDatos.setBackground(Color.WHITE);
		btnRecargarDatos.setIcon(new ImageIcon(MenuAdmins.class.getResource("/img/refresh.png")));
		btnRecargarDatos.setBounds(1494, 8, 125, 45);
		pnlInicio.add(btnRecargarDatos);

		JSeparator sptSubrayadoResumen = new JSeparator();
		sptSubrayadoResumen.setForeground(Color.BLACK);
		sptSubrayadoResumen.setBounds(10, 54, 1609, 8);
		pnlInicio.add(sptSubrayadoResumen);

		JPanel pnlMostrarCantidades = new JPanel();
		pnlMostrarCantidades.setBackground(Color.WHITE);
		pnlMostrarCantidades.setBounds(10, 66, 1609, 839);
		pnlInicio.add(pnlMostrarCantidades);
		pnlMostrarCantidades.setLayout(null);

		JPanel pnlUsuariosTotales = new JPanel();
		pnlUsuariosTotales.setLayout(null);
		pnlUsuariosTotales.setBackground(new Color(186, 218, 191));
		pnlUsuariosTotales.setBounds(14, 14, 646, 398);
		pnlMostrarCantidades.add(pnlUsuariosTotales);

		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarios.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblUsuarios.setBounds(0, 112, 646, 30);
		pnlUsuariosTotales.add(lblUsuarios);
		
		lblMostrarUsuarios = new JLabel("0");
		lblMostrarUsuarios.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblMostrarUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblMostrarUsuarios.setBounds(0, 254, 646, 30);
		pnlUsuariosTotales.add(lblMostrarUsuarios);

		JPanel pnlMatcheos = new JPanel();
		pnlMatcheos.setLayout(null);
		pnlMatcheos.setBackground(Color.WHITE);
		pnlMatcheos.setBounds(14, 426, 646, 398);
		pnlMostrarCantidades.add(pnlMatcheos);

		JPanel pnlCantMatcheos = new JPanel();
		pnlCantMatcheos.setLayout(null);
		pnlCantMatcheos.setBackground(new Color(227, 241, 226));
		pnlCantMatcheos.setBounds(0, 0, 646, 181);
		pnlMatcheos.add(pnlCantMatcheos);

		JLabel lblMatcheosDisp = new JLabel("Matcheos disponibles");
		lblMatcheosDisp.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatcheosDisp.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblMatcheosDisp.setBounds(0, 40, 646, 30);
		pnlCantMatcheos.add(lblMatcheosDisp);

		lblMostrarMatcheosDisp = new JLabel("0");
		lblMostrarMatcheosDisp.setHorizontalAlignment(SwingConstants.CENTER);
		lblMostrarMatcheosDisp.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblMostrarMatcheosDisp.setBounds(0, 110, 646, 30);
		pnlCantMatcheos.add(lblMostrarMatcheosDisp);

		JPanel pnlContratos = new JPanel();
		pnlContratos.setLayout(null);
		pnlContratos.setBackground(new Color(216, 229, 198));
		pnlContratos.setBounds(0, 202, 646, 196);
		pnlMatcheos.add(pnlContratos);

		JLabel lblContratos = new JLabel("Contratos exitosos");
		lblContratos.setHorizontalAlignment(SwingConstants.CENTER);
		lblContratos.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblContratos.setBounds(0, 45, 646, 30);
		pnlContratos.add(lblContratos);

		lblMostrarContratos = new JLabel("0");
		lblMostrarContratos.setHorizontalAlignment(SwingConstants.CENTER);
		lblMostrarContratos.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblMostrarContratos.setBounds(0, 120, 646, 30);
		pnlContratos.add(lblMostrarContratos);

		JPanel pnlPersonas = new JPanel();
		pnlPersonas.setLayout(null);
		pnlPersonas.setBackground(new Color(209, 232, 238));
		pnlPersonas.setBounds(676, 14, 283, 398);
		pnlMostrarCantidades.add(pnlPersonas);

		JLabel lblPersonas = new JLabel("Personas");
		lblPersonas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPersonas.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblPersonas.setBounds(0, 112, 283, 30);
		pnlPersonas.add(lblPersonas);

		lblMostrarPersonas = new JLabel("0");
		lblMostrarPersonas.setHorizontalAlignment(SwingConstants.CENTER);
		lblMostrarPersonas.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblMostrarPersonas.setBounds(0, 254, 283, 30);
		pnlPersonas.add(lblMostrarPersonas);

		JPanel pnlEmpresas = new JPanel();
		pnlEmpresas.setBounds(672, 426, 283, 398);
		pnlMostrarCantidades.add(pnlEmpresas);
		pnlEmpresas.setLayout(null);
		pnlEmpresas.setBackground(new Color(209, 232, 238));

		JLabel lblEmpresas = new JLabel("Empresas");
		lblEmpresas.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpresas.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblEmpresas.setBounds(0, 112, 283, 30);
		pnlEmpresas.add(lblEmpresas);

		lblMostrarEmpresas = new JLabel("0");
		lblMostrarEmpresas.setHorizontalAlignment(SwingConstants.CENTER);
		lblMostrarEmpresas.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblMostrarEmpresas.setBounds(0, 254, 283, 30);
		pnlEmpresas.add(lblMostrarEmpresas);

		JPanel pnlCandidatos = new JPanel();
		pnlCandidatos.setLayout(null);
		pnlCandidatos.setBackground(Color.WHITE);
		pnlCandidatos.setBounds(971, 14, 624, 398);
		pnlMostrarCantidades.add(pnlCandidatos);

		JPanel pnlUniversitario = new JPanel();
		pnlUniversitario.setLayout(null);
		pnlUniversitario.setBackground(new Color(237, 215, 214));
		pnlUniversitario.setBounds(10, 0, 297, 190);
		pnlCandidatos.add(pnlUniversitario);

		JLabel lblUniversitario = new JLabel("Universitario");
		lblUniversitario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUniversitario.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblUniversitario.setBounds(0, 37, 297, 30);
		pnlUniversitario.add(lblUniversitario);

		lblMostrarUniversitario = new JLabel("0");
		lblMostrarUniversitario.setHorizontalAlignment(SwingConstants.CENTER);
		lblMostrarUniversitario.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblMostrarUniversitario.setBounds(0, 104, 297, 30);
		pnlUniversitario.add(lblMostrarUniversitario);

		JPanel pnlTecnicos = new JPanel();
		pnlTecnicos.setLayout(null);
		pnlTecnicos.setBackground(new Color(237, 215, 214));
		pnlTecnicos.setBounds(317, 0, 297, 190);
		pnlCandidatos.add(pnlTecnicos);

		JLabel lblTecnicos = new JLabel("Técnicos");
		lblTecnicos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTecnicos.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblTecnicos.setBounds(0, 37, 297, 30);
		pnlTecnicos.add(lblTecnicos);

		lblMostrarTecnicos = new JLabel("0");
		lblMostrarTecnicos.setHorizontalAlignment(SwingConstants.CENTER);
		lblMostrarTecnicos.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblMostrarTecnicos.setBounds(0, 104, 297, 30);
		pnlTecnicos.add(lblMostrarTecnicos);

		JPanel pnlObrero = new JPanel();
		pnlObrero.setLayout(null);
		pnlObrero.setBackground(new Color(237, 215, 214));
		pnlObrero.setBounds(10, 208, 297, 190);
		pnlCandidatos.add(pnlObrero);

		JLabel lblObrero = new JLabel("Obreros");
		lblObrero.setHorizontalAlignment(SwingConstants.CENTER);
		lblObrero.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblObrero.setBounds(0, 37, 297, 30);
		pnlObrero.add(lblObrero);

		lblMostrarObrero = new JLabel("0");
		lblMostrarObrero.setHorizontalAlignment(SwingConstants.CENTER);
		lblMostrarObrero.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblMostrarObrero.setBounds(0, 104, 297, 30);
		pnlObrero.add(lblMostrarObrero);

		JPanel pnlPeticiones = new JPanel();
		pnlPeticiones.setLayout(null);
		pnlPeticiones.setBackground(Color.WHITE);
		pnlPeticiones.setBounds(971, 426, 624, 398);
		pnlMostrarCantidades.add(pnlPeticiones);

		JPanel pnlOfertas = new JPanel();
		pnlOfertas.setLayout(null);
		pnlOfertas.setBackground(new Color(244, 235, 233));
		pnlOfertas.setBounds(12, 0, 297, 398);
		pnlPeticiones.add(pnlOfertas);

		JLabel lblOfertas = new JLabel("Ofertas disponibles");
		lblOfertas.setHorizontalAlignment(SwingConstants.CENTER);
		lblOfertas.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblOfertas.setBounds(0, 112, 297, 30);
		pnlOfertas.add(lblOfertas);

		lblMostrarOfertas = new JLabel("0");
		lblMostrarOfertas.setHorizontalAlignment(SwingConstants.CENTER);
		lblMostrarOfertas.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblMostrarOfertas.setBounds(0, 254, 297, 30);
		pnlOfertas.add(lblMostrarOfertas);

		JPanel pnlSolicitudes = new JPanel();
		pnlSolicitudes.setLayout(null);
		pnlSolicitudes.setBackground(new Color(244, 235, 233));
		pnlSolicitudes.setBounds(317, 0, 297, 398);
		pnlPeticiones.add(pnlSolicitudes);

		JLabel lblSolicitudes = new JLabel("Solicitudes activas");
		lblSolicitudes.setHorizontalAlignment(SwingConstants.CENTER);
		lblSolicitudes.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblSolicitudes.setBounds(0, 112, 297, 30);
		pnlSolicitudes.add(lblSolicitudes);

		lblMostrarSolicitudes = new JLabel("0");
		lblMostrarSolicitudes.setHorizontalAlignment(SwingConstants.CENTER);
		lblMostrarSolicitudes.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblMostrarSolicitudes.setBounds(0, 254, 297, 30);
		pnlSolicitudes.add(lblMostrarSolicitudes);

		JPanel pnlInformes = new JPanel();
		pnlInformes.setBackground(Color.WHITE);
		jtpMenus.addTab("New tab", null, pnlInformes, null);
		pnlInformes.setLayout(null);

		JLabel lblInformes_1 = new JLabel("Informes");
		lblInformes_1.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		lblInformes_1.setBounds(10, 11, 260, 51);
		pnlInformes.add(lblInformes_1);

		JSeparator sptSubrayadorInformes = new JSeparator();
		sptSubrayadorInformes.setForeground(Color.BLACK);
		sptSubrayadorInformes.setBounds(10, 54, 1609, 8);
		pnlInformes.add(sptSubrayadorInformes);

		JPanel pnlMatc = new JPanel();
		pnlMatc.setBackground(Color.WHITE);
		jtpMenus.addTab("New tab", null, pnlMatc, null);
		pnlMatc.setLayout(null);

		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.BLACK);
		separator_3.setBounds(10, 54, 1609, 8);
		pnlMatc.add(separator_3);

		JLabel lblMatching = new JLabel("Matching");
		lblMatching.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		lblMatching.setBounds(10, 11, 260, 51);
		pnlMatc.add(lblMatching);

		JButton btnRefrescarMatch = new JButton("Refrescar matching");
		btnRefrescarMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// refrescar match
				Bolsa.getInstancia().iniciarMatchingAutomatico();
			}
		});
		btnRefrescarMatch.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRefrescarMatch.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRefrescarMatch.setIcon(new ImageIcon(MenuAdmins.class.getResource("/img/match64.png")));
		btnRefrescarMatch.setBackground(Color.WHITE);
		btnRefrescarMatch.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnRefrescarMatch.setBounds(23, 94, 755, 152);
		pnlMatc.add(btnRefrescarMatch);

		JButton btnVisualizarMatchesgeneral = new JButton("Visualizar Matches (General)");
		btnVisualizarMatchesgeneral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformeMatches im = new InformeMatches();
				im.setModal(true);
				im.setVisible(true);
			}
		});
		btnVisualizarMatchesgeneral.setIcon(new ImageIcon(MenuAdmins.class.getResource("/img/table64.png")));
		btnVisualizarMatchesgeneral.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnVisualizarMatchesgeneral.setHorizontalTextPosition(SwingConstants.CENTER);
		btnVisualizarMatchesgeneral.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnVisualizarMatchesgeneral.setBackground(Color.WHITE);
		btnVisualizarMatchesgeneral.setBounds(840, 94, 755, 152);
		pnlMatc.add(btnVisualizarMatchesgeneral);

		JPanel pnlRespaldo = new JPanel();
		pnlRespaldo.setBackground(Color.WHITE);
		jtpMenus.addTab("New tab", null, pnlRespaldo, null);
		pnlRespaldo.setLayout(null);

		JLabel lblRespaldo_1 = new JLabel("Respaldo");
		lblRespaldo_1.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		lblRespaldo_1.setBounds(10, 11, 260, 51);
		pnlRespaldo.add(lblRespaldo_1);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(10, 54, 1609, 8);
		pnlRespaldo.add(separator_1);

		JLabel lblRespaldomsg = new JLabel("Utilice este botón para realizar un respaldo de la información");
		lblRespaldomsg.setHorizontalAlignment(SwingConstants.CENTER);
		lblRespaldomsg.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		lblRespaldomsg.setBounds(10, 87, 1609, 51);
		pnlRespaldo.add(lblRespaldomsg);

		JButton btnRespaldo = new JButton("Realizar respaldo de datos");
		btnRespaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					sfd = new Socket("127.0.0.1", 7000);
					DataInputStream aux = new DataInputStream(new FileInputStream(new File("BdLaborea.dat")));
					SalidaSocket = new DataOutputStream((sfd.getOutputStream()));
					int unByte;
					try {
						while ((unByte = aux.read()) != -1) {
							SalidaSocket.write(unByte);
							SalidaSocket.flush();
						}

						JOptionPane.showMessageDialog(null, "Respaldo realizado correctamente");
					} catch (IOException ioe) {
						System.out.println("Error: " + ioe);

					}
				} catch (UnknownHostException uhe) {
					JOptionPane.showMessageDialog(null, "No se puede acceder al servidor.", "Información",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException ioe) {
					JOptionPane.showMessageDialog(null, "Error de comunicación: " + ioe.getMessage());
					ioe.printStackTrace(); // Para ver detalles en consola
				}
			}

		});
		btnRespaldo.setBackground(Color.WHITE);
		btnRespaldo.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRespaldo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRespaldo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnRespaldo.setIcon(new ImageIcon(MenuAdmins.class.getResource("/img/database64.png")));
		btnRespaldo.setBounds(575, 186, 476, 108);
		pnlRespaldo.add(btnRespaldo);
		
		cargarInformacion(); // Para mostrar todo la información en la pantalla
	}

	private void cargarInformacion() {
		ArrayList<Integer> cantidades = Bolsa.getInstancia().cantidadesCantidatos();
		lblMostrarUsuarios.setText(String.valueOf(Bolsa.getInstancia().cantidadUsuarios()));
		lblMostrarPersonas.setText(String.valueOf(cantidades.get(0)));
		lblMostrarEmpresas.setText(String.valueOf(Bolsa.getInstancia().cantidadEmpresas()));
		lblMostrarUniversitario.setText(String.valueOf(cantidades.get(1)));
		lblMostrarTecnicos.setText(String.valueOf(cantidades.get(2)));
		lblMostrarObrero.setText(String.valueOf(cantidades.get(3)));
		lblMostrarMatcheosDisp.setText(String.valueOf(Bolsa.getInstancia().cantMatheosActivos()));
		lblMostrarContratos.setText(String.valueOf(Bolsa.getInstancia().cantContratos()));
		lblMostrarOfertas.setText(String.valueOf(Bolsa.getInstancia().cantOfertasDisp()));
		lblMostrarSolicitudes.setText(String.valueOf(Bolsa.getInstancia().cantidadSolicPend()));
	}
}
