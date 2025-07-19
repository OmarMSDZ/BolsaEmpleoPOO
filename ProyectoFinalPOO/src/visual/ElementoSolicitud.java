package visual;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

import logica.Solicitud;

public class ElementoSolicitud extends JPanel {

    private Solicitud soli;
   

    private JLabel lblTitulo;
    private JLabel lblSalario;
    private JLabel lblModalidad;
    private JLabel lblTipo;
    private JLabel lblFecha;
    private JLabel lblArea;

    public ElementoSolicitud(Solicitud soli) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.BLACK, 2, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        setBackground(Color.WHITE);
        setAlignmentX(LEFT_ALIGNMENT);

        this.soli = soli;

        lblTitulo = new JLabel("Título");
        lblTitulo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        lblTitulo.setAlignmentX(LEFT_ALIGNMENT);
        add(lblTitulo);
        
        lblArea = new JLabel("Area");
        lblArea.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblArea.setAlignmentX(0.0f);
        add(lblArea);

        lblSalario = new JLabel("Salario");
        lblSalario.setIcon(new ImageIcon(ElementoSolicitud.class.getResource("/img/money.png")));
        lblSalario.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblSalario.setAlignmentX(LEFT_ALIGNMENT);
        add(lblSalario);

        lblModalidad = new JLabel("Modalidad");
        lblModalidad.setIcon(new ImageIcon(ElementoSolicitud.class.getResource("/img/info.png")));
        lblModalidad.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblModalidad.setAlignmentX(LEFT_ALIGNMENT);
        add(lblModalidad);

        lblTipo = new JLabel("Tipo");
        lblTipo.setIcon(new ImageIcon(ElementoSolicitud.class.getResource("/img/briefcase.png")));
        lblTipo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblTipo.setAlignmentX(LEFT_ALIGNMENT);
        add(lblTipo);

        lblFecha = new JLabel("Fecha");
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblFecha.setAlignmentX(LEFT_ALIGNMENT);
        add(lblFecha);

        if (soli.getCodigo() != null) lblTitulo.setText("Solicitud #"+soli.getCodigo());
        if(soli.getArea()!=null) lblArea.setText(soli.getArea());
        if(soli.getSalarioDeseado()>0) {
        	lblSalario.setText(String.valueOf(soli.getSalarioDeseado()));
        } else {
        	lblSalario.setText("No especificado");	
        }
        if (soli.getModalidad() != null) lblModalidad.setText(soli.getModalidad());
        if (soli.getTipoEmpleo() != null) lblTipo.setText(soli.getTipoEmpleo());
        if (soli.getFechaSolicitud() != null) lblFecha.setText(String.valueOf(soli.getFechaSolicitud()));

        SwingUtilities.invokeLater(() -> {
            setMaximumSize(new Dimension(Integer.MAX_VALUE, getPreferredSize().height));
            revalidate();
        });
    }

    // === Getters y Setters ===

    public Solicitud getSolicitud() {
        return soli;
    }

    public void setSolicitud(Solicitud soli) {
        this.soli = soli;
    }

}
