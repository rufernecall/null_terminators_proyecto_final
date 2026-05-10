package com.null_terminators.proyectofinal.scratch;

import com.null_terminators.proyectofinal.dao.DatabaseConnection;
import java.sql.*;

public class FixDatabase {
    public static void main(String[] args) {
        String drop1 = "DROP FUNCTION IF EXISTS registrar_venta_pro(bigint, bigint, numeric, jsonb)";
        String drop2 = "DROP FUNCTION IF EXISTS registrar_venta_pro(bigint, bigint, numeric, jsonb, text)";
        
        String create = 
            "CREATE OR REPLACE FUNCTION registrar_venta_pro(p_cli bigint, p_vend bigint, p_tot numeric, p_det jsonb, p_doc text) " +
            "RETURNS bigint AS $func$ " +
            "DECLARE " +
            "  v_vid bigint; " +
            "  v_it jsonb; " +
            "  v_ser text; " +
            "  v_num int; " +
            "BEGIN " +
            "  -- 1. Insertar Cabecera\n" +
            "  INSERT INTO ventas (cliente_id, vendedor_id, total, fecha, estado) " +
            "  VALUES (p_cli, p_vend, p_tot, now(), 'COMPLETADO') RETURNING id INTO v_vid; " +
            " " +
            "  -- 2. Insertar Detalles\n" +
            "  FOR v_it IN SELECT * FROM jsonb_array_elements(p_det) LOOP " +
            "    INSERT INTO detalles_venta (venta_id, producto_id, cantidad, precio_unitario, subtotal) " +
            "    VALUES (v_vid, (v_it->>'producto_id')::bigint, (v_it->>'cantidad')::int, (v_it->>'precio_unitario')::numeric, (v_it->>'subtotal')::numeric); " +
            " " +
            "    UPDATE productos SET stock = stock - (v_it->>'cantidad')::int WHERE id = (v_it->>'producto_id')::bigint; " +
            "  END LOOP; " +
            " " +
            "  -- 3. Comprobante\n" +
            "  v_ser := CASE WHEN p_doc = 'FACTURA' THEN 'F001' ELSE 'B001' END; " +
            "  SELECT COALESCE(MAX(numero), 0) + 1 INTO v_num FROM comprobantes WHERE tipo = p_doc AND serie = v_ser; " +
            "  INSERT INTO comprobantes (tipo, serie, numero, fecha, monto_total, estado, venta_id) " +
            "  VALUES (p_doc, v_ser, v_num, now(), p_tot, 'EMITIDO', v_vid); " +
            " " +
            "  RETURN v_vid; " +
            "END; " +
            "$func$ LANGUAGE plpgsql;";

        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement()) {
            
            // Ejecutar sin transaccion explicita para evitar problemas con el pooler de supabase
            try { st.execute(drop1); } catch (Exception e) { System.out.println("No se pudo borrar 1"); }
            try { st.execute(drop2); } catch (Exception e) { System.out.println("No se pudo borrar 2"); }
            st.execute(create);
            
            System.out.println("Version final (5 params) instalada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
