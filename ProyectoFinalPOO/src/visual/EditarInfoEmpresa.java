package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Bolsa;
import logica.Empresa;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;

public class EditarInfoEmpresa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtCorreo;
	private JTextField txtDireccion;
	private JTextField txtMunicipio;
	private JTextField txtTelefono;
	private JTextField txtRnc;
	private JTabbedPane jtpEditarInformacion;
	private JComboBox cbxProvincia;
	private static Empresa empActual;
	private JComboBox cbxTipoEmp;
	private JComboBox cbxSectorEmp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditarInfoEmpresa dialog = new EditarInfoEmpresa(0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditarInfoEmpresa(int tipo) {
		setTitle("Laborea - Editar informaci\u00F3n empresarial");

		setBounds(100, 100, 527, 587);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel pnlPrincipal = new JPanel();
			pnlPrincipal.setBackground(Color.WHITE);
			pnlPrincipal.setBounds(0, 0, 509, 541);
			contentPanel.add(pnlPrincipal);
			pnlPrincipal.setLayout(null);
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setBackground(Color.WHITE);
				buttonPane.setBounds(0, 493, 509, 48);
				pnlPrincipal.add(buttonPane);
				buttonPane.setLayout(null);
				{
					JButton btnModificar = new JButton("Modificar");
					btnModificar.setBackground(Color.WHITE);
					btnModificar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
					btnModificar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (Bolsa.getUsuarioActivo() != null) {

								Empresa aux = (Empresa) Bolsa.getUsuarioActivo();

								if (tipo == 0) {
									if (validar(0)) {
										aux.setNombre(txtNombre.getText());
										aux.setTelefono(txtTelefono.getText());
										aux.setCorreoElectronico(txtCorreo.getText());
										aux.setProvincia(cbxProvincia.getSelectedItem().toString());
										aux.setMunicipio(txtDireccion.getText());
										aux.setDireccion(txtDireccion.getText());
										Bolsa.getInstancia().modificarUsuario(aux);
										JOptionPane.showMessageDialog(null,
												"¡Información general modificada con éxito!", "Información",
												JOptionPane.INFORMATION_MESSAGE);
										dispose();// cerrar ventana
									} else {
										JOptionPane.showMessageDialog(null, "¡Complete correctamente los campos!",
												"Alerta", JOptionPane.WARNING_MESSAGE);
									}

								} else if (tipo == 1) {
									if (validar(1)) {
										aux.setRnc(txtRnc.getText());
										aux.setTipoEmpresa(cbxTipoEmp.getSelectedItem().toString());
										aux.setSectorEmpresarial(cbxSectorEmp.getSelectedItem().toString());
										Bolsa.getInstancia().modificarUsuario(aux);
										JOptionPane.showMessageDialog(null,
												"¡Información empresarial modificada con éxito!", "Información",
												JOptionPane.INFORMATION_MESSAGE);
										dispose();// cerrar ventana
									} else {
										JOptionPane.showMessageDialog(null, "¡Complete correctamente los campos!",
												"Alerta", JOptionPane.WARNING_MESSAGE);
									}
								} else {
									JOptionPane.showConfirmDialog(null, "La acción no pudo ser realizada.",
											"Advertencia", JOptionPane.WARNING_MESSAGE);
								}

							}
						}
					});
					btnModificar.setBounds(240, 13, 128, 30);
					btnModificar.setActionCommand("OK");
					buttonPane.add(btnModificar);
					getRootPane().setDefaultButton(btnModificar);
				}
				{
					JButton btnCancelar = new JButton("Cancelar");
					btnCancelar.setBackground(Color.WHITE);
					btnCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
					btnCancelar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int validar = JOptionPane.showConfirmDialog(null, "¿Desea cerrar esta pestaña?",
									"Información", JOptionPane.YES_NO_OPTION);
							if (validar == 1) {
								JOptionPane.showMessageDialog(null, "Acción cancelada", "Información",
										JOptionPane.INFORMATION_MESSAGE, null);
							} else {
								dispose();
							}
						}
					});
					btnCancelar.setBounds(369, 13, 128, 30);
					btnCancelar.setActionCommand("Cancel");
					buttonPane.add(btnCancelar);
				}
			}

			jtpEditarInformacion = new JTabbedPane(JTabbedPane.TOP);
			jtpEditarInformacion.setBackground(Color.WHITE);
			jtpEditarInformacion.setBounds(-12, -24, 531, 533);
			pnlPrincipal.add(jtpEditarInformacion);

			JPanel panelDatosGenerales = new JPanel();
			panelDatosGenerales.setBackground(Color.WHITE);
			jtpEditarInformacion.addTab("New tab", null, panelDatosGenerales, null);
			panelDatosGenerales.setLayout(null);

			JLabel iconLaborea = new JLabel("New label");
			iconLaborea.setIcon(new ImageIcon(EditarInfoEmpresa.class.getResource("/img/Laborea.png")));
			iconLaborea.setHorizontalAlignment(SwingConstants.CENTER);
			iconLaborea.setBounds(22, 15, 150, 50);
			panelDatosGenerales.add(iconLaborea);

			JLabel lblDatos = new JLabel("Editar datos generales");
			lblDatos.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			lblDatos.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDatos.setBounds(285, 12, 229, 50);
			panelDatosGenerales.add(lblDatos);

			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
			lblNombre.setBounds(22, 109, 229, 30);
			panelDatosGenerales.add(lblNombre);

			JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
			lblTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			lblTelefono.setHorizontalAlignment(SwingConstants.LEFT);
			lblTelefono.setBounds(272, 109, 229, 30);
			panelDatosGenerales.add(lblTelefono);

			JLabel lblCorreo = new JLabel("Correo el\u00E9ctronico:");
			lblCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			lblCorreo.setHorizontalAlignment(SwingConstants.LEFT);
			lblCorreo.setBounds(22, 183, 479, 30);
			panelDatosGenerales.add(lblCorreo);

			JLabel lblProvincia = new JLabel("Provincia:");
			lblProvincia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			lblProvincia.setHorizontalAlignment(SwingConstants.LEFT);
			lblProvincia.setBounds(22, 253, 479, 30);
			panelDatosGenerales.add(lblProvincia);

			JLabel lblMunicipio = new JLabel("Municipio:");
			lblMunicipio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			lblMunicipio.setHorizontalAlignment(SwingConstants.LEFT);
			lblMunicipio.setBounds(22, 328, 479, 30);
			panelDatosGenerales.add(lblMunicipio);

			JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
			lblDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			lblDireccion.setHorizontalAlignment(SwingConstants.LEFT);
			lblDireccion.setBounds(23, 402, 478, 30);
			panelDatosGenerales.add(lblDireccion);

			txtNombre = new JTextField();
			txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			txtNombre.setBounds(22, 140, 229, 30);
			panelDatosGenerales.add(txtNombre);
			txtNombre.setColumns(10);

			txtCorreo = new JTextField();
			txtCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			txtCorreo.setBounds(22, 214, 479, 30);
			panelDatosGenerales.add(txtCorreo);
			txtCorreo.setColumns(10);

			txtDireccion = new JTextField();
			txtDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			txtDireccion.setBounds(23, 434, 478, 30);
			panelDatosGenerales.add(txtDireccion);
			txtDireccion.setColumns(10);

			txtMunicipio = new JTextField();
			txtMunicipio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			txtMunicipio.setBounds(22, 359, 479, 30);
			panelDatosGenerales.add(txtMunicipio);
			txtMunicipio.setColumns(10);

			txtTelefono = new JTextField();
			txtTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			txtTelefono.setBounds(272, 140, 229, 30);
			panelDatosGenerales.add(txtTelefono);
			txtTelefono.setColumns(10);

			cbxProvincia = new JComboBox();
			cbxProvincia.setModel(new DefaultComboBoxModel(new String[] { "<< Seleccione >>", "Azua", "Bahoruco",
					"Barahona", "Dajab\u00F3n", "Distrito Nacional", "Duarte", "El\u00EDas Pi\u00F1a", "El Seibo",
					"Espaillat", "Hato Mayor", "Hermanas Mirabal", "Independencia", "La Altagracia", "La Romana",
					"La Vega", "Mar\u00EDa Trinidad S\u00E1nchez", "Monse\u00F1or Nouel", "Monte Cristi", "Monte Plata",
					"Pedernales", "Peravia", "Puerto Plata", "Saman\u00E1", "San Crist\u00F3bal",
					"San Jos\u00E9 de Ocoa", "San Juan", "San Pedro de Macor\u00EDs", "S\u00E1nchez Ram\u00EDrez",
					"Santiago", "Santiago Rodr\u00EDguez", "Santo Domingo", "Valverde" }));
			cbxProvincia.setBackground(Color.WHITE);
			cbxProvincia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			cbxProvincia.setBounds(22, 284, 479, 30);
			panelDatosGenerales.add(cbxProvincia);

			JSeparator sptSubrayado = new JSeparator();
			sptSubrayado.setForeground(Color.BLACK);
			sptSubrayado.setBounds(12, 67, 514, 18);
			panelDatosGenerales.add(sptSubrayado);

			JPanel pnlDatosEmpresa = new JPanel();
			pnlDatosEmpresa.setBackground(Color.WHITE);
			jtpEditarInformacion.addTab("New tab", null, pnlDatosEmpresa, null);
			pnlDatosEmpresa.setLayout(null);

			JLabel iconLaborea2 = new JLabel("");
			iconLaborea2.setHorizontalAlignment(SwingConstants.CENTER);
			iconLaborea2.setVerticalAlignment(SwingConstants.CENTER);
			iconLaborea2.setBackground(Color.WHITE);
			iconLaborea2.setEnabled(true);
			iconLaborea2.setIcon(new ImageIcon(EditarInfoEmpresa.class.getResource("/img/Laborea.png")));
			iconLaborea2.setBounds(22, 15, 150, 50);
			pnlDatosEmpresa.add(iconLaborea2);

			JLabel lblDatosEmp = new JLabel("Editar datos empresariales");
			lblDatosEmp.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			lblDatosEmp.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDatosEmp.setBounds(288, 13, 226, 50);
			pnlDatosEmpresa.add(lblDatosEmp);

			JSeparator sptSubrayado2 = new JSeparator();
			sptSubrayado2.setForeground(Color.BLACK);
			sptSubrayado2.setBounds(12, 67, 514, 11);
			pnlDatosEmpresa.add(sptSubrayado2);

			JLabel lblRnc = new JLabel("RNC:");
			lblRnc.setHorizontalAlignment(SwingConstants.LEFT);
			lblRnc.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			lblRnc.setBounds(22, 140, 251, 30);
			pnlDatosEmpresa.add(lblRnc);

			JLabel lblTipoEmp = new JLabel("Tipo de empresa");
			lblTipoEmp.setHorizontalAlignment(SwingConstants.LEFT);
			lblTipoEmp.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			lblTipoEmp.setBounds(22, 231, 482, 30);
			pnlDatosEmpresa.add(lblTipoEmp);

			JLabel lblSectorEmp = new JLabel("Sector:");
			lblSectorEmp.setHorizontalAlignment(SwingConstants.LEFT);
			lblSectorEmp.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			lblSectorEmp.setBounds(22, 334, 482, 30);
			pnlDatosEmpresa.add(lblSectorEmp);

			txtRnc = new JTextField();
			txtRnc.setBackground(Color.WHITE);
			txtRnc.setHorizontalAlignment(SwingConstants.LEFT);
			txtRnc.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			txtRnc.setBounds(22, 172, 482, 30);
			pnlDatosEmpresa.add(txtRnc);
			txtRnc.setColumns(10);

			cbxSectorEmp = new JComboBox();
			cbxSectorEmp.setModel(new DefaultComboBoxModel(
					new String[] { "<< Seleccione >>", "P\u00FAblico", "Privado", "Semi-P\u00FAblico" }));
			cbxSectorEmp.setBackground(Color.WHITE);
			cbxSectorEmp.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			cbxSectorEmp.setBounds(22, 367, 482, 30);
			pnlDatosEmpresa.add(cbxSectorEmp);

			cbxTipoEmp = new JComboBox();
			cbxTipoEmp.setModel(new DefaultComboBoxModel(new String[] { "<< Seleccione >>", "Agroindustrial",
					"Agropecuaria", "Alimenticia", "Artesanal", "Automotriz", "Aviaci\u00F3n", "Biotecnol\u00F3gica",
					"Cinematogr\u00E1fica", "Comercial", "Comunicaciones", "Constructora", "Consultora",
					"Cosm\u00E9tica", "De Energ\u00EDas Renovables", "De Exportaci\u00F3n e Importaci\u00F3n",
					"De Log\u00EDstica", "De Marketing y Publicidad", "De Medios de Comunicaci\u00F3n",
					"De Miner\u00EDa", "De Moda y Textil", "De Reciclaje", "De Seguridad", "De Servicios Financieros",
					"De Software", "De Telecomunicaciones", "De Transporte", "De Turismo", "De Videojuegos",
					"Editorial", "Educativa", "Electrodom\u00E9sticos", "Electr\u00F3nica", "Energ\u00E9tica",
					"Farmac\u00E9utica", "Financiera", "Gastron\u00F3mica", "Hospitalaria", "Inmobiliaria",
					"Instituci\u00F3n Bancaria", "Instituci\u00F3n de Seguros", "Metal\u00FArgica", "Naviera",
					"Petrolera", "Qu\u00EDmica", "Retail", "Salud y Bienestar", "Sider\u00FArgica", "Tecnol\u00F3gica",
					"Textil" }));
			cbxTipoEmp.setBackground(Color.WHITE);
			cbxTipoEmp.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			cbxTipoEmp.setBounds(22, 261, 482, 30);
			pnlDatosEmpresa.add(cbxTipoEmp);
		}
		// Mostrar pantalla según lo que se desee editar
		if (tipo == 0) {
			cargarDatosEmpresa(0);
			jtpEditarInformacion.setSelectedIndex(0);
		} else if (tipo == 1) {
			cargarDatosEmpresa(1);
			jtpEditarInformacion.setSelectedIndex(1);
		}
	}

	private void cargarDatosEmpresa(int tipo) {
		if (Bolsa.getUsuarioActivo() instanceof Empresa && Bolsa.getUsuarioActivo() != null) {
			Empresa aux = (Empresa) Bolsa.getUsuarioActivo();
			if (tipo == 0) {

				txtNombre.setText(aux.getNombre());
				txtTelefono.setText(aux.getTelefono());
				txtCorreo.setText(aux.getCorreoElectronico());
				cbxProvincia.setSelectedItem(aux.getProvincia());
				txtMunicipio.setText(aux.getMunicipio());
				txtDireccion.setText(aux.getDireccion());

			} else if (tipo == 1) {

				txtRnc.setText(aux.getRnc());
				cbxTipoEmp.setSelectedItem(aux.getTipoEmpresa());
				cbxSectorEmp.setSelectedItem(aux.getSectorEmpresarial());

			}
		}
	}

	private boolean validar(int tipo) {
		boolean valido = false;
		if (tipo == 0) {
			if (!txtNombre.getText().equals("") && !txtTelefono.getText().equals("") && !txtCorreo.getText().equals("")
					&& cbxProvincia.getSelectedIndex() > 0 && !txtMunicipio.getText().equals("")
					&& !txtDireccion.getText().equals("")) {
				valido = true;
			}
		} else if (tipo == 1) {
			if (!txtRnc.getText().equals("") && cbxTipoEmp.getSelectedIndex() > 0
					&& cbxSectorEmp.getSelectedIndex() > 0) {
				valido = true;
			}
		}
		return valido;
	}
}
