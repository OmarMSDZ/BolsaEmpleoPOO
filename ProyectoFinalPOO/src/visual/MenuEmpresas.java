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
import java.awt.Dialog.ModalityType;
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

public class MenuEmpresas extends JDialog {

	private JPanel contentPane;
	private JLabel iconUsuario;
	private JLabel iconLaborea;
	private JPanel pnlOpciones;
	private JLabel lblSalarioOferta;
	private JLabel lblTipoEmpleoOferta;
	private JLabel lblModalidadOferta;
	private JLabel lblMostrarLicencia;
	private JLabel lblMovilizarseOferta;
	private JTabbedPane jtpDescripcionOferta;
	private JLabel lblMostrarHorario;

	// Solicitud seleccionada
	private static Oferta ofertaSeleccionada = null;
	private static Empresa empresaActual = null;

	private JTabbedPane jtpMenus;
	private JRadioButton rdbtnLicenciaNo;
	private JRadioButton rdbtnLicenciaSi;
	private JRadioButton rdbtnMovilidadNo;
	private JRadioButton rdbtnMovilidadSi;
	private JComboBox cmbAreaEstudios;
	private JLabel lblMostrarNomb;
	private JLabel lblMostrarRc;
	private JLabel lblMostrarTelefono;
	private JLabel lblMostrarCorreoEmp;
	private JLabel lblMostrarNumOfertas;
	private JLabel lblMostrarEstado;
	private JComboBox cbxHorarioOferta;
	private JComboBox cbxTipoEmpleo;
	private JComboBox cbxModalidad;
	private JSpinner spnSalarioDeseado;
	private JPanel pnlMisOfertas;
	private JLabel lblTituloOferta;
	private JLabel lblEmpresaOferta;
	private JLabel lblAreaOferta;
	private JLabel lblFechaOferta;
	private JLabel lblMostrarEstadoOferta;
	private JTextField txtDescripcion;
	private JTextField txtPuestoTrabajo;
	private JComboBox cmbNivelEstudio;
	private JSpinner spnAnniosExp;
	private JSpinner spnCantVacantes;
	private JButton btnRecargarInfoEmpresa;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuEmpresas frame = new MenuEmpresas();
					frame.setVisible(true);
					frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							try (ObjectOutputStream bolsaWrite = new ObjectOutputStream(
									new FileOutputStream("BdLaborea.dat"))) {
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
		});
	}*/

	/**
	 * Create the frame.
	 */
	public MenuEmpresas(Window parent) {
		super(parent, "", ModalityType.APPLICATION_MODAL);
		setResizable(false);

		Empresa actual = (Empresa) Bolsa.getUsuarioActivo();

		if (actual != null) {
			empresaActual = actual;
			setTitle("Laborea - ¡Bienvenido " + actual.getNombre() + " " + actual.getRnc() + "!");
		} else {
			// Para pruebas
			empresaActual = new Empresa(Bolsa.getInstancia().generarCodigoUsuario(), "Laborea contratistas", "1234",
					"849-352-5887", "mxtn0001@ce.pucmm.edu.do", "Santiago", "Santiago de los Caballeros",
					"Casa de Omar", true, "123456789012", "Desarrollo de software", "Privado");
			Bolsa.getInstancia().insertarUsuario(empresaActual);
			Bolsa.setUsuarioActivo(empresaActual);
			setTitle("Laborea - Pruebas del menú de empresas");
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		addWindowListener(new WindowAdapter() {
//		    @Override
//		    public void windowClosing(WindowEvent e) {
//		        try {
//		            Bolsa.guardarEstado();
//		            System.out.println("Datos guardados al cerrar menu empresas" );
//		        } catch (Exception ex) {
//		            ex.printStackTrace();
//		        }
//		    }
//		});
		setBounds(100, 100, 1100, 687);

		getToolkit().getScreenSize();
		setSize(1920, 1075);
		setLocationRelativeTo(null);

//		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnlOcultoIzquierdo = new JPanel();
		pnlOcultoIzquierdo.setBackground(Color.WHITE);
		pnlOcultoIzquierdo.setBounds(250, 120, 19, 915);
		contentPane.add(pnlOcultoIzquierdo);

		JPanel pnlOcultoInferior = new JPanel();
		pnlOcultoInferior.setBackground(Color.WHITE);
		pnlOcultoInferior.setBounds(257, 1031, 1637, 10);
		contentPane.add(pnlOcultoInferior);

		JPanel pnlOcultoDerecho = new JPanel();
		pnlOcultoDerecho.setBackground(Color.WHITE);
		pnlOcultoDerecho.setBounds(1885, 64, 19, 971);
		contentPane.add(pnlOcultoDerecho);

		JLabel lblMostrarNombEmp = new JLabel("Nombre");
		lblMostrarNombEmp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMostrarNombEmp.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMostrarNombEmp.setBounds(1442, 27, 340, 74);
		contentPane.add(lblMostrarNombEmp);

		iconUsuario = new JLabel("");
		iconUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Abrir menú administradores, perfil de empresa
				if (empresaActual != null) {
					VisualizarDatosEmpresa visualizarDatosEmp = new VisualizarDatosEmpresa();
					visualizarDatosEmp.setModal(true);
					visualizarDatosEmp.setVisible(true);
				}
			}
		});
		iconUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		iconUsuario.setIcon(new ImageIcon(MenuEmpresas.class.getResource("/img/iconBuilding.png")));
		iconUsuario.setBounds(1792, 27, 102, 74);
		contentPane.add(iconUsuario);

		JPanel pnlEncabezado = new JPanel();
		pnlEncabezado.setBackground(Color.WHITE);
		pnlEncabezado.setBounds(250, 64, 1644, 59);
		contentPane.add(pnlEncabezado);

		// Mostrar el nombre de la empresa y su rnc en la esquina superior derecha de la
		// pantalla
		if (empresaActual != null) {
			lblMostrarNombEmp.setText(empresaActual.getNombre() + " " + empresaActual.getRnc());
		}

		iconLaborea = new JLabel("");
		iconLaborea.setHorizontalAlignment(SwingConstants.CENTER);
		iconLaborea.setIcon(new ImageIcon(MenuEmpresas.class.getResource("/img/Laborea.png")));
		iconLaborea.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		iconLaborea.setBounds(12, 13, 199, 59);
		contentPane.add(iconLaborea);

		// Hacer que el panel tenga bordes redondeados (simulando ser un button), usando
		// paintcomponent
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

		JPanel btnCrearOferta = new JPanel() {
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
		btnCrearOferta.setOpaque(false);

		btnCrearOferta.setBackground(new Color(100, 110, 130));
		btnCrearOferta.setBounds(0, 62, 268, 80);
		pnlOpciones.add(btnCrearOferta);
		btnCrearOferta.setLayout(null);

		JLabel lblBtnCrearOferta = new JLabel("Crear oferta");
		lblBtnCrearOferta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtpMenus.setSelectedIndex(0);
			}
		});
		lblBtnCrearOferta.setForeground(Color.WHITE);
		lblBtnCrearOferta.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblBtnCrearOferta.setHorizontalAlignment(SwingConstants.CENTER);
		lblBtnCrearOferta.setBounds(22, 11, 236, 58);
		btnCrearOferta.add(lblBtnCrearOferta);

		JPanel btnVisualizarOferta = new JPanel() {
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

		btnVisualizarOferta.setOpaque(false);
		btnVisualizarOferta.setBackground(new Color(100, 110, 130));
		btnVisualizarOferta.setLayout(null);
		btnVisualizarOferta.setBounds(0, 169, 268, 80);
		pnlOpciones.add(btnVisualizarOferta);

		JLabel lblMisOfertas = new JLabel("Mis ofertas");
		lblMisOfertas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cargarOferta();
				jtpMenus.setSelectedIndex(1);// ir a mis solicitudes
			}
		});
		lblMisOfertas.setForeground(Color.WHITE);
		lblMisOfertas.setHorizontalAlignment(SwingConstants.CENTER);
		lblMisOfertas.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMisOfertas.setBounds(22, 11, 236, 58);
		btnVisualizarOferta.add(lblMisOfertas);

		JPanel pnlSalir = new JPanel() {
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

		JPanel pnlOfertas = new JPanel();
		pnlOfertas.setBackground(Color.WHITE);
		jtpMenus.addTab("New tab", null, pnlOfertas, null);
		pnlOfertas.setLayout(null);

		JLabel lblCrearOferta = new JLabel("Crear oferta");
		lblCrearOferta.setHorizontalAlignment(SwingConstants.LEFT);
		lblCrearOferta.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblCrearOferta.setBounds(10, 11, 260, 51);
		pnlOfertas.add(lblCrearOferta);

		JLabel lblHorarioDeseado = new JLabel("Horario propuesto:");
		lblHorarioDeseado.setHorizontalAlignment(SwingConstants.LEFT);
		lblHorarioDeseado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblHorarioDeseado.setBounds(672, 460, 395, 51);
		pnlOfertas.add(lblHorarioDeseado);

		JLabel lblTipoEmpleo = new JLabel("Tipo de empleo:");
		lblTipoEmpleo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTipoEmpleo.setBounds(672, 255, 395, 51);
		pnlOfertas.add(lblTipoEmpleo);

		JLabel lblModalidadDeseada = new JLabel("Modalidad:");
		lblModalidadDeseada.setHorizontalAlignment(SwingConstants.LEFT);
		lblModalidadDeseada.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblModalidadDeseada.setBounds(1181, 158, 395, 51);
		pnlOfertas.add(lblModalidadDeseada);

		JLabel lblSalarioPropuesto = new JLabel("Salario propuesto:");
		lblSalarioPropuesto.setHorizontalAlignment(SwingConstants.LEFT);
		lblSalarioPropuesto.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblSalarioPropuesto.setBounds(1181, 255, 395, 51);
		pnlOfertas.add(lblSalarioPropuesto);

		JLabel lbldisponeDeLicencia = new JLabel("¿Requiere licencia de conducir?");
		lbldisponeDeLicencia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lbldisponeDeLicencia.setBounds(672, 559, 395, 51);
		pnlOfertas.add(lbldisponeDeLicencia);

		JLabel lblDisponibilidadDeMovilidad = new JLabel("Requiere movilidad:");
		lblDisponibilidadDeMovilidad.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDisponibilidadDeMovilidad.setBounds(1181, 460, 395, 51);
		pnlOfertas.add(lblDisponibilidadDeMovilidad);

		JSeparator sptSubrayadoCrearOferta = new JSeparator();
		sptSubrayadoCrearOferta.setForeground(Color.BLACK);
		sptSubrayadoCrearOferta.setBounds(10, 54, 1609, 8);
		pnlOfertas.add(sptSubrayadoCrearOferta);

		cbxTipoEmpleo = new JComboBox();
		cbxTipoEmpleo.setModel(new DefaultComboBoxModel(
				new String[] { "<< Seleccione >>", "Tiempo completo", "Tiempo Parcial", "Temporal", "Freelance" }));
		cbxTipoEmpleo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		cbxTipoEmpleo.setBackground(Color.WHITE);
		cbxTipoEmpleo.setBounds(672, 301, 395, 39);
		pnlOfertas.add(cbxTipoEmpleo);

		cbxModalidad = new JComboBox();
		cbxModalidad.setModel(
				new DefaultComboBoxModel(new String[] { "<< Seleccione >>", "Presencial", "Remoto", "Mixto" }));
		cbxModalidad.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		cbxModalidad.setBackground(Color.WHITE);
		cbxModalidad.setBounds(1181, 207, 395, 39);
		pnlOfertas.add(cbxModalidad);

		cbxHorarioOferta = new JComboBox();
		cbxHorarioOferta.setModel(new DefaultComboBoxModel(new String[] { "<< Seleccione >>", "Matutino", "Vespertino",
				"Nocturno", "Matutino y Vespertino", "Vespertino y Nocturno", "Todos" }));
		cbxHorarioOferta.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		cbxHorarioOferta.setBackground(Color.WHITE);
		cbxHorarioOferta.setBounds(672, 509, 395, 39);
		pnlOfertas.add(cbxHorarioOferta);

		spnSalarioDeseado = new JSpinner();
		spnSalarioDeseado.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		spnSalarioDeseado.setBackground(Color.WHITE);
		spnSalarioDeseado.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spnSalarioDeseado.setBounds(1181, 301, 395, 39);
		pnlOfertas.add(spnSalarioDeseado);

		rdbtnLicenciaSi = new JRadioButton("Sí");
		rdbtnLicenciaSi.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnLicenciaSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnLicenciaSi.setSelected(true);
				rdbtnLicenciaNo.setSelected(false);

			}
		});
		rdbtnLicenciaSi.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		rdbtnLicenciaSi.setBackground(Color.WHITE);
		rdbtnLicenciaSi.setBounds(672, 608, 109, 37);
		pnlOfertas.add(rdbtnLicenciaSi);

		rdbtnLicenciaNo = new JRadioButton("No");
		rdbtnLicenciaNo.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnLicenciaNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnLicenciaSi.setSelected(false);
				rdbtnLicenciaNo.setSelected(true);
			}
		});
		rdbtnLicenciaNo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		rdbtnLicenciaNo.setBackground(Color.WHITE);
		rdbtnLicenciaNo.setBounds(797, 610, 109, 35);
		pnlOfertas.add(rdbtnLicenciaNo);

		rdbtnMovilidadSi = new JRadioButton("Sí, requiere movilizarse");
		rdbtnMovilidadSi.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnMovilidadSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnMovilidadSi.setSelected(true);
				rdbtnMovilidadNo.setSelected(false);
			}
		});
		rdbtnMovilidadSi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		rdbtnMovilidadSi.setBackground(Color.WHITE);
		rdbtnMovilidadSi.setBounds(1181, 503, 200, 51);
		pnlOfertas.add(rdbtnMovilidadSi);

		rdbtnMovilidadNo = new JRadioButton("No aplica");
		rdbtnMovilidadNo.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnMovilidadNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnMovilidadSi.setSelected(false);
				rdbtnMovilidadNo.setSelected(true);
			}
		});
		rdbtnMovilidadNo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		rdbtnMovilidadNo.setBackground(Color.WHITE);
		rdbtnMovilidadNo.setBounds(1390, 503, 186, 51);
		pnlOfertas.add(rdbtnMovilidadNo);

		JLabel lblDatosGeneralesEmpresa = new JLabel("Datos generales");
		lblDatosGeneralesEmpresa.setHorizontalAlignment(SwingConstants.LEFT);
		lblDatosGeneralesEmpresa.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblDatosGeneralesEmpresa.setBounds(10, 97, 260, 51);
		pnlOfertas.add(lblDatosGeneralesEmpresa);

		JSeparator sptSubrayadoDatosGenerales = new JSeparator();
		sptSubrayadoDatosGenerales.setForeground(Color.BLACK);
		sptSubrayadoDatosGenerales.setBounds(10, 145, 650, 8);
		pnlOfertas.add(sptSubrayadoDatosGenerales);

		JLabel lblNombreEmp = new JLabel("Nombre:");
		lblNombreEmp.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreEmp.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblNombreEmp.setBounds(10, 168, 260, 51);
		pnlOfertas.add(lblNombreEmp);

		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblTelefono.setBounds(10, 292, 260, 51);
		pnlOfertas.add(lblTelefono);

		JLabel lblCorreoEmp = new JLabel("Correo:");
		lblCorreoEmp.setHorizontalAlignment(SwingConstants.LEFT);
		lblCorreoEmp.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblCorreoEmp.setBounds(10, 358, 260, 51);
		pnlOfertas.add(lblCorreoEmp);

		JLabel lblRnc = new JLabel("RNC:");
		lblRnc.setHorizontalAlignment(SwingConstants.LEFT);
		lblRnc.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblRnc.setBounds(10, 230, 260, 51);
		pnlOfertas.add(lblRnc);

		JLabel lblNumOfertas = new JLabel("Número de ofertas:");
		lblNumOfertas.setHorizontalAlignment(SwingConstants.LEFT);
		lblNumOfertas.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblNumOfertas.setBounds(10, 429, 260, 51);
		pnlOfertas.add(lblNumOfertas);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.LEFT);
		lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblEstado.setBounds(10, 501, 260, 51);
		pnlOfertas.add(lblEstado);

		JLabel lblDatosOferta = new JLabel("Datos oferta");
		lblDatosOferta.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblDatosOferta.setBounds(672, 97, 260, 51);
		pnlOfertas.add(lblDatosOferta);

		JSeparator sptSubrayadoDatosOferta = new JSeparator();
		sptSubrayadoDatosOferta.setForeground(Color.BLACK);
		sptSubrayadoDatosOferta.setBounds(658, 145, 891, 10);
		pnlOfertas.add(sptSubrayadoDatosOferta);

		lblMostrarNomb = new JLabel("Nombre");
		lblMostrarNomb.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarNomb.setForeground(Color.DARK_GRAY);
		lblMostrarNomb.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblMostrarNomb.setBounds(247, 168, 413, 51);
		pnlOfertas.add(lblMostrarNomb);

		lblMostrarRc = new JLabel("RNC");
		lblMostrarRc.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarRc.setForeground(Color.DARK_GRAY);
		lblMostrarRc.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblMostrarRc.setBounds(247, 230, 401, 51);
		pnlOfertas.add(lblMostrarRc);

		lblMostrarTelefono = new JLabel("Teléfono:");
		lblMostrarTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarTelefono.setForeground(Color.DARK_GRAY);
		lblMostrarTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblMostrarTelefono.setBounds(247, 292, 401, 51);
		pnlOfertas.add(lblMostrarTelefono);

		lblMostrarCorreoEmp = new JLabel("Correo");
		lblMostrarCorreoEmp.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarCorreoEmp.setForeground(Color.DARK_GRAY);
		lblMostrarCorreoEmp.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblMostrarCorreoEmp.setBounds(247, 358, 401, 51);
		pnlOfertas.add(lblMostrarCorreoEmp);

		lblMostrarNumOfertas = new JLabel("Número de ofertas");
		lblMostrarNumOfertas.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarNumOfertas.setForeground(Color.DARK_GRAY);
		lblMostrarNumOfertas.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblMostrarNumOfertas.setBounds(247, 429, 401, 51);
		pnlOfertas.add(lblMostrarNumOfertas);

		lblMostrarEstado = new JLabel("Estado");
		lblMostrarEstado.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarEstado.setForeground(Color.DARK_GRAY);
		lblMostrarEstado.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblMostrarEstado.setBounds(247, 501, 401, 51);
		pnlOfertas.add(lblMostrarEstado);

		// cargar datos de persona al iniciar esta pantalla
		cargarDatosEmpresa();

		JButton btnRegistrarOferta = new JButton("Registrar oferta");
		btnRegistrarOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (empresaActual != null) {
					if (validosDatosOferta()) {

						// Registrar oferta
						String codigoGenerado = Bolsa.getInstancia().generarCodigoOferta();
						String puestoTrabajo = txtPuestoTrabajo.getText();
						String descripcion = txtDescripcion.getText();
						String tipoEmpleo = cbxTipoEmpleo.getSelectedItem().toString();
						String modalidad = cbxModalidad.getSelectedItem().toString();
						String horarioPropuesto = cbxHorarioOferta.getSelectedItem().toString();
						String nivelEduc = cmbNivelEstudio.getSelectedItem().toString();
						String areaEstudios = cmbAreaEstudios.getSelectedItem().toString();
						int anniosExp = (int) spnAnniosExp.getValue();
						float salarioEstimado = (float) spnSalarioDeseado.getValue();
						boolean reqLicencia = false;
						if (rdbtnLicenciaSi.isSelected()) {
							reqLicencia = true;
						}
						boolean reqMovilidad = false;
						if (rdbtnMovilidadSi.isSelected()) {
							reqMovilidad = true;
						}

						Date fechaOferta = new Date();
						int cantVacantes = (int) spnCantVacantes.getValue();

						Oferta nuevaOferta = new Oferta(codigoGenerado, puestoTrabajo, descripcion, tipoEmpleo,
								modalidad, horarioPropuesto, nivelEduc, areaEstudios, anniosExp, salarioEstimado,
								reqLicencia, reqMovilidad, fechaOferta, cantVacantes, true, empresaActual);

						Bolsa.getInstancia().insertarOferta(nuevaOferta);
						JOptionPane.showMessageDialog(null, "Oferta creada exitosamente.", "Información",
								JOptionPane.INFORMATION_MESSAGE, null);
						limpiarFormularioOfertas();
					} else {
						JOptionPane.showMessageDialog(null, "Complete la información de todos los campos.",
								"Advertencia", JOptionPane.WARNING_MESSAGE, null);
					}
				} else {
					JOptionPane.showMessageDialog(null, "No hay ninguna empresa activa en este momento.", "Advertencia",
							JOptionPane.WARNING_MESSAGE, null);

				}
			}

		});
		btnRegistrarOferta.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnRegistrarOferta.setBackground(Color.WHITE);
		btnRegistrarOferta.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnRegistrarOferta.setBounds(1306, 839, 313, 66);
		pnlOfertas.add(btnRegistrarOferta);

		JLabel lblObtenidoAutomticamente = new JLabel("* Completado automáticamente *");
		lblObtenidoAutomticamente.setHorizontalAlignment(SwingConstants.CENTER);
		lblObtenidoAutomticamente.setForeground(Color.GRAY);
		lblObtenidoAutomticamente.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblObtenidoAutomticamente.setBounds(282, 106, 232, 39);
		pnlOfertas.add(lblObtenidoAutomticamente);

		JSeparator stpBarraVerticalCentral = new JSeparator();
		stpBarraVerticalCentral.setOrientation(SwingConstants.VERTICAL);
		stpBarraVerticalCentral.setForeground(Color.BLACK);
		stpBarraVerticalCentral.setBounds(658, 145, 16, 760);
		pnlOfertas.add(stpBarraVerticalCentral);

		JLabel lblAreaEstudios = new JLabel("Área de estudios:");
		lblAreaEstudios.setHorizontalAlignment(SwingConstants.LEFT);
		lblAreaEstudios.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAreaEstudios.setBounds(672, 358, 395, 51);
		pnlOfertas.add(lblAreaEstudios);

		cmbAreaEstudios = new JComboBox();
		cmbAreaEstudios.setModel(new DefaultComboBoxModel(new String[] { "<< Seleccione >>",
				"Tecnolog\u00EDa / Desarrollo de Software", "Marketing y Publicidad", "Ventas y Comercio",
				"Administraci\u00F3n / Oficina", "Recursos Humanos", "Finanzas / Contabilidad",
				"Log\u00EDstica y Distribuci\u00F3n", "Ingenier\u00EDa", "Salud / Medicina",
				"Educaci\u00F3n / Capacitaci\u00F3n", "Atenci\u00F3n al Cliente / Call Center",
				"Dise\u00F1o Gr\u00E1fico / UX/UI", "Legal / Jur\u00EDdico", "Producci\u00F3n / Manufactura",
				"Turismo y Hoteler\u00EDa", "Construcci\u00F3n / Arquitectura", "Investigaci\u00F3n y Desarrollo",
				"Servicios Generales / Mantenimiento", "Compras y Abastecimiento", "Calidad / Seguridad Industrial" }));
		cmbAreaEstudios.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		cmbAreaEstudios.setBackground(Color.WHITE);
		cmbAreaEstudios.setBounds(672, 405, 395, 39);
		pnlOfertas.add(cmbAreaEstudios);

		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDescripcion.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescripcion.setBounds(1181, 358, 395, 51);
		pnlOfertas.add(lblDescripcion);

		txtDescripcion = new JTextField();
		txtDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtDescripcion.setBounds(1181, 405, 395, 39);
		pnlOfertas.add(txtDescripcion);
		txtDescripcion.setColumns(10);

		JLabel lblPuestoTrabajo = new JLabel("Puesto de trabajo:");
		lblPuestoTrabajo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblPuestoTrabajo.setHorizontalAlignment(SwingConstants.LEFT);
		lblPuestoTrabajo.setBounds(672, 164, 395, 39);
		pnlOfertas.add(lblPuestoTrabajo);

		txtPuestoTrabajo = new JTextField();
		txtPuestoTrabajo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtPuestoTrabajo.setBounds(672, 207, 395, 39);
		pnlOfertas.add(txtPuestoTrabajo);
		txtPuestoTrabajo.setColumns(10);

		JLabel lblCantVacantes = new JLabel("Cantidad de vacantes:");
		lblCantVacantes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCantVacantes.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantVacantes.setBounds(1181, 565, 395, 39);
		pnlOfertas.add(lblCantVacantes);

		spnCantVacantes = new JSpinner();
		spnCantVacantes.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnCantVacantes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		spnCantVacantes.setBounds(1181, 606, 395, 39);
		pnlOfertas.add(spnCantVacantes);

		JLabel lblNivelEducacion = new JLabel("Nivel de eduación:");
		lblNivelEducacion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNivelEducacion.setHorizontalAlignment(SwingConstants.LEFT);
		lblNivelEducacion.setBounds(672, 667, 395, 39);
		pnlOfertas.add(lblNivelEducacion);

		JLabel lblAnniosExp = new JLabel("Años de experiencia:");
		lblAnniosExp.setVisible(false);
		lblAnniosExp.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAnniosExp.setBounds(1181, 667, 395, 39);
		pnlOfertas.add(lblAnniosExp);

		spnAnniosExp = new JSpinner();
		spnAnniosExp.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spnAnniosExp.setVisible(false);
		spnAnniosExp.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		spnAnniosExp.setBounds(1181, 705, 395, 39);
		pnlOfertas.add(spnAnniosExp);

		cmbNivelEstudio = new JComboBox();
		cmbNivelEstudio.setBackground(Color.WHITE);
		cmbNivelEstudio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbNivelEstudio.getSelectedIndex() == 2) {
					lblAnniosExp.setVisible(true);
					spnAnniosExp.setVisible(true);
				} else {
					lblAnniosExp.setVisible(false);
					spnAnniosExp.setVisible(false);
				}
			}
		});
		cmbNivelEstudio.setModel(new DefaultComboBoxModel(
				new String[] { "<< Seleccione >>", "Universitario / Grado", "Técnico superior", "Obrero" }));
		cmbNivelEstudio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cmbNivelEstudio.setBounds(672, 705, 395, 39);
		pnlOfertas.add(cmbNivelEstudio);
		
		btnRecargarInfoEmpresa = new JButton("Recargar");
		btnRecargarInfoEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarDatosEmpresa();
			}
		});
		btnRecargarInfoEmpresa.setBackground(Color.WHITE);
		btnRecargarInfoEmpresa.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnRecargarInfoEmpresa.setIcon(new ImageIcon(MenuEmpresas.class.getResource("/img/refresh.png")));
		btnRecargarInfoEmpresa.setBounds(550, 116, 109, 23);
		pnlOfertas.add(btnRecargarInfoEmpresa);

		JPanel pnlVerOfertas = new JPanel();
		pnlVerOfertas.setBackground(Color.WHITE);
		jtpMenus.addTab("New tab", null, pnlVerOfertas, null);
		pnlVerOfertas.setLayout(null);

		JScrollPane spnlOfertas = new JScrollPane();
		spnlOfertas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spnlOfertas.setBounds(10, 11, 339, 895);
		pnlVerOfertas.add(spnlOfertas);

		pnlMisOfertas = new JPanel();
		pnlMisOfertas.setBackground(Color.WHITE);
		spnlOfertas.setViewportView(pnlMisOfertas);
		pnlMisOfertas.setLayout(new BoxLayout(pnlMisOfertas, BoxLayout.Y_AXIS)); // Para mostrar los elementos
																					// verticales
		cargarOferta();

		JPanel pnlDescOferta = new JPanel();
		pnlDescOferta.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlDescOferta.setBackground(Color.WHITE);
		pnlDescOferta.setBounds(359, 11, 1263, 895);
		pnlVerOfertas.add(pnlDescOferta);
		pnlDescOferta.setLayout(null);

		jtpDescripcionOferta = new JTabbedPane(JTabbedPane.TOP);
		jtpDescripcionOferta.setBorder(null);
		jtpDescripcionOferta.setBounds(0, -32, 1276, 927);
		pnlDescOferta.add(jtpDescripcionOferta);

		JPanel pnlMensajeOferta = new JPanel();
		pnlMensajeOferta.setBackground(Color.WHITE);
		jtpDescripcionOferta.addTab("New tab", null, pnlMensajeOferta, null);
		pnlMensajeOferta.setLayout(null);

		JLabel lblMostrarMensaje = new JLabel("Seleccione una oferta para ver todos sus datos");
		lblMostrarMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblMostrarMensaje.setForeground(Color.DARK_GRAY);
		lblMostrarMensaje.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		lblMostrarMensaje.setBounds(0, 348, 1238, 113);
		pnlMensajeOferta.add(lblMostrarMensaje);

		JPanel pnlVistaOferta = new JPanel();
		pnlVistaOferta.setBackground(Color.WHITE);
		jtpDescripcionOferta.addTab("New tab", null, pnlVistaOferta, null);
		pnlVistaOferta.setLayout(null);

		lblTituloOferta = new JLabel("Oferta # - RNC de la empresa");
		lblTituloOferta.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 26));
		lblTituloOferta.setBounds(10, 11, 1047, 61);
		pnlVistaOferta.add(lblTituloOferta);

		JSeparator sptSubrayadoSOfertas = new JSeparator();
		sptSubrayadoSOfertas.setForeground(Color.BLACK);
		sptSubrayadoSOfertas.setBounds(10, 83, 1248, 13);
		pnlVistaOferta.add(sptSubrayadoSOfertas);

		lblSalarioOferta = new JLabel("Salario de la oferta");
		lblSalarioOferta.setForeground(Color.DARK_GRAY);
		lblSalarioOferta.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblSalarioOferta.setBounds(230, 199, 388, 47);
		pnlVistaOferta.add(lblSalarioOferta);

		lblTipoEmpleoOferta = new JLabel("Tipo de empleo ofertado");
		lblTipoEmpleoOferta.setForeground(Color.DARK_GRAY);
		lblTipoEmpleoOferta.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblTipoEmpleoOferta.setBounds(230, 249, 388, 47);
		pnlVistaOferta.add(lblTipoEmpleoOferta);

		lblModalidadOferta = new JLabel("Modalidad de la oferta");
		lblModalidadOferta.setHorizontalAlignment(SwingConstants.LEFT);
		lblModalidadOferta.setForeground(Color.DARK_GRAY);
		lblModalidadOferta.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblModalidadOferta.setBounds(230, 304, 388, 47);
		pnlVistaOferta.add(lblModalidadOferta);

		JLabel lblLicenciaConducir = new JLabel("Licencia de conducir:");
		lblLicenciaConducir.setIcon(new ImageIcon(MenuEmpresas.class.getResource("/img/driving-licence.png")));
		lblLicenciaConducir.setForeground(Color.BLACK);
		lblLicenciaConducir.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblLicenciaConducir.setBounds(10, 432, 210, 47);
		pnlVistaOferta.add(lblLicenciaConducir);

		lblMostrarLicencia = new JLabel("Licencia (sí o no)");
		lblMostrarLicencia.setForeground(Color.DARK_GRAY);
		lblMostrarLicencia.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblMostrarLicencia.setBounds(230, 432, 388, 47);
		pnlVistaOferta.add(lblMostrarLicencia);

		JLabel lblDisponibilidadDeMovilizarse = new JLabel("Disp. de movilizarse:");
		lblDisponibilidadDeMovilizarse.setIcon(new ImageIcon(MenuEmpresas.class.getResource("/img/position.png")));
		lblDisponibilidadDeMovilizarse.setForeground(Color.BLACK);
		lblDisponibilidadDeMovilizarse.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblDisponibilidadDeMovilizarse.setBounds(10, 501, 210, 47);
		pnlVistaOferta.add(lblDisponibilidadDeMovilizarse);

		lblMovilizarseOferta = new JLabel("Movilizarse oferta");
		lblMovilizarseOferta.setForeground(Color.DARK_GRAY);
		lblMovilizarseOferta.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblMovilizarseOferta.setBounds(230, 501, 388, 47);
		pnlVistaOferta.add(lblMovilizarseOferta);

		JLabel lblSalario = new JLabel("Salario deseado:");
		lblSalario.setIcon(new ImageIcon(MenuEmpresas.class.getResource("/img/money.png")));
		lblSalario.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblSalario.setBounds(10, 199, 182, 47);
		pnlVistaOferta.add(lblSalario);

		JLabel lblTipoEmpOferta = new JLabel("Tipo empleo:");
		lblTipoEmpOferta.setIcon(new ImageIcon(MenuEmpresas.class.getResource("/img/briefcase.png")));
		lblTipoEmpOferta.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblTipoEmpOferta.setBounds(10, 259, 182, 47);
		pnlVistaOferta.add(lblTipoEmpOferta);

		JLabel lblModalidad = new JLabel("Modalidad:");
		lblModalidad.setIcon(new ImageIcon(MenuEmpresas.class.getResource("/img/building.png")));
		lblModalidad.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblModalidad.setBounds(10, 314, 129, 47);
		pnlVistaOferta.add(lblModalidad);

		JLabel lblHorario = new JLabel("Horario:");
		lblHorario.setHorizontalAlignment(SwingConstants.LEFT);
		lblHorario.setIcon(new ImageIcon(MenuEmpresas.class.getResource("/img/clock.png")));
		lblHorario.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblHorario.setBounds(10, 378, 129, 47);
		pnlVistaOferta.add(lblHorario);

		lblMostrarHorario = new JLabel("Horario propuesto");
		lblMostrarHorario.setForeground(Color.DARK_GRAY);
		lblMostrarHorario.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblMostrarHorario.setBounds(230, 368, 388, 47);
		pnlVistaOferta.add(lblMostrarHorario);

		JLabel btnCerrarVistaOferta = new JLabel("");
		btnCerrarVistaOferta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtpDescripcionOferta.setSelectedIndex(0);
				// solicitudSelected = null;
			}
		});
		btnCerrarVistaOferta.setHorizontalAlignment(SwingConstants.CENTER);
		btnCerrarVistaOferta.setIcon(new ImageIcon(MenuEmpresas.class.getResource("/img/close.png")));
		btnCerrarVistaOferta.setBounds(1169, 11, 79, 72);
		pnlVistaOferta.add(btnCerrarVistaOferta);

		JLabel lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmpresa.setIcon(new ImageIcon(MenuEmpresas.class.getResource("/img/money.png")));
		lblEmpresa.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblEmpresa.setBounds(10, 104, 129, 47);
		pnlVistaOferta.add(lblEmpresa);

		lblEmpresaOferta = new JLabel("Empresa que oferta");
		lblEmpresaOferta.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmpresaOferta.setForeground(Color.DARK_GRAY);
		lblEmpresaOferta.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblEmpresaOferta.setBounds(230, 94, 388, 47);
		pnlVistaOferta.add(lblEmpresaOferta);

		JLabel lblFechaRealizacion = new JLabel("Fecha de realización:");
		lblFechaRealizacion.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaRealizacion.setIcon(new ImageIcon(MenuEmpresas.class.getResource("/img/calendar.png")));
		lblFechaRealizacion.setForeground(Color.BLACK);
		lblFechaRealizacion.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblFechaRealizacion.setBounds(10, 572, 210, 47);
		pnlVistaOferta.add(lblFechaRealizacion);

		lblFechaOferta = new JLabel("Fecha de la oferta");
		lblFechaOferta.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaOferta.setForeground(Color.DARK_GRAY);
		lblFechaOferta.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblFechaOferta.setBounds(230, 572, 388, 47);
		pnlVistaOferta.add(lblFechaOferta);

		JButton btnVisualizarMatches = new JButton("Visualizar matches");
		btnVisualizarMatches.setBackground(Color.WHITE);
		btnVisualizarMatches.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnVisualizarMatches.setHorizontalTextPosition(SwingConstants.CENTER);
		btnVisualizarMatches.setIcon(new ImageIcon(MenuEmpresas.class.getResource("/img/table.png")));
		btnVisualizarMatches.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnVisualizarMatches.setBounds(681, 807, 182, 73);
		pnlVistaOferta.add(btnVisualizarMatches);

		JButton btnCancelarOferta = new JButton("Cancelar oferta");
		btnCancelarOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Cancelar oferta
				int opccion = JOptionPane.showConfirmDialog(null, "¿Desea cancelar esta oferta?", "Cancelar",
						JOptionPane.WARNING_MESSAGE);
				if (opccion == JOptionPane.OK_OPTION) {
					Bolsa.getInstancia().eliminarOferta(ofertaSeleccionada);
					cargarVistaPreviaOferta(ofertaSeleccionada);
					jtpDescripcionOferta.setSelectedIndex(0);
				}
			}
		});
		btnCancelarOferta.setBackground(Color.WHITE);
		btnCancelarOferta.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCancelarOferta.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCancelarOferta.setIcon(new ImageIcon(MenuEmpresas.class.getResource("/img/close1.png")));
		btnCancelarOferta.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnCancelarOferta.setBounds(1066, 807, 182, 73);
		pnlVistaOferta.add(btnCancelarOferta);

		JLabel lblEstadoOferta = new JLabel("Estado de la oferta:");
		lblEstadoOferta.setHorizontalAlignment(SwingConstants.LEFT);
		lblEstadoOferta.setBackground(Color.WHITE);
		lblEstadoOferta.setForeground(Color.BLACK);
		lblEstadoOferta.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblEstadoOferta.setBounds(10, 810, 210, 47);
		pnlVistaOferta.add(lblEstadoOferta);

		lblMostrarEstadoOferta = new JLabel("Estado de la oferta");
		lblMostrarEstadoOferta.setForeground(Color.DARK_GRAY);
		lblMostrarEstadoOferta.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblMostrarEstadoOferta.setBounds(230, 810, 448, 47);
		pnlVistaOferta.add(lblMostrarEstadoOferta);

		JLabel lblAreaEstudio = new JLabel("\u00C1rea:");
		lblAreaEstudio.setIcon(new ImageIcon(MenuEmpresas.class.getResource("/img/quality.png")));
		lblAreaEstudio.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblAreaEstudio.setBounds(10, 148, 182, 47);
		pnlVistaOferta.add(lblAreaEstudio);

		lblAreaOferta = new JLabel("Área de oferta");
		lblAreaOferta.setForeground(Color.DARK_GRAY);
		lblAreaOferta.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblAreaOferta.setBounds(230, 145, 388, 47);
		pnlVistaOferta.add(lblAreaOferta);

		JLabel lblPuestoTrab = new JLabel("Puesto de trabajo:");
		lblPuestoTrab.setIcon(new ImageIcon(MenuEmpresas.class.getResource("/img/iconLaboral_x16.png")));
		lblPuestoTrab.setHorizontalAlignment(SwingConstants.LEFT);
		lblPuestoTrab.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblPuestoTrab.setBounds(630, 94, 199, 47);
		pnlVistaOferta.add(lblPuestoTrab);

		JLabel lblMostrarPuestoTrab = new JLabel("Puesto de trabajo");
		lblMostrarPuestoTrab.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblMostrarPuestoTrab.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarPuestoTrab.setBounds(849, 94, 388, 47);
		pnlVistaOferta.add(lblMostrarPuestoTrab);

		JLabel lblDesc = new JLabel("Descripción:");
		lblDesc.setIcon(new ImageIcon(MenuEmpresas.class.getResource("/img/iconDescripcion_x16.png")));
		lblDesc.setHorizontalAlignment(SwingConstants.LEFT);
		lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblDesc.setBounds(630, 145, 199, 47);
		pnlVistaOferta.add(lblDesc);

		JLabel lblMostrarDesc = new JLabel("Descripción del puesto de trabajo");
		lblMostrarDesc.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblMostrarDesc.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarDesc.setBounds(849, 145, 388, 47);
		pnlVistaOferta.add(lblMostrarDesc);

		JLabel lblCantVacant = new JLabel("Cant. Vacant. Disp:");
		lblCantVacant.setIcon(new ImageIcon(MenuEmpresas.class.getResource("/img/iconPersonas_x16.png")));
		lblCantVacant.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantVacant.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblCantVacant.setBounds(630, 199, 199, 47);
		pnlVistaOferta.add(lblCantVacant);

		JLabel lblMostrarCantVacant = new JLabel("Cantidad de vacantes disponibles");
		lblMostrarCantVacant.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblMostrarCantVacant.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarCantVacant.setBounds(849, 199, 388, 47);
		pnlVistaOferta.add(lblMostrarCantVacant);

		JLabel lblNivelEduc = new JLabel("Nivel Educ:");
		lblNivelEduc.setIcon(new ImageIcon(MenuEmpresas.class.getResource("/img/iconEstudios_x16.png")));
		lblNivelEduc.setHorizontalAlignment(SwingConstants.LEFT);
		lblNivelEduc.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNivelEduc.setBounds(630, 249, 199, 47);
		pnlVistaOferta.add(lblNivelEduc);

		JLabel lblMostrarNivelEduc = new JLabel("Nivel educativo");
		lblMostrarNivelEduc.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblMostrarNivelEduc.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarNivelEduc.setBounds(849, 249, 388, 47);
		pnlVistaOferta.add(lblMostrarNivelEduc);

		JLabel lblExpAnnios = new JLabel("Años de exp:");
		lblExpAnnios.setIcon(new ImageIcon(MenuEmpresas.class.getResource("/img/iconAnnios_x16.png")));
		lblExpAnnios.setHorizontalAlignment(SwingConstants.LEFT);
		lblExpAnnios.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblExpAnnios.setBounds(630, 304, 199, 47);
		pnlVistaOferta.add(lblExpAnnios);

		JLabel lblMostrarAnniosExp = new JLabel("Años de experiencia");
		lblMostrarAnniosExp.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblMostrarAnniosExp.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarAnniosExp.setBounds(849, 304, 388, 47);
		pnlVistaOferta.add(lblMostrarAnniosExp);
		
		JButton btnModificarOferta = new JButton("Modificar oferta");
		btnModificarOferta.setIcon(new ImageIcon(MenuEmpresas.class.getResource("/img/update.png")));
		btnModificarOferta.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnModificarOferta.setHorizontalTextPosition(SwingConstants.CENTER);
		btnModificarOferta.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnModificarOferta.setBackground(Color.WHITE);
		btnModificarOferta.setBounds(873, 807, 182, 73);
		pnlVistaOferta.add(btnModificarOferta);
	}

	private void cargarDatosEmpresa() {
		if (empresaActual != null) {

			lblMostrarNomb.setText(empresaActual.getNombre());
			lblMostrarRc.setText(empresaActual.getRnc());
			lblMostrarTelefono.setText(empresaActual.getTelefono());
			lblMostrarCorreoEmp.setText(empresaActual.getCorreoElectronico());

			lblMostrarNumOfertas.setText(String.valueOf(empresaActual.getMisOfertas().size()));

			if (empresaActual.isEstado()) {
				lblMostrarEstado.setText("Activa");
			} else {
				lblMostrarEstado.setText("Inactiva");
			}

		}
	}

	// Limpiar formulario de creación de las ofertas
	private void limpiarFormularioOfertas() {
		txtPuestoTrabajo.setText("");
		cmbAreaEstudios.setSelectedIndex(0);
		cbxHorarioOferta.setSelectedIndex(0);
		cbxTipoEmpleo.setSelectedIndex(0);
		cbxModalidad.setSelectedIndex(0);
		txtDescripcion.setText("");
		rdbtnMovilidadSi.setSelected(false);
		rdbtnMovilidadNo.setSelected(false);
		rdbtnLicenciaSi.setSelected(false);
		rdbtnLicenciaNo.setSelected(false);
		spnSalarioDeseado.setValue(0);
		spnCantVacantes.setValue(0);
		cmbNivelEstudio.setSelectedIndex(0);
		spnAnniosExp.setValue(0);
		cargarDatosEmpresa();
		cargarOferta();
	}

	// Validar creación de las ofertas
	private boolean validosDatosOferta() {
		boolean valido = true;

		if (txtPuestoTrabajo.getText().trim().isEmpty() || txtDescripcion.getText().trim().isEmpty()
				|| cmbAreaEstudios.getSelectedIndex() == 0 || cbxHorarioOferta.getSelectedIndex() == 0
				|| cbxTipoEmpleo.getSelectedIndex() == 0 || cbxModalidad.getSelectedIndex() == 0
				|| (int) spnCantVacantes.getValue() == 0 || cmbNivelEstudio.getSelectedIndex() == 0
				|| (cmbNivelEstudio.getSelectedIndex() == 2 && (int) spnAnniosExp.getValue() == 0)
				|| (!rdbtnMovilidadNo.isSelected() && !rdbtnMovilidadSi.isSelected())
				|| (!rdbtnLicenciaSi.isSelected() && !rdbtnLicenciaNo.isSelected())
				|| (float) spnSalarioDeseado.getValue() == 0) {
			valido = false;
		}
		return valido;
	}

	// Cargar todas las ofertas en el panel correspondiente
	private void cargarOferta() {
		if (empresaActual != null) {

			pnlMisOfertas.removeAll();

			for (Oferta actual : empresaActual.getMisOfertas()) {
				ElementosOferta elementosOfer = new ElementosOferta(actual);

				elementosOfer.addMouseListener(new java.awt.event.MouseAdapter() {
					@Override
					public void mouseClicked(java.awt.event.MouseEvent e) {
						ofertaSeleccionada = elementosOfer.getOferta();
						cargarVistaPreviaOferta(ofertaSeleccionada);
						jtpDescripcionOferta.setSelectedIndex(1);
					}
				});

				pnlMisOfertas.add(elementosOfer);
				pnlMisOfertas.add(Box.createVerticalStrut(10));
			}
			pnlMisOfertas.revalidate();
			pnlMisOfertas.repaint();
		}
	}

	private void cargarVistaPreviaOferta(Oferta oferta) {
		if (oferta != null) {
			lblTituloOferta.setText("Oferta #" + oferta.getCodigo());
			lblEmpresaOferta.setText(oferta.getEmpReclutadora().getNombre());

			lblAreaOferta.setText(oferta.getArea());
			lblSalarioOferta.setText(String.valueOf(oferta.getSalarioEstimado()));
			lblTipoEmpleoOferta.setText(oferta.getTipo());
			lblModalidadOferta.setText(oferta.getModalidad());
			lblMostrarHorario.setText(oferta.getHorario());
			if (oferta.isRequiereLicencia()) {
				lblMostrarLicencia.setText("Requiere licencia de conducir");
			} else {
				lblMostrarLicencia.setText("No aplica");
			}
			if (oferta.isRequiereMovilidad()) {
				lblMovilizarseOferta.setText("Requiere movilizarse");
			} else {
				lblMovilizarseOferta.setText("No aplica");
			}
			Date fechaOferta = oferta.getFechaOferta();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = dateFormat.format(fechaOferta);
			lblFechaOferta.setText(formattedDate);
			if (oferta.isEstadoOferta()) {
				lblMostrarEstadoOferta.setText("Disponible");
			} else {
				lblMostrarEstadoOferta.setText("No disponible");
			}
		}
	}
}
