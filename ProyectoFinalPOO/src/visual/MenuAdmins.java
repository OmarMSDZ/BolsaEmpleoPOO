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

	private JPanel contentPane;
	private Dimension dim;
	private JLabel label;
	private JLabel label_1;
	private JPanel pnlOpciones;

	private JTabbedPane jtpMenus;
	private JLabel lblMostrarNombreDePerfil;

	static Socket sfd = null;
	static DataInputStream EntradaSocket;
	static DataOutputStream SalidaSocket;
	private Servidor servidor;

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

		lblMostrarNombreDePerfil = new JLabel("Administrador");
		lblMostrarNombreDePerfil.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMostrarNombreDePerfil.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMostrarNombreDePerfil.setBounds(1443, 13, 340, 74);
		contentPane.add(lblMostrarNombreDePerfil);

		label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(MenuAdmins.class.getResource("/img/admin-panel.png")));
		label.setBounds(1792, 13, 102, 74);
		contentPane.add(label);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(250, 64, 1644, 59);
		contentPane.add(panel_1);

		label_1 = new JLabel("");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setIcon(new ImageIcon(MenuAdmins.class.getResource("/img/Laborea.png")));
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
		contentPane.add(jtpMenus);

		JPanel pnlInicio = new JPanel();
		pnlInicio.setBackground(Color.WHITE);
		jtpMenus.addTab("New tab", null, pnlInicio, null);
		pnlInicio.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Resumen");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		lblNewLabel_4.setBounds(10, 11, 260, 51);
		pnlInicio.add(lblNewLabel_4);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(10, 54, 1609, 8);
		pnlInicio.add(separator_2);

		JPanel pnlInformes = new JPanel();
		pnlInformes.setBackground(Color.WHITE);
		jtpMenus.addTab("New tab", null, pnlInformes, null);
		pnlInformes.setLayout(null);

		JLabel lblInformes_1 = new JLabel("Informes");
		lblInformes_1.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		lblInformes_1.setBounds(10, 11, 260, 51);
		pnlInformes.add(lblInformes_1);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(10, 54, 1609, 8);
		pnlInformes.add(separator);

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
					JOptionPane.showMessageDialog(null, "No se puede acceder al servidor.");
				} catch (IOException ioe) {
					JOptionPane.showMessageDialog(null, "Error de comunicación: " + ioe.getMessage());
					ioe.printStackTrace(); // para ver detalles en consola
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

	}
}
