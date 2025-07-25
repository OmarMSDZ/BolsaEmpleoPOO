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
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

public class VizualizarDatosEmpresa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static Empresa empresaActiva;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VizualizarDatosEmpresa dialog = new VizualizarDatosEmpresa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VizualizarDatosEmpresa() {
		setBounds(100, 100, 850, 950);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		empresaActiva = (Empresa) Bolsa.getUsuarioActivo();

		if (empresaActiva != null) {
			setTitle("Laborea - Perfil de: " + empresaActiva.getNombre() + " con RNC: " + empresaActiva.getRnc());
		} else {
			setTitle("Laborea - Perfil empresarial");
		}

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
					}
				});
				btnCerrar.setBounds(685, 13, 118, 31);
				btnCerrar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}
		
		JButton btnCambiarPasswd = new JButton("Cambiar contrase\u00F1a");
		btnCambiarPasswd.setBackground(Color.WHITE);
		btnCambiarPasswd.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnCambiarPasswd.setBounds(12, 703, 180, 38);
		pnlPrincipal.add(btnCambiarPasswd);
		
		JButton btnDesactivarCuenta = new JButton("Desactivar cuenta");
		btnDesactivarCuenta.setBackground(Color.WHITE);
		btnDesactivarCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDesactivarCuenta.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnDesactivarCuenta.setBounds(204, 703, 180, 38);
		pnlPrincipal.add(btnDesactivarCuenta);
		
		JLabel iconLaborea = new JLabel("");
		iconLaborea.setIcon(new ImageIcon(VizualizarDatosEmpresa.class.getResource("/img/Laborea.png")));
		iconLaborea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		iconLaborea.setHorizontalAlignment(SwingConstants.CENTER);
		iconLaborea.setBounds(0, 0, 150, 75);
		pnlPrincipal.add(iconLaborea);
		
		JLabel iconRegresar = new JLabel("");
		iconRegresar.setIcon(new ImageIcon(VizualizarDatosEmpresa.class.getResource("/img/flechaRegresar.png")));
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
		pnlDatosUsuario.setBackground(Color.WHITE);
		pnlDatosUsuario.setBounds(12, 213, 800, 250);
		pnlPrincipal.add(pnlDatosUsuario);
		pnlDatosUsuario.setLayout(null);
		
		JLabel lblInformacionUsuario = new JLabel("Informaci\u00F3n general:");
		lblInformacionUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblInformacionUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblInformacionUsuario.setBounds(0, 0, 353, 38);
		pnlDatosUsuario.add(lblInformacionUsuario);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 39, 800, 20);
		pnlDatosUsuario.add(separator);
		
		JButton btnEditarGeneral = new JButton("editar");
		btnEditarGeneral.setBackground(Color.WHITE);
		btnEditarGeneral.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnEditarGeneral.setIcon(new ImageIcon(VizualizarDatosEmpresa.class.getResource("/img/edit.png")));
		btnEditarGeneral.setBounds(684, 2, 118, 38);
		pnlDatosUsuario.add(btnEditarGeneral);
		
		JPanel pnlDatosEmpresariales = new JPanel();
		pnlDatosEmpresariales.setBackground(Color.WHITE);
		pnlDatosEmpresariales.setBounds(12, 498, 800, 192);
		pnlPrincipal.add(pnlDatosEmpresariales);
		pnlDatosEmpresariales.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Informaci\u00F3n empresa:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(0, 0, 353, 38);
		pnlDatosEmpresariales.add(lblNewLabel_1);
		
		JSeparator sptSubrayadoEmpresa = new JSeparator();
		sptSubrayadoEmpresa.setBounds(0, 37, 800, 24);
		pnlDatosEmpresariales.add(sptSubrayadoEmpresa);
		
		JButton btnEditarEmpresa = new JButton("Editar");
		btnEditarEmpresa.setBackground(Color.WHITE);
		btnEditarEmpresa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnEditarEmpresa.setIcon(new ImageIcon(VizualizarDatosEmpresa.class.getResource("/img/edit.png")));
		btnEditarEmpresa.setBounds(684, 0, 118, 38);
		pnlDatosEmpresariales.add(btnEditarEmpresa);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VizualizarDatosEmpresa.class.getResource("/img/iconBuilding.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(366, 117, 100, 75);
		pnlPrincipal.add(lblNewLabel);
	}
}
