/*
 * Erweiterbarer Mapper für alle persistente Daten des Bankprojekts.
 * Er stellt ein Cashing für die Datenobjekte zur Verfügung, so dass bei Datenbank-
 * zugriffen geprüft wird, ob das gesuchte Objekt schon existiert.
 */

package de.hdm.bankProject.db;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Christian Rathke
 */
public class DataMapper {
    /**
     * Object cache für Speichern und Zugriff
     */
    private Map<Integer,Object> rememberedObjects = new HashMap();

    protected Object checkForObject (int id) {
        Object data = rememberedObjects.get(id);
        if (data == null) return null;
        return data;
    }

    protected void rememberObject(int id, Object data) {
        rememberedObjects.put(id, data);
    }

    protected void removeObject(int id) {
        rememberedObjects.remove(id);
    }
}
