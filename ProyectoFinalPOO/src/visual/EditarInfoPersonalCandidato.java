package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import logica.Bolsa;
import logica.Obrero;
import logica.Persona;
import logica.Solicitud;
import logica.TecnicoSuperior;
import logica.Universitario;

public class EditarInfoPersonalCandidato extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static Persona persActual;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtMunicipio;
	private JTextField txtDireccion;
	private JTextField txtCorreo;
	private JTabbedPane jtpNivelEducativo;
	private JTabbedPane jtpEditarInformacion;
	private JTextField txtTecnico;
	private JComboBox cbxCarrera;
	private JSpinner spnAniosExp;
	private JCheckBox chkbxVentas;
	private JCheckBox chkbxMecanica;
	private JCheckBox chkbxElectricidad;
	private JCheckBox chkbxSeguridad;
	private JCheckBox chkbxMantenimiento;
	private JCheckBox chkbxConduccion;
	private JCheckBox chkbxLimpieza;
	private JComboBox cbxProvincia;
	private JCheckBox chkbxOfimatica;
	private JLabel lblNivelEducativo;
	private JTextField txtCedula;
	private JRadioButton rdbtnSexoF;
	private JRadioButton rdbtnSexoM;
	private JSpinner spnFechaNacimiento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditarInfoPersonalCandidato dialog = new EditarInfoPersonalCandidato(0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditarInfoPersonalCandidato(int tipo) {
		setResizable(false);
		  Persona actual = (Persona) Bolsa.getUsuarioActivo();

		    if (actual != null) {
		        persActual = actual;
		    } else {
		    	//para pruebas
		        persActual = new Universitario(
		            "U-1", "Omar Jadis", "1234", "8091231234", "omarM@gmail.com",
		            "Santiago", "Santiago", "Su casa", true, "Morales Diaz", "M",
		            new Date(), "40215233418", false, new ArrayList<Solicitud>(),
		            "Ingeniería en Sistemas Computacionales"
		        );

		        Bolsa.getInstancia().insertarUsuario(persActual);
		        Bolsa.setUsuarioActivo(persActual); // insertar y establecer como activo para pruebas
		        persActual = (Persona) Bolsa.getUsuarioActivo();
		    }
		 
		setTitle("Laborea - Editar informaci\u00F3n personal/profesional");
		setBounds(100, 100, 494, 570);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			jtpEditarInformacion = new JTabbedPane(JTabbedPane.TOP);
			jtpEditarInformacion.setEnabled(false);
			jtpEditarInformacion.setBounds(0, -25, 478, 520);
			contentPanel.add(jtpEditarInformacion);
			{
				JPanel pnlInfoPersonal = new JPanel();
				pnlInfoPersonal.setBackground(Color.WHITE);
				jtpEditarInformacion.addTab("New tab", null, pnlInfoPersonal, null);
				pnlInfoPersonal.setLayout(null);
				{
					JLabel label = new JLabel("Nombre/s");
					label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
					label.setBounds(10, 68, 189, 32);
					pnlInfoPersonal.add(label);
				}
				{
					txtNombre = new JTextField();
					txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
					txtNombre.setColumns(10);
					txtNombre.setBounds(10, 100, 233, 20);
					pnlInfoPersonal.add(txtNombre);
				}
				{
					JLabel label = new JLabel("Apellido/s");
					label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
					label.setBounds(10, 120, 189, 32);
					pnlInfoPersonal.add(label);
				}
				{
					txtApellido = new JTextField();
					txtApellido.setFont(new Font("Segoe UI", Font.PLAIN, 12));
					txtApellido.setColumns(10);
					txtApellido.setBounds(10, 156, 233, 20);
					pnlInfoPersonal.add(txtApellido);
				}
				{
					JLabel lblTelfono = new JLabel("Tel\u00E9fono");
					lblTelfono.setFont(new Font("Segoe UI", Font.PLAIN, 18));
					lblTelfono.setBounds(10, 223, 189, 32);
					pnlInfoPersonal.add(lblTelfono);
				}
				{
					txtTelefono = new JTextField();
					txtTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 12));
					txtTelefono.setColumns(10);
					txtTelefono.setBounds(10, 253, 453, 20);
					pnlInfoPersonal.add(txtTelefono);
				}
				{
					JLabel label = new JLabel("Provincia");
					label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
					label.setBounds(10, 274, 189, 32);
					pnlInfoPersonal.add(label);
				}
				{
					cbxProvincia = new JComboBox();
					cbxProvincia.setModel(new DefaultComboBoxModel(new String[] { "<Seleccione>", "Azua", "Bahoruco",
							"Barahona", "Dajab\u00F3n", "Duarte", "El Seibo", "El\u00EDas Pi\u00F1a", "Espaillat",
							"Hato Mayor", "Hermanas Mirabal", "Independencia", "La Altagracia", "La Romana", "La Vega",
							"Mar\u00EDa Trinidad S\u00E1nchez", "Monse\u00F1or Nouel", "Monte Cristi", "Monte Plata",
							"Pedernales", "Peravia", "Puerto Plata", "Saman\u00E1", "S\u00E1nchez Ram\u00EDrez",
							"San Crist\u00F3bal", "San Jos\u00E9 de Ocoa", "San Juan", "San Pedro de Macor\u00EDs",
							"Santiago", "Santiago Rodr\u00EDguez", "Santo Domingo", "Valverde" }));
					cbxProvincia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
					cbxProvincia.setBackground(Color.WHITE);
					cbxProvincia.setBounds(10, 312, 453, 20);
					pnlInfoPersonal.add(cbxProvincia);
				}
				{
					JLabel label = new JLabel("Municipio");
					label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
					label.setBounds(10, 331, 189, 32);
					pnlInfoPersonal.add(label);
				}
				{
					txtMunicipio = new JTextField();
					txtMunicipio.setFont(new Font("Segoe UI", Font.PLAIN, 12));
					txtMunicipio.setColumns(10);
					txtMunicipio.setBounds(10, 362, 453, 20);
					pnlInfoPersonal.add(txtMunicipio);
				}
				{
					JLabel lblDireccin = new JLabel("Direcci\u00F3n");
					lblDireccin.setFont(new Font("Segoe UI", Font.PLAIN, 18));
					lblDireccin.setBounds(10, 381, 189, 32);
					pnlInfoPersonal.add(lblDireccin);
				}
				{
					txtDireccion = new JTextField();
					txtDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
					txtDireccion.setColumns(10);
					txtDireccion.setBounds(10, 409, 453, 20);
					pnlInfoPersonal.add(txtDireccion);
				}
				{
					JLabel lblCorreoElectrnico = new JLabel("Correo Electr\u00F3nico");
					lblCorreoElectrnico.setFont(new Font("Segoe UI", Font.PLAIN, 18));
					lblCorreoElectrnico.setBounds(10, 429, 189, 32);
					pnlInfoPersonal.add(lblCorreoElectrnico);
				}
				{
					txtCorreo = new JTextField();
					txtCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
					txtCorreo.setColumns(10);
					txtCorreo.setBounds(10, 461, 453, 20);
					pnlInfoPersonal.add(txtCorreo);
				}
				{
					JLabel label = new JLabel("");
					label.setIcon(new ImageIcon(EditarInfoPersonalCandidato.class.getResource("/img/Laborea.png")));
					label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
					label.setBounds(-47, 11, 189, 46);
					pnlInfoPersonal.add(label);
				}
				{
					JLabel lblEditarInformacionPersonal = new JLabel("Editar Informaci\u00F3n Personal");
					lblEditarInformacionPersonal.setFont(new Font("Segoe UI", Font.PLAIN, 18));
					lblEditarInformacionPersonal.setBounds(230, 11, 233, 46);
					pnlInfoPersonal.add(lblEditarInformacionPersonal);
				}
				{
					JSeparator separator = new JSeparator();
					separator.setForeground(Color.BLACK);
					separator.setBounds(0, 59, 473, 11);
					pnlInfoPersonal.add(separator);
				}

				JLabel lblCedula = new JLabel("C\u00E9dula");
				lblCedula.setFont(new Font("Segoe UI", Font.PLAIN, 18));
				lblCedula.setBounds(253, 64, 189, 32);
				pnlInfoPersonal.add(lblCedula);

				txtCedula = new JTextField();
				txtCedula.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				txtCedula.setColumns(10);
				txtCedula.setBounds(253, 100, 210, 20);
				pnlInfoPersonal.add(txtCedula);

				JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
				lblFechaDeNacimiento.setFont(new Font("Segoe UI", Font.PLAIN, 18));
				lblFechaDeNacimiento.setBounds(253, 120, 189, 32);
				pnlInfoPersonal.add(lblFechaDeNacimiento);

				spnFechaNacimiento = new JSpinner();
				spnFechaNacimiento.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				spnFechaNacimiento.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_YEAR));
				JSpinner.DateEditor de_spnFechaNacimiento = new JSpinner.DateEditor(spnFechaNacimiento, "dd/MM/yyyy");
				spnFechaNacimiento.setEditor(de_spnFechaNacimiento);

				spnFechaNacimiento.setBounds(253, 156, 210, 20);
				pnlInfoPersonal.add(spnFechaNacimiento);

				JLabel lblSexo = new JLabel("Sexo");
				lblSexo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
				lblSexo.setBounds(10, 187, 101, 32);
				pnlInfoPersonal.add(lblSexo);

				rdbtnSexoM = new JRadioButton("Masculino");
				rdbtnSexoM.setSelected(true);
				rdbtnSexoM.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (rdbtnSexoM.isSelected()) {
							rdbtnSexoF.setSelected(false);
						}
					}
				});
				rdbtnSexoM.setBackground(Color.WHITE);
				rdbtnSexoM.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				rdbtnSexoM.setBounds(67, 196, 109, 23);
				pnlInfoPersonal.add(rdbtnSexoM);

				rdbtnSexoF = new JRadioButton("Femenino");
				rdbtnSexoF.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (rdbtnSexoF.isSelected()) {
							rdbtnSexoM.setSelected(false);
						}
					}
				});
				rdbtnSexoF.setBackground(Color.WHITE);
				rdbtnSexoF.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				rdbtnSexoF.setBounds(201, 196, 109, 23);
				pnlInfoPersonal.add(rdbtnSexoF);
			}
			{
				JPanel pnlInfoProfesional = new JPanel();
				pnlInfoProfesional.setBackground(Color.WHITE);
				jtpEditarInformacion.addTab("New tab", null, pnlInfoProfesional, null);
				pnlInfoProfesional.setLayout(null);
				
				JSeparator separator_1 = new JSeparator();
				separator_1.setForeground(Color.BLACK);
				separator_1.setBounds(0, 153, 473, 11);
				pnlInfoProfesional.add(separator_1);
				{
					JSeparator separator = new JSeparator();
					separator.setForeground(Color.BLACK);
					separator.setBounds(0, 59, 473, 11);
					pnlInfoProfesional.add(separator);
				}

				JPanel panel_1 = new JPanel();
				panel_1.setBackground(Color.WHITE);
				panel_1.setBounds(0, 318, 473, 19);
				pnlInfoProfesional.add(panel_1);

				JPanel panel = new JPanel();
				panel.setBackground(Color.WHITE);
				panel.setBounds(0, 153, 473, 30);
				pnlInfoProfesional.add(panel);
				{
					JLabel label = new JLabel("");
					label.setIcon(new ImageIcon(EditarInfoPersonalCandidato.class.getResource("/img/Laborea.png")));
					label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
					label.setBounds(-47, 11, 189, 46);
					pnlInfoProfesional.add(label);
				}
				{
					JLabel lblEditarInformacinProfesional = new JLabel("Editar Informaci\u00F3n Profesional");
					lblEditarInformacinProfesional.setFont(new Font("Segoe UI", Font.PLAIN, 18));
					lblEditarInformacinProfesional.setBounds(214, 11, 249, 46);
					pnlInfoProfesional.add(lblEditarInformacinProfesional);
				}
				{
					jtpNivelEducativo = new JTabbedPane(JTabbedPane.TOP);
					jtpNivelEducativo.setEnabled(false);
					jtpNivelEducativo.setBounds(0, 153, 473, 169);
					pnlInfoProfesional.add(jtpNivelEducativo);
					{
						JPanel pnlUniv = new JPanel();
						pnlUniv.setBackground(Color.WHITE);
						jtpNivelEducativo.addTab("New tab", null, pnlUniv, null);
						pnlUniv.setLayout(null);
						{
							JLabel lblCarrera = new JLabel("Carrera");
							lblCarrera.setFont(new Font("Segoe UI", Font.PLAIN, 18));
							lblCarrera.setBounds(10, 11, 146, 25);
							pnlUniv.add(lblCarrera);
						}
						{
							cbxCarrera = new JComboBox();
							cbxCarrera.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Administraci\u00F3n de Empresas", "Agronom\u00EDa", "Antropolog\u00EDa", "Animaci\u00F3n Digital", "Arquitectura", "Artes Esc\u00E9nicas", "Artes Pl\u00E1sticas", "Astrobiolog\u00EDa", "Astronom\u00EDa", "Bellas Artes", "Bioinform\u00E1tica", "Biolog\u00EDa", "Biomedicina", "Biotecnolog\u00EDa", "Bioqu\u00EDmica", "Ciencia de Datos", "Ciencia y Tecnolog\u00EDa de Materiales Avanzados", "Ciencias Ambientales", "Ciencias de la Computaci\u00F3n", "Ciencias de la Comunicaci\u00F3n", "Ciencias de la Tierra", "Ciencias Forenses", "Ciencias Pol\u00EDticas", "Comercio Internacional", "Computaci\u00F3n Cu\u00E1ntica", "Contabilidad", "Criminolog\u00EDa", "Ciberseguridad", "Cine", "Danza", "Derecho", "Dise\u00F1o de Interiores", "Dise\u00F1o de Moda", "Dise\u00F1o Gr\u00E1fico", "Dise\u00F1o Industrial", "Dise\u00F1o Multimedia", "Dise\u00F1o UX-UI", "Dise\u00F1o y Gesti\u00F3n de Ciudades Inteligentes", "Econom\u00EDa", "Educaci\u00F3n", "Enfermer\u00EDa", "Entretenimiento Digital", "Estad\u00EDstica", "Farmacia", "Filosof\u00EDa", "Fisioterapia", "Finanzas", "Foniatr\u00EDa", "Fotograf\u00EDa", "Geograf\u00EDa", "Geof\u00EDsica", "Geolog\u00EDa", "Gen\u00E9tica", "Gerontolog\u00EDa", "Gesti\u00F3n Ambiental", "Gesti\u00F3n de la Innovaci\u00F3n y Emprendimiento", "Gesti\u00F3n de Riesgos y Desastres", "Gesti\u00F3n P\u00FAblica", "Historia", "Imagenolog\u00EDa", "Ingenier\u00EDa Aeroespacial", "Ingenier\u00EDa Agr\u00F3noma", "Ingenier\u00EDa Ambiental", "Ingenier\u00EDa Biom\u00E9dica", "Ingenier\u00EDa Civil", "Ingenier\u00EDa de Materiales", "Ingenier\u00EDa El\u00E9ctrica", "Ingenier\u00EDa Electr\u00F3nica", "Ingenier\u00EDa en Automatizaci\u00F3n y Control", "Ingenier\u00EDa en Energ\u00EDas Renovables", "Ingenier\u00EDa en Inteligencia Artificial", "Ingenier\u00EDa en Mecatr\u00F3nica", "Ingenier\u00EDa en Minas y Metalurgia", "Ingenier\u00EDa en Nanotecnolog\u00EDa", "Ingenier\u00EDa en Sistemas Computacionales", "Ingenier\u00EDa en Software", "Ingenier\u00EDa en Telecomunicaciones", "Ingenier\u00EDa en Transporte y V\u00EDas", "Ingenier\u00EDa Forestal", "Ingenier\u00EDa Gen\u00E9tica", "Ingenier\u00EDa Industrial", "Ingenier\u00EDa Mec\u00E1nica", "Ingenier\u00EDa Naval", "Ingenier\u00EDa Petrolera", "Ingenier\u00EDa Qu\u00EDmica", "Ingenier\u00EDa en Energ\u00EDa y Sustentabilidad", "Lenguas Modernas", "Ling\u00FC\u00EDstica", "Log\u00EDstica y Transporte", "Logopedia", "Laboratorio Cl\u00EDnico", "Matem\u00E1ticas", "Marketing", "Medicina", "Medicina Veterinaria", "Mercadotecnia", "M\u00FAsica", "Nanotecnolog\u00EDa Aplicada", "Negocios Internacionales", "Neurociencias", "Nutrici\u00F3n", "Oceanograf\u00EDa", "Odontolog\u00EDa", "Optometr\u00EDa", "Paleontolog\u00EDa", "Pedagog\u00EDa", "Periodismo", "Producci\u00F3n Audiovisual", "Publicidad y Relaciones P\u00FAblicas", "Psicolog\u00EDa", "Psicolog\u00EDa Cl\u00EDnica", "Qu\u00EDmica", "Realidad Virtual y Aumentada", "Relaciones Internacionales", "Rob\u00F3tica", "Sociolog\u00EDa", "Tecnolog\u00EDa de Alimentos", "Tecnolog\u00EDa M\u00E9dica", "Teatro", "Teolog\u00EDa", "Traducci\u00F3n e Interpretaci\u00F3n", "Trabajo Social", "Turismo y Hoteler\u00EDa", "Videojuegos", "Zootecnia"}));
							cbxCarrera.setFont(new Font("Segoe UI", Font.PLAIN, 12));
							cbxCarrera.setBounds(10, 47, 448, 25);
							pnlUniv.add(cbxCarrera);
						}
					}
					{
						JPanel pnlTec = new JPanel();
						pnlTec.setBackground(Color.WHITE);
						jtpNivelEducativo.addTab("New tab", null, pnlTec, null);
						pnlTec.setLayout(null);
						{
							JLabel lblTcnico = new JLabel("T\u00E9cnico");
							lblTcnico.setFont(new Font("Segoe UI", Font.PLAIN, 18));
							lblTcnico.setBounds(10, 11, 146, 25);
							pnlTec.add(lblTcnico);
						}
						{
							txtTecnico = new JTextField();
							txtTecnico.setFont(new Font("Segoe UI", Font.PLAIN, 12));
							txtTecnico.setBounds(10, 47, 255, 20);
							pnlTec.add(txtTecnico);
							txtTecnico.setColumns(10);
						}
						{
							JLabel lblAosDeExperiencia = new JLabel("A\u00F1os de experiencia");
							lblAosDeExperiencia.setFont(new Font("Segoe UI", Font.PLAIN, 18));
							lblAosDeExperiencia.setBounds(10, 78, 210, 25);
							pnlTec.add(lblAosDeExperiencia);
						}

						spnAniosExp = new JSpinner();
						spnAniosExp.setFont(new Font("Segoe UI", Font.PLAIN, 12));
						spnAniosExp
								.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
						spnAniosExp.setBounds(10, 110, 255, 20);
						pnlTec.add(spnAniosExp);
					}
					{
						JPanel pnlObrero = new JPanel();
						pnlObrero.setBackground(Color.WHITE);
						jtpNivelEducativo.addTab("New tab", null, pnlObrero, null);
						pnlObrero.setLayout(null);

						JLabel lblHabilidades = new JLabel("Habilidades");
						lblHabilidades.setFont(new Font("Segoe UI", Font.PLAIN, 18));
						lblHabilidades.setBounds(10, 11, 146, 25);
						pnlObrero.add(lblHabilidades);

						chkbxVentas = new JCheckBox("Ventas");
						chkbxVentas.setFont(new Font("Segoe UI", Font.PLAIN, 12));
						chkbxVentas.setBackground(Color.WHITE);
						chkbxVentas.setBounds(10, 51, 81, 23);
						pnlObrero.add(chkbxVentas);

						chkbxMecanica = new JCheckBox("Mec\u00E1nica");
						chkbxMecanica.setFont(new Font("Segoe UI", Font.PLAIN, 12));
						chkbxMecanica.setBackground(Color.WHITE);
						chkbxMecanica.setBounds(10, 80, 81, 23);
						pnlObrero.add(chkbxMecanica);

						chkbxOfimatica = new JCheckBox("Ofim\u00E1tica");
						chkbxOfimatica.setFont(new Font("Segoe UI", Font.PLAIN, 12));
						chkbxOfimatica.setBackground(Color.WHITE);
						chkbxOfimatica.setBounds(101, 51, 119, 23);
						pnlObrero.add(chkbxOfimatica);

						chkbxElectricidad = new JCheckBox("Electricidad");
						chkbxElectricidad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
						chkbxElectricidad.setBackground(Color.WHITE);
						chkbxElectricidad.setBounds(234, 51, 106, 23);
						pnlObrero.add(chkbxElectricidad);

						chkbxSeguridad = new JCheckBox("Seguridad");
						chkbxSeguridad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
						chkbxSeguridad.setBackground(Color.WHITE);
						chkbxSeguridad.setBounds(234, 80, 106, 23);
						pnlObrero.add(chkbxSeguridad);

						chkbxMantenimiento = new JCheckBox("Mantenimiento");
						chkbxMantenimiento.setFont(new Font("Segoe UI", Font.PLAIN, 12));
						chkbxMantenimiento.setBackground(Color.WHITE);
						chkbxMantenimiento.setBounds(101, 80, 119, 23);
						pnlObrero.add(chkbxMantenimiento);

						chkbxConduccion = new JCheckBox("Conducci\u00F3n");
						chkbxConduccion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
						chkbxConduccion.setBackground(Color.WHITE);
						chkbxConduccion.setBounds(343, 51, 106, 23);
						pnlObrero.add(chkbxConduccion);

						chkbxLimpieza = new JCheckBox("Limpieza");
						chkbxLimpieza.setFont(new Font("Segoe UI", Font.PLAIN, 12));
						chkbxLimpieza.setBackground(Color.WHITE);
						chkbxLimpieza.setBounds(343, 80, 119, 23);
						pnlObrero.add(chkbxLimpieza);
					}
				}
				{
					JLabel lblNewLabel = new JLabel("Nivel Educativo");
					lblNewLabel.setForeground(Color.DARK_GRAY);
					lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
					lblNewLabel.setBounds(10, 81, 146, 25);
					pnlInfoProfesional.add(lblNewLabel);
				}
				{
					lblNivelEducativo = new JLabel("Nivel Educativo");
					lblNivelEducativo.setForeground(Color.BLACK);
					lblNivelEducativo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
					lblNivelEducativo.setBounds(10, 117, 214, 25);
					pnlInfoProfesional.add(lblNivelEducativo);
				}
				{
					JLabel lblnoModificable = new JLabel("**No modificable");
					lblnoModificable.setForeground(Color.RED);
					lblnoModificable.setFont(new Font("Segoe UI", Font.PLAIN, 11));
					lblnoModificable.setBounds(142, 84, 146, 25);
					pnlInfoProfesional.add(lblnoModificable);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnGuardar = new JButton("Modificar");
				btnGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (persActual != null) {
							// Editar info personal
							if (jtpEditarInformacion.getSelectedIndex() == 0) {
								if (validar(0)) {

									persActual.setNombre(txtNombre.getText());
									persActual.setApellidos(txtApellido.getText());
									persActual.setCedula(txtCedula.getText());
									if (rdbtnSexoM.isSelected()) {
										persActual.setSexo("M");
									} else if (rdbtnSexoF.isSelected()) {
										persActual.setSexo("F");
									}
									persActual.setFechaNacimiento((Date) spnFechaNacimiento.getValue());
									persActual.setTelefono(txtTelefono.getText());
									persActual.setProvincia(cbxProvincia.getSelectedItem().toString());
									persActual.setMunicipio(txtMunicipio.getText());
									persActual.setDireccion(txtDireccion.getText());
									persActual.setCorreoElectronico(txtCorreo.getText());

									Bolsa.getInstancia().modificarUsuario(persActual);
									JOptionPane.showMessageDialog(null, "¡Información personal modificada con éxito!",
											"Información", JOptionPane.INFORMATION_MESSAGE);
									dispose();// cerrar ventana

								} else {
									JOptionPane.showMessageDialog(null, "¡Complete correctamente los campos!", "Alerta",
											JOptionPane.WARNING_MESSAGE);
								}
							}
							// editar info profesional
							else if (jtpEditarInformacion.getSelectedIndex() == 1) {
								if (validar(1)) {
									if (persActual instanceof Universitario) {
										((Universitario) persActual)
												.setCarrera(cbxCarrera.getSelectedItem().toString());
									} else if (persActual instanceof TecnicoSuperior) {
										((TecnicoSuperior) persActual).setTecnico(txtTecnico.getText());
										((TecnicoSuperior) persActual)
												.setAniosExperiencia((Integer) spnAniosExp.getValue());
									} else if (persActual instanceof Obrero) {
										((Obrero) persActual).setVentas(chkbxVentas.isSelected());
										((Obrero) persActual).setMecanica(chkbxMecanica.isSelected());
										((Obrero) persActual).setOfimatica(chkbxOfimatica.isSelected());
										((Obrero) persActual).setMantenimiento(chkbxMantenimiento.isSelected());
										((Obrero) persActual).setElectricidad(chkbxElectricidad.isSelected());
										((Obrero) persActual).setSeguridad(chkbxSeguridad.isSelected());
										((Obrero) persActual).setConduccion(chkbxConduccion.isSelected());
										((Obrero) persActual).setLimpieza(chkbxLimpieza.isSelected());

									}
									Bolsa.getInstancia().modificarUsuario(persActual);
									JOptionPane.showMessageDialog(null,
											"¡Información profesional modificada con éxito!", "Información",
											JOptionPane.INFORMATION_MESSAGE);
									dispose();// cerrar ventana
								} else {
									JOptionPane.showMessageDialog(null, "¡Complete correctamente los campos!", "Alerta",
											JOptionPane.WARNING_MESSAGE);
								}
							}
						}
					}

				});
				btnGuardar.setFocusable(false);
				btnGuardar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				btnGuardar.setBackground(Color.WHITE);
				btnGuardar.setActionCommand("OK");
				buttonPane.add(btnGuardar);
				getRootPane().setDefaultButton(btnGuardar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setFocusable(false);
				btnCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setBackground(Color.WHITE);
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		// mostrar pantalla segun lo que se desee editar
		if (tipo == 0) {
			cargarDatos(0);
			jtpEditarInformacion.setSelectedIndex(0);
		} else if (tipo == 1) {
			cargarDatos(1);
			jtpEditarInformacion.setSelectedIndex(1);
		}

	}

	// Validar campos
	private boolean validar(int tipo) {
		boolean valido = false;
		if (tipo == 0) {

			// tipo 0, parte de informacion personal
			if (!txtNombre.getText().equals("") && !txtApellido.getText().equals("") && !txtCedula.getText().equals("")
					&& (rdbtnSexoM.isSelected() || rdbtnSexoF.isSelected()) && !txtTelefono.getText().equals("")
					&& cbxProvincia.getSelectedIndex() > 0 && !txtMunicipio.getText().equals("")
					&& !txtDireccion.equals("") && !txtCorreo.getText().equals("")) {
				// validar fecha de nacimiento
				Date fechaNacimiento = (Date) spnFechaNacimiento.getValue(); // o el componente si tienes un
																				// JDateChooser
				LocalDate fechaNac = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate hoy = LocalDate.now();
				int edad = Period.between(fechaNac, hoy).getYears();
				if (edad >= 17) {
					valido = true;
				}

			}

		} else if (tipo == 1) { // tipo 1, parte de informacion profesional
			if (persActual instanceof Universitario) {
				if(cbxCarrera.getSelectedIndex()!=0) {
					valido = true;
				}
			} else if (persActual instanceof TecnicoSuperior) {
				if(!txtTecnico.getText().equals("") && (Integer)spnAniosExp.getValue()>=0) {
					valido = true;
				}
			} else if (persActual instanceof Obrero) {
				//debe tener al menos uno seleccionado
				if(chkbxVentas.isSelected() ||
						chkbxMecanica.isSelected()||
						chkbxMantenimiento.isSelected()||
						chkbxLimpieza.isSelected()||
						chkbxOfimatica.isSelected()||
						chkbxSeguridad.isSelected()||
						chkbxConduccion.isSelected()||
						chkbxElectricidad.isSelected()) {
					valido = true;
				}
			}
		}
		return valido;
	}

	// cargar datos al abrir
	private void cargarDatos(int tipo) {
		if (persActual != null) {
			if (tipo == 0) {
				txtNombre.setText(persActual.getNombre());
				txtApellido.setText(persActual.getApellidos());
				txtCedula.setText(persActual.getCedula());
				if (persActual.getSexo().equalsIgnoreCase("M")) {
					rdbtnSexoM.setSelected(true);
				} else {
					rdbtnSexoF.setSelected(true);
				}
				txtTelefono.setText(persActual.getTelefono());
				txtMunicipio.setText(persActual.getMunicipio());
				txtDireccion.setText(persActual.getDireccion());
				txtCorreo.setText(persActual.getCorreoElectronico());
				cbxProvincia.setSelectedItem(persActual.getProvincia());

				// obtener fecha de nacimiento
				spnFechaNacimiento.setValue(persActual.getFechaNacimiento());
				
			} else if (tipo == 1) {
				if (persActual instanceof Universitario) {
					// cargar info universitario
					lblNivelEducativo.setText("Universitario");
					jtpNivelEducativo.setSelectedIndex(0);

					cbxCarrera.setSelectedItem(((Universitario) persActual).getCarrera());
				} else if (persActual instanceof TecnicoSuperior) {
					// cargar info tecnico
					lblNivelEducativo.setText("Técnico Superior");
					jtpNivelEducativo.setSelectedIndex(1);

					txtTecnico.setText(((TecnicoSuperior) persActual).getTecnico());
					spnAniosExp.setValue(((TecnicoSuperior) persActual).getAniosExperiencia());
				} else if (persActual instanceof Obrero) {
					// cargar info obrero
					lblNivelEducativo.setText("Obrero");
					jtpNivelEducativo.setSelectedIndex(2);
					// activar checkboxes
					chkbxVentas.setSelected(((Obrero) persActual).isVentas());
					chkbxMecanica.setSelected(((Obrero) persActual).isMecanica());
					chkbxOfimatica.setSelected(((Obrero) persActual).isOfimatica());
					chkbxMantenimiento.setSelected(((Obrero) persActual).isMantenimiento());
					chkbxElectricidad.setSelected(((Obrero) persActual).isElectricidad());
					chkbxSeguridad.setSelected(((Obrero) persActual).isSeguridad());
					chkbxConduccion.setSelected(((Obrero) persActual).isConduccion());
					chkbxLimpieza.setSelected(((Obrero) persActual).isLimpieza());

				}

			}
		}
	}
}
