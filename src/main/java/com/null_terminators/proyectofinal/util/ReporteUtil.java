package com.null_terminators.proyectofinal.util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.null_terminators.proyectofinal.model.DetalleVenta;
import com.null_terminators.proyectofinal.model.Venta;
import java.awt.Color; // Importacion faltante
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.JFileChooser;

/**
 * Utilidad para generacion de boletas PDF con diseño profesional
 * 
 * @author rufernecall
 */
public class ReporteUtil {

    public static void generarBoletaPDF(Venta orden, List<DetalleVenta> detalles) {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("Boleta_" + orden.getId() + ".pdf"));

        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();

            Document document = new Document(PageSize.A4);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                PdfWriter.getInstance(document, fos);
                document.open();

                // Estilos de Fuente
                Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22, Color.DARK_GRAY);
                Font fontLabel = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
                Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 12);
                Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.WHITE);

                // Cabecera
                Paragraph titulo = new Paragraph("BOLETA DE VENTA", fontTitulo);
                titulo.setAlignment(Element.ALIGN_CENTER);
                titulo.setSpacingAfter(20);
                document.add(titulo);

                // Informacion de la Orden
                document.add(new Paragraph("Nº de Orden: " + orden.getId(), fontLabel));
                document.add(new Paragraph(
                        "Cliente: " + (orden.getCliente() != null ? orden.getCliente().getNombre() : "General"),
                        fontNormal));
                document.add(new Paragraph("Fecha: " + orden.getFecha().toString(), fontNormal));
                document.add(new Paragraph("Estado: " + orden.getEstado(), fontNormal));
                document.add(new Paragraph(" "));

                // Tabla de productos (Diseño moderno)
                PdfPTable table = new PdfPTable(4);
                table.setWidthPercentage(100);
                table.setSpacingBefore(10);
                table.setWidths(new float[] { 4, 1, 2, 2 });

                // Encabezados de tabla
                String[] headers = { "Producto", "Cant", "P. Unit", "Subtotal" };
                for (String header : headers) {
                    PdfPCell cell = new PdfPCell(new Paragraph(header, fontHeader));
                    cell.setBackgroundColor(new Color(50, 100, 255)); // Azul corporativo
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setPadding(8);
                    table.addCell(cell);
                }

                // Contenido de la tabla
                for (DetalleVenta d : detalles) {
                    table.addCell(new PdfPCell(
                            new Paragraph(d.getProducto() != null ? d.getProducto().getNombre() : "N/A", fontNormal)));

                    PdfPCell cCant = new PdfPCell(new Paragraph(String.valueOf(d.getCantidad()), fontNormal));
                    cCant.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cCant);

                    PdfPCell cPrecio = new PdfPCell(
                            new Paragraph(String.format("S/ %.2f", d.getPrecioUnitario()), fontNormal));
                    cPrecio.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    table.addCell(cPrecio);

                    PdfPCell cSub = new PdfPCell(new Paragraph(String.format("S/ %.2f", d.getSubtotal()), fontNormal));
                    cSub.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    table.addCell(cSub);
                }

                document.add(table);

                // Resumen Final
                Paragraph totalPar = new Paragraph("\nTOTAL A PAGAR: S/ " + String.format("%.2f", orden.getTotal()),
                        fontTitulo);
                totalPar.setAlignment(Element.ALIGN_RIGHT);
                document.add(totalPar);

                document.close();
                System.out.println("Comprobante generado: " + file.getAbsolutePath());

            } catch (Exception e) {
                System.err.println("Error critico PDF: " + e.getMessage());
            }
        }
    }
}
