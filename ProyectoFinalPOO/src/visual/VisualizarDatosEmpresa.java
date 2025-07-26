package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import logica.Bolsa;
import logica.Empresa;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VisualizarDatosEmpresa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static Empresa empresaActiva;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VisualizarDatosEmpresa dialog = new VisualizarDatosEmpresa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VisualizarDatosEmpresa() {
		setBounds(100, 100, 850, 950);
		setLocationRelativeTo(null);

		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		empresaActiva = (Empresa) Bolsa.getUsuarioActivo();

		if (empresaActiva != null) {
			setTitle("Laborea - Perfil de: " + empresaActiva.getNombre() + " con RNC: " + empresaActiva.getRnc());
		} else {
			// Para pruebas
			empresaActiva = new Empresa(Bolsa.getInstancia().generarCodigoUsuario(), "Laborea contratistas", "1234",
					"849-352-5887", "mxtn0001@ce.pucmm.edu.do", "Santiago", "Santiago de los Caballeros",
					"Casa de Omar", true, "123456789012", "Desarrollo de software", "Privado");
			Bolsa.getInstancia().insertarUsuario(empresaActiva);
			Bolsa.setUsuarioActivo(empresaActiva);
			setTitle("Laborea - Perfil empresarial");
		}
		SwingUtilities.updateComponentTreeUI(this);
		JPanel pnlPrincipal = new JPanel();
		pnlPrincipal.setBackground(Color.WHITE);

		pnlPrincipal.setBounds(0, 0, 832, 903);
		contentPanel.add(pnlPrincipal);
		pnlPrincipal.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setBounds(0, 844, 832, 59);
			pnlPrincipal.add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton btnRecargar = new JButton("Recargar");
				btnRecargar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
					}
				});
				btnRecargar.setBackground(Color.WHITE);
				btnRecargar.setBounds(555, 13, 118, 31);
				btnRecargar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
				btnRecargar.setActionCommand("OK");
				buttonPane.add(btnRecargar);
				getRootPane().setDefaultButton(btnRecargar);
			}
			{
				JButton btnCerrar = new JButton("Cerrar");
				btnCerrar.setBackground(Color.WHITE);
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int validar = JOptionPane.showConfirmDialog(null, "¿Desea cerrar esta pestaña?", "Información",
								JOptionPane.YES_NO_OPTION);
						if (validar == 1) {
							JOptionPane.showMessageDialog(null, "Acción cancelada", "Información",
									JOptionPane.INFORMATION_MESSAGE, null);
						} else {
							dispose();
						}
					}
				});
				btnCerrar.setBounds(685, 13, 118, 31);
				btnCerrar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}

		JButton btnCambiarPasswd = new JButton("Cambiar contrase\u00F1a");
		btnCambiarPasswd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String actual = JOptionPane.showInputDialog(null, "Ingrese su contraseña actual:", "Verificación",
						JOptionPane.PLAIN_MESSAGE);

				if (actual == null)
					return;

				if (!empresaActiva.getPasswd().equals(actual)) {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta. No se pudo cambiar.", "Advertencia",
							JOptionPane.ERROR_MESSAGE, null);
					return;
				}

				String nuevaPasswd = JOptionPane.showInputDialog(null, "Ingrese su nueva contraseña:",
						"Cambio de contraseña", JOptionPane.PLAIN_MESSAGE);

				if (nuevaPasswd == null || nuevaPasswd.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No se ingresó una nueva contraseña.", "Alerta",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				// Modificar datos empresa
				Bolsa.getInstancia().modificarUsuario(empresaActiva);

				JOptionPane.showMessageDialog(null, "Contraseña actualizada exitosamente.", "Información",
						JOptionPane.INFORMATION_MESSAGE, null);
			}
		});
		btnCambiarPasswd.setBackground(Color.WHITE);
		btnCambiarPasswd.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnCambiarPasswd.setBounds(22, 703, 180, 38);
		pnlPrincipal.add(btnCambiarPasswd);

		JButton btnDesactivarCuenta = new JButton("Desactivar cuenta");
		btnDesactivarCuenta.setEnabled(false);
		btnDesactivarCuenta.setBackground(Color.WHITE);
		btnDesactivarCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(null, "Introduzca su contraseña:", "Verificación",
						JOptionPane.PLAIN_MESSAGE);
				if (empresaActiva.getPasswd().equals(input)) {
					// Acción protegida
					// Bolsa.getInstancia().desactivarCuenta(empresaActiva);
				} else {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Acción denegada",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDesactivarCuenta.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnDesactivarCuenta.setBounds(214, 703, 180, 38);
		pnlPrincipal.add(btnDesactivarCuenta);

		JLabel iconLaborea = new JLabel("");
		iconLaborea.setIcon(new ImageIcon(VisualizarDatosEmpresa.class.getResource("/img/Laborea.png")));
		iconLaborea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		iconLaborea.setHorizontalAlignment(SwingConstants.CENTER);
		iconLaborea.setBounds(0, 0, 150, 75);
		pnlPrincipal.add(iconLaborea);

		JLabel iconRegresar = new JLabel("");
		iconRegresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int validar = JOptionPane.showConfirmDialog(null, "¿Seguro que desea volver atrás?", "Información",
						JOptionPane.YES_NO_OPTION);
				if (validar == 1) {
					JOptionPane.showMessageDialog(null, "Acción cancelada", "Información",
							JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					dispose();
				}
			}
		});
		iconRegresar.setIcon(new ImageIcon(VisualizarDatosEmpresa.class.getResource("/img/flechaRegresar.png")));
		iconRegresar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		iconRegresar.setHorizontalAlignment(SwingConstants.CENTER);
		iconRegresar.setBounds(732, 0, 100, 75);
		pnlPrincipal.add(iconRegresar);

		JLabel lblPerfil = new JLabel("Datos empresa");
		lblPerfil.setFont(new Font("Segoe UI", Font.BOLD, 28));
		lblPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerfil.setBounds(0, 69, 832, 38);
		pnlPrincipal.add(lblPerfil);

		JPanel pnlDatosUsuario = new JPanel();
		pnlDatosUsuario.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlDatosUsuario.setBackground(Color.WHITE);
		pnlDatosUsuario.setBounds(12, 213, 800, 250);
		pnlPrincipal.add(pnlDatosUsuario);
		pnlDatosUsuario.setLayout(null);

		JLabel lblInformacionUsuario = new JLabel("Informaci\u00F3n general:");
		lblInformacionUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblInformacionUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblInformacionUsuario.setBounds(22, 2, 353, 38);
		pnlDatosUsuario.add(lblInformacionUsuario);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(0, 43, 800, 20);
		pnlDatosUsuario.add(separator);

		JButton btnEditarGeneral = new JButton("editar");
		btnEditarGeneral.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EditarInfoEmpresa editDataEmpresa = new EditarInfoEmpresa(0);
				editDataEmpresa.setModal(true);
				editDataEmpresa.setVisible(true);
			}
		});
		btnEditarGeneral.setBackground(Color.WHITE);
		btnEditarGeneral.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnEditarGeneral.setIcon(new ImageIcon(VisualizarDatosEmpresa.class.getResource("/img/edit.png")));
		btnEditarGeneral.setBounds(688, 1, 100, 40);
		pnlDatosUsuario.add(btnEditarGeneral);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.DARK_GRAY);
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setBounds(22, 156, 168, 32);
		pnlDatosUsuario.add(lblNombre);

		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setForeground(Color.DARK_GRAY);
		lblCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCorreo.setBounds(223, 156, 168, 32);
		pnlDatosUsuario.add(lblCorreo);

		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setForeground(Color.DARK_GRAY);
		lblTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelefono.setBounds(223, 62, 168, 32);
		pnlDatosUsuario.add(lblTelefono);

		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setForeground(Color.DARK_GRAY);
		lblDireccion.setHorizontalAlignment(SwingConstants.LEFT);
		lblDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDireccion.setBounds(424, 62, 168, 32);
		pnlDatosUsuario.add(lblDireccion);

		JLabel lblProvincia = new JLabel("Provincia:");
		lblProvincia.setForeground(Color.DARK_GRAY);
		lblProvincia.setHorizontalAlignment(SwingConstants.LEFT);
		lblProvincia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblProvincia.setBounds(424, 156, 168, 32);
		pnlDatosUsuario.add(lblProvincia);

		JLabel lblMunicipio = new JLabel("Municipio:");
		lblMunicipio.setForeground(Color.DARK_GRAY);
		lblMunicipio.setHorizontalAlignment(SwingConstants.LEFT);
		lblMunicipio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMunicipio.setBounds(625, 156, 168, 32);
		pnlDatosUsuario.add(lblMunicipio);

		JLabel lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setForeground(Color.DARK_GRAY);
		lblCodigo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCodigo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCodigo.setBounds(22, 62, 168, 32);
		pnlDatosUsuario.add(lblCodigo);

		JLabel lblMostrarCodigo = new JLabel("C\u00F3digo");
		lblMostrarCodigo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMostrarCodigo.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarCodigo.setBounds(22, 94, 168, 32);
		pnlDatosUsuario.add(lblMostrarCodigo);

		JLabel lblMostrarTelefono = new JLabel("Tel\u00E9fono");
		lblMostrarTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMostrarTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarTelefono.setBounds(223, 94, 168, 32);
		pnlDatosUsuario.add(lblMostrarTelefono);

		JLabel lblMostrarDireccion = new JLabel("Direcci\u00F3n");
		lblMostrarDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMostrarDireccion.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarDireccion.setBounds(424, 94, 168, 32);
		pnlDatosUsuario.add(lblMostrarDireccion);

		JLabel lblMostrarNombre = new JLabel("Nombre");
		lblMostrarNombre.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMostrarNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarNombre.setBounds(22, 189, 168, 32);
		pnlDatosUsuario.add(lblMostrarNombre);

		JLabel lblMostrarCorreo = new JLabel("Correo");
		lblMostrarCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMostrarCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarCorreo.setBounds(223, 189, 168, 32);
		pnlDatosUsuario.add(lblMostrarCorreo);

		JLabel lblMostrarProvincia = new JLabel("Provincia");
		lblMostrarProvincia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMostrarProvincia.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarProvincia.setBounds(424, 189, 168, 32);
		pnlDatosUsuario.add(lblMostrarProvincia);

		JLabel lblMostrarMunicipio = new JLabel("Municipio");
		lblMostrarMunicipio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMostrarMunicipio.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarMunicipio.setBounds(625, 189, 168, 32);
		pnlDatosUsuario.add(lblMostrarMunicipio);

		JPanel pnlDatosEmpresariales = new JPanel();
		pnlDatosEmpresariales.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlDatosEmpresariales.setBackground(Color.WHITE);
		pnlDatosEmpresariales.setBounds(12, 498, 800, 192);
		pnlPrincipal.add(pnlDatosEmpresariales);
		pnlDatosEmpresariales.setLayout(null);

		JLabel lblInformacionEmp = new JLabel("Informaci\u00F3n empresa:");
		lblInformacionEmp.setHorizontalAlignment(SwingConstants.LEFT);
		lblInformacionEmp.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblInformacionEmp.setBounds(23, 0, 353, 38);
		pnlDatosEmpresariales.add(lblInformacionEmp);

		JSeparator sptSubrayadoEmpresa = new JSeparator();
		sptSubrayadoEmpresa.setForeground(Color.BLACK);
		sptSubrayadoEmpresa.setBounds(0, 47, 800, 24);
		pnlDatosEmpresariales.add(sptSubrayadoEmpresa);

		JButton btnEditarEmpresa = new JButton("Editar");
		btnEditarEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarInfoEmpresa editDataEmpresa = new EditarInfoEmpresa(1);
				editDataEmpresa.setModal(true);
				editDataEmpresa.setVisible(true);
			}
		});
		btnEditarEmpresa.setBackground(Color.WHITE);
		btnEditarEmpresa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnEditarEmpresa.setIcon(new ImageIcon(VisualizarDatosEmpresa.class.getResource("/img/edit.png")));
		btnEditarEmpresa.setBounds(688, 6, 100, 40);
		pnlDatosEmpresariales.add(btnEditarEmpresa);

		JLabel lblRnc = new JLabel("RCN:");
		lblRnc.setForeground(Color.DARK_GRAY);
		lblRnc.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblRnc.setHorizontalAlignment(SwingConstants.LEFT);
		lblRnc.setBounds(23, 74, 168, 32);
		pnlDatosEmpresariales.add(lblRnc);

		JLabel lblTipoEmp = new JLabel("Tipo de empresa:");
		lblTipoEmp.setForeground(Color.DARK_GRAY);
		lblTipoEmp.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTipoEmp.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipoEmp.setBounds(265, 74, 168, 32);
		pnlDatosEmpresariales.add(lblTipoEmp);

		JLabel lblSector = new JLabel("Sector:");
		lblSector.setForeground(Color.DARK_GRAY);
		lblSector.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblSector.setHorizontalAlignment(SwingConstants.LEFT);
		lblSector.setBounds(507, 74, 168, 32);
		pnlDatosEmpresariales.add(lblSector);

		JLabel lblMostrarRnc = new JLabel("RNC");
		lblMostrarRnc.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMostrarRnc.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarRnc.setBounds(23, 107, 168, 32);
		pnlDatosEmpresariales.add(lblMostrarRnc);

		JLabel lblMostrarTipoEmp = new JLabel("Tipo de empresa");
		lblMostrarTipoEmp.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMostrarTipoEmp.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarTipoEmp.setBounds(265, 107, 168, 32);
		pnlDatosEmpresariales.add(lblMostrarTipoEmp);

		JLabel lblMostrarSector = new JLabel("Sector");
		lblMostrarSector.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMostrarSector.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarSector.setBounds(507, 107, 168, 32);
		pnlDatosEmpresariales.add(lblMostrarSector);

		JLabel iconBuilding = new JLabel("");
		iconBuilding.setIcon(new ImageIcon(VisualizarDatosEmpresa.class.getResource("/img/iconBuilding.png")));
		iconBuilding.setHorizontalAlignment(SwingConstants.CENTER);
		iconBuilding.setBounds(366, 117, 100, 75);
		pnlPrincipal.add(iconBuilding);
	}

	private void cargarInformacionEmpresa() {
		// Datos generales

		// Información empresarial
	}
}
