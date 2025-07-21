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
import javax.swing.JButton;
import javax.swing.JSeparator;

public class MenuCandidatos extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private JLabel label;
	private JLabel label_1;
	private JPanel pnlOpciones;
	private JLabel lblTituloOferta;
	private JLabel lblEmpresaOferta;
	private JLabel lblProvinciaOferta;
	private JLabel lblSalarioOferta;
	private JLabel lblDescripcionOferta;
	private JLabel lblTipoOferta;
	private JLabel lblModalidadOferta;
	private JLabel lblFormacionOferta;
	private JLabel lblExpMinimaOferta;
	private JLabel lblConocimientosOferta;
	private JLabel lblLicConducirOferta;
	private JLabel lblMovilizarseOferta;
	private JTabbedPane jtpDescripcionOferta;
	private JLabel lblHorariosOferta;

	private static Oferta ofertaSelected = null; // Oferta seleccionada, para cuando se haga la solicitud
	private JTabbedPane jtpMenus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuCandidatos frame = new MenuCandidatos(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuCandidatos(Usuario regUsuario) {
		if (regUsuario != null) {
			if (regUsuario instanceof Persona) {
				Persona regPersona = (Persona) regUsuario;
				setTitle("Laborea - ¡Bienvenido " + regPersona.getNombre() + " " + regPersona.getApellidos() + "!");
			} else {
				JOptionPane.showMessageDialog(null, "Error crítico, verifique los datos.", "¡Advertencia!", JOptionPane.WARNING_MESSAGE);
			}
		} else {
			setTitle("Laborea - Pruebas menú candidatos");
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 687);

		// Poner ventana en centro de pantalla y tamaño maximo
		dim = getToolkit().getScreenSize(); // obtener dimensiones de la pantalla de la pc
		setSize(dim.width, dim.height);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// quitar bordes azules en jtp
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(250, 64, 440, 53);
		contentPane.add(panel_1);

		JLabel lblMostrarNombreDe = new JLabel("Nombre");
		lblMostrarNombreDe.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMostrarNombreDe.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblMostrarNombreDe.setBounds(1606, 27, 176, 74);
		contentPane.add(lblMostrarNombreDe);

		// mostrar nombre completo persona en la esquina de la pantalla
		/* if (regPersona != null) {
			lblMostrarNombreDe.setText(persActual.getNombre() + " " + persActual.getApellido());
		} */

		label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/* // Abrir menú administrador desde el perfil de candidatos
				VisualizarPerfilCandidato vpc = new VisualizarPerfilCandidato(regPersona);
				vpc.setModal(true);
				vpc.setVisible(true); */
			}
		});
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/user.png")));
		label.setBounds(1792, 27, 102, 74);
		contentPane.add(label);

		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/Laborea.png")));
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		label_1.setBounds(-11, 11, 271, 74);
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

				// Dibuja un rectángulo redondeado (x, y, width, height, arcWidth, arcHeight)
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 80, 80);
			}
		};
		pnlOpciones.setOpaque(false);
		pnlOpciones.setBackground(new Color(45, 45, 60));
		pnlOpciones.setBounds(-31, 120, 278, 948);
		contentPane.add(pnlOpciones);
		pnlOpciones.setLayout(null);

		JPanel pnlBtnOfertas = new JPanel() {
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
		pnlBtnOfertas.setOpaque(false);

		pnlBtnOfertas.setBackground(new Color(100, 110, 130));
		pnlBtnOfertas.setBounds(0, 62, 268, 80);
		pnlOpciones.add(pnlBtnOfertas);
		pnlBtnOfertas.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Explorar Ofertas");
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
		pnlBtnOfertas.add(lblNewLabel_3);

		JPanel pnlBtnSolicitudes = new JPanel() {
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
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(10, 11, 260, 51);
		pnlSolicitudes.add(lblNewLabel_4);

		JPanel pnlMisSolicitudes = new JPanel();
		pnlMisSolicitudes.setBackground(Color.WHITE);
		jtpMenus.addTab("New tab", null, pnlMisSolicitudes, null);
		pnlMisSolicitudes.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 11, 339, 895);
		pnlMisSolicitudes.add(scrollPane);

		JPanel ContenedorOfertas = new JPanel();
		ContenedorOfertas.setBackground(Color.WHITE);
		scrollPane.setViewportView(ContenedorOfertas);
		ContenedorOfertas.setLayout(new BoxLayout(ContenedorOfertas, BoxLayout.Y_AXIS)); // para que muestre los
																							// elementos vertical

		// Insertar elementos en panel de ofertas (hacer dinamico con un for y
		// obteniendo de la list de ofertas)
		/* Empresa empPrueba = new Empresa("E-1", "Laborea", "1234", "Tecnologia", "8091231234", "Prueba@gmail.com",
				"Santiago", "Centro ciudad");
		Oferta of1 = new Oferta("O-1", "Desarrollador Web", "Desarrollo de aplicaciones web", "Presencial",
				"Tiempo Completo", "Matutino-Vespertino", false, false, 1, "Manejo de PHP", "Universitario", 0,
				"Santiago", new Date(), true, empPrueba);
		Oferta of2 = new Oferta("O-2", "Ing. en IA", "Desarrollo de herramientas de IA", "Presencial",
				"Tiempo Completo", "Matutino-Vespertino", false, false, 1, "Manejo de PHP", "Universitario", 0,
				"Santiago", new Date(), true, empPrueba);
		Oferta of3 = new Oferta("O-3", "Agente de Marketing", "Crear publicidad y demas", "Presencial",
				"Tiempo Completo", "Matutino-Vespertino", false, false, 1, "Manejo de PHP", "Universitario", 0,
				"Santiago", new Date(), true, empPrueba);
		Oferta of4 = new Oferta("O-4", "Analista de Datos", "Analiza datos", "Presencial", "Freelance",
				"Matutino-Vespertino", false, false, 1, "Manejo de PHP", "Universitario", 0, "Santiago", new Date(),
				true, empPrueba);
		Oferta of5 = new Oferta("O-5", "Arq. de Software", "Diseña y dirige el proceso de la app", "Remoto",
				"Tiempo Completo", "Matutino-Vespertino", false, false, 1, "Manejo de PHP", "Universitario", 0,
				"Santiago", new Date(), true, empPrueba);
		Oferta of6 = new Oferta("O-6", "Desarrollador Movil", "Desarrollo de apps moviles", "Mixto", "Tiempo Parcial",
				"Nocturno", false, false, 1, "Manejo de PHP", "Universitario", 0, "Santiago", new Date(), true,
				empPrueba);
		Bolsa.getInstancia().insertarOferta(of1);
		Bolsa.getInstancia().insertarOferta(of2);
		Bolsa.getInstancia().insertarOferta(of3);
		Bolsa.getInstancia().insertarOferta(of4);
		Bolsa.getInstancia().insertarOferta(of5);
		Bolsa.getInstancia().insertarOferta(of6); */
		// llenar el scroll con los componentes de ofertas
		for (Oferta aux : Bolsa.getInstancia().getListaOfertas()) {
			ElementoOferta eleOf = new ElementoOferta(aux); // crear elemento por cada oferta

			// Evento para cuando se haga clic en cada elemento
			eleOf.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					// obtener oferta seleccionada
					ofertaSelected = eleOf.getOferta();
					// llenar campos de seccion vista oferta
					lblTituloOferta.setText(eleOf.getOferta().getPuestoTrab());
					lblEmpresaOferta.setText(eleOf.getOferta().getEmpReclutadora().getNombre());
					lblProvinciaOferta.setText(eleOf.getOferta().getProvincia());
					lblDescripcionOferta.setText(eleOf.getOferta().getDescripcion());
					if (eleOf.getOferta().getSalarioEstimado() > 0) {
						lblSalarioOferta.setText(String.valueOf(eleOf.getOferta().getSalarioEstimado()));
					} else {
						lblSalarioOferta.setText("A discutir en entrevista");
					}
					lblTipoOferta.setText(eleOf.getOferta().getTipo());
					lblModalidadOferta.setText(eleOf.getOferta().getModalidad());
					lblHorariosOferta.setText(eleOf.getOferta().getHorario());

					lblFormacionOferta.setText(eleOf.getOferta().getFormacion());
					if (eleOf.getOferta().getAniosExp() > 0) {
						lblExpMinimaOferta.setText(String.valueOf(eleOf.getOferta().getAniosExp()) + " año/s");
					} else {
						lblExpMinimaOferta.setText("No requiere experiencia");
					}
					lblConocimientosOferta.setText(eleOf.getOferta().getConocimientos());
					if (eleOf.getOferta().isRequiereLicencia()) {
						lblLicConducirOferta.setText("Requiere licencia de conducir");
					} else {
						lblLicConducirOferta.setText("N/A");
					}

					if (eleOf.getOferta().isRequiereMovilidad()) {
						lblMovilizarseOferta.setText("Debe movilizarse frecuentemente");
					} else {
						lblMovilizarseOferta.setText("N/A");
					}

					// mostrar panel con la descripcion
					jtpDescripcionOferta.setSelectedIndex(1);
				}
			});

			ContenedorOfertas.add(eleOf);
			ContenedorOfertas.add(Box.createVerticalStrut(10)); // añadir espacio entre elementos
		}

		JPanel pnlDescSolicitudes = new JPanel();
		pnlDescSolicitudes.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlDescSolicitudes.setBackground(Color.WHITE);
		pnlDescSolicitudes.setBounds(359, 11, 1263, 895);
		pnlMisSolicitudes.add(pnlDescSolicitudes);
		pnlDescSolicitudes.setLayout(null);

		jtpDescripcionOferta = new JTabbedPane(JTabbedPane.TOP);
		jtpDescripcionOferta.setBorder(null);
		jtpDescripcionOferta.setBounds(0, -24, 1263, 919);
		pnlDescSolicitudes.add(jtpDescripcionOferta);

		JPanel pnlMensajeOferta = new JPanel();
		pnlMensajeOferta.setBackground(Color.WHITE);
		jtpDescripcionOferta.addTab("New tab", null, pnlMensajeOferta, null);
		pnlMensajeOferta.setLayout(null);

		JLabel lblNewLabel = new JLabel("Seleccione una oferta para comenzar");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		lblNewLabel.setBounds(0, 348, 1238, 113);
		pnlMensajeOferta.add(lblNewLabel);

		JPanel pnlVistaOferta = new JPanel();
		pnlVistaOferta.setBackground(Color.WHITE);
		jtpDescripcionOferta.addTab("New tab", null, pnlVistaOferta, null);
		pnlVistaOferta.setLayout(null);

		lblTituloOferta = new JLabel("TituloOferta");
		lblTituloOferta.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 26));
		lblTituloOferta.setBounds(10, 11, 1047, 61);
		pnlVistaOferta.add(lblTituloOferta);

		lblEmpresaOferta = new JLabel("EmpresaOferta");
		lblEmpresaOferta.setForeground(new Color(100, 149, 237));
		lblEmpresaOferta.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		lblEmpresaOferta.setBounds(10, 75, 1047, 61);
		pnlVistaOferta.add(lblEmpresaOferta);

		lblProvinciaOferta = new JLabel("ProvinciaOferta");
		lblProvinciaOferta.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/pin.png")));
		lblProvinciaOferta.setForeground(Color.DARK_GRAY);
		lblProvinciaOferta.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		lblProvinciaOferta.setBounds(10, 130, 1047, 61);
		pnlVistaOferta.add(lblProvinciaOferta);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(10, 191, 1248, 13);
		pnlVistaOferta.add(separator);

		lblSalarioOferta = new JLabel("SalarioOferta");
		lblSalarioOferta.setForeground(Color.DARK_GRAY);
		lblSalarioOferta.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblSalarioOferta.setBounds(149, 198, 343, 47);
		pnlVistaOferta.add(lblSalarioOferta);

		lblTipoOferta = new JLabel("TipoOferta");
		lblTipoOferta.setForeground(Color.DARK_GRAY);
		lblTipoOferta.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblTipoOferta.setBounds(149, 243, 343, 47);
		pnlVistaOferta.add(lblTipoOferta);

		lblModalidadOferta = new JLabel("ModalidadOferta");
		lblModalidadOferta.setForeground(Color.DARK_GRAY);
		lblModalidadOferta.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblModalidadOferta.setBounds(149, 287, 405, 47);
		pnlVistaOferta.add(lblModalidadOferta);

		lblDescripcionOferta = new JLabel("DescripcionOferta");
		lblDescripcionOferta.setForeground(Color.DARK_GRAY);
		lblDescripcionOferta.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblDescripcionOferta.setBounds(507, 243, 812, 47);
		pnlVistaOferta.add(lblDescripcionOferta);

		JLabel lblRequisitos = new JLabel("Requisitos");
		lblRequisitos.setForeground(Color.BLACK);
		lblRequisitos.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		lblRequisitos.setBounds(10, 388, 1047, 47);
		pnlVistaOferta.add(lblRequisitos);

		JLabel lblFormacin = new JLabel("Formaci\u00F3n:");
		lblFormacin.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/graduation.png")));
		lblFormacin.setForeground(Color.BLACK);
		lblFormacin.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblFormacin.setBounds(10, 446, 210, 47);
		pnlVistaOferta.add(lblFormacin);

		lblFormacionOferta = new JLabel("FormacionOferta");
		lblFormacionOferta.setForeground(Color.DARK_GRAY);
		lblFormacionOferta.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblFormacionOferta.setBounds(230, 446, 448, 47);
		pnlVistaOferta.add(lblFormacionOferta);

		JLabel lblExperienciaMinima = new JLabel("Experiencia m\u00EDnima:");
		lblExperienciaMinima.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/briefcase.png")));
		lblExperienciaMinima.setForeground(Color.BLACK);
		lblExperienciaMinima.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblExperienciaMinima.setBounds(10, 504, 210, 47);
		pnlVistaOferta.add(lblExperienciaMinima);

		lblExpMinimaOferta = new JLabel("ExpMinimaOferta");
		lblExpMinimaOferta.setForeground(Color.DARK_GRAY);
		lblExpMinimaOferta.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblExpMinimaOferta.setBounds(230, 504, 448, 47);
		pnlVistaOferta.add(lblExpMinimaOferta);

		JLabel lblConocimientos = new JLabel("Conocimientos:");
		lblConocimientos.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/idea.png")));
		lblConocimientos.setForeground(Color.BLACK);
		lblConocimientos.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblConocimientos.setBounds(10, 562, 210, 47);
		pnlVistaOferta.add(lblConocimientos);

		lblConocimientosOferta = new JLabel("ConocimientosOferta");
		lblConocimientosOferta.setForeground(Color.DARK_GRAY);
		lblConocimientosOferta.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblConocimientosOferta.setBounds(230, 562, 448, 47);
		pnlVistaOferta.add(lblConocimientosOferta);

		JLabel lblLicenciaDeConducir = new JLabel("Licencia de conducir:");
		lblLicenciaDeConducir.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/driving-licence.png")));
		lblLicenciaDeConducir.setForeground(Color.BLACK);
		lblLicenciaDeConducir.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblLicenciaDeConducir.setBounds(10, 620, 210, 47);
		pnlVistaOferta.add(lblLicenciaDeConducir);

		lblLicConducirOferta = new JLabel("LicConducirOferta");
		lblLicConducirOferta.setForeground(Color.DARK_GRAY);
		lblLicConducirOferta.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblLicConducirOferta.setBounds(230, 620, 448, 47);
		pnlVistaOferta.add(lblLicConducirOferta);

		JLabel lblDisponibilidadDeMovilizarse = new JLabel("Disp. de movilizarse:");
		lblDisponibilidadDeMovilizarse.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/position.png")));
		lblDisponibilidadDeMovilizarse.setForeground(Color.BLACK);
		lblDisponibilidadDeMovilizarse.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblDisponibilidadDeMovilizarse.setBounds(10, 678, 210, 47);
		pnlVistaOferta.add(lblDisponibilidadDeMovilizarse);

		lblMovilizarseOferta = new JLabel("MovilizarseOferta");
		lblMovilizarseOferta.setForeground(Color.DARK_GRAY);
		lblMovilizarseOferta.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblMovilizarseOferta.setBounds(230, 678, 448, 47);
		pnlVistaOferta.add(lblMovilizarseOferta);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(44, 62, 80));
		panel.setBounds(967, 736, 281, 109);
		pnlVistaOferta.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Solicitar");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// realizar solicitud de parte de cliente
				/* if (persActual != null && ofertaSelected != null) { // validar que no esten vacios tanto el campo de
																	// personaActual como el de ofertaSeleccionada
					if (persActual.contieneOferta(ofertaSelected)) { // validar que no tenga esa oferta en alguna
																		// solicitud
						String codSoli = Bolsa.getInstancia().generarCodigoSolicitud();
						Solicitud soli = new Solicitud(codSoli, new Date(), "ACTIVA", persActual, ofertaSelected); // crear
																													// solicitud

						Bolsa.getInstancia().insertarSolicitud(soli); // insertar en la lista

						JOptionPane.showMessageDialog(null, "¡Solicitud realizada con éxito!", "Informacion",
								JOptionPane.INFORMATION_MESSAGE);
						jtpDescripcionOferta.setSelectedIndex(0); // regresar a seleccionar oferta
					} else {
						JOptionPane.showMessageDialog(null, "Error: Oferta solicitada previamente", "Error",
								JOptionPane.WARNING_MESSAGE);
					}
				} */
			}
		});
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(0, 11, 281, 87);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Salario:");
		lblNewLabel_2.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/money.png")));
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 202, 117, 40);
		pnlVistaOferta.add(lblNewLabel_2);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/briefcase.png")));
		lblTipo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblTipo.setBounds(10, 253, 129, 27);
		pnlVistaOferta.add(lblTipo);

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/info.png")));
		lblDescripcin.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblDescripcin.setBounds(507, 215, 188, 27);
		pnlVistaOferta.add(lblDescripcin);

		JLabel lblModalidad = new JLabel("Modalidad:");
		lblModalidad.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/building.png")));
		lblModalidad.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblModalidad.setBounds(10, 297, 129, 27);
		pnlVistaOferta.add(lblModalidad);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 435, 644, 13);
		pnlVistaOferta.add(separator_1);

		JLabel lblHorario = new JLabel("Horario:");
		lblHorario.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/clock.png")));
		lblHorario.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblHorario.setBounds(10, 340, 129, 27);
		pnlVistaOferta.add(lblHorario);

		lblHorariosOferta = new JLabel("HorarioOferta");
		lblHorariosOferta.setForeground(Color.DARK_GRAY);
		lblHorariosOferta.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblHorariosOferta.setBounds(149, 330, 405, 47);
		pnlVistaOferta.add(lblHorariosOferta);

		JLabel btnCerrarVistaOferta = new JLabel("");
		btnCerrarVistaOferta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtpDescripcionOferta.setSelectedIndex(0);
				ofertaSelected = null;
			}
		});
		btnCerrarVistaOferta.setHorizontalAlignment(SwingConstants.CENTER);
		btnCerrarVistaOferta.setIcon(new ImageIcon(MenuCandidatos.class.getResource("/img/close.png")));
		btnCerrarVistaOferta.setBounds(1171, 11, 77, 73);
		pnlVistaOferta.add(btnCerrarVistaOferta);
	}
}
