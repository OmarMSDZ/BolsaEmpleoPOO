package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Persona;

import java.awt.Color;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;

public class EditarInfoPersonalCandidato extends JDialog {

	private final JPanel contentPanel = new JPanel();
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
	private JLabel lblNivelEducativo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditarInfoPersonalCandidato dialog = new EditarInfoPersonalCandidato(null, 0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditarInfoPersonalCandidato(Persona persActiva, int tipo ) {
		setResizable(false);
		setTitle("Laborea - Editar informaci\u00F3n personal/profesional");
		setBounds(100, 100, 494, 542);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			jtpEditarInformacion = new JTabbedPane(JTabbedPane.TOP);
			jtpEditarInformacion.setEnabled(false);
			jtpEditarInformacion.setBounds(0, -25, 478, 495);
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
					txtNombre.setBounds(10, 100, 453, 20);
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
					txtApellido.setBounds(10, 156, 453, 20);
					pnlInfoPersonal.add(txtApellido);
				}
				{
					JLabel lblTelfono = new JLabel("Tel\u00E9fono");
					lblTelfono.setFont(new Font("Segoe UI", Font.PLAIN, 18));
					lblTelfono.setBounds(10, 187, 189, 32);
					pnlInfoPersonal.add(lblTelfono);
				}
				{
					txtTelefono = new JTextField();
					txtTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 12));
					txtTelefono.setColumns(10);
					txtTelefono.setBounds(10, 217, 453, 20);
					pnlInfoPersonal.add(txtTelefono);
				}
				{
					JLabel label = new JLabel("Provincia");
					label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
					label.setBounds(10, 238, 189, 32);
					pnlInfoPersonal.add(label);
				}
				{
					JComboBox cbxProvincia = new JComboBox();
					cbxProvincia.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Azua", "Bahoruco", "Barahona", "Dajab\u00F3n", "Duarte", "El Seibo", "El\u00EDas Pi\u00F1a", "Espaillat", "Hato Mayor", "Hermanas Mirabal", "Independencia", "La Altagracia", "La Romana", "La Vega", "Mar\u00EDa Trinidad S\u00E1nchez", "Monse\u00F1or Nouel", "Monte Cristi", "Monte Plata", "Pedernales", "Peravia", "Puerto Plata", "Saman\u00E1", "S\u00E1nchez Ram\u00EDrez", "San Crist\u00F3bal", "San Jos\u00E9 de Ocoa", "San Juan", "San Pedro de Macor\u00EDs", "Santiago", "Santiago Rodr\u00EDguez", "Santo Domingo", "Valverde"}));
					cbxProvincia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
					cbxProvincia.setBackground(Color.WHITE);
					cbxProvincia.setBounds(10, 276, 453, 20);
					pnlInfoPersonal.add(cbxProvincia);
				}
				{
					JLabel label = new JLabel("Municipio");
					label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
					label.setBounds(10, 295, 189, 32);
					pnlInfoPersonal.add(label);
				}
				{
					txtMunicipio = new JTextField();
					txtMunicipio.setFont(new Font("Segoe UI", Font.PLAIN, 12));
					txtMunicipio.setColumns(10);
					txtMunicipio.setBounds(10, 326, 453, 20);
					pnlInfoPersonal.add(txtMunicipio);
				}
				{
					JLabel lblDireccin = new JLabel("Direcci\u00F3n");
					lblDireccin.setFont(new Font("Segoe UI", Font.PLAIN, 18));
					lblDireccin.setBounds(10, 345, 189, 32);
					pnlInfoPersonal.add(lblDireccin);
				}
				{
					txtDireccion = new JTextField();
					txtDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
					txtDireccion.setColumns(10);
					txtDireccion.setBounds(10, 373, 453, 20);
					pnlInfoPersonal.add(txtDireccion);
				}
				{
					JLabel lblCorreoElectrnico = new JLabel("Correo Electr\u00F3nico");
					lblCorreoElectrnico.setFont(new Font("Segoe UI", Font.PLAIN, 18));
					lblCorreoElectrnico.setBounds(10, 393, 189, 32);
					pnlInfoPersonal.add(lblCorreoElectrnico);
				}
				{
					txtCorreo = new JTextField();
					txtCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
					txtCorreo.setColumns(10);
					txtCorreo.setBounds(10, 425, 453, 20);
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
			}
			{
				JPanel pnlInfoProfesional = new JPanel();
				pnlInfoProfesional.setBackground(Color.WHITE);
				jtpEditarInformacion.addTab("New tab", null, pnlInfoProfesional, null);
				pnlInfoProfesional.setLayout(null);
				
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(Color.WHITE);
				panel_1.setBounds(0, 331, 473, 46);
				pnlInfoProfesional.add(panel_1);
				
				JPanel panel = new JPanel();
				panel.setBackground(Color.WHITE);
				panel.setBounds(0, 150, 473, 46);
				pnlInfoProfesional.add(panel);
				{
					JLabel label = new JLabel("");
					label.setIcon(new ImageIcon(EditarInfoPersonalCandidato.class.getResource("/img/Laborea.png")));
					label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
					label.setBounds(-47, 11, 189, 46);
					pnlInfoProfesional.add(label);
				}
				{
					JSeparator separator = new JSeparator();
					separator.setForeground(Color.BLACK);
					separator.setBounds(0, 59, 473, 11);
					pnlInfoProfesional.add(separator);
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
					jtpNivelEducativo.setBounds(0, 166, 473, 169);
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
							cbxCarrera.setFont(new Font("Segoe UI", Font.PLAIN, 12));
							cbxCarrera.setBounds(10, 47, 241, 20);
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
						spnAniosExp.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
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
						
						JCheckBox chkbxOfimatica = new JCheckBox("Ofim\u00E1tica");
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
					lblNivelEducativo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
					lblNivelEducativo.setBounds(10, 117, 146, 25);
					pnlInfoProfesional.add(lblNivelEducativo);
				}
				
				JLabel lblNoEditable = new JLabel("*No editable");
				lblNoEditable.setForeground(new Color(250, 128, 114));
				lblNoEditable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				lblNoEditable.setBounds(147, 81, 146, 25);
				pnlInfoProfesional.add(lblNoEditable);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnGuardar = new JButton("Guardar");
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
		//mostrar pantalla segun lo que se desee editar
		if(tipo==0) {
			jtpEditarInformacion.setSelectedIndex(0);
		} else if(tipo==1) {
			jtpEditarInformacion.setSelectedIndex(1);
		}
	}
}
